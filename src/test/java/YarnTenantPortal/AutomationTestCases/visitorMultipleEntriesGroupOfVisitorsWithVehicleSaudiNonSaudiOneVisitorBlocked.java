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
	public void chooseVisitorTabAndAddVisitor() throws InterruptedException {

		randomGenerator.Visitor visitor = randomGenerator.generateRandomContact();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		Thread.sleep(10000);
		WebElement visitorTab = driver.findElement(By.linkText("My Visitors"));
		visitorTab.click();

		Thread.sleep(10000);
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
		WebElement entryType = driver
				.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[2]/form/div[1]/div[3]/span[1]/label"));
		entryType.click();

		Thread.sleep(2000);
		WebElement visitEndDate = driver
				.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[2]/form/div[2]/div[2]/input"));
		visitEndDate.sendKeys("12/31/2024" + Keys.TAB + "02" + "30" + "P");

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
			WebElement alertDialog = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".p-dialog")));

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
		documentNumber.sendKeys("1234567891234");

		Thread.sleep(2000);
		WebElement gender = driver.findElement(By.xpath("//*[@id=\"pv_id_8\"]/span"));
		gender.click();

		Thread.sleep(2000);
		WebElement genderOption = driver.findElement(By.xpath("//*[@id=\"pv_id_8_0\"]"));
		genderOption.click();

		Thread.sleep(4000);
		WebElement transportation = driver.findElement(By.id("visitor-0-with-vehicle"));
		transportation.click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement carPlateType = wait.until(ExpectedConditions.elementToBeClickable(By.id("pv_id_21")));
		carPlateType.click();

		Thread.sleep(4000);
		WebElement carPlateOption = driver.findElement(By.id("pv_id_21_0"));
		carPlateOption.click();

		Thread.sleep(2000);
		WebElement carPlateNumber = driver.findElement(By.cssSelector(
				"#pv_id_9_0_content > div > div > div:nth-child(2) > div:nth-child(2) > div > div:nth-child(2) > input"));
		carPlateNumber.sendKeys("123-zxcv");

		Thread.sleep(2000);
		WebElement addOneMoreVisitorButton = driver
				.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[2]/form/div[4]/button[1]"));
		addOneMoreVisitorButton.click();

		Thread.sleep(4000);
		WebElement extraVisitorFirstName = driver
				.findElement(By.xpath("//*[@id=\"pv_id_9_1_content\"]/div/div/div[1]/div[1]/input"));
		extraVisitorFirstName.sendKeys(visitor.firstName);

		Thread.sleep(4000);
		WebElement extraVisitorLastName = driver
				.findElement(By.xpath("//*[@id=\"pv_id_9_1_content\"]/div/div/div[1]/div[2]/input"));
		extraVisitorLastName.sendKeys(visitor.lastName);

		Thread.sleep(4000);
		WebElement extraVisitorEmail = driver
				.findElement(By.xpath("//*[@id=\"pv_id_9_1_content\"]/div/div/div[1]/div[3]/input"));
		extraVisitorEmail.sendKeys(visitor.email);

		Thread.sleep(6000);
		WebElement extraVisitorNationality = driver.findElement(By.id("pv_id_23"));
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
			WebElement nationalityOptionExtraVisitor = driver.findElement(By.id("pv_id_23_0"));
			nationalityOptionExtraVisitor.click();
		}

		Thread.sleep(4000);
		WebElement nationalityOptionExtraVisitor = driver.findElement(By.id("pv_id_23_0"));
		nationalityOptionExtraVisitor.click();

		Thread.sleep(4000);
		WebElement documentTypeListExtraVisitor = driver.findElement(By.id("pv_id_24"));
		documentTypeListExtraVisitor.click();

		Thread.sleep(4000);
		WebElement documentTypeListOptionExtraVisitor = driver.findElement(By.id("pv_id_24_0"));
		documentTypeListOptionExtraVisitor.click();

		Thread.sleep(4000);
		WebElement documentNumberExtraVisitor = driver
				.findElement(By.xpath("//*[@id=\"pv_id_9_1_content\"]/div/div/div[1]/div[6]/input"));
		documentNumberExtraVisitor.sendKeys(visitor.numbers);

		Thread.sleep(4000);
		WebElement genderExtraVisitor = driver.findElement(By.id("pv_id_25"));
		genderExtraVisitor.click();

		Thread.sleep(4000);
		WebElement genderExtraVisitorOption = driver.findElement(By.id("pv_id_25_0"));
		genderExtraVisitorOption.click();

		Thread.sleep(4000);
		WebElement vehicleExtraVisitor = driver.findElement(By.id("visitor-1-with-vehicle"));
		vehicleExtraVisitor.click();

		Thread.sleep(4000);
		WebElement extraCarPlateType = driver.findElement(By.id("pv_id_26"));
		extraCarPlateType.click();

		Thread.sleep(4000);
		WebElement extraCarPlateTypeOption = driver.findElement(By.id("pv_id_26_1"));
		extraCarPlateTypeOption.click();

		Thread.sleep(4000);
		WebElement extraCarPlateNumber = driver
				.findElement(By.xpath("//*[@id=\"pv_id_9_1_content\"]/div/div/div[2]/div[2]/div/div[2]/input"));
		extraCarPlateNumber.sendKeys(visitor.numbers);

		Thread.sleep(6000);
		WebElement submitButton = driver
				.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[2]/form/div[4]/button[2]"));
		submitButton.click();

	}

	@Test(dependsOnMethods = "chooseVisitorTabAndAddVisitor")
	public void checkDefaultVisitorStatus() {

		System.out.println("Worked fine");

	}
	
	
}
