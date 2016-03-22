import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class test {
 
	public static void main(String[] args) throws InterruptedException {
		/*
		 * 
		 * xipmuosnoino
zloioduvan
		 * 
		 */
		ChromeOptions options = new ChromeOptions();
		//options.addExtensions(new File("D:\\selenium-2.52.0\\ext\\anonymoX_v1.2.3.crx"));
		options.addExtensions(new File("D:\\selenium-2.52.0\\ext\\Browsec_v2.5.7.crx"));
		
		System.setProperty("webdriver.chrome.driver", "D:\\selenium-2.52.0\\chromedriver.exe");
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://tjournal.ru");
		 Thread.sleep(20000000);
        System.out.println("Successfully opened the website https://tjournal.ru");
        driver.findElement(By.className("b-head__actions__login")).click();
        Thread.sleep(2000);
        
        /*
        driver.findElement(By.xpath("//div[@class='b-login-popup__login']/a[@class='tw']")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("username_or_email")).sendKeys("login");
        driver.findElement(By.id("password")).sendKeys("pass");
        driver.findElement(By.id("allow")).click(); 
        */
        
        ////////////
        
        driver.findElement(By.xpath("//div[@class='b-login-popup__login']/a[@class='vk']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("login");
        driver.findElement(By.xpath("//input[@name='pass']")).sendKeys("pass");
        driver.findElement(By.id("install_allow")).click(); 
        Thread.sleep(2000);
        driver.findElement(By.id("install_allow")).click(); 
        
        //////////////
        
        Thread.sleep(2000);
		driver.get("https://tjournal.ru/users/16412/comments");
		Thread.sleep(2000);
		
		List<WebElement> comments = driver.findElements(By.xpath("//div[@class='b-profile__comments']/div/div[1]/a"));
		List<String> commentsLinks = new ArrayList<String>();

		for (WebElement webElement : comments) {
			commentsLinks.add(webElement.getAttribute("href"));
		}
		for (String link : commentsLinks) {
			driver.get(link);
			//Create an action object called myMouse
			Actions myMouse = new Actions(driver); 
			Thread.sleep(1000);
			//there is a slight delay before each mouse movement hence the "Thread.sleep" statement
			JavascriptExecutor js = (JavascriptExecutor) driver;
			myMouse.moveToElement(driver.findElement(By.className("highlight")), 0,0).build().perform();   //Shows link
			js.executeScript("javascript:window.scrollBy(0,-100)");
			//myMouse.moveToElement(driver.findElement(By.className("highlight")).findElement(By.className("marks"))).build().perform();   //Shows link
			myMouse.contextClick(driver.findElement(By.className("highlight"))).build().perform();
			WebElement down = driver.findElement(By.className("highlight")).findElement(By.className("thumbs-down"));
			down.click();
			myMouse.sendKeys(Keys.ESCAPE);
			Thread.sleep(1000);
		}

        driver.quit();
    }
}