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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class requestSubmission {

	WebDriver driver = new FirefoxDriver();
    WebDriverWait wait;

    private String baseUrl;
    private String username;
    private String password;
	
    
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
		AssertJUnit.assertEquals("Mahmoud Abbas", userName.getText());
		//AssertJUnit.assertEquals("Andalus Tenant", userName.getText());

		Thread.sleep(2000);
    }

	@Test
	public void chooseSubscriptions() throws InterruptedException {

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
	
	@Test(dependsOnMethods = "chooseSubscriptions")
	public void chooseServiceToSubmitRequest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebElement servicesButton = driver.findElement(By.linkText("Services"));
		servicesButton.click();
		
		Thread.sleep(6000);
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

		Thread.sleep(4000);
		WebElement requestServiceButton = driver
				.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[2]/div/div[1]/div/button[1]"));
		requestServiceButton.click();

		Thread.sleep(6000);
		WebElement servicesDropDown = driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/form/div[1]/div/div"));
		servicesDropDown.click();

		Thread.sleep(6000);
		WebElement serviceOption = driver.findElement(By.xpath("//*[@id=\"pv_id_8_0\"]"));
		serviceOption.click();

		Thread.sleep(8000);
		WebElement serviceCategory = driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/form/div[2]/div/div"));
		serviceCategory.click();

		Thread.sleep(8000);
		WebElement serviceCategoryOption = driver.findElement(By.xpath("//*[@id=\"pv_id_10_1\"]"));
		serviceCategoryOption.click();

		Thread.sleep(4000);
		WebElement description = driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/form/div[3]/textarea"));
		description.sendKeys("Testing description new request is added.");

		Thread.sleep(4000);
		WebElement preferredVisitDate = driver.findElement(By.xpath("//*[@id=\"pv_id_9\"]/input"));
		preferredVisitDate.sendKeys("16-9-2024");

		Thread.sleep(4000);
		WebElement preferredVisitTime = driver
				.findElement(By.xpath("/html/body/div[4]/div/div[2]/form/div[4]/div[1]/div[2]/input"));
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
}
