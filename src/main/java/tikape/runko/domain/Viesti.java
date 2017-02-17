package tikape.runko.domain;

import java.sql.Timestamp;

public class Viesti {

    private int ketju;
    private Timestamp aika;
    private int id;
    private String nimimerkki;
    private String sisalto;
    private int vastausId;

    public Viesti(int ketju, Timestamp aika, int id, String nimimerkki,
            String sisalto, int vastausId) {
        this.ketju = ketju;
        this.aika = aika;
        this.id = id;
        this.nimimerkki = nimimerkki;
        this.sisalto = sisalto;
        this.vastausId = vastausId;
    }

    public Timestamp getAika() {
        return aika;
    }

    public int getId() {
        return id;
    }

    public int getKetju() {
        return ketju;
    }

    public String getNimimerkki() {
        return nimimerkki;
    }

    public String getSisalto() {
        return sisalto;
    }

    public int getVastausId() {
        return vastausId;
    }

    @Override
    public String toString() {
        return ketju + "|" + aika + "|" + id + "|"
                + nimimerkki + "|" + sisalto + "|" + vastausId;
    }
}
