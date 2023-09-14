package com.qa.airbnb;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignIn {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.get("https://rent-site-clone-2-0.vercel.app/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement userMenu = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("user-menu")));
        userMenu.click();

        WebElement signUpButton = wait
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Sign up']")));
        signUpButton.click();

        driver.findElement(By.id("email")).sendKeys("test-java@test.com");
        driver.findElement(By.id("name")).sendKeys("Test Java");
        driver.findElement(By.id("password")).sendKeys("test-java-123");
        driver.findElement(By.xpath("//*[text()='Continue']")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Account created!']")));

        driver.quit();
    }
}
