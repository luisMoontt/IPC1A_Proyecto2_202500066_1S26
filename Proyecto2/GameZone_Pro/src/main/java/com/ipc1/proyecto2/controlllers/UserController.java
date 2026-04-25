package com.ipc1.proyecto2.controlllers;

import com.ipc1.proyecto2.models.User;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class UserController {
    private User[] users;
    private int n_users;
    private User loggedUser;
    private static final String FILE_PATH = "users.txt";

    public UserController() {
        this.users = new User[100];
        this.n_users = 0;
        this.loggedUser = null;
    }
    
    public int getUserCount() {
        return n_users;
    }

    public User getCurrentUser() {
        return loggedUser;
    }
    
    // LOGUEAR Y REGISTRAR /////////////////////////////////////////////////////
    public User register(String username, String password) {
        loadUsers();
        if (username == null || username.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                "El nombre de usuario no puede estar vacío.",
                "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        if (password == null || password.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                "La contraseña no puede estar vacía.",
                "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        if (n_users >= 100) {
            JOptionPane.showMessageDialog(null,
                "No se pueden registrar más usuarios. Límite alcanzado (" + 100 + ").",
                "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        if (findUser(username) != null) {
            JOptionPane.showMessageDialog(null,
                "El usuario '" + username + "' ya existe.\nIntente con otro nombre.",
                "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        users[n_users] = new User(username, password, 0, 1);
        n_users++;
        
        saveUsersToFile();
        listUsers();
        
        return (login(username, password));
    }
    //
    public User login(String username, String password) {
        loadUsers();
        if (username == null || username.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                "Debe ingresar un nombre de usuario.",
                "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        if (password == null || password.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                "Debe ingresar una contraseña.",
                "RROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        User found = findUser(username);

        if (found == null) {
            JOptionPane.showMessageDialog(null,
                "El usuario '" + username + "' no existe.",
                "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        if (!found.getPassword().equals(password)) {
            JOptionPane.showMessageDialog(null,
                "Contraseña incorrecta. Intente de nuevo.",
                "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
        System.out.println("BIEN VENIDO "+username);
        loggedUser = found;
        return loggedUser;
    }
    //
    public void logout() {
        if (loggedUser != null) {
            System.out.println("Sesión cerrada para: " + loggedUser.getUsername());
            loggedUser = null;
        } else {
            System.out.println("No hay sesión activa.");
        }
    }
    // BUSCAR Y LISTAR /////////////////////////////////////////////////////////
    public User findUser(String username) {
        loadUsers();
        for (int i = 0; i < n_users; i++) {
            if (users[i].getUsername().equalsIgnoreCase(username)) {
                return users[i];
            }
        }
        return null;
    }
    public void listUsers() {
        if (n_users == 0) {
            System.out.println("No hay usuarios registrados.");
            return;
        }
        
        System.out.println("                  LISTA DE USUARIOS                     ");
        System.out.println("--------------------------------------------------------");
        System.out.printf("%-15s %-15s %-10s %-8s%n", "Username", "password","XP", "Level");
        
        for (int i = 0; i < n_users; i++) {
            System.out.printf("%-15s %-15s %-10d %-8d%n",
                users[i].getUsername(), users[i].getPassword(), users[i].getXp(), users[i].getLevel());
        }
        
    }
     // LEER Y ESCRIBIR USUARIOS ////////////////////////////////////////////////
    public boolean loadUsers() {
        return loadUsersFromFile("docs/"+FILE_PATH);
    }
    
    private boolean loadUsersFromFile(String filePath) {
        users = new User[100];
        n_users = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null && n_users < 100) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\|");
                if (parts.length >= 4) {
                    String username = parts[0];
                    String password = parts[1];
                    int xp          = Integer.parseInt(parts[2]);
                    int level       = Integer.parseInt(parts[3]);

                    users[n_users] = new User(username, password, xp, level);
                    n_users++;
                }
            }
            System.out.println("Usuarios cargados: " + n_users);
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
    public boolean saveUsersToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("docs/"+FILE_PATH))) {
            for (int i = 0; i < n_users; i++) {
                bw.write(users[i].getUsername() + "|" +
                         users[i].getPassword() + "|" +
                         users[i].getXp()       + "|" +
                         users[i].getLevel()    + "|" );
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            System.out.println("Error guardando usuarios: " + e.getMessage());
            return false;
        }
    }   
}
