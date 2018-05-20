package com.lazerycode.selenium.tests;

import com.lazerycode.selenium.DriverBase;
import com.lazerycode.selenium.page_objects.AbstractPage;
import com.lazerycode.selenium.page_objects.GoogleHomePage;
import com.lazerycode.selenium.page_objects.MobileTestingPage;
import com.lazerycode.selenium.page_objects.QualityMindsHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import static com.lazerycode.selenium.page_objects.AbstractPage.Tabs.MOBILE_TESTING;

public class GoogleExampleIT extends DriverBase {



    @Test
    public void googleCheeseExample() throws Exception {
        // Create a new WebDriver instance
        // Notice that the remainder of the code relies on the interface,
        // not the implementation.
        WebDriver driver = getDriver();

        // And now use this to visit Google
        driver.get("http://www.google.com");
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");

        GoogleHomePage googleHomePage = new GoogleHomePage();

        // Check the title of the page
        System.out.println("Page title is: " + driver.getTitle());

        googleHomePage.enterSearchTerm("Cheese")
                .submitSearch();

        // Google's search is rendered dynamically with JavaScript.
        // Wait for the page to load, timeout after 10 seconds
        WebDriverWait wait = new WebDriverWait(driver, 10, 100);


        // Should see: "cheese! - Google Search"
        System.out.println("Page title is: " + driver.getTitle());
    }

    @Test
    public void googleMilkExample() throws Exception {
        // Create a new WebDriver instance
        // Notice that the remainder of the code relies on the interface,
        // not the implementation.
        WebDriver driver = getDriver();

        // And now use this to visit Google
        driver.get("http://www.google.com");
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");

        GoogleHomePage googleHomePage = new GoogleHomePage();

        // Check the title of the page
        System.out.println("Page title is: " + driver.getTitle());

        googleHomePage.enterSearchTerm("Milk")
                .submitSearch();

        // Google's search is rendered dynamically with JavaScript.
        // Wait for the page to load, timeout after 10 seconds
        WebDriverWait wait = new WebDriverWait(driver, 10, 100);

        // Should see: "cheese! - Google Search"
        System.out.println("Page title is: " + driver.getTitle());
    }

    @Test
    public void TestCase1() throws Exception {
        QualityMindsHomePage qualityMindsHomePage = new QualityMindsHomePage();
        qualityMindsHomePage.verifyPageLanguage(QualityMindsHomePage.Language.DE);
        qualityMindsHomePage.switchToLanguage(QualityMindsHomePage.Language.EN);
        qualityMindsHomePage.verifyPageLanguage(QualityMindsHomePage.Language.EN);
    }

    @Test
    public void TestCase2() throws Exception {
        QualityMindsHomePage qualityMindsHomePage = new QualityMindsHomePage();
        qualityMindsHomePage.switchToLanguage(QualityMindsHomePage.Language.EN);
        qualityMindsHomePage.verifyPageLanguage(QualityMindsHomePage.Language.EN);

        MobileTestingPage mobileTesting = qualityMindsHomePage.clickTab(MOBILE_TESTING);
    }
}