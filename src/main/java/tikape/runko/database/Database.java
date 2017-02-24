package tikape.runko.database;

import java.sql.*;
import java.util.*;

public class Database {

    private String databaseAddress;
    private Connection conn;
    private boolean debug;

    public Database(String driver, String databaseAddress) throws ClassNotFoundException {
        Class.forName(driver);
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

        lista.add("INSERT INTO Aihe (nimi) VALUES ('A');");
        lista.add("INSERT INTO Aihe (nimi) VALUES ('B');");
        lista.add("INSERT INTO Aihe (nimi) VALUES ('C');");
        lista.add("INSERT INTO Aihe (nimi) VALUES ('D');");
        lista.add("INSERT INTO Aihe (nimi) VALUES ('E');");
        lista.add("INSERT INTO Aihe (nimi) VALUES ('F');");
        lista.add("INSERT INTO Aihe (nimi) VALUES ('G');");
        lista.add("INSERT INTO Aihe (nimi) VALUES ('H');");
        lista.add("INSERT INTO Aihe (nimi) VALUES ('I');");
        lista.add("INSERT INTO Aihe (nimi) VALUES ('J');");
        lista.add("INSERT INTO Aihe (nimi) VALUES ('K');");
        lista.add("INSERT INTO Aihe (nimi) VALUES ('L');");

        lista.add("INSERT INTO Ketju (aihe, otsikko) "
                + "VALUES (1, 'a');");
        lista.add("INSERT INTO Ketju (aihe, otsikko) "
                + "VALUES (1, 'b');");
        lista.add("INSERT INTO Ketju (aihe, otsikko) "
                + "VALUES (1, 'c');");
        lista.add("INSERT INTO Ketju (aihe, otsikko) "
                + "VALUES (1, 'd');");
        lista.add("INSERT INTO Ketju (aihe, otsikko) "
                + "VALUES (1, 'e');");
        lista.add("INSERT INTO Ketju (aihe, otsikko) "
                + "VALUES (6, 'f');");
        lista.add("INSERT INTO Ketju (aihe, otsikko) "
                + "VALUES (6, 'g');");
        lista.add("INSERT INTO Ketju (aihe, otsikko) "
                + "VALUES (8, 'h');");

        System.out.println(new Timestamp(System.currentTimeMillis()).toString());
        lista.add("INSERT INTO Viesti (ketju, aika, id, nimimerkki, sisalto, "
                + "vastausId) VALUES (1, '"
                + new Timestamp(System.currentTimeMillis()).toString()
                + "', 1, 'käyttäjä', 'tekstiä', 0)");
        lista.add("INSERT INTO Viesti (ketju, aika, id, nimimerkki, sisalto, "
                + "vastausId) VALUES (1, '"
                + new Timestamp(System.currentTimeMillis()).toString()
                + "', 2, 'käyttäjä', 'tekstiä', 0)");
        lista.add("INSERT INTO Viesti (ketju, aika, id, nimimerkki, sisalto, "
                + "vastausId) VALUES (1, '"
                + new Timestamp(System.currentTimeMillis()).toString()
                + "', 3, 'käyttäjä', 'tekstiä', 0)");
        lista.add("INSERT INTO Viesti (ketju, aika, id, nimimerkki, sisalto, "
                + "vastausId) VALUES (1, '"
                + new Timestamp(System.currentTimeMillis()).toString()
                + "', 4, 'käyttäjä', 'tekstiä', 0)");
        lista.add("INSERT INTO Viesti (ketju, aika, id, nimimerkki, sisalto, "
                + "vastausId) VALUES (1, '"
                + new Timestamp(System.currentTimeMillis()).toString()
                + "', 5, 'käyttäjä', 'tekstiä', 0)");
        lista.add("INSERT INTO Viesti (ketju, aika, id, nimimerkki, sisalto, "
                + "vastausId) VALUES (1, '"
                + new Timestamp(System.currentTimeMillis()).toString()
                + "', 6, 'käyttäjä', 'tekstiä', 0)");
        lista.add("INSERT INTO Viesti (ketju, aika, id, nimimerkki, sisalto, vastausId) VALUES (1, '2017-02-24 12:11:05', 7, 'käyttäjä', 'tekstiä', 0");

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
