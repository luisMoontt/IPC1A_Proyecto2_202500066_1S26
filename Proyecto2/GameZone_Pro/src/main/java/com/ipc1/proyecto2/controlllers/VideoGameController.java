package com.ipc1.proyecto2.controlllers;

import com.ipc1.proyecto2.models.VideoGames;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Admin
 */
public class VideoGameController {
    private static final String FILE_PATH = "docs/catalogo.txt";
    private VideoGames[] videogames = new VideoGames[100];
    private int n_games = 0;
    
    public void updateStock(String id_videoGame, int quantity){
        loadFromFile();
        for (int i = 0; i < n_games; i++) {
            if (videogames[i].getId_videogame().equals(id_videoGame)) {
                
                videogames[i].setStock(videogames[i].getStock() - quantity);
            }
        }
        saveToFile();
    }
    
    public VideoGames findVideoGame(String id_videoGame) {
        loadFromFile();
        for (int i = 0; i < n_games; i++) {
            if (videogames[i].getId_videogame().equals(id_videoGame)) {
                return videogames[i];
            }
        }
        return null;
    }
    
    public VideoGames[] getVideogames() {
        loadFromFile();
        VideoGames[] copia = new VideoGames[n_games];
        System.arraycopy(videogames, 0, copia, 0, n_games);
        return copia;
    }
    
    public int getNGames(){ 
        return n_games; 
    }
    /////////////////////////////////////////////////////////////////////////////
    public void loadFromFile() {
        videogames = new VideoGames[100];
        n_games = 0;
        
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null && n_games < 100) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split("\\|");
                if (parts.length >= 7) {
                    videogames[n_games] = new VideoGames(
                        parts[0],                        
                        parts[1],                      
                        parts[2],                        
                        Double.parseDouble(parts[3]),    
                        parts[4],                        
                        Integer.parseInt(parts[5]),      
                        parts[6]                       
                    );
                    n_games++;
                }
            }
            System.out.println("Catalogo de Video Juegos cargado: "+n_games);
            
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
            for (int i = 0; i < n_games; i++) {
                bw.write(
                    videogames[i].getId_videogame() + "|" +
                    videogames[i].getName() + "|" +
                    videogames[i].getGenre() + "|" +
                    videogames[i].getPrice() + "|" +
                    videogames[i].getPlataform() + "|" +
                    videogames[i].getStock() + "|" +
                    videogames[i].getDescription() + "|"
                );
                bw.newLine();
            }
            System.out.println("Videojuegos guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error guardando videojuegos: " + e.getMessage());
        }
    }
}
