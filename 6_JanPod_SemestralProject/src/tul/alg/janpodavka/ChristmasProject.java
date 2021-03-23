package tul.alg.janpodavka;


import java.util.Scanner;



public class ChristmasProject {

    //Pridání barev a Scanneru
    public static final String Blue = "\u001B[44m";
    public static final String Black = "\u001B[47m";
    public static final String Red = "\u001B[41m";
    public static final String Reset = "\u001B[0m";
    public static final String White = "\u001B[40m";
    public static final String Skyblack = "\u001B[107m";
    public static final String Brown = "\u001B[100m";
    public static final String Green = "\033[42m";
    public static final String Yellow = "\033[103m";

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Zadejte výšku a šířku "); //doporučuje se alespoň 20, pro méně jak 10 nefunguje (vyskočení z pole)
        int radek = sc.nextInt();
        int sloupec = sc.nextInt();
        System.out.println("Zadejte početnost hvězd (0- skoro žádné až 10-všude)");
        int pocetHvezd = sc.nextInt();
        String[][] pole = new String[radek][sloupec];
        System.out.println("Je již napadaný sníh ? 0 = Ne, 1 = Ano");
        int jeSnih = sc.nextInt();
        System.out.println("Má skřítek nešika nasadit na stromeček ozdoby ? (0-NE !, 1-Ano prosím !)");
        int ozdoby = sc.nextInt();
        System.out.println("Sněží ? (0-NE, 1-Ano)");
        int snezi = sc.nextInt();


        //Vytvořit metody vykresli vše
        okraj(pole);
        snezeni(pole,snezi);
        Nebe(pole, pocetHvezd);
        zeme(pole, jeSnih);
        kmen(pole);
        strom(pole, ozdoby);
        darekvlevo(pole);
        darekvpravo(pole);
        zobrazPole(pole);


    }

    /**
     * Zobrazí zvolené 2D pole
     *
     * @param pole
     */
    public static void zobrazPole(String[][] pole) {
        for (int i = 0; i < pole.length; i++) {
            for (int j = 0; j < pole[i].length; j++) {
                System.out.print(pole[i][j]);
            }
            System.out.println();
        }

    }

    /**
     * Vykreslí okraj + vnitřek jako mezery
     *
     * @param pole
     */
    public static void okraj(String[][] pole) {
        for (int i = 0; i < pole.length; i++) {
            for (int j = 0; j < pole[i].length; j++) {

                if (i == 0 || i == pole.length - 1) {
                    pole[i][j] = (Black + "  " + Reset);
                } else if (j == 0 || j == pole[i].length - 1) {
                    pole[i][j] = (Black + "  " + Reset);
                } else {
                    pole[i][j] = "  ";
                }
            }
        }

    }

    /**
     * Vykreslí posle s náhodným rozložením hvězd, s četností dle parametrů pocetHvezd
     *
     * @param pole
     * @param pocetHvezd
     */
    public static void Nebe(String[][] pole, int pocetHvezd) {
        int randomnumber;
        for (int i = 1; i < pole.length / 3; i++) {
            for (int j = 1; j < pole[i].length - 1; j++) {
                randomnumber = (int) (Math.random() * ((10) + 1));
                if (randomnumber > pocetHvezd) {
                    pole[i][j] = (Skyblack + "  " + Reset);
                } else {
                    pole[i][j] = (Skyblack + "* " + Reset);
                }
            }

        }

    } // (1/3 celku)

    /**
     * Zjistí zda je sníh a podle toho vykreslí buď jen travnatou vrstvu, nebo na ní i sněhovou pokrývku
     *
     * @param pole
     * @param jeSnih
     */
    public static void zeme(String[][] pole, int jeSnih) {
        int zemindex = (int) ((double) pole.length * 0.833); // 5/6
        System.out.println(zemindex);
        for (int i = pole.length - 2; i > zemindex; i--) {
            for (int j = 1; j < pole[i].length - 1; j++) {
                switch (jeSnih) {
                    case 1:
                        if (pole.length - 2 == i) {
                            pole[i][j] = (Green + "  " + Reset); //zelena
                            break;
                        } else {
                            pole[i][j] = (White + "  " + Reset);
                            break;
                        }
                    case 0:
                        pole[i][j] = (Green + "  " + Reset);
                        break;
                }

            }

        }

    }

    /**
     * vykreslí kmen do šířky +-kmensirkaindex  a do výšky kmenindex od výšky zemindex
     *
     * @param pole
     */
    public static void kmen(String[][] pole) {
        int zemindex = (int) ((double) pole.length * 0.833);
        int kmenindex = (int) ((double) pole.length * 0.6);
        int kmensirkaindex = (int) ((double) pole[1].length * 0.075);
        System.out.println(kmensirkaindex);

        for (int i = zemindex; i > kmenindex; i--) {
            for (int j = ((pole[i].length) / 2) - kmensirkaindex; j < ((pole[i].length) / 2) + kmensirkaindex; j++) {
                pole[i][j] = (Brown + "  " + Reset);

            }
        }
    }

    /**
     * Vykreslí strom, podle parametru ozdoby bud s nima nebo bez (ozdoby mají šance 10% na zobrazení)
     *
     * @param pole
     * @param ozdoby
     */
    public static void strom(String[][] pole, int ozdoby) {

        int randomnumber;

        int kmenindex = (int) ((double) pole.length * 0.6);
        int stromsirkaindex = (int) ((double) pole[1].length * 0.2);
        int minusindex = -1; //index je kvůli kosení stromku a i%2 kvůli tomu aby se strom zúžil vždy po 2 px(indexech))
        for (int i = kmenindex; i > ((pole.length * 0.333) - 5); i--) { //bude asi potřeba doupravit -5 podle velikosti obrázku
            if (i % 2 == 0) {
                minusindex++;
            }
            for (int j = (((pole[i].length) / 2) - stromsirkaindex) + minusindex; j < (((pole[i].length) / 2) + stromsirkaindex) - minusindex; j++) {
                switch (ozdoby) {
                    case 1: //s ozdoby
                        randomnumber = (int) (Math.random() * ((10) + 1));
                        if (randomnumber < 10) {
                            pole[i][j] = (Green + "  " + Reset);
                            break;
                        } else {
                            pole[i][j] = (Red + "  " + Reset);
                            break;
                        }
                    case 0: //bez
                        pole[i][j] = (Green + "  " + Reset);
                        break;
                }


            }
        }
    }

    /**
     * Vykreslí červený dárek
     * @param pole
     */
    public static void darekvlevo(String[][] pole) {

        int zemindex = (int) ((double) pole.length * 0.833);
        int darekindex = (int) ((double) pole.length * 0.1);
        int kmensirkaindex = (int) ((double) pole[1].length * 0.075) + 1;
        for (int i = zemindex; i > (zemindex - darekindex);i-- ) {
            for (int j = (int) (0.25 * pole.length); j < ((pole[1].length) / 2) - kmensirkaindex; j++ ) {
                pole[i][j] = (Red + "  " + Reset);

            }
        }
    }

    /**
     * Vykreslí 2 dárky v pravo od stromu
     * @param pole
     */
    public static void darekvpravo(String[][] pole) {
// žlutý dárek
        int zemindex = (int) ((double) pole.length * 0.833);
        int darekindex = (int) ((double) pole.length * 0.075);
        int kmensirkaindex = (int) ((double) pole[1].length * 0.075) + 1;
        for (int i = zemindex; i > (zemindex - darekindex);i-- ) {
            for (int j = ((pole[1].length) / 2) + kmensirkaindex; j < (pole[1].length) * (0.75) ; j++ ) {
                pole[i][j] = (Yellow + "  " + Reset);

            }
        }
        //modrý dárek
        int darekindex2 = (int) ((double) pole.length * 0.125);
        for (int i = zemindex; i > (zemindex - darekindex2);i-- ) {
            for (int j = (int) ((pole[1].length) * (0.75)); j < (pole[1].length) * (0.8) ; j++ ) {
                pole[i][j] = (Blue + "  " + Reset);

            }
        }


    }

    /**
     * Dle "Sněží" vykreslí sníh s 10% šancí na objevení
     * @param pole
     * @param snezi
     */
    public static void snezeni(String[][] pole, int snezi) {
        int zemindex = (int) ((double) pole.length * 0.833);
        int randomNumber;
        for(int i = pole.length / 3; i < zemindex; i++){
            for(int j = 1; j < pole[i].length -1; j++){
                randomNumber = (int) (Math.random() * ((10) + 1));
                if(snezi == 1 && randomNumber >= 9){
                    pole[i][j] = (White + "  " + Reset);
                }
            }
        }
    }
}



