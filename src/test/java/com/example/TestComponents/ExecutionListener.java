package com.example.TestComponents;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.Utils.PropertyClass;
import org.testng.IExecutionListener;

import java.io.File;

public class ExecutionListener implements IExecutionListener {

    /**
     * Invoked once all the suites have been run.
     */
    public void onExecutionFinish() {
        uploadTestResultsToS3("my-app-test-results","reports/index.html");
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
}
