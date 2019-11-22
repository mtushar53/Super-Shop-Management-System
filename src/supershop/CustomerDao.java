package labib;

import java.util.*;
import java.sql.*;
import java.io.*;

import labib.*;

public class CustomerDao {

    private Connection myConn;

    public CustomerDao() throws Exception {

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

    public void addCustomer(Customer theCustomer) throws Exception {
        PreparedStatement myStmt = null;

        try {
            // prepare statement
            myStmt = myConn.prepareStatement("insert into customerinfo"
                    + " (id, name, address, phoneno)"
                    + " values (?, ?, ?, ?)");

            // set params
            myStmt.setInt(1, theCustomer.getId());
            myStmt.setString(2, theCustomer.getName());
            myStmt.setString(3, theCustomer.getAddress());
            myStmt.setString(4, theCustomer.getPhoneNo());

            // execute SQL
            myStmt.executeUpdate();
        }
        finally {
            close(myStmt);
        }

    }

    public void updateCustomer(Customer theCustomer) throws SQLException {
        PreparedStatement myStmt = null;

        try {
            // prepare statement
            myStmt = myConn.prepareStatement("update customerinfo"
                    + " set id=?, name=?, address=?, phoneno=?");

            // set params
            myStmt.setInt(1, theCustomer.getId());
            myStmt.setString(2, theCustomer.getName());
            myStmt.setString(3, theCustomer.getAddress());
            myStmt.setString(4, theCustomer.getPhoneNo());

            // execute SQL
            myStmt.executeUpdate();
        }
        finally {
            close(myStmt);
        }

    }

    public void deleteCustomer(int customerId) throws SQLException{
        PreparedStatement mystmt = null;

        try {
            mystmt = myConn.prepareStatement("delete from customerinfo where id=?");
            mystmt.setInt(1,customerId);
            mystmt.executeUpdate();
        }
        finally {
            close(mystmt);
        }

    }

    public List<Customer> getAllCustomer() throws Exception {
        List<Customer> list = new ArrayList<>();

        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery("select * from customerinfo");

            while (myRs.next()) {
                Customer tempCustomer = convertRowToCustomer(myRs);
                list.add(tempCustomer);
            }

            return list;
        }
        finally {
            close(myStmt, myRs);
        }
    }

    public List<Customer> searchCustomer(String lastName) throws Exception {
        List<Customer> list = new ArrayList<>();

        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            lastName += "%";
            myStmt = myConn.prepareStatement("select * from customerinfo where name like ?");

            myStmt.setString(1, lastName);

            myRs = myStmt.executeQuery();

            while (myRs.next()) {
                Customer tempCustomer = convertRowToCustomer(myRs);
                list.add(tempCustomer);
            }

            return list;
        }
        finally {
            close(myStmt, myRs);
        }
    }

    private Customer convertRowToCustomer(ResultSet myRs) throws SQLException {

        int id = myRs.getInt("id");
        String name = myRs.getString("name");
        String address  = myRs.getString("address");
        String phoneNo = myRs.getString("phoneno");

        Customer tempCustomer = new Customer(id, name, address, phoneNo);

        return tempCustomer;
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

        CustomerDao dao = new CustomerDao();
        System.out.println(dao.searchCustomer("1"));

        System.out.println(dao.getAllCustomer());
    }
}
