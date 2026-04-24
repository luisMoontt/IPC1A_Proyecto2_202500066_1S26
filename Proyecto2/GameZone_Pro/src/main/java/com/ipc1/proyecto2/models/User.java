package com.ipc1.proyecto2.models;

/**
 *
 * @author Admin
 */
public class User {
    private String username;
    private String password;
    private int xp;
    private int level;

    public User() {
    }

    public User(String username, String password, int xp, int level) {
        this.username = username;
        this.password = password;
        this.xp = xp;
        this.level = level;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
