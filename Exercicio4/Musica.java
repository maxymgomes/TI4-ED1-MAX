package Exercicio4;

import java.util.Objects;

public class Musica implements Comparable<Musica> {
    private String titulo;
    private String artista;
    private int duracao;

    public Musica(String titulo, String artista, int duracao) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracao = duracao;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getArtista() {
        return artista;
    }

    public int getDuracao() {
        return duracao;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Musica)) {
            return false;
        }

        Musica outra = (Musica) obj;

        return titulo.equals(outra.titulo) &&
                artista.equals(outra.artista);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, artista);
    }

    @Override
    public int compareTo(Musica outra) {
        return titulo.compareTo(outra.titulo);
    }

    @Override
    public String toString() {
        int minutos = duracao / 60;
        int segundos = duracao % 60;

        return String.format("%s - %s (%d:%02d)",
                titulo, artista, minutos, segundos);
    }
}