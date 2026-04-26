package com.ipc1.proyecto2.models;

/**
 *
 * @author Admin
 */
public class ShopCart {
    private User user;
    private NodeShopCart head;
    private int size;

    public ShopCart(User user) {
        this.user  = user;
        this.head   = null;
        this.size  = 0;
    }

    public boolean addVideogame(VideoGames vg, int cantidad) {
        if (cantidad <= 0)
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0.");

        NodeShopCart actual = head;
        while (actual != null) {
            if (actual.getVideogame().getId_videogame().equals(vg.getId_videogame())) {
                int nuevaCantidad = actual.getCantidad() + cantidad;
                if (nuevaCantidad > vg.getStock()) {
                    System.out.println("Stock insuficiente. Disponible: " + vg.getStock());
                    return false;
                }
                actual.setCantidad(nuevaCantidad);
                return true;
            }
            actual = actual.getSiguiente();
        }

        if (cantidad > vg.getStock()) {
            System.out.println("Stock insuficiente. Disponible: " + vg.getStock());
            return false;
        }
        
        
        NodeShopCart nuevo = new NodeShopCart(vg, cantidad);
        
        
        nuevo.setSiguiente(head);
        head = nuevo;
        size ++;
        return true;
    }

    public NodeShopCart findVideoGame(String idVideogame) {
        NodeShopCart actual = head;
        while (actual != null) {
            if (actual.getVideogame().getId_videogame().equals(idVideogame))
                return actual;
            actual = actual.getSiguiente();
        }
        return null;
    }

    public boolean removeVideogame(String id_videogame) {
        if (head == null) return false;

        if (head.getVideogame().getId_videogame().equals(id_videogame)) {
            head = head.getSiguiente();
            size--;
            return true;
        }
        
        NodeShopCart actual = head;
        while (actual.getSiguiente() != null) {
            if (actual.getSiguiente().getVideogame().getId_videogame().equals(id_videogame)) {
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                size--;
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }
    
    public void emptyCart() {
        head  = null;
        size = 0;
    }
    
    public double calculateTotal() {
        double total = 0;
        NodeShopCart actual = head;
        while (actual != null) {
            total += actual.getVideogame().getPrice() * actual.getCantidad();
            actual = actual.getSiguiente();
        }
        return total;
    }

    public int getSize() { 
        return size; 
    }

    public User getUser() { 
        return user; 
    }
    public NodeShopCart getHead(){ 
        return head;  
    }
}
