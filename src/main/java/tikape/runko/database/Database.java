package tikape.runko.database;

import java.sql.*;
import java.util.*;

public class Database {

    private String address;
    private boolean debug;

    public Database(String address) {
        this.address = address;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(address);
    }

    public void init() {
        List<String> lauseet = createTableLauseet();
        try {
            for (String lause : lauseet) {
                update(lause);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private List<String> createTableLauseet() {
        ArrayList<String> lista = new ArrayList<>();
        lista.add("CREATE TABLE Aihe ("
                + "aiheId integer PRIMARY KEY, "
                + "aiheMuokattu timestamp NOT NULL, "
                + "aiheNimi varchar(255) NOT NULL, "
                + "aiheViestienMaara integer NOT NULL)");
        lista.add("CREATE TABLE Ketju ("
                + "ketjuId integer PRIMARY KEY, "
                + "ketjuAihe integer NOT NULL, "
                + "ketjuMuokattu timestamp NOT NULL, "
                + "ketjuOtsikko varchar(255) NOT NULL, "
                + "ketjuViestienMaara integer NOT NULL, "
                + "FOREIGN KEY(ketjuAihe) REFERENCES Aihe(aiheId))");
        lista.add("CREATE TABLE Viesti ("
                + "viestiId integer PRIMARY KEY, "
                + "viestiKetju integer NOT NULL, "
                + "viestiAika timestamp NOT NULL, "
                + "viestiNimimerkki varchar(255) NOT NULL, "
                + "viestiSisalto varchar(2000) NOT NULL, "
                + "FOREIGN KEY(viestiKetju) REFERENCES Ketju(ketjuId))");
        return lista;
    }

    public <T> List<T> queryAndCollect(String query, Collector<T> col,
            Object... params) throws SQLException {
        Connection conn = getConnection();
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
        conn.close();
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
        Connection conn = getConnection();
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
        conn.close();
        return changes;
    }
}
