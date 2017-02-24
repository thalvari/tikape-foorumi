package tikape.runko.database;

import java.sql.SQLException;
import java.util.List;
import tikape.runko.database.collector.ViestiCollector;
import tikape.runko.domain.Viesti;

public class ViestiDao implements Dao<Viesti, Integer> {

    private Database database;

    public ViestiDao(Database database) {
        this.database = database;
    }

    @Override
    public List<Viesti> findAll() throws SQLException {
        return this.database.queryAndCollect("SELECT * FROM Viesti",
                new ViestiCollector());
    }

    @Override
    public Viesti findOne(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void save(Viesti element) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Viesti> findAll(int aihe) throws SQLException {
        return this.database.queryAndCollect("SELECT * FROM Aihe A, Ketju K, "
                + "Viesti V WHERE A.id = ? AND A.id = K.aihe "
                + "AND K.id = V.ketju;",
                new ViestiCollector(), aihe);
    }
}
