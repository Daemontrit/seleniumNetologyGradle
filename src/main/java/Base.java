import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Base {

    @BeforeAll
    static void configWebdriver() {
        System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
    }

    private WebDriver driver;

    @BeforeEach
    void setUp() {
        driver=new ChromeDriver();
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--disable-dev-shm-usage");
//        options.addArguments("--no-sandbox");
//        options.addArguments("--headless");
//        driver = new ChromeDriver(options);

    }

    @Test
    void loginTest() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Петр Первый");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79660778844");
        driver.findElement(By.cssSelector("[data-test-id='agreement'] span")).click();
        driver.findElement(By.cssSelector("[class='form-field form-field_size_m form-field_theme_alfa-on-white'] button")).click();

        String actualText = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText();
        Assertions.assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", actualText.strip());

    }

    @Test
    void unAcsessAnValidName() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Ivan Perviy");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79660778844");
        driver.findElement(By.cssSelector("[data-test-id='agreement'] span")).click();
        driver.findElement(By.cssSelector("[class='form-field form-field_size_m form-field_theme_alfa-on-white'] button")).click();

        String errorMessage = driver.findElement(By.xpath("//span[@class[contains(.,'input_invalid')]]//span[@class='input__sub']")).getText();
        Assertions.assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", errorMessage);
    }
    @Test
    void unAcsessAnValidPhone() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Николай Афанасий");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+888");
        driver.findElement(By.cssSelector("[data-test-id='agreement'] span")).click();
        driver.findElement(By.cssSelector("[class='form-field form-field_size_m form-field_theme_alfa-on-white'] button")).click();

        String errorMessage = driver.findElement(By.xpath("//span[@class[contains(.,'input_invalid')]]//span[@class='input__sub']")).getText();
        Assertions.assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", errorMessage);
    }
    @Test
    void unAcsessAnValidNameAndPhone() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Ivan Perviy");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+888");
        driver.findElement(By.cssSelector("[data-test-id='agreement'] span")).click();
        driver.findElement(By.cssSelector("[class='form-field form-field_size_m form-field_theme_alfa-on-white'] button")).click();

        String errorMessage = driver.findElement(By.xpath("//span[@class[contains(.,'input_invalid')]]//span[@class='input__sub']")).getText();
        Assertions.assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", errorMessage);
    }


    @AfterEach
    void down() {
        driver.quit();
    }
}

