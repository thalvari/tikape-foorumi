package tikape.runko.domain;

import java.sql.Timestamp;

public class Aihe {

    private int aiheId;
    private Timestamp aiheMuokattu;
    private String aiheNimi;
    private int aiheViestienMaara;

    public Aihe(int aiheId, Timestamp aiheMuokattu, String aiheNimi,
            int aiheViestienMaara) {
        this.aiheId = aiheId;
        this.aiheMuokattu = aiheMuokattu;
        this.aiheNimi = aiheNimi;
        this.aiheViestienMaara = aiheViestienMaara;
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

    public int getAiheViestienMaara() {
        return aiheViestienMaara;
    }

    public void setAiheViestienMaara(int aiheViestienMaara) {
        this.aiheViestienMaara = aiheViestienMaara;
    }

    @Override
    public String toString() {
        return aiheId + "|" + aiheMuokattu + "|" + aiheNimi + "|"
                + aiheViestienMaara;
    }
}
