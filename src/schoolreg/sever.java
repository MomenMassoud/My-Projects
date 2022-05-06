package schoolreg;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.DataInputStream;
import java.net.ServerSocket;

public class sever implements Runnable {

    

    @Override
    public void run() {
         try {
            String user,password;
            int c=0;
            ServerSocket server = new ServerSocket(22000);//port
            Socket other = server.accept();//accept connection
            DataInputStream otherinput = new DataInputStream(other.getInputStream());//Input client
            DataOutputStream othertoutput = new DataOutputStream(other.getOutputStream());//output client
            user=otherinput.readUTF();
            password=otherinput.readUTF();
            
               
               if(user.equalsIgnoreCase("admin")&&password.equalsIgnoreCase("admin")){
                   System.out.println("login!");
                   
               }
                
            
            otherinput.close();
            othertoutput.close();
            other.close();
            

        } catch (IOException ex) {//if make any exceptions
            Logger.getLogger(sever.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
