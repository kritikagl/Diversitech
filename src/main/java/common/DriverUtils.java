//package common;
//
//
//import org.openqa.selenium.*;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxOptions;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.logging.LogType;
//import org.openqa.selenium.logging.LoggingPreferences;
////import org.openqa.selenium.remote.BrowserType;
//import org.openqa.selenium.remote.CapabilityType;
//import org.openqa.selenium.remote.RemoteWebDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.FluentWait;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.text.SimpleDateFormat;
//import java.time.Duration;
//import java.util.NoSuchElementException;
//import java.util.*;
//import java.util.function.Function;
//import java.util.logging.Level;
//
//
//public class DriverUtils  {
//    static final Logger logger = LoggerFactory.getLogger(DriverUtils.class);
//    //static final int timeout = ConsoleConstant.SERENITY_VARIABLE.getPropertyAsInteger("webdriver.wait.for.timeout", 30);
//    private static WebDriver firefoxDriver;
//    private static WebDriver chromeIncognitoDriver;
//    private static WebDriver chromeNormalDriver;
//
//    public enum OdigoDrivers {
//        FIREFOX, INCOGNITO, CHROME
//    }
//
///*||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
//  ||                                                                        DRIVER RELATED METHODS                                                                                                      ||
//  ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
// */
//
//    /**
//     * Create and launch given browser , opens given url and maximizes
//     *
//     * @requiredDriver chrome, firefox or incognito
//     * @urlToOpen url to be opened
//     */
//
//
//    /**
//     * Returns the webdriver object of given type only it is already opened
//     * If not found any opened driver, will return null
//     *
//     * @param requiredDriver
//     * @return
//     */
//
//
//    protected static Map<OdigoDrivers, WebDriver> getAllOpenedDrivers() {
//        Map<OdigoDrivers, WebDriver> mp = new HashMap<>();
//        if (firefoxDriver != null)
//            mp.put(OdigoDrivers.FIREFOX, firefoxDriver);
//        if (chromeIncognitoDriver != null)
//            mp.put(OdigoDrivers.INCOGNITO, chromeIncognitoDriver);
//        if (chromeNormalDriver != null && !chromeNormalDriver.toString().contains("Uninitialised"))
//            mp.put(OdigoDrivers.CHROME, chromeNormalDriver);
//        logger.info("Total drivers opened are: {}", mp.size());
//        for (Map.Entry<OdigoDrivers, WebDriver> entry : mp.entrySet()) {
//            logger.info("DriverType: {},   Driver Object: {}", entry.getKey().name(), entry.getValue());
//        }
//        return mp;
//    }
//
//    protected static void quitAllOdigoDriversExceptChrome() {
//        logger.info("Quitting all drivers opened");
//        Map<OdigoDrivers, WebDriver> mp = getAllOpenedDrivers();
//        for (Map.Entry<OdigoDrivers, WebDriver> entry : mp.entrySet()) {
//            if (entry.getKey() != OdigoDrivers.CHROME) {
//                logger.info("Quitting {}", entry.getKey().name());
//                entry.getValue().quit();
//            } else {
//                logger.info("Not Closing Chrome driver from utils, serenity will take care: {}", entry.getValue());
//            }
//        }
//        chromeIncognitoDriver = null;
//        firefoxDriver = null;
//    }
//
//    /**
//     * To switch between driveres during multi session execution
//     * This method should be called from respective page class before using that page class with different driver
//     *
//     * @param browser
//     */
//
//
//
///*||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
//  ||                                                                        OVERRIDE METHODS FOR CONSISTENCY                                                                                            ||
//  ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
// */
//
//
//    public void clickOn(WebElement element) {
//        waitAndClick(element);
//    }
//
//
//    public void typeInto(WebElement field, String value) {
//        waitAndType(field, value);
//    }
//
//
///*||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
//  ||                                                                        UTILS METHODS TO GET ELEMENTS                                                                                               ||
//  ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
// */
//
//    /**
//     * Returns WebElement
//     *
//     * @param obj can be of Type String, By or WebElement
//     * @return
//     */
//    protected WebElement getWebElement(Object obj) {
//        WebElement element = null;
//        if (obj instanceof String)
//            element = findBy((String) obj);
//        else if (obj instanceof By)
//            element = find((By) obj);
//        else if (obj instanceof WebElement) {
//            element = ((WebElement) obj);
//        } else {
//            throw new IllegalArgumentException("Unsupported object type. Please provide String, By or WebElement type object");
//        }
//        return element;
//    }
//
//    /**
//     * Returns WebElement drived from a dynamic String
//     *
//     * @param pattern a String pattern
//     * @param params  Array of parameters to construct this String
//     * @return
//     */
//    protected WebElement getDynamicWebElement(String pattern, Object... params) {
//        String str = String.format(pattern, params);
//        return findBy(str);
//    }
//
//
///*||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
//  ||                                                                        UTILS METHODS TO CHECK CONDITIONS                                                                                           ||
//  ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
// */
//
//    /**
//     * Checks for presence of element given
//     *
//     * @param element
//     * @return true if available, false otherwise
//     */
//    protected static boolean isElementPresent(WebElement element) {
//        boolean flag = false;
//        try {
//            if (element.isDisplayed() || element.isEnabled())
//                flag = true;
//        } catch (StaleElementReferenceException ex) {
//            flag = isElementPresent(element);
//        } catch (org.openqa.selenium.NoSuchElementException e) {
//            flag = false;
//        }
//        return flag;
//    }
//
//    /**
//     * Checks if given element is selected
//     *
//     * @param obj of type String, By or WebElement
//     * @return true if available, false otherwise
//     */
//    protected boolean isElementSelected(Object obj) {
//        boolean isSelected = false;
//        try {
//            isSelected = getWebElement(obj).isSelected();
//        } catch (StaleElementReferenceException e) {
//            isSelected = isElementSelected(obj);
//        }
//        return isSelected;
//    }
//
//    /**
//     * Checks for availability of element given
//     *
//     * @param obj of type String, By or WebElement
//     * @return true if available, false otherwise
//     */
//    protected boolean isElementEnabled(Object obj) {
//        boolean isEnabled = false;
//        try {
//            isEnabled = getWebElement(obj).isEnabled();
//        } catch (StaleElementReferenceException e) {
//            isEnabled = isElementEnabled(obj);
//        }
//        return isEnabled;
//    }
//
//    /**
//     * Checks if alert is present
//     *
//     * @return
//     */
//    protected boolean isAlertPresent() {
//        try {
//            getDriver().switchTo().alert();
//            return true;
//        } catch (NoAlertPresentException ex) {
//            return false;
//        }
//    }
//
///*||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
//  ||                                                                        UTILS METHODS TO WAIT                                                                                                       ||
//  ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
// */
//
//    /**
//     * This method will wait for list of web element to have minimum elements
//     *
//     * @param by:      locatormvnb;,l of elements
//     * @param timeOut: maximum time selenium will wait in seconds
//     * @param size     : minimum number of elements
//     */
//    protected void waitForListOfElement(By by, int timeOut, int size) {
//        FluentWait wait = new FluentWait(getDriver())
//                .withTimeout(Duration.ofSeconds(timeOut))
//                .ignoring(NoSuchElementException.class);
//        wait.until(new Function() {
//            public Object apply(Object o) {
//                return getDriver().findElements(by).size() >= size;
//            }
//        });
//    }
//
//    /**
//     * This method is used to wait for the attribute value to be for any element
//     *
//     * @param element:   web element
//     * @param attribute: attribute
//     * @param value:     expected value
//     */
//    protected void waitForAttributeToBe(WebElement element, String attribute, String value) {
//        WebDriverWait wait = new WebDriverWait(getDriver(), 30);
//        wait.until(ExpectedConditions.attributeToBe(element, attribute, value));
//    }
//
//    protected void waitForPageToLoadCompletely() {
//        logger.info("Waiting for Page load....");
//        waitJQueryAngular();
//        logger.info("Page load Completed....");
//    }
//
//    /**
//     * Wait for element to be visible
//     *
//     * @param obj of type String, By or WebElement
//     */
//    protected void waitUntilElementIsDisplayed(Object obj) {
//        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
//        if (obj instanceof By)
//            wait.until(ExpectedConditions.visibilityOfElementLocated((By) obj));
//        else if (obj instanceof WebElement)
//            wait.until(ExpectedConditions.visibilityOf((WebElement) obj));
//        else
//            logger.error("Not Supported Object Type, use By or WebElement only");
//    }
//
//    /**
//     * Wait for all the elements given in the list
//     *
//     * @param elements
//     */
//    protected void waitUntilElementsAreDisplayed(List<WebElement> elements) {
//        WebDriverWait wait = new WebDriverWait(getDriver(), 120);
//        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
//    }
//
//    /**
//     * Wait for number of windows
//     *
//     * @param count
//     */
//    protected void waitForNumberOfWindows(int count) {
//        WebDriverWait wait = new WebDriverWait(getDriver(), 120);
//        wait.until(ExpectedConditions.numberOfWindowsToBe(count));
//    }
//
///*||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
//  ||                                                                        UTILS METHODS TO CLICK ELEMENTS                                                                                             ||
//  ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
// */
//
//    /**
//     * Clicks element using JavaScript
//     *
//     * @param obj of type String, By or WebElement
//     * @return true if available, false otherwise
//     */
//    protected void clickByJavaScript(Object obj) {
//        WebElement element = getWebElement(obj);
//        executeJavascript("arguments[0].click();", element);
//        logger.info("Element clicked using javascript executor");
//    }
//
//    /**
//     * Wait and click element
//     *
//     * @param obj of type String, By or WebElement
//     */
//    protected void waitAndClick(Object obj) {
//        try {
//            WebElement element = getWebElement(obj);
//            new WebDriverWait(getDriver(), timeout).until(ExpectedConditions.elementToBeClickable(element)).click();
//        } catch (StaleElementReferenceException e) {
//            waitAndClick(obj);
//        }
//    }
//
//    /**
//     * Wait and double click element
//     *
//     * @param obj of type String, By or WebElement
//     */
//    protected void waitAndDoubleClick(Object obj) {
//        try {
//            WebElement element = getWebElement(obj);
//            new WebDriverWait(getDriver(), timeout).until(ExpectedConditions.elementToBeClickable(element));
//            Actions act = new Actions(getDriver());
//            act.doubleClick(element).build().perform();
//        } catch (StaleElementReferenceException e) {
//            waitAndDoubleClick(obj);
//        }
//    }
//
///*||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
//  ||                                                                        UTILS METHODS TO GET FROM ELEMENTS                                                                                          ||
//  ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
// */
//
//    /**
//     * Get text from given element
//     *
//     * @param obj can be of type By, String or WebElement
//     * @return
//     */
//    protected String getTextFromElement(Object obj) {
//        String text = null;
//        try {
//            text = getWebElement(obj).getText();
//        } catch (StaleElementReferenceException e) {
//            text = getTextFromElement(obj);
//        }
//        return text;
//    }
//
//    /**
//     * Get given attribute from element
//     *
//     * @param obj       can be of type By, String or WebElement
//     * @param attribute
//     * @return
//     */
//    protected String getAttribute(Object obj, String attribute) {
//        String attr = null;
//        try {
//            attr = getWebElement(obj).getAttribute(attribute);
//        } catch (StaleElementReferenceException e) {
//            attr = getAttribute(obj, attribute);
//        }
//        return attr;
//    }
//
//    /**
//     * Handle Alert and get text from alert
//     * Author- Chandan Varshney
//     */
//    protected String getTextAndCloseAlert() {
//        waitABit(3000);
//        String txt = "";
//        if (isAlertPresent()) {
//            Alert alert = getDriver().switchTo().alert();
//            txt = alert.getText();
//            System.out.println(txt);
//            alert.accept();
//        }
//        return txt;
//    }
//
//
///*||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
//  ||                                                                        UTILS METHODS TO SCROLL PAGE                                                                                                ||
//  ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
// */
//
//    protected void scrollToTopOfThePage() {
//        //((JavascriptExecutor) getDriver()).executeScript("window.scrollTo(0, -document.body.scrollHeight)");
//        Actions act = new Actions(getDriver());
//        act.sendKeys(Keys.HOME).build().perform();
//    }
//
//    protected void scrollToDownOfThePage() {
//        ((JavascriptExecutor) getDriver()).executeScript("window.scrollTo(0, document.body.scrollHeight)");
//        Actions act = new Actions(getDriver());
//        act.sendKeys(Keys.END).build().perform();
//    }
//
//    /**
//     * This method is used to scroll to element on the page
//     *
//     * @param element: element to scroll
//     */
//    protected void scrollIntoViewByJS(WebElement element) {
//        try {
//            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
//        } catch (Exception e) {
//        }
//        try {
//            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(false);", element);
//        } catch (Exception e) {
//        }
//        try {
//            Actions actions = new Actions(getDriver());
//            actions.moveToElement(element);
//            actions.perform();
//        } catch (Exception e) {
//        }
//    }
///*||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
//  ||                                                                        UTILS METHODS FOR MOUSE/KEYBOARD ACTIONS                                                                                    ||
//  ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
// */
//
//    /**
//     * Type text into given element
//     *
//     * @param obj of type String, By or WebElement
//     */
//    protected void waitAndType(Object obj, String text) {
//        waitUntilElementIsDisplayed(obj);
//        getWebElement(obj).sendKeys(text);
//    }
//
//    /**
//     * Mouse hover on given element
//     *
//     * @param obj of type String, By or WebElement
//     * @return actions
//     */
//    protected Actions mouseHover(Object obj) {
//        WebElement element = getWebElement(obj);
//        Actions action = new Actions(getDriver());
//        action.moveToElement(element).build().perform();
//        return action;
//    }
//
//    protected void mouseHoverJScript(WebElement HoverElement) {
//        try {
//            if (isElementPresent(HoverElement)) {
//
//                String mouseOverScript =
//                        "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
//                ((JavascriptExecutor) getDriver()).executeScript(mouseOverScript, HoverElement);
//
//            } else {
//                logger.info("Element was not visible to hover " + "\n");
//
//            }
//        } catch (StaleElementReferenceException e) {
//            mouseHoverJScript(HoverElement);
//        } catch (NoSuchElementException e) {
//            logger.info("Element " + HoverElement + " was not found in DOM" + e.getMessage());
//        } catch (Exception e) {
//            logger.info("Error occurred while hovering" + e.getMessage());
//        }
//    }
//
//    /**
//     * Drag and drop element using JS
//     *
//     * @param ByFrom
//     * @param ByTo
//     * @author chandan varshney
//     */
//    protected void dragDropByJS(By ByFrom, By ByTo) {
//        WebElement LocatorFrom = getDriver().findElement(ByFrom);
//        WebElement LocatorTo = getDriver().findElement(ByTo);
//        String xto = Integer.toString(LocatorTo.getLocation().x);
//        String yto = Integer.toString(LocatorTo.getLocation().y);
//        ((JavascriptExecutor) getDriver()).executeScript(
//                "function simulate(f,c,d,e){var b,a=null;for(b in eventMatchers)if(eventMatchers[b].test(c)){a=b;break}if(!a)return!1;document.createEvent?(b=document.createEvent(a),a==\"HTMLEvents\"?b.initEvent(c,!0,!0):b.initMouseEvent(c,!0,!0,document.defaultView,0,d,e,d,e,!1,!1,!1,!1,0,null),f.dispatchEvent(b)):(a=document.createEventObject(),a.detail=0,a.screenX=d,a.screenY=e,a.clientX=d,a.clientY=e,a.ctrlKey=!1,a.altKey=!1,a.shiftKey=!1,a.metaKey=!1,a.button=1,f.fireEvent(\"on\"+c,a));return!0} var eventMatchers={HTMLEvents:/^(?:load|unload|abort|error|select|change|submit|reset|focus|blur|resize|scroll)$/,MouseEvents:/^(?:click|dblclick|mouse(?:down|up|over|move|out))$/}; "
//                        + "simulate(arguments[0],\"mousedown\",0,0); simulate(arguments[0],\"mousemove\",arguments[1],arguments[2]); simulate(arguments[0],\"mouseup\",arguments[1],arguments[2]); ",
//                LocatorFrom, xto, yto);
//    }
//
//    /**
//     * @param source,target sCan be of type String, By and WebElement
//     * @return This method drag and WebElement from <source> and Drop into <target>
//     * @author piyush.garg
//     */
//    protected void dragAndDrop(Object source, Object target) {
//        Actions act = new Actions(getDriver());
//        act.click(getWebElement(source));
//        act.moveToElement(getWebElement(target));
//        act.build().perform();
//    }
//
//
// /*||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
//   ||                                                                        OTHER UTILS METHODS                                                                                                         ||
//   ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
// */
//
//    /**
//     * If multiple windows are opened then switch to required window
//     *
//     * @param obj can be of type String, By or WebElement
//     */
//    protected void switchToWindowWhichHasElement(Object obj) {
//        String parent = getDriver().getWindowHandle();
//        Set<String> allWindows = getDriver().getWindowHandles();
//        logger.info("Total number of windows present: " + allWindows.size());
//        for (String window : allWindows) {
//            getDriver().switchTo().window(window);
//            waitForPageToLoadCompletely();
//            try {
//                waitUntilElementIsDisplayed(getWebElement(obj));
//                logger.info("Switched successfully...");
//                break;
//            } catch (Exception e) {
//                logger.info(window + " is not having required element");
//            }
//        }
//    }
//
//    /**
//     * Uses serenity's Serenity.takeScreenshot() method to take full page screenshot
//     */
//
//    protected void takeFullPageScreenShot() {
//        try {
//            int heightInner = Integer
//                    .parseInt(((JavascriptExecutor) getDriver()).executeScript("return window.innerHeight").toString());
//            int heightTotal = Integer.parseInt(
//                    ((JavascriptExecutor) getDriver()).executeScript("return document.documentElement.scrollHeight")
//                            .toString());
//            int scrollInitial = 0;
//            int scrollFinal = heightInner - 150;
//            ((JavascriptExecutor) getDriver())
//                    .executeScript("window.scrollBy(0,-" + heightTotal + ")");
//            for (int i = 0; i < (heightTotal / scrollFinal); i++) {
//                Serenity.takeScreenshot();
//                ((JavascriptExecutor) getDriver()).executeScript(
//                        "window.scrollBy(" + scrollInitial + "," + scrollFinal + ")");
//            }
//        } catch (Exception e) {
//            logger.info(e.getMessage());
//        }
//    }
//
//    /**
//     * Get Screenshot of given output Tyoe
//     *
//     * @param outputType
//     * @return
//     */
//    protected Object getScreenShotObject(OutputType outputType) {
//        Object screenShotObj = null;
//        for (int i = 1; i <= 3; i++) {
//            try {
//                if (outputType.equals(OutputType.BYTES) && screenShotObj == null)
//                    screenShotObj = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
//                else if (outputType.equals(OutputType.BASE64) && screenShotObj == null)
//                    screenShotObj = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);
//            } catch (Exception ex) {
//                logger.error("Screenshot failed to capture retry count {}/3", i);
//                logger.error(Arrays.toString(ex.getStackTrace()));
//            }
//        }
//        return screenShotObj;
//    }
//
//    /**
//     * Execute a Javascript
//     *
//     * @param script Script String
//     * @param params Optional parameters array
//     * @return
//     */
//    protected Object executeJavascript(String script, Object... params) {
//        return getJavascriptExecutorFacade().executeScript(script, params);
//    }
//
//    protected void HighlightElement(WebElementFacade elm) {
//        try {
//            JavascriptExecutor js = (JavascriptExecutor) getDriver();
//            js.executeScript("arguments[0].style.border='4px solid red'", elm);
//            Thread.sleep(1000);
//            js.executeScript("arguments[0].style.border=''", elm);
//        } catch (InterruptedException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    protected void waitUntilElementIsDisplayedForClick(WebElement element) {
//        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
//        wait.until(ExpectedConditions.elementToBeClickable(element));
//    }
//
//    /**
//     * @param format
//     * @return Date in string format
//     * @AddedBy Chandan Varshney
//     */
//    protected String getCurrentDateInSpecifiedFormat(String format) {
//        SimpleDateFormat formatter = new SimpleDateFormat(format);
//        formatter.setTimeZone(TimeZone.getDefault());
//        return formatter.format(new Date());
//    }
//
//
//}
