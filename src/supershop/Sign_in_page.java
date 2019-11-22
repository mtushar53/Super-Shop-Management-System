package labib;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sign_in_page  {

    JPanel p1,p2,p3,p4;
    JTextField tf1;
    JPasswordField ps,cps;
    JButton signin,back;
    static String username;
    static String password="",copassword="";
    char [] pas,cpas;
    public Sign_in_page()
    {};

    public Sign_in_page(FrameOne f1)
    {


        p1= new JPanel();
        p2= new JPanel();
        p3= new JPanel();
        p1.setLayout(null);

        signin = new JButton("Sign in");
        signin.setBounds(223,200,75,20);
        signin.setFont(new Font("Times New Roman", Font.PLAIN + Font.ITALIC, 11));
        signin.setBackground(Color.GRAY);
        signin.setForeground(Color.orange);
        back= new JButton("Back");
        back.setFont(new Font("Times New Roman", Font.PLAIN + Font.ITALIC, 11));
        back.setBounds(93,200,75,20);
        back.setForeground(Color.orange);
        back.setBackground(Color.GRAY);
        JLabel lb1=new JLabel("Username");
        lb1.setBounds(25,55,100,20);
        lb1.setFont(new Font("Times New Roman", Font.PLAIN + Font.ITALIC, 14));
        lb1.setForeground(Color.ORANGE);
        JLabel lb2=new JLabel("Password");
        lb2.setFont(new Font("Times New Roman", Font.PLAIN + Font.ITALIC, 14));
        lb2.setForeground(Color.ORANGE);
        lb2.setBounds(25,95,100,20);
        JLabel lb3=new JLabel("Confirm Password");
        lb3.setFont(new Font("Times New Roman", Font.PLAIN + Font.ITALIC, 14));
        lb3.setForeground(Color.ORANGE);
        lb3.setBounds(10,135,150,20);
        tf1=new JTextField(20);
        tf1.setBounds(124,55,150,20);
        tf1.setFont(new Font("Times New Roman", Font.BOLD+ Font.PLAIN, 13));
        ps=new JPasswordField(20);
        ps.setBounds(124,95,150,20);
        ps.setFont(new Font("Times New Roman", Font.BOLD + Font.PLAIN, 13));
        cps=new JPasswordField(20);
        cps.setBounds(124,135,150,20);
        cps.setFont(new Font("Times New Roman", Font.BOLD + Font.PLAIN, 13));

        p1.setBackground(Color.BLACK);
        p2.setBackground(Color.ORANGE);
        p3.setBackground(Color.orange);
        p1.add(ps);
        p1.add(cps);
        p1.add(tf1);
        p1.add(lb1);
        p1.add(lb2);
        p1.add(lb3);
        p1.add(signin);
        p1.add(back);


        f1.add(p1,BorderLayout.CENTER);
        f1.add(p2,BorderLayout.NORTH);
        f1.add(p3,BorderLayout.SOUTH);

        signin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username=tf1.getText();
                pas= ps.getPassword();
                cpas=cps.getPassword();
               for(int i=0;i<pas.length;i++) {
                   password+=pas[i];
                   copassword+=cpas[i];
               }
                if(password.equals(copassword) && !username.isEmpty()) {

                    JOptionPane.showMessageDialog(null, "Sign in successful ");
                    File_Handel f=new File_Handel("pasfile.txt");

                    f.writeFile("1",username,password);
                }
                else {
                    if(username.isEmpty())
                        JOptionPane.showMessageDialog(null, "Please fill up the username field!! ");
                    else
                        JOptionPane.showMessageDialog(null, "Password don't match!! ");
                }
                p1.setVisible(false);
                p2.setVisible(false);
                p3.setVisible(false);
                Log_in_page s=new Log_in_page(f1);
            }
        });
//        tf1.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
//        ps.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//
//            }
//        });
//        cps.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object obj = e.getSource();
                if(obj==back)
                {
                    p1.setVisible(false);
                    p2.setVisible(false);
                    p3.setVisible(false);
                    Log_in_page s=new Log_in_page(f1);

                }
            }
        });



    }


}
