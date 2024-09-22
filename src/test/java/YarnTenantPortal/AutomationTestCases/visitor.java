/*
 * 
 * 
 * 
 * Test Case 6: Add Visitor _ one time _ one visitor _ without vehicle _ new visitor
 * 
 * 
 * 
 */



package YarnTenantPortal.AutomationTestCases;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class visitor {

	
	WebDriver driver = new ChromeDriver();
	// WebDriver driver = new FirefoxDriver();
	WebDriverWait wait;

	@Test
	@BeforeTest
	public void testOpenTenantPortal() throws InterruptedException {
		
		// driver.manage().window().maximize(); 

		driver.navigate().to("https://nakhla_sandbox.yarncloud.dev/tenant/auth/login/");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void enterCorrectCredentials() throws InterruptedException {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		Thread.sleep(4000);
		WebElement email = driver.findElement(By.cssSelector("#__nuxt > main > div > div > div:nth-child(3) > form > div:nth-child(1) > input"));
		email.sendKeys("yarn.user.tenant@gmail.com");

		Thread.sleep(4000);
		WebElement password = driver.findElement(By.cssSelector("#__nuxt > main > div > div > div:nth-child(3) > form > div:nth-child(2) > input"));
		password.sendKeys("123456");

		Thread.sleep(4000);
		WebElement loginButton = driver
				.findElement(By.cssSelector("#__nuxt > main > div > div > div:nth-child(3) > form > div.mb-8 > button"));
		loginButton.click();

		Thread.sleep(8000);
		WebElement userName = driver.findElement(By.cssSelector("#__nuxt > main > nav.fixed.z-10.top-0.right-0.w-full.border.border-b.shadow-xl.bg-\\[var\\(--c1\\)\\].hidden.sm\\:block.print\\:\\!hidden > div > div.flex.justify-between.items-center.h-22.border-b > div.flex.items-center.gap-4 > span.text-sm"));
		AssertJUnit.assertEquals("Mahmoud Abbas", userName.getText());
	}
	
	
	@Test(dependsOnMethods = "enterCorrectCredentials")
	public void chooseVisitorTabAndAddVisitor() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		Thread.sleep(8000);
		WebElement visitorTab = driver.findElement(By.linkText("My Visitors"));
		visitorTab.click();
		
		Thread.sleep(8000);
		WebElement addVisitorButton = driver.findElement(By.cssSelector("#__nuxt > main > div > div > div.hidden.sm\\:block > div:nth-child(2) > div > div > button"));
		addVisitorButton.click();
		
		Thread.sleep(8000);
		WebElement visitorTypeList = driver.findElement(By.cssSelector("#pv_id_5 > span"));
		visitorTypeList.click();
		
		Thread.sleep(2000);
		WebElement visitorTypeListOption = driver.findElement(By.id("pv_id_5_2"));
		visitorTypeListOption.click();
		
		Thread.sleep(2000);
		WebElement entryType = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[2]/form/div[1]/div[3]/span[1]/label"));
		entryType.click();
		
		Thread.sleep(2000);
		WebElement visitTime = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[2]/form/div[2]/div[2]/input"));
		visitTime.sendKeys("10-10-2024	09:35:00 p"); // "10-10-2024	09:35" + Keys.TAB + "PM"
		
		
		
		
		
		Thread.sleep(2000);
		WebElement visitorFirstName = driver.findElement(By.xpath("//*[@id=\"pv_id_9_0_content\"]/div/div/div[1]/div[1]/input"));
		visitorFirstName.sendKeys("Mahmoud");
		
		Thread.sleep(2000);
		WebElement visitorLastName = driver.findElement(By.xpath("//*[@id=\"pv_id_9_0_content\"]/div/div/div[1]/div[2]/input"));
		visitorLastName.sendKeys("Abbas");
		
		Thread.sleep(2000);
		WebElement email = driver.findElement(By.xpath("//*[@id=\"pv_id_9_0_content\"]/div/div/div[1]/div[3]/input"));
		email.sendKeys("testing@gmail.com");
		
		Thread.sleep(2000);
		WebElement nationality = driver.findElement(By.xpath("//*[@id=\"pv_id_6\"]/span"));
		nationality.click();
		
		Thread.sleep(2000);
		WebElement nationalityOption = driver.findElement(By.xpath("//*[@id=\"pv_id_6_0\"]"));
		nationalityOption.click();
		
		Thread.sleep(4000);
		WebElement documentType = driver.findElement(By.xpath("//*[@id=\"pv_id_7\"]/span"));
		documentType.click();
		
		Thread.sleep(2000);
		WebElement documentTypeListOption = driver.findElement(By.xpath("//*[@id=\"pv_id_7_0\"]"));
		documentTypeListOption.click();
		
		Thread.sleep(2000);
		WebElement documentNumber = driver.findElement(By.xpath("//*[@id=\"pv_id_9_0_content\"]/div/div/div[1]/div[6]/input"));
		documentNumber.sendKeys("123456789");
		
		Thread.sleep(2000);
		WebElement gender = driver.findElement(By.xpath("//*[@id=\"pv_id_8\"]/span"));
		gender.click();
		
		Thread.sleep(2000);
		WebElement genderOption = driver.findElement(By.xpath("//*[@id=\"pv_id_8_0\"]"));
		genderOption.click();
		
		Thread.sleep(2000);
		WebElement transportation = driver.findElement(By.xpath("//*[@id=\"pv_id_9_0_content\"]/div/div/div[2]/div/div/span[1]"));
		transportation.click();
		
		Thread.sleep(4000);
		WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[2]/form/div[4]/button[2]"));
		submitButton.click();
		
	}
}
