package labib;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


class FrameOne extends JFrame{

    public FrameOne(int x,int y)
    {
        new JFrame("Super Manager");
        setSize(y,x);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


    }

}


public class Log_in_page {


    JPanel p1,p2,p3,p4;
    JTextField tf1;
    JPasswordField ps;
    JButton signin,login;
    Sign_in_page s;
    File_Handel file;

    private String password="",user="";

    Log_in_page(FrameOne f1)
    {

        f1.setBounds(420,150,400,350);
        p1= new JPanel();
        p2= new JPanel();
        p3= new JPanel();
        p1.setLayout(null);

        signin = new JButton("Sign in");
        signin.setBounds(93,158,75,20);
        signin.setFont(new Font("Times New Roman", Font.PLAIN + Font.ITALIC, 11));
        signin.setBackground(Color.GRAY);
        signin.setForeground(Color.orange);
        login= new JButton("Log in");
        login.setFont(new Font("Times New Roman", Font.PLAIN + Font.ITALIC, 11));
        login.setBounds(223,158,75,20);
        login.setForeground(Color.orange);
        login.setBackground(Color.GRAY);
        JLabel lb1=new JLabel("Username");
        lb1.setBounds(25,55,100,20);
        lb1.setFont(new Font("Times New Roman", Font.PLAIN + Font.ITALIC, 14));
        lb1.setForeground(Color.ORANGE);
        JLabel lb2=new JLabel("Password");
        lb2.setFont(new Font("Times New Roman", Font.PLAIN + Font.ITALIC, 14));
        lb2.setForeground(Color.ORANGE);
        lb2.setBounds(25,95,100,20);
        tf1=new JTextField(20);
        tf1.setBounds(124,55,150,20);
        tf1.setFont(new Font("Times New Roman", Font.BOLD+ Font.PLAIN, 13));
        ps=new JPasswordField(20);
        ps.setBounds(124,95,150,20);
        ps.setFont(new Font("Times New Roman", Font.BOLD + Font.PLAIN, 13));


        p1.setBackground(Color.BLACK);
        p2.setBackground(Color.ORANGE);
        p3.setBackground(Color.orange);
        p1.add(ps);
        p1.add(tf1);
        p1.add(lb1);
        p1.add(lb2);
        p1.add(signin);
        p1.add(login);

        f1.add(p1,BorderLayout.CENTER);
        f1.add(p2,BorderLayout.NORTH);
        f1.add(p3,BorderLayout.SOUTH);

        f1.setVisible(true);

        signin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object obj = e.getSource();
                if(obj==signin)
                {
                    p1.setVisible(false);
                    p2.setVisible(false);
                    p3.setVisible(false);
                    s=new Sign_in_page(f1);

                }
            }
        });
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username=tf1.getText();
                String pas= String.valueOf(ps.getPassword());
                if(username.equals(user) && pas.equals(password)){
                    JOptionPane.showMessageDialog(null, "Log in successful!!");
                    f1.setVisible(false);
                    FrameOne f2=new FrameOne(780,1300);

                    Main_page m=new Main_page(f2);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Invalid username or password !!");
                }


            }
        });

        p1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("x :"+e.getX()+" Y :  "+e.getY());
            }
        });





    }






}
