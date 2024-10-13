package YarnTenantPortal.AutomationTestCases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class visitorMultipleEntriesOneVisitorWithoutVehicleExistingValidVisitorResubmit {

	WebDriver driver = new FirefoxDriver();
	WebDriverWait wait;
	private String baseUrl;
	private String username;
	private String password;

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
		AssertJUnit.assertEquals("Mahmoud Abbas", userName.getText());

		Thread.sleep(2000);
	}

	@Test(priority = 0)
	public void chooseVisitorTabAndAddVisitor() throws InterruptedException {

		randomGenerator.Visitor visitor = randomGenerator.generateRandomContact();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		Thread.sleep(10000);
		WebElement visitorTab = driver.findElement(By.linkText("My Visitors"));
		visitorTab.click();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement addVisitorButton = driver.findElement(By.cssSelector(
				"#__nuxt > main > div > div > div.hidden.sm\\:block > div:nth-child(2) > div > div > button"));
		addVisitorButton.click();

		Thread.sleep(10000);
		WebElement visitorTypeList = driver.findElement(By.cssSelector("#pv_id_5 > span"));
		visitorTypeList.click();

		Thread.sleep(8000);
		WebElement visitorTypeListOption = driver.findElement(By.id("pv_id_5_2"));
		visitorTypeListOption.click();

		Thread.sleep(2000);
		WebElement entryType = driver.findElement(By.id("multiple"));
		entryType.click();

		Thread.sleep(2000);
		WebElement visitEndDate = driver
				.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[2]/form/div[2]/div[2]/input"));
		visitEndDate.sendKeys("12/31/2024" + "02" + "30" + "P");

		Thread.sleep(2000);
		WebElement visitorFirstName = driver
				.findElement(By.xpath("//*[@id=\"pv_id_9_0_content\"]/div/div/div[1]/div[1]/input"));
		visitorFirstName.sendKeys("abbas");

		Thread.sleep(2000);
		WebElement visitorLastName = driver
				.findElement(By.xpath("//*[@id=\"pv_id_9_0_content\"]/div/div/div[1]/div[2]/input"));
		visitorLastName.sendKeys("adham");

		Thread.sleep(2000);
		WebElement email = driver.findElement(By.xpath("//*[@id=\"pv_id_9_0_content\"]/div/div/div[1]/div[3]/input"));
		email.sendKeys("a.adham@gmail.com");

		Thread.sleep(6000);
		WebElement nationality = driver.findElement(By.xpath("//*[@id=\"pv_id_6\"]/span"));
		nationality.click();

		// Wait for the alert dialog to appear
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement alertDialog = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".p-dialog")));

		// Click on the resubmit button
		WebElement resubmitButton = alertDialog
				.findElement(By.xpath("/html/body/div[4]/div/div[2]/div/div[4]/button[1]"));
		resubmitButton.click();

		Thread.sleep(4000);
		WebElement resubmittedVisitEndDate = driver
				.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/form/div[5]/input"));
		resubmittedVisitEndDate.sendKeys("12/31/2024" + "02" + "30" + "P");

		Thread.sleep(4000);
		WebElement resubmitVisitorRequestButton = driver
				.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/form/div[9]/button"));
		resubmitVisitorRequestButton.click();

	}

	@Test(priority = 1)
	public void checkAlertForAddingResubmittedVisitors() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement successMessage = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".p-toast > div:nth-child(1)")));

		String alertMessageText = successMessage.getText();

		assertEquals(alertMessageText, alertMessageText);

	}

}
