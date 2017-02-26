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
        return this.database.queryAndCollect("SELECT * FROM Viesti "
                + "ORDER BY viestiAika DESC",
                rs -> new Viesti(
                        rs.getInt("viestiId"),
                        new Ketju(rs.getInt("viestiKetju"),
                                ketjuDao.findOne(rs.getString("viestiKetju"))
                                .getKetjuAihe(),
                                ketjuDao.findOne(rs.getString("viestiKetju"))
                                .getKetjuMuokattu(),
                                ketjuDao.findOne(rs.getString("viestiKetju"))
                                .getKetjuOtsikko()),
                        rs.getTimestamp("viestiAika"),
                        rs.getString("viestiNimimerkki"),
                        rs.getString("viestiSisalto")));
    }

    @Override
    public Viesti findOne(String key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void save(Viesti viesti) throws SQLException {
        database.update("INSERT "
                + "INTO Viesti (viestiKetju, viestiAika, viestiNimimerkki, "
                + "viestiSisalto) VALUES (?, ?, ?, ?)",
                viesti.getViestiKetju().getKetjuId(),
                viesti.getViestiAika(),
                viesti.getViestiNimimerkki(),
                viesti.getViestiSisalto());
        database.update("UPDATE Aihe SET aiheMuokattu = ? WHERE aiheId = ?",
                viesti.getViestiAika(),
                viesti.getViestiKetju().getKetjuAihe().getAiheId());
        database.update("UPDATE Ketju SET ketjuMuokattu = ? WHERE ketjuId = ?",
                viesti.getViestiAika(),
                viesti.getViestiKetju().getKetjuId());
    }

    public List<Viesti> findBy(Ketju ketju) throws SQLException {
        return this.database.queryAndCollect("SELECT * FROM Ketju K, Viesti V "
                + "WHERE K.ketjuId = V.viestiKetju AND K.ketjuId = ? "
                + "ORDER BY viestiAika DESC",
                rs -> new Viesti(
                        rs.getInt("viestiId"),
                        new Ketju(rs.getInt("viestiKetju"),
                                ketjuDao.findOne(rs.getString("viestiKetju"))
                                .getKetjuAihe(),
                                ketjuDao.findOne(rs.getString("viestiKetju"))
                                .getKetjuMuokattu(),
                                ketjuDao.findOne(rs.getString("viestiKetju"))
                                .getKetjuOtsikko()),
                        rs.getTimestamp("viestiAika"),
                        rs.getString("viestiNimimerkki"),
                        rs.getString("viestiSisalto")),
                ketju.getKetjuId());
    }
}
