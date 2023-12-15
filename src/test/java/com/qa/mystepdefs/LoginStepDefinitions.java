package com.qa.mystepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import pages.LoginPage;

public class LoginStepDefinitions {

    private WebDriver driver;
    private LoginPage loginPage;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\kritika.kaushik\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


    @Given("I am on the ORO login page")
    public void i_am_on_the_login_page() {
        driver.get("https://oro-stag5.diversitech.com/admin/user/login");
        loginPage = new LoginPage(driver);
        driver.manage().window().maximize();

    }

    @Given("I have entered a valid username and password")
    public void i_have_entered_a_valid_username_and_password() throws InterruptedException {
        loginPage.enterEmail("deepak.barnwal@globallogic.com");
        loginPage.enterPassword("Password123");
    }

    @Given("I have entered invalid {string} and {string}")
    public void i_have_entered_invalid_and(String username, String password) throws InterruptedException {
        loginPage.enterEmail(username);
        loginPage.enterPassword(password);
    }

    @When("I click on the login button")
    public void i_click_on_the_login_button() throws InterruptedException {
        loginPage.clickLoginButton();
    }

    @Then("I should be logged in successfully")
    public void i_should_be_logged_in_successfully() throws InterruptedException {
        Assert.assertEquals(loginPage.usernameDisplayed(), true);
    }

    @When("I click on the Customer 360 link")
    public void i_click_on_the_Customer_360_link() throws InterruptedException {
        loginPage.clickCustomer360();

    }


    @Then("I click on Research campaign")
    public void iClickOnResearchCampagian() throws InterruptedException {
        loginPage.clickonRC();


    }

    @Then("I have entered PO number")
    public void iHaveEnteredPONumber() throws InterruptedException {
        loginPage.EnterPOnumber("P1589645");

    }

    @Then("click on search")
    public void clickOnSearch() throws InterruptedException {
        loginPage.clickOnSearch(); {
        }
    }

    @Then("expand the result")
    public void expandTheResult() throws InterruptedException {
        loginPage.expandTheResult();
    }

    @Then("click on Advanced search")
    public void clickOnAdvancedSearch() throws InterruptedException {
        loginPage.clickOnAdvancedSearch();
    }

    @Then("I have entered customer id")
    public void iHaveEnteredCustomerId() throws InterruptedException {
        loginPage.iHaveEnteredCustomerId("82917");
    }

    @Then("I have entered customername")
    public void iHaveEnteredCustomername() throws InterruptedException {
        loginPage.iHaveEnteredCustomername();
    }

    @Then("verify that bill of lading is getting displayed")
    public void verifyThatBillOfLadingIsGettingDisplayed() throws InterruptedException{
        loginPage.verifyThatBillOfLadingIsGettingDisplayed();
    }

    @Then("I have entered Invoice number")
    public void iHaveEnteredInvoiceNumber() throws InterruptedException {
        loginPage.iHaveEnteredInvoiceNumber("2989614");
    }

    @Then("I have entered shipment number")
    public void iHaveEnteredShipmentNumber() throws InterruptedException{
        loginPage.iHaveEnteredShipmentNumber("3614835");
    }

    @Then("I have entered Order number")
    public void iHaveEnteredOrderNumber() throws InterruptedException {
        loginPage.iHaveEnteredOrderNumber("2793722");
    }


    @Then("I have entered tracking number")
    public void iHaveEnteredTrackingNumber() throws InterruptedException {
        loginPage.iHaveEnteredTrackingNumber("640624254742");
    }

    @Then("verify that invoice number is getting displayed")
    public void verifyThatInvoiceNumberIsGettingDisplayed()throws InterruptedException {
        loginPage.verifyThatInvoiceNumberIsGettingDisplayed();
    }

    @Then("verify that shipment number is getting displayed")
    public void verifyThatShipmentNumberIsGettingDisplayed() throws InterruptedException {
        loginPage.verifyThatBillOfLadingIsGettingDisplayed();

    }

    @Then("verify that Order number is getting displayed")
    public void verifyThatOrderNumberIsGettingDisplayed() throws InterruptedException{
        loginPage.verifyThatOrderNumberIsGettingDisplayed();
    }

    @Then("verify that tracking number is getting displayed")
    public void verifyThatTrackingNumberIsGettingDisplayed() throws InterruptedException{
        loginPage.verifyThatBillOfLadingIsGettingDisplayed();
    }

    @And("enter the date into the calendar")
    public void enterTheDateIntoTheCalendar() throws InterruptedException{
        loginPage.enterTheDateIntoTheCalendar("2020-12-01");
    }

    @Then("click on clear search button")
    public void clickOnClearSearchButton() throws InterruptedException {
        loginPage.clickOnClearSearchButton();

    }

    @Then("verify that all the fields get reset")
    public void verifyThatAllTheFieldsGetReset() throws InterruptedException {
        loginPage.verifyThatAllTheFieldsGetReset();
    }

    @And("click on search on advanced search")
    public void clickOnSearchOnAdvancedSearch()throws InterruptedException  {
        loginPage.clickOnSearchOnAdvancedSearch();{
    }
}

    @Then("click on customers")
    public void clickOnCustomers() throws InterruptedException{
        loginPage.clickOnCustomers();
    }

    @Then("expand the shipment column")
    public void expandTheShipmentColumn() throws InterruptedException {
        loginPage.expandTheShipmentColumn();
    }

    @Then("verify that bill of lading under shipment is getting displayed")
    public void verifyThatBillOfLadingUnderShipmentIsGettingDisplayed() throws InterruptedException{
        loginPage.verifyThatBillOfLadingUnderShipmentIsGettingDisplayed();
    }
}
