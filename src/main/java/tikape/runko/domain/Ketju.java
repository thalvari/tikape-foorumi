package tikape.runko.domain;

import java.sql.Timestamp;

public class Ketju {

    private int ketjuId;
    private Aihe ketjuAihe;
    private Timestamp ketjuMuokattu;
    private String ketjuOtsikko;

    public Ketju(int ketjuId, Aihe ketjuAihe, Timestamp ketjuMuokattu,
            String ketjuOtsikko) {
        this.ketjuId = ketjuId;
        this.ketjuAihe = ketjuAihe;
        this.ketjuMuokattu = ketjuMuokattu;
        this.ketjuOtsikko = ketjuOtsikko;
    }

    public Aihe getKetjuAihe() {
        return ketjuAihe;
    }

    public void setKetjuAihe(Aihe ketjuAihe) {
        this.ketjuAihe = ketjuAihe;
    }

    public int getKetjuId() {
        return ketjuId;
    }

    public void setKetjuId(int ketjuId) {
        this.ketjuId = ketjuId;
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

    @Override
    public String toString() {
        return ketjuId + "|" + ketjuMuokattu + "|" + ketjuOtsikko;
    }
}
