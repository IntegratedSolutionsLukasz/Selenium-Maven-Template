package com.lazerycode.selenium.page_objects;

import com.lazerycode.selenium.DriverBase;
import com.lazerycode.selenium.util.Query;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Łukasz on 20.05.2018.
 */
public class QualityMindsHomePage extends AbstractPage{


    private final String homePageAddress = "http://www.qualityminds.de";
    protected Query cookiesConsent = new Query(By.linkText("Alles klar!"), driver);

    public QualityMindsHomePage() throws Exception {
        driver.get(homePageAddress);
        cookiesConsent.findWebElement().click();
    }




}
