package pages;

//import common.DriverUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


//import DriverUtils;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class LoginPage {


    private WebDriver driver;

    // Locators
    private By emailInputLocator = By.xpath("//*[@id=\"prependedInput\"]");
    private By passwordInputLocator = By.xpath("//*[@id=\"prependedInput2\"]");
    private By loginButtonLocator = By.xpath("//*[@id=\"_submit\"]\n");
    private By forgottenPasswordLinkLocator = By.linkText("Forgotten Password");
    private By usernameLabel = By.xpath("//*[@id=\"user-menu\"]\n");
    //private By fillInThePoNumber=By.xpath("//input[@name='poNumber']");
    @FindBy(xpath = "//input[@name='poNumber' and @type=\"text\"]")
    private WebElement fillInThePoNumber;
    @FindBy(xpath = "//*[text()='Search']")
    private WebElement Searchbutton;
    @FindBy(xpath = "//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeSmall css-e7p61m' and @aria-label='expand row']")
    private WebElement Expandbutton;
    @FindBy(xpath = "//*[text()='Advanced Search']")
    private WebElement Advancedsearchbutton;
    @FindBy(xpath = "//input[@placeholder='Enter Customer ID']")
    private WebElement customeridfield;
    @FindBy(xpath = "//input[@placeholder='Enter Customer Name']")
    private WebElement customernamefield;
    @FindBy(xpath = "//*[text()='Ship To']")
    private WebElement shiptcolumn;
    @FindBy(xpath = "//span[@class='MuiChip-label MuiChip-labelMedium css-9iedg7']")
    private WebElement rc;
    @FindBy(xpath = "//iframe[@class='dt-dashboard-customer-360-iframe']")
    private WebElement RCframe;
    @FindBy(xpath = "//div[@class='MuiBox-root css-1k04fj7']//a[1]")
    private WebElement billofladingvalue;
    @FindBy(xpath = "//input[@name=\"invoiceNumber\"]")
    private WebElement invloucenumber;
    @FindBy(xpath = " //input[@name=\"shipmentNumber\"]")
    private WebElement shipmentnumber;
    @FindBy(xpath = " //input[@name=\"orderNumber\"]")
    private WebElement ordernumber;
    @FindBy(xpath = " //input[@name=\"trackingNumber\"]")
    private WebElement trackingnumber;
    @FindBy(xpath = "//a[@class='MuiTypography-root MuiTypography-inherit MuiLink-root MuiLink-underlineAlways css-6tv1c4' and contains(text(),'2989614')]")
    private WebElement invoicenumbertablefield;
    @FindBy(xpath = " //div[@class='MuiBox-root css-1k04fj7']//a[1]")
    private WebElement shipmentnumbertablefield;
    @FindBy(xpath = " //tr[@class='MuiTableRow-root css-ace0x2']//td[4]")
    private WebElement ordernumbertablefield;
    @FindBy(xpath = " //div[@class='MuiBox-root css-1k04fj7']//a[2]")
    private WebElement trackingnumberfield;
    @FindBy(xpath = " //*[@placeholder='YYYY-MM-DD'][1]")
    private WebElement dateinput;
    @FindBy(xpath = " //*[text()='Clear Search']")
    private WebElement  clearsearchbutton;
    @FindBy(xpath = " //button[@type='submit']")
    private WebElement  advanced_submit_button;
    @FindBy(xpath = "//span[@title='Customers'] ")
    private WebElement customers;
    @FindBy(xpath = "(//span[text()='Customer 360'])[2]")
    private WebElement customer360;
    @FindBy(xpath = "//th[@colspan='2' ]//button [@aria-label='expand column'][1]")
    private WebElement expandshipmentcolumn;
    @FindBy(xpath = "//p[@class='MuiTypography-root MuiTypography-body2 css-xrf920' and text()='82917 | OAK HARBOR | OH']")
    private WebElement customernamefieldvalue;


    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public  void EnterPOnumber(String ponumber) throws InterruptedException {
        HighlightElement(fillInThePoNumber);
        fillInThePoNumber.click();
        fillInThePoNumber.sendKeys(ponumber);
        Thread.sleep(5000);
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
        Thread.sleep(10000);

        WebElement us= driver.findElement(usernameLabel);
            boolean tr;
        tr = us.getText().toString().equals("Deepak Barnwal");
        return tr;
    }
    public WebDriver getDriver() {
        return this.driver;
    }

//    protected void switchToWindowWhichHasElement(Object obj) {
//        Set<String>handles =driver.getWindowHandles();
//        Iterator it=handles.iterator();
//        String parentid=(String) it.next();
//        String childid=(String) it.next();
//        if(it)
//    }
    public void switchToWindowWhichHasElement(Object obj) {

        Set<String> allWindows = getDriver().getWindowHandles();

        for (String window : allWindows) {
            getDriver().switchTo().window(window);


        }

    }
    protected void HighlightElement(WebElement elm) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("arguments[0].style.border='4px solid red'", elm);
            Thread.sleep(1000);
            js.executeScript("arguments[0].style.border=''", elm);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }


    public void login(String email, String password) throws InterruptedException {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }
    public void clickOnCustomers()  throws InterruptedException{
        HighlightElement( customers);
        customers.click();
        Thread.sleep(5000);

    }


    public void clickCustomer360() throws InterruptedException {

//        HighlightElement(customer360);
//        customer360.click();
//        Thread.sleep(5000);

//       // List<WebElement> cs ]'
//        //
//        // = driver.findElements(customer);
//        //Thread.sleep(10000);
//        //driver.findElement(customer).click();
////         //  driver.findElement(customer360).click();
        String current_url=driver.getCurrentUrl();
        String editUrl=current_url+"dashboard/customer-360/";
        driver.get(editUrl);
        Thread.sleep(10000);
    }



    public void  clickonRC() throws InterruptedException{

       driver.switchTo().frame(RCframe);
        HighlightElement(rc);
        rc.click();
        switchToWindowWhichHasElement(fillInThePoNumber);
        Thread.sleep(10000);

    }

    public void clickOnSearch()  throws InterruptedException{
        HighlightElement( Searchbutton);
        Searchbutton.click();
        Thread.sleep(10000);

    }

    public void expandTheResult() throws InterruptedException {

        HighlightElement(Expandbutton);
        Thread.sleep(10000);
        Expandbutton.click();
        JavascriptExecutor js= (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,350)", "");
        Thread.sleep(10000);
    }

    public void expandTheShipmentColumn()throws InterruptedException {

        HighlightElement(expandshipmentcolumn);
        Expandbutton.click();
        Thread.sleep(5000);
    }

    public void clickOnAdvancedSearch() throws InterruptedException {

        HighlightElement(Advancedsearchbutton);
        Advancedsearchbutton.click();
        Thread.sleep(5000);
    }

    public void iHaveEnteredCustomerId(String id) throws InterruptedException {
        HighlightElement(customeridfield);
        customeridfield.click();
        customeridfield.sendKeys(id);
        Thread.sleep(10000);
    }


    public void iHaveEnteredCustomername() throws InterruptedException  {
        HighlightElement(customernamefield);
        customernamefield.sendKeys("C NELSON MANUFACTURING CO INC");
        Thread.sleep(10000);
        customernamefield.sendKeys(Keys.ARROW_DOWN);
        customernamefield.sendKeys(Keys.ARROW_DOWN);

        Thread.sleep(3000);
        customernamefieldvalue.click();
        Thread.sleep(10000);

    }

    public void  iHaveEnteredInvoiceNumber(String inumber) throws InterruptedException{
        HighlightElement(invloucenumber);
        invloucenumber.click();
        invloucenumber.sendKeys(inumber);
        Thread.sleep(5000);
    }
    public void  iHaveEnteredShipmentNumber(String Snumber) throws InterruptedException{
        HighlightElement(shipmentnumber);
        shipmentnumber.click();
        shipmentnumber.sendKeys(Snumber);
        Thread.sleep(5000);
    }
    public void  iHaveEnteredOrderNumber(String onumber) throws InterruptedException{
        HighlightElement(ordernumber);
        ordernumber.click();
        ordernumber.sendKeys(onumber);
        Thread.sleep(5000);
    }
    public void iHaveEnteredTrackingNumber(String tnumber) throws InterruptedException{
        HighlightElement(trackingnumber);
        trackingnumber.click();
        trackingnumber.sendKeys(tnumber);
        Thread.sleep(5000);
    }
    public void verifyThatInvoiceNumberIsGettingDisplayed()throws InterruptedException {
        HighlightElement(invoicenumbertablefield);
        if (!invoicenumbertablefield.getText().equals("2989614")) {
            Assert.fail();
        }


    }
    public void verifyThatBillOfLadingIsGettingDisplayed()  throws InterruptedException
    {
        HighlightElement(billofladingvalue);
        if(!billofladingvalue.getText().equals("3614835")){
            Assert.fail();
        }
    }
//        public void verifyThatShipmentNumberIsGettingDisplayed()throws InterruptedException
//        {
//            HighlightElement(shipmentnumbertablefield);
//            if(!shipmentnumbertablefield.getText().equals("3969208")){
//                Assert.fail();
//            }
//        }
    public void verifyThatOrderNumberIsGettingDisplayed()throws InterruptedException
    {
        HighlightElement(ordernumbertablefield);
        if(!ordernumbertablefield.getText().equals("2793722")){
            Assert.fail();
        }
    }
    public void verifyThatTrackingNumberIsGettingDisplayed()throws InterruptedException
    {
        HighlightElement(trackingnumberfield);
        if(!trackingnumberfield.getText().equals("640624254742")){
            Assert.fail();
        }
    }
    public void  enterTheDateIntoTheCalendar(String date) throws InterruptedException{
        HighlightElement(dateinput);
        dateinput.click();
        dateinput.sendKeys(date);
        Thread.sleep(5000);
    }
    public void clickOnClearSearchButton() throws InterruptedException {

        HighlightElement(clearsearchbutton);
        clearsearchbutton.click();
        Thread.sleep(10000);
    }

    public void verifyThatAllTheFieldsGetReset()throws InterruptedException

    {
        if (Searchbutton.isEnabled()) {
            Assert.fail();
        }

}
    public void clickOnSearchOnAdvancedSearch() throws InterruptedException {
        Thread.sleep(3000);
        HighlightElement(advanced_submit_button);
        advanced_submit_button.click();
        Thread.sleep(5000);
    }

    public void  verifyThatBillOfLadingUnderShipmentIsGettingDisplayed () throws InterruptedException{
    {
        HighlightElement(billofladingvalue);
        if(!billofladingvalue.getText().equals("3969208")){
            Assert.fail();
        }
    }
}}

