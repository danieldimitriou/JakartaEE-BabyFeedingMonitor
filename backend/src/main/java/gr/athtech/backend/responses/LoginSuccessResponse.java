package gr.athtech.backend.responses;

public class LoginSuccessResponse {
    private String jwt;
    private int statusCode;

    public LoginSuccessResponse(String jwt, int statusCode) {
        this.jwt = jwt;
        this.statusCode = statusCode;
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
}
