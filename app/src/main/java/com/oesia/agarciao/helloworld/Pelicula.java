package com.oesia.agarciao.helloworld;

/**
 * Created by Alberto on 09/09/2015.
 */
public class Pelicula {
    private String titulo;
    private String anho;
    private String director;

    public Pelicula(String titulo, String anho, String director) {
        this.titulo = titulo;
        this.anho = anho;
        this.director = director;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAnho() {
        return anho;
    }

    public void setAnho(String anho) {
        this.anho = anho;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
}
