package com.ipc1.proyecto2.controlllers;

import com.ipc1.proyecto2.models.ShopCart;
import com.ipc1.proyecto2.models.NodeShopCart;
import com.ipc1.proyecto2.models.User;
import com.ipc1.proyecto2.models.VideoGames;

/**
 *
 * @author Admin
 */

public class ShopCartController {
    private boolean f_time;
    private final ShopCart cart;
    private final VideoGameController vg_controller;
    private AchievementsController a_controller;
    private ShoppingRecordController spr_controller;
    
    public ShopCartController(User user, VideoGameController vg_controller) {
        this.f_time = true;
        this.cart = new ShopCart(user);
        this.vg_controller = vg_controller;
        this.spr_controller = new ShoppingRecordController();
        this.a_controller = new AchievementsController();
    }

    public boolean addForId(String id_videoGame, int quantity) {
        VideoGames vg = vg_controller.findVideoGame(id_videoGame);
        if (vg == null) {
            System.out.println("Videojuego no encontrado en el catálogo.");
            return false;
        }
        if(cart.addVideogame(vg, quantity)){
            vg_controller.updateStock(id_videoGame, quantity);
            return true;
        }
        return false;
    }
    
    public void setCantidad(String id_videogame, int nuevaCantidad) {
        NodeShopCart nodo = cart.findVideoGame(id_videogame);
        if (nodo != null && nuevaCantidad > 0) {
            nodo.setCantidad(nuevaCantidad);
        }
    }
    
    public void payCart() {
        if (!validarCarrito()) {
            System.out.println("Compra rechazada, revisa el carrito.");
            return;
        }
        
        NodeShopCart actual = cart.getHead();
        
        while (actual != null) {
            vg_controller.updateStock(
                actual.getVideogame().getId_videogame(),
                actual.getCantidad()
            );
            actual = actual.getSiguiente();
        }
        
        spr_controller.generateShoppingRecord(cart);
        
        if(f_time){
            a_controller.updateAchievement(cart.getUser().getUsername(), 1);
            f_time=false;
        }
        
        System.out.println("Compra realizada. Total: Q" + cart.calculateTotal());
        cart.emptyCart();
    }
    
    public boolean validarCarrito() {
        NodeShopCart actual = cart.getHead();
        while (actual != null) {
            VideoGames vg = vg_controller.findVideoGame(
                actual.getVideogame().getId_videogame()
            );
            if (vg == null || actual.getCantidad() > vg.getStock()) {
                System.out.println("❌ Stock insuficiente: " + actual.getVideogame().getName()
                    + " | Pedido: " + actual.getCantidad()
                    + " | Disponible: " + vg.getStock());
                return false;
            }
            actual = actual.getSiguiente();
        }
        return true;
    }

    
    public boolean remove(String id_videogame) {
        return cart.removeVideogame(id_videogame);
    }


    public boolean add(VideoGames vg, int quantity) {
        return cart.addVideogame(vg, quantity);
    }

    public NodeShopCart buscar(String idVideogame) {
        return cart.findVideoGame(idVideogame);
    }

    public void empty() {
        cart.emptyCart();
    }

    public double getTotal() { 
        return cart.calculateTotal(); 
    }
    public int obtenerTamanio() { 
        return cart.getSize();    
    }
    public ShopCart getCarrito() { 
        return cart; 
    }
}
