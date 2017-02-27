package tikape.runko.domain;

import java.sql.Timestamp;

public class Ketju {

    private int ketjuId;
    private Aihe ketjuAihe;
    private Timestamp ketjuMuokattu;
    private String ketjuOtsikko;
    private int ketjuViestienMaara;

    public Ketju(int ketjuId, Aihe ketjuAihe, Timestamp ketjuMuokattu,
            String ketjuOtsikko, int ketjuViestienMaara) {
        this.ketjuId = ketjuId;
        this.ketjuAihe = ketjuAihe;
        this.ketjuMuokattu = ketjuMuokattu;
        this.ketjuOtsikko = ketjuOtsikko;
        this.ketjuViestienMaara = ketjuViestienMaara;
    }

    public Ketju(Aihe ketjuAihe, String ketjuOtsikko) {
        this(0, ketjuAihe, new Timestamp(System.currentTimeMillis()),
                ketjuOtsikko, 0);
    }

    public int getKetjuId() {
        return ketjuId;
    }

    public void setKetjuId(int ketjuId) {
        this.ketjuId = ketjuId;
    }

    public Aihe getKetjuAihe() {
        return ketjuAihe;
    }

    public void setKetjuAihe(Aihe ketjuAihe) {
        this.ketjuAihe = ketjuAihe;
    }

    public Timestamp getKetjuMuokattu() {
        return ketjuMuokattu;
    }

    public void setKetjuMuokattu(Timestamp ketjuMuokattu) {
        this.ketjuMuokattu = ketjuMuokattu;
    }

    public String getKetjuOtsikko() {
        return ketjuOtsikko;
    }

    public void setKetjuOtsikko(String ketjuOtsikko) {
        this.ketjuOtsikko = ketjuOtsikko;
    }

    public int getKetjuViestienMaara() {
        return ketjuViestienMaara;
    }

    public void setKetjuViestienMaara(int ketjuViestienMaara) {
        this.ketjuViestienMaara = ketjuViestienMaara;
    }

    @Override
    public String toString() {
        return ketjuId + "|" + ketjuMuokattu + "|" + ketjuOtsikko + "|"
                + ketjuViestienMaara;
    }
}
