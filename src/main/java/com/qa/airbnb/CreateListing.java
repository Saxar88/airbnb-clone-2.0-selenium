package com.qa.airbnb;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateListing {
    public static void main(String[] args) {
        List<String> browsers = Arrays.asList("chrome", "firefox");

        for (String browser : browsers) {
            WebDriver driver;

            if (browser.equals("chrome")) {
                System.setProperty("webdriver.chrome.driver", "C:/Users/W10/Code/bin/chromedriver.exe");
                driver = new ChromeDriver();
            } else if (browser.equals("firefox")) {
                System.setProperty("webdriver.gecko.driver", "C:/Users/W10/Code/bin/geckodriver.exe");
                FirefoxOptions options = new FirefoxOptions();
                options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
                driver = new FirefoxDriver(options);
            } else {
                continue;
            }

            try {
                driver.get("https://rent-site-clone-2-0.vercel.app/");

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement userMenu = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("user-menu")));
                userMenu.click();

                WebElement signUpButton = wait
                        .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Log in']")));
                signUpButton.click();

                WebElement emailField = driver.findElement(By.id("email"));
                emailField.sendKeys("test-java@test.com");
                WebElement passwordField = driver.findElement(By.id("password"));
                passwordField.sendKeys("test-java-123");
                WebElement continueButton = driver.findElement(By.xpath("//*[text()='Continue']"));
                continueButton.click();

                wait.until(
                        ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Logged in successfully!']")));

                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("modal")));
                Thread.sleep(500);
                WebElement rentButton = wait
                        .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Airbnb your home']")));
                rentButton.click();

                WebElement windmillsCategory = wait
                        .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Windmills']")));
                windmillsCategory.click();

                WebElement nextButton = driver.findElement(By.xpath("//*[text()='Next']"));
                nextButton.click();

                WebElement countryInputContainer = wait.until(
                        ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='text-lg css-19bb58m']")));
                countryInputContainer.click();
                WebElement countryInput = driver.findElement(By.id("react-select-2-input"));
                countryInput.sendKeys("Greece");
                nextButton.click();

                WebElement addGuestButton = wait.until(
                        ExpectedConditions.presenceOfElementLocated(
                                By.xpath("//div//div//div//div//div//div[2]//div[1]//div[3]//div[3]")));
                for (int i = 0; i < 3; i++) {
                    addGuestButton.click();
                }
                WebElement addRoomButton = driver.findElement(By.xpath("//div[3]//div[1]//div[3]//div[3]"));
                addRoomButton.click();
                nextButton.click();

                WebElement imageInputContainer = wait
                        .until(ExpectedConditions
                                .presenceOfElementLocated(By.id("image-upload")));
                imageInputContainer.sendKeys(Keys.ENTER);
                WebElement imageInput = wait
                        .until(ExpectedConditions.presenceOfElementLocated(By.name("file")));
                imageInput.sendKeys("C:\\Users\\W10\\Desktop\\Anemomilos.webp");
                nextButton.click();

                WebElement titleInput = wait
                        .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Title']")));
                titleInput.sendKeys("Anemomilos");
                WebElement descriptionInput = driver.findElement(By.xpath("//*[text()='Description']"));
                descriptionInput.sendKeys(
                        "It is a real old windmill that was used to grind wheat. This operation stopped with the appearance of the machine mills, and a few years later it was turned into a residence. It has 3 floors. The kitchen and the bathroom are on the ground floor, the children's bedroom is upstairs, and on the third floor there is the main bedroom with a 360 degrees view of the endless blue sea, but also the mountainous part of the island. Outside there is a pool, a kitchen, and a shower.");
                nextButton.click();

                WebElement priceInput = wait
                        .until(ExpectedConditions.presenceOfElementLocated(By.id("price")));
                priceInput.click();
                priceInput.sendKeys(Keys.BACK_SPACE, "248");
                WebElement createButton = driver.findElement(By.xpath("//*[text()='Create']"));
                createButton.click();

                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Listing created!']")));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            driver.quit();
        }
    }
}
