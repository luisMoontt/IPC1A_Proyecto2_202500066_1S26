package com.ipc1.proyecto2.controlllers;

import com.ipc1.proyecto2.models.Ticket;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class TicketController {
    private static final String FILE_PATH = "docs/tickets_vendidos.txt";
    private Ticket[] tickets;
    private int n_tickets;
    
    public TicketController(){
        this.tickets = new Ticket[100];
        this.n_tickets = 0;
        loadFromFile();
    }
    
    public void createTicket(String id_user, String id_tournament, String eventName) {
        if (id_user == null || id_user.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                "El ID de usuario no puede estar vacío.",
                "Error al crear Ticket", JOptionPane.ERROR_MESSAGE);
        }

        if (id_tournament == null || id_tournament.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                "El ID de torneo no puede estar vacío.",
                "Error al crear Ticket", JOptionPane.ERROR_MESSAGE);
            
        }

        if (n_tickets >= 100) {
            JOptionPane.showMessageDialog(null,
                "No se pueden crear más tickets. Límite alcanzado.",
                "Error al crear Ticket", JOptionPane.ERROR_MESSAGE);
           
        }

        String id_ticket = "TKT-00" + (n_tickets);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String purchaseDate = now.format(dateFormatter);
        String purchaseTime = now.format(timeFormatter);

        tickets[n_tickets] = new Ticket(id_ticket, id_user, id_tournament, purchaseDate, purchaseTime);
        n_tickets++;
        saveToFile();

        JOptionPane.showMessageDialog(null,
            "TICKET CONSEGUIDO EXITOSAMENTE PARA"+ "\n"+
            eventName+"\n" +
            "Propietario: "+id_user+"\n"+
            "ID: "       + id_ticket    + "\n" +
            "Fecha: "    + purchaseDate + "\n" +
            "Hora: "     + purchaseTime,
            "Ticket Comprado", JOptionPane.INFORMATION_MESSAGE);
    }
    
   

    public void loadFromFile() {
        tickets = new Ticket[100];
        n_tickets = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null && n_tickets < 100) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split("\\|");
                if (parts.length >= 5) {
                    tickets[n_tickets] = new Ticket(
                        parts[0],  
                        parts[1], 
                        parts[2], 
                        parts[3], 
                        parts[4]   
                    );
                    n_tickets++;
                }
            }
            System.out.println("Tickets cargados: " + n_tickets);
            
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + FILE_PATH);
            
        } catch (IOException e) {
            System.out.println("Error leyendo archivo: " + e.getMessage());
            
        }
    }

    public void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            for (int i = 0; i < n_tickets; i++) {
                bw.write(
                    tickets[i].getId_ticket()      + "|" +
                    tickets[i].getId_user()        + "|" +
                    tickets[i].getId_tournament()  + "|" +
                    tickets[i].getPurchaseDate()   + "|" +
                    tickets[i].getPurchaseTime()   + "|"
                );
                bw.newLine();
            }
            System.out.println("Tickets guardados correctamente.");
            
        } catch (IOException e) {
            System.out.println("Error guardando tickets: " + e.getMessage());
            
        }
    }
}
