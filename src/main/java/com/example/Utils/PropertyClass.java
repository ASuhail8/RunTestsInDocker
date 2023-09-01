package com.example.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyClass {

    public static String getProperty(String key) throws IOException {

        FileInputStream fis = new FileInputStream("src/test/resources/some.properties");
        Properties prop = new Properties();
        prop.load(fis);
        return prop.getProperty(key);

    }

}
