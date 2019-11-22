package labib;

import javax.swing.*;
import java.awt.*;

public class MainClass {

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FrameOne f1=new FrameOne(350,400);
                    new Log_in_page(f1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
