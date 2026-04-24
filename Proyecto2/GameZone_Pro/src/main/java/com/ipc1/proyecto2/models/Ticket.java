package com.ipc1.proyecto2.models;

/**
 *
 * @author Admin
 */
public class Ticket {
    private String id_ticket;
    private String id_user;
    private String id_tournament;
    private String purchaseDate;
    private String purchaseTime;

    public Ticket() {
    }

    public Ticket(String id_ticket, String id_user, String id_tournament, String purchaseDate, String purchaseTime) {
        this.id_ticket = id_ticket;
        this.id_user = id_user;
        this.id_tournament = id_tournament;
        this.purchaseDate = purchaseDate;
        this.purchaseTime = purchaseTime;
    }

    public String getId_ticket() {
        return id_ticket;
    }

    public void setId_ticket(String id_ticket) {
        this.id_ticket = id_ticket;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getId_tournament() {
        return id_tournament;
    }

    public void setId_tournament(String id_tournament) {
        this.id_tournament = id_tournament;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(String purchaseTime) {
        this.purchaseTime = purchaseTime;
    } 
}
