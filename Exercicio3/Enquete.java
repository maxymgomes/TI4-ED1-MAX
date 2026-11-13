package Exercicio3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Enquete {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        File arquivo = new File("votos.txt");
        try (Scanner sc = new Scanner(arquivo)) {
            Set<Voto> votacao = new HashSet<>();
            while (sc.hasNextLine()){
                String linha = sc.nextLine();
                String[] campos = linha.split(";");
                Voto voto = new Voto();
                voto.setCodigo(campos[0]);
                voto.setVoto(campos[1]);
                votacao.add(voto);
            }

            Map<String, Integer> votos = new HashMap<>();

        }catch (FileNotFoundException e) {
            System.out.println("Erro: O arquivo 'vendas.csv' não foi encontrado no diretório.");
        }
    }
}
