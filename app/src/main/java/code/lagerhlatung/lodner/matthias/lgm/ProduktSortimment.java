package code.lagerhlatung.lodner.matthias.lgm;

import java.io.Serializable;

class ProduktSortimment implements Serializable {

    private final int PSNr;
    private final int SNr;

    private final int SollBestand;

    private final Produkt produkt;


    public ProduktSortimment(int pPSNr, int pSNr, int pSollBestand, Produkt pProdukt) {
        PSNr = pPSNr;
        SNr = pSNr;

        SollBestand = pSollBestand;
        produkt = pProdukt;
    }


    public int getPSNr() {
        return PSNr;
    }

    public int getSNr() {
        return SNr;
    }


    public Produkt getProdukt() {
        return produkt;
    }

    public int getSollBestand() {
        return SollBestand;
    }


}
