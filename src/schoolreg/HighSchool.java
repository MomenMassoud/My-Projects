package schoolreg;

import java.io.IOException;

public class HighSchool extends SchoolData{
     private int number;//Numbrer Of Subject
    Subjects sub[];//Array of class subject
    private String FirstName,  LastName,  age,  address,  username,  password;//data of student

    public Subjects[] getSub() {
        return sub;
    }

    @Override
    public String getFirstName() {
        return FirstName;
    }

    @Override
    public String getLastName() {
        return LastName;
    }

    @Override
    public String getAge() {
        return age;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }
    public HighSchool(String FirstName, String LastName, String age, String address, String username, String password) {
        super(FirstName, LastName, age, address, username, password);
    }

    public void setNumber(int number) {
        this.number = number;
        System.out.println(this.number);
        sub=new Subjects[this.number];
    }


    public int getNumber() {
        return number;
    }

    public HighSchool() {
    }
    //This method start gui for each subject of student to set data 
    public void start(String Name) throws IOException{
        System.out.println(Name);
        for(int i=0;i<getNumber();i++){
            sub[i]=new Subjects();
            sub[i].setUsername(getUsername());
            System.out.println(getUsername());
            sub[i].start();
        }
        
    }
    
}
