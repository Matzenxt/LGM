package com.example.matthias.lgm_v1_dummy;


import java.io.Serializable;

public class Bestellung implements Serializable {

    private int BNr;
    private int VNr;
    private String Datum;


    public Bestellung(int bnr, int vnr, String datum){
        BNr = bnr;
        VNr = vnr;
        Datum = datum;
    }


    public void setBNr(int BNr) {
        this.BNr = BNr;
    }

    public void setVNr(int VNr) {
        this.VNr = VNr;
    }

    public void setDatum(String datum) {
        Datum = datum;
    }


    public int getBNr() {
        return BNr;
    }

    public int getVNr() {
        return VNr;
    }

    public String getDatum() {
        return Datum;
    }

}
