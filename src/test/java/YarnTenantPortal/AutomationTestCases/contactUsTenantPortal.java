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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class contactUsTenantPortal {

	WebDriver driver = new ChromeDriver();
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
    
    private void login() throws InterruptedException { // login code

    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		WebElement email = driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[3]/form/div[1]/input"));
		email.sendKeys(username);

		WebElement passcode = driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[3]/form/div[2]/div/input"));
		passcode.sendKeys(password);

		WebElement loginButton = driver
				.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[3]/button"));
		loginButton.click();

		WebElement userName = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/nav[1]/div/div[1]/div[2]/span[2]"));
		AssertJUnit.assertEquals(tenant, userName.getText()); 
		

		Thread.sleep(2000);
	}
	
	

	@Test
	public void testRedirectToContactUsPage() {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

		WebElement contactUsTab = driver
				.findElement(By.linkText("Contact us"));
		contactUsTab.click();

		WebElement formTitle = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[2]/h4"));
		formTitle.getText();

		assertEquals("Contact us", formTitle.getText());

	}

	@Test(dependsOnMethods = "testRedirectToContactUsPage")
	public void enterDataInContactUsForm() throws InterruptedException {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

		WebElement contactUsMessageCategory = driver
				.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/form/div[1]/div/span"));
		contactUsMessageCategory.click();

		Thread.sleep(500);

		WebElement contactUsCategory = driver.findElement(By.xpath("//li[@aria-label='Complaint']"));
		contactUsCategory.click();

		//Thread.sleep(2000);
		WebElement messageSubject = driver
				.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[2]/form/div[2]/input"));
		messageSubject.sendKeys("Solve a problem for me?");
		
		//Thread.sleep(2000);
		WebElement messageBody = driver
				.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[2]/form/div[3]/textarea"));
		messageBody.sendKeys("Hi, I need to contact with the system admin to solve a problem for me?");
		
		
		//Thread.sleep(4000);
		driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/form/div[4]/div/div/input")).sendKeys("C:\\Users\\eng_m\\eclipse-workspace\\AutomationTestCases\\logo-white.png");
				
		//Thread.sleep(2000);
		WebElement submitButton = driver
				.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[2]/form/div[5]/button"));
		submitButton.click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement successMessage = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".p-toast > div:nth-child(1)")));

		String alertMessageText = successMessage.getText();
		System.out.println("Alert message: " + alertMessageText);

		assertEquals(alertMessageText, alertMessageText);
	}
	
	
	@Test(dependsOnMethods = "enterDataInContactUsForm")
	public void checkContactUsHistory() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		
		//Thread.sleep(6000);
		WebElement contactUsHistoryButton = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[1]/div[2]/button"));
		contactUsHistoryButton.click();
		
		
		//Thread.sleep(2000);
		WebElement messageDescription = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[2]/a[1]/div/div[3]/p"));
		assertEquals("Hi, I need to contact with the system admin to solve a problem for me?", messageDescription.getText());
		
		
	}
}
