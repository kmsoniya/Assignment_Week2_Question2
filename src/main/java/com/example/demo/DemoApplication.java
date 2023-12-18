package com.example.demo;

import org.jfree.chart.JFreeChart;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		try {
			//  1: Creation of table in the database
			DatabaseManager.createTable();

			//  2: Reading from Excel data
			List<DataModel> data = ExcelReader.readExcel("C:\\Users\\km.soniya\\Downloads\\demo4\\demo\\src\\main\\resources\\AccoliteInterview.xlsx");

			//  3: Insertion of data into the database
			DatabaseManager.insertData(data);
			DatabaseManager.teamWithMaxInterviews();
			DatabaseManager.teamWithMinInterviews();
			DatabaseManager.top3Skills();
			DatabaseManager.top3Panels();
			DatabaseManager.skillsInPeakTime();

			// 4: Generating charts
			JFreeChart chart = GenerateChart.createChart(data);

			// 5: Generating PDF and embed charts
			GeneratePdf.generatePdf(data, "Output.pdf");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
