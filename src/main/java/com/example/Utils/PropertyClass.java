package com.example.Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyClass {

    public static String getProperty(String key) {

        Properties prop = new Properties();
        InputStream fis = null;
        try {
            fis = new FileInputStream("src/test/resources/some.properties");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            prop.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop.getProperty(key);

    }

}
