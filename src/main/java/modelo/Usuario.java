/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Jairo
 */
public class Usuario {

    String nombre, apellido, correo, cedula, celular, ruta, miembro, pais, ciudad;
    int anio, mes, dia;

    public Usuario() {
        nombre = "";
        apellido = "";
        correo = "";
        cedula = "";
        celular = "";
        ruta = "";
        miembro = "";
        pais = "";
        ciudad = "";
        anio = 0;
        mes = 0;
        dia = 0;
    }

    public Usuario(String nombre, String apellido, String cedula, String celular, String pais, String ciudad, String correo, String ruta, String miembro, int[] fecha) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.cedula = cedula;
        this.celular = celular;
        this.pais = pais;
        this.ciudad = ciudad;
        this.ruta = ruta;
        this.miembro = miembro;
        this.anio = fecha[0];
        this.mes = fecha[1];
        this.dia = fecha[2];
    }

    public String nombres() {
        return nombre;
    }

    public String nombre() {
        return nombre.split(" ")[0];
    }

    public String apellidos() {
        return apellido;
    }

    public String apellido() {
        return apellido.split(" ")[0];
    }

    public String cedula() {
        return cedula;
    }

    public String correo() {
        return correo;
    }

    public String celular() {
        return celular;
    }

    public String ruta() {
        return ruta;
    }

    public String miembro() {
        return miembro;
    }

    public String pais() {
        return pais;
    }

    public String ciudad() {
        return ciudad;
    }

    public int anio() {
        return anio;
    }

    public int mes() {
        return mes;
    }

    public int dia() {
        return dia;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + ";" + cedula + ";" + celular + ";" + pais + ";" + ciudad + ";" + correo + ";" + ruta + ";" + miembro + ";" + fecha();
    }

    private String fecha() {
        return String.valueOf(anio) + "/" + String.valueOf(mes) + "/" + String.valueOf(dia);
    }

    public void cambiarRuta(String string) {
        this.ruta = string;
    }
}
