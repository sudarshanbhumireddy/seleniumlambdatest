

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class seleniumbasic {
 

    public String username = "bhumireddy.sudarshan";
    public String accesskey = "LefCO1X7fQ8A0gWnlVt7RtFNNS8cxlPvxA8115bAUMLs1HlVHN";
    public static RemoteWebDriver driver = null;
    public String gridURL = "@hub.lambdatest.com/wd/hub";
    boolean status = false;
    @BeforeClass
    public void setUp() throws Exception {
       DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("version", "70.0");
        capabilities.setCapability("platform", "win10"); // If this cap isn't specified, it will just get the any available one
        capabilities.setCapability("build", "LambdaTestSampleApp");
        capabilities.setCapability("name", "LambdaTestJavaSample");
        try {
            driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), capabilities);
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

	@Test(priority=1)
	public void FirstScenario() {
		driver.get("https://www.lambdatest.com/selenium-playground/");
	    driver.manage().window().maximize();
	 //   driver.switchTo().alert().accept();  
	   // driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div/span[1]")).click();
	    driver.findElement(By.xpath("//a[contains(text(),'Simple Form Demo')]")).click();

	    String url= driver.getCurrentUrl();
	    Assert.assertTrue(url.contains("simple-form-demo"));
	    
	    String actualMessage=  "Welcome to LambdaTest";
	    JavascriptExecutor executor=(JavascriptExecutor) driver;
	    executor.executeScript("window.scrollBy(0,300)");
	    driver.findElement(By.cssSelector("#user-message")).sendKeys(actualMessage);
	    driver.findElement(By.xpath("//button[@id='showInput']")).click();
	    WebElement ele=  driver.findElement(By.xpath("//div[@id='user-message'][p]"));
	    Assert.assertTrue(ele.getText().contains(actualMessage));
	}
	
	@Test(priority=3)
	public static void SecondScenario() {
		driver.get(" https://www.lambdatest.com/selenium-playground");
	    driver.manage().window().maximize();
	  //  driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div/span[1]")).click();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	    driver.findElement(By.xpath("//a[contains(text(),'Simple Form Demo')]")).click();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	    JavascriptExecutor executor=(JavascriptExecutor) driver;
	    executor.executeScript("window.scrollBy(0,500)");
	    driver.findElement(By.xpath("//p[contains(text(),'Progress Bar & Sliders')]")).click();
	     WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
	   
	  // WebElement ele=  driver.findElement((By.xpath("//div[@class='mb-10 sp__list ']/ul/li/a[starts-with(text(),'Drag')]")));
	  // System.out.println(ele.isDisplayed()+""+ele.getLocation());
	  // ele.click();
	  // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='mb-10 sp__list ']/ul/li/a[starts-with(text(),'Drag')]"))).click();
	     driver.get("https://www.lambdatest.com/selenium-playground/drag-drop-range-sliders-demo");
	     executor.executeScript("window.scrollBy(0,300)");
	    WebElement slider = driver.findElement(By.xpath("//div[@id='slider3']/div/input"));
	   
         //executor.executeScript("document.getElementById(\"sliderWidget\").value=1.5;");
         //System.out.println(slider.getAttribute("value"));
         while(true) {
        	  slider.sendKeys(Keys.RIGHT);
              if(slider.getAttribute("value").equals("95")) {
        		break;
     	  }
         }
         String actualValue=slider.getAttribute("value");
         String expectedValue="95";
         Assert.assertEquals(actualValue, expectedValue);
        
	 }
	
	@Test(priority=2)
	public static void ThirdScenario() {
		
		driver.get("https://www.lambdatest.com/selenium-playground/");
	    driver.manage().window().maximize();
	    driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div/span[1]")).click();
	    JavascriptExecutor executor=(JavascriptExecutor) driver;
	    executor.executeScript("window.scrollBy(0,500)");
	    driver.findElement(By.xpath("//a[contains(text(),'Input Form Submit')]")).click();
	    executor.executeScript("window.scrollBy(0,500)");
	    driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
	    String actualresult = driver.findElement(By.id("name")).getAttribute("validationMessage");
	    String expectedresult="Please fill out this field.";
	   Assert.assertTrue(actualresult.equals(expectedresult));
	   driver.findElement(By.id("name")).sendKeys("test");
	   driver.findElement(By.id("inputEmail4")).sendKeys("test@gmail.com");
	   driver.findElement(By.id("inputPassword4")).sendKeys("testselenium@123");
	   driver.findElement(By.id("company")).sendKeys("ABCcompany");
	   driver.findElement(By.id("websitename")).sendKeys("testwebsite.com");
	   WebElement country = driver.findElement(By.name("country"));  
	   Select countrydropdown = new Select(country);  
	   countrydropdown.selectByVisibleText("United States");
	   driver.findElement(By.id("inputCity")).sendKeys("Bangalore");
	   driver.findElement(By.id("inputAddress1")).sendKeys("sampleaddress1");
	   driver.findElement(By.id("inputAddress2")).sendKeys("sampleaddress2");
	   driver.findElement(By.id("inputState")).sendKeys("Karnataka");
	   driver.findElement(By.id("inputZip")).sendKeys("500072");
	   driver.findElement(By.id("name")).sendKeys("test");
	   driver.findElement(By.id("name")).sendKeys("test");
	   driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
	 String result=  driver.findElement(By.className("success-msg")).getText();
	 System.out.println(result);
	 String responseMessage="Thanks for contacting us, we will get back to you shortly.";
	 Assert.assertTrue(result.equals(responseMessage));
	}
	
	
	    
	    @AfterClass
	    public void tearDown() throws Exception {
	       if (driver != null) {
	            ((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
	            driver.quit();
	        }
	    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
