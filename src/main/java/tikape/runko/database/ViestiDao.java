package tikape.runko.database;

import java.sql.SQLException;
import java.util.List;
import tikape.runko.domain.Ketju;
import tikape.runko.domain.Viesti;

public class ViestiDao implements Dao<Viesti, String> {

    private Database database;
    private KetjuDao ketjuDao;

    public ViestiDao(Database database) {
        this.database = database;
        ketjuDao = new KetjuDao(database);
    }

    @Override
    public List<Viesti> findAll() throws SQLException {
        return this.database.queryAndCollect("SELECT * FROM Viesti",
                rs -> new Viesti(
                        rs.getInt("viestiId"),
                        new Ketju(rs.getInt("viestiKetju"),
                                ketjuDao.findOne(rs.getString("viestiKetju")).getKetjuAihe(),
                                ketjuDao.findOne(rs.getString("viestiKetju")).getKetjuMuokattu(),
                                ketjuDao.findOne(rs.getString("viestiKetju")).getKetjuOtsikko()),
                        rs.getTimestamp("viestiAika"),
                        rs.getString("viestiNimimerkki"),
                        rs.getString("viestiSisalto")));
    }

    @Override
    public void save(Viesti element) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Viesti findOne(String key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Viesti> findBy(Ketju ketju) throws SQLException {
        return this.database.queryAndCollect(
                "SELECT * FROM Ketju K, Viesti V WHERE K.id = V.ketju AND K.id = ?",
                rs -> new Viesti(
                        rs.getInt("viestiId"),
                        new Ketju(rs.getInt("viestiKetju"),
                                ketjuDao.findOne(rs.getString("viestiKetju")).getKetjuAihe(),
                                ketjuDao.findOne(rs.getString("viestiKetju")).getKetjuMuokattu(),
                                ketjuDao.findOne(rs.getString("viestiKetju")).getKetjuOtsikko()),
                        rs.getTimestamp("viestiAika"),
                        rs.getString("viestiNimimerkki"),
                        rs.getString("viestiSisalto")),
                ketju.getKetjuId());
    }
}
