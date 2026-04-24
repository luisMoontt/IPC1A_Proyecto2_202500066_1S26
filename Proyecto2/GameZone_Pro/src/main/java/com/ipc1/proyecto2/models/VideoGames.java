package com.ipc1.proyecto2.models;

/**
 *
 * @author Admin
 */
public class VideoGames {
  private String id_videogame;
  private String name;
  private String genre;
  private double price;
  private String plataform;
  private int stock;
  private String description;

    public VideoGames() {
    }

    public VideoGames(String id_videogame, String name, String genre, double price, String plataform, int stock, String description) {
        this.id_videogame = id_videogame;
        this.name = name;
        this.genre = genre;
        this.price = price;
        this.plataform = plataform;
        this.stock = stock;
        this.description = description;
    }

    public String getId_videogame() {
        return id_videogame;
    }

    public void setId_videogame(String id_videogame) {
        this.id_videogame = id_videogame;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPlataform() {
        return plataform;
    }

    public void setPlataform(String plataform) {
        this.plataform = plataform;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
