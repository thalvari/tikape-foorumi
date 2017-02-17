package tikape.runko.database.collector;

import java.sql.ResultSet;
import java.sql.SQLException;
import tikape.runko.database.Collector;
import tikape.runko.domain.Viesti;

public class ViestiCollector implements Collector<Viesti> {

    @Override
    public Viesti collect(ResultSet rs) throws SQLException {
        return new Viesti(rs.getInt("ketju"),
                rs.getTimestamp("aika"),
                rs.getInt("id"),
                rs.getString("nimimerkki"),
                rs.getString("sisalto"),
                rs.getInt("vastausId"));
    }
}
