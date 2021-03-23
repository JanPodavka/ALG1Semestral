package tul.alg.janpodavka;

import java.util.Scanner;

public class SemestralProject {
    /*
    doc
    Semestrální práce - Načtení dvou libovolných polynomů n-tého stupně a provedení jejich součinu a součtu
    @Jan Podávka
    */
    public static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        //inializace proměnných
        int degree = 0;
        boolean end = false;

        //while cyklus pro ukončení programu v případě záporného stupně 1. polynomu
        do {
            int[] firstPolynom = loadFirstPolynomials(degree);
            if (firstPolynom.length > 0) {
                int[] secondPolynom = loadFirstPolynomials(degree);
                int[] sum = sumOfPolynomials(firstPolynom, secondPolynom);
                int[] multiply = multipleOfPolynomials(firstPolynom, secondPolynom); // Tvorba polí
                gui(firstPolynom, secondPolynom, sum, multiply);
            } else {
                end = true;
            }
        } while (!end);


    }

    //metody logiky*************************************************

    /**
     * Vytvoří první, naplního zadanými koeficienty a kontroluje zda stupeň není záporny
     * Pole má delká degree + 1 (kvůli nultému prvku) a v případě záporého stupně o velikosti 0
     *
     * @param degree
     * @return
     */
    public static int[] loadFirstPolynomials(int degree) {
        System.out.println("Zadej stupeň prvního polynomu");
        degree = sc.nextInt();
        if (degree >= 0) {
            int[] firstPolynomial = new int[degree + 1]; //Vytvoří pole s velikostí stupeň + nulový prvek
            System.out.println("Zadej koeficienty prvního");
            fillArray(firstPolynomial);
            return firstPolynomial;// Naplní předvytvořené pole koeficienty
        } else {
            int[] firstPolynomial = new int[0];
            return firstPolynomial;
        }

    }



    /**
     * Naplní pole o jeho celé šiřce zadanými hodnotami
     *
     * @param polynomial
     * @return
     */
    public static int[] fillArray(int[] polynomial) {
        for (int i = polynomial.length - 1; i >= 0; i--) {
            polynomial[i] = sc.nextInt();
        }
        return polynomial;
    }

    /**
     * Vytvoří pole součtu dvou polí (Maximální možný stupeň při násobení)
     *
     * @param firstPolynomial
     * @param secondPolynomial
     * @return
     */
   /* public static int[] loadResultingPolynomial(int[] firstPolynomial, int[] secondPolynomial) {

        int[] result = new int[firstPolynomial.length + secondPolynomial.length];
        return result;
    } */

    /**
     * Sečte hodnoty 2 polynomů do výsledného pole
     *
     * @param firstPolynomial
     * @param secondPolynomial
     * @return sum
     */
    public static int[] sumOfPolynomials(int[] firstPolynomial, int[] secondPolynomial) {

        int[] sum;
        if(firstPolynomial.length >= secondPolynomial.length){
            sum = new int[firstPolynomial.length];
        }
        else{
            sum = new int[secondPolynomial.length];
        }
       // int[] sum = loadResultingPolynomial(firstPolynomial, secondPolynomial);

        for (int i = 0; i < firstPolynomial.length; i++) { //Sečtení dvou polynomů
            sum[i] = firstPolynomial[i];
        }
        for (int i = 0; i < secondPolynomial.length; i++) {
            sum[i] += secondPolynomial[i];
        }
        return sum;
    }

    /**
     * Vynásobení dvou polynomů-"každá hodnota s každou" do výsledného pole
     *
     * @param firstPolynomial
     * @param secondPolynomial
     * @return
     */
    public static int[] multipleOfPolynomials(int[] firstPolynomial, int[] secondPolynomial) {
       // int[] multiply = loadResultingPolynomial(firstPolynomial,secondPolynomial);
        int[] multiply = new int[firstPolynomial.length-1 + secondPolynomial.length];
        for (int i = 0; i < firstPolynomial.length; i++) {

            for (int j = 0; j < secondPolynomial.length; j++) {
                multiply[i + j] += firstPolynomial[i] * secondPolynomial[j]; //[stupeň] = hodnota daného stupně + další hodnota
            }
        }
        return multiply;
    }

    //zobrazovací metody***********************************************

    /**
     * Podle vstupního pole o velikosti n, zobrazí na standartní výstup tvar polynomu
     *
     * @param polynomial
     */
    public static void display(int[] polynomial) {

        int nonZeroNumber = 0;

        for (int i = polynomial.length - 1; i >= 0; i--) {

            //znaménko
            if (polynomial[i] > 0 && nonZeroNumber != 0) {
                System.out.print(" + ");
            }
            else if (polynomial[i] < 0) {
                System.out.print(" - ");
            }

            if (polynomial[i] != 0) { //poslední nenulové číslo
                nonZeroNumber = polynomial[i];
            }

            //Hodnota
            if (polynomial[i] != 0 && Math.abs(polynomial[i]) != 1) { //zobrazení hodnoty,abs kvůli zápornému známenu
                System.out.print(Math.abs(polynomial[i]));
            }
            if (Math.abs(polynomial[i]) == 1 && i == 0) { //Vyjímka pro případ že je absolutní člen 1
                System.out.print(Math.abs(polynomial[i]));
            }

            //Mocnina
            if (i > 1 && Math.abs(polynomial[i]) >= 1) {
                System.out.print("x^" + i); //pokud je to druhá a vyšší mocnina pro hodnoty > 1 ,+- od nuly
            } else if (i == 1 && polynomial[i] != 0) { //první mocnina
                System.out.print("x");
            }

        }
        System.out.println();


    }

    /**
     * Zobrazí první s druhým polynomem a jejich součet a součin
     *
     * @param firstPolynom
     * @param secondPolynom
     * @param sum
     * @param multiply
     */
    public static void gui(int[] firstPolynom, int[] secondPolynom, int[] sum, int[] multiply) {
        System.out.print("První polynom :");
        display(firstPolynom);
        System.out.print("Druhý polynom :");
        display(secondPolynom);
        System.out.print("součet polynomů :");
        display(sum);
        System.out.print("součin polynomů :");
        display(multiply);

    }

}
