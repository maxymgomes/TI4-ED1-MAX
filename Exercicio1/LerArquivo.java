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

                // Acumula faturamento por vendedor
                faturamentoPorVendedor.put(
                        vendedor,
                        faturamentoPorVendedor.getOrDefault(vendedor, 0.0) + valorVenda
                );

                // Acumula quantidade por produto
                quantidadePorProduto.put(
                        produto,
                        quantidadePorProduto.getOrDefault(produto, 0) + quantidade
                );
            }

            System.out.println("=== Faturamento Total por Vendedor ===");

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

            System.out.println("\n=== Quantidade Total Vendida por Produto ===");
            for (String produto : quantidadePorProduto.keySet()) {
                System.out.printf("%s: %d unidades%n", produto, quantidadePorProduto.get(produto));
            }

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
