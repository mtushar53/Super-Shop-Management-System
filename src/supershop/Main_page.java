package labib;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main_page {

    static JPanel p1,p2,p3,p4,p5;
    JButton home,bill,cashier,customar,product,supplier,stock;
    JLabel lb1,lb2;
    Product_page product_page;
    Suppliers_page suppliers_page;

    JLabel clock,date;
    int fsupp=0,fpro=0,fcash=0,fcust=0,fbill=0,fstock=0;
    static int day,month,year,minute,hour;
    public Main_page()
    {}


    public static void setClock()
    {
        Calendar cal=new GregorianCalendar();
        day= cal.get(Calendar.DAY_OF_MONTH);
        month=1+ cal.get(Calendar.MONTH);
        year= cal.get(Calendar.YEAR);

//        second=cal.get(Calendar.SECOND);
        minute= cal.get(Calendar.MINUTE);
        hour= cal.get(Calendar.HOUR);
        p1.setVisible(false);
        p1.setVisible(true);
    }
    public Main_page(FrameOne f2)
    {






        ImageIcon image1,image2,image3,image4,image5,image6,image7,image8;
        p1=new JPanel();
        p1.setLayout(new BorderLayout());
        p1.setBackground(Color.BLACK);
        p2=new JPanel();
        p2.setBounds(500,100,1200,500);
        p2.setBackground(Color.red);
        p2.setLayout(null);
        p1.add(p2,BorderLayout.CENTER);
        p5 = new JPanel();
        p5.setLayout(new GridLayout(7,1));
        p1.add(p5,BorderLayout.WEST);
        //image set
        image1=new ImageIcon(getClass().getResource("pic.jpg"));
        image2=new ImageIcon(getClass().getResource("home_1.png"));
        image3=new ImageIcon(getClass().getResource("product.png"));
        image4=new ImageIcon(getClass().getResource("supplier.png"));
        image5=new ImageIcon(getClass().getResource("customer.png"));
        image6=new ImageIcon(getClass().getResource("cashier.png"));
        image7=new ImageIcon(getClass().getResource("bill.png"));
        image8=new ImageIcon(getClass().getResource("stock.png"));
        setClock();
        clock=new JLabel(hour+ " : "+minute);
        clock.setBounds(410,640,300,40);
        clock.setFont(new Font("Times New Roman", Font.BOLD + Font.PLAIN, 30));
        clock.setForeground(Color.BLACK);
        date=new JLabel("Date : "+day+"/"+month+"/"+year);
        date.setBounds(350,580,300,40);
        date.setFont(new Font("Times New Roman", Font.BOLD + Font.PLAIN, 30));
        date.setForeground(Color.BLACK);

        lb1=new JLabel(image1);
        lb1.setBounds(150,100,750,500);
        lb2 = new JLabel();
        lb2.setBounds(250,30,700,70);
        lb2.setForeground(Color.orange);
        lb2.setText("Welcome To Our Management System");
        lb2.setFont(new Font("Times New Roman",Font.BOLD + Font.ITALIC,38));

        //button set
        home=new JButton(image2);
        //home.setBounds(50,20,250,80);
        home.setText("Home");
        home.setForeground(Color.red);
        home.setFont(new Font("Times New Roman", Font.BOLD + Font.ITALIC, 14));
        product=new JButton(image3);
        //product.setBounds(50,120,250,80);
        product.setText("Product");
        product.setForeground(Color.red);
        product.setFont(new Font("Times New Roman", Font.BOLD + Font.ITALIC, 14));
        supplier=new JButton(image4);
        //supplier.setBounds(50,240,250,80);
        supplier.setText("Supplier");
        supplier.setForeground(Color.red);
        supplier.setFont(new Font("Times New Roman", Font.BOLD + Font.ITALIC, 14));
        customar=new JButton(image5);
        //customar.setBounds(50,360,250,80);
        customar.setText("Customer");
        customar.setForeground(Color.red);
        customar.setFont(new Font("Times New Roman", Font.BOLD + Font.ITALIC, 14));
        cashier=new JButton(image6);
        // cashier.setBounds(50,480,250,80);
        cashier.setText("Cashier");
        cashier.setForeground(Color.red);
        cashier.setFont(new Font("Times New Roman", Font.BOLD + Font.ITALIC, 14));

        bill=new JButton(image7);
        //bill.setBounds(50,600,250,80);
        bill.setText("Bill");
        bill.setForeground(Color.red);
        bill.setFont(new Font("Times New Roman", Font.BOLD + Font.ITALIC, 14));

        stock=new JButton(image8);
        //stock.setBounds(50,720,100,80);
        stock.setText("Stock");
        stock.setForeground(Color.red);
        stock.setFont(new Font("Times New Roman", Font.BOLD + Font.ITALIC, 14));

        //adding

        p5.add(home);
        p2.add(lb1,BorderLayout.CENTER);
        p2.add(lb2,BorderLayout.NORTH);
        p5.add(product);
        p5.add(supplier);
        p5.add(customar);
        p5.add(cashier);
        p5.add(bill);
        p5.add(stock);
        p2.add(date);
        //p2.add(clock);
        p3=new JPanel();
        p4=new JPanel();
        p3.setBackground(Color.orange);
        p4.setBackground(Color.orange);
        f2.add(p1,BorderLayout.CENTER);
        f2.add(p3,BorderLayout.NORTH);
        f2.add(p4,BorderLayout.SOUTH);
        f2.setVisible(true);
        p1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("x :"+e.getX()+" Y :  "+e.getY());
            }
        });
        product.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setClock();
                Object obj=e.getSource();
                if(obj==product)
                {
                    p2.setVisible(false);


                    if(fsupp==1)
                    {
                        Suppliers_page sg= null;
                        try {
                            sg = new Suppliers_page();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        sg.removepan();
                    }

                    if(fcash==1)
                    {
                        Cashiar_page ccp=new Cashiar_page();
                        ccp.removepan();
                    }
                    if(fstock==1)
                    {
                        Stock_page stp= new Stock_page();
                        stp.removepan();
                    }
                    if(fbill==1)
                    {
                        Bill_page bg=new Bill_page();
                        bg.removepan();
                    }
                    if(fcust==1)
                    {
                        Customer_page cp= new Customer_page();
                        cp.removepan();
                    }
                    p1.setVisible(false);
                    p1.setVisible(true);
                    fpro=1;
                    product_page=new Product_page(p1);
                }

            }
        });
        cashier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object obj=e.getSource();
                setClock();
                if(obj==cashier)
                {
                    p2.setVisible(false);
                    if(fpro==1)
                    {
                        Product_page pg= new Product_page();
                        pg.removepan();
                    }
                    if(fsupp==1)
                    {
                        Suppliers_page sg= null;
                        try {
                            sg = new Suppliers_page();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        sg.removepan();
                    }
                    if(fstock==1)
                    {
                        Stock_page stp= new Stock_page();
                        stp.removepan();
                    }
                    if(fbill==1)
                    {
                        Bill_page bg=new Bill_page();
                        bg.removepan();
                    }
                    if(fcust==1)
                    {
                        Customer_page cp= new Customer_page();
                        cp.removepan();
                    }
//                    fsupp=0,fpro=0,fcash=0,fcust=0,fbill=0,fstock=0
                    p1.setVisible(false);
                    p1.setVisible(true);
                    fcash=1;

                    Cashiar_page cp=new Cashiar_page(p1);
                }

            }
        });
        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object obj=e.getSource();
                setClock();
                if(obj==home)
                {
                    if(fpro==1)
                    {
                        Product_page pg= new Product_page();
                        pg.removepan();
                    }
                    if(fsupp==1)
                    {
                        Suppliers_page sg= null;
                        try {
                            sg = new Suppliers_page();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        sg.removepan();
                    }
                    if(fcash==1)
                    {
                        Cashiar_page ccp=new Cashiar_page();
                        ccp.removepan();
                    }
                    if(fstock==1)
                    {
                        Stock_page stp= new Stock_page();
                        stp.removepan();
                    }
                    if(fbill==1)
                    {
                        Bill_page bg=new Bill_page();
                        bg.removepan();
                    }
                    if(fcust==1)
                    {
                        Customer_page cp= new Customer_page();
                        cp.removepan();
                    }
                    fsupp=0;
                    fpro=0;
                    fcash=0;
                    fcust=0;
                    fbill=0;
                    fstock=0;
                    p1.setVisible(false);
                    p1.setVisible(true);
                    p2.setVisible(false);
                    p2.setVisible(true);
                }
            }
        });
        bill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object obj=e.getSource();
                setClock();
                if(obj==bill)
                {
                    p2.setVisible(false);
                    if(fpro==1)
                    {
                        Product_page pg= new Product_page();
                        pg.removepan();
                    }

                    if(fcash==1)
                    {
                        Cashiar_page ccp=new Cashiar_page();
                        ccp.removepan();
                    }
                    if(fstock==1)
                    {
                        Stock_page stp= new Stock_page();
                        stp.removepan();
                    }
                    if(fsupp==1)
                    {
                        Suppliers_page sg= null;
                        try {
                            sg = new Suppliers_page();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        sg.removepan();
                    }
                    if(fcust==1)
                    {
                        Customer_page cp= new Customer_page();
                        cp.removepan();
                    }

                    p1.setVisible(false);
                    p1.setVisible(true);
                    fbill=1;

                    Bill_page bg=new Bill_page(p1);
                }
            }
        });
        supplier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object obj = e.getSource();
                setClock();
                if(obj==supplier){
                    p2.setVisible(false);
                    if(fpro==1)
                    {
                        Product_page pg= new Product_page();
                        pg.removepan();
                    }

                    if(fcash==1)
                    {
                        Cashiar_page ccp=new Cashiar_page();
                        ccp.removepan();

                    }
                    if(fstock==1)
                    {
                        Stock_page stp= new Stock_page();
                        stp.removepan();
                    }
                    if(fbill==1)
                    {
                        Bill_page bg=new Bill_page();
                        bg.removepan();
                    }
                    if(fcust==1)
                    {
                        Customer_page cp= new Customer_page();
                        cp.removepan();
                    }

                    p1.setVisible(false);
                    p1.setVisible(true);
                    fsupp=1;

                    try {
                        suppliers_page=new Suppliers_page(p1);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        customar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object obj = e.getSource();
                setClock();
                if(obj==customar){
                    p2.setVisible(false);
                    if(fpro==1)
                    {
                        Product_page pg= new Product_page();
                        pg.removepan();
                    }
                    if(fsupp==1)
                    {
                        Suppliers_page sg= null;
                        try {
                            sg = new Suppliers_page();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        sg.removepan();
                    }
                    if(fcash==1)
                    {
                        Cashiar_page ccp=new Cashiar_page();
                        ccp.removepan();
                    }
                    if(fstock==1)
                    {
                        Stock_page stp= new Stock_page();
                        stp.removepan();
                    }
                    if(fbill==1)
                    {
                        Bill_page bg=new Bill_page();
                        bg.removepan();
                    }


                    p1.setVisible(false);
                    p1.setVisible(true);
                    fcust=1;
                    new Customer_page(p1);
                }
            }
        });

        stock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object obj = e.getSource();
                setClock();
                if(obj==stock){
                    p2.setVisible(false);
                    if(fpro==1)
                    {
                        Product_page pg= new Product_page();
                        pg.removepan();
                    }
                    if(fsupp==1)
                    {
                        Suppliers_page sg= null;
                        try {
                            sg = new Suppliers_page();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        sg.removepan();
                    }
                    if(fcash==1)
                    {
                        Cashiar_page ccp=new Cashiar_page();
                        ccp.removepan();
                    }

                    if(fbill==1)
                    {
                        Bill_page bg=new Bill_page();
                        bg.removepan();
                    }
                    if(fcust==1)
                    {
                        Customer_page cp= new Customer_page();
                        cp.removepan();
                    }
                    p1.setVisible(false);
                    p1.setVisible(true);
                    fstock=1;
                    new Stock_page(p1);
                }
            }
        });
    }
}
