package com.ipc1.proyecto2.controlllers;

import com.ipc1.proyecto2.models.ShopCart;
import com.ipc1.proyecto2.models.NodeShopCart;
import com.ipc1.proyecto2.models.NodeShoppingRecord;
import com.ipc1.proyecto2.models.ShoppingRecord;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ShoppingRecordController {
    private static final String FILE_PATH = "docs/historial.txt";
    private static final String SEP       = "|";

    private NodeShoppingRecord head;
    private int size;

    public ShoppingRecordController() {
        this.head  = null;
        this.size = 0;
    }

    private void add(ShoppingRecord record) {
        NodeShoppingRecord nuevo = new NodeShoppingRecord(record);
        if (head == null) {
            head = nuevo;
        } else {
            NodeShoppingRecord actual = head;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
        size++;
    }
    
    public void generateShoppingRecord(ShopCart cart) {
        loadShoppingRecordsFromFile();
        LocalDateTime now   = LocalDateTime.now();
        String date  = now.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String time  = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        String id_user = cart.getUser().getUsername();
        NodeShopCart nodo  = cart.getHead();
        
        int count = 0;
        
        if (cart.getHead() == null) {
            System.out.println("ERROR - El carrito esta vacio");
            return;
        }

        while (nodo != null) {
            add(new ShoppingRecord(
                nodo.getVideogame().getId_videogame(),
                nodo.getVideogame().getName(),
                id_user,
                date,
                time,
                nodo.getCantidad(),
                nodo.getVideogame().getPrice() * nodo.getCantidad()
            ));
            count++;
            nodo = nodo.getSiguiente();
        }
        
        saveToFile();
        System.out.println("Se generaron " + count + " registros de compra para: " + id_user);
    }

    
    public ShoppingRecord[] listByUser(String id_user) {
        loadShoppingRecordsFromFile();
        int count = 0;
        NodeShoppingRecord actual = head;
        while (actual != null) {
            if (actual.getRecord().getId_user().equals(id_user)) count++;
            actual = actual.getSiguiente();
        }

        ShoppingRecord[] resultado = new ShoppingRecord[count];
        double totalGastado = 0;
        int idx = 0;

        System.out.println("\n=== Historial — Usuario: " + id_user + " ===");

        actual= head;
        while (actual != null) {
            ShoppingRecord r = actual.getRecord();
            if (r.getId_user().equals(id_user)) {
                System.out.printf(
                    "  [%s] %s | %s %s | x%d | Q%.2f%n",
                    r.getId_videogame(), r.getName_videogame(),
                    r.getDate(), r.getTime(),
                    r.getQuantity(), r.getTotal()
                );
                totalGastado     += r.getTotal();
                resultado[idx++]  = r;
            }
            actual = actual.getSiguiente();
        }

        if (count == 0) {
            System.out.println("  Sin registros para este usuario.");
        } else {
            System.out.printf("  Total gastado: Q%.2f  (%d compras)%n", totalGastado, count);
        }

        return resultado;
    }

    ////////////////////////////////////////////////////////////////////////////
    
    public void loadShoppingRecordsFromFile() {
        head  = null;
        size = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\|");
                if (parts.length >= 7) {
                    add(new ShoppingRecord(
                        parts[0], 
                        parts[1],                      
                        parts[2],                       
                        parts[3],                       
                        parts[4],                       
                        Integer.parseInt(parts[5]),     
                        Double.parseDouble(parts[6])    
                    ));
                }
            }
            System.out.println("Historial cargado: " + size + " registros en el historial.");

        } catch (FileNotFoundException e) {
            System.out.println("Historial no encontrado, se creará al guardar.");
        } catch (IOException e) {
            System.out.println("Error leyendo historial: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error en formato del historial: " + e.getMessage());
        }
    }
    public void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            NodeShoppingRecord actual = head;
            while (actual != null) {
                ShoppingRecord r = actual.getRecord();
                bw.write(
                    r.getId_videogame()   + SEP +
                    r.getName_videogame() + SEP +
                    r.getId_user()        + SEP +
                    r.getDate()           + SEP +
                    r.getTime()           + SEP +
                    r.getQuantity()       + SEP +
                    r.getTotal()
                );
                bw.newLine();
                actual = actual.getSiguiente();
            }
            System.out.println("Historial guardado correctamente.");
        } catch (IOException e) {
            System.out.println("ERROR guardando historial: " + e.getMessage());
        }
    }

    public NodeShoppingRecord getHead() { 
        return head;  
    }
    public int getSize() { 
        return size; 
    }
}
