package tikape.runko.database;

import java.sql.*;
import java.util.*;
import tikape.runko.domain.Viesti;

public interface Dao<T, K> {

    T findOne(K key) throws SQLException;

    List<T> findAll() throws SQLException;

    List<T> findAll(K key) throws SQLException;

    void save(T element) throws SQLException;

    public T findViimeisinAihe(K key) throws SQLException;
    
    public T findViimeisinKetju(K key) throws SQLException;
}
