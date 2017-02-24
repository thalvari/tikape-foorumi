package tikape.runko.domain;

import java.sql.Timestamp;

public class Aihe {

    private int id;
    private String nimi;
    private Timestamp viimeisin;

    public Aihe(int id, String nimi) {
        this.id = id;
        this.nimi = nimi;
    }

    public int getId() {
        return id;
    }

    public String getNimi() {
        return nimi;
    }

    @Override
    public String toString() {
        return id + "|" + nimi;
    }

    public Timestamp getViimeisin() {
        return viimeisin;
    }

    public void setViimeisin(Timestamp viimeisin) {
        this.viimeisin = viimeisin;
    }
}
