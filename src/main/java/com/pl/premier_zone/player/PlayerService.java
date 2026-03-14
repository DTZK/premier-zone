package com.pl.premier_zone.player;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;

//this file should be managed by the container
//service player handles business logic and is between controller and repo
@Component
public class PlayerService {
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository){
        this.playerRepository=playerRepository;
    }

    public List<Player> getPlayers(){
        return playerRepository.findAll();
    }

    public List<Player> getPlayersFromTeam(String teamName){
        return playerRepository.findAll().stream()
        .filter(player -> teamName.equals(player.getTeamName()))
        .collect(Collectors.toList());
    }

    public List<Player> getPlayersByName(String search){
        return playerRepository.findAll().stream()
        .filter(player -> player.getPlayerName().toLowerCase().contains(search))
        .collect(Collectors.toList());
    }

    public List<Player> getPlayersByPos(String search){
        return playerRepository.findAll().stream()
        .filter(player -> player.getPosition().toLowerCase()
        .contains(search)).collect(Collectors.toList());
    }

    public List<Player> getPlayersByNation(String search){
        return playerRepository.findAll().stream()
        .filter(player -> player.getNation().toLowerCase()
        .contains(search.toLowerCase())).collect(Collectors.toList());
    }

    public List<Player> getPlayersByTeamAndPos(String team, String position){
        return playerRepository.findAll().stream()
        .filter(player -> team.equals(player.getTeamName()) && position.equals(player.getPosition()))
        .collect(Collectors.toList());
    } 

    public Player addPlayer(Player player){
        playerRepository.save(player);
        return player;
    }

    public Player updatePlayer(Player updatedPlayer){
        Optional<Player> existingPlayer = playerRepository.findByPlayerName(updatedPlayer.getPlayerName());
        if (existingPlayer.isPresent()){
            Player toUpdatePlayer = existingPlayer.get();
            toUpdatePlayer.setPlayerName(updatedPlayer.getPlayerName());
            toUpdatePlayer.setTeamName(updatedPlayer.getTeamName());
            toUpdatePlayer.setPosition(updatedPlayer.getPosition());
            toUpdatePlayer.setNation(updatedPlayer.getNation());

            playerRepository.save(toUpdatePlayer);
            return toUpdatePlayer;
        }
        return null;
    }

    //makes operation is a transaction, for data integrity
    @Transactional
    public void deletePlayer(String name){
        playerRepository.deleteByPlayerName(name);
    }
}
