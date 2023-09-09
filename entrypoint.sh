#!/bin/bash

# Run your Selenium tests
java -Dbrowser="$BROWSER" -jar my-app-1.0-SNAPSHOT-fat-tests.jar -testjar my-app-1.0-SNAPSHOT-fat-tests.jar -xmlpathinjar "$MODULE"

# Get the current date and time in a specific format
current_time=$(date "+%Y-%m-%d %H:%M:%S")

# Upload test result files to S3
aws s3 cp reports s3://my-app-bucket-from-dockerfile/test-results-"$current_time" --recursive

# Optionally, clean up files or perform any other post-processing steps
