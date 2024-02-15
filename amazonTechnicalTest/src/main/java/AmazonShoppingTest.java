import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AmazonShoppingTest {
    // Declare the WebDriver object
    WebDriver driver;

    // Declare the variables for the expected item name and price
    String expectedItemName = "TP-Link N450 WiFi Router - Wireless Internet Router for Home (TL-WR940N)";
    String expectedItemPrice = "$55.39";

    @BeforeTest
    public void setUp() throws Exception{
        // Set the system property for Chrome driver
        System.setProperty("webdriver.chrome.driver", "Resources1/chromedriver.exe");

        // Instantiate the Chrome driver
        driver = new ChromeDriver();

        // Navigate to Amazon.com
        driver.get("https://www.amazon.com/");

        // Maximize the browser window
        driver.manage().window().maximize();
    }

    @Test
    public void testAmazon() {
        // Locate the search box element, enter the item name and click on it
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys(expectedItemName);
        searchBox.submit();

        // Locate the first item element and click on it
        WebElement firstItem = driver.findElement(By.xpath("(//span[@class='a-size-medium a-color-base a-text-normal'])[1]"));
        firstItem.click();

        // Locate the add to cart button element and click on it
        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-button"));
        addToCartButton.click();

        // Navigate to the cart
        WebElement cartIcon = driver.findElement(By.id("nav-cart-count"));
        cartIcon.click();

        // Locate the item name element in the cart and get its text
        WebElement itemName = driver.findElement(By.xpath("//span[@class='a-size-medium sc-product-title a-text-bold']"));
        String actualItemName = itemName.getText();

       // Locate the item price element in the cart and get its text
        WebElement itemPrice = driver.findElement(By.xpath("//span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap sc-product-price a-text-bold']"));
        String actualItemPrice = itemPrice.getText();

        // Assert that the actual item name and price match the expected ones
        Assert.assertEquals(actualItemName, expectedItemName, "Item name does not match");
        Assert.assertEquals(actualItemPrice, expectedItemPrice, "Item price does not match");
    }
    @AfterTest
    public void tearDown() {
        // Close the browser
        driver.close();

    }

}
