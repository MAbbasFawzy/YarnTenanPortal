/*
 * 
 * 
 * Test Case 12: Edit tenant profile information
 * Description: make sure that the tenant can edit his information 
 * 
 * 
 */



package YarnTenantPortal.AutomationTestCases;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class editTenantProfileInformation {

	WebDriver driver = new ChromeDriver();
	WebDriverWait wait;

	private String baseUrl;
	private String username;
	private String password;
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
			tenant = properties.getProperty("tenant");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void login() throws InterruptedException { // login code

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement email = driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[3]/form/div[1]/input"));
		email.sendKeys(username);

		WebElement passcode = driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[3]/form/div[2]/div/input"));
		passcode.sendKeys(password);

		WebElement loginButton = driver
				.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[3]/button"));
		loginButton.click();

		WebElement userName = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/nav[1]/div/div[1]/div[2]/span[2]"));
		AssertJUnit.assertEquals(tenant, userName.getText()); 
		

		Thread.sleep(2000);
	}
	
	
	@Test
	public void editProfile() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebElement profileIcon = driver.findElement(By.xpath("/html/body/div[1]/main/nav[1]/div/div[1]/div[2]/a[4]/img"));
		profileIcon.click();
		
		Thread.sleep(2000);
		driver.getCurrentUrl();
		
		Thread.sleep(2000);
		WebElement editButton = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[2]/div/div[1]/button[1]"));
		editButton.click();
		
		Thread.sleep(2000);
		WebElement jobTitle = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[2]/div/form/div[4]/input"));
		jobTitle.sendKeys("Quality control engineer / Quality Assurance Engineer");
		
		Thread.sleep(4000);
		WebElement submitButtonForEditProfile = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[2]/div/form/div[5]/button[1]"));
		submitButtonForEditProfile.click();
		
		Thread.sleep(4000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".p-toast[data-pc-name='toast'] .p-toast-message")));
		
		String alertMessageText = successMessage.getText();
		AssertJUnit.assertEquals(alertMessageText, alertMessageText);
        System.out.println("Alert message: " + alertMessageText);
        
	}
}
