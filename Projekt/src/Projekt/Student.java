package Projekt;

import java.util.ArrayList;
import java.util.List;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

abstract class Student {
    private int id;
    private String jmeno;
    private String prijmeni;
    private int rokNarozeni;
    private List<Integer> znamky = new ArrayList<>();

    public Student(String jmeno, String prijmeni, int rokNarozeni) {
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.rokNarozeni = rokNarozeni;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJmeno() {
        return jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public void pridatZnamku(int znamka) {
        znamky.add(znamka);
    }
    public Integer getRokNarozeni() {
    	return rokNarozeni;
    }

    public double getPrumerZnamek() {
        return znamky.stream().mapToInt(Integer::intValue).average().orElse(0);
    }
    public String ziskejZnamkyJakoText() {
        StringBuilder sb = new StringBuilder();
        for (int z : znamky) {
            sb.append(z).append(",");
        }
        return sb.toString();
    }

    public abstract void spustDovednost();

    public abstract String getObor();

    @Override
    public String toString() {
        return String.format("ID: %d, %s %s, %d, Průměr: %.2f, Obor: %s",
                id, jmeno, prijmeni, rokNarozeni, getPrumerZnamek(), getObor());
    }
}