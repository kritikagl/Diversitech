package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Customer360page {
    private WebDriver driver;
    // Locators
    private By emailInputLocator = By.xpath("//*[@id=\"prependedInput\"]");
    private By passwordInputLocator = By.xpath("//*[@id=\"prependedInput2\"]");
    private By loginButtonLocator = By.xpath("//*[@id=\"_submit\"]\n");
    private By forgottenPasswordLinkLocator = By.linkText("Forgotten Password");
    private By usernameLabel = By.xpath("//*[@id=\"user-menu\"]\n");
    private By customer = By.xpath("//*[@id=\"main-menu\"]");

    private By customer360 = By.xpath("//span[@title='Customer 360']");
    //private By fillInThePoNumber=By.xpath("//input[@name='poNumber']");
    @FindBy(xpath = "//*[@placeholder=\"Enter the customer name or a 3-5 digits code\"]")
    private WebElement companyName;

    @FindBy(xpath = "//*[@id=\"single-spa-application:shipment-lookup\"]")
    private WebElement shipmentPlaceHolder;

    @FindBy(xpath = "//*[@id=\"po-number-input\"]")
    private WebElement poNumber;

    @FindBy(xpath = "//*[@class=\"MuiTable-root css-1vdcb0h\"]")
    private WebElement dataTable;

    @FindBy(xpath = "//iframe[@class='dt-dashboard-customer-360-iframe']")
    private WebElement cusframe;

    @FindBy(xpath = "//*[@id=\"order-number-input\"]")
    private WebElement orderNumber;

    @FindBy(xpath = "//*[@id=\"shipment-number-input\"]")
    private WebElement shipmentNumber;

    @FindBy(xpath = "//*[@id=\"item-number-input\"]")
    private WebElement itemNumber;

    @FindBy(xpath = "//*[@id=\"single-spa-application:account-information\"]/div/div[2]/ul/li[3]/div/span/a")
    private WebElement orderInfo;

    @FindBy(xpath="//*[@id=\"single-spa-application:search-dashboard\"]/div/div/div[3]/div/div[2]/button[2]")
    private WebElement dropButton;

    @FindBy(xpath="//*[@id=\"split-button-menu\"]/li[2]")
    private WebElement purchaseOrderButton;

    @FindBy(xpath = "//*[@placeholder=\"Enter the product order number\"]")
    private WebElement purchaseOrder;

    @FindBy(xpath = "//*[@id=\"single-spa-application:account-information\"]/div/div[2]/ul/li[1]/div/span")
    private WebElement companyInfo;

    // Constructor
    public Customer360page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    // Methods
    public void enterEmail(String email) throws InterruptedException {
        Thread.sleep(10000);
        WebElement emailInput = driver.findElement(emailInputLocator);
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passwordInput = driver.findElement(passwordInputLocator);
        passwordInput.sendKeys(password);
    }

    public void clickLoginButton() throws InterruptedException {
        WebElement loginButton = driver.findElement(loginButtonLocator);
        loginButton.click();
    }


    public boolean usernameDisplayed() throws InterruptedException {
        Thread.sleep(20000);

        WebElement us = driver.findElement(usernameLabel);
        boolean tr;
        tr = us.getText().toString().equals("Deepak Barnwal");
        return tr;
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public void clickCustomer360() throws InterruptedException {
        // List<WebElement> cs ]'
        //
        // = driver.findElements(customer);
        //Thread.sleep(10000);
        //driver.findElement(customer).click();
        //  driver.findElement(customer360).click();
        String current_url = driver.getCurrentUrl();
        String editUrl = current_url + "dashboard/customer-360/";
        driver.get(editUrl);
        Thread.sleep(10000);

    }
    //_______________________________________//Customer 360//---------------------------------------------
    public boolean customer360_homePage_displayed() {
        String actualPageTitle = driver.getTitle();
        String expectedPageTitle = "Customer 360 - Customers";
        return actualPageTitle.equals(expectedPageTitle);

    }

    public void searchWithCompanyName360() throws InterruptedException {
        driver.switchTo().frame(cusframe);
        if (companyName.getText().toString().isEmpty()) {
            companyName.sendKeys("22867");
            companyName.sendKeys(Keys.ENTER);
            Thread.sleep(10000);

        } else {
            companyName.clear();
            companyName.sendKeys("22867");
            companyName.sendKeys(Keys.ENTER);
        }
    }

    public void searchWithPurchaseOrder() throws InterruptedException {
        driver.switchTo().frame(cusframe);
        dropButton.click();
        Thread.sleep(10000);
        dropButton.sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(10000);
        purchaseOrderButton.click();
        if (purchaseOrder.getText().toString().isEmpty()) {
            purchaseOrder.sendKeys("P000115568");
            purchaseOrder.sendKeys(Keys.ENTER);
            Thread.sleep(10000);

        } else {
            purchaseOrder.clear();
            purchaseOrder.sendKeys("P000115568");
            purchaseOrder.sendKeys(Keys.ENTER);
        }

    }
    public boolean purchaseOrderDisplayed() {
        return orderInfo.getText().toString().equals("22867");
    }

    public boolean companyInfoDisplayed() {
        String actualTitle=companyInfo.getText().toString();
        return actualTitle.equals("WITTICHEN SUPPLY COMPANY");
    }

    public void scrollTillShipment() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", shipmentPlaceHolder);
        Thread.sleep(10000);


    }

    public void enterPONUmber360() throws InterruptedException {
        if (!poNumber.getText().toString().isEmpty()) {
            poNumber.clear();

        }
        poNumber.sendKeys("P000115568");
        poNumber.sendKeys(Keys.ENTER);
        Thread.sleep(10000);
    }

    public boolean poResultDisplayed360() {

        return dataTable.isDisplayed();
    }

    public void enterOrderNUmber360() throws InterruptedException {
        if (!orderNumber.getText().toString().isEmpty()) {
            orderNumber.clear();

        }
        orderNumber.sendKeys("2292357");
        orderNumber.sendKeys(Keys.ENTER);
        Thread.sleep(10000);
    }

    public boolean orderResultDisplayed360() {

        return dataTable.isDisplayed();
    }

    public boolean verifyDataInGrid(String expectedText) {
        List<WebElement> rowsList = dataTable.findElements(By.tagName("tr"));
        int rowCount = rowsList.size();
        for (int i = 0; i < rowCount; i++) {
            // count columns with size() method
            List<WebElement> cols = rowsList.get(i).findElements(By.tagName("td"));
            int cols_cnt = cols.size();
            //iterate cols of table
            for (int j = 0; j < cols_cnt; j++) {
                // get cell text with getText()
                String c = cols.get(j).getText().toString();
                if (c.equals(expectedText)) {
                    break;
                } else {
                    continue;
                }
            }
        }
        return true;
    }

    public void enterShipmentNUmber360() throws InterruptedException {
        if (!shipmentNumber.getText().toString().isEmpty()) {
            shipmentNumber.clear();

        }
        shipmentNumber.sendKeys("2570934");
        shipmentNumber.sendKeys(Keys.ENTER);
        Thread.sleep(10000);
    }

    public void enterItemNUmber360() throws InterruptedException {
        if (!itemNumber.getText().toString().isEmpty()) {
            itemNumber.clear();

        }
        itemNumber.sendKeys("1002");
        itemNumber.sendKeys(Keys.ENTER);
        Thread.sleep(10000);
    }
}



