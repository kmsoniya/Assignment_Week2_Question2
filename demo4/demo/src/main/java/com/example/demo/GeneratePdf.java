package com.example.demo;



import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
//import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class GeneratePdf {
    public static void generatePdf(List<DataModel> data, String outputPath) throws IOException {
        try (OutputStream fos = new FileOutputStream(outputPath);
             PdfWriter writer = new PdfWriter(fos);
             PdfDocument pdfDocument = new PdfDocument(writer);
             Document document = new Document(pdfDocument)) {

            // Creating JFreeChart
           // JFreeChart chart =GenerateChart.createChart(data);  // normal defining
            // Create JFreeChart
            JFreeChart chart1 = GenerateChart.createChart(data);
            JFreeChart chart2 = GenerateChart.createTop3PanelsPieChart(data);
            JFreeChart chart3 = GenerateChart.createTop3SkillsPieChart(data);


            // Converting JFreeChart to BufferedImage
            int width = 700; // set width of the image
            int height = 500; // set height of the image
            BufferedImage bufferedImage1 = chart1.createBufferedImage(width, height);
            BufferedImage bufferedImage2 = chart2.createBufferedImage(width, height);
            BufferedImage bufferedImage3 = chart3.createBufferedImage(width, height);

            // Converting BufferedImage to iTextPDF Image
            Image itextImage1= new Image(ImageDataFactory.create(bufferedImage1, null));
            Image itextImage2 = new Image(ImageDataFactory.create(bufferedImage2, null));
            Image itextImage3 = new Image(ImageDataFactory.create(bufferedImage3, null));

            // Add content to the PDF
            document.add(itextImage1);
            document.add(itextImage2);
            document.add(itextImage3);
        }
    }
    /*public static void createPanelPdf(DefaultPieDataset dataset, String outputPath) {
        try (OutputStream fos = new FileOutputStream(outputPath);
             PdfWriter writer = new PdfWriter(fos);
             PdfDocument pdfDocument = new PdfDocument(writer);
             Document document = new Document(pdfDocument)) {

            // Create JFreeChart
            JFreeChart chart = GenerateChart.createPieChart("Top 3 Panels",dataset);
            // Convert JFreeChart to BufferedImage
            int width = 600; // set width of the image
            int height = 400; // set height of the image
            BufferedImage bufferedImage = chart.createBufferedImage(width, height);

            // Convert BufferedImage to iTextPDF Image
            Image itextImage = new Image(ImageDataFactory.create(bufferedImage, null));

            // Add content to the PDF
            document.add(itextImage);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
*/
   /* public static void createSkillsPdf(DefaultPieDataset dataset, String outputPath) {
        try (OutputStream fos = new FileOutputStream(outputPath);
             PdfWriter writer = new PdfWriter(fos);
             PdfDocument pdfDocument = new PdfDocument(writer);
             Document document = new Document(pdfDocument)) {

            // Create JFreeChart
            JFreeChart chart = GenerateChart.createPieChart("Top 3 Skills",dataset);
            // Convert JFreeChart to BufferedImage
            int width = 600; // set width of the image
            int height = 400; // set height of the image
            BufferedImage bufferedImage = chart.createBufferedImage(width, height);

            // Convert BufferedImage to iTextPDF Image
            Image itextImage = new Image(ImageDataFactory.create(bufferedImage, null));

            // Add content to the PDF
            document.add(itextImage);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/
}