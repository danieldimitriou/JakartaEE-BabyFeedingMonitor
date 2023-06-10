package gr.athtech.backend.responses;

public class ChartResponse {
    private String message;
    private byte[] chart;

    public ChartResponse(String message, byte[] chart) {
        this.message = message;
        this.chart = chart;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public byte[] getChart() {
        return chart;
    }

    public void setChart(byte[] chart) {
        this.chart = chart;
    }
}
