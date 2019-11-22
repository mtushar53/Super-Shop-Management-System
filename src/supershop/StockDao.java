package labib;

import java.util.*;
import java.sql.*;
import java.io.*;

import labib.*;

public class StockDao {

    private Connection myConn;

    public StockDao() throws Exception {

        // get db properties
//        Properties props = new Properties();
//        props.load(new FileInputStream("demo.properties"));
//
//        String user = props.getProperty("user");
//        String password = props.getProperty("password");
//        String dburl = props.getProperty("dburl");

        // connect to database
        myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/database?autoReconnect=true&useSSL=false", "root", "tushar");

        System.out.println("DB connection successful to: " + "jdbc:mysql://localhost:3306/database");
    }

    public void addStock(Stock theStock) throws Exception {
        PreparedStatement myStmt = null;

        try {
            // prepare statement
            myStmt = myConn.prepareStatement("insert into stockinfo"
                    + " (id, name, quantity, price, supid)"
                    + " values (?, ?, ?, ?, ?)");

            // set params
            myStmt.setInt(1, theStock.getId());
            myStmt.setString(2, theStock.getName());
            myStmt.setInt(3, theStock.getQuantity());
            myStmt.setDouble(4,theStock.getPrice());
            myStmt.setInt(5, theStock.getSupId());

            // execute SQL
            myStmt.executeUpdate();
        }
        finally {
            close(myStmt);
        }

    }

    public void updateStock(Stock theStock) throws SQLException {
        PreparedStatement myStmt = null;

        try {
            // prepare statement
            myStmt = myConn.prepareStatement("update stockinfo"
                    + " set id=?, name=?, quantity=?, supid=?");

            // set params
            myStmt.setInt(1, theStock.getId());
            myStmt.setString(2, theStock.getName());
            myStmt.setInt(3, theStock.getQuantity());
            myStmt.setInt(4, theStock.getSupId());

            // execute SQL
            myStmt.executeUpdate();
        }
        finally {
            close(myStmt);
        }

    }

    public void deleteStock(int stockId) throws SQLException{
        PreparedStatement mystmt = null;

        try {
            mystmt = myConn.prepareStatement("delete from stockinfo where id=?");
            mystmt.setInt(1,stockId);
            mystmt.executeUpdate();
        }
        finally {
            close(mystmt);
        }

    }

    public List<Stock> getAllStock() throws Exception {
        List<Stock> list = new ArrayList<>();

        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery("select * from stockinfo");

            while (myRs.next()) {
                Stock tempStock = convertRowToStock(myRs);
                list.add(tempStock);
            }

            return list;
        }
        finally {
            close(myStmt, myRs);
        }
    }

    public Stock valueAtId(String id){
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        Stock tempStock=null;
        try {
            myStmt = myConn.prepareStatement("select * from stockinfo where id=?");
            myStmt.setString(1,id);
            myRs = myStmt.executeQuery();

            if(myRs.next()){
                tempStock = convertRowToStock(myRs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                close(myStmt,myRs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tempStock;
    }

    public List<Stock> searchStock(String lastName) throws Exception {
        List<Stock> list = new ArrayList<>();

        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            lastName += "%";
            myStmt = myConn.prepareStatement("select * from stockinfo where name like ?");

            myStmt.setString(1, lastName);

            myRs = myStmt.executeQuery();

            while (myRs.next()) {
                Stock tempStock = convertRowToStock(myRs);
                list.add(tempStock);
            }

            return list;
        }
        finally {
            close(myStmt, myRs);
        }
    }

    private Stock convertRowToStock(ResultSet myRs) throws SQLException {

        int id = myRs.getInt("id");
        String name = myRs.getString("name");
        int quantity  = myRs.getInt("quantity");
        double price = myRs.getDouble("price");
        int supId = myRs.getInt("supid");

        Stock tempStock = new Stock(id, name, quantity, price, supId);

        return tempStock;
    }


    private static void close(Connection myConn, Statement myStmt, ResultSet myRs)
            throws SQLException {

        if (myRs != null) {
            myRs.close();
        }

        if (myStmt != null) {

        }

        if (myConn != null) {
            myConn.close();
        }
    }

    private void close(Statement myStmt, ResultSet myRs) throws SQLException {
        close(null, myStmt, myRs);
    }

    private void close(Statement myStmt) throws SQLException {
        close(null, myStmt, null);
    }

    public static void main(String[] args) throws Exception {

        StockDao dao = new StockDao();
        System.out.println(dao.searchStock("1"));

        System.out.println(dao.getAllStock());
    }
}
