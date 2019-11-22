package labib;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Product_page extends Component {

    static JPanel p2=new JPanel();
    JButton search;
    JTable table;
    JLabel lbl1,searchLebel;
    JTextField textSearch;

    private StockDao stockDao;
    Product_page(){}
    public void removepan(){
        p2.setVisible(false);
    }

    Product_page(JPanel p1)
    {
        try {
            stockDao = new StockDao();
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
        }

        p1.setVisible(false);
        p1.setVisible(true);


        p2.setBounds(325,0,2000,2000);
        p2.setBackground(Color.RED);
        p2.setLayout(null);



        table=new JTable();

//        Object[] columns = {"Product Id","Product Name","Quantity","Unit Per Price"};
//        DefaultTableModel model = new DefaultTableModel();
//        model.setColumnIdentifiers(columns);
//        table.setModel(model);
//        table.setBackground(Color.darkGray);
//        table.setForeground(Color.orange);
//        table.setFont(new Font("",1,22));
//        table.setRowHeight(30);


        lbl1=new JLabel("Product Information");
        lbl1.setBounds(450,10,400,30);
        lbl1.setFont(new Font("Times New Roman", Font.BOLD + Font.PLAIN, 30));
        lbl1.setForeground(Color.orange);


        searchLebel = new JLabel("Search Product");


        searchLebel.setBounds(460,500,150,30);

        searchLebel.setFont(new Font("Times New Roman",Font.BOLD,18));



        searchLebel.setForeground(Color.ORANGE);


        textSearch = new JTextField();


        textSearch.setBounds(340,550,350,30);

        textSearch.setFont(new Font("Times New Roman",Font.BOLD,18));




        search = new JButton("Search");
        search.setBounds(480,600,80,30);

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(10, 50, 1130, 400);
        pane.setViewportView(table);


        p2.add(lbl1);
        p2.add(pane);

        p2.add(textSearch);
        p2.add(search);
        p2.add(searchLebel);
        p2.setVisible(true);
        p1.add(p2);




        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String searchText = textSearch.getText();

                    ArrayList<Stock> productList = null;

                    if ((searchText != null) && (searchText.trim().length() > 0)) {
                        productList = (ArrayList<Stock>) stockDao.searchStock(searchText);
                    } else {
                        productList = (ArrayList<Stock>) stockDao.getAllStock();
                    }

                    // create the model and update the "table"
                    ProductTableModel model = new ProductTableModel(productList);

                    table.setModel(model);

					/*
					for (Employee temp : employees) {
						System.out.println(temp);
					}
					*/
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(Product_page.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });


    }
}
