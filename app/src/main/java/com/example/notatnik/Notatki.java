package com.example.notatnik;

public class Notatki {
    private String tytul;
    private String tresc;
    private String data;

    public String getTytul() {
        return tytul;
    }

    public String getTresc() {
        return tresc;
    }

    public String getData() {
        return data;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Notatki(String tytul, String tresc, String data) {
        this.tytul = tytul;
        this.tresc = tresc;
        this.data = data;
    }
}
