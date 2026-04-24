package com.ipc1.proyecto2.models;

/**
 *
 * @author Admin
 */
public class Tournament {
    private String id_tournament;
    private String name;
    private String game;
    private String date;
    private String time;
    private double priceTickets;
    private int disableTickes;

    public Tournament() {
    }

    public Tournament(String id_tournament, String name, String game, String date, String time, double priceTickets, int disableTickes) {
        this.id_tournament = id_tournament;
        this.name = name;
        this.game = game;
        this.date = date;
        this.time = time;
        this.priceTickets = priceTickets;
        this.disableTickes = disableTickes;
    }

    public String getId_tournament() {
        return id_tournament;
    }

    public void setId_tournament(String id_tournament) {
        this.id_tournament = id_tournament;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getPriceTickets() {
        return priceTickets;
    }

    public void setPriceTickets(double priceTickets) {
        this.priceTickets = priceTickets;
    }

    public int getDisableTickes() {
        return disableTickes;
    }

    public void setDisableTickes(int disableTickes) {
        this.disableTickes = disableTickes;
    }    
}
