package com.ipc1.proyecto2.models;

public class ShoppingRecord {

    private String id_videogame;   
    private String name_videogame; 
    private String id_user;        
    private String date;           
    private String time;           
    private int    quantity;      
    private double total;          

    public ShoppingRecord() {
    }

    public ShoppingRecord(String id_videogame, String name_videogame, String id_user, String date, String time, int quantity, double total) {
        this.id_videogame = id_videogame;
        this.name_videogame = name_videogame;
        this.id_user = id_user;
        this.date = date;
        this.time = time;
        this.quantity = quantity;
        this.total = total;
    }

    public String getId_videogame() {
        return id_videogame;
    }

    public void setId_videogame(String id_videogame) {
        this.id_videogame = id_videogame;
    }

    public String getName_videogame() {
        return name_videogame;
    }

    public void setName_videogame(String name_videogame) {
        this.name_videogame = name_videogame;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return String.format(
            "[%s] %s | User: %s | %s %s | x%d | Q%.2f",
            id_videogame, name_videogame, id_user, date, time, quantity, total
        );
    }
}
