package com.oesia.agarciao.helloworld;

/**
 * Created by Alberto on 09/09/2015.
 */
public class Pelicula {
    private String titulo;
    private String director;
    private String anho;

    public Pelicula() {
    }

    public Pelicula(String titulo, String director, String anho) {
        this.titulo = titulo;
        this.director = director;
        this.anho = anho;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getAnho() {
        return anho;
    }

    public void setAnho(String anho) {
        this.anho = anho;
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "titulo='" + titulo + '\'' +
                ", director='" + director + '\'' +
                ", anho='" + anho + '\'' +
                '}';
    }
}
