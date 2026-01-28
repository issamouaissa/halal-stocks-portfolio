package emsi.java.halalstocks.dtos;

public class ClientDTO {
    private int clientid;
    private String clientname;
    private String email;
    private String password;

    public ClientDTO() {
    }
    public ClientDTO(int clientid, String clientname, String email, String password) {
        this.clientid = clientid;
        this.clientname = clientname;
        this.email = email;
        this.password = password;
    }

    public int getClientid() {
        return clientid;
    }

    public void setClientid(int clientid) {
        this.clientid = clientid;
    }

    public String getClientname() {
        return clientname;
    }

    public void setClientname(String clientname) {
        this.clientname = clientname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}