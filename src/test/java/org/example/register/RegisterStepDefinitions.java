package org.example.register;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.lang3.RandomStringUtils;

public class RegisterStepDefinitions {

    private WebDriver driver;

    @Given("user fill form with new data")
    public void fillFormNewData() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://multibags.1dt.com.br/shop/customer/registration.html");

        WebElement firstName = driver.findElement(By.id("firstName"));
        WebElement lastName = driver.findElement(By.id("lastName"));
        WebElement state = driver.findElement(By.id("hidden_zones"));
        WebElement email = driver.findElement(By.id("emailAddress"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement repeatPassword = driver.findElement(By.id("passwordAgain"));

        String randomString = RandomStringUtils.randomAlphabetic(8);

        firstName.sendKeys("Seu Zé ");
        lastName.sendKeys("Do Teste");
        state.sendKeys("São Paulo");
        email.sendKeys(randomString + "seuze@test.com");
        password.sendKeys("123456");
        repeatPassword.sendKeys("123456");
    }

    @Given("user fill form with already registered email")
    public void fillFormExistentEmail() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://multibags.1dt.com.br/shop/customer/registration.html");

        WebElement firstName = driver.findElement(By.id("firstName"));
        WebElement lastName = driver.findElement(By.id("lastName"));
        WebElement state = driver.findElement(By.id("hidden_zones"));
        WebElement email = driver.findElement(By.id("emailAddress"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement repeatPassword = driver.findElement(By.id("passwordAgain"));

        firstName.sendKeys("Seu Zé ");
        lastName.sendKeys("Do Teste");
        state.sendKeys("São Paulo");
        email.sendKeys("seuze@test.com");
        password.sendKeys("123456");
        repeatPassword.sendKeys("123456");
    }

    @When("user clicks create account")
    public void clickCreateAccount() {
        WebElement createAccountButton = driver.findElement(By.className("login-btn"));
        createAccountButton.click();
    }

    @Then("the page is redirected to customer dashboard")
    public void pageRedirect() {
        new WebDriverWait(driver,10L).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getCurrentUrl().startsWith("http://multibags.1dt.com.br/shop/customer/dashboard.html");
            }
        });
        driver.quit();
    }

    @Then("an error message is displayed")
    public void errorMessage() {
        new WebDriverWait(driver,10L).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.id("customer.errors")).isDisplayed();
            }
        });
        driver.quit();
    }
}
