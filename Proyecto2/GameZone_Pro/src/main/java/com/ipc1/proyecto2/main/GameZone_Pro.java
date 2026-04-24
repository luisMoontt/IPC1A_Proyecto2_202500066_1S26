package com.ipc1.proyecto2.main;

import com.ipc1.proyecto2.controlllers.ShopCartController;
import com.ipc1.proyecto2.controlllers.ShoppingRecordController;
import com.ipc1.proyecto2.controlllers.TicketController;
import com.ipc1.proyecto2.controlllers.TournamentController;
import com.ipc1.proyecto2.controlllers.UserController;
import com.ipc1.proyecto2.controlllers.VideoGameController;
import java.io.File;

/**
 * @author Luis Monterroso - 202500066
 */
public class GameZone_Pro {

    public static void main(String[] args) {
        UserController u_controller = new UserController();
        TournamentController tr_controller = new TournamentController();
        VideoGameController vg_controller = new VideoGameController();
        
        verifyDocs();
        u_controller.loadUsers();
        tr_controller.loadFromFile();
        tr_controller.buyATicket("TRN-000", "noely");
        vg_controller.loadFromFile();
        
        ShopCartController shc_controller = new ShopCartController(u_controller.findUser("noely"),vg_controller);
        ShoppingRecordController spr_controller = new ShoppingRecordController();
        
        
        shc_controller.addForId("VDG-001", 3);
        shc_controller.addForId("VDG-002", 1);
        shc_controller.payCart();
        spr_controller.listByUser("noely");
        
        
        
        
        //u_controller.register("luismont", "123456");
        //u_controller.register("luisElPro", "123456");
        //u_controller.saveUsersToFile();
    }
    
    private static void verifyDocs(){
        File docsFolder = new File("docs");
        File docsImages = new File("docs/images");
    
        if (!docsFolder.exists()) {
            docsFolder.mkdir();
            docsImages.mkdir();
            System.out.println("CARPETAS CREADAS");
        } else {
            System.out.println("CARPETAS YA EXISTENTES");
        }
    }
    
    private static void loadDocs(){
    
    
    }
}
