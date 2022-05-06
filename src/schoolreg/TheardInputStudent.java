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
public class TheardInputStudent extends Thread {
    int NumberOfstudents;
    StudentData st[];
    @Override
    public void run() {
        try {
            number();
        } catch (IOException ex) {
            Logger.getLogger(TheardInputStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void number() throws IOException {
        //This Method Start stage to 
        Parent root = FXMLLoader.load(getClass().getResource("numberST.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("School System");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    TextField num;//take input of number of student
    @FXML
    Button add;//this button save number of student

    @FXML
    private void numberAction(ActionEvent event) throws IOException {
        //Take number of student Admin will set data for him
        NumberOfstudents=(Integer.parseInt(num.getText()));//save number of student
        Stage stage = (Stage) add.getScene().getWindow();//import stage of student
        stage.close();//close stage
        controlclass();//call method to take data of students
    }

    private StudentData[] controlclass() throws IOException {
        //This Method Call Of array of object to set teacher data and run GUI of Set Student data
        st = new StudentData[NumberOfstudents];
        for (int i = 0; i < st.length; i++) {
            st[i] = new StudentData();
            st[i].start();//all things in this method
        }
        return st;
    }

    
}
