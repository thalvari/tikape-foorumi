package tikape.runko.database.collector;

import java.sql.ResultSet;
import java.sql.SQLException;
import tikape.runko.database.Collector;
import tikape.runko.domain.Ketju;

public class KetjuCollector implements Collector<Ketju> {

    @Override
    public Ketju collect(ResultSet rs) throws SQLException {
        return new Ketju(rs.getInt("id"),
                rs.getInt("aihe"),
                rs.getString("otsikko"));
    }
}
