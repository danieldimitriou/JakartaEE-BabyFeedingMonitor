package gr.athtech.backend.responses;

public class LoginSuccessResponse {
    private String jwt;
    private int statusCode;
    private String role;

    public LoginSuccessResponse(String jwt, int statusCode, String role) {
        this.jwt = jwt;
        this.statusCode = statusCode;
        this.role = role;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
