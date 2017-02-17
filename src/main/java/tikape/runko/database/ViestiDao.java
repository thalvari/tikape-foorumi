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
    public List<Viesti> findAll(int key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
