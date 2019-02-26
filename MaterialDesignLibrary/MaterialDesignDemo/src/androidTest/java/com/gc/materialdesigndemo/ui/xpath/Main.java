package com.gc.materialdesigndemo.ui.xpath;

import android.content.Context;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.util.Log;

import com.gc.materialdesigndemo.temp.AutomationCore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void demo(String myXpath){
        String filename = "demo.xml";
        FileOutputStream outputStream;
        try {
            outputStream = AutomationCore.getInstance().getTargetContext().openFileOutput(filename, Context.MODE_PRIVATE);
            AutomationCore.getInstance().getUiDevice().dumpWindowHierarchy(outputStream);
            File myfile = AutomationCore.getInstance().getTargetContext().getFilesDir();
            File my_xpath_file = new File(AutomationCore.getInstance().getTargetContext().getFilesDir().getAbsoluteFile() + "/demo.xml");
            BufferedReader br = new BufferedReader(new FileReader(my_xpath_file.getAbsolutePath()));
            String line = null;
            while ((line = br.readLine()) != null) {
                Log.d("fuck",line);
            }
            UiSelector object = XpathParser.parseMe(myXpath, my_xpath_file);
            AutomationCore.getInstance().getUiDevice().findObject(object).click();
            outputStream.close();
        } catch (FileNotFoundException e) {
            Log.d("shivam",e.getMessage());
        } catch (IOException e) {
            Log.d("shivam",e.getMessage());
        } catch (UiObjectNotFoundException e) {
            Log.d("NOCLICK","not able to click");
            e.printStackTrace();
        }

    }

}
