package tikape.runko.database;

import java.sql.SQLException;
import java.util.List;
import tikape.runko.domain.Aihe;

public class AiheDao implements Dao<Aihe, String> {

    private Database database;

    public AiheDao(Database database) {
        this.database = database;
    }

    @Override
    public List<Aihe> findAll(String offset) throws SQLException {
        return database.queryAndCollect("SELECT * FROM Aihe "
                + "ORDER BY aiheMuokattu DESC LIMIT 10 OFFSET ?",
                rs -> new Aihe(
                        rs.getInt("aiheId"),
                        rs.getString("aiheNimi"),
                        rs.getTimestamp("aiheMuokattu")), offset);
    }

    @Override
    public Aihe findOne(String key) throws SQLException {
        List<Aihe> aiheet = database.queryAndCollect("SELECT * FROM Aihe "
                + "WHERE aiheId = ?",
                rs -> new Aihe(
                        rs.getInt("aiheId"),
                        rs.getString("aiheNimi"),
                        rs.getTimestamp("aiheMuokattu")),
                key);
        if (aiheet == null) {
            return null;
        } else {
            return aiheet.get(0);
        }
    }

    @Override
    public void save(Aihe aihe) throws SQLException {
        database.update("INSERT INTO Aihe (aiheMuokattu, aiheNimi) "
                + "VALUES (?, ?)",
                aihe.getAiheMuokattu(),
                aihe.getAiheNimi());
    }
}
