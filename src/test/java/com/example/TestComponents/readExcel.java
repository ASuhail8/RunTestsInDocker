package com.example.TestComponents;

import com.example.Utils.PropertyClass;

import java.io.IOException;
import java.util.ArrayList;

public class readExcel {

    public static void main(String[] args) throws IOException {
        ArrayList<String> al = PropertyClass.getTestcaseNames("Sheet1");
        System.out.println(al);
   }
}
