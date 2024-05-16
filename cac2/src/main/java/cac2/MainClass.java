package cac2;

// import java.util.Arrays;
import java.util.List;

public class MainClass {
    public static void main(String[] args) {
        // Path to your CSV file
        String csvFilePath = "C://Users//Siddhartha//Desktop//Data Science//Datasets (Others)//UFC//UFC Dataset//cac2//src//main//resources//ufc-fighters-statistics.csv";

        // Instantiate DataLoader and load the dataset
        DataLoader dataLoader = new DataLoader();
        List<String[]> dataset = dataLoader.loadDataset(csvFilePath);

        // Proceed with further data processing and analysis
        if (dataset != null) {
            // Perform analysis or machine learning tasks using the loaded dataset
        } else {
            System.out.println("Failed to load dataset.");
        }
    }
}
