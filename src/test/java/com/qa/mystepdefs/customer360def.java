package com.qa.mystepdefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.Customer360page;
import pages.LoginPage;

public class customer360def {

    private WebDriver driver;
    private LoginPage loginPage;

    private Customer360page customer;

    public customer360def(Customer360page customer) {
        this.customer = customer;
    }


    @Then("Customer Page is displayed")
    public void customer_page_displayed() {
//        customer360=new Customer360(driver);
        org.junit.Assert.assertEquals(customer.customer360_homePage_displayed(), true);

    }

    @When("I enter PO number")
    public void i_enter_PO_number() throws InterruptedException {
        customer.searchWithCompanyName360();
        customer.scrollTillShipment();
        customer.enterPONUmber360();

    }

    @Then("PO Number details are displayed")
    public void po_Number_is_displayed() {
        Assert.assertEquals(customer.poResultDisplayed360(), true);
    }

    @When("I enter Order number")
    public void i_enter_order_number() throws InterruptedException {
        customer.searchWithCompanyName360();
        customer.scrollTillShipment();
        customer.enterOrderNUmber360();

    }

    @Then("Order Number is displayed in grid")
    public void order_Number_is_displayed() {
        Assert.assertEquals(customer.orderResultDisplayed360(), true);
        Assert.assertEquals(customer.verifyDataInGrid("2292357"), true);
    }

    @When("I enter Shipment number")
    public void i_enter_shipment_number() throws InterruptedException {
        customer.searchWithCompanyName360();
        customer.scrollTillShipment();
        customer.enterShipmentNUmber360();

    }

    @Then("Shipment Number is displayed in grid")
    public void shipment_Number_is_displayed() {
        Assert.assertEquals(customer.verifyDataInGrid("2570934"), true);
    }

    @When("I enter Item number")
    public void i_enter_item_number() throws InterruptedException {
        customer.searchWithCompanyName360();
        customer.scrollTillShipment();
        customer.enterItemNUmber360();

    }

    @Then("Item Number is displayed in grid")
    public void item_Number_is_displayed() {
        Assert.assertEquals(customer.verifyDataInGrid("2170796"), true);
    }

    @When("I enter Company Name")
    public void i_enter_company_name() throws InterruptedException {
        customer.searchWithCompanyName360();
    }
    @Then("Company details displayed in Account Information")
    public void Company_details_displayed_in_Account_Information(){
        Assert.assertEquals(customer.companyInfoDisplayed(),true);
    }


    @When("I select Purchase Order")
    public void select_purchase_order() throws InterruptedException {
        customer.searchWithPurchaseOrder();
    }

    @Then("Purchase Number is displayed in grid")
    public void purchase_number_displayed_in_grid(){
        Assert.assertEquals(customer.purchaseOrderDisplayed(),true);
    }

}
