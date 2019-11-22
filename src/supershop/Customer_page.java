package labib;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Customer_page extends Component {
    static JPanel p4=new JPanel();
    JButton add,edit,remove,search;
    JTable table;
    JLabel lbl1,id,name,address,phoneNo,searchLebel;
    JTextField textId,textName,textAddress,textPhoneNo,textSearch;

    private CustomerDao customerDao;
    Customer_page(){}

    public void removepan(){
        p4.setVisible(false);
    }

    Customer_page(JPanel p1) {

        try {
            customerDao = new CustomerDao();
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
        }

        p4.setBounds(325, 0, 2000, 2000);
        p4.setBackground(Color.RED);
        p4.setLayout(null);


        table = new JTable();

//        Object[] columns = {"Customer Id", "Customer Name", "Address", "Phone Number"};
//        DefaultTableModel model = new DefaultTableModel();
//        model.setColumnIdentifiers(columns);
//        table.setModel(model);
//        table.setBackground(Color.white);
//        table.setForeground(Color.orange);
//        table.setFont(new Font("", 1, 22));
//        table.setRowHeight(30);


        lbl1 = new JLabel("Customer Information");
        lbl1.setBounds(450, 10, 400, 30);
        lbl1.setFont(new Font("Times New Roman", Font.BOLD + Font.PLAIN, 30));
        lbl1.setForeground(Color.orange);

        id = new JLabel("Id");
        name = new JLabel("Name");
        address = new JLabel("Address");
        phoneNo = new JLabel("Phone Number");
        searchLebel = new JLabel("Search Customer");

        id.setBounds(80, 500, 50, 30);
        name.setBounds(80, 540, 50, 30);
        address.setBounds(80, 580, 80, 30);
        phoneNo.setBounds(80, 620, 150, 30);
        searchLebel.setBounds(750, 500, 150, 30);
        id.setFont(new Font("Times New Roman", Font.BOLD, 18));
        name.setFont(new Font("Times New Roman", Font.BOLD, 18));
        address.setFont(new Font("Times New Roman", Font.BOLD, 18));
        phoneNo.setFont(new Font("Times New Roman", Font.BOLD, 18));
        searchLebel.setFont(new Font("Times New Roman", Font.BOLD, 18));


        id.setForeground(Color.ORANGE);
        name.setForeground(Color.ORANGE);
        address.setForeground(Color.ORANGE);
        phoneNo.setForeground(Color.ORANGE);
        searchLebel.setForeground(Color.ORANGE);

        textId = new JTextField();
        textName = new JTextField();
        textAddress = new JTextField();
        textPhoneNo = new JTextField();
        textSearch = new JTextField();

        textId.setBounds(210, 500, 400, 30);
        textName.setBounds(210, 540, 400, 30);
        textAddress.setBounds(210, 580, 400, 30);
        textPhoneNo.setBounds(210, 620, 400, 30);
        textSearch.setBounds(750, 550, 300, 30);

        textId.setFont(new Font("Times New Roman", Font.BOLD, 18));
        textName.setFont(new Font("Times New Roman", Font.BOLD, 18));
        textAddress.setFont(new Font("Times New Roman", Font.BOLD, 18));
        textPhoneNo.setFont(new Font("Times New Roman", Font.BOLD, 18));
        textSearch.setFont(new Font("Times New Roman", Font.BOLD, 18));


        add = new JButton("Add");
        add.setBounds(630, 530, 60, 30);
        edit = new JButton("Edit");
        edit.setBounds(630, 570, 60, 30);
        remove = new JButton("Remove");
        remove.setBounds(630, 610, 80, 30);
        search = new JButton("Search");
        search.setBounds(750, 600, 80, 30);

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(10, 50, 1130, 400);
        pane.setViewportView(table);

        p4.add(add);
        p4.add(edit);
        p4.add(remove);
        p4.add(lbl1);
        p4.add(pane);
        p4.add(id);
        p4.add(name);
        p4.add(address);
        p4.add(phoneNo);
        p4.add(textId);
        p4.add(textName);
        p4.add(textAddress);
        p4.add(textPhoneNo);
        p4.add(textSearch);
        p4.add(search);
        p4.add(searchLebel);
        p4.setVisible(true);
        p1.add(p4);


        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveCustomer();
            }
        });


        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // get the selected row
                    int row = table.getSelectedRow();

                    // make sure a row is selected
                    if (row < 0) {
                        JOptionPane.showMessageDialog(Customer_page.this,
                                "You must select an employee", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // prompt the user
                    int response = JOptionPane.showConfirmDialog(
                            Customer_page.this, "Delete this Customer?", "Confirm",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                    if (response != JOptionPane.YES_OPTION) {
                        return;
                    }

                    // get the current employee
                    Customer tempCustomer = (Customer) table.getValueAt(row, CustomerTableModel.OBJECT_COL);

                    // delete the employee
                    customerDao.deleteCustomer(tempCustomer.getId());

                    // refresh GUI
                    refreshCustomerView();

                    // show success message
                    textId.setText(null);
                    textName.setText(null);
                    textAddress.setText(null);
                    textPhoneNo.setText(null);
                    JOptionPane.showMessageDialog(Customer_page.this,
                            "Customer deleted succesfully.", "Customer Deleted",
                            JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(Customer_page.this,
                            "Error deleting Customer: " + exc.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }


            }
        });


        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    CustomerTableModel model = (CustomerTableModel) table.getModel();
                    int i = table.getSelectedRow();

                    textId.setText(model.getValueAt(i,0).toString());
                    textName.setText(model.getValueAt(i,1).toString());
                    textAddress.setText(model.getValueAt(i,2).toString());
                    textPhoneNo.setText(model.getValueAt(i,3).toString());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }


            }
        });

        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editCustomer();
//               int row = table.getSelectedRow();
//
//               // make sure a row is selected
//               if (row < 0) {
//                   JOptionPane.showMessageDialog(Product_page.this, "You must select an employee", "Error",
//                           JOptionPane.ERROR_MESSAGE);
//                   return;
//               }
//
//               // get the current employee
//               Product tempProduct = (Product) table.getValueAt(row, ProductTableModel.OBJECT_COL);
//
//               try {
//                   productDao.updateProduct(tempProduct);
//                   refreshProductView();
//               } catch (SQLException e1) {
//                   e1.printStackTrace();
//               }
            }
        });

        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String searchText = textSearch.getText();

                    ArrayList<Customer> customerList = null;

                    if ((searchText != null) && (searchText.trim().length() > 0)) {
                        customerList = (ArrayList<Customer>) customerDao.searchCustomer(searchText);
                    } else {
                        customerList = (ArrayList<Customer>) customerDao.getAllCustomer();
                    }

                    // create the model and update the "table"
                    CustomerTableModel model = new CustomerTableModel(customerList);

                    table.setModel(model);
                    textId.setText(null);
                    textName.setText(null);
                    textAddress.setText(null);
                    textPhoneNo.setText(null);

					/*
					for (Employee temp : employees) {
						System.out.println(temp);
					}
					*/
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(Customer_page.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }

    protected void saveCustomer() {

        // get the employee info from gui
        String  id = textId.getText();
        String name = textName.getText();
        String address = textAddress.getText();
        String phoneNo = textPhoneNo.getText();
        int idVar = Integer.parseInt(id);

        Customer tempCustomer = new Customer(idVar, name, address, phoneNo);

        try {
            // save to the database
            customerDao.addCustomer(tempCustomer);

            // close dialog
            setVisible(false);

            // refresh gui list
            refreshCustomerView();

            // show success message

            textId.setText(null);
            textName.setText(null);
            textAddress.setText(null);
            textPhoneNo.setText(null);
            JOptionPane.showMessageDialog(this,
                    "Customer added succesfully.",
                    "Customer Added",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error saving Customer: "
                            + exc.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    protected void editCustomer(){
        int row = table.getSelectedRow();
        String  id = textId.getText();
        String name = textName.getText();
        String address = textAddress.getText();
        String phoneNo = textPhoneNo.getText();
        int idVar = Integer.parseInt(id);

        Customer tempCustomer = (Customer) table.getValueAt(row,SupplierTableModel.OBJECT_COL);
        tempCustomer.setId(idVar);
        tempCustomer.setName(name);
        tempCustomer.setAddress(address);
        tempCustomer.setPhoneNo(phoneNo);

        try {
            // save to the database
            customerDao.updateCustomer(tempCustomer);

            // close dialog
            setVisible(false);

            // refresh gui list
            refreshCustomerView();

            // show success message
            JOptionPane.showMessageDialog(this,
                    "Customer added succesfully.",
                    "Customer Added",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error saving Customer: "
                            + exc.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refreshCustomerView() {
        try {
            List<Customer> customers = customerDao.getAllCustomer();

            // create the model and update the "table"
            CustomerTableModel model = new CustomerTableModel(customers);

            table.setModel(model);
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(this, "Error: " + exc, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
