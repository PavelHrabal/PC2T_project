package Projekt;

import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static Databaze databaze = new Databaze();

    public static void main(String[] args) {
        boolean run = true;
        while (run) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Přidat nového studenta");
            System.out.println("2. Přidat známku studentovi");
            System.out.println("3. Propustit studenta");
            System.out.println("4. Vyhledat studenta podle ID");
            System.out.println("5. Spustit dovednost studenta");
            System.out.println("6. Abecední výpis studentů podle oboru");
            System.out.println("7. Výpis průměru v oborech");
            System.out.println("8. Výpis počtu studentů v oborech");
            System.out.println("9. Konec aplikace");
            System.out.println("10. Uložit databázi do souboru");
            System.out.println("11. Načíst databázi ze souboru");

            int volba = nactiInt("Zadejte volbu:");

            switch (volba) {
                case 1 -> pridatStudenta();
                case 2 -> pridatZnamku();
                case 3 -> propustitStudenta();
                case 4 -> vyhledatStudenta();
                case 5 -> spustitDovednost();
                case 6 -> databaze.vypisAbecedne();
                case 7 -> databaze.vypisPrumeryOboru();
                case 8 -> databaze.vypisPocetStudentuOboru();
                case 9 -> run = false;
                case 10 -> databaze.ulozDoSouboru("studenti.txt");
                case 11 -> databaze.nactiZeSouboru("studenti.txt");
                default -> System.out.println("Neplatná volba.");
            }
        }
    }

    private static void pridatStudenta() {
        System.out.println("Zvolte obor: 1 - Telekomunikace, 2 - Kyberbezpečnost");
        int obor = nactiInt("Obor:");
        String jmeno = nactiText("Jméno:");
        String prijmeni = nactiText("Příjmení:");
        int rok = nactiInt("Rok narození:");

        Student s;
        if (obor == 1) {
            s = new StudentTelekomunikace(jmeno, prijmeni, rok);
        } else {
            s = new StudentKyberbezpecnost(jmeno, prijmeni, rok);
        }
        databaze.pridatStudenta(s);
        System.out.println("Přidán student s ID: " + s.getId());
    }

    private static void pridatZnamku() {
        int id = nactiInt("Zadejte ID studenta:");
        int znamka = nactiInt("Zadejte známku (1-5):");
        if (znamka < 1 || znamka > 5) {
            System.out.println("Neplatná známka.");
            return;
        }
        Student s = databaze.najdiStudenta(id);
        if (s == null) {
            System.out.println("Student s ID " + id + " nenalezen!");
        } else {
            System.out.println("Nalezen student: " + s);
            s.pridatZnamku(znamka);
            System.out.println("Známka přidána.");
        }
    }

    private static void propustitStudenta() {
        int id = nactiInt("Zadejte ID studenta k odebrání:");
        databaze.odstranitStudenta(id);
    }

    private static void vyhledatStudenta() {
        int id = nactiInt("Zadejte ID studenta:");
        Student s = databaze.najdiStudenta(id);
        if (s != null) {
            System.out.println(s);
        } else {
            System.out.println("Student nenalezen.");
        }
    }

    private static void spustitDovednost() {
        int id = nactiInt("Zadejte ID studenta:");
        Student s = databaze.najdiStudenta(id);
        if (s != null) {
            s.spustDovednost();
        } else {
            System.out.println("Student nenalezen.");
        }
    }

    private static int nactiInt(String vyzva) {
        System.out.print(vyzva);
        while (!sc.hasNextInt()) {
            System.out.println("Zadejte celé číslo.");
            sc.next();
        }
        return sc.nextInt();
    }

    private static String nactiText(String vyzva) {
        System.out.print(vyzva);
        return sc.next();
    }
}
