/*
 * 
 * 
 * Test Case 12: Edit tenant profile information
 * Description: make sure that the tenant can edit his information 
 * 
 * 
 */



package YarnTenantPortal.AutomationTestCases;

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

public class editTenantProfileInformation {

	WebDriver driver = new ChromeDriver();

	// WebDriver driver = new FirefoxDriver();

	@Test
	@BeforeTest
	public void testOpenTenantPortal() throws InterruptedException {
		
		//driver.manage().window().maximize(); 

		driver.navigate().to("https://nakhla_sandbox.yarncloud.dev/tenant/auth/login/");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void enterCorrectCredentials() throws InterruptedException {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebElement email = driver.findElement(By.cssSelector("#__nuxt > main > div > div > div:nth-child(3) > form > div:nth-child(1) > input"));
		email.sendKeys("yarn.user.tenant@gmail.com");

		WebElement password = driver.findElement(By.cssSelector("#__nuxt > main > div > div > div:nth-child(3) > form > div:nth-child(2) > input"));
		password.sendKeys("123456");

		WebElement loginButton = driver
				.findElement(By.cssSelector("#__nuxt > main > div > div > div:nth-child(3) > form > div.mb-8 > button"));
		loginButton.click();

		WebElement userName = driver.findElement(By.cssSelector("#__nuxt > main > nav.fixed.z-10.top-0.right-0.w-full.border.border-b.shadow-xl.bg-\\[var\\(--c1\\)\\].hidden.sm\\:block.print\\:\\!hidden > div > div.flex.justify-between.items-center.h-22.border-b > div.flex.items-center.gap-4 > span.text-sm"));
		AssertJUnit.assertEquals("Mahmoud Abbas", userName.getText());

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}
	
	@Test(dependsOnMethods = "enterCorrectCredentials")
	public void editProfile() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebElement profileIcon = driver.findElement(By.xpath("/html/body/div[1]/main/nav[1]/div/div[1]/div[2]/a[4]/img"));
		profileIcon.click();
		
		Thread.sleep(2000);
		driver.getCurrentUrl();
		
		Thread.sleep(2000);
		WebElement editButton = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[2]/div/div[1]/button[1]"));
		editButton.click();
		
		Thread.sleep(2000);
		WebElement jobTitle = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[2]/div/form/div[4]/input"));
		jobTitle.sendKeys("Quality control engineer / Quality Assurance Engineer");
		
		Thread.sleep(4000);
		WebElement submitButtonForEditProfile = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[2]/div/form/div[5]/button[1]"));
		submitButtonForEditProfile.click();
		
		Thread.sleep(4000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".p-toast[data-pc-name='toast'] .p-toast-message")));
		
		String alertMessageText = successMessage.getText();
        System.out.println("Alert message: " + alertMessageText);
        
	}
}
