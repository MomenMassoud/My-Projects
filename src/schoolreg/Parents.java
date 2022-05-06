package schoolreg;
public class Parents {
    //This Class Are Save Data Of parent student
    public void setFatherName(String FatherName) {
        this.FatherName = FatherName;
    }

    public void setMotherName(String MotherName) {
        this.MotherName = MotherName;
    }

    public String getFatherName() {
        return FatherName;
    }

    public String getMotherName() {
        return MotherName;
    }

    public Parents(String FatherName, String MotherName) {
        this.FatherName = FatherName;
        this.MotherName = MotherName;
    }

    public Parents() {
    }
    private String FatherName,MotherName;
}
