package Exercicio1;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.*;

public class LerArquivo {
    static Map<String, Double> faturamentoPorVendedor = new TreeMap<>();
    public static void main(String[] args){
        Locale.setDefault(Locale.US);
        File arquivo = new File("vendas.csv");
        try (Scanner sc = new Scanner(arquivo)){
            Set<Venda> set = new HashSet<>();
            while (sc.hasNextLine()){
                String linha = sc.nextLine();
                String[] campos = linha.split(";");
                Venda venda = new Venda();
                venda.setData(LocalDate.parse(campos[0]));
                venda.setNomeVendedor(campos[1]);
                venda.setNomeProduto(campos[2]);
                venda.setQntVendida(Integer.parseInt(campos[3]));
                venda.setPrecoU(Double.parseDouble(campos[4]));
                set.add(venda);

            }

            Map<String, Integer> quantidadePorProduto = new HashMap<>();

            for (Venda v : set) {

                String vendedor = v.getNomeVendedor();
                String produto = v.getNomeProduto();

                int quantidade = v.getQntVendida();
                double valorVenda = quantidade * v.getPrecoU();


                faturamentoPorVendedor.put(
                        vendedor,
                        faturamentoPorVendedor.getOrDefault(vendedor, 0.0) + valorVenda
                );


                quantidadePorProduto.put(
                        produto,
                        quantidadePorProduto.getOrDefault(produto, 0) + quantidade
                );
            }

            System.out.println("=== Faturamento por Vendedor ===");

            for (String vendedor : faturamentoPorVendedor.keySet()) {
                System.out.printf(
                        "%s: R$ %.2f%n",
                        vendedor,
                        faturamentoPorVendedor.get(vendedor)
                );
            }
            System.out.println("\n=== Consulta de Vendedores ===");

            consultarVendedor("Bruno");
            consultarVendedor("Diego");

            System.out.println("\n=== Faturamento Total ===");

            double total = 0;

            for (double valor : faturamentoPorVendedor.values()) {
                total += valor;
            }

            System.out.printf("Faturamento total: R$ %.2f%n", total);

            System.out.println("\n=== Media por vendedor ===");
            double media = total / faturamentoPorVendedor.size();
            System.out.printf("Media por vendedor: R$ %.2f%n",media);

            String maiorVendedor = "";
            double maiorValor = 0;

            for (String nome : faturamentoPorVendedor.keySet()) {
                if (faturamentoPorVendedor.get(nome) > maiorValor) {
                    maiorValor = faturamentoPorVendedor.get(nome);
                    maiorVendedor = nome;
                }
            }
            System.out.println("\n=== Maior Faturamento ===");
            System.out.println("Melhor vendedor: " + maiorVendedor
                    + " (R$ " + maiorValor + ")");

            System.out.println("\n=== Participacao dos vendedores ===");

            for (String nome : faturamentoPorVendedor.keySet()) {
                double percentual = (faturamentoPorVendedor.get(nome) / total) * 100;

                System.out.printf("%s: %.2f%%%n", nome, percentual);
            }

            System.out.println("\n=== Vendedores Acima da Media ===");
            for (String nome : faturamentoPorVendedor.keySet()){
                if (faturamentoPorVendedor.get(nome) > media){
                    System.out.println(nome);
                }
            }


            System.out.println("\n=== Quantidade Total Vendida por Produto ===");
            for (String produto : quantidadePorProduto.keySet()) {
                System.out.printf("%s: %d unidades%n", produto, quantidadePorProduto.get(produto));
            }

            System.out.println("\n=== Produto mais vendido ===");
            String produtoMaisVendido = "";
            int maiorQuantidade = 0;

            for (String produto : quantidadePorProduto.keySet()){
                if (quantidadePorProduto.get(produto) > maiorQuantidade){
                    maiorQuantidade = quantidadePorProduto.get(produto);
                    produtoMaisVendido = produto;
                }
            }
            System.out.println("Produto mais vendido: "
                    + produtoMaisVendido +
                    " ("
                    + maiorQuantidade + " unidades)");

        }catch (FileNotFoundException e) {
            System.out.println("Erro: O arquivo 'vendas.csv' não foi encontrado no diretório.");
        }
    }
    public static void consultarVendedor(String nome) {
        if (faturamentoPorVendedor.containsKey(nome)) {
            System.out.printf(
                    "Faturamento de %s: R$ %.2f%n",
                    nome,
                    faturamentoPorVendedor.get(nome)
            );
        } else {
            System.out.println("Vendedor " + nome + " não encontrado.");
        }
    }
}
