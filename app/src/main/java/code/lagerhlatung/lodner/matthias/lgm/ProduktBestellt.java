package code.lagerhlatung.lodner.matthias.lgm;

import java.io.Serializable;


class ProduktBestellt implements Serializable {

    private final int PBNr;

    private final int BNr;

    private final int Bestand;

    private final Produkt produkt;

    public ProduktBestellt(int pPBNr, int pBNr, int pBestand, Produkt pProdukt) {
        PBNr = pPBNr;
        BNr = pBNr;

        Bestand = pBestand;
        produkt = pProdukt;
    }


    public int getPBNr() {
        return PBNr;
    }

    public int getBNr() {
        return BNr;
    }

    public int getBestand() {
        return Bestand;
    }


    public Produkt getProdukt() {
        return produkt;
    }
}
