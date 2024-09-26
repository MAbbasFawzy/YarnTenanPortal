/*
 * 
 * 
 * Test Case 3: Contact Us with attachment
Description: From the home page click on Contact us -> fill the fields and add an attachment then click on submit , then verify the success message appears and that the contact us request appears in the Contact Request History page 

 * 
 * 
 */

package YarnTenantPortal.AutomationTestCases;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class contactUsTenantPortal {

	WebDriver driver = new ChromeDriver();
	WebDriverWait wait;

	// WebDriver driver = new FirefoxDriver();

	@Test
	@BeforeTest
	public void testOpenTenantPortal() {

		driver.manage().window().maximize();

		driver.navigate().to("https://nakhla_sandbox.yarncloud.dev/tenant/auth/login/");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement email = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[1]/input"));
		email.sendKeys("test.tenant@yarn.com.sa");

		WebElement password = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[2]/input"));
		password.sendKeys("123");

		WebElement loginButton = driver
				.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[3]/button"));
		loginButton.click();

	}

	@Test
	public void testRedirectToContactUsPage() {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement contactUsTab = driver
				.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/nav[1]/div/div[2]/div[1]/a[11]"));
		contactUsTab.click();

		WebElement formTitle = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[2]/h4"));
		formTitle.getText();

		assertEquals("Contact us", formTitle.getText());

	}

	@Test(dependsOnMethods = "testRedirectToContactUsPage")
	public void enterDataInContactUsForm() throws InterruptedException {

		Thread.sleep(6000);

		WebElement contactUsMessageCategory = driver
				.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/form/div[1]/div/span"));
		contactUsMessageCategory.click();

		Thread.sleep(2000);

		WebElement contactUsCategory = driver.findElement(By.xpath("//*[@id=\"pv_id_2_0\"]"));
		contactUsCategory.click();

		Thread.sleep(2000);
		WebElement messageSubject = driver
				.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[2]/form/div[2]/input"));
		messageSubject.sendKeys("Solve a problem for me?");

		Thread.sleep(2000);
		WebElement messageBody = driver
				.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[2]/form/div[3]/textarea"));
		messageBody.sendKeys("Hi, I need to contact with the system admin to solve a problem for me?");

		Thread.sleep(4000);
		driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/form/div[4]/div/div/input")).sendKeys("C:\\Users\\eng_m\\eclipse-workspace\\AutomationTestCases\\logo-white.png");
				
		Thread.sleep(2000);
		WebElement submitButton = driver
				.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[2]/form/div[5]/button"));
		submitButton.click();
	}
}
