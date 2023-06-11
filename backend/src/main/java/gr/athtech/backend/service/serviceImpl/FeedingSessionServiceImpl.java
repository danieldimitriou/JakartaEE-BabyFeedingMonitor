package gr.athtech.backend.service.serviceImpl;

import gr.athtech.backend.dto.FeedingSessionListDTO;
import gr.athtech.backend.model.FeedingSession;
import gr.athtech.backend.repository.FeedingSessionRepository;
import gr.athtech.backend.service.FeedingSessionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;
import javax.inject.Inject;
import javax.naming.NamingException;
import javax.ws.rs.NotFoundException;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;


public class FeedingSessionServiceImpl implements FeedingSessionService {
    @Inject
    private FeedingSessionRepository feedingSessionRepository;
    private static final Logger logger = (Logger) LogManager.getLogger(FeedingSessionServiceImpl.class);

    @Override
    public Optional<FeedingSession> getFeedingSessionById(int id) {
        Optional<FeedingSession> feedingSession = this.feedingSessionRepository.getById(id);
        if (feedingSession.isPresent()) {
            return feedingSession;
        } else {
            throw new NotFoundException("Feeding session not found");
        }
    }

    @Override
    public Optional<FeedingSessionListDTO> getAll() {
        return this.feedingSessionRepository.getAll();
    }


    @Override
    public boolean createFeedingSession(FeedingSession feedingSession) throws NamingException {
        feedingSession.calculateDuration();
        logger.error(feedingSession);
        return this.feedingSessionRepository.create(feedingSession);
    }

    @Override
    public Optional<FeedingSession> updateFeedingSession(FeedingSession newFeedingSessionData) {
        newFeedingSessionData.calculateDuration();
        return this.feedingSessionRepository.update(newFeedingSessionData);
    }


    @Override
    public Optional<byte[]> getChart() {
        Optional<FeedingSessionListDTO> feedingSessionListDTO = this.getAll();
        if (feedingSessionListDTO.isPresent()) {
            FeedingSessionListDTO sessions = feedingSessionListDTO.get();
            Map<Date, List<FeedingSession>> sessionsByDate = groupSessionsByDate(sessions.getFeedingSessions());

            // Sort the dates in ascending order in order for them to appear correctly in the graph
            List<Date> sortedDates = new ArrayList<>(sessionsByDate.keySet());
            Collections.sort(sortedDates);

            // Calculate the average amount consumed for each date
            Date[] dates = new Date[sortedDates.size()];
            double[] amountsConsumed = new double[sortedDates.size()];
            for (int i = 0; i < sortedDates.size(); i++) {
                Date date = sortedDates.get(i);
                List<FeedingSession> sessionsOnDate = sessionsByDate.get(date);
                double averageSessionConsumed = 0;
                for (FeedingSession session : sessionsOnDate) {
                    amountsConsumed[i] = session.getAmountConsumed();
                }
                dates[i] = date;
            }

            String title = "Feeding Session Chart";
            String xLabel = "Date";
            String yLabel = "Average Amount Consumed in ml.";

            byte[] chartImage = generateLineChart(dates, amountsConsumed, title, xLabel, yLabel);
            return Optional.of(chartImage);
        }
        return Optional.empty();
    }

    private Map<Date, List<FeedingSession>> groupSessionsByDate(List<FeedingSession> sessions) {
        Map<Date, List<FeedingSession>> sessionsByDate = new HashMap<>();
        for (FeedingSession session : sessions) {
            Date date = session.getDate();
            sessionsByDate.computeIfAbsent(date, k -> new ArrayList<>()).add(session);
        }
        return sessionsByDate;
    }

    private byte[] generateLineChart(Date[] dates, double[] amounts, String title, String xLabel, String yLabel) {
        DefaultXYDataset dataset = new DefaultXYDataset();
        double[][] seriesData = new double[][] { convertDatesToMillis(dates), amounts };
        dataset.addSeries("Date", seriesData);

        JFreeChart chart = ChartFactory.createXYLineChart(
                title,
                xLabel,
                yLabel,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinesVisible(true);   // Show range (y-axis) grid lines
        plot.setRangeGridlinePaint(Color.GRAY);  // Set the color of range grid lines
        plot.setDomainGridlinesVisible(true);  // Show domain (x-axis) grid lines
        plot.setDomainGridlinePaint(Color.GRAY);//        plot.setPaint(Color.BLACK);// Set your desired background color here
        DateAxis dateAxis = new DateAxis();
        dateAxis.setDateFormatOverride(new SimpleDateFormat("dd/MM/yyyy")); // Set your desired date format here
        plot.setDomainAxis(dateAxis);
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesShapesVisible(0, true);
        renderer.setSeriesPaint(0, Color.BLACK);
        plot.setRenderer(renderer);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ChartUtils.writeChartAsPNG(outputStream, chart, 800, 600);
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private double[] convertDatesToMillis(Date[] dates) {
        double[] millis = new double[dates.length];
        for (int i = 0; i < dates.length; i++) {
            millis[i] = dates[i].getTime();
        }
        return millis;
    }

    @Override
    public boolean deleteFeedingSession(int id) {
        boolean d = this.feedingSessionRepository.delete(id);
        logger.error("DELETE RESSSS:" + d);
        return d;
    }

    @Override
    public Optional<FeedingSessionListDTO> getByDates(Date startDate, Date endDate) {
        return this.feedingSessionRepository.getByDates(startDate, endDate);
    }
}
