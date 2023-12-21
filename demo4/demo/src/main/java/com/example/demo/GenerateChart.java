package com.example.demo;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GenerateChart {
    public static JFreeChart createChart(List<DataModel> data) {
        // Example: Create a bar chart with team names and the count of interviews
        CategoryDataset dataset = createDataset(data);
        JFreeChart chart = ChartFactory.createBarChart(
                "Interviews by Team",
                "Team",
                "Number of Interviews",
                dataset,
                PlotOrientation.HORIZONTAL,
                true,
                true,
                false
        );
        return chart;
    }


    private static CategoryDataset createDataset(List<DataModel> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Count interviews by team
        Map<String, Long> teamInterviewCounts = data.stream()
                .collect(Collectors.groupingBy(DataModel::getTeam, Collectors.counting()));

        teamInterviewCounts.forEach((team, count) -> {
            dataset.addValue(count, "Interviews", team);
        });

        // Add other dataset entries as needed

        return dataset;
    }


    public static JFreeChart createTop3SkillsPieChart(List<DataModel> data) {
        // Example: Create a pie chart for the top 3 skills
        DefaultPieDataset dataset = createTop3SkillsDataset(data);
        JFreeChart chart = ChartFactory.createPieChart(
                "Top 3 Skills",
                dataset,
                true,
                true,
                false
        );

        // Customize chart properties as needed

        return chart;
    }

    private static DefaultPieDataset createTop3SkillsDataset(List<DataModel> data) {
        DefaultPieDataset dataset = new DefaultPieDataset();

        // Example: Count interviews for the top 3 skills
        Map<String, Long> skillsInterviewCounts = data.stream()
                .collect(Collectors.groupingBy(DataModel::getSkill, Collectors.counting()));

        // Sort and limit to the top 3 skills
        skillsInterviewCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(3)
                .forEach(entry -> dataset.setValue(entry.getKey(), entry.getValue()));

        // Add other dataset entries as needed

        return dataset;
    }


    public static JFreeChart createTop3PanelsPieChart(List<DataModel> data) {
        // Example: Create a pie chart for the top 3 panels
        DefaultPieDataset dataset = createTop3PanelsDataset(data);
        JFreeChart chart = ChartFactory.createPieChart(
                "Top 3 Panels",
                dataset,
                true,
                true,
                false
        );

        // Customize chart properties as needed

        return chart;
    }

    private static DefaultPieDataset createTop3PanelsDataset(List<DataModel> data) {
        DefaultPieDataset dataset = new DefaultPieDataset();

        // Example: Count interviews for the top 3 panels
        Map<String, Long> panelInterviewCounts = data.stream()
                .collect(Collectors.groupingBy(DataModel::getPanelName, Collectors.counting()));

        // Sort and limit to the top 3 panels
        panelInterviewCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(3)
                .forEach(entry -> dataset.setValue(entry.getKey(), entry.getValue()));

        // Add other dataset entries as needed

        return dataset;
    }








//    public static JFreeChart createPieChart(String title, DefaultPieDataset dataset) {
//        JFreeChart chart = ChartFactory.createPieChart(
//                title,   // chart title
//                dataset,          // data
//                true,             // include legend
//                true,
//                false);
//
//        int width = 640;   //  image width
//        int height = 480;  //  image height
//        return chart;
//    }


}