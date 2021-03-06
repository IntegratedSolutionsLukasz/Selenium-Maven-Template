package com.lazerycode.selenium.tests;

import com.lazerycode.selenium.DriverBase;
import com.lazerycode.selenium.page_objects.CareerTestingPage;
import com.lazerycode.selenium.page_objects.MobileTestingPage;
import com.lazerycode.selenium.page_objects.QualityMindsHomePage;
import org.testng.annotations.Test;

import static com.lazerycode.selenium.page_objects.AbstractPage.SubTabs.MOBILE_TESTING;
import static com.lazerycode.selenium.page_objects.AbstractPage.Tabs.CAREER;
import static com.lazerycode.selenium.page_objects.AbstractPage.Tabs.SERVICES;

public class GoogleExampleIT extends DriverBase {

    //drivers will be downloaded automatically thanks to maven and also path will be setup
    //reporting is done by TestNG. You need to setup your IDE
    @Test
    public void TestCase1() throws Exception {
        QualityMindsHomePage qualityMindsHomePage = new QualityMindsHomePage();
        qualityMindsHomePage.verifyPageLanguage(QualityMindsHomePage.Language.DE);
        qualityMindsHomePage.switchToLanguage(QualityMindsHomePage.Language.EN)
                            .verifyPageLanguage(QualityMindsHomePage.Language.EN);
    }

    @Test
    public void TestCase2() throws Exception {
        QualityMindsHomePage qualityMindsHomePage = new QualityMindsHomePage();
        qualityMindsHomePage.switchToLanguage(QualityMindsHomePage.Language.EN);
        qualityMindsHomePage.verifyPageLanguage(QualityMindsHomePage.Language.EN);

        MobileTestingPage mobileTesting = qualityMindsHomePage.clickSubTab(MOBILE_TESTING);
        mobileTesting.verifyThatTabIsHighlighted(SERVICES);
        mobileTesting.clickOnMore();
        mobileTesting.verifyThatRonWernerIsContact();
    }

    @Test
    public void TestCase3() throws Exception {
        QualityMindsHomePage qualityMindsHomePage = new QualityMindsHomePage();
        qualityMindsHomePage.switchToLanguage(QualityMindsHomePage.Language.EN);
        qualityMindsHomePage.verifyPageLanguage(QualityMindsHomePage.Language.EN);

        CareerTestingPage careerTestingPage = qualityMindsHomePage.clickTab(CAREER);
        careerTestingPage.clickApllyNow();
        careerTestingPage.uploadFile("C:\\Users\\lukasz.wiszowaty\\Downloads\\UsefulAutomatedSOftwareTestingMetrics.pdf");

        careerTestingPage.switchToLanguage(QualityMindsHomePage.Language.DE);
        careerTestingPage.checkCurrentURL("http://www.qualityminds.de/content/bewerbung");
    }
}