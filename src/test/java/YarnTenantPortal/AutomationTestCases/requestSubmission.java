package YarnTenantPortal.AutomationTestCases;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class requestSubmission {

	WebDriver driver = new ChromeDriver();
	WebDriverWait wait;

	// WebDriver driver = new FirefoxDriver();

	@Test
	@BeforeTest
	public void testOpenTenantPortal() {

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.navigate().to("https://nakhla_sandbox.yarncloud.dev/tenant/auth/login/");

		WebElement email = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[1]/input"));
		email.sendKeys("test.tenant@yarn.com.sa");

		WebElement password = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[2]/input"));
		password.sendKeys("123");

		WebElement loginButton = driver
				.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[3]/button"));
		loginButton.click();

	}

	@Test(dependsOnMethods = "testOpenTenantPortal")
	public void requestSubmit() throws InterruptedException {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		Thread.sleep(8000);
		WebElement myRequestsTab = driver.findElement(By.linkText("My Requests"));
		myRequestsTab.click();

		Thread.sleep(6000);
		WebElement subcriptionsTab = driver.findElement(By.linkText("Subscriptions"));
		subcriptionsTab.click();

		Thread.sleep(6000);
		WebElement subscribeButton = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[1]/button"));
		subscribeButton.click();
		
	}
}
