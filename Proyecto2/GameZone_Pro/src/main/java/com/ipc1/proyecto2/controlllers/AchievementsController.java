package com.ipc1.proyecto2.controlllers;

import com.ipc1.proyecto2.models.Achievements;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Admin
 */
public class AchievementsController {
    private Achievements[] achievements;
    private int count;

    public AchievementsController() {
        this.achievements = new Achievements[80];
        this.count = 0;
    }
    
    // AGREGAR /////////////////////////////////////////////////////////////////
    public void add(int id_achievements, String id_user, String name, String description, int xp, boolean state) {
        loadAchievements();
        if (count >= achievements.length) 
        achievements[count++] = new Achievements(id_achievements, id_user, name, description, xp, state);
        saveAchievementsToFile();
    }

    // LISTAR POR USUARIO //////////////////////////////////////////////////////
    public Achievements[] listForUser(String id_user) {
        int total = 0;
        for (int i = 0; i < count; i++) {
            if (achievements[i].getId_user().equals(id_user)) total++;
        }

        Achievements[] resultado = new Achievements[total];
        int idx = 0;
        for (int i = 0; i < count; i++) {
            if (achievements[i].getId_user().equals(id_user)) {
                resultado[idx++] = achievements[i];
            }
        }
        return resultado;
    }

    
    // LEER Y ESCRIBIR LOGROS ////////////////////////////////////////////////
    public boolean loadAchievements() {
        return loadAchievementsFromFile("docs/" + "logros.txt");
    }

    private boolean loadAchievementsFromFile(String filePath) {
        achievements = new Achievements[100];
        count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null && count < 100) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split("\\|");
                if (parts.length >= 6) {
                    int     id_achievement = Integer.parseInt(parts[0]);
                    String  id_user        = parts[1];
                    String  name           = parts[2];
                    String  description    = parts[3];
                    int     xp             = Integer.parseInt(parts[4]);
                    boolean state          = Boolean.parseBoolean(parts[5]);
                    achievements[count] = new Achievements(id_achievement, id_user, name, description, xp, state);
                    count++;
                }
            }
            System.out.println("Logros cargados: " + count);
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + filePath);
            return false;
        } catch (IOException e) {
            System.out.println("Error leyendo el archivo: " + e.getMessage());
            return false;
        } catch (NumberFormatException e) {
            System.out.println("Error en formato de datos: " + e.getMessage());
            return false;
        }
    }
    //
    public boolean saveAchievementsToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("docs/" + "logros.txt"))) {
            for (int i = 0; i < count; i++) {
                bw.write(achievements[i].getId_achievement() + "|" +
                         achievements[i].getId_user()        + "|" +
                         achievements[i].getName()           + "|" +
                         achievements[i].getDescription()    + "|" +
                         achievements[i].getXp()             + "|" +
                         achievements[i].isState()           + "|");
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            System.out.println("Error guardando logros: " + e.getMessage());
            return false;
        }
    }
}
