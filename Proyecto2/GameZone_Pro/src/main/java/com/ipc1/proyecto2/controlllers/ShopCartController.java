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
    private final ShopCart cart;
    private final VideoGameController vg_controller;
    private ShoppingRecordController spr_controller;
    
    public ShopCartController(User user, VideoGameController vg_controller) {
        this.cart = new ShopCart(user);
        this.vg_controller = vg_controller;
        this.spr_controller = new ShoppingRecordController();
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
    public void payCart(){
        double total;
        
        spr_controller.generateShoppingRecord(cart);
        total = cart.calculateTotal();
        System.out.println("COMPRA REALIZADA EXITOSAMENTE");
        System.out.println("Total: Q"+total);
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
