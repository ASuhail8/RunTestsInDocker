package com.example;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class S3Upload {

    public static void main(String[] args) {

        /*System.out.println("uploading to S3 Started");
        // Initialize Amazon S3 client
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().build();

        // Specify the S3 bucket name and the local directory where your test result files are stored
        String bucketName = "my-app-test-results";
        String localDirectory = "reports/index.html";

        // Upload files to S3
        s3Client.putObject(new PutObjectRequest(bucketName, getCurrentDateAndTime() + "-my-app-test-reports/index.html", new File(localDirectory)));
        System.out.println("uploading to S3 completed");*/

        uploadAllFilesToS3();

    }

    public static String getCurrentDateAndTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM HH:mm");
        return currentDateTime.format(formatter);
    }


    public static void uploadAllFilesToS3() {
        // Specify your AWS region
        Regions clientRegion = Regions.US_EAST_1; // Change to your desired region

        // Initialize Amazon S3 client with your AWS credentials
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(clientRegion)
               // .withCredentials(new ProfileCredentialsProvider("your-aws-profile")) // Change to your AWS profile
                .build();

        // Specify the S3 bucket name and the local directory where your test result files are stored
        String bucketName = "my-app-test-results";
        String localDirectory = "reports";

        try {
            // List all files under the local directory
            File dir = new File(localDirectory);
            File[] files = dir.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        // Upload each file to S3
                        String key = getCurrentDateAndTime()+"-ecommerce-test-results/" + file.getName(); // S3 key (path) for the file
                        s3Client.putObject(new PutObjectRequest(bucketName, key, file));
                        System.out.println("Uploaded file: " + file.getName());
                    }
                }
            }
        } catch (AmazonServiceException e) {
            // Catch and handle Amazon S3 service exceptions
            e.printStackTrace();
        } catch (SdkClientException e) {
            // Catch and handle SDK client exceptions
            e.printStackTrace();
        }
    }
}

