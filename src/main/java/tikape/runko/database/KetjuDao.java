package tikape.runko.database;

import java.sql.SQLException;
import java.util.List;
import tikape.runko.database.collector.KetjuCollector;
import tikape.runko.domain.Ketju;

public class KetjuDao implements Dao<Ketju, Integer> {

    private Database database;

    public KetjuDao(Database database) {
        this.database = database;
    }

    @Override
    public List<Ketju> findAll(int aihe) throws SQLException {
        return this.database.queryAndCollect(
                "SELECT K.id, K.aihe, K.otsikko "
                + "FROM Aihe A, Ketju K WHERE A.id = K.aihe "
                + "AND A.id = ?",
                new KetjuCollector(), aihe);
    }

    @Override
    public Ketju findOne(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void save(Ketju element) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Ketju> findAll() throws SQLException {
        return this.database.queryAndCollect("SELECT * FROM Ketju",
                new KetjuCollector());
    }
}
