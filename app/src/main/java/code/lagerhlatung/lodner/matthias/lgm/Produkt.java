package code.lagerhlatung.lodner.matthias.lgm;

import java.io.Serializable;

public class Produkt implements Serializable {

    private final int PNr;

    private final String Bezeichnung;
    private final int Fuellmenge;
    private final String Barcode;

    public Produkt(int pnr, String bezeichnung, int fuellmenge, String barcode) {
        PNr = pnr;
        Bezeichnung = bezeichnung;
        Fuellmenge = fuellmenge;
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
