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
public class QualityMindsHomePage{

    private final RemoteWebDriver driver = DriverBase.getDriver();
    private final String homePageAddress = "http://www.qualityminds.de";

    private Query languageSwitcher = new Query(By.className("language-switcher-locale-url"), driver);

    public QualityMindsHomePage() throws Exception {
        driver.get(homePageAddress);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public QualityMindsHomePage verifyPageLanguage(Language lang) {
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

    public void switchToLanguage(Language lang) {
        List<WebElement> elements = languageSwitcher.findWebElement().findElements(By.tagName("li"));

        for (WebElement element : elements) {
            if (element.getAttribute("class").contains(lang.getLanguageSwitcher())) {
                element.click();
                break;
            }
        }
    }

    public enum Language {
        EN("en menu"),
        DE("de menu");

        private String languageSwitcher;

        Language(String languageSwitcher) {
            this.languageSwitcher=languageSwitcher;
        }

        public String getLanguageSwitcher() {
            return languageSwitcher;
        }
    }
}
