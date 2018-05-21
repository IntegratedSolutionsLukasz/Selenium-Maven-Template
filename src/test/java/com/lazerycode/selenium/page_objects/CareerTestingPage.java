package com.lazerycode.selenium.page_objects;

import com.lazerycode.selenium.util.Query;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Łukasz on 21.05.2018.
 */
public class CareerTestingPage extends AbstractPage {

    protected Query inputFile = new Query(By.id("edit-submitted-right-files-0-upload"), driver);
    protected Query uploadButton = new Query(By.xpath("//div[contains(@class, 'button-container clearfix')]/input"), driver);


    public CareerTestingPage() throws Exception {
    }

    public void clickApllyNow() {
        WebElement applyNow = driver.findElement(By.linkText("Apply now!"));

        Actions act = new Actions(driver);
        new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(applyNow));
        act.moveToElement(applyNow).click().build().perform();
    }

    public void uploadFile(String path) {
        inputFile.findWebElement().sendKeys(path);uploadButton.findWebElement().submit();
    }
}
