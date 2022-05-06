package schoolreg;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
//Thread If Admin Select Set Teacher and Student
public class TheardInputTeacher extends Thread{
    private Teachers th[];
    private int getNumberOfteacher;
    int  NumberOfteacher;

    public void setTh(Teachers[] th) {
        this.th = th;
    }

    public void setGetNumberOfteacher(int getNumberOfteacher) {
        this.getNumberOfteacher = getNumberOfteacher;
    }

    public Teachers[] getTh() {
        return th;
    }

    public int getGetNumberOfteacher() {
        return getNumberOfteacher;
    }

    public TheardInputTeacher(Teachers[] th, int getNumberOfteacher) {
        this.th = th;
        this.getNumberOfteacher = getNumberOfteacher;
    }

    public TheardInputTeacher() {
    }
    @Override
    public void run() {
        try {
            thRun();
        } catch (IOException ex) {
            Logger.getLogger(TheardInputTeacher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void thRun() throws IOException {
        //Start Stage to take Number Of Teacher Admin will set him
        Parent root = FXMLLoader.load(getClass().getResource("TeacherNumber.fxml"));//call FXML
        Scene scene = new Scene(root);//creat sence
        Stage stage = new Stage();//creat stage
        stage.setTitle("School System");//sit title
        stage.setScene(scene);//set sence in stage
        stage.show();//run stage
    }
    @FXML
    TextField numth;//Number of teacher
    @FXML
    Button addth;//Button add to take number of TH and close this stage

    @FXML
    private void thnumberAction(ActionEvent event) throws IOException {
        //action of Button addth 
        NumberOfteacher=(Integer.parseInt(numth.getText()));//save number of Teacher
        Stage stage = (Stage) addth.getScene().getWindow();//import stage from button
        stage.close();//close stage
        TheacherStart();//start set data for all teachers
    }

    private Teachers[]  TheacherStart() throws IOException {
        //This Method Call Of array of object to set teacher data and run GUI of Set teacher data
        th = new Teachers[NumberOfteacher];
        for (int i = 0; i < th.length; i++) {
            th[i] = new Teachers();
            th[i].start();//all things in this method
        }
        return th;//return data
    }
    
}
