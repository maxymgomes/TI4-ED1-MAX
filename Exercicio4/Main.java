package Exercicio4;

import java.io.*;
import java.util.*;

public class Main {

    public static String lerCaminho(Scanner sc) {
        System.out.print("Informe o caminho do arquivo: ");
        String caminho = sc.nextLine();
        return caminho;
    }

    public static ArrayList<Musica> lerArquivo(String caminho) throws FileNotFoundException {
        File arquivo = new File(caminho);
        Scanner leitor = new Scanner(arquivo);
        ArrayList<Musica> playlist = new ArrayList<>();
        Set<Musica> vistas = new HashSet<>();
        int repetidas = 0;
        while (leitor.hasNextLine()) {
            String linha = leitor.nextLine();
            String[] partes = linha.split(";");
            String titulo = partes[0];
            String artista = partes[1];
            int duracao = Integer.parseInt(partes[2]);
            Musica musica = new Musica(titulo, artista, duracao);
            if (vistas.contains(musica)) {
                repetidas++;
            } else {
                vistas.add(musica);
                playlist.add(musica);
            }
        }
        leitor.close();
        System.out.println("Músicas repetidas ignoradas: " + repetidas);
        return playlist;
    }

    public static void exibirPlaylist(ArrayList<Musica> playlist) {
        System.out.println("\nPlaylist (" + playlist.size() + " músicas):");
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ". " + playlist.get(i));
        }
    }

    public static String formatarTempo(int duracao) {
        int minutos = duracao / 60;
        int segundos = duracao % 60;
        return String.format("%d:%02d", minutos, segundos);
    }

    public static void exibirCalculos(ArrayList<Musica> playlist) {
        int total = 0;
        if (playlist.isEmpty()) {
            System.out.println("Playlist vazia.");
            return;
        }
        Musica maior = playlist.get(0);
        for (Musica musica : playlist) {
            total += musica.getDuracao();
            if (musica.getDuracao() > maior.getDuracao()) {
                maior = musica;
            }
        }
        System.out.println("Duração total: " + formatarTempo(total));
        System.out.println("Música mais longa: " + maior);
    }

    public static void tempoPorArtista(ArrayList<Musica> playlist) {
        Map<String, Integer> tempos = new TreeMap<>();
        for (Musica musica : playlist) {
            String artista = musica.getArtista();
            int duracao = musica.getDuracao();
            if (tempos.containsKey(artista)) {
                int total = tempos.get(artista);
                total += duracao;
                tempos.put(artista, total);
            } else {
                tempos.put(artista, duracao);
            }
        }
        System.out.println("\nTempo por artista:");
        for (String artista : tempos.keySet()) {
            System.out.println(artista + ": " +
                    formatarTempo(tempos.get(artista)));
        }
    }

    public static void buscarMusica(ArrayList<Musica> playlist, Scanner sc) {
        System.out.println("\nBuscar música");
        System.out.print("Título: ");
        String titulo = sc.nextLine();
        System.out.print("Artista: ");
        String artista = sc.nextLine();
        Musica busca = new Musica(titulo, artista, 0);
        int posicao = playlist.indexOf(busca);
        if (posicao == -1) {
            System.out.println("Música não encontrada.");
        } else {
            System.out.println(titulo + " está na posição " +
                    (posicao + 1) + " da playlist.");
        }
    }

    public static void ordenarPlaylist(ArrayList<Musica> playlist) {
        Collections.sort(playlist);
        System.out.println("\nEm ordem alfabética:");
        for (Musica musica : playlist) {
            System.out.println(musica);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        String caminho = lerCaminho(sc);
        ArrayList<Musica> playlist = lerArquivo(caminho);
        exibirPlaylist(playlist);
        exibirCalculos(playlist);
        tempoPorArtista(playlist);
        buscarMusica(playlist, sc);
        ordenarPlaylist(playlist);
        sc.close();
    }
}