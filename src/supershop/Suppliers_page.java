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

public class Suppliers_page extends Component {

    static JPanel p3=new JPanel();
    JButton add,edit,remove,search;
    JTable table;
    JLabel lbl1,id,name,address,phoneNo,searchLebel;
    JTextField textId,textName,textAddress,textPhoneNo,textSearch;

    private SupplierDao supplierDao;

    public Suppliers_page() throws Exception {}

    public void removepan(){
        p3.setVisible(false);
    }


    Suppliers_page(JPanel p1) throws Exception {

        try {
            supplierDao = new SupplierDao();
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
        }
        p1.setVisible(false);
        p1.setVisible(true);


        p3.setBounds(325,0,2000,2000);
        p3.setBackground(Color.RED);
        p3.setLayout(null);



        table=new JTable();

//        Object[] columns = {"Supplier Id","Supplier Name","Address","Phone Number"};
//        DefaultTableModel model = new DefaultTableModel();
//        model.setColumnIdentifiers(columns);
//        table.setModel(model);
//        table.setBackground(Color.white);
//        table.setForeground(Color.orange);
//        table.setFont(new Font("",1,22));
//        table.setRowHeight(30);


        lbl1=new JLabel("Suppliers Information");
        lbl1.setBounds(450,10,400,30);
        lbl1.setFont(new Font("Times New Roman", Font.BOLD + Font.PLAIN, 30));
        lbl1.setForeground(Color.orange);

        id = new JLabel("Id");
        name = new JLabel("Name");
        address = new JLabel("Address");
        phoneNo = new JLabel("Phone Number");
        searchLebel = new JLabel("Search Supplier");

        id.setBounds(80,500,50,30);
        name.setBounds(80,540,50,30);
        address.setBounds(80,580,80,30);
        phoneNo.setBounds(80,620,150,30);
        searchLebel.setBounds(750,500,150,30);
        id.setFont(new Font("Times New Roman",Font.BOLD,18));
        name.setFont(new Font("Times New Roman",Font.BOLD,18));
        address.setFont(new Font("Times New Roman",Font.BOLD,18));
        phoneNo.setFont(new Font("Times New Roman",Font.BOLD,18));
        searchLebel.setFont(new Font("Times New Roman",Font.BOLD,18));


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

        textId.setBounds(210,500,400,30);
        textName.setBounds(210,540,400,30);
        textAddress.setBounds(210,580,400,30);
        textPhoneNo.setBounds(210,620,400,30);
        textSearch.setBounds(750,550,300,30);

        textId.setFont(new Font("Times New Roman",Font.BOLD,18));
        textName.setFont(new Font("Times New Roman",Font.BOLD,18));
        textAddress.setFont(new Font("Times New Roman",Font.BOLD,18));
        textPhoneNo.setFont(new Font("Times New Roman",Font.BOLD,18));
        textSearch.setFont(new Font("Times New Roman",Font.BOLD,18));



        add=new JButton("Add");
        add.setBounds(630,530,60,30);
        edit=new JButton("Edit");
        edit.setBounds(630,570,60,30);
        remove=new JButton("Remove");
        remove.setBounds(630,610,80,30);
        search = new JButton("Search");
        search.setBounds(750,600,80,30);

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(10, 50, 1130, 400);
        pane.setViewportView(table);

        p3.add(add);
        p3.add(edit);
        p3.add(remove);
        p3.add(lbl1);
        p3.add(pane);
        p3.add(id);
        p3.add(name);
        p3.add(address);
        p3.add(phoneNo);
        p3.add(textId);
        p3.add(textName);
        p3.add(textAddress);
        p3.add(textPhoneNo);
        p3.add(textSearch);
        p3.add(search);
        p3.add(searchLebel);
        p3.setVisible(true);
        p1.add(p3);



        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveSupplier();
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
                        JOptionPane.showMessageDialog(Suppliers_page.this,
                                "You must select an employee", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // prompt the user
                    int response = JOptionPane.showConfirmDialog(
                            Suppliers_page.this, "Delete this employee?", "Confirm",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                    if (response != JOptionPane.YES_OPTION) {
                        return;
                    }

                    // get the current employee
                    Supplier tempSupplier = (Supplier) table.getValueAt(row, SupplierTableModel.OBJECT_COL);

                    // delete the employee
                    supplierDao.deleteSupplier(tempSupplier.getId());

                    // refresh GUI
                    refreshSupplierView();

                    // show success message
                    textId.setText(null);
                    textName.setText(null);
                    textAddress.setText(null);
                    textPhoneNo.setText(null);
                    JOptionPane.showMessageDialog(Suppliers_page.this,
                            "Supplier deleted succesfully.", "Supplier Deleted",
                            JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(Suppliers_page.this,
                            "Error deleting Supplier: " + exc.getMessage(), "Error",
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
                    SupplierTableModel model = (SupplierTableModel) table.getModel();
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
                editProduct();
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

                    ArrayList<Supplier> supplierList = null;

                    if ((searchText != null) && (searchText.trim().length() > 0)) {
                        supplierList = (ArrayList<Supplier>) supplierDao.searchSupplier(searchText);
                    } else {
                        supplierList = (ArrayList<Supplier>) supplierDao.getAllSupplier();
                    }

                    // create the model and update the "table"
                    SupplierTableModel model = new SupplierTableModel(supplierList);

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
                    JOptionPane.showMessageDialog(Suppliers_page.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }

    protected void saveSupplier() {

        // get the employee info from gui
        String  id = textId.getText();
        String name = textName.getText();
        String address = textAddress.getText();
        String phoneNo = textPhoneNo.getText();
        int idVar = Integer.parseInt(id);

        Supplier tempSupplier = new Supplier(idVar, name, address, phoneNo);

        try {
            // save to the database
            supplierDao.addSupplier(tempSupplier);

            // close dialog
            setVisible(false);

            // refresh gui list
            refreshSupplierView();

            // show success message

            textId.setText(null);
            textName.setText(null);
            textAddress.setText(null);
            textPhoneNo.setText(null);
            JOptionPane.showMessageDialog(this,
                    "Supplier added succesfully.",
                    "Supplier Added",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error saving Supplier: "
                            + exc.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    protected void editProduct(){
        int row = table.getSelectedRow();
        String  id = textId.getText();
        String name = textName.getText();
        String address = textAddress.getText();
        String phoneNo = textPhoneNo.getText();
        int idVar = Integer.parseInt(id);

        Supplier tempSupplier = (Supplier) table.getValueAt(row,SupplierTableModel.OBJECT_COL);
        tempSupplier.setId(idVar);
        tempSupplier.setName(name);
        tempSupplier.setAddress(address);
        tempSupplier.setPhoneNo(phoneNo);

        try {
            // save to the database
            supplierDao.updateSupplier(tempSupplier);

            // close dialog
            setVisible(false);

            // refresh gui list
            refreshSupplierView();

            // show success message
            JOptionPane.showMessageDialog(this,
                    "Product added succesfully.",
                    "Employee Added",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error saving employee: "
                            + exc.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refreshSupplierView() {
        try {
            List<Supplier> suppliers = supplierDao.getAllSupplier();

            // create the model and update the "table"
            SupplierTableModel model = new SupplierTableModel(suppliers);

            table.setModel(model);
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(this, "Error: " + exc, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
