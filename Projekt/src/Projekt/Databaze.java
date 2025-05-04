package Projekt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.*;

class Databaze {
    private Map<Integer, Student> studenti = new HashMap<>();
    private int dalsiId = 1;

    public void pridatStudenta(Student s) {
        s.setId(dalsiId++);
        studenti.put(s.getId(), s);
    }

    public void odstranitStudenta(int id) {
        if (studenti.remove(id) != null) {
            System.out.println("Student s ID " + id + " byl odebrán.");
        } else {
            System.out.println("Student s ID " + id + " nenalezen.");
        }
    }

    public Student najdiStudenta(int id) {
        return studenti.get(id);
    }

    public void vypisAbecedne() {
        studenti.values().stream()
            .sorted(Comparator.comparing(Student::getPrijmeni).thenComparing(Student::getJmeno))
            .forEach(System.out::println);
    }

    public void vypisPrumeryOboru() {
        Map<String, List<Double>> prumery = new HashMap<>();
        for (Student s : studenti.values()) {
            prumery.computeIfAbsent(s.getObor(), k -> new ArrayList<>()).add(s.getPrumerZnamek());
        }
        for (String obor : prumery.keySet()) {
            List<Double> hodnoty = prumery.get(obor);
            double prumer = hodnoty.stream().mapToDouble(Double::doubleValue).average().orElse(0);
            System.out.printf("%s: %.2f\n", obor, prumer);
        }
    }

    public void vypisPocetStudentuOboru() {
        Map<String, Integer> pocty = new HashMap<>();
        for (Student s : studenti.values()) {
            pocty.put(s.getObor(), pocty.getOrDefault(s.getObor(), 0) + 1);
        }
        for (String obor : pocty.keySet()) {
            System.out.println(obor + ": " + pocty.get(obor));
        }
    }
    public void ulozDoSouboru(String nazevSouboru) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nazevSouboru))) {
            for (Student s : studenti.values()) {
                StringBuilder sb = new StringBuilder();
                sb.append(s.getId()).append(";")
                  .append(s.getJmeno()).append(";")
                  .append(s.getPrijmeni()).append(";")
                  .append(s.getRokNarozeni()).append(";")
                  .append(s instanceof StudentKyberbezpecnost ? "Kyberbezpecnost" : "Telekomunikace").append(";")
                  .append(s.getPrumerZnamek()).append(";")
                  .append(s.ziskejZnamkyJakoText());
                bw.write(sb.toString());
                bw.newLine();
            }
            System.out.println("Uloženo do souboru.");
        } catch (IOException e) {
            System.out.println("Chyba při ukládání: " + e.getMessage());
        }
    }

    public void nactiZeSouboru(String nazevSouboru) {
        try (BufferedReader br = new BufferedReader(new FileReader(nazevSouboru))) {
            String radek;
            while ((radek = br.readLine()) != null) {
                String[] casti = radek.split(";");
                if (casti.length >= 7) {
                	try {
                    int id = Integer.parseInt(casti[0]);
                    String jmeno = casti[1];
                    String prijmeni = casti[2];
                    int rok = Integer.parseInt(casti[3]);
                    String obor = casti[4];
                    String[] znamkyText = casti[6].split(",");

                    Student s;
                    if (obor.equals("Kyberbezpecnost")) {
                        s = new StudentKyberbezpecnost(jmeno, prijmeni, rok);
                    } else {
                        s = new StudentTelekomunikace(jmeno, prijmeni, rok);
                    }

                    s.setId(id);
                    for (String z : znamkyText) {
                        if (!z.isEmpty()) {
                            s.pridatZnamku(Integer.parseInt(z));
                        }
                    }

                    studenti.put(id, s);
                    dalsiId = Math.max(dalsiId, id + 1);
                	}
                	catch (NumberFormatException e){
                		  System.out.println("Chybný formát řádku: " + radek);
                	}
                }
            }
            System.out.println("Načteno ze souboru.");
        } catch (IOException | NumberFormatException e) {
            System.out.println("Chyba při načítání: " + e.getMessage());
        }
    }
}