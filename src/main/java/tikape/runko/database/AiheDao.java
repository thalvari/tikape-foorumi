package tikape.runko.database;

import java.sql.SQLException;
import java.util.List;
import tikape.runko.database.collector.AiheCollector;
import tikape.runko.domain.Aihe;

public class AiheDao implements Dao<Aihe, Integer> {

    private Database database;

    public AiheDao(Database database) {
        this.database = database;
    }

    @Override
    public List<Aihe> findAll() throws SQLException {
        return this.database.queryAndCollect("SELECT * FROM Aihe",
                new AiheCollector());
    }

    @Override
    public Aihe findOne(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void save(Aihe element) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Aihe> findAll(int key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
