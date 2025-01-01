/*
 * 
 * 
 * Test Case 4: Subscribe to a service
 * Description: navigate to “services” tab and subscribe to a service (Ex: internet Service) and verify it appears in My requests - subscription with open status 
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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class subscribeToService {

	WebDriver driver = new FirefoxDriver();

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

		WebElement passcode = driver
				.findElement(By.xpath("/html/body/div[1]/main/div/div/div[3]/form/div[2]/div/input"));
		passcode.sendKeys(password);

		WebElement loginButton = driver
				.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[3]/button"));
		loginButton.click();

		WebElement userName = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/nav[1]/div/div[1]/div[2]/span[2]"));
		AssertJUnit.assertEquals(tenant, userName.getText());

		Thread.sleep(2000);
	}

	@Test
	public void chooseServicesSearch() throws InterruptedException {

		WebElement servicesButton = driver
				.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/nav[1]/div/div[2]/div[1]/a[2]"));
		servicesButton.click();

		WebElement searchBox = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[2]/div/div/input"));
		Thread.sleep(2000);
		searchBox.click();
		Thread.sleep(2000);
		searchBox.sendKeys("Internet");

		Thread.sleep(6000);
		WebElement serviceTypeButton = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/div"));
		serviceTypeButton.click();

		Thread.sleep(6000);
		WebElement serviceTitle = driver
				.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[2]/div/div[1]/h4/span[2]"));
		assertEquals(serviceTitle.getText(), serviceTitle.getText());

	}

	@Test(dependsOnMethods = "chooseServicesSearch")
	public void chooseServicesSearchAndSubscribe() throws InterruptedException {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement subscribeButton = driver
				.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[2]/div/div[1]/div/button[2]"));
		subscribeButton.click();

		Thread.sleep(4000);
		WebElement servicesList = driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/form/div[1]/div/div"));
		servicesList.click();

		Thread.sleep(2000);
		WebElement serviceOption = driver.findElement(By.xpath("//li[@aria-label='Internet Subscription - 12 Months']"));
		serviceOption.click();

		Thread.sleep(4000);
		WebElement serviceCategoryList = driver.findElement(By.xpath("/html[1]/body[1]/div[4]/div[1]/div[2]/form[1]/div[3]/div[1]"));
		serviceCategoryList.click();

		Thread.sleep(4000);
		WebElement subCategoryList = driver.findElement(By.xpath("/html[1]/body[1]/div[5]/div[2]/ul[1]/li[1]"));
		subCategoryList.click();

		Thread.sleep(1000);
		WebElement serviceDescription = driver
				.findElement(By.xpath("/html/body/div[4]/div/div[2]/form/div[5]/textarea"));
		serviceDescription.sendKeys("I want to subscribe to a higher network connection.");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement dateInput = driver
				.findElement(By.xpath("//input[@role='combobox' and @class='p-inputtext p-component']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", dateInput);

		// Click on the next button to navigate to November if needed
		WebElement nextButton = driver.findElement(By.className("p-datepicker-next"));
		nextButton.click(); // Click if you need to go to the next month

		// Wait for the date picker to update
		Thread.sleep(4000); // Wait for 1 second

		// Select the date (30)
		WebElement dateToSelect = driver.findElement(By.xpath("//td[@aria-label='30']"));
		dateToSelect.click();

		// Wait for the time picker to be visible (if necessary)
		Thread.sleep(4000); // Wait for 1 second

		// Set the hour (4 PM)
		WebElement hourIncrementButton = driver
				.findElement(By.xpath("//div[@class='p-hour-picker']//button[@aria-label='Next Hour']"));
		for (int i = 0; i < 4; i++) { // Increment to 4 PM
			hourIncrementButton.click();
		}

		// Set the minutes (33)
		WebElement minuteIncrementButton = driver
				.findElement(By.xpath("//div[@class='p-minute-picker']//button[@aria-label='Next Minute']"));
		for (int i = 0; i < 33; i++) { // Increment to 33 minutes
			minuteIncrementButton.click();
		}

		// Set AM/PM to PM
		WebElement ampmButton = driver.findElement(By.xpath("//div[@class='p-ampm-picker']//button[@aria-label='pm']"));
		ampmButton.click();


		Thread.sleep(2000);
		WebElement submitSubscriptionButton = driver
				.findElement(By.xpath("/html/body/div[4]/div/div[2]/form/div[8]/button"));
		submitSubscriptionButton.click();

		Thread.sleep(4000);
		
		WebElement successMessage = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector(".p-toast[data-pc-name='toast'] .p-toast-message")));

		String alertMessageText = successMessage.getText();
		System.out.println("Alert message: " + alertMessageText);

	}

}
