package labib;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Bill_page extends Component {

    static JPanel p3=new JPanel();
    JButton add,edit,remove,genarate;
    JTable table;
    JLabel lbl1,id,quantity,customer,total,totalValue;
    JTextField textId,textQuantity,textcustomer;
    Object[] sellRow = new Object[3];


    private StockDao stockDao;
    int billNo=0;

    public Bill_page() {}

    public void removepan(){
        p3.setVisible(false);
    }


    Bill_page(JPanel p1)
    {
        try {
            stockDao = new StockDao();
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
        }

        p1.setVisible(false);
        p1.setVisible(true);


        p3.setBounds(325,0,2000,2000);
        p3.setBackground(Color.RED);
        p3.setLayout(null);



        table=new JTable();

        Object[] columns = {"Product Id","Product Name","Product Quantity","Price per unit","Total price"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        table.setFont(new Font("",1,18));
        table.setRowHeight(25);


        lbl1=new JLabel("Bill Information");
        lbl1.setBounds(450,10,400,30);
        lbl1.setFont(new Font("Times New Roman", Font.BOLD + Font.PLAIN, 30));
        lbl1.setForeground(Color.orange);

        id = new JLabel("Product Id");
        quantity = new JLabel("Quantity");
        customer=new JLabel("Customer Name");
        total = new JLabel("Total Bill : ");
        totalValue = new JLabel("0.0");
        id.setBounds(70,500,100,30);
        quantity.setBounds(80,540,80,30);
        customer.setBounds(60,580,150,30);
        total.setBounds(800,460,150,30);
        totalValue.setBounds(960,460,100,30);
        id.setFont(new Font("Times New Roman",Font.BOLD,18));
        customer.setFont(new Font("Times New Roman",Font.BOLD,18));
        quantity.setFont(new Font("Times New Roman",Font.BOLD,18));
        total.setFont(new Font("Times New Roman",Font.BOLD,22));
        totalValue.setFont(new Font("Times New Roman",Font.BOLD,22));



        id.setForeground(Color.ORANGE);
        customer.setForeground(Color.ORANGE);
        quantity.setForeground(Color.ORANGE);
        total.setForeground(Color.ORANGE);
        totalValue.setForeground(Color.ORANGE);


        textId = new JTextField();
        textQuantity = new JTextField();
        textcustomer=new JTextField();
        textId.setBounds(210,500,400,30);
        textQuantity.setBounds(210,540,400,30);
        textcustomer.setBounds(210,580,400,30);

        textId.setFont(new Font("Times New Roman",Font.BOLD,18));
        textcustomer.setFont(new Font("Times New Roman",Font.BOLD,18));
        textQuantity.setFont(new Font("Times New Roman",Font.BOLD,18));




        add=new JButton("Add");
        add.setBounds(630,500,60,30);
        edit=new JButton("Edit");
        edit.setBounds(630,540,60,30);
        remove=new JButton("Remove");
        remove.setBounds(630,580,80,30);
        genarate=new JButton("Generate Bill");
        genarate.setBounds(800,540,150,30);
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(10, 50, 1130, 400);

        p3.add(add);
        p3.add(edit);
        p3.add(remove);
        p3.add(genarate);
        p3.add(lbl1);
        p3.add(pane);
        p3.add(id);
        p3.add(quantity);
        p3.add(customer);
        p3.add(textId);
        p3.add(textcustomer);
        p3.add(textQuantity);
        p3.add(total);
        p3.add(totalValue);
        p3.setVisible(true);
        p1.add(p3);



        Object[] row = new Object[5];
        add.addActionListener(new ActionListener() {
            double sum;

            @Override

            public void actionPerformed(ActionEvent e) {
                Object obj = e.getSource();

                if(obj == add){
                    String id = textId.getText();
                    double price = stockDao.valueAtId(id).getPrice();
                    int quantity = Integer.parseInt(textQuantity.getText());
                    double totalVal=0;
                    row[0] = id;
                    row[1] = stockDao.valueAtId(id).getName();
                    row[2] = textQuantity.getText();
                    row[3] = stockDao.valueAtId(id).getPrice();
                    sum= price*quantity;
                    row[4] = sum;

                    model.addRow(row);
                    totalVal= sum +Double.parseDouble(totalValue.getText());

                    totalValue.setText(String.valueOf(totalVal));


                    textId.setText(null);
                    textQuantity.setText(null);




                    p3.setVisible(false);
                    p3.setVisible(true);


                }
            }
        });



        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = table.getSelectedRow();

                if(i>=0){
                    model.removeRow(i);
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Remove Error");
                }
            }
        });


        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i = table.getSelectedRow();

                textId.setText(model.getValueAt(i,0).toString());

                textQuantity.setText(model.getValueAt(i,2).toString());


            }
        });
        genarate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object obj= e.getSource();
                if(obj==genarate)
                {
                    sellRow[0] = billNo++;
                    sellRow[1] = textcustomer.getText();
                    sellRow[2] = totalValue.getText();

                    //Cashiar_page.getModel().addRow(sellRow);
                    textcustomer.setText(null);
                    table.setModel(new DefaultTableModel(null,new String[]{"Product Id","Product Name","Product Quantity","Price per unit","Total price"}));
                    totalValue.setText("0.0");
                }
            }
        });
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = table.getSelectedRow();

                if(i>=0){
                    model.setValueAt(textId.getText(),i,0);

                    model.setValueAt(textQuantity.getText(),i,2);

                }
                else{
                    JOptionPane.showMessageDialog(null,"Edit error");
                }


            }
        });



    }
}
