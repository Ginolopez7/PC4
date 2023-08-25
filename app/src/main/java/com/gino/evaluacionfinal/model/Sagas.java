package com.gino.evaluacionfinal.model;

public class Sagas extends Shows {

    private String episodios;
    private String genero;

    public Sagas(String name, String imgUrl, String episodios, String genero) {
        super(name, imgUrl);
        this.episodios = episodios;
        this.genero = genero;
    }

    public String getEpisodios() {
        return episodios;
    }

    public void setEpisodios(String episodios) {
        this.episodios = episodios;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
