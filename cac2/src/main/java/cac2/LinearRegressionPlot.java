package cac2;

import javax.swing.*;
import java.awt.*;

public class LinearRegressionPlot extends JPanel {

    private double[] heights;
    private double[] weights;
    private double slope;
    private double intercept;

    public LinearRegressionPlot(double[] heights, double[] weights, double slope, double intercept) {
        this.heights = heights;
        this.weights = weights;
        this.slope = slope;
        this.intercept = intercept;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Set rendering hints for better quality
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Set background color
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Set plot area
        int padding = 50;
        int plotWidth = getWidth() - 2 * padding;
        int plotHeight = getHeight() - 2 * padding;

        // Draw x and y axes
        g2d.setColor(Color.BLACK);
        g2d.drawLine(padding, padding, padding, getHeight() - padding); // y-axis
        g2d.drawLine(padding, getHeight() - padding, getWidth() - padding, getHeight() - padding); // x-axis

        // Draw actual data points
        g2d.setColor(Color.BLUE);
        for (int i = 0; i < heights.length; i++) {
            int x = padding + (int) ((heights[i] - min(heights)) / (max(heights) - min(heights)) * plotWidth);
            int y = getHeight() - padding - (int) ((weights[i] - min(weights)) / (max(weights) - min(weights)) * plotHeight);
            g2d.fillOval(x - 2, y - 2, 4, 4);
        }

        // Draw regression line
        g2d.setColor(Color.RED);
        int x1 = padding;
        int y1 = getHeight() - padding - (int) ((slope * min(heights) + intercept - min(weights)) / (max(weights) - min(weights)) * plotHeight);
        int x2 = getWidth() - padding;
        int y2 = getHeight() - padding - (int) ((slope * max(heights) + intercept - min(weights)) / (max(weights) - min(weights)) * plotHeight);
        g2d.drawLine(x1, y1, x2, y2);
    }

    private double min(double[] values) {
        double min = Double.MAX_VALUE;
        for (double value : values) {
            if (value < min) {
                min = value;
            }
        }
        return min;
    }

    private double max(double[] values) {
        double max = Double.MIN_VALUE;
        for (double value : values) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        // Sample data for demonstration
        double[] heights = {190.5,185.42,177.8,167.64,175.26,182.88,198.12,165.1,160.02,187.96,170.18,172.72,193.04,180.34, 162.56,200.66,195.58,208.28,157.48,210.82,203.2,152.4,154.94,226.06,205.74,218.44}; // Replacing with actual height data
        double[] weights = {92.99, 83.91, 97.98, 61.23, 70.31, 120.2, 118.39, 51.26, 65.77, 87.09, 77.11, 56.7, 106.59, 108.86, 127.01, 110.22, 104.33, 52.16, 69.85, 113.4, 83.01, 90.72, 73.03, 72.57, 107.95, 76.2, 140.61, 63.05, 102.06, 119.75, 102.97, 115.67, 117.93, 90.26, 112.04, 158.76, 79.38, 82.1, 68.04, 109.32, 116.12, 95.25, 156.49, 105.23, 81.65, 74.84, 104.78, 111.13, 106.14, 99.79, 109.77, 97.07, 118.84, 100.24, 115.21, 142.88, 112.94, 97.52, 111.58, 99.34, 117.03, 86.18, 107.05, 114.76, 112.49, 120.66, 88.45, 174.63, 119.29, 58.97, 136.08, 113.85, 124.74, 122.47, 103.42, 88, 102.51, 87.54, 80.74, 116.57, 151.95, 146.51, 67.59, 93.89, 64.86, 108.41, 103.87, 129.73, 195.04, 85.28, 181.44, 98.43, 47.63, 101.6, 176.9, 133.81, 88.9, 89.36, 75.75, 131.54, 110.68, 96.16, 117.48, 84.37, 145.15, 185.97, 149.69, 129.27, 107.5, 105.69, 349.27, 89.81};     // Replacing with actual weight data

        // Create instance of LinearRegression
        LinearRegression model = new LinearRegression();

        // Train the model
        model.train(heights, weights);

        // Make predictions
        double newHeight = 190; // Replace with the height for which you want to predict weight
        double predictedWeight = model.predict(newHeight);
        System.out.println("Predicted weight for a height of 190 cm: " + predictedWeight);

        // Create plot panel
        JFrame frame = new JFrame("Linear Regression Plot");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        LinearRegressionPlot plot = new LinearRegressionPlot(heights, weights, model.getSlope(), model.getIntercept());
        frame.add(plot);
        frame.setVisible(true);
    }
}
