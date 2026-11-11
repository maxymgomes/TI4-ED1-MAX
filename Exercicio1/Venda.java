package Exercicio1;


import java.time.LocalDate;

public class Venda {
     private LocalDate data;
     private String nomeVendedor;
     private String nomeProduto;
     private Integer qntVendida;
     private Double precoU;

    public Venda(LocalDate data, String nomeVendedor, String nomeProduto, Integer qntVendida, Double precoU) {
        this.data = data;
        this.nomeVendedor = nomeVendedor;
        this.nomeProduto = nomeProduto;
        this.qntVendida = qntVendida;
        this.precoU = precoU;
    }

    public Venda() {
    }

    public LocalDate getData() {
        return data;
    }

    public String getNomeVendedor() {
        return nomeVendedor;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public Integer getQntVendida() {
        return qntVendida;
    }

    public Double getPrecoU() {
        return precoU;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setNomeVendedor(String nomeVendedor) {
        this.nomeVendedor = nomeVendedor;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public void setQntVendida(Integer qntVendida) {
        this.qntVendida = qntVendida;
    }

    public void setPrecoU(Double precoU) {
        this.precoU = precoU;
    }


}
