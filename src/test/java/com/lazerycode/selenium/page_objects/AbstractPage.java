package com.lazerycode.selenium.page_objects;

import com.lazerycode.selenium.DriverBase;
import com.lazerycode.selenium.util.Query;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

/**
 * Created by Łukasz on 20.05.2018.
 */
public class AbstractPage {
    final RemoteWebDriver driver = DriverBase.getDriver();

    protected Query languageSwitcher = new Query(By.className("language-switcher-locale-url"), driver);

    public AbstractPage() throws Exception {
    }

    public AbstractPage verifyPageLanguage(QualityMindsHomePage.Language lang) {
        List<WebElement> elements = languageSwitcher.findWebElement().findElements(By.tagName("li"));

        boolean active=false;
        for (WebElement element : elements) {
            if (element.getAttribute("class").contains("active")) {
                Assert.assertFalse(active,"Two or more languages are active");
                Assert.assertTrue(element.getAttribute("class").contains(lang.getLanguageSwitcher()));
                active=true;
            }
        }

        return this;
    }

    public void waitForPageLoaded() {
        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
        try {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }

    public MobileTestingPage clickTab(Tabs tab) throws Exception {

        return new MobileTestingPage();
    }

    public enum Tabs {
        MOBILE_TESTING("Mobile Testing");
        private String tabName;

        Tabs(String tabName) {
            this.tabName = tabName;
        }

        public String getTabName() {
            return tabName;
        }
    }
}
