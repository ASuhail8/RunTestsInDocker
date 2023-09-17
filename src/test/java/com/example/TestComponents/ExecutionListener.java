package com.example.TestComponents;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.S3Upload;
import com.example.Utils.PropertyClass;
import org.testng.IExecutionListener;

import java.io.File;

import static com.example.S3Upload.getCurrentDateAndTime;

public class ExecutionListener implements IExecutionListener {

    /**
     * Invoked once all the suites have been run.
     */
    public void onExecutionFinish() {
        //uploadTestResultsToS3("my-app-test-results","reports/index.html");
        uploadAllFilesToS3();
    }

    public void uploadTestResultsToS3(String bucketName, String localDirectory) {
        String uploadToS3 = System.getProperty("uploadToS3") != null ? System.getProperty("uploadToS3") : PropertyClass.getProperty("uploadToS3");
        if (uploadToS3.equalsIgnoreCase("true")) {
            System.out.println("uploading to S3 Started");
            // Initialize Amazon S3 client
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard().build();
            // Upload files to S3
            s3Client.putObject(new PutObjectRequest(bucketName, BaseTest.getCurrentDateAndTime() + "-my-app-test-reports/index.html", new File(localDirectory)));
            System.out.println("uploading to S3 completed");
        }
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
                        String key = getCurrentDateAndTime() + "-ecommerce-test-results/" + file.getName(); // S3 key (path) for the file
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
