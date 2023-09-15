package com.qa.airbnb;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CancelClientReservation {
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
                emailField.sendKeys("test1@test.com");
                WebElement passwordField = driver.findElement(By.id("password"));
                passwordField.sendKeys("test123");
                WebElement continueButton = driver.findElement(By.xpath("//*[text()='Continue']"));
                continueButton.click();

                wait.until(
                        ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Logged in successfully!']")));

                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("modal")));

                Thread.sleep(500);

                WebElement myReservationsButton = driver
                        .findElement(By.xpath("//*[text()='My reservations']"));
                myReservationsButton.click();
                WebElement startDate = wait
                        .until(ExpectedConditions.presenceOfElementLocated(
                                By.xpath("/html[1]/body[1]/div[3]/div[1]/div[2]/div[1]/div[1]/button[1]")));
                startDate.click();
                WebElement endDate = wait
                        .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='26']")));
                endDate.click();
                WebElement reserveButton = driver.findElement(By.xpath("//*[text()='Reserve']"));
                reserveButton.click();

                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Reservation made!']")));

                WebElement cancelFirstReservationButton = wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("/html[1]/body[1]/div[3]/div[1]/div[2]/div[1]/div[1]/button[1]")));
                cancelFirstReservationButton.click();

                wait.until(ExpectedConditions.invisibilityOfElementLocated(
                        By.xpath("//*[text()='Reservation cancelled']")));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            driver.quit();
        }
    }
}
