package com.example.conversor;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ConversorUITest {

    private WebDriver driver;
    private WebDriverWait wait;
    private String baseUrl;

    @BeforeAll
    void setupClass() {
        // URL base: usar variable de entorno si está definida (ideal en CI)
        String envUrl = System.getenv("BASE_URL");
        baseUrl = (envUrl != null && !envUrl.isBlank())
                ? envUrl
                : "http://localhost:8080/conversor";

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage", "--window-size=1366,768");
        // Opcional: desactivar GPU/infobars para entornos CI
        options.addArguments("--disable-gpu", "--disable-infobars");
        driver = new ChromeDriver(options);

        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @AfterAll
    void teardownClass() {
        if (driver != null) driver.quit();
    }

    @BeforeEach
    void openPage() {
        driver.get(baseUrl);
        // Esperar a que el input esté presente antes de cada test
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("inputNumber")));
    }

    @Test
    void testConvertToBinary() {
        WebElement input = driver.findElement(By.id("inputNumber"));
        input.clear();
        input.sendKeys("10");

        WebElement button = driver.findElement(By.xpath("//button[contains(text(),'Convertir a Binario')]"));
        button.click();

        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("result")));
        Assertions.assertTrue(result.getText().contains("1010"),
                "El resultado debería contener 1010 (binario de 10). Texto actual: " + result.getText());
    }

    @Test
    void testConvertToInteger() {
        WebElement input = driver.findElement(By.id("inputNumber"));
        input.clear();
        input.sendKeys("1010");

        WebElement button = driver.findElement(By.xpath("//button[contains(text(),'Convertir a Entero')]"));
        button.click();

        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("result")));
        Assertions.assertTrue(result.getText().contains("10"),
                "El resultado debería contener 10 (entero de 1010). Texto actual: " + result.getText());
    }

    @Test
    void testConvertToRoman() {
        WebElement input = driver.findElement(By.id("inputNumber"));
        input.clear();
        input.sendKeys("1994");

        WebElement button = new WebDriverWait(driver, Duration.ofSeconds(5))
            .until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(normalize-space(.),'Convertir a Romano')]")
            ));
        button.click();

        WebElement result = new WebDriverWait(driver, Duration.ofSeconds(5))
            .until(ExpectedConditions.visibilityOfElementLocated(By.id("result")));
        Assertions.assertTrue(result.getText().contains("MCMXCIV"),
                "El resultado debería contener MCMXCIV (romano de 1994). Texto actual: " + result.getText());
    }
}
