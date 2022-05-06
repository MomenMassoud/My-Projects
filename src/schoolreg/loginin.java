package schoolreg;
public class loginin {
    //this class is static to save who is login
    private String username;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public loginin(String username) {
        this.username = username;
    }

    public loginin() {
    }
}
