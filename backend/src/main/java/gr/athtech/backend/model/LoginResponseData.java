package gr.athtech.backend.model;

public class LoginResponseData {
    String jwt;
    String role;
    int statusCode;

    public LoginResponseData(String jwt, String role, int statusCode) {
        this.jwt = jwt;
        this.role = role;
        this.statusCode = statusCode;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
