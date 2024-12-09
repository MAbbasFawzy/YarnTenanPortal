/*
 * 
 * 
 * Test Case 11: Add Visitor _ multiple entries _ group of visitors_ with vehicle Saudi & non-saudi _ one of the visitors is Blocked
 * 
 * 
 */

package YarnTenantPortal.AutomationTestCases;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class visitorMultipleEntriesGroupOfVisitorsWithVehicleSaudiNonSaudiOneVisitorBlocked {

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
		visitorFirstName.sendKeys(visitor.firstName + " " + 2);

		Thread.sleep(2000);
		WebElement visitorLastName = driver.findElement(
				By.xpath("/html/body/div[1]/main/div/div/div[2]/form/div[3]/div/div[2]/div/div/div[1]/div[2]/input"));
		visitorLastName.sendKeys(visitor.lastName + " " + 2);

		Thread.sleep(2000);
		WebElement email = driver.findElement(By.xpath(
				"/html/body/div[1]/main/div/div/div[2]/form/div[3]/div/div[2]/div/div/div[1]/div[3]/div[1]/input"));
		email.sendKeys(visitor.email);

		Thread.sleep(4000);
		WebElement nationality = driver.findElement(By.xpath(
				"/html/body/div[1]/main/div/div/div[2]/form/div[3]/div/div[2]/div/div/div[1]/div[4]/div[1]/span"));
		nationality.click();

		WebElement searchBox = driver.findElement(By.xpath("//input[@role='searchbox']"));
		searchBox.sendKeys("Egypt");

		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement alertDialog = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".p-dialog")));

			WebElement cancelButton = alertDialog.findElement(By.cssSelector("button[type='button']"));
			cancelButton.click();
		} catch (Exception e) {
			/*
			 * Thread.sleep(6000); WebElement nationalityOption =
			 * driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li[1]"));
			 * Thread.sleep(6000); nationalityOption.click();
			 */

			Thread.sleep(6000);
			WebElement nationalityOption = driver.findElement(By.xpath("/html[1]/body[1]/div[4]/div[2]/ul[1]/li[1]"));
			// WebElement nationalityOption =
			// driver.findElement(By.xpath("//li[@class='p-dropdown-item p-focus' and
			// @aria-label='Egypt']"));
			nationalityOption.click();

			/*
			 * Thread.sleep(6000); nationalityOption.click();
			 */
		}

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
		documentNumber.sendKeys(visitor.numbers + 2);

		// Format today's date
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String formattedDate = today.format(formatter);

		// Locate the date of birth input and click it
		WebElement dateOfBirth = driver.findElement(By.xpath(
				"/html/body/div[1]/main/div/div/div[2]/form/div[3]/div/div[2]/div/div/div[1]/div[7]/span/input"));
		dateOfBirth.click();

		// Wait for the date picker to be visible
		WebDriverWait waitBirthDate = new WebDriverWait(driver, Duration.ofSeconds(2));
		waitBirthDate
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='p-datepicker-group']"))); // Adjust the ID as needed
																														

		// Wait for the specific day to be clickable
		Thread.sleep(500);
		WebElement birthDateToSelect = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class='p-datepicker-today']")));
		Thread.sleep(2000);
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
		phoneNumber.sendKeys("01005710450");

		Thread.sleep(4000);
		WebElement transportation = driver.findElement(By.id("visitor-0-with-vehicle"));
		transportation.click();

		WebDriverWait waitCarPlate = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement carPlateType = waitCarPlate.until(ExpectedConditions.elementToBeClickable(By.id("pv_id_28")));
		carPlateType.click();

		Thread.sleep(4000);
		WebElement carPlateOption = driver.findElement(By.id("pv_id_28_0"));
		carPlateOption.click();

		Thread.sleep(2000);
		WebElement carPlateNumber = driver.findElement(By.xpath(
				"/html/body/div[1]/main/div/div/div[2]/form/div[3]/div/div[2]/div/div/div[2]/div[2]/div/div[2]/input"));
		carPlateNumber.sendKeys("1234");

		Thread.sleep(2000);
		WebElement carPlateLetter = driver.findElement(By.xpath(
				"/html/body/div[1]/main/div/div/div[2]/form/div[3]/div/div[2]/div/div/div[2]/div[2]/div/div[3]/input"));
		carPlateLetter.sendKeys("zxc");

		Thread.sleep(2000);
		WebElement addOneMoreVisitorButton = driver
				.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/form/div[4]/button[1]"));
		addOneMoreVisitorButton.click();

		Thread.sleep(4000);
		WebElement extraVisitorFirstName = driver.findElement(By
				.xpath("/html/body/div[1]/main/div/div/div[2]/form/div[3]/div[2]/div[2]/div/div/div[1]/div[1]/input"));
		extraVisitorFirstName.sendKeys(visitor.firstName + "1");

		Thread.sleep(4000);
		WebElement extraVisitorLastName = driver.findElement(By
				.xpath("/html/body/div[1]/main/div/div/div[2]/form/div[3]/div[2]/div[2]/div/div/div[1]/div[2]/input"));
		extraVisitorLastName.sendKeys(visitor.lastName + "1");

		Thread.sleep(4000);
		WebElement extraVisitorEmail = driver.findElement(By
				.xpath("//input[@class='p-inputtext p-component flex w-full h-10' and @data-pc-name='inputtext' and @data-pc-section='root']"));
		extraVisitorEmail.sendKeys(visitor.email);

		Thread.sleep(6000);
		WebElement extraVisitorNationality = driver.findElement(By.xpath(
				"/html/body/div[1]/main/div/div/div[2]/form/div[3]/div[2]/div[2]/div/div/div[1]/div[4]/div[1]/span"));
		extraVisitorNationality.click();

		try {

			WebDriverWait waitPopUpForSecondVisitor = new WebDriverWait(driver, Duration.ofSeconds(20));
			WebElement dialog = waitPopUpForSecondVisitor
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/div")));

			Thread.sleep(4000);
			WebElement okayButton = dialog.findElement(By.xpath("/html/body/div[4]/div/div[2]/div/div[5]/button"));
			okayButton.click();

			Thread.sleep(4000);
			extraVisitorNationality.click();

		} catch (Exception e) {

			Thread.sleep(4000);
			WebElement nationalityOptionExtraVisitor = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@id='pv_id_28_0']")));
			nationalityOptionExtraVisitor.click();
		}

		Thread.sleep(4000);
		WebElement documentTypeListExtraVisitor = driver.findElement(By
				.xpath("/html/body/div[1]/main/div/div/div[2]/form/div[3]/div[2]/div[2]/div/div/div[1]/div[5]/div[1]"));
		documentTypeListExtraVisitor.click();

		Thread.sleep(4000);
		WebElement documentTypeListOptionExtraVisitor = driver
				.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li[1]"));
		documentTypeListOptionExtraVisitor.click();

		try {
			// Your previous code to navigate and perform actions

			// Wait for the pop-up to be visible
			WebDriverWait waitPopUpExistingVisitor = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement alertDialog = waitPopUpExistingVisitor
					.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".p-dialog")));

			// Locate the "Okay" button within the pop-up
			WebElement okayButton = alertDialog.findElement(By.xpath("/html/body/div[4]/div/div[2]/div/div[5]/button"));

			// Click the "Okay" button to close the pop-up
			okayButton.click();

		} catch (Exception e) {
			e.printStackTrace();
		}

		Thread.sleep(4000);
		WebElement documentNumberExtraVisitor = driver.findElement(By
				.xpath("/html/body/div[1]/main/div/div/div[2]/form/div[3]/div[2]/div[2]/div/div/div[1]/div[6]/input"));
		documentNumberExtraVisitor.sendKeys(visitor.numbers + "1");

		Thread.sleep(4000);
		WebElement birthDateExtraVisitor = driver.findElement(By.xpath(
				"/html/body/div[1]/main/div/div/div[2]/form/div[3]/div[2]/div[2]/div/div/div[1]/div[7]/span/input"));
		birthDateExtraVisitor.click();

		WebDriverWait waitExtraVisitor = new WebDriverWait(driver, Duration.ofSeconds(10));
		waitExtraVisitor.until(ExpectedConditions.visibilityOfElementLocated(By.id("pv_id_32_panel"))); // Adjust the ID
																										// as needed
		WebElement birthDateToSelectExtraVisitor = driver
				.findElement(By.xpath("//td[@aria-label='" + today.getDayOfMonth() + "']"));
		birthDateToSelectExtraVisitor.click();

		Thread.sleep(4000);
		WebElement genderExtraVisitor = driver.findElement(By
				.xpath("/html/body/div[1]/main/div/div/div[2]/form/div[3]/div[2]/div[2]/div/div/div[1]/div[8]/div[1]"));
		genderExtraVisitor.click();

		Thread.sleep(4000);
		WebElement genderExtraVisitorOption = driver.findElement(By.xpath("/html/body/div[4]/div/ul/li[1]"));
		genderExtraVisitorOption.click();

		Thread.sleep(4000);
		WebElement phoneNumberExtraVisitor = driver.findElement(By
				.xpath("/html/body/div[1]/main/div/div/div[2]/form/div[3]/div[2]/div[2]/div/div/div[1]/div[9]/input"));
		phoneNumberExtraVisitor.sendKeys("01005710460");

		Thread.sleep(4000);
		WebElement vehicleExtraVisitor = driver.findElement(By.id("visitor-1-with-vehicle"));
		vehicleExtraVisitor.click();

		Thread.sleep(4000);
		WebElement extraCarPlateType = driver.findElement(By.xpath(
				"/html/body/div[1]/main/div/div/div[2]/form/div[3]/div[2]/div[2]/div/div/div[2]/div[2]/div/div[1]/div[1]"));
		extraCarPlateType.click();

		Thread.sleep(4000);
		WebElement extraCarPlateTypeOption = driver.findElement(By.xpath("/html/body/div[4]/div/ul/li[2]"));
		extraCarPlateTypeOption.click();

		Thread.sleep(4000);
		WebElement extraCarPlateNumber = driver.findElement(By.xpath(
				"/html/body/div[1]/main/div/div/div[2]/form/div[3]/div[2]/div[2]/div/div/div[2]/div[2]/div/div[2]/input"));
		extraCarPlateNumber.sendKeys(visitor.numbers + "1");

		Thread.sleep(6000);
		WebElement submitButton = driver
				.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/form/div[4]/button[2]"));
		submitButton.click();

	}

	@Test(dependsOnMethods = "chooseVisitorTabAndAddVisitor")
	public void checkDefaultVisitorStatus() {

		System.out.println("Worked fine");

	}

}
