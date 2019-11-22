package labib;

import java.util.*;
import java.sql.*;
import java.io.*;

import labib.*;

public class SupplierDao {

    private Connection myConn;

    public SupplierDao() throws Exception {

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

    public void addSupplier(Supplier theSupplier) throws Exception {
        PreparedStatement myStmt = null;

        try {
            // prepare statement
            myStmt = myConn.prepareStatement("insert into supplierinfo"
                    + " (id, name, address, phoneno)"
                    + " values (?, ?, ?, ?)");

            // set params
            myStmt.setInt(1, theSupplier.getId());
            myStmt.setString(2, theSupplier.getName());
            myStmt.setString(3, theSupplier.getAddress());
            myStmt.setString(4, theSupplier.getPhoneNo());

            // execute SQL
            myStmt.executeUpdate();
        }
        finally {
            close(myStmt);
        }

    }

    public void updateSupplier(Supplier theSupplier) throws SQLException {
        PreparedStatement myStmt = null;

        try {
            // prepare statement
            myStmt = myConn.prepareStatement("update supplierinfo"
                    + " set id=?, name=?, address=?, phoneno=?");

            // set params
            myStmt.setInt(1, theSupplier.getId());
            myStmt.setString(2, theSupplier.getName());
            myStmt.setString(3, theSupplier.getAddress());
            myStmt.setString(4, theSupplier.getPhoneNo());

            // execute SQL
            myStmt.executeUpdate();
        }
        finally {
            close(myStmt);
        }

    }

    public void deleteSupplier(int supplierId) throws SQLException{
        PreparedStatement mystmt = null;

        try {
            mystmt = myConn.prepareStatement("delete from supplierinfo where id=?");
            mystmt.setInt(1,supplierId);
            mystmt.executeUpdate();
        }
        finally {
            close(mystmt);
        }

    }

    public List<Supplier> getAllSupplier() throws Exception {
        List<Supplier> list = new ArrayList<>();

        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery("select * from supplierinfo");

            while (myRs.next()) {
                Supplier tempSupplier = convertRowToSupplier(myRs);
                list.add(tempSupplier);
            }

            return list;
        }
        finally {
            close(myStmt, myRs);
        }
    }

    public List<Supplier> searchSupplier(String lastName) throws Exception {
        List<Supplier> list = new ArrayList<>();

        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            lastName += "%";
            myStmt = myConn.prepareStatement("select * from supplierinfo where name like ?");

            myStmt.setString(1, lastName);

            myRs = myStmt.executeQuery();

            while (myRs.next()) {
                Supplier tempSupplier = convertRowToSupplier(myRs);
                list.add(tempSupplier);
            }

            return list;
        }
        finally {
            close(myStmt, myRs);
        }
    }

    private Supplier convertRowToSupplier(ResultSet myRs) throws SQLException {

        int id = myRs.getInt("id");
        String name = myRs.getString("name");
        String address  = myRs.getString("address");
        String phoneNo = myRs.getString("phoneno");

        Supplier tempSupplier = new Supplier(id, name, address, phoneNo);

        return tempSupplier;
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

        SupplierDao dao = new SupplierDao();
        System.out.println(dao.searchSupplier("1"));

        System.out.println(dao.getAllSupplier());
    }
}
