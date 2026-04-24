package com.ipc1.proyecto2.models;

/**
 *
 * @author Admin
 */
public class Card {
    private String id_card;
    private String name;
    private String type;
    private String rarity;
    private int attack;
    private int defense;
    private int ps;
    private String image;

    public Card() {
    }

    public Card(String id_card, String name, String type, String rarity, int attack, int defense, int ps, String image) {
        this.id_card = id_card;
        this.name = name;
        this.type = type;
        this.rarity = rarity;
        this.attack = attack;
        this.defense = defense;
        this.ps = ps;
        this.image = image;
    }
    
    

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getPs() {
        return ps;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
