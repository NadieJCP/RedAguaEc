/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Jairo
 */
public class Registro {

    Usuario[] usuarios;
    int n;

    public Registro() {
        usuarios = new Usuario[1000];
        n = 0;
    }

    public void addUser(Usuario user) {
        usuarios[n++] = user;
    }

    public void borrarUsuario(int i) {
        new File(usuarios[i].ruta()).delete();
        for (int j = i; j < n; j++) {
            usuarios[j] = usuarios[j + 1];
        }
        n--;
    }

    public int totalUsuarios() {
        return n;
    }

    public Usuario obtenerUsuario(int i) {
        return usuarios[i];
    }

    public void guardar(String ruta) {
        File f = new File(ruta);
        if (f.exists()) {
            f.delete();
        }
        String[] datos = new String[n];
        for (int i = 0; i < n; i++) {
            datos[i] = usuarios[i].toString();
        }
        ExportarFile exp = new ExportarFile(ruta, ";");
        exp.exportarDatos(datos);
    }

    public void restaurar(String ruta) {
        try {
            ImportarFile imp = new ImportarFile(ruta, ";");
            String[][] data = imp.enCaracteres();
            for (String[] data1 : data) {
                usuarios[n++] = darUsuario(data1);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    private String analizar(String nombre) {
        String nombres = nombre.toLowerCase();
        String[] l = nombre.split("");
        String letras = "";
        int[] vocalesTildadas = {225, 233, 237, 243, 250};
        for (int i = 0; i < nombres.length(); i++) {
            char letra = nombres.charAt(i);
            int value = (int) (letra);
            if (value == 32) {
                letras += l[i];
            } else if (value >= 97) {
                if (value <= 122) {
                    letras += l[i];
                }
                for (int j = 0; j < 5; j++) {
                    if (vocalesTildadas[j] == letra) {
                        letras += l[i];
                    }
                }
            }
        }
        return letras;
    }

    public void editarUsuario(Usuario usuario, String nombre, String apellido, String pais, String ciudad, String corre, String nCedula, String celu, String ruta, String miembro, int[] fecha) {
        for (int i = 0; i < n; i++) {
            if (usuarios[i].equals(usuario)) {
                usuarios[i] = new Usuario(nombre, apellido, nCedula, celu, pais, ciudad, corre, ruta, miembro, fecha);
            }
        }
    }

    public boolean modificado(String ruta) {
        if (new File(ruta).exists()) {
            try {
                ImportarFile imp = new ImportarFile(ruta, ";");
                String[] data = imp.lineas();
                if (data.length != n) {
                    return true;
                }
                for (int i = 0; i < n; i++) {
                    if (!data[i].equals(usuarios[i].toString())) {
                        return true;
                    }
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            return false;
        } else {
            return n != 0;
        }
    }

    public int[] vector(String fecha) {
        int[] fechas = new int[3];
        String[] split = fecha.split("/");
        if (split.length != 3) {
            return null;
        }
        for (int i = 0; i < split.length; i++) {
            fechas[i] = Integer.parseInt(split[i]);
        }
        return fechas;
    }

    public Usuario darUsuario(String[] data1) {
        String[] nombres = analizar(data1[0]).split(" ");
        int[] dates = vector(data1[8]);
        if (dates == null) {
            return null;
        }
        return new Usuario(nombres[0] + " " + nombres[1], nombres[2] + " " + nombres[3], data1[1], data1[2], data1[3], data1[4], data1[5], data1[6], data1[7], dates);
    }
}
