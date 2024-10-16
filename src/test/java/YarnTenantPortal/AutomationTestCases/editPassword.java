/*
 * 
 * Test Case 13: Edit tenant portal password
 * Description: make sure that the tenant can edit his password and then logout and login again with the new password
 * 
 * 
 * 
 */





package YarnTenantPortal.AutomationTestCases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class editPassword extends base {

	
	WebDriver driver = new ChromeDriver();
	WebDriverWait wait;
	
	private String baseUrl;
    private String username;
    private String password;
    private String newpassword;
    private String tenant;
    
	
	
    @BeforeTest
    public void setup() throws InterruptedException {
        loadProperties();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.navigate().to(baseUrl);
        login();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    private void loadProperties() {
		Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }
            properties.load(input);
            baseUrl = properties.getProperty("base.url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
            newpassword = properties.getProperty("newpassword");
            tenant = properties.getProperty("tenant");
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

    private void login() throws InterruptedException {
        // login code
    	
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	WebElement email = driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[3]/form/div[1]/input"));
		email.sendKeys(username);

		WebElement passcode = driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[3]/form/div[2]/input"));
		passcode.sendKeys(password);

		WebElement loginButton = driver
				.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[3]/button"));
		loginButton.click();

		WebElement userName = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/nav[1]/div/div[1]/div[2]/span[2]"));
		AssertJUnit.assertEquals(tenant, userName.getText());

		Thread.sleep(2000);
    }

	
	
	@Test
	public void editProfilePassword() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebElement profileIcon = driver.findElement(By.xpath("/html/body/div[1]/main/nav[1]/div/div[1]/div[2]/a[4]/img"));
		profileIcon.click();
		
		Thread.sleep(2000);
		driver.getCurrentUrl();
		
		Thread.sleep(2000);
		WebElement editPasswordButton = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[2]/div/div[1]/button[2]"));
		editPasswordButton.click();
		
		Thread.sleep(2000);
		WebElement currentPasswordInput = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[2]/form/div[1]/input"));
		currentPasswordInput.sendKeys(password);
		
		Thread.sleep(2000);
		WebElement newPasswordInput = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[2]/form/div[2]/input"));
		newPasswordInput.sendKeys(newpassword);
		
		Thread.sleep(2000);
		WebElement confirmNewPassword = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[2]/form/div[3]/input"));
		confirmNewPassword.sendKeys(newpassword);
		
		Thread.sleep(2000);
		WebElement submitPasswordEdit = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[2]/form/div[4]/button[1]"));
		submitPasswordEdit.click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".p-toast[data-pc-name='toast'] .p-toast-message")));
		
		String alertMessageText = successMessage.getText();
        System.out.println("Alert message: " + "Alert message: Success");
		
		Thread.sleep(8000);
		
	}
	
	@Test(dependsOnMethods = "editProfilePassword")
	public void loginWithNewPassword() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		Thread.sleep(8000);
		WebElement logoutButton = driver.findElement(By.cssSelector("#__nuxt > main > nav.fixed.z-10.top-0.right-0.w-full.border.border-b.shadow-xl.bg-\\[var\\(--c1\\)\\].hidden.sm\\:block.print\\:\\!hidden > div > div.flex.justify-between.items-center.h-22.border-b > div.flex.items-center.gap-4 > a:nth-child(7) > svg"));
		logoutButton.click();
		
		Thread.sleep(10000);
		driver.getCurrentUrl();
		assertEquals("https://nakhla_sandbox.yarncloud.dev/tenant/auth/login", driver.getCurrentUrl());
		
		Thread.sleep(2000);
		WebElement email = driver.findElement(By.cssSelector("#__nuxt > main > div > div > div:nth-child(3) > form > div:nth-child(1) > input"));
		email.sendKeys(username);

		WebElement password = driver.findElement(By.cssSelector("#__nuxt > main > div > div > div:nth-child(3) > form > div:nth-child(2) > input"));
		password.sendKeys(newpassword);
		
		WebElement loginButton = driver
				.findElement(By.cssSelector("#__nuxt > main > div > div > div:nth-child(3) > form > div.mb-8 > button"));
		loginButton.click();
	}
}
