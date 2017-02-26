package tikape.runko.database;

import java.sql.*;
import java.util.*;

public interface Dao<T, K> {

    List<T> findAll() throws SQLException;

    T findOne(K key) throws SQLException;

    void save(T element) throws SQLException;
}
