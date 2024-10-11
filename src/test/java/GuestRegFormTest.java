
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utils;

import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GuestRegFormTest {
    WebDriver driver;

    @BeforeAll
    public void setup() {
        // Set up WebDriver and maximize the window

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); // Implicit wait
    }

    @Test
    public void GuestRegistrationForm() throws InterruptedException {

        // Visit the website
        driver.get("https://demo.wpeverest.com/user-registration/guest-registration-form/");

        // Input the first name
        driver.findElement(By.id("first_name")).sendKeys("khadizakhan");

        // Input the last name
        driver.findElement(By.id("last_name")).sendKeys("ms");

        // Enter the email
        driver.findElement(By.id("user_email")).sendKeys("kkhadizakhanm@gmail.com");

        //Click the gender radio button
        driver.findElement(By.id("radio_1665627729_Female")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("user_pass")).sendKeys("*Khafn01171*");

        // Date Picker Handling
        By dateOfBirthLocator = By.xpath("//input[@data-id='date_box_1665628538']");
        WebElement dateOfBirthField = driver.findElement(dateOfBirthLocator);

        // Scroll to the Date of Birth field
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dateOfBirthField);
        Thread.sleep(2000);

        // Use JavaScript to set the date
        String desiredDate = "1996-12-25";
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('1996-12-25')", dateOfBirthField); // Remove 'readonly' attribute
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + desiredDate + "')", dateOfBirthField);

        Thread.sleep(3000);

        Utils.scroll(driver, 500);
        driver.findElement(By.id("input_box_1665629217")).sendKeys("Bangladeshi");

        WebElement phoneElement = driver.findElement(By.xpath("//input[@data-id='phone_1665627880']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", phoneElement);
        Thread.sleep(2000);

        //phoneElement.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + "(323) 232-3232" + "')", phoneElement);

        Thread.sleep(3000);

        Utils.scroll(driver, 500);

        //Select country from dropdown
        Select select = new Select(driver.findElement(By.id("country_1665629257")));
        select.selectByVisibleText("Bangladesh");

        Utils.scroll(driver, 1500);

        Thread.sleep(5000);

        //Click on check box (privacy policy)
        driver.findElement(By.id("privacy_policy_1665633140")).click();

        //Click on Submit button
        WebElement submitButton = driver.findElement(By.cssSelector(".btn.button.ur-submit-button"));
        submitButton.click();

        Thread.sleep(2000);

        // Find the success message element and get its text
        WebElement successMessage = driver.findElement(By.id("ur-submit-message-node"));
        String actualMessage = successMessage.getText();

        Thread.sleep(5000);

        // Assert the expected message
        String expectedMessage = "User successfully registered.";
        Assertions.assertEquals(expectedMessage, actualMessage, "The success message is not as expected.");
    }

    // Close the driver
    @AfterAll
    public void closeDriver() {
        driver.quit();
    }
}
