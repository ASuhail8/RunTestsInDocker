package com.example.Utils;

import java.io.*;
import java.util.Properties;

public class PropertyClass {

    public static String getProperty(String key) {
        //String path = System.getProperty("path");
        Properties prop = new Properties();
        InputStream inputStream = PropertyClass.class.getClassLoader().getResourceAsStream("config/some.properties");
        try {
            prop.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop.getProperty(key);

    }

    public void readResources(){
        InputStream inputStream = getClass().getResourceAsStream("test-resources/some.properties");

        if (inputStream != null) {
            // Read the resource content
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while (true) {
                try {
                    if ((line = reader.readLine()) == null) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                // Process the content as needed
                System.out.println(line);
            }
        } else {
            // Resource not found
            System.err.println("Resource not found.");
        }

    }

}
