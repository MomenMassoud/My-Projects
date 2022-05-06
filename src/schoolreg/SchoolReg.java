package schoolreg;

import java.io.IOException;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SchoolReg extends Application  {

    StudentData st[];//Array Of Object To Creat Classes Of SchoolData
    Teachers th[];//Array Of Object of Teacheres
    private int numberOfstudents, numberOfteacher;//This Is varabiles To Nkow How Much Of Student And Th

    public int getNumberOfteacher() {
        return numberOfteacher;
    }

    public void setNumberOfteacher(int numberOfteacher) {
        this.numberOfteacher = numberOfteacher;
    }

    public void setNumberOfstudents(int numberOfstudents) {
        this.numberOfstudents = numberOfstudents;
    }

    public int getNumberOfstudents() {
        return numberOfstudents;
    }

    @Override
    public void start(Stage stage) throws Exception {//Start GUI
        login();
    }

    static loginin lg = new loginin();//Static object to know who is login secces
    @FXML
    TextField loginName;//Text Filed Of User name Of Student
    @FXML
    PasswordField passwordlogin;//Text Filed Of Password Of Student
    @FXML
    Button studentBT;//Button Login As Student

    public static void main(String[] args) {
        launch(args);
        System.out.println(lg.getUsername());

    }

    
    static DBHandler db = new DBHandler();//Class Of Data Base Connection

    public DBHandler checkloginst() throws SQLException, IOException {//Check Of UserName And Password For Student
        boolean login;//To Know If username and Password is correct
        String user = loginName.getText();//Save input userName From Gui
        String password = passwordlogin.getText();//Save Input Password From GUI
        db.getConnection();//start conniction database
        db.user_name = user;//save user name in class to can use later
        db.password = password;//save password in class to can use later
        login = db.read(user, password);//send to function read to check name and password
        if (login == true) {//if true
            //Start New Stage To Can print show data in screen
            Stage stage = (Stage) loginbt.getScene().getWindow();
            stage.close();//close stage login
            st(loginName.getText());//save who is login
            prints();//call this function to show new stage

        } else {//if password or email is not correct
            loginName.setText("InValid");//print massege
            loginName.setStyle("-fx-background-color:Red");
            passwordlogin.setText("InValid");//print massege
            passwordlogin.setStyle("-fx-background-color:Red");
        }
        return db;//return connection and who is login
    }

    public loginin st(String username) {
        lg.setUsername(username);//save in static object who is login
        return lg;//return value
    }

    public void printsTH() throws IOException {
        // This Function Show Stage Of show data Of Teacher If he login
        Parent root = FXMLLoader.load(getClass().getResource("teacherlogin.fxml"));//File GUI FXML
        Scene scene = new Scene(root);//sence
        Stage stage = new Stage();//stage
        stage.setTitle("School System");//Title Of Stage
        stage.setScene(scene);//Set Scene in stage
        stage.show();//start Stage 
        
    }

    public DBHandler checkloginTH() throws SQLException, IOException {
        //Check Of UserName And Password For Teacher
        boolean login;//To Know If  username and password is correct
        String user = loginName.getText();//take input username From GUI
        String password = passwordlogin.getText();//take input password From GUI
        db.getConnection();//Start Connection to DataBase
        db.user_name = user;//save username to use agin
        db.password = password;//save password to use agin
        login = db.readTH(user, password);//send username and password to check
        if (login == true) {
            //if true close stage login and start new stage to show data
            Stage stage = (Stage) loginbt.getScene().getWindow();//get stage from Button
            stage.close();//close stage
            st(loginName.getText());//save who is login
            printsTH();//start new stage
        } else {//if password or email is not correct
            loginName.setText("InValid");//Send Massege
            loginName.setStyle("-fx-background-color:Red");
            passwordlogin.setText("InValid");//Send Massege
            passwordlogin.setStyle("-fx-background-color:Red");
        }
        return db;//return value 
    }

    @FXML
    public void loginth(ActionEvent event) throws SQLException, IOException {
        //Method action of Button Login As Teacher To start Method Check 
        checkloginTH();//Check of data
    }
    
    @FXML
    TextField welcomTH;//TextField To Print Firstname And LastName Of Teacher
    
    @FXML
    TextField fisrtTH;//TextField To Print Firstname  Of Teacher
    
    @FXML
    TextField lastTH;//TextField To Print LastName Of Teacher
    
    @FXML
    TextField SalaryTH;//TextField To Print salary Of Teacher
    
    @FXML
    TextField subjectTH;//TextField To Print  subjectName  Of Teacher
    
    
    @FXML
    Button exitTH;//Button exit In stage teacher to exit program
    
    
    @FXML
    Button logoutTH;//Button exit In stage teacher and start stage login again
    
    @FXML
    private void logoutTH() throws IOException{
        //This method close Stage Teacher And start method login agin
        Stage stage = (Stage) logoutTH.getScene().getWindow();//Import Stage From Button
        stage.close();//close stage TH
        login();//Run login Stage Aagin
    }
    
    
    @FXML
    private void exitTH(ActionEvent event) throws SQLException{
        //This method close Stage Teacher and Close Program
        Stage stage = (Stage) exitTH.getScene().getWindow();//Import Stage From Button
        stage.close();//close stage TH
    }

    @FXML
    private void actionprintsTH(ActionEvent event) throws SQLException {
        //Action of Stage Teacher
        String user = lg.getUsername();//import username again to import data from database
        String firstname, Last_Name, User_Name, subjectname, salary;//Variables save value from database
        Connection connect = null;//conniction
        Statement statment = null;//statment sql
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;//result from sql
        try {
            Class.forName("com.mysql.jdbc.Driver");//Start driver
            connect = DriverManager.getConnection("jdbc:mysql://localhost/schoolreg?user=root&password=admin");//connect server of sql
            statment = connect.createStatement();//start statment connection 
            resultSet = statment.executeQuery("select * from teacher");//set statment of sql to query of tabels
            while (resultSet.next()) {//loop to read tables
                String User_N = resultSet.getString("Email");//this is primaryKey We use him to found all data of TH
                if (User_N.equals(lg.getUsername())) {//if username= Value of email we send so we found teaher now take data
                    firstname = resultSet.getString("First_Name");//take fisrt name of TH
                    Last_Name = resultSet.getString("Last_Name");//take last name of TH
                    User_Name = resultSet.getString("Email");//Take user name(email) of TH
                    subjectname = resultSet.getString("Subject_Name");//Take subject name for TH
                    salary = resultSet.getString("Salary");
                    welcomTH.setText(firstname+Last_Name);//Take salary of TH
                    fisrtTH.setText(firstname);//print first name in GUI
                    lastTH.setText(Last_Name);//print last name in GUI
                    SalaryTH.setText(salary);//print salary in GUI
                    subjectTH.setText(subjectname);//Print subject name in GUI
                    break;// break loop and disconnect data base becase we found data
                }
            }
        } catch (ClassNotFoundException ex) {//Exception for calss.forname
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void prints() throws IOException {
        //This Methos to run stage of student show data
        Parent root = FXMLLoader.load(getClass().getResource("loginstudent.fxml"));//Start Stage Of show data of student
        Scene scene = new Scene(root);//Creat sence
        Stage stage = new Stage();//creat stage
        stage.setTitle("School System");//set title
        stage.setScene(scene);//Set sence in stage
        stage.show();//show GUI
    }

    private String back(String username) {
        username = loginName.getText();
        return username;
    }

    @FXML
    TextField welcom;//Text Field To print Firstname and lastName for student

    @FXML
    TextField fisrt;//Text Field To print Firstname  for student

    @FXML
    TextField last;//Text Field To print  lastName for student

    @FXML
    TextField education;//Text Field To print education for student

    @FXML
    TextField feeses;//Text Field To print feeses for student

    @FXML
    TextField addr;//Text Field To print address for student

    @FXML
    TextField grades;//Text Field To total grade for student

    @FXML
    private void actionprintdrade(ActionEvent event) throws SQLException {
        Connection connect = null;//creat connection 
        Statement statment = null;//creat statment
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;//creat Result
        String subject_grade, subject_name, subjectTH;//Variable save data of subjects
        int c = 0;//Counter Of Subjects for student
        double total = 0;//get Total Grades of subjects
        try {
            Class.forName("com.mysql.jdbc.Driver");//set Driver
            connect = DriverManager.getConnection("jdbc:mysql://localhost/schoolreg?user=root&password=admin");//Start Connection in data base
            statment = connect.createStatement();//start statment
            resultSet = statment.executeQuery("select * from subjects");//set statment syql
            while (resultSet.next()) {//loop to found subject of this student
                String User_N = resultSet.getString("Student_UserName");//take user name to but him in if statment
                if (User_N.equals(lg.getUsername())) {//if user name = student _id(user name of student) take a data
                    subject_grade = resultSet.getString("Subject_Grade");//take grade 
                    subject_name = resultSet.getString("Subject_Name");//take subject name
                    subjectTH = resultSet.getString("Subject_TH");//take th_Name
                    total += Double.parseDouble(subject_grade);//assin total
                    c = c + 1;//count subject
                }
            }
            int subject = 100 * c;//assin how much subject we found
            grades.setText(total / subject * 100 + "%");//print total grades
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SchoolReg.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    Button exit;//Button Exit to close stage of student and close program

    @FXML
    private void exitbutton(ActionEvent event) {
        Stage stage = (Stage) exit.getScene().getWindow();//import stage from button
        stage.close();//close stage
    }

    @FXML
    Button bank22;//Button To Open website BankElmrefa

    @FXML
    public void network(ActionEvent event) {
        //Method open URL
        try {
            URI uri = new URI("https://moe.gov.eg/");
            java.awt.Desktop.getDesktop().browse(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    Button logout;//Button To Close stage Student and return to method login agian

    @FXML
    private void logoutbutton(ActionEvent event) throws IOException {
        Stage stage = (Stage) logout.getScene().getWindow();//Import stage from Button
        stage.close();//close Stage
        login();//Return to stage login again
    }
    
    
    
    //this method are print show data of student when he login
    @FXML
    private void actionprints(ActionEvent event) throws SQLException {
        String user = lg.getUsername();
        String firstname, Last_Name, Address, User_Name, educationStage;
        double fees;
        Connection connect = null;
        Statement statment = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            System.out.println(lg.getUsername());
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/schoolreg?user=root&password=admin");
            statment = connect.createStatement();
            resultSet = statment.executeQuery("select User_Name,First_Name,Last_Name,educationStage,Fees,Address from studentdata");
            while (resultSet.next()) {
                String User_N = resultSet.getString("User_Name");
                if (User_N.equals(lg.getUsername())) {
                    firstname = resultSet.getString("First_Name");
                    Last_Name = resultSet.getString("Last_Name");
                    Address = resultSet.getString("Address");
                    User_Name = resultSet.getString("User_Name");
                    educationStage = resultSet.getString("educationStage");
                    fees = resultSet.getDouble("Fees");
                    welcom.setText(firstname + Last_Name);
                    fisrt.setText(firstname);
                    last.setText(Last_Name);
                    education.setText(educationStage);
                    feeses.setText("" + fees);
                    addr.setText(Address);
                    connect.close();
                    statment.close();
                    resultSet.close();

                    break;
                }
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    //this first stage in this program 
    public void login() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("School System");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    TextField admintf;
    @FXML
    PasswordField adminpass;
    @FXML
    Button loginbt;
    @FXML
    CheckBox stcheck;
    @FXML
    CheckBox thcheck;

    //This Method Are Controlle button login as admin to set data
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException, InterruptedException {
        sever s=new sever();
            Client c=new Client();
           c.setPassword(admintf.getText());
           c.setUsername(adminpass.getText());
            new Thread(s).start();
            new Thread(c).start();
        if (admintf.getText().equals("admin") && adminpass.getText().equals("admin")) {
            System.out.println("login! 2");
            if(stcheck.isSelected() &&thcheck.isSelected()){
                
                TheardInputTeacher t1=new TheardInputTeacher();
                t1.run();
                TheardInputStudent t2=new TheardInputStudent();
                t2.run();
                
                Stage stage = (Stage) loginbt.getScene().getWindow();
                stage.close();
                
            }else{
                if (stcheck.isSelected()) {
                TheardInputStudent t2=new TheardInputStudent();
                t2.number();
                Stage stage = (Stage) loginbt.getScene().getWindow();
                stage.close();
            }
            if (thcheck.isSelected()) {
                TheardInputTeacher t1=new TheardInputTeacher();
                t1.thRun();
                Stage stage = (Stage) loginbt.getScene().getWindow();
                stage.close();
            }
            }
            
        }
    }

    

   
}
