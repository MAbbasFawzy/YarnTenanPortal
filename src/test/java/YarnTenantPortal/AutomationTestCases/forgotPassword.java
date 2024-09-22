package YarnTenantPortal.AutomationTestCases;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class forgotPassword {

	WebDriver driver = new ChromeDriver();

	@Test
	@BeforeTest
	public void testOpenTenantPortal() {

		driver.manage().window().maximize();

		driver.navigate().to("https://nakhla_sandbox.yarncloud.dev/tenant/auth/login/");

	}

	@Test
	public void testRedirectToFogrotPasswordPage() {

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		WebElement forgotPasswordButton = driver
				.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[4]"));
		forgotPasswordButton.click();

	}

	@Test(dependsOnMethods = "testRedirectToFogrotPasswordPage")
	public void testEnterCorrectEmail() {

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		WebElement email = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[1]/input"));
		email.sendKeys("test.tenant@yarn.com.sa");

		WebElement submitButton = driver
				.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[2]/button"));
		submitButton.click();
		
		email.clear();

	}

	@Test (dependsOnMethods = "testEnterCorrectEmail")
	public void testEnterNonExistentEmail() {

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		WebElement email = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[1]/input"));
		email.sendKeys("test.tenant@yarn.com");

		WebElement submitButton = driver
				.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[2]/button"));
		submitButton.click();
		
		email.clear();
	}

	@Test (dependsOnMethods = "testEnterNonExistentEmail")
	public void testEnterEmptyEmail() {
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		WebElement email = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[1]/input"));
		email.sendKeys("     ");

		WebElement submitButton = driver
				.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[2]/button"));
		submitButton.click();
		
		email.clear();
	}
	
	@Test (dependsOnMethods = "testEnterEmptyEmail")
	public void testEnterInvalidEmailFormat() {
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		WebElement email = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[1]/input"));
		email.sendKeys("asdasdasdasdasdasdasdasd");

		WebElement submitButton = driver
				.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[2]/button"));
		submitButton.click();
		
		email.clear();
	}
}
