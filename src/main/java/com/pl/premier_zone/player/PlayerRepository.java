package com.pl.premier_zone.player;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    void deleteByPlayerName(String playerName);
    Optional<Player> findByPlayerName(String playerName);
}