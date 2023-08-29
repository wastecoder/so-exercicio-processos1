package view;

import controller.RedesController;

import java.util.Scanner;

public class Main {
    //A Classe Main.java deve dar as opções de chamadas do método ip ou do método ping com JOptionPane e, dependendo da escolha, instanciar a Classe RedesController.java e chamar o método escolhido. A opção de finalizar a aplicação também deve estar disponível.
    public static void main(String[] args) {
        RedesController controller = new RedesController();
        Scanner leia = new Scanner(System.in);
        int escolha;

        do {
            System.out.println("\n+=================+");
            System.out.println("|      MENU       |");
            System.out.println("+=================+");
            System.out.println("| 1) Exibir IP    |");
            System.out.println("| 2) Exibir ping  |");
            System.out.println("| 0) Sair         |");
            System.out.println("+=================+");
            System.out.print("Digite a opção: ");
            escolha = leia.nextInt();

            switch (escolha) {
                case 1:
                    controller.ip();
                    break;
                case 2:
                    controller.ping();
                    break;
                case 0:
                    System.out.println("FINALIZADO");
                    break;
                default:
                    System.out.println("OPÇÃO INVÁLIDA!");
            }

        } while (escolha != 0);
    }
}
