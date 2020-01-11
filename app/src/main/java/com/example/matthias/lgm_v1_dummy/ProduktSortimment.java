package com.example.matthias.lgm_v1_dummy;

import java.io.Serializable;

public class ProduktSortimment implements Serializable {

    private int PSNr;
    private int PNr;
    private int SNr;

    private int SollBestand;

    private Produkt produkt;


    public ProduktSortimment(int pPSNr, int pPNr, int pSNr, int pSollBestand, Produkt pProdukt){
        PSNr = pPSNr;
        PNr = pPNr;
        SNr = pSNr;

        SollBestand = pSollBestand;
        produkt = pProdukt;
    }


    public int getPSNr() {
        return PSNr;
    }

    public void setPSNr(int PSNr) {
        this.PSNr = PSNr;
    }

    public int getPNr() {
        return PNr;
    }

    public void setPNr(int PNr) {
        this.PNr = PNr;
    }

    public int getSNr() {
        return SNr;
    }

    public void setSNr(int SNr) {
        this.SNr = SNr;
    }


    public Produkt getProdukt(){
        return produkt;
    }

    public int getSollBestand() {
        return SollBestand;
    }

    public void setSollBestand(int sollBestand) {
        SollBestand = sollBestand;
    }


}
