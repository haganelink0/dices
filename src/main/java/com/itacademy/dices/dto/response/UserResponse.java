package com.itacademy.dices.dto.response;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class UserResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "registration_date", nullable = false, updatable = false)
    @CreatedDate
    private Date registrationDate;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval=true)
    private List<GameResponse> gameList;
    
    @Column(name = "winrate")
    private Double winrate;

    public UserResponse() {
    }

    public UserResponse(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Double getWinrate() {
        return winrate;
    }

    public void setWinrate(Double winrate) {
        this.winrate = winrate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<GameResponse> getGameList() {
        return gameList;
    }

    public void setGameList(List<GameResponse> gameList) {
        this.gameList = gameList;
    }

    public void addGame(GameResponse game) {
        gameList.add(game);
    }

    public void removeGame(GameResponse game) {
        gameList.remove(game);
    }



}
