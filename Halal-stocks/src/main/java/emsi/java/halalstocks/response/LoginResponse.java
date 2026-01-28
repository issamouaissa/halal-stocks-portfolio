/*
package emsi.java.halalstocks.response;

public class LoginResponse {
    private String message;
    private Boolean status;

    // Default constructor
    public LoginResponse() {}

    // Parameterized constructor
    public LoginResponse(String message, Boolean status) {
        this.message = message;
        this.status = status;
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}
*/





package emsi.java.halalstocks.response;

public class LoginResponse {
    private String message;
    private Boolean status;
    private Integer clientId;

    // Constructeurs
    public LoginResponse() {}

    public LoginResponse(String message, Boolean status, Integer clientId) {
        this.message = message;
        this.status = status;
        this.clientId = clientId;
    }

    // Getters et Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "message='" + message + '\'' +
                ", status=" + status +
                ", clientId=" + clientId +
                '}';
    }
}