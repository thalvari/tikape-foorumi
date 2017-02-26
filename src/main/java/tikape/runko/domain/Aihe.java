package tikape.runko.domain;

import java.sql.Timestamp;

public class Aihe {

    private int aiheId;
    private String aiheNimi;
    private Timestamp aiheMuokattu;

    public Aihe(int aiheId, String aiheNimi, Timestamp aiheMuokattu) {
        this.aiheId = aiheId;
        this.aiheNimi = aiheNimi;
        this.aiheMuokattu = aiheMuokattu;
    }

    public int getAiheId() {
        return aiheId;
    }

    public void setAiheId(int aiheId) {
        this.aiheId = aiheId;
    }

    public Timestamp getAiheMuokattu() {
        return aiheMuokattu;
    }

    public void setAiheMuokattu(Timestamp aiheMuokattu) {
        this.aiheMuokattu = aiheMuokattu;
    }

    public String getAiheNimi() {
        return aiheNimi;
    }

    public void setAiheNimi(String aiheNimi) {
        this.aiheNimi = aiheNimi;
    }

    @Override
    public String toString() {
        return aiheId + "|" + aiheMuokattu + "|" + aiheNimi;
    }
}
