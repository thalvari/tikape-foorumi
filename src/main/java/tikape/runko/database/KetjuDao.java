package tikape.runko.database;

import java.sql.SQLException;
import java.util.List;
import tikape.runko.domain.Aihe;
import tikape.runko.domain.Ketju;

public class KetjuDao implements Dao<Ketju, String> {

    private Database database;
    private AiheDao aiheDao;

    public KetjuDao(Database database) {
        this.database = database;
        aiheDao = new AiheDao(database);
    }

    @Override
    public List<Ketju> findAll() throws SQLException {
        return database.queryAndCollect("SELECT * FROM Ketju",
                rs -> new Ketju(
                        rs.getInt("ketjuId"),
                        new Aihe(
                                rs.getInt("ketjuAihe"),
                                aiheDao.findOne(rs.getString("ketjuAihe"))
                                .getAiheNimi(),
                                aiheDao.findOne(rs.getString("ketjuAihe"))
                                .getAiheMuokattu()),
                        rs.getTimestamp("ketjuMuokattu"),
                        rs.getString("ketjuOtsikko")));

    }

    @Override
    public Ketju findOne(String key) throws SQLException {
        List<Ketju> ketjut = database.queryAndCollect(
                "SELECT * FROM Ketju WHERE ketjuId = ?",
                rs -> new Ketju(
                        rs.getInt("ketjuId"),
                        new Aihe(
                                rs.getInt("ketjuAihe"),
                                aiheDao.findOne(rs.getString("ketjuAihe"))
                                .getAiheNimi(),
                                aiheDao.findOne(rs.getString("ketjuAihe"))
                                .getAiheMuokattu()),
                        rs.getTimestamp("ketjuMuokattu"),
                        rs.getString("ketjuOtsikko")),
                key);
        if (ketjut == null) {
            return null;
        } else {
            return ketjut.get(0);
        }
    }

    @Override
    public void save(Ketju ketju) throws SQLException {
        database.update("INSERT "
                + "INTO Ketju (ketjuAihe, ketjuMuokattu, ketjuOtsikko) "
                + "VALUES (?, ?, ?)",
                ketju.getKetjuAihe().getAiheId(),
                ketju.getKetjuMuokattu(),
                ketju.getKetjuOtsikko());
        database.update("UPDATE Aihe SET aiheMuokattu = ? WHERE aiheId = ?",
                ketju.getKetjuMuokattu(),
                ketju.getKetjuAihe().getAiheId());
    }

    public List<Ketju> findBy(Aihe aihe) throws SQLException {
        return database.queryAndCollect(
                "SELECT * FROM Aihe A, Ketju K WHERE A.aiheId = K.ketjuAihe "
                + "AND A.aiheId = ?",
                rs -> new Ketju(
                        rs.getInt("ketjuId"),
                        new Aihe(
                                rs.getInt("ketjuAihe"),
                                aiheDao.findOne(rs.getString("ketjuAihe"))
                                .getAiheNimi(),
                                aiheDao.findOne(rs.getString("ketjuAihe"))
                                .getAiheMuokattu()),
                        rs.getTimestamp("ketjuMuokattu"),
                        rs.getString("ketjuOtsikko")),
                aihe.getAiheId());
    }
}
