import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import utils.Utils;
import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DigitalUniteWebFormTest {
    WebDriver driver;

    @BeforeAll
    public void setup() {
        // Set up WebDriver and maximize the window

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); // Implicit wait
    }

    @Test
    @DisplayName("Check the form and upload file")
    public void submitForm() throws InterruptedException {

        // Visit the website
        driver.get("https://www.digitalunite.com/practice-webform-learners");

        // Accept the cookies
        WebElement cookie = driver.findElement(By.id("onetrust-accept-btn-handler"));
        cookie.click();

        // Input the name
        driver.findElement(By.id("edit-name")).sendKeys("Khadiza");

        // Input the phone number
        driver.findElement(By.id("edit-number")).sendKeys("01712345678");


        // Enter the date
        driver.findElement(By.id("edit-date")).sendKeys("10/10/2024");

        // Enter the email
        driver.findElement(By.id("edit-email")).sendKeys("khadiza@gmail.com");

        // Enter the message about myself
        driver.findElement(By.id("edit-tell-us-a-bit-about-yourself-")).sendKeys("Automation testing learner.");

        // Scroll down
        Utils.scroll(driver, 500);

        // Upload the file
        WebElement uploadElement = driver.findElement(By.id("edit-uploadocument-upload"));
        uploadElement.sendKeys("C:\\Users\\Khadiza Khan\\Desktop\\junit-1.jpg");

        // Wait for the submission process to complete
        Thread.sleep(5000); // Avoid using Thread.sleep, consider using WebDriverWait

        // Scroll down and select the checkbox
        Utils.scroll(driver, 500);
        WebElement checkBoxElement = driver.findElement(By.cssSelector("label[for='edit-age']"));
        boolean isDisplayed = checkBoxElement.isDisplayed();
        Thread.sleep(5000);
        if (isDisplayed) {
            checkBoxElement.click();
        }

        // Find the submit button and click it
        WebElement submitButton = driver.findElement(By.id("edit-submit"));
        submitButton.click();

        // Wait for the submission process to complete
        Thread.sleep(5000); // Avoid using Thread.sleep, consider using WebDriverWait

        // Find the success message element and get its text
        WebElement successMessage = driver.findElement(By.id("block-pagetitle-2"));
        String actualMessage = successMessage.getText();

        // Assert the expected message
        String expectedMessage = "Thank you for your submission!";
        Assertions.assertEquals(expectedMessage, actualMessage, "The success message is not as expected.");

    }

    // Close the driver
    @AfterAll
    public void closeDriver() {
        driver.quit();
    }
}
