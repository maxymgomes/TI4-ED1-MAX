package Exercicio3;

public class Voto {
    private String codigo;
    private String voto;

    public Voto() {
    }

    public Voto(String codigo, String voto) {
        this.codigo = codigo;
        this.voto = voto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getVoto() {
        return voto;
    }

    public void setVoto(String voto) {
        this.voto = voto;
    }
}
