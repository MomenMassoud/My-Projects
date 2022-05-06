package schoolreg;
import java.sql.Connection;
import java .sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
//This Class Are Save data and send to super class schoolData and controll GUI
public class StudentData {
    Connection connect=null;
        Statement statment=null;
      PreparedStatement preparedStatement=null;
      ResultSet resultSet =null;
    SchoolData sd=new SchoolData();//Array Of Object To Super Class For All sub Classes
    public void start() throws IOException{//To Run Gui To Set Data Of Student
         Parent root = FXMLLoader.load(getClass().getResource("STDATA.fxml"));
        Scene scene = new Scene(root);
        Stage stage=new Stage();
        stage.setTitle("School System");
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    TextField fisrtname;
    @FXML
    TextField lastname;
    @FXML
    TextField fathername;
    @FXML
    TextField mothername;
    @FXML
    TextField Username;
    @FXML
    TextField Address;
    @FXML
    TextField nume;
    @FXML
    TextField Fees;
    @FXML
    TextField Age;
    @FXML
    TextField EducationStage;
    @FXML
    PasswordField Password;
    @FXML
    Button save;
    
    //GUI To Input Number Of Subject
     @FXML
    private void numberAction(ActionEvent event) throws IOException {//Action T
         setdata();
         Stage stage = (Stage) save.getScene().getWindow();
         stage.close();
    }
    
    private String  setdata() throws IOException{
        //Send Data ToSuper Class
        sd.setFirstName(fisrtname.getText());
         sd.setLastName(lastname.getText());
         sd.setAddress(Address.getText());
         sd.setUsername(Username.getText());
         sd.setPassword(Password.getText());
         
         sd.setAge(Age.getText());
         sd.setFees(Double.parseDouble(Fees.getText()));
         sd.parents.setFatherName(fathername.getText());
         sd.parents.setMotherName(mothername.getText());
         try{
           DBHandler bd=new DBHandler();
           bd.getConnection();
           bd.con(fisrtname.getText(), lastname.getText(), Address.getText(), Username.getText(),Password.getText(), EducationStage.getText(), Age.getText(),Double.parseDouble(Fees.getText()) , nume.getText(), fathername.getText(), mothername.getText());
       }catch(Exception ex){
        ex.printStackTrace();
    }
         if(EducationStage.getText().equalsIgnoreCase("primaryschool")){
             sd.primary=new PrimarySchool();
             sd.primary.setNumber(Integer.parseInt(nume.getText()));
             sd.primary.start(Username.getText());
             
             
         }
         
         if(EducationStage.getText().equalsIgnoreCase("middleschool")){
             sd.middle=new MiddleSchool();
             sd.middle.setNumber(Integer.parseInt(nume.getText()));
            sd.middle.start(Username.getText());
            
         }
         if(EducationStage.getText().equalsIgnoreCase("highschool")){
             sd.high=new HighSchool();
             sd.high.setNumber(Integer.parseInt(nume.getText()));
             sd.high.start(Username.getText());
         }
         if(EducationStage.getText().equalsIgnoreCase("kinderkides")){
             sd.kinder=new KinderKides();
             
             
         }
         user=sd.getUsername();
         System.out.println("User"+user);
         firstname=sd.getFirstName();
         last_name=sd.getLastName();
          busreg();
        return user;
    }
    //GUI Reg In BUS
     public void busreg() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("BUSGUI.fxml"));
        Scene scene = new Scene(root);
        Stage stage=new Stage();
        stage.setTitle("School System");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    Button yes;
    
    @FXML
    Button NO;
    static String user;
     static String firstname;
     static String last_name;
    
    
    //If Student will Reg In BUS
    @FXML
    private void yes()throws IOException{
        sd.bus=new Bus();
        sd.setBusreg(true);
        double f=sd.getFees()+2000;
        sd.setFees(sd.getFees()+2000);
         try{
           DBHandler bd=new DBHandler();
           bd.getConnection();
           bd.updatebus(user,firstname,last_name);
           
           
           
       }catch(Exception ex){
        ex.printStackTrace();
    }
        Stage stage = (Stage) yes.getScene().getWindow();
        stage.close();
    }
    //If Student not will Reg In BUS
    @FXML
    private void No()throws IOException{
        sd.setBusreg(false);
        Stage stage = (Stage) NO.getScene().getWindow();
        stage.close();
    }
    
   
    
}