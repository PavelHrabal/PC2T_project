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
}