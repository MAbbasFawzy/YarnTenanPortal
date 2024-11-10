/*
 * 
 * Test Case 1: Tenant Portal Authentication and pages navigation Description: User can sign in and then navigate through the different URL pages
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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class loginAndNavigation {

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
	public void checkServicesPageOpen() {

		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		WebElement servicesTab = driver.findElement(By.linkText("Services"));
		servicesTab.click();

	}

	@Test // (dependsOnMethods = "checkServicesPageOpen")
	public void checkMyRequestsPageOpen() {

		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		WebElement myRequestsTab = driver.findElement(By.linkText("My Requests"));
		myRequestsTab.click();
	}

	@Test // (dependsOnMethods = "checkMyRequestsPageOpen")
	public void checkMyInvoicesPageOpen() {

		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		WebElement myInvoices = driver.findElement(By.linkText("My Requests"));
		myInvoices.click();
	}

	@Test // (dependsOnMethods = "checkMyInvoicesPageOpen")
	public void checkMyVisitorsPageOpen() {

		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

		WebElement myVisitors = driver.findElement(By.linkText("My Visitors"));
		myVisitors.click();
	}

	@Test // (dependsOnMethods = "checkMyVisitorsPageOpen")
	public void checkMyViolationsPageOpen() {

		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		WebElement myViolations = driver.findElement(By.linkText("My Violations"));
		myViolations.click();
	}

	@Test // (dependsOnMethods = "checkMyViolationsPageOpen")
	public void checkMyDocumentsPageOpen() {

		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

		WebElement myDocuments = driver.findElement(By.linkText("My Documents"));
		myDocuments.click();

	}

	@Test // (dependsOnMethods = "checkMyDocumentsPageOpen")
	public void checkMyDependentsPageOpen() {

		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

		WebElement myDependents = driver.findElement(By.linkText("My Dependents"));
		myDependents.click();

	}

	@Test // (dependsOnMethods = "checkMyDependentsPageOpen")
	public void checkMyPetsPageOpen() {

		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

		WebElement myPets = driver.findElement(By.linkText("My Pets"));
		myPets.click();
	}

	@Test // (dependsOnMethods = "checkMyPetsPageOpen")
	public void checkMyVehiclesPageOpen() {

		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

		WebElement myVehicles = driver.findElement(By.linkText("My Vehicles"));
		myVehicles.click();
	}

	@Test // (dependsOnMethods = "checkMyVehiclesPageOpen")
	public void checkContactUsPage() {

		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

		WebElement contactUs = driver.findElement(By.linkText("Contact us"));
		contactUs.click();
	}

}
