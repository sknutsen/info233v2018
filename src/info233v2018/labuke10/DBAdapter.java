package info233v2018.labuke10;

import java.sql.*;

public class DBAdapter {
    /**
     * Connect to a sample database
     */
    public Connection connect() {
        /*Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:C:/Users/soknu/OneDrive/Dokumenter/GitHub/info233/info233v2018/src/info233v2018/labuke10/chinook.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }*/
        String url = "jdbc:sqlite:C:/Users/soknu/OneDrive/Dokumenter/GitHub/info233/info233v2018/src/info233v2018/labuke10/chinook.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void selectAll(){
        String sql = "SELECT EmployeeId, FirstName FROM employees";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("EmployeeId") +  "\t" +
                        rs.getString("FirstName") + "\t");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DBAdapter db = new DBAdapter();
        db.selectAll();
    }
}
