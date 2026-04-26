package com.ipc1.proyecto2.controlllers;

import com.ipc1.proyecto2.models.Tournament;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class TournamentController {
    private static final String FILE_PATH = "docs/torneos.txt";
    private Tournament[] tournaments;
    private TicketController tk_controller;
    private UserController u_controller;
    private int n_tournaments;

    public TournamentController() {
        this.tournaments = new Tournament[100];
        this.n_tournaments = 0;
        this.tk_controller = new TicketController();
        this.u_controller = new UserController();
    }

    public boolean buyATicket(String id_tournament, String id_user){
        
        for (int i = 0; i < n_tournaments; i++) {
            if (tournaments[i].getId_tournament().equalsIgnoreCase(id_tournament)) {
                
                if(tournaments[i].getDisableTickes() < 1){
                    JOptionPane.showMessageDialog(null,
                        "Lo sentimos "+id_user+", ya no hay tickets disponibles para el evento: \n "+tournaments[i].getName(),
                        "Tickets Agotados", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                
                u_controller.updateUser(id_user, 150);
                
                tk_controller.createTicket(id_user, id_tournament, tournaments[i].getName());
                tournaments[i].setDisableTickes(tournaments[i].getDisableTickes() - 1);
                
                saveToFile();
            }
        }
        return false; 
    }
    
    public Tournament findTournament(String id_tournament) {
        for (int i = 0; i < n_tournaments; i++) {
            if (tournaments[i].getId_tournament().equalsIgnoreCase(id_tournament)) {
                return tournaments[i];
            }
        }

        JOptionPane.showMessageDialog(null,
            "No se encontró ningún torneo con ID: " + id_tournament,
            "Torneo no encontrado", JOptionPane.ERROR_MESSAGE);
        return null;
    }
    ///////////////////////////////////////////////////////////////////////////
    public void loadFromFile() {
        tournaments = new Tournament[100];
        n_tournaments = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null && n_tournaments < 100) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split("\\|");
                if (parts.length >= 7) {
                    tournaments[n_tournaments] = new Tournament(
                        parts[0],                        // id_tournament
                        parts[1],                        // name
                        parts[2],                        // game
                        parts[3],                        // date
                        parts[4],                        // time
                        Double.parseDouble(parts[5]),    // priceTickets
                        Integer.parseInt(parts[6])       // disableTickets
                    );
                    n_tournaments++;
                }
            }
            System.out.println("Torneos cargados: " + n_tournaments);
            
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + FILE_PATH);
           
        } catch (IOException e) {
            System.out.println("Error leyendo archivo: " + e.getMessage());
            
        } catch (NumberFormatException e) {
            System.out.println("Error en formato de datos: " + e.getMessage());
            
        }
    }

    public void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            for (int i = 0; i < n_tournaments; i++) {
                bw.write(
                    tournaments[i].getId_tournament() + "|" +
                    tournaments[i].getName()          + "|" +
                    tournaments[i].getGame()          + "|" +
                    tournaments[i].getDate()          + "|" +
                    tournaments[i].getTime()          + "|" +
                    tournaments[i].getPriceTickets()  + "|" +
                    tournaments[i].getDisableTickes() + "|"
                );
                bw.newLine();
            }
            System.out.println("Torneos guardados correctamente.");
           
        } catch (IOException e) {
            System.out.println("Error guardando torneos: " + e.getMessage());
           
        }
    }
}
