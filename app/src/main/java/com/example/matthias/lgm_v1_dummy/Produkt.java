package com.example.matthias.lgm_v1_dummy;

import java.io.Serializable;

public class Produkt implements Serializable {

    private int PNr;

    private String Bezeichnung;
    private int Fuellmenge;
    private String Barcode;

    public Produkt(int pnr, String bezeichnung, int fuellmenge, String barcode){
        PNr = pnr;
        Bezeichnung = bezeichnung;
        Fuellmenge = fuellmenge;
        Barcode = barcode;
    }

    public void setPNr(int PNr) {
        this.PNr = PNr;
    }

    public void setBezeichnung(String bezeichnung) {
        Bezeichnung = bezeichnung;
    }

    public void setFuellmenge(int fuellmenge) {
        Fuellmenge = fuellmenge;
    }

    public void setBarcode(String barcode) {
        Barcode = barcode;
    }


    public int getPNr() {
        return PNr;
    }

    public String getBezeichnung() {
        return Bezeichnung;
    }

    public int getFuellmenge() {
        return Fuellmenge;
    }

    public String getBarcode() {
        return Barcode;
    }
}
