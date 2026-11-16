package Exercicio5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Inscricoes {

    public static void main(String[] args) {
        HashSet<Participante> unicos = new HashSet<>();
        int recebidas = 0;

        // ---------- Leitura do arquivo (HashSet remove duplicatas pelo e-mail) ----------
        try {
            Scanner arquivo = new Scanner(new File("inscricoes.txt"));
            while (arquivo.hasNextLine()) {
                String linha = arquivo.nextLine().trim();
                if (linha.isEmpty()) {
                    continue;
                }
                String[] partes = linha.split(";");
                String nome = partes[0].trim();
                String email = partes[1].trim();
                String curso = partes[2].trim();

                recebidas++;
                unicos.add(new Participante(nome, email, curso)); // se o e-mail já existe, não adiciona
            }
            arquivo.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo inscricoes.txt não encontrado.");
            return;
        }

        System.out.println("Inscrições recebidas: " + recebidas);
        System.out.println("Participantes únicos: " + unicos.size());
        System.out.println("Inscrições duplicadas: " + (recebidas - unicos.size()));

        // ---------- Lista ordenada por nome ----------
        ArrayList<Participante> lista = new ArrayList<>(unicos);
        Collections.sort(lista);

        System.out.println();
        System.out.println("Participantes em ordem alfabética:");
        for (Participante p : lista) {
            System.out.println(p);
        }

        // ---------- Contagem por curso (TreeMap = ordenado pela chave) ----------
        TreeMap<String, Integer> porCurso = new TreeMap<>();
        for (Participante p : lista) {
            String curso = p.getCurso();
            if (porCurso.containsKey(curso)) {
                porCurso.put(curso, porCurso.get(curso) + 1);
            } else {
                porCurso.put(curso, 1);
            }
        }

        System.out.println();
        System.out.println("Inscritos por curso:");
        String melhorCurso = "";
        int maior = 0;
        for (Map.Entry<String, Integer> e : porCurso.entrySet()) {
            System.out.println(e.getKey() + ": " + e.getValue());
            if (e.getValue() > maior) {
                maior = e.getValue();
                melhorCurso = e.getKey();
            }
        }
        System.out.println("Curso com mais inscritos: " + melhorCurso);

        // ---------- Consulta por e-mail (contains) ----------
        Scanner teclado = new Scanner(System.in);
        System.out.println();
        System.out.print("Consultar e-mail: ");
        String email = teclado.nextLine().trim();

        // o contains usa equals, que só olha o e-mail; nome e curso do objeto de busca não importam
        Participante busca = new Participante("", email, "");
        if (unicos.contains(busca)) {
            System.out.println(email + " está inscrito(a).");
        } else {
            System.out.println(email + " não está inscrito(a).");
        }
        teclado.close();
    }
}