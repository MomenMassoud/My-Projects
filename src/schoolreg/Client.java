package schoolreg;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client implements Runnable{
    private String username;
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    Client(String username,String password){
        
    }

    Client() {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run() {
       try {
            String str="";
            Scanner s=new Scanner(System.in);
            InetAddress ip=InetAddress.getLocalHost();
            Socket other = new Socket(ip, 22000);
            DataInputStream otherinput=new DataInputStream(other.getInputStream());//Input client
            DataOutputStream othertoutput=new DataOutputStream(other.getOutputStream());//output client
            
            othertoutput.writeUTF(username.toString());
            othertoutput.writeUTF(password.toString());
            
            
            otherinput.close();
            othertoutput.close();
            other.close();
            
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
