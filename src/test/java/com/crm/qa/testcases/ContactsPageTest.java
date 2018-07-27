//author -shermila

package com.crm.qa.testcases;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ContactsPageTest extends TestBase {
    LoginPage loginPage;
    HomePage homePage;
    TestUtil testUtil;
    ContactsPage contactsPage;
    String sheetName = "contacts";

    public ContactsPageTest() {
    }

    @BeforeMethod
    public void setUp() {
        intilization();
        this.testUtil = new TestUtil();
        this.contactsPage = new ContactsPage();
        this.loginPage = new LoginPage();
        this.homePage = this.loginPage.Login(prop.getProperty("username"), prop.getProperty("password"));
        this.testUtil.switchToFrame();
        this.contactsPage = this.homePage.clickOnContactsLink();
    }

    @Test(
        priority = 1
    )
    public void verifyContactsPageLabel() {
        Assert.assertTrue(this.contactsPage.verifyContactsLabel(), "contacts label is missing on the page");
    }

    @Test(
        priority = 2
    )
    public void selectSingleContactsTest() {
        this.contactsPage.selectContactsByName("test2 test2");
    }

    @Test(
        priority = 3
    )
    public void selectMultipleContactsTest() {
        this.contactsPage.selectContactsByName("test2 test2");
        this.contactsPage.selectContactsByName("ui uiiii");
    }

    @DataProvider
    public Object[][] getCRMTestData() {
        Object[][] data = TestUtil.getTestData(this.sheetName);
        return data;
    }

    @Test(
        priority = 4,
        dataProvider = "getCRMTestData"
    )
    public void validateCreateNewContact(String title, String firstName, String lastName, String company) {
        this.homePage.clickOnNewContactLink();
        this.contactsPage.createNewContact(title, firstName, lastName, company);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
