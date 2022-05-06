package schoolreg;

import java.io.IOException;
//This Class Save All data Student this is Super class
public class SchoolData implements Name{
    //Prameter student
    private String FirstName;
    private boolean busreg;
    private String LastName,address,username,password,educationStage,age;
    Parents parents=new Parents();
    
    private double fees;
    PrimarySchool primary;
    MiddleSchool middle;
    HighSchool high;
    KinderKides kinder;
    Bus bus;
    
    
    public void setFees(double fees) {
        this.fees = fees;
    }

    public double getFees() {
        return fees;
    }
    public void setFirstName(String FirstName) {
        
        this.FirstName = FirstName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public boolean isBusreg() {
        return busreg;
    }

    public String getEducationStage() {
        return educationStage;
    }

    public void setBusreg(boolean busreg) {
        this.busreg = busreg;
    }

    public void setEducationStage(String educationStage) {
        this.educationStage = educationStage;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setParents(Parents parents) {
        this.parents = parents;
    }

    @Override
    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Parents getParents() {
        return parents;
    }

    public SchoolData(String FirstName, String LastName, String age, String address, String username, String password) {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.age = age;
        this.address = address;
        this.username = username;
        this.password = password;
    }

    public SchoolData() {
    }
    public void update(){
        fees+=2000;
    }
    
}
