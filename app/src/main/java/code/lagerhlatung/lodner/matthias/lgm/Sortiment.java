package code.lagerhlatung.lodner.matthias.lgm;


import java.io.Serializable;

class Sortiment implements Serializable {

    private final int SNr;
    private final int VNr;
    private final String Beschreibung;


    public Sortiment(int snr, int vnr, String beschreibung) {
        SNr = snr;
        VNr = vnr;

        Beschreibung = beschreibung;
    }


    public int getSNr() {
        return SNr;
    }

    public int getVNr() {
        return VNr;
    }

    public String getBeschreibung() {
        return Beschreibung;
    }

}
