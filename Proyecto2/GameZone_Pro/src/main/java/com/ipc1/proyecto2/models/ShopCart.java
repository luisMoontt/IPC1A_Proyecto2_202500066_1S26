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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Carrito de: ").append(user.getUsername()).append(" ===\n");

        if (head == null) {
            sb.append("  (vacío)\n");
        } else {
            NodeShopCart actual = head;
            int linea = 1;
            while (actual != null) {
                VideoGames vg = actual.getVideogame();
                sb.append(String.format(
                    "  %d. [%s] %s (%s) | x%d | Q%.2f c/u | Subtotal: Q%.2f%n",
                    linea++,
                    vg.getId_videogame(),
                    vg.getName(),
                    vg.getPlataform(),
                    actual.getCantidad(),
                    vg.getPrice(),
                    vg.getPrice() * actual.getCantidad()
                ));
                actual = actual.getSiguiente();
            }
        }
        sb.append(String.format("  TOTAL: Q%.2f%n", calculateTotal()));
        return sb.toString();
    }
}
