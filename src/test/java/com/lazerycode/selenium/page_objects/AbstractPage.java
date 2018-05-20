package com.lazerycode.selenium.page_objects;

import com.lazerycode.selenium.DriverBase;
import com.lazerycode.selenium.util.Query;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Łukasz on 20.05.2018.
 */
public class AbstractPage {
    final RemoteWebDriver driver = DriverBase.getDriver();

    protected Query languageSwitcher = new Query(By.className("language-switcher-locale-url"), driver);
    protected Query topMenu = new Query(By.xpath("//ul[contains(@class, 'menu full')]"), driver);


    public AbstractPage() throws Exception {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    public AbstractPage verifyPageLanguage(QualityMindsHomePage.Language lang) {
        List<WebElement> elements = languageSwitcher.findWebElement().findElements(By.tagName("li"));

        boolean active = false;
        for (WebElement element : elements) {
            if (element.getAttribute("class").contains("active")) {
                Assert.assertFalse(active, "Two or more languages are active");
                Assert.assertTrue(element.getAttribute("class").contains(lang.getLanguageSwitcher()));
                active = true;
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

    public AbstractPage switchToLanguage(Language lang) {
        List<WebElement> elements = languageSwitcher.findWebElement().findElements(By.tagName("li"));

        for (WebElement element : elements) {
            if (element.getAttribute("class").contains(lang.getLanguageSwitcher())) {
                element.click();
                break;
            }
        }
        return this;
    }

    public <T extends AbstractPage>T clickSubTab(SubTabs tab) throws Exception {
        WebElement aa = topMenu.findWebElement();
        List<WebElement> topMenuTabs = topMenu.findWebElement().findElements(By.xpath("./li"));

        for (WebElement searchedTab : topMenuTabs) {
            List<WebElement> internalTabList = searchedTab.findElements(By.xpath(".//li"));
            for (WebElement internalListElement : internalTabList) {
                if (internalListElement.findElement(By.xpath("./a")).getAttribute("href").contains(tab.getSubTabsName())) {
                    Actions actions = new Actions(driver);
                    actions.moveToElement(searchedTab.findElement(By.xpath("./a"))).moveToElement(internalListElement.findElement(By.xpath("./a"))).click().build().perform();
                    break;
                }
            }
        }

        switch (tab) {
            case MOBILE_TESTING:return (T) new MobileTestingPage();
        }

        return null;
    }

    public <T extends AbstractPage>T clickTab(Tabs tab) throws Exception {
        driver.findElement(By.linkText(tab.getTabName())).click();

        switch (tab) {
            case CAREER:return (T) new CareerTestingPage();
        }
        return null;
    }

    public enum Tabs {
        CAREER("Career");
        private String TabName;

        Tabs(String TabName) {
            this.TabName = TabName;
        }

        public String getTabName() {
            return TabName;
        }
    }

    public enum SubTabs {
        MOBILE_TESTING("mobile");
        private String SubTabName;

        SubTabs(String SubTabName) {
            this.SubTabName = SubTabName;
        }

        public String getSubTabsName() {
            return SubTabName;
        }
    }

    public enum Language {
        EN("en menu"),
        DE("de menu");

        private String languageSwitcher;

        Language(String languageSwitcher) {
            this.languageSwitcher = languageSwitcher;
        }

        public String getLanguageSwitcher() {
            return languageSwitcher;
        }
    }
}
