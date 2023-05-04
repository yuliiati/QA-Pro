import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.List;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HwRegistrationFormTests {

    ChromeDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","/Users/49160/Downloads/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));   // Ждун :)))))
        driver.get("https://suninjuly.github.io/registration1.html");   // Поменяли ссылку :)

    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @Test   // 1. Все невалидные данные
    public void NoRegWithInvalidData() throws InterruptedException {

        WebElement firsNameInputField = driver.findElement(By.cssSelector(".first_block .form-group:nth-child(1) input"));
        firsNameInputField.sendKeys(" ");
        WebElement lastNameInputField = driver.findElement(By.xpath("// div[@class='first_block']//input[@class='form-control second']"));
        lastNameInputField.sendKeys("0007");
        WebElement emailInputField = driver.findElement(By.xpath("// div[@class='first_block']//input[@class='form-control third']"));
        emailInputField.sendKeys(".com");
        WebElement phoneInputField = driver.findElement(By.cssSelector("input[placeholder='Input your phone:']"));
        phoneInputField.sendKeys("+0");
        assertEquals("Input your phone:", phoneInputField.getAttribute("placeholder"));
        WebElement addressInputField = driver.findElement(By.cssSelector("input[placeholder='Input your address:']"));
        addressInputField.sendKeys("/$/ ,$&%% $) )86)))");
        assertEquals("Input your address:", addressInputField.getAttribute("placeholder"));
        sleep(1000);
        WebElement submitButton = driver.findElement(By.cssSelector("[type='submit']"));
        submitButton.click();

        WebElement successMessage = driver.findElement(By.tagName("h1"));
        Assert.assertEquals(successMessage.getText(), "Congratulations! You have successfully registered!", successMessage.getText());
        assertEquals("Заповніть це поле.", successMessage.getAttribute("validationMessage"));  // Заповніть це поле.
        assertTrue(firsNameInputField.getText().contains("Заповніть"));
        assertTrue(lastNameInputField.getText().contains("Заповніть"));
        assertTrue(emailInputField.getText().contains("Заповніть"));
        sleep(1000);
    }

    @Test   // 2. Слишком короткое имя
    public void testShortNameValidation() throws InterruptedException {
        WebElement firstNameInputField = driver.findElement(By.cssSelector("input[placeholder='Input your first name']"));
        firstNameInputField.sendKeys("J");
        WebElement lastNameInputField = driver.findElement(By.xpath("// div[@class='first_block']//input[@class='form-control second']"));
        lastNameInputField.sendKeys("Brandon");
        WebElement emailInputField = driver.findElement(By.xpath("// div[@class='first_block']//input[@class='form-control third']"));
        emailInputField.sendKeys("qa23@gmail.com");
        WebElement submitButton = driver.findElement(By.cssSelector("[type='submit']"));
        submitButton.click();
//        WebElement firstNameInputField =
//                driver.findElement(By.cssSelector("input[placeholder='Input your first name']"));
//        assertEquals("Заповніть це поле.", firstNameInputField.getAttribute("validationMessage"));
        WebElement errorMessage = driver.findElement(By.cssSelector("h1"));
        Assert.assertEquals(errorMessage.getAttribute("validationMessage"), "The value length is too short");
    }

    @Test   // 3. Слишком длинное имя
    public void testLongNameValidation() throws InterruptedException {
        WebElement firstNameInputField = driver.findElement(By.cssSelector(".first_block .form-group:nth-child(1) input"));
        firstNameInputField.sendKeys("VeryVeryVeryLoooooooooooooooooooooooongFirstName");
        WebElement lastNameInputField = driver.findElement(By.xpath("// div[@class='first_block']//input[@class='form-control second']"));
        lastNameInputField.sendKeys("Brandon");
        WebElement emailInputField = driver.findElement(By.xpath("// div[@class='first_block']//input[@class='form-control third']"));
        emailInputField.sendKeys("qa23@gmail.com");
        WebElement submitButton = driver.findElement(By.cssSelector("[type='submit']"));
        submitButton.click();
        WebElement errorMessage = driver.findElement(By.cssSelector("h1"));
        sleep(3000);
        Assert.assertEquals(errorMessage.getAttribute("validationMessage"), "The value length is too long");
        driver.quit();
    }

    @Test   // 4. Заполнение только необходимых полей невалидными данными:
    public void testInvalidRequiredFields() throws InterruptedException {
        WebElement firstNameInputField = driver.findElement(By.cssSelector(".first_block .form-group:nth-child(1) input"));
        firstNameInputField.sendKeys("&&/");
        WebElement lastNameInputField = driver.findElement(By.xpath("// div[@class='first_block']//input[@class='form-control second']"));
        lastNameInputField.sendKeys(")   87");
        WebElement emailInputField = driver.findElement(By.xpath("// div[@class='first_block']//input[@class='form-control third']"));
        emailInputField.sendKeys("Teeeeestgmailcom");
        WebElement submitButton = driver.findElement(By.cssSelector("[type='submit']"));
        submitButton.click();
        sleep(3000);

        WebElement errorMessage = driver.findElement(By.cssSelector("h1"));
        Assert.assertEquals("Congratulations! You have successfully registered!", errorMessage.getText());
        driver.quit();
    }

    @Test   // 5. Заполнение только необязательных полей правильно, а обязательных - невалидными данными:
    public void testInvalidRequiredFieldsWithValidOptionalFields() {

        WebElement firstNameInput = driver.findElement(By.cssSelector(".first_block .form-group:nth-child(1) input"));
        firstNameInput.sendKeys("John123");
        WebElement lastNameInput = driver.findElement(By.cssSelector("input[placeholder='Input your last name']"));
        lastNameInput.sendKeys("Doe456");
        WebElement emailInput = driver.findElement(By.cssSelector("input[placeholder='Input your email']"));
        emailInput.sendKeys("email@gmail");
        WebElement phoneInputField = driver.findElement(By.cssSelector("input[placeholder='Input your phone:']"));
        phoneInputField.sendKeys("+1602361835");
        WebElement addressInputField = driver.findElement(By.cssSelector("input[placeholder='Input your address:']"));
        addressInputField.sendKeys("Berlin, Germany, 15438");
        WebElement submitButton = driver.findElement(By.cssSelector("[type='submit']"));
        submitButton.click();
        WebElement successMessage = driver.findElement(By.cssSelector("h1"));
        Assert.assertEquals(successMessage.getText(), "Congratulations! You have successfully registered!");
        driver.quit();
    }

    @Test   // 6. Заполнение одного обязательного поля и одного необязательного поля, остальные пустые:
    public void testOneRequiredOneOptionalField() {

        WebElement firstNameInput = driver.findElement(By.cssSelector(".first_block .form-group:nth-child(1) input"));
        firstNameInput.sendKeys("John");
        WebElement addressInputField = driver.findElement(By.cssSelector("input[placeholder='Input your address:']"));
        addressInputField.sendKeys("New York, 23012");
        WebElement submitButton = driver.findElement(By.cssSelector("[type='submit']"));
        submitButton.click();
        WebElement successMessage = driver.findElement(By.cssSelector("h1"));
        Assert.assertEquals(successMessage.getText(), " Success message not found", "Congratulations! You have successfully registered!");
        driver.quit();
    }


    @Test   // 7. Заполнение всех полей пустыми значениями:
    public void testEmptyFields() throws InterruptedException {
        WebElement firstNameInput = driver.findElement(By.cssSelector(".first_block .form-group:nth-child(1) input"));
        firstNameInput.sendKeys(" ");
        WebElement lastNameInput = driver.findElement(By.cssSelector("input[placeholder='Input your last name']"));
        lastNameInput.sendKeys(" ");
        WebElement emailInput = driver.findElement(By.cssSelector("input[placeholder='Input your email']"));
        emailInput.sendKeys(" ");
        WebElement phoneInputField = driver.findElement(By.cssSelector("input[placeholder='Input your phone:']"));
        phoneInputField.sendKeys(" ");
        WebElement addressInputField = driver.findElement(By.cssSelector("input[placeholder='Input your address:']"));
        addressInputField.sendKeys(" ");
        WebElement submitButton = driver.findElement(By.cssSelector("[type='submit']"));
        submitButton.click();
        WebElement errorMessage = driver.findElement(By.cssSelector("h1"));
        Assert.assertEquals(errorMessage.getAttribute("validationMessage"), "Error message not found");
        sleep(3000);
        driver.quit();
        // "Oops! All fields are required."
//        Assert.assertEquals(errorMessage.getText(), "Oops! All fields are required.", "Error message not found");

        }

    }






