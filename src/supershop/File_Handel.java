package labib;

import sun.java2d.pipe.BufferedRenderPipe;

import javax.swing.*;
import java.io.*;

public class File_Handel {
    String file_name;
    String username;
    String password;

   public File_Handel(String file_name)
   {
       file_name=this.file_name;
   }
   public void readFile()
   {

   }
   public boolean password_read(String username,String password)
   {
       String [] line={};
       boolean flag=false;
       try {
           BufferedReader br=new BufferedReader(new FileReader(file_name));
           int i=0;
           while ((line[i] = br.readLine()) != null)
           {

               if(line[i].equals("1"))
               {
                   JOptionPane.showMessageDialog(null, "Already Signed in!!");
                   flag=false;
               }
               i++;
           }
           if(line[1].equals(username)&& line[2].equals(password))
               flag = true;


       } catch (FileNotFoundException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }

       return flag;
   }

   public void writeFile(String ...s)
   {
       try {
           PrintWriter w=new PrintWriter(file_name,"UTF-8");
           for(int i=0;i<s.length;i++) {
               w.println(s[i]);
           }
           w.close();

       } catch (FileNotFoundException e) {
//           e.printStackTrace();
           System.out.println("File not found");
       } catch (UnsupportedEncodingException e) {
//           e.printStackTrace();
           System.out.println("Unsupported encoding");
       }
   }
}
