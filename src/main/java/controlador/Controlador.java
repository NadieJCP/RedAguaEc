/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.Registro;
import modelo.Usuario;
import modelo.EnviarMails;
import java.security.GeneralSecurityException;
import javax.mail.MessagingException;

/**
 *
 * @author Jairo
 */
public class Controlador {

    Registro registro;
    String rutaRaiz;
    EnviarMails enviar;

    public Controlador(String rutaRaiz) {
        registro = new Registro();
        try {
            enviar = new EnviarMails();
        } catch (MessagingException | GeneralSecurityException ex) {
            System.out.println(ex.getMessage());
        }
        this.rutaRaiz = rutaRaiz;
    }

    public int totalUsuarios() {
        return registro.totalUsuarios();
    }

    public Usuario obtenerUsuario(int i) {
        return registro.obtenerUsuario(i);
    }

    public void borrarUsuario(int str) {
        registro.borrarUsuario(str);
    }

    public void recuperar(String ruta) {
        registro.restaurar(ruta);
    }

    public void editarUsuario(Usuario usuario, String nombre, String apellido, String pais, String ciudad, String corre, String nCedula, String celu, String ruta, String miembro, int[] fecha) {
        registro.editarUsuario(usuario, nombre, apellido, pais, ciudad, corre, nCedula, celu, ruta, miembro, fecha);
    }

    public void anadirUsuario(Usuario usuario) {
        registro.addUser(usuario);
    }

    public boolean enviarMensaje(String cuerpo, String file, String asunto, String[] correos) {
        return enviar.enviarMsg(cuerpo, file, asunto, correos).isEmpty();
    }

    public String rutaRaiz() {
        return rutaRaiz;
    }

    public boolean modificado(String ruta) {
        return registro.modificado(ruta);
    }

    public void guardar(String ruta) {
        registro.guardar(ruta);
    }

    public Usuario darUsuario(String[] data1) {
        return registro.darUsuario(data1);
    }

}
