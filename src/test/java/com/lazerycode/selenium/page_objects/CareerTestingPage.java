package com.lazerycode.selenium.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Łukasz on 21.05.2018.
 */
public class CareerTestingPage extends AbstractPage {

    public CareerTestingPage() throws Exception {
    }

    public void clickApllyNow() {
        WebElement applyNow = driver.findElement(By.linkText("Apply now!"));

        Actions act = new Actions(driver);
        new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(applyNow));
        act.moveToElement(applyNow).click().build().perform();
    }
}
