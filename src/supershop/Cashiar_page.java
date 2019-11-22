package labib;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Cashiar_page {

    static JPanel p3=new JPanel();
    static JTable table1,table2;
    JLabel totalsell,totalbuy,total,label1,selling_info,buying_info;

    double total_sell=0,total_buy=0,t_total=0;


    public Cashiar_page() {}

    public void removepan(){
        p3.setVisible(false);
    }

    static DefaultTableModel model;

    Cashiar_page(JPanel p1)
    {
        p1.setVisible(false);
        p1.setVisible(true);

        p3.setBounds(325,0,2000,2000);
        p3.setBackground(Color.RED);
        p3.setLayout(null);
        table1=new JTable();
        Object[] columns = {"Bill No","Customer Name","Total bill"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        table1.setModel(model);
        table1.setBackground(Color.WHITE);
        table1.setForeground(Color.orange);
        table1.setFont(new Font("",1,22));
        table1.setRowHeight(30);

        JScrollPane pane = new JScrollPane(table1);
        pane.setBounds(10, 80, 1130, 250);
        pane.setViewportView(table1);

        table2=new JTable();
        Object[] columns2 = {"Supplier Id","Product Name","Product Unit","Total Cost"};
        DefaultTableModel mode2 = new DefaultTableModel();
        mode2.setColumnIdentifiers(columns2);
        table2.setModel(mode2);
        table2.setBackground(Color.darkGray);
        table2.setForeground(Color.orange);
        table2.setFont(new Font("",1,22));
        table2.setRowHeight(30);

        JScrollPane pane2 = new JScrollPane(table2);
        pane2.setBounds(10, 375, 1130, 250);
        pane2.setViewportView(table2);

        selling_info=new JLabel("Selling Information");
        selling_info.setBounds(450,45,400,30);
        selling_info.setFont(new Font("Times New Roman", Font.BOLD + Font.PLAIN, 20));
        selling_info.setForeground(Color.orange);
        label1=new JLabel("Cashiar Page");
        label1.setBounds(450,10,400,30);
        label1.setFont(new Font("Times New Roman", Font.BOLD + Font.PLAIN, 30));
        label1.setForeground(Color.orange);
        totalsell=new JLabel("Total Sell = "+total_sell);
        totalsell.setBounds(650,45,400,30);
        totalsell.setFont(new Font("Times New Roman", Font.BOLD + Font.PLAIN, 20));
        totalsell.setForeground(Color.orange);
        buying_info=new JLabel("Buying Information");
        buying_info.setBounds(450,340,400,30);
        buying_info.setFont(new Font("Times New Roman", Font.BOLD + Font.PLAIN, 20));
        buying_info.setForeground(Color.orange);
        totalbuy=new JLabel("Total Buy = "+total_buy);
        totalbuy.setBounds(650,340,400,30);
        totalbuy.setFont(new Font("Times New Roman", Font.BOLD + Font.PLAIN, 20));
        totalbuy.setForeground(Color.orange);
        total=new JLabel("Total Trading = "+t_total);
        total.setBounds(650,650,400,30);
        total.setFont(new Font("Times New Roman", Font.BOLD + Font.PLAIN, 20));
        total.setForeground(Color.orange);

        p3.add(buying_info);
        p3.add(selling_info);
        p3.add(pane);
        p3.add(pane2);
        p3.add(total);
        p3.add(label1);
        p3.add(totalsell);
        p3.add(totalbuy);
        p3.setVisible(true);
        p1.add(p3);


    }

    public static DefaultTableModel getModel(){
        return model;
    }

}
