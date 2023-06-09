package gr.athtech.backend.model;

public class LoginResponseData {
    String jwt;
    String role;

    public LoginResponseData(String jwt, String role) {
        this.jwt = jwt;
        this.role = role;
    }

    public String getJwt() {
        return jwt;
    }

    public String getRole() {
        return role;
    }
}
