package com.example.bytebusters;

public class Notificacion {

    private int icono;
    private String texto;

    public Notificacion(int icono, String texto) {
        this.icono = icono;
        this.texto = texto;
    }

    public int getIcono() {
        return icono;
    }

    public String getTexto() {
        return texto;
    }
}
