package tikape.runko.domain;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Ketju {

    private int id;
    private int aihe;
    private String otsikko;
    private Timestamp viimeisin;

    public Ketju(int id, int aihe, String otsikko) {
        this.id = id;
        this.aihe = aihe;
        this.otsikko = otsikko;
    }

    public int getAihe() {
        return aihe;
    }

    public int getId() {
        return id;
    }

    public String getOtsikko() {
        return otsikko;
    }

    @Override
    public String toString() {
        return id + "|" + aihe + "|" + otsikko;
    }

    public Timestamp getViimeisin() {
        return viimeisin;
    }

    public void setViimeisin(Timestamp viimeisin) {
        this.viimeisin = viimeisin;
    }
}
