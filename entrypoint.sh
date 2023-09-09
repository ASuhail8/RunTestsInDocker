#!/bin/bash

# Run your Selenium tests
java -Dbrowser="$BROWSER" -jar my-app-1.0-SNAPSHOT-fat-tests.jar -testjar my-app-1.0-SNAPSHOT-fat-tests.jar -xmlpathinjar "$MODULE"

# Upload test result files to S3
aws s3 cp reports s3://my-app-bucket-from-dockerfile/test-results --recursive

# Optionally, clean up files or perform any other post-processing steps
