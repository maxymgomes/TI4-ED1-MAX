package Exercicio3;
import java.io.*;
import java.util.*;

public class main {

    public static String lerCaminho(Scanner sc) {
        System.out.print("Informe o caminho do arquivo: ");
        String caminho = sc.nextLine();
        return caminho;
    }

    public static Scanner abrirArquivo(String caminho) throws FileNotFoundException {
        File arquivo = new File(caminho);
        Scanner leitor = new Scanner(arquivo);
        return leitor;
    }

    public static void lerArquivo(Scanner leitor) throws FileNotFoundException {
        Map<String, Integer> votos = new TreeMap<>();
        Set<String> eleitores = new HashSet<>();
        List<String> repetidos = new ArrayList<>();
        while (leitor.hasNextLine()) {
            String linha = leitor.nextLine();
            String[] partes = linha.split(";");
            String codigo = partes[0];
            String linguagem = partes[1];
            if (eleitores.contains(codigo)) {
                repetidos.add(codigo);
            } else {
                eleitores.add(codigo);
                if (votos.containsKey(linguagem)) {
                    int qntd = votos.get(linguagem);
                    qntd++;
                    votos.put(linguagem, qntd);
                } else {
                    votos.put(linguagem, 1);
                }
            }
        }
        leitor.close();
        gerarResultado(votos, repetidos);
    }

    public static void gerarResultado(Map<String, Integer> votos, List<String> repetidos) throws FileNotFoundException {
        PrintWriter escritor = new PrintWriter("resultados.txt");
        int total = 0;
        int maior = 0;
        String vencedora = "";
        for (String linguagem : votos.keySet()) {
            int qntd = votos.get(linguagem);
            total += qntd;
            if (qntd > maior) {
                maior = qntd;
                vencedora = linguagem;
            }
        }
        escritor.println("Votos desconsiderados (eleitor repetido): " + repetidos);
        escritor.println("Total de votos válidos: " + total);
        escritor.println("\nResultado:");
        for (String linguagem : votos.keySet()) {
            int qntd = votos.get(linguagem);
            double percentual = (double) qntd / total * 100;
            escritor.printf(Locale.US, "%s: %d votos (%.2f%%)%n", linguagem, qntd, percentual);
        }
        escritor.println("\nVencedora: " + vencedora);
        escritor.close();
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        String caminho = lerCaminho(sc);
        Scanner leitor = abrirArquivo(caminho);
        lerArquivo(leitor);
        sc.close();
    }
}