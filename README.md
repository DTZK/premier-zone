# premier-zone
Learning how to create a Java backend coding project using Spring Boot and PostgreSQL

A RESTful API built with Spring Boot that serves Premier League player statistics. This project demonstrates end-to-end backend development — from database design and data ingestion to API development and deployment-ready architecture.

--
##TODO:
Work on Frontend(React)

---

## 📌 Project Overview

PremierZone is a data-driven backend application that stores, manages, and exposes Premier League player stats through a clean REST API. It was built to practise real-world skills in SQL, data modelling, and backend engineering.

**Key capabilities:**
- Query players by team, name, position, nation, or a combination
- Add, update, and delete player records
- Filter and search across a full Premier League dataset

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot 4.0 |
| Database | PostgreSQL 17 |
| ORM | Hibernate / Spring Data JPA |
| Build Tool | Maven |
| Version Control | Git / GitHub |

---

## 📊 Dataset

The dataset contains Premier League player statistics, including:

| Column | Description |
|---|---|
| `player_name` | Full name of the player |
| `nation` | Player's nationality |
| `position` | Playing position (FW, MF, DF, GK) |
| `age` | Player's age |
| `matches_played` | Total matches played |
| `starts` | Number of starts |
| `minutes_played` | Total minutes played |
| `goals` | Goals scored |
| `assists` | Assists provided |
| `penalties_scored` | Penalties scored |
| `yellow_cards` | Yellow cards received |
| `red_cards` | Red cards received |
| `expected_goals` | xG (Expected Goals) |
| `expected_assists` | xAG (Expected Assisted Goals) |
| `team_name` | Club name |

---

## 🔌 API Endpoints

Base URL: `http://localhost:8080/api/1/player`

### Get Players
```
GET /api/1/player
```
Returns all players. Supports the following optional query parameters:

| Parameter | Example | Description |
|---|---|---|
| `team` | `?team=Arsenal` | Filter by team |
| `name` | `?name=salah` | Search by player name |
| `position` | `?position=FW` | Filter by position |
| `nation` | `?nation=ENG` | Filter by nationality |
| `team` + `position` | `?team=Arsenal&position=FW` | Filter by team and position |

### Add Player
```
POST /api/1/player
Content-Type: application/json
```
```json
{
  "playerName": "John Doe",
  "nation": "ENG",
  "position": "FW",
  "age": 23,
  "matchesPlayed": 10,
  "starts": 8,
  "minutesPlayed": 720.0,
  "goals": 5.0,
  "assists": 3.0,
  "penaltiesScored": 1.0,
  "yellowCards": 1.0,
  "redCards": 0.0,
  "expectedGoals": 4.5,
  "expectedAssists": 2.8,
  "teamName": "Arsenal"
}
```

### Update Player
```
PUT /api/1/player
Content-Type: application/json
```
Pass the updated player object in the request body (matched by player name).

### Delete Player
```
DELETE /api/1/player/{playerName}
```
Example: `DELETE /api/1/player/Mohamed Salah`

---

## 🗄️ Database Setup

1. Install PostgreSQL and create the database:
```sql
CREATE DATABASE prem_stats;
```

2. Create the player_stats table:
```sql
CREATE TABLE player_stats (
    id SERIAL PRIMARY KEY,
    player_name VARCHAR(150),
    nation VARCHAR(50),
    position VARCHAR(50),
    age INTEGER,
    matches_played INTEGER,
    starts INTEGER,
    minutes_played FLOAT,
    goals FLOAT,
    assists FLOAT,
    penalties_scored FLOAT,
    yellow_cards FLOAT,
    red_cards FLOAT,
    expected_goals FLOAT,
    expected_assists FLOAT,
    team_name VARCHAR(100)
);
```

3. Import the CSV data:
```sql
\COPY player_stats FROM 'path/to/prem_stats.csv' DELIMITER ',' CSV HEADER;
```

---

## ⚙️ Getting Started

### Prerequisites
- Java 17+
- PostgreSQL 17
- Maven

### Configuration

Create `src/main/resources/application.properties` (not committed to GitHub):
```properties
spring.application.name=PremierZone
spring.datasource.url=jdbc:postgresql://localhost:5432/prem_stats
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

See `application.properties.example` for a template.

### Run the Application
```bash
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`.

---

## 📁 Project Structure

```
premier-zone/
├── src/
│   └── main/
│       ├── java/com/pl/premier_zone/
│       │   └── player/
│       │       ├── Player.java          # Entity / data model
│       │       ├── PlayerRepository.java # Database access layer
│       │       ├── PlayerService.java    # Business logic layer
│       │       └── PlayerController.java # REST API layer
│       └── resources/
│           └── application.properties.example
├── pom.xml
└── README.md
```

---

## 🔮 Planned Features

- [ ] Add player performance rankings (top scorers, most assists)
- [ ] Aggregate stats by team and nationality
- [ ] Frontend dashboard to visualise player data
- [ ] Pagination and sorting on API responses

---

## 👤 Author

**Dickson Tay**  
GitHub: [@DTZK](https://github.com/DTZK)
