package Exercicio5;

public class Participante implements Comparable<Participante> {

    private String nome;
    private String email;
    private String curso;

    public Participante(String nome, String email, String curso) {
        this.nome = nome;
        this.email = email;
        this.curso = curso;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCurso() {
        return curso;
    }

    // equals e hashCode usam SÓ o e-mail (é ele que identifica o participante)
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Participante outro = (Participante) o;
        return email.equals(outro.email);
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }

    // compareTo usa o nome (para ordenar em ordem alfabética)
    @Override
    public int compareTo(Participante outro) {
        return nome.compareTo(outro.nome);
    }

    @Override
    public String toString() {
        return nome + " (" + email + ") - " + curso;
    }
}