package Exercicio6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class FilaRU {

    public static void main(String[] args) {
        LinkedList<String> fila = new LinkedList<>();
        HashSet<String> almocaram = new HashSet<>();
        ArrayList<String> atendidos = new ArrayList<>();
        int recusadas = 0;

        // ---------- Leitura do arquivo ----------
        try {
            Scanner arquivo = new Scanner(new File("fila.txt"));
            while (arquivo.hasNextLine()) {
                String linha = arquivo.nextLine().trim();
                if (linha.isEmpty()) {
                    continue;
                }
                String[] partes = linha.split("\\s+");
                String comando = partes[0];

                if (comando.equals("CHEGA")) {
                    String nome = partes[1];
                    if (fila.contains(nome)) {
                        System.out.println("CHEGA " + nome + ": já está na fila");
                        recusadas++;
                    } else if (almocaram.contains(nome)) {
                        System.out.println("CHEGA " + nome + ": já almoçou hoje");
                        recusadas++;
                    } else {
                        fila.addLast(nome);
                        System.out.println("CHEGA " + nome + ": entrou na fila");
                    }
                } else if (comando.equals("ATENDE")) {
                    if (fila.isEmpty()) {
                        System.out.println("ATENDE: fila vazia, ninguém para atender");
                    } else {
                        String nome = fila.removeFirst();
                        almocaram.add(nome);
                        atendidos.add(nome);
                        System.out.println("ATENDE: " + nome);
                    }
                }
            }
            arquivo.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo fila.txt não encontrado.");
            return;
        }

        // ---------- Resumo final ----------
        System.out.println();
        System.out.println("Atendidos (" + atendidos.size() + "): " + atendidos);
        System.out.println("Ainda na fila (" + fila.size() + "):");
        for (int i = 0; i < fila.size(); i++) {
            int posicao = i + 1;
            System.out.println(posicao + ". " + fila.get(i) + " - espera estimada: " + (posicao * 3) + " min");
        }
        System.out.println("Entradas recusadas: " + recusadas);

        // ---------- Consulta pelo teclado ----------
        Scanner teclado = new Scanner(System.in);
        System.out.println();
        System.out.println("Consulta (digite \"sair\" para encerrar)");
        while (true) {
            System.out.print("Nome: ");
            if (!teclado.hasNextLine()) {
                break;
            }
            String nome = teclado.nextLine().trim();
            if (nome.equalsIgnoreCase("sair")) {
                break;
            }
            int indice = fila.indexOf(nome);
            if (indice != -1) {
                System.out.println(nome + " está na fila, posição " + (indice + 1) + ".");
            } else if (almocaram.contains(nome)) {
                System.out.println(nome + " já almoçou.");
            } else {
                System.out.println(nome + " não passou pelo RU hoje.");
            }
        }
        teclado.close();
    }
}