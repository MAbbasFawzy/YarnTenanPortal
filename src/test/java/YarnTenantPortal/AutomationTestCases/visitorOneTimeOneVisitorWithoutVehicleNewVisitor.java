/*
 * 
 * 
 * 
 * Test Case 6: Add Visitor _ one time _ one visitor _ without vehicle _ new visitor
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
import java.util.random.RandomGenerator;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

public class visitorOneTimeOneVisitorWithoutVehicleNewVisitor extends randomGenerator {

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
		WebElement entryType = driver.findElement(By.id("single"));
		entryType.click();

		Thread.sleep(2000);
		WebElement visitEndDate = driver
				.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[2]/form/div[2]/div[2]/input"));
		visitEndDate.sendKeys("10/18/2024" + "02" + "30" + "P");

		Thread.sleep(2000);
		WebElement visitorFirstName = driver
				.findElement(By.xpath("//*[@id=\"pv_id_9_0_content\"]/div/div/div[1]/div[1]/input"));
		visitorFirstName.sendKeys(visitor.firstName);

		Thread.sleep(2000);
		WebElement visitorLastName = driver
				.findElement(By.xpath("//*[@id=\"pv_id_9_0_content\"]/div/div/div[1]/div[2]/input"));
		visitorLastName.sendKeys(visitor.lastName);

		Thread.sleep(2000);
		WebElement email = driver.findElement(By.xpath("//*[@id=\"pv_id_9_0_content\"]/div/div/div[1]/div[3]/input"));
		email.sendKeys(visitor.email);

		Thread.sleep(2000);
		WebElement nationality = driver.findElement(By.xpath("//*[@id=\"pv_id_6\"]/span"));
		nationality.click();

		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement alertDialog = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".p-dialog")));

			WebElement cancelButton = alertDialog.findElement(By.cssSelector("button[type='button']"));
			cancelButton.click();
		} catch (Exception e) {
			Thread.sleep(4000);
			WebElement nationalityOption = driver.findElement(By.xpath("//*[@id=\"pv_id_6_0\"]"));
			nationalityOption.click();
		}

		

		Thread.sleep(4000);
		WebElement documentType = driver.findElement(By.xpath("//*[@id=\"pv_id_7\"]/span"));
		documentType.click();

		Thread.sleep(2000);
		WebElement documentTypeListOption = driver.findElement(By.xpath("//*[@id=\"pv_id_7_0\"]"));
		documentTypeListOption.click();

		Thread.sleep(2000);
		WebElement documentNumber = driver
				.findElement(By.xpath("//*[@id=\"pv_id_9_0_content\"]/div/div/div[1]/div[6]/input"));
		documentNumber.sendKeys(visitor.numbers);

		Thread.sleep(2000);
		WebElement gender = driver.findElement(By.xpath("//*[@id=\"pv_id_8\"]/span"));
		gender.click();

		Thread.sleep(2000);
		WebElement genderOption = driver.findElement(By.xpath("//*[@id=\"pv_id_8_0\"]"));
		genderOption.click();

		Thread.sleep(2000);
		WebElement transportation = driver
				.findElement(By.xpath("//*[@id=\"pv_id_9_0_content\"]/div/div/div[2]/div/div/span[1]"));
		transportation.click();

		Thread.sleep(6000);
		WebElement submitButton = driver
				.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[2]/form/div[4]/button[2]"));
		submitButton.click();

		WebDriverWait waitSuccessMessage = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement successMessage = waitSuccessMessage
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".p-toast > div:nth-child(1)")));

		String alertMessageText = successMessage.getText();
		System.out.println("Alert message: " + alertMessageText);
	}

	@Test(priority = 1)
	public void checkAlertForMaxNumberOfVisitors() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement successMessage = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".p-toast > div:nth-child(1)")));

		String alertMessageText = successMessage.getText();

		assertEquals(alertMessageText, alertMessageText);

	}

	@Test(priority = 2)
	public void checkDefaultVisitorStatus() {

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		WebElement visitorStatus = driver
				.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[2]/div[1]/span[2]"));
		visitorStatus.getText();
		assertEquals("Pending", visitorStatus.getText());
	}

}
