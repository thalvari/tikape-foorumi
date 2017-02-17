package tikape.runko.domain;

import java.util.ArrayList;

public class Aihe {

    private int id;
    private String nimi;
    private ArrayList<Ketju> ketjut;

    public Aihe(int id, String nimi) {
        this.id = id;
        this.nimi = nimi;
        ketjut = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNimi() {
        return nimi;
    }

    public ArrayList<Ketju> getKetjut() {
        return ketjut;
    }

    public void lisaaKetju(Ketju ketju) {
        ketjut.add(ketju);
    }

    @Override
    public String toString() {
        return id + "|" + nimi;
    }
}
