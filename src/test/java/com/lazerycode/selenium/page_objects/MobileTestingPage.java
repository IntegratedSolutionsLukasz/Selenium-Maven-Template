package com.lazerycode.selenium.page_objects;

import com.lazerycode.selenium.util.Query;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * Created by Łukasz on 20.05.2018.
 */
public class MobileTestingPage extends AbstractPage{
    protected Query moreButton = new Query(By.linkText("more"), driver);

    public MobileTestingPage() throws Exception {
    }

    public void verifyThatTabIsHighlighted(Tabs tab) {
        Assert.assertTrue(driver.findElement(By.linkText(tab.getTabName())).getAttribute("class").contains("active"),tab+" is active?");
    }

    public void clickOnMore() {
        Actions act = new Actions(driver);
        new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(moreButton.findWebElement()));
        act.moveToElement(moreButton.findWebElement()).click().build().perform();
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();" , (moreButton.findWebElement()));
    }

    public void verifyThatRonWernerIsContact() {
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Ron Werner | Team Lead Mobile Testing')]")).isDisplayed());
    }
}
