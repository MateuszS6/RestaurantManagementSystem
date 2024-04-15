package GUI;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

public class SalesPanel extends MyPanel {
    private JPanel mainPanel;
    private JTabbedPane tabbedPane;
    private JComboBox bookingsComboBox;
    private JComboBox coversComboBox;
    private JLabel dayCoversLabel;
    private JLabel dayBookingsLabel;
    private JLabel annualBookingsLabel;
    private JLabel annualCoversLabel;
    private JPanel bookingsGraphTab;

    public SalesPanel(MyPanel parent) {
        super("Sales", parent.getConnection());

        // Drop-down menus: set number using selected index of drop-down menu
        bookingsComboBox.addActionListener(e -> dayBookingsLabel.setText(getConnection().getDayAverageBookings(bookingsComboBox.getSelectedIndex())));
        coversComboBox.addActionListener(e -> dayCoversLabel.setText(getConnection().getDayAverageCovers(coversComboBox.getSelectedIndex())));

        // Set statistic labels using inherited connection
        dayBookingsLabel.setText(getConnection().getDayAverageBookings(0)); // 0 -> on Monday
        dayCoversLabel.setText(getConnection().getDayAverageCovers(0)); // 0 -> Monday
        annualBookingsLabel.setText(getConnection().getAnnualBookings());
        annualCoversLabel.setText(getConnection().getAnnualCovers());

        // Create charts
        createChart();
        createPopRevenueChart();
        createPredictedBookings();
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void createChart() {
        // Create a time series data set
        TimeSeries series = new TimeSeries("Number of Covers");

        Map<Date, Integer> bookingDataMap = getConnection().getBookingData();
        for (Map.Entry<Date, Integer> entry : bookingDataMap.entrySet())
            series.addOrUpdate(new Day(entry.getKey()), entry.getValue());
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(series);

        // Create the chart
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Booking Pattern", // Title
                "Date",            // X-axis label
                "Number of Covers",// Y-axis label
                dataset,           // Dataset
                true,              // Show legend
                true,              // Use tooltips
                false              // Configure chart to generate URLs?
        );

        // Customise the appearance of the chart
        XYPlot plot = chart.getXYPlot();
        XYItemRenderer renderer = plot.getRenderer();
        if (renderer instanceof XYLineAndShapeRenderer rr) {
            rr.setSeriesShapesVisible(0, true);
            rr.setSeriesShapesFilled(0, true);
        }

        // Add the chart to a panel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setFillZoomRectangle(true);
        chartPanel.setMouseWheelEnabled(true);
        bookingsGraphTab.setLayout(new BorderLayout());
        bookingsGraphTab.add(chartPanel, BorderLayout.CENTER);
    }

    private void createPopRevenueChart() {
        // Fetch your data
        Map<String, Map.Entry<Integer, BigDecimal>> dishSalesData = getConnection().getDishData();

        // Create the datasets
        DefaultCategoryDataset quantityDataset = new DefaultCategoryDataset();
        DefaultCategoryDataset revenueDataset = new DefaultCategoryDataset();

        // Loop through the dish data map and populate the datasets
        for (Map.Entry<String, Map.Entry<Integer, BigDecimal>> entry : dishSalesData.entrySet()) {
            String dishName = entry.getKey();
            Map.Entry<Integer, BigDecimal> salesData = entry.getValue();
            quantityDataset.addValue(salesData.getKey(), "Quantity Sold", dishName); // Aggregate quantity
            revenueDataset.addValue(salesData.getValue().doubleValue(), "Revenue", dishName); // Aggregate revenue
        }

        // Create the line chart for quantity sold
        JFreeChart chart = ChartFactory.createLineChart(
                "Dish Popularity and Revenue", // chart title
                "Dish Name",                  // domain axis label
                "Quantity Sold",              // range axis label
                quantityDataset,              // data
                PlotOrientation.VERTICAL,
                true,                         // include legend
                true,                         // tooltips
                false                         // urls
        );

        // Get the plot
        CategoryPlot plot = chart.getCategoryPlot();

        // Set the line renderer for the quantity dataset
        LineAndShapeRenderer quantityRenderer = new LineAndShapeRenderer();
        quantityRenderer.setSeriesPaint(0, new Color(79, 129, 189)); // Set the color for the quantity line
        quantityRenderer.setBaseShapesVisible(true);
        quantityRenderer.setBaseItemLabelsVisible(true);
        quantityRenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        plot.setRenderer(quantityRenderer);

        // Add the second dataset (revenue)
        plot.setDataset(1, revenueDataset);
        plot.mapDatasetToRangeAxis(1, 0);

        // Set the line renderer for the revenue dataset
        LineAndShapeRenderer revenueRenderer = new LineAndShapeRenderer();
        revenueRenderer.setSeriesPaint(0, new Color(192, 80, 77)); // Set the color for the revenue line
        revenueRenderer.setBaseShapesVisible(true);
        revenueRenderer.setBaseItemLabelsVisible(true);
        revenueRenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        plot.setRenderer(1, revenueRenderer);

        // Customize the plot appearance
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        // Customize axis labels
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

        // Customize the range axis
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // Customize the chart legend
        if (chart.getLegend() != null) chart.getLegend().setItemFont(new Font("Dialog", Font.PLAIN, 12));

        // Add the customized chart to a ChartPanel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setMouseWheelEnabled(true);

        // Add the ChartPanel to the popRevenueTab
        tabbedPane.addTab("Popularity & Revenue", chartPanel);
    }

    private void createPredictedBookings() {
        // Fetch historical booking data
        Map<Date, Integer> historicalBookings = getConnection().getBookingData();
        TimeSeries series = new TimeSeries("Historical Bookings");

        for (Map.Entry<Date, Integer> entry : historicalBookings.entrySet())
            series.addOrUpdate(new Day(entry.getKey()), entry.getValue());

        // Now fetch the future booking predictions - decide how many days you want to predict
        Map<Date, Integer> futurePredictions = getConnection().getFutureBookingPredictions();

        // Populate the forecast series with predicted data
        TimeSeries forecastSeries = new TimeSeries("Forecasted Bookings");
        for (Map.Entry<Date, Integer> entry : futurePredictions.entrySet())
            forecastSeries.addOrUpdate(new Day(entry.getKey()), entry.getValue());

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(series); // Your historical data
        dataset.addSeries(forecastSeries); // Your forecasted data

        // Now create your chart with the dataset
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Booking Trend and Forecast",
                "Date",
                "Number of Bookings",
                dataset,
                true,
                true,
                false
        );

        // Customise the appearance of the chart to distinguish forecasted data
        XYPlot plot = chart.getXYPlot(); // Assuming 'chart' is your JFreeChart object

        // Customising the renderer for historical bookings
        XYItemRenderer historicalRenderer = new XYLineAndShapeRenderer(true, false);
        historicalRenderer.setSeriesPaint(0, new Color(0, 0, 255)); // Blue for historical bookings
        historicalRenderer.setSeriesStroke(0, new BasicStroke(2.0f));
        plot.setRenderer(0, historicalRenderer);

        // Customising the renderer for forecasted bookings
        XYItemRenderer forecastRenderer = new XYLineAndShapeRenderer(true, false);
        forecastRenderer.setSeriesPaint(0, new Color(255, 165, 0)); // Orange for forecasted bookings
        forecastRenderer.setSeriesStroke(0, new BasicStroke(2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL, 1.0f, new float[] {10.0f}, 0.0f));
        plot.setRenderer(1, forecastRenderer);

        // Customising the plot background and gridlines
        plot.setBackgroundPaint(Color.white);
        plot.setDomainGridlinePaint(new Color(221, 221, 221));
        plot.setRangeGridlinePaint(new Color(221, 221, 221));

        // Rotate x-axis labels to make them more readable
        // CategoryAxis domainAxis = plot.getDomainAxis();
        // domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

        // Customising the range axis
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // Set tooltips for more interaction
        historicalRenderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator());
        forecastRenderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator());

        // Update the legend and titles for clarity
        LegendTitle legend = chart.getLegend();
        if (legend != null) {
            legend.setItemFont(new Font("Dialog", Font.PLAIN, 12));
        }
        chart.setTitle(new TextTitle("Booking Trend and Forecast", new Font("Dialog", Font.BOLD, 14)));

        // Add the chart to a panel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setMouseWheelEnabled(true);


        // Add the chart to a panel like before
        //chartPanel = new ChartPanel(chart);
        // ... rest of your ChartPanel setup ...

        // Add the ChartPanel to your GUI
        tabbedPane.addTab("Booking Forecast", chartPanel);
    }
}
