package tikape.foorumi.database;

import java.sql.SQLException;
import java.util.List;
import tikape.foorumi.domain.Ketju;
import tikape.foorumi.domain.Viesti;

public class ViestiDao implements Dao<Viesti, String> {

    private Database database;
    private KetjuDao ketjuDao;

    public ViestiDao(Database database) {
        this.database = database;
        ketjuDao = new KetjuDao(database);
    }

    @Override
    public List<Viesti> findAll(String offset) throws SQLException {
        return database.queryAndCollect("SELECT * FROM Viesti "
                + "ORDER BY viestiAika DESC LIMIT 10 OFFSET ?",
                rs -> new Viesti(
                        rs.getInt("viestiId"),
                        ketjuDao.findOne(rs.getString("viestiKetju")),
                        rs.getTimestamp("viestiAika"),
                        rs.getString("viestiNimimerkki"),
                        rs.getString("viestiSisalto")),
                offset);
    }

    @Override
    public Viesti findOne(String key) throws SQLException {
        List<Viesti> viestit = database.queryAndCollect(
                "SELECT * FROM Viesti WHERE viestiId = ?",
                rs -> new Viesti(
                        rs.getInt("viestiId"),
                        ketjuDao.findOne(rs.getString("viestiKetju")),
                        rs.getTimestamp("viestiAika"),
                        rs.getString("viestiNimimerkki"),
                        rs.getString("viestiSisalto")),
                key);
        if (viestit == null || viestit.isEmpty()) {
            return null;
        } else {
            return viestit.get(0);
        }
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
        database.update("UPDATE Aihe SET aiheViestienMaara = ? "
                + "WHERE aiheId = ?",
                viesti.getViestiKetju().getKetjuAihe().getAiheViestienMaara()
                + 1,
                viesti.getViestiKetju().getKetjuAihe().getAiheId());
        database.update("UPDATE Ketju SET ketjuMuokattu = ? WHERE ketjuId = ?",
                viesti.getViestiAika(),
                viesti.getViestiKetju().getKetjuId());
        database.update("UPDATE Ketju SET ketjuViestienMaara = ? "
                + "WHERE ketjuId = ?",
                viesti.getViestiKetju().getKetjuViestienMaara() + 1,
                viesti.getViestiKetju().getKetjuId());
    }

    public List<Viesti> findBy(Ketju ketju, String offset) throws SQLException {
        return database.queryAndCollect("SELECT * FROM Ketju K, Viesti V "
                + "WHERE K.ketjuId = V.viestiKetju AND K.ketjuId = ? "
                + "ORDER BY viestiAika DESC LIMIT 10 OFFSET ?",
                rs -> new Viesti(
                        rs.getInt("viestiId"),
                        ketjuDao.findOne(rs.getString("viestiKetju")),
                        rs.getTimestamp("viestiAika"),
                        rs.getString("viestiNimimerkki"),
                        rs.getString("viestiSisalto")),
                ketju.getKetjuId(),
                offset);
    }
}
