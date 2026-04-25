package com.ipc1.proyecto2.models;

/**
 * @author Admin
 */
public class Achievements {
    private int id_achievement;
    private String id_user;
    private String name;
    private String description;
    private boolean state;

    public Achievements() {
    }

    public Achievements(int id_achievement, String id_user, String name, String description, boolean state) {
        this.id_achievement = id_achievement;
        this.id_user = id_user;
        this.name = name;
        this.description = description;
        this.state = state;
    }

    public int getId_achievement() {
        return id_achievement;
    }

    public void setId_achievement(int id_achievement) {
        this.id_achievement = id_achievement;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
    
    
}
