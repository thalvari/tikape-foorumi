package tikape.runko.domain;

import java.sql.Timestamp;

public class Viesti {

    private int viestiId;
    private Ketju viestiKetju;
    private Timestamp viestiAika;
    private String viestiNimimerkki;
    private String viestiSisalto;

    public Viesti(int viestiId, Ketju viestiKetju, Timestamp viestiAika,
            String viestiNimimerkki, String viestiSisalto) {
        this.viestiId = viestiId;
        this.viestiKetju = viestiKetju;
        this.viestiAika = viestiAika;
        this.viestiNimimerkki = viestiNimimerkki;
        this.viestiSisalto = viestiSisalto;
    }

    public Viesti(Ketju viestiKetju, String viestiNimimerkki,
            String viestiSisalto) {
        this(0, viestiKetju, new Timestamp(System.currentTimeMillis()),
                viestiNimimerkki, viestiSisalto);
    }

    public int getViestiId() {
        return viestiId;
    }

    public void setViestiId(int viestiId) {
        this.viestiId = viestiId;
    }

    public Timestamp getViestiAika() {
        return viestiAika;
    }

    public void setViestiAika(Timestamp viestiAika) {
        this.viestiAika = viestiAika;
    }

    public Ketju getViestiKetju() {
        return viestiKetju;
    }

    public void setViestiKetju(Ketju viestiKetju) {
        this.viestiKetju = viestiKetju;
    }

    public String getViestiNimimerkki() {
        return viestiNimimerkki;
    }

    public void setViestiNimimerkki(String viestiNimimerkki) {
        this.viestiNimimerkki = viestiNimimerkki;
    }

    public String getViestiSisalto() {
        return viestiSisalto;
    }

    public void setViestiSisalto(String viestiSisalto) {
        this.viestiSisalto = viestiSisalto;
    }

    @Override
    public String toString() {
        return viestiId + "|" + viestiAika + "|" + viestiNimimerkki + "|"
                + viestiSisalto;
    }
}
