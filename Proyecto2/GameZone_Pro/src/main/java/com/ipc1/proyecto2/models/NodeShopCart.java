package com.ipc1.proyecto2.models;

/**
 *
 * @author Admin
 */
public class NodeShopCart {

    private VideoGames  videogame;
    private int         cantidad;
    private NodeShopCart siguiente;

    public NodeShopCart(VideoGames videogame, int cantidad) {
        this.videogame  = videogame;
        this.cantidad   = cantidad;
        this.siguiente  = null;
    }

    public VideoGames getVideogame() {
        return videogame;
    }

    public void setVideogame(VideoGames videogame) {
        this.videogame = videogame;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public NodeShopCart getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodeShopCart siguiente) {
        this.siguiente = siguiente;
    }
}
