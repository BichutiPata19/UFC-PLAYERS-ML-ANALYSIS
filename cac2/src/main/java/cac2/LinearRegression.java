package cac2;

public class LinearRegression {
    private double slope;
    private double intercept;

    public void train(double[] heights, double[] weights) {
        // Calculate the mean of heights and weights
        double meanHeight = calculateMean(heights);
        double meanWeight = calculateMean(weights);

        // Calculate the slope (m) and intercept (b) using the formula for simple linear regression
        double numerator = 0;
        double denominator = 0;
        for (int i = 0; i < heights.length; i++) {
            numerator += (heights[i] - meanHeight) * (weights[i] - meanWeight);
            denominator += Math.pow((heights[i] - meanHeight), 2);
        }
        slope = numerator / denominator;
        intercept = meanWeight - slope * meanHeight;
    }

    public double predict(double height) {
        // Use the trained model to predict weight for a given height
        return slope * height + intercept;
    }

    public double getSlope() {
        return slope;
    }

    public double getIntercept() {
        return intercept;
    }

    private double calculateMean(double[] values) {
        // Calculate the mean of an array of values
        double sum = 0;
        for (double value : values) {
            sum += value;
        }
        return sum / values.length;
    }
}
