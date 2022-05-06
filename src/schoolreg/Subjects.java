package schoolreg;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
//This Class Save Data Of Subject and Run GUI
public class Subjects extends SchoolData {
    private double grade;//Parameters For Subjects data
    private String SUBname,THname,username;
    


    public void setGrade(double grade) {
        this.grade = grade;
    }

    public void setSUBname(String SUBname) {
        this.SUBname = SUBname;
    }

   

    public void setTHname(String THname) {
        this.THname = THname;
    }

    public double getGrade() {
        return grade;
    }

    public String getSUBname() {
        return SUBname;
    }

    public String getTHname() {
        return THname;
    }

    public Subjects(double grade, String SUBname, String THname) {
        this.grade = grade;
        this.SUBname = SUBname;
        this.THname = THname;
    }

    public Subjects() {
    }
    
    public void start() throws IOException{//This Method To Run GUI
    
         Parent root = FXMLLoader.load(getClass().getResource("SubjectData.fxml"));
        Scene scene = new Scene(root);
        Stage stage=new Stage();
        stage.setTitle("School System");
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    Button sub1;
    @FXML
    TextField SUBN;
    @FXML
    TextField THN;
    @FXML
    TextField Grade;
    
     @FXML
    private void numberAction(ActionEvent event) throws IOException {//Action Button ADD
         setGrade(Double.parseDouble(Grade.getText()));
         setSUBname(SUBN.getText());
         setTHname(THN.getText());
         try{
           DBHandler bd=new DBHandler();
           bd.getConnection();
           String name=bd.read();
           System.out.println(name);
           bd.conSubject(Grade.getText(), SUBN.getText(), THN.getText(),name);
           
           
       }catch(Exception ex){
        ex.printStackTrace();
    }
         Stage stage = (Stage) sub1.getScene().getWindow();
         stage.close();
    }
}
