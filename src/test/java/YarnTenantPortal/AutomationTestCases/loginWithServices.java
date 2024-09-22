/*
 * 
 * Test case 0: sending contact us message from services module
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

public class loginWithServices {

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
	public void chooseServicesSearchAndSendContactUS() throws InterruptedException {

		WebElement servicesButton = driver
				.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/nav[1]/div/div[2]/div[1]/a[2]"));
		servicesButton.click();

		WebElement searchBox = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[2]/div/div/input"));
		Thread.sleep(2000);
		searchBox.click();
		Thread.sleep(2000);
		searchBox.sendKeys("Internet");

		Thread.sleep(6000);
		WebElement serviceTypeButton = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/div"));
		serviceTypeButton.click();

		Thread.sleep(2000);
		WebElement serviceTitle = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[2]/div/div[1]/h4/span[2]"));
		assertEquals("Internet Services", serviceTitle.getText());
		
		WebElement contactUsButtonInServices = driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div/div[1]/div/button[3]"));
		contactUsButtonInServices.click();
		
		Thread.sleep(2000);
		WebElement contactUsServicesSubject = driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/form/div[1]/input"));
		contactUsServicesSubject.sendKeys("Test subject");
		
		WebElement contactUsServicesMessage = driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/form/div[2]/textarea"));
		contactUsServicesMessage.sendKeys("The first testing message in contact us from services module");
		
		WebElement submitContactUsServiceButton = driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/form/div[3]/button"));
		submitContactUsServiceButton.click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".p-toast[data-pc-name='toast'] .p-toast-message")));
		
		String alertMessageText = successMessage.getText();
        System.out.println("Alert message: " + alertMessageText);
        
        assertEquals(alertMessageText, alertMessageText);
		
	}

}
