package tikape.runko.domain;

import java.util.ArrayList;

public class Ketju {

    private int id;
    private int aihe;
    private String otsikko;
    private ArrayList<Viesti> viestit;

    public Ketju(int id, int aihe, String otsikko) {
        this.id = id;
        this.aihe = aihe;
        this.otsikko = otsikko;
        viestit = new ArrayList<>();
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

    public ArrayList<Viesti> getViestit() {
        return viestit;
    }

    public void lisaaViesti(Viesti viesti) {
        viestit.add(viesti);
    }

    @Override
    public String toString() {
        return id + "|" + aihe + "|" + otsikko;
    }
}
