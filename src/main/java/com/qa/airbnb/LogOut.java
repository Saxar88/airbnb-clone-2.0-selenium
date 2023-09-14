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

public class LogOut {
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

            driver.get("https://rent-site-clone-2-0.vercel.app/");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement userMenu = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("user-menu")));
            userMenu.click();

            WebElement signUpButton = wait
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Log in']")));
            signUpButton.click();

            driver.findElement(By.id("email")).sendKeys("test-java@test.com");
            driver.findElement(By.id("password")).sendKeys("test-java-123");
            driver.findElement(By.xpath("//*[text()='Continue']")).click();

            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Logged in successfully!']")));

            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("modal")));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Log out']"))).click();
            driver.quit();
        }
    }
}