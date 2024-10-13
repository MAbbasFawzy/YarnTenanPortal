package YarnTenantPortal.AutomationTestCases;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class e2eRegressionClass {

	/*
	 * WebDriver driver = new FirefoxDriver(); WebDriverWait wait;
	 * 
	 * private String baseUrl; private String username; private String password;
	 * 
	 * 
	 * 
	 * @BeforeTest public void setup() throws InterruptedException {
	 * loadProperties(); driver.manage().window().maximize(); wait = new
	 * WebDriverWait(driver, Duration.ofSeconds(10)); driver.navigate().to(baseUrl);
	 * login(); }
	 * 
	 * @AfterClass public void tearDown() { if (driver != null) { driver.quit(); } }
	 * 
	 * private void loadProperties() { Properties properties = new Properties(); try
	 * (InputStream input =
	 * getClass().getClassLoader().getResourceAsStream("config.properties")) { if
	 * (input == null) {
	 * System.out.println("Sorry, unable to find config.properties"); return; }
	 * properties.load(input); baseUrl = properties.getProperty("base.url");
	 * username = properties.getProperty("username"); password =
	 * properties.getProperty("password");
	 * 
	 * } catch (IOException e) { e.printStackTrace(); } }
	 * 
	 * private void login() throws InterruptedException { // login code
	 * 
	 * driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); WebElement
	 * email = driver.findElement(By.xpath(
	 * "/html/body/div[1]/main/div/div/div[3]/form/div[1]/input"));
	 * email.sendKeys(username);
	 * 
	 * WebElement passcode = driver.findElement(By.xpath(
	 * "/html/body/div[1]/main/div/div/div[3]/form/div[2]/input"));
	 * passcode.sendKeys(password);
	 * 
	 * WebElement loginButton = driver .findElement(By.xpath(
	 * "//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[3]/button"));
	 * loginButton.click();
	 * 
	 * WebElement userName = driver.findElement(By.xpath(
	 * "//*[@id=\"__nuxt\"]/main/nav[1]/div/div[1]/div[2]/span[2]"));
	 * AssertJUnit.assertEquals("Mahmoud Abbas", userName.getText()); //
	 * AssertJUnit.assertEquals("Andalus Tenant", userName.getText());
	 * 
	 * Thread.sleep(2000); }
	 */

	@Test(priority = 0)
	public void runTestClass1() {

		loginAndNavigation login1 = new loginAndNavigation();
		login1.checkContactUsPage();
		login1.checkMyDocumentsPageOpen();

	}

	@Test(priority = 1)
	public void runTestClass2() throws InterruptedException {

		editTenantProfileInformation edit1 = new editTenantProfileInformation();
		edit1.editProfile();

	}

}
