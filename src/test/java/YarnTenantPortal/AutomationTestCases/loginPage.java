package YarnTenantPortal.AutomationTestCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;

public class loginPage {

	WebDriver driver = new ChromeDriver();
	private String baseUrl;
    private String username;
    private String password;
    
	//WebDriver driver = new FirefoxDriver();

	@Test
	@BeforeTest
	public void testOpenTenantPortal() {

		loadProperties();
		
		driver.manage().window().maximize();

		driver.navigate().to(baseUrl);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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


	@Test 
	public void enterCorrectCredentials() {

		WebElement email = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[1]/input"));
		email.sendKeys("test.tenant@yarn.com.sa");

		WebElement password = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[2]/input"));
		password.sendKeys("123");

		WebElement loginButton = driver
				.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[3]/button"));
		loginButton.click();

		WebElement userName = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/nav[1]/div/div[1]/div[2]/span[2]"));
		AssertJUnit.assertEquals("Test Yarn", userName.getText());
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement logOutButton = driver.findElement(By.xpath("/html/body/div[1]/main/nav[1]/div/div[1]/div[2]/a[5]"));
		logOutButton.click();

	}

	@Test //(dependsOnMethods = "enterCorrectCredentials")
	public void enterWrongEmail() {
		
		driver.manage().window().maximize();

		driver.navigate().to("https://nakhla_sandbox.yarncloud.dev/tenant/auth/login/");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement email = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[1]/input"));
		email.sendKeys("test.tenant@yarn.com");

		WebElement password = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[2]/input"));
		password.sendKeys("123");

		WebElement loginButton = driver
				.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[3]/button"));
		loginButton.click();

		WebElement alert = driver.findElement(By.className("p-ripple-disabled"));
		alert.getText();
		System.out.println(alert.getText());

		email.clear();
		password.clear();

	}

	@Test //(dependsOnMethods = "enterWrongEmail")
	public void enterWrongPassword() {
		
		
		driver.manage().window().maximize();

		driver.navigate().to("https://nakhla_sandbox.yarncloud.dev/tenant/auth/login/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebElement email = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[1]/input"));
		email.sendKeys("test.tenant@yarn.com.sa");

		WebElement password = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[2]/input"));
		password.sendKeys("123456");

		WebElement loginButton = driver
				.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[3]/button"));
		loginButton.click();
		
		email.clear();
		password.clear();
	}
	
	@Test //(dependsOnMethods = "enterWrongPassword")
	public void enterEmptyEmail() {
		
		driver.manage().window().maximize();

		driver.navigate().to("https://nakhla_sandbox.yarncloud.dev/tenant/auth/login/");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebElement email = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[1]/input"));
		email.sendKeys("  ");
		
		WebElement password = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[2]/input"));
		password.sendKeys("123");
		
		WebElement loginButton = driver
				.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[3]/button"));
		loginButton.click();
		
		email.clear();
		password.clear();
		
	}
	
	
	@Test //(dependsOnMethods = "enterEmptyEmail")
	public void enterEmptyPassword() {
		
		driver.manage().window().maximize();

		driver.navigate().to("https://nakhla_sandbox.yarncloud.dev/tenant/auth/login/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebElement email = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[1]/input"));
		email.sendKeys("test.tenant@yarn.com.sa");
		
		WebElement password = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[2]/input"));
		password.sendKeys("    ");
		
		WebElement loginButton = driver
				.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[3]/button"));
		loginButton.click();
		
		email.clear();
		password.clear();
		
	}
	
	@Test //(dependsOnMethods = "enterEmptyPassword")
	public void enterEmailWithSpecialCharacters() {
		
		driver.manage().window().maximize();

		driver.navigate().to("https://nakhla_sandbox.yarncloud.dev/tenant/auth/login/");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebElement email = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[1]/input"));
		email.sendKeys("test@#$.tenant@yarn.com.sa");
		
		WebElement password = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[2]/input"));
		password.sendKeys("123");
		
		WebElement loginButton = driver
				.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[3]/button"));
		loginButton.click();
		
		email.clear();
		password.clear();
		
	}
	
	@Test //(dependsOnMethods = "enterEmailWithSpecialCharacters")
	public void enterPasswordWithSpecialCharacters() {
		
		driver.manage().window().maximize();

		driver.navigate().to("https://nakhla_sandbox.yarncloud.dev/tenant/auth/login/");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebElement email = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[1]/input"));
		email.sendKeys("test.tenant@yarn.com.sa");
		
		WebElement password = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[2]/input"));
		password.sendKeys("!@#");
		
		WebElement loginButton = driver
				.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[3]/button"));
		loginButton.click();
		
		email.clear();
		password.clear();
		
	}
	
	@Test //(dependsOnMethods = "enterPasswordWithSpecialCharacters")
	public void enterPasswordWithLongCharacters() {
		
		driver.manage().window().maximize();

		driver.navigate().to("https://nakhla_sandbox.yarncloud.dev/tenant/auth/login/");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebElement email = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[1]/input"));
		email.sendKeys("test.tenant@yarn.com.sa");
		
		WebElement password = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[2]/input"));
		password.sendKeys("123456789123456789123456789123456789");
		
		WebElement loginButton = driver
				.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[3]/button"));
		loginButton.click();
		
		email.clear();
		password.clear();
		
	}
	
	@Test //(dependsOnMethods = "enterPasswordWithLongCharacters")
	public void enterEmailWithLongCharacters() {
		
		driver.manage().window().maximize();

		driver.navigate().to("https://nakhla_sandbox.yarncloud.dev/tenant/auth/login/");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebElement email = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[1]/input"));
		email.sendKeys("testasdasdasdasdasdasdasdasdasd.tenant@yarn.com.sa");
		
		WebElement password = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[2]/input"));
		password.sendKeys("123");
		
		WebElement loginButton = driver
				.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[3]/button"));
		loginButton.click();
		
		email.clear();
		password.clear();
		
	}
	
	
	
}
