package org.alfresco.consulting.tools.content.creator.agents;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.Random;

import org.alfresco.consulting.locator.PropertiesLocator;
import org.alfresco.consulting.tools.content.creator.BulkImportManifestCreator;
import org.alfresco.consulting.words.RandomWords;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xslf.usermodel.*;

import javax.imageio.ImageIO;

public class JpgAgent extends Thread  {
    /**
     * @param args
     * @throws java.io.IOException
     */

    private static Properties props = PropertiesLocator.getProperties("alfresco-consulting.properties");
    private static String files_deployment_location = props.getProperty("files_deployment_location");
    private static String images_location = props.getProperty("images_location");

    public static void main(String[] args) throws IOException {

        RandomWords.init();
        Calendar cal = Calendar.getInstance();

        try {
            File imagesFolder = new File(images_location);
            File[] files =   imagesFolder.listFiles();
            int size = files.length;
            Random rand = new Random();
            int number = rand.nextInt(size);
            File randomImage = files[number];


            //InputStream is =new URL("http://lorempixel.com/g/800/600/").openStream();
            InputStream is = new FileInputStream(randomImage);
            FileOutputStream outStream = null;


            String fileName =  cal.getTimeInMillis() +"_JpegImageSSMR.jpg";
            String filePath = files_deployment_location + "/" + fileName;
            // Creating the metadata file
            BulkImportManifestCreator.createBulkManifest(fileName);
            FileOutputStream out = new FileOutputStream(filePath);
            IOUtils.copy(is,out);
            out.close();





        } catch (Exception e) {
            System.out.println("First Catch");
            e.printStackTrace();
        }


    }
}