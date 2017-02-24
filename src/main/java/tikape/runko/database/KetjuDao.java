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
    public List<Ketju> findAll(Integer key) throws SQLException {
        return this.database.queryAndCollect(
                "SELECT * FROM Ketju K WHERE K.aihe = ?", new KetjuCollector(),
                key);
    }

    @Override
    public Ketju findOne(Integer key) throws SQLException {
        List<Ketju> ketjut = this.database.queryAndCollect(
                "SELECT * FROM Ketju WHERE id = ?", new KetjuCollector(),
                key);
        if (ketjut == null) {
            return null;
        } else {
            return ketjut.get(0);
        }
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

    @Override
    public Ketju findViimeisinAihe(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Ketju findViimeisinKetju(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
