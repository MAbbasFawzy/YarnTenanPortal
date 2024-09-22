package YarnTenantPortal.AutomationTestCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class loginWithRequests {

	
	WebDriver driver = new ChromeDriver();

	// WebDriver driver = new FirefoxDriver();

	@Test
	@BeforeTest
	public void testOpenTenantPortal() {

		driver.manage().window().maximize();

		driver.navigate().to("https://nakhla_sandbox.yarncloud.dev/tenant/auth/login/");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void enterCorrectCredentials() {

		WebElement email = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[1]/input"));
		email.sendKeys("yarn.user.tenant@gmail.com");

		WebElement password = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[2]/input"));
		password.sendKeys("123456789");

		WebElement loginButton = driver
				.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[3]/button"));
		loginButton.click();

		WebElement userName = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/nav[1]/div/div[1]/div[2]/span[2]"));
		AssertJUnit.assertEquals("Mahmoud Abbas", userName.getText());

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@Test
	public void chooseRequestsAndSubmit() {
		
	}
	
	
}
