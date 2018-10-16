package modelo;

import java.util.Properties;

public abstract class Mail {

    final String emisor = "redaguaec@gmail.com";
    final String password = "S0mosAgu@0420";

    Properties prop = new Properties();

    public void propiedades() {
        System.out.println("Define las propiedades");
    }

    public void setHost() {
        System.out.println("Define el host");
    }

    public void setPort() {
        System.out.println("Define el puerto de enlace al mail");
    }

}
