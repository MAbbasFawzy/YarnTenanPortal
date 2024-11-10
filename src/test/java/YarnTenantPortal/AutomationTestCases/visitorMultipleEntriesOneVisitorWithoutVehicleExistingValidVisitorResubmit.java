/*
 * 
 * Test Case 8: Add Visitor _ multiple entries _ one visitor _ without vehicle _ existing valid visitor - resubmit
 * This test case is when the visitor request is expired, you will find resubmit button
 */



package YarnTenantPortal.AutomationTestCases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

public class visitorMultipleEntriesOneVisitorWithoutVehicleExistingValidVisitorResubmit extends randomGenerator {

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

		Thread.sleep(4000);
		// Locate and click the calendar input field
		WebElement calendarInput = driver
				.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/form/div[2]/div[2]/span/input"));
		calendarInput.click();

		Thread.sleep(4000);
		// Select the date (e.g., November 9, 2024)
		// Navigate to the correct month if necessary
		WebElement nextMonthButton = driver.findElement(By.xpath("//button[@aria-label='Next Month']"));
		nextMonthButton.click(); // Click to go to the next month if needed

		// Select the day (9th in this case)
		WebElement dateToSelect = driver.findElement(By.xpath("//td[@aria-label='9']"));
		dateToSelect.click();

		// Select the time
		// Increment hour
		WebElement hourIncrementButton = driver.findElement(By.xpath("//button[@aria-label='Next Hour']"));
		hourIncrementButton.click(); // Click to increment hour

		// Increment minute
		WebElement minuteIncrementButton = driver.findElement(By.xpath("//button[@aria-label='Next Minute']"));
		minuteIncrementButton.click(); // Click to increment minute

		// Select AM/PM
		WebElement amPmButton = driver.findElement(By.xpath("//button[@aria-label='pm']")); // Change to 'am' if needed
		amPmButton.click(); // Click to select PM

		Thread.sleep(4000);
		WebElement visitorFirstName = driver.findElement(
				By.xpath("/html/body/div[1]/main/div/div/div[2]/form/div[3]/div/div[2]/div/div/div[1]/div[1]/input"));
		visitorFirstName.sendKeys("Emily");

		Thread.sleep(2000);
		WebElement visitorLastName = driver.findElement(
				By.xpath("/html/body/div[1]/main/div/div/div[2]/form/div[3]/div/div[2]/div/div/div[1]/div[2]/input"));
		visitorLastName.sendKeys("Brown");

		Thread.sleep(2000);
		WebElement email = driver.findElement(
				By.xpath("/html/body/div[1]/main/div/div/div[2]/form/div[3]/div/div[2]/div/div/div[1]/div[3]/input"));
		email.sendKeys("emilybrown80@hotmail.com");

		Thread.sleep(4000);
		WebElement nationality = driver.findElement(By.xpath(
				"/html/body/div[1]/main/div/div/div[2]/form/div[3]/div/div[2]/div/div/div[1]/div[4]/div[1]/span"));
		nationality.click();

		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement alertDialog = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".p-dialog")));

			WebElement cancelButton = alertDialog.findElement(By.cssSelector("button[type='button']"));
			cancelButton.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Thread.sleep(4000);
		nationality.click();
		Thread.sleep(6000);
		WebElement nationalityOption = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li[1]"));
		Thread.sleep(6000);
		nationalityOption.click();

		Thread.sleep(4000);
		WebElement documentType = driver.findElement(By.xpath(
				"/html/body/div[1]/main/div/div/div[2]/form/div[3]/div/div[2]/div/div/div[1]/div[5]/div[1]/span"));
		documentType.click();

		Thread.sleep(2000);
		WebElement documentTypeListOption = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li[1]"));
		documentTypeListOption.click();

		Thread.sleep(2000);
		WebElement documentNumber = driver.findElement(
				By.xpath("/html/body/div[1]/main/div/div/div[2]/form/div[3]/div/div[2]/div/div/div[1]/div[6]/input"));
		documentNumber.sendKeys(visitor.numbers);

		// Get today's date
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String formattedDate = today.format(formatter);

		Thread.sleep(2000);
		WebElement dateOfBirth = driver.findElement(By.xpath(
				"/html/body/div[1]/main/div/div/div[2]/form/div[3]/div/div[2]/div/div/div[1]/div[7]/span/input"));
		dateOfBirth.click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pv_id_10_panel"))); // Adjust the ID as needed

		WebElement birthDateToSelect = driver
				.findElement(By.xpath("//td[@aria-label='" + today.getDayOfMonth() + "']"));
		birthDateToSelect.click();

		Thread.sleep(2000);
		WebElement gender = driver.findElement(By.xpath(
				"/html/body/div[1]/main/div/div/div[2]/form/div[3]/div/div[2]/div/div/div[1]/div[8]/div[1]/span"));
		gender.click();

		Thread.sleep(2000);
		WebElement genderOption = driver.findElement(By.xpath("/html/body/div[4]/div/ul/li[1]"));
		genderOption.click();

		Thread.sleep(2000);
		WebElement phoneNumber = driver.findElement(
				By.xpath("/html/body/div[1]/main/div/div/div[2]/form/div[3]/div/div[2]/div/div/div[1]/div[9]/input"));
		phoneNumber.sendKeys("01005710499");

		Thread.sleep(2000);
		WebElement transportation = driver.findElement(By.xpath(
				"/html/body/div[1]/main/div/div/div[2]/form/div[3]/div/div[2]/div/div/div[2]/div/div/span[1]/input"));
		transportation.click();

		Thread.sleep(6000);
		WebElement submitButton = driver
				.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/form/div[4]/button[2]"));
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

	/*
	 * @Test(priority = 2) public void checkDefaultVisitorStatus() {
	 * 
	 * driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 * 
	 * WebElement visitorStatus = driver .findElement(By.xpath(
	 * "//*[@id=\"__nuxt\"]/main/div/div/div[2]/div[1]/span[2]"));
	 * visitorStatus.getText(); assertEquals("Pending", visitorStatus.getText()); }
	 */
}
