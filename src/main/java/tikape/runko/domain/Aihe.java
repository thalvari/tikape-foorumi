package tikape.runko.domain;

import java.util.ArrayList;

public class Aihe {

    private int id;
    private String nimi;

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
}
