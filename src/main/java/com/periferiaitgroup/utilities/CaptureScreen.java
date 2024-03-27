package com.periferiaitgroup.utilities;

import com.epam.healenium.SelfHealingDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class CaptureScreen {
    public static String captureScreen(WebDriver driver, File rutaCarpeta) {
        String hora = HoraSistema.currentDate("HH-mm-ss");

        WebDriver delegatedDriver = ((SelfHealingDriver) driver).getDelegate();

        File srcFile = ((TakesScreenshot) delegatedDriver).getScreenshotAs(OutputType.FILE);

        String pathImage = rutaCarpeta + "\\" + hora + ".png";

        try {
            FileUtils.copyFile(srcFile, new File(pathImage));
        }catch (IOException e){
            System.out.println("Falla al tomar la captura de pantalla\n" + e);
        }

        return new File(pathImage).toString();
    }
}
