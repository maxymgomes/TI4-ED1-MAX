package Exercicio2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Cordenacao {
    public static void main() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> ED = new ArrayList<>();
        ArrayList<Integer> BD = new ArrayList<>();
        System.out.println("Quantos alunos em ED? ");
        int qnt = sc.nextInt();
        System.out.println("Digite as matriculas");
        for (int i = 0; i <  qnt; i++){
            int x = sc.nextInt();
            if (ED.contains(x)){
                System.out.println("Matricula " + x + " repitada, ignorada");
            }else {
                ED.add(x);
            }
        }

        System.out.println("Quantos alunos em BD? ");
        int qnt1 = sc.nextInt();
        System.out.println("Digite as matriculas");
        for (int i = 0; i <  qnt1; i++){
            int x = sc.nextInt();
            if (BD.contains(x)){
                System.out.println("Matricula " + x + " repitada, ignorada");
            }else {
                BD.add(x);
            }
        }
        Set<Integer> alunosED = new HashSet<>(ED);
        Set<Integer> alunosBD = new HashSet<>(BD);
//        alunosED.addAll(alunosBD);
//        System.out.println("Total de alunos distintos " + alunosED);
//
//        alunosED.retainAll(alunosBD);
//
//        System.out.println("Alunos nas duas disciplinas: " + alunosED);
//        alunosED.addAll(alunosBD);
        int total = ED.size();
        for (Integer aluno : alunosED) {
            if (!alunosBD.contains(aluno)){
                total++;
            }
        }
        System.out.println("Total de alunos distintos: " + total);

        System.out.print("\nCursam as duas disciplinas: ");
        int duasM = 0;
        for (Integer aluno : alunosED){
            if (alunosBD.contains(aluno)){
                System.out.print(aluno + " ");
                duasM++;
            }
        }

        System.out.print("\nAlunos somente de ED: ");

        for (Integer aluno : alunosED) {
            if (!alunosBD.contains(aluno)) {
                System.out.print(aluno + " ");
            }
        }

        System.out.print("\nAlunos somente de BD: ");

        for (Integer aluno : alunosBD) {
            if (!alunosED.contains(aluno)) {
                System.out.print(aluno + " ");
            }
        }

        double porcentagem = ((double) duasM / alunosED.size()) * 100;
        System.out.print("\nAlunos de ED que tambem cursam BD: " +porcentagem+"%");

//        int totalalunos = 0;
//        totalalunos = ED.size() + BD.size();
//        for (Integer matricula : ED) {
//            if (BD.contains(matricula)) {
//                totalalunos = totalalunos - 1;
//            }
//        }
//        ArrayList<Integer> duasM = new ArrayList<>();
//        ArrayList<Integer>  soED = new ArrayList<>();
//        ArrayList<Integer> soBD = new ArrayList<>();
//        for (Integer matricula : ED){
//            if (BD.contains(matricula)){
//                duasM.add(matricula);
//            }else {
//                soED.add(matricula);
//            }
//        }
//        for (Integer matricula : BD){
//            if (ED.contains(matricula) == false){
//                soBD.add(matricula);
//            }
//        }
//        System.out.println("Total de alunos distintos " + totalalunos );
//        System.out.println("Cursam as duas disciplinas " + duasM);
//        System.out.println("Somente ED: " + soED);
//        System.out.println("Somente BD: " + soBD);
    }
}
