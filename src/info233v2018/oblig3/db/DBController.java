package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBController<T> {

    //Fields
    private DBAdapter adapter;

    public DBController(DBAdapter adapter) {
        this.adapter = adapter;
    }

    public String[] selectAllFromStringColumn(String column, String tbl){
        String sql = "SELECT " + column + " FROM " + tbl + ";";
        ArrayList<String> list = new ArrayList<>();

        try (Connection conn = adapter.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                list.add(rs.getString(column));
            }

            return list.toArray(new String[list.size()]);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return new String[1];
    }

    public Integer[] selectAllFromIntColumn(String column, String tbl){
        String sql = "SELECT " + column + " FROM " + tbl + ";";
        ArrayList<Integer> list = new ArrayList<>();

        try (Connection conn = adapter.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                list.add(rs.getInt(column));
            }

            return list.toArray(new Integer[list.size()]);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return new Integer[1];
    }

    public Float[] selectAllFromFloatColumn(String column, String tbl){
        String sql = "SELECT " + column + " FROM " + tbl + ";";
        ArrayList<Float> list = new ArrayList<>();

        try (Connection conn = adapter.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                list.add(rs.getFloat(column));
            }

            return list.toArray(new Float[list.size()]);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return new Float[1];
    }

    public void insert (String tbl, String insertedTxt) {
        String sql = "INSERT INTO " + tbl + " VALUES (" + insertedTxt + ");";

        try (Connection conn = adapter.connect();
             Statement pstmt = conn.createStatement()) {
            pstmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
