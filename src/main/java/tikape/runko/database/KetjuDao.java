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
    public List<Ketju> findAll(String offset) throws SQLException {
        return database.queryAndCollect("SELECT * FROM Ketju "
                + "ORDER BY ketjuMuokattu DESC LIMIT 10 OFFSET ?",
                rs -> new Ketju(
                        rs.getInt("ketjuId"),
                        aiheDao.findOne(rs.getString("ketjuAihe")),
                        rs.getTimestamp("ketjuMuokattu"),
                        rs.getString("ketjuOtsikko")), offset);

    }

    @Override
    public Ketju findOne(String key) throws SQLException {
        List<Ketju> ketjut = database.queryAndCollect(
                "SELECT * FROM Ketju WHERE ketjuId = ?",
                rs -> new Ketju(
                        rs.getInt("ketjuId"),
                        aiheDao.findOne(rs.getString("ketjuAihe")),
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

    public List<Ketju> findBy(Aihe aihe, String offset) throws SQLException {
        return database.queryAndCollect(
                "SELECT * FROM Ketju WHERE ketjuAihe = ? "
                + "ORDER BY ketjuMuokattu DESC LIMIT 10 OFFSET ?",
                rs -> new Ketju(
                        rs.getInt("ketjuId"),
                        aiheDao.findOne(rs.getString("ketjuAihe")),
                        rs.getTimestamp("ketjuMuokattu"),
                        rs.getString("ketjuOtsikko")),
                aihe.getAiheId(), offset);
    }
}
