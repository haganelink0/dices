package com.itacademy.dices.dto.pojo;


public class UserPojo  {

    private int id;
    private String name;

    public UserPojo() {
    }

    public UserPojo(int id, String name) {
        this.id = id;
        this.name = name;
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
}
