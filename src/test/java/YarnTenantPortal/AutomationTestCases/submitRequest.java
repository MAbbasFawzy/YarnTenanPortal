/*
 * 
 * Test Case 2: Submit a request 
Description : User can click on “Submit a Request” and then create a request (ex: maintenance request) and then verify the request is created successfully and appears in the open requests page
 * 
 * 
 */



package YarnTenantPortal.AutomationTestCases;

import static org.testng.Assert.assertEquals;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class submitRequest {

	WebDriver driver = new ChromeDriver();
	WebDriverWait wait;

	@Test
	@BeforeTest
	public void testOpenTenantPortal() throws InterruptedException {

		driver.manage().window().maximize();

		wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // 30 seconds timeout

		driver.navigate().to("https://nakhla_sandbox.yarncloud.dev/tenant/auth/login/");

		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test(dependsOnMethods = "testOpenTenantPortal")
	public void enterCorrectCredentials() throws InterruptedException {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		Thread.sleep(2000);
		WebElement email = driver.findElement(By.cssSelector("div.form-group:nth-child(1) > input:nth-child(1)"));
		email.sendKeys("yarn.user.tenant@gmail.com");

		Thread.sleep(2000);
		WebElement password = driver.findElement(By.cssSelector("div.form-group:nth-child(2) > input:nth-child(1)"));
		password.sendKeys("123456789");

		Thread.sleep(2000);
		WebElement loginButton = driver.findElement(By.cssSelector(".py-1"));
		loginButton.click();

		Thread.sleep(4000);

		WebElement userName = driver.findElement(By.xpath("/html/body/div[1]/main/nav[1]/div/div[1]/div[2]/span[2]"));
		AssertJUnit.assertEquals("Mahmoud Abbas", userName.getText());
	}

	@Test(dependsOnMethods = "enterCorrectCredentials")
	public void chooseServicesSearchAndSubmitRequest() throws InterruptedException {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		Thread.sleep(8000);

		WebElement servicesButton = driver.findElement(By.linkText("Services"));
		servicesButton.click();

		Thread.sleep(4000);
		WebElement searchBox = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[2]/div/div/input"));
		Thread.sleep(4000);
		searchBox.click();
		Thread.sleep(4000);
		searchBox.sendKeys("Internet");

		Thread.sleep(4000);
		WebElement serviceTypeButton = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/div"));
		serviceTypeButton.click();

		Thread.sleep(4000);
		WebElement serviceTitle = driver
				.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[2]/div/div[1]/h4/span[2]"));
		assertEquals("Internet Services", serviceTitle.getText());

		WebElement requestServiceButton = driver
				.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[2]/div/div[1]/div/button[1]"));
		requestServiceButton.click();

		Thread.sleep(4000);
		WebElement servicesDropDown = driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/form/div[1]/div/div"));
		servicesDropDown.click();

		Thread.sleep(4000);
		WebElement serviceOption = driver.findElement(By.xpath("//*[@id=\"pv_id_8_0\"]"));
		serviceOption.click();

		Thread.sleep(4000);
		WebElement serviceCategory = driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/form/div[2]/div/div"));
		serviceCategory.click();

		Thread.sleep(4000);
		WebElement serviceCategoryOption = driver.findElement(By.xpath("//*[@id=\"pv_id_10_1\"]"));
		serviceCategoryOption.click();
		
		Thread.sleep(4000);
		WebElement description = driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/form/div[3]/textarea"));
		description.sendKeys("Testing description new request is added.");
		
		Thread.sleep(4000);
		WebElement preferredVisitDate = driver.findElement(By.xpath("//*[@id=\"pv_id_9\"]/input"));
		preferredVisitDate.sendKeys("16-9-2024");
		
		Thread.sleep(4000);
		WebElement preferredVisitTime = driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/form/div[4]/div[1]/div[2]/input"));
		preferredVisitTime.sendKeys("18:30");

		Thread.sleep(4000);
		WebElement submitRequest = driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/form/div[7]/button"));
		submitRequest.click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement successMessage = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".p-toast > div:nth-child(1)")));

		String alertMessageText = successMessage.getText();
		System.out.println("Alert message: " + alertMessageText);

		assertEquals(alertMessageText, alertMessageText);

	}

	@Test(dependsOnMethods = "chooseServicesSearchAndSubmitRequest")
	public void checkNewRequestAdded() throws InterruptedException {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebElement myRequestsTab = driver.findElement(By.linkText("My Requests"));
		myRequestsTab.click();
		
		Thread.sleep(2000);
		WebElement newRequest = driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[3]/a[1]"));
		newRequest.click();
		
		Thread.sleep(2000);
		WebElement requestDescription = driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[7]/div[2]"));
		requestDescription.getText();
		
		assertEquals(requestDescription.getText(), "Testing description new request is added.");
	}

}
