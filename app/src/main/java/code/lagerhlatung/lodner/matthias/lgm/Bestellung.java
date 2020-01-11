package code.lagerhlatung.lodner.matthias.lgm;


import java.io.Serializable;

class Bestellung implements Serializable {

    private final int BNr;
    private final int VNr;
    private final String Datum;


    public Bestellung(int bnr, int vnr, String datum) {
        BNr = bnr;
        VNr = vnr;
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
