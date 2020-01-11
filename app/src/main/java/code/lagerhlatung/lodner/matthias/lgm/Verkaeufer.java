package code.lagerhlatung.lodner.matthias.lgm;

import java.io.Serializable;

@SuppressWarnings("serial")
class Verkaeufer implements Serializable {

    private final int VNr;

    private final String Name;
    private final String Inhaber;
    private final String Ort;
    private final String PLZ;
    private final String Strasse;
    private final String Nr;

    public Verkaeufer(int pId, String pName, String pInhaber, String pOrt, String pPLZ, String pStrasse, String pNr) {
        VNr = pId;
        Name = pName;
        Inhaber = pInhaber;
        Ort = pOrt;
        PLZ = pPLZ;
        Strasse = pStrasse;
        Nr = pNr;
    }


    public int getVNr() {
        return VNr;
    }

    public String getName() {
        return Name;
    }

    public String getInhaber() {
        return Inhaber;
    }

    public String getOrt() {
        return Ort;
    }

    public String getPLZ() {
        return PLZ;
    }

    public String getStrasse() {
        return Strasse;
    }

    public String getNr() {
        return Nr;
    }
}
