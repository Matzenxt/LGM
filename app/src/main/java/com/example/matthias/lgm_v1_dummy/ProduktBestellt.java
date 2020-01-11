package com.example.matthias.lgm_v1_dummy;

import java.io.Serializable;



public class ProduktBestellt implements Serializable {

    private int PBNr;

    private int BNr;
    private int PNr;

    private int Bestand;

    private Produkt produkt;

    public ProduktBestellt(int pPBNr, int pBNr, int pPNr, int pBestand, Produkt pProdukt){
        PBNr = pPBNr;
        BNr = pBNr;
        PNr = pPNr;

        Bestand = pBestand;
        produkt = pProdukt;
    }


    public void setPBNr(int PBNr) {
        this.PBNr = PBNr;
    }

    public void setBNr(int BNr) {
        this.BNr = BNr;
    }

    public void setPNr(int PNr) {
        this.PNr = PNr;
    }

    public void setBestand(int bestand) {
        Bestand = bestand;
    }


    public int getPBNr() {
        return PBNr;
    }

    public int getBNr() {
        return BNr;
    }

    public int getPNr() {
        return PNr;
    }

    public int getBestand() {
        return Bestand;
    }



    public Produkt getProdukt(){
        return produkt;
    }
}
