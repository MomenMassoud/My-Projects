package schoolreg;

import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
//THIs Class Save Data Of Teacher And Run His GUI to Take INPUT
public class Teachers {
    private String email,password, firstname,lastname,subject;//Parameters For Teachers Data 
    private double Salary;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setSalary(double Salary) {
        this.Salary = Salary;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getSubject() {
        return subject;
    }

    public double getSalary() {
        return Salary;
    }

    public Teachers(String email, String password, String firstname, String lastname, String subject, double Salary) {
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.subject = subject;
        this.Salary = Salary;
    }

    public Teachers() {
    }
    
    
    public void start() throws IOException{//This Method to run gui
        Parent root = FXMLLoader.load(getClass().getResource("TeacherData.fxml"));
        Scene scene = new Scene(root);
        Stage stage=new Stage();
        stage.setTitle("School System");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    TextField fisrtname;
    
    @FXML
    TextField Lastname;
    
    @FXML
    TextField Username;
    
    @FXML
    TextField salary1;
    
    @FXML
    TextField subjectName;
    
    @FXML
    PasswordField Password;
    
    @FXML
    TextField ID;
    
    @FXML
    Button save;
    
    @FXML
    private void Action(ActionEvent event) throws IOException, SQLException{//Action Button
        setEmail(Username.getText());
        setFirstname(fisrtname.getText());
        setLastname(Lastname.getText());
        setPassword(Password.getText());
        setSalary(Double.parseDouble(salary1.getText()));
        setSubject(subjectName.getText());
        String id=ID.getText();
        try{
            DBHandler bd=new DBHandler();
           bd.getConnection();
           bd.conTeacher(id,fisrtname.getText(), Lastname.getText(), Username.getText(), Password.getText(), salary1.getText(), subjectName.getText());
        }catch(Exception ex){
            ex.printStackTrace();
        }
       Stage stage = (Stage) save.getScene().getWindow();
        stage.close();
    }
    
}
