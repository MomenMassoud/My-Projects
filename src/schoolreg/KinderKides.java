package schoolreg;
public class KinderKides extends SchoolData{
    private String FirstName,LastName;
    public KinderKides(String FirstName, String LastName, String age, String address, String username, String password) {
        super(FirstName, LastName, age, address, username, password);
    }
   
    @Override
     public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
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
     public void setLastName(String LastName) {
        this.LastName = LastName;
    }
    public KinderKides() {
    }
}
