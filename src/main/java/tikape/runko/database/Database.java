package tikape.runko.database;

import java.sql.*;
import java.util.*;

public class Database {

    private String databaseAddress;
    private Connection conn;
    private boolean debug;

    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress);
    }

    public void init() {
        List<String> lauseet = sqliteLauseet();

        // "try with resources" sulkee resurssin automaattisesti lopuksi
        try {
            conn = getConnection();
            Statement st = conn.createStatement();

            // suoritetaan komennot
            for (String lause : lauseet) {
                System.out.println("Running command >> " + lause);
                st.executeUpdate(lause);
            }

        } catch (Throwable t) {
            // jos tietokantataulu on jo olemassa, ei komentoja suoriteta
            System.out.println("Error >> " + t.getMessage());
        }
    }

    private List<String> sqliteLauseet() {
        ArrayList<String> lista = new ArrayList<>();

        lista.add("CREATE TABLE Aihe (id integer PRIMARY KEY,"
                + " nimi varchar(255) NOT NULL);");
        lista.add("CREATE TABLE Ketju (id integer PRIMARY KEY,"
                + " aihe integer NOT NULL,"
                + " otsikko varchar(255) NOT NULL,"
                + " FOREIGN KEY(aihe) REFERENCES Aihe(id));");
        lista.add("CREATE TABLE Viesti (ketju integer NOT NULL,"
                + " aika timestamp NOT NULL,"
                + " id integer NOT NULL,"
                + " nimimerkki varchar(255) NOT NULL,"
                + " sisalto varchar(2000) NOT NULL,"
                + " vastausId integer,"
                + " FOREIGN KEY(ketju) REFERENCES Ketju(id));");

        return lista;
    }

    public <T> List<T> queryAndCollect(String query,
            Collector<T> col, Object... params) throws SQLException {
        if (debug) {
            System.out.println("---");
            System.out.println("Executing: " + query);
            System.out.println("---");
        }

        List<T> rows = new ArrayList<>();
        PreparedStatement stmt = conn.prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            if (debug) {
                System.out.println("---");
                System.out.println(query);
                debug(rs);
                System.out.println("---");
            }

            rows.add(col.collect(rs));
        }

        rs.close();
        stmt.close();
        return rows;
    }

    public void setDebugMode(boolean d) {
        debug = d;
    }

    private void debug(ResultSet rs) throws SQLException {
        int columns = rs.getMetaData().getColumnCount();
        for (int i = 0; i < columns; i++) {
            System.out.print(
                    rs.getObject(i + 1) + ":"
                    + rs.getMetaData().getColumnName(i + 1) + "  ");
        }

        System.out.println();
    }

    public int update(String updateQuery, Object... params)
            throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(updateQuery);

        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }

        int changes = stmt.executeUpdate();

        if (debug) {
            System.out.println("---");
            System.out.println(updateQuery);
            System.out.println("Changed rows: " + changes);
            System.out.println("---");
        }
        stmt.close();

        return changes;
    }
}
