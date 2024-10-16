/*
 * 
 * 
 * Test Case 4: Subscribe to a service
 * Description: navigate to “services” tab and subscribe to a service (Ex: internet Service) and verify it appears in My requests - subscription with open status 
 * 
 * 
 * 
 */



package YarnTenantPortal.AutomationTestCases;

import static org.testng.Assert.assertEquals;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class subscribeToService {
	
	WebDriver driver = new FirefoxDriver();

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
    
    private void login() throws InterruptedException {
        // login code
    	
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	WebElement email = driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[3]/form/div[1]/input"));
		email.sendKeys(username);

		WebElement passcode = driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[3]/form/div[2]/input"));
		passcode.sendKeys(password);

		WebElement loginButton = driver
				.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[3]/button"));
		loginButton.click();

		WebElement userName = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/nav[1]/div/div[1]/div[2]/span[2]"));
		AssertJUnit.assertEquals(tenant, userName.getText());

		Thread.sleep(2000);
    }

	@Test
	public void chooseServicesSearch() throws InterruptedException {

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
		
	}

	@Test(dependsOnMethods = "chooseServicesSearch")
	public void chooseServicesSearchAndSubscribe() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebElement subscribeButton = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[2]/div/div[1]/div/button[2]"));
		subscribeButton.click();

		Thread.sleep(4000);
		WebElement servicesList = driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/form/div[1]/div/div"));
		servicesList.click();
		
		Thread.sleep(2000);
		WebElement serviceOption = driver.findElement(By.xpath("//*[@id=\"pv_id_8_0\"]"));
		serviceOption.click();
		
		Thread.sleep(4000);
		WebElement serviceCategoryList = driver.findElement(By.xpath("//*[@id=\"pv_id_13\"]/div"));
		serviceCategoryList.click();
		
		Thread.sleep(2000);
		WebElement subCategoryList = driver.findElement(By.xpath("//*[@id=\"pv_id_13_0\"]"));
		subCategoryList.click();
		
		Thread.sleep(1000);
		WebElement serviceDescription = driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/form/div[5]/textarea"));
		serviceDescription.sendKeys("I want to subscribe to a higher network connection.");
		
		Thread.sleep(2000);
		WebElement preferredVisitDate = driver.findElement(By.cssSelector("input[type='date']"));
		preferredVisitDate.sendKeys("16-9-2024");
		
		Thread.sleep(2000);
		WebElement preferredVisitTime = driver.findElement(By.cssSelector("input[type='time']"));
		preferredVisitTime.sendKeys("18:30");
		
		Thread.sleep(2000);
		WebElement submitSubscriptionButton = driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/form/div[8]/button"));
		submitSubscriptionButton.click();

		Thread.sleep(4000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".p-toast[data-pc-name='toast'] .p-toast-message")));
		
		String alertMessageText = successMessage.getText();
        System.out.println("Alert message: " + alertMessageText);
	
	}
}
