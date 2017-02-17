package tikape.runko.domain;

import java.util.ArrayList;

public class Ketju {

    private int id;
    private Aihe aihe;
    private String otsikko;
    private ArrayList<Viesti> viestit;

    public Ketju(int id, Aihe aihe, String otsikko) {
        this.id = id;
        this.aihe = aihe;
        this.otsikko = otsikko;
        viestit = new ArrayList<>();
    }

    public Aihe getAihe() {
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
}
