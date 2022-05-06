package schoolreg;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
//This Class Are Controll in conniction database

public class DBHandler extends Configs {

    String password, user_name;//This Varibale to save who is login
    private Connection connection;//Inislaize connect
    private DBHandler handler;
    private PreparedStatement pst;

    public Connection getConnection() {//Start Conniction
        try {
            //set Driver
            String con = "jdbc:mysql://" + this.dbhost + ":" + this.dbport + "/" + this.dbname + "?autoReconnect=true&useSSL=false";
            connection = DriverManager.getConnection(con, this.dbuser, this.dbpass);//start conniction
            System.out.println("done");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("no");//if connection filed
        }
        return connection;
    }
    //This method are set data student in database
    public void con(String firstname, String Last_Name, String Address, String User_Name, String Password, String educationStage, String Age, double Fees, String Subject_Number, String Father_Name, String Mother_Name) throws SQLException {
        try {
            String insert = "INSERT INTO studentdata(First_Name,Last_Name,Address,User_Name,Password,educationStage,Age,Fees,Subject_Number,Father_Name,Mother_Name)" + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            pst = connection.prepareStatement(insert);
            pst.setString(1, firstname);
            pst.setString(2, Last_Name);
            pst.setString(3, Address);
            pst.setString(4, User_Name);
            pst.setString(5, Password);
            pst.setString(6, educationStage);
            pst.setString(7, Age);
            pst.setDouble(8, Fees);
            pst.setString(9, Subject_Number);
            pst.setString(10, Father_Name);
            pst.setString(11, Mother_Name);
            pst.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
    //This Method Are set data in tabel teacher in database
    public void conTeacher(String ID, String First_Name, String Last_Name, String Email, String Password, String Salary, String Subject_Name) throws SQLException {
        try {
            String insert = "INSERT INTO teacher(ID,First_Name,Last_Name,Email,Password,Salary,Subject_Name)" + "VALUES(?,?,?,?,?,?,?)";
            pst = connection.prepareStatement(insert);
            pst.setString(1, ID);
            pst.setString(2, First_Name);
            pst.setString(3, Last_Name);
            pst.setString(4, Email);
            pst.setString(5, Password);
            pst.setString(6, Salary);
            pst.setString(7, Subject_Name);
            pst.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
    //Set data of subject in table subject in database
    public void conSubject(String Subject_Grade, String Subject_Name, String Subject_TH, String Student_UserName) throws SQLException {
        try {
            String insert = "INSERT INTO subjects(Subject_Grade,Subject_Name,Subject_TH,Student_UserName)" + "VALUES(?,?,?,?)";
            pst = connection.prepareStatement(insert);
            pst.setString(1, Subject_Grade);
            pst.setString(2, Subject_Name);
            pst.setString(3, Subject_TH);
            pst.setString(4, Student_UserName);
            pst.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
    //This Method to gave all class username of student when admin set his data
    public String read() throws SQLException {
        String name = null;//to save username
        Connection connect = null;//start connect
        Statement statment = null;//set statment
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;//result
        try {
            Class.forName("com.mysql.jdbc.Driver");//start driver
            connect = DriverManager.getConnection("jdbc:mysql://localhost/schoolreg?user=root&password=admin");//try connect in data base
            statment = connect.createStatement();//connect satment in database
            resultSet = statment.executeQuery("select User_Name from studentdata");//set query
            while (resultSet.next()) {
                name = resultSet.getString("User_Name");
                System.out.println(name);
                break;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return name;//return username
    }
    //this method are check username and password to login student
    public boolean read(String name, String Password) throws SQLException {
        String pass = null;
        String firstname = null, lastname = null;
        Connection connect = null;
        String User = null;
        Statement statment = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/schoolreg?user=root&password=admin");
            statment = connect.createStatement();
            resultSet = statment.executeQuery("select First_Name,Last_Name,User_Name,Password from studentdata");
            while (resultSet.next()) {
                User = resultSet.getString("User_Name");
                pass = resultSet.getString("Password");
                firstname = resultSet.getString("First_Name");
                lastname = resultSet.getString("Last_Name");
                if (User.equals(name)) {
                    if (pass.equals(Password)) {
                        System.out.println("login \n Welcome " + firstname + lastname);
                        return true;
                    }
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    //check username and password to login teacher
    public boolean readTH(String name, String Password) throws SQLException {
        String pass = null;
        String firstname = null, lastname = null;
        Connection connect = null;
        String User = null;
        Statement statment = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/schoolreg?user=root&password=admin");
            statment = connect.createStatement();
            resultSet = statment.executeQuery("select First_Name,Last_Name,Email,Password from teacher");
            while (resultSet.next()) {
                User = resultSet.getString("Email");
                pass = resultSet.getString("Password");
                firstname = resultSet.getString("First_Name");
                lastname = resultSet.getString("Last_Name");
                if (User.equals(name)) {
                    if (pass.equals(Password)) {
                        System.out.println("login \n Welcome " + firstname + lastname);
                        return true;
                    }
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    //set data student in bus if he reg in bus 
    public void updatebus(String user, String first, String last) throws SQLException {
        try {
            String insert = "INSERT INTO bus(Bus_no,First_Name,Last_Name,Student_UserName)" + "VALUES(?,?,?,?)";
            pst = connection.prepareStatement(insert);
            pst.setString(1, "10");
            pst.setString(2, first);
            pst.setString(3, last);
            pst.setString(4, user);
            pst.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

}
