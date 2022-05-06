package schoolreg;
//Class Bus To Save Data Of Students Reg in Bus
public class Bus extends SchoolData{
   private double newfees;//New Fees Of Student

    public void setNewfees(double newfees) {
        this.newfees = newfees;
    }

    public double getNewfees() {
        return newfees;
    }
    //Constructor
    public Bus(double newfees, String FirstName, String LastName, String age, String address, String username, String password) {
        super(FirstName, LastName, age, address, username, password);
        this.newfees = newfees;
    }

    public Bus(String FirstName, String LastName, String age, String address, String username, String password) {
        super(FirstName, LastName, age, address, username, password);
    }

    public Bus() {
    }
}
