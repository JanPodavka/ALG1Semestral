package tul.alg.janpodavka;

import java.util.Scanner;

/*
    doc
    * Program pro sečtení a roznásobení dvoujce polynomů
    @Jan Podávka
    * Datum vypracování: 03.01.2021
    */
public class UI {

    public static Scanner sc = new Scanner(System.in); //globální scanner

    public static void main(String[] args) {

        menu();

    }

    /**
     * Vygeneruje menu
     * Využívá metod-gui,loadChoice
     */
    public static void menu() {
        boolean end = false;
        do {
            gui();
            int choice = loadChoice();  //Načítání volby
            switch (choice) { // menu výběru mezi Vánoční úlohou,semestrální prací nebo koncem programu
                case 0:
                    end = true;
                    break;
                case 1:
                    ChristmasProject.main(null);
                    break;
                case 2:
                    SemestralProject.main(null);
                    break;
                default:
                    System.out.println("Zadej validní volbu !");
                    break;
            }
        } while (!end);
    }

    /**
     * Grafické uživatelské rozhraní
     *
     */
    public static void gui() {
        System.out.println("**************************************************************");
        System.out.println("*                      Výběr úloh                            *");
        System.out.println("*  0. Konec hry                                              *");
        System.out.println("*  1. Vánoční úloha                                          *");
        System.out.println("*  2. Semestrální práce                                      *");
        System.out.println("**************************************************************");


    }

    /**
     * Načte hodnotu vybrané volby
     * @return
     */
    public static int loadChoice() {
        System.out.println("Zadej volbu");
        return sc.nextInt();
    }
}
