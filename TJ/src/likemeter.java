import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map.Entry;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class likemeter {
 
	public static void main(String[] args) throws InterruptedException {
		/*
		 * 
		 * xipmuosnoino
zloioduvan
		 * 
		 */
		
		String hedzin = "16412";
		String vit = "2422";
		
		String che = "1";
		String lih = "2";
		String zhi = "3";
		String cip = "22";
		String eli = "3030";
		
		String user = "496";//klim
		ChromeOptions options = new ChromeOptions();
		//options.addExtensions(new File("D:\\selenium-2.52.0\\ext\\anonymoX_v1.2.3.crx"));
		//options.addExtensions(new File("D:\\selenium-2.52.0\\ext\\Browsec_v2.5.7.crx"));
		
		System.setProperty("webdriver.chrome.driver", "D:\\selenium-2.52.0\\chromedriver.exe");
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://tjournal.ru");
		// Thread.sleep(20000000);
        System.out.println("Successfully opened the website https://tjournal.ru");
        driver.findElement(By.className("b-head__actions__login")).click();
        Thread.sleep(2000);
        
        /*//twitter
        driver.findElement(By.xpath("//div[@class='b-login-popup__login']/a[@class='tw']")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("username_or_email")).sendKeys("login");
        driver.findElement(By.id("password")).sendKeys("pass");
        driver.findElement(By.id("allow")).click(); 
        */
        
        ////////////VK login
        
        driver.findElement(By.xpath("//div[@class='b-login-popup__login']/a[@class='vk']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("login");
        driver.findElement(By.xpath("//input[@name='pass']")).sendKeys("pass");
        driver.findElement(By.id("install_allow")).click(); 
        Thread.sleep(2000);
        driver.findElement(By.id("install_allow")).click(); 
        
        //////////////
        
        Thread.sleep(2000);
		driver.get("https://tjournal.ru/users/"+user+"/comments");
		Thread.sleep(2000);
		WebElement lastpagenumber = driver.findElement(By.xpath("//*[@id='b-content']/div[1]/div[1]/div/div/div/div[2]/div[2]/ul/li[9]/a")); 
        int lastpage = Integer.parseInt(lastpagenumber.getText());
		System.out.println("last page count"+lastpage);
		
		
		
		HashMap<String,Integer> results = new HashMap<String,Integer>();
		HashMap<String,String> names = new HashMap<String,String>();
		for(int i=1;i<lastpage;i++){
			driver.get("https://tjournal.ru/users/"+user+"/comments/page/"+i);
			List<WebElement> comments = driver.findElements(By.className("comment"));
			for (WebElement comment : comments) {
				List<WebElement> marks = comment.findElements(By.tagName("li"));
				for (WebElement mark : marks) {
					String id = mark.getAttribute("data-id");
					String flag = mark.findElement(By.tagName("a")).getAttribute("class");
					String name = mark.findElement(By.tagName("i")).getAttribute("innerText");
					//System.out.println(name + " : "+flag);
					//names.put(id, name);
					if(results.containsKey(id+"_"+flag)){
						Integer val =  results.get(id+"_"+flag);
						val++;
						results.put(id+"_"+flag, val);
					}else{
						results.put(id+"_"+flag, 1);
					}
				}
			}
		}
		Set<Entry<String, Integer>>  set = results.entrySet();
		for (Entry<String, Integer> entry : set) {
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
		
//		System.out.println("/********");
//		Set<Entry<String, String>>  nameset = names.entrySet();
//		for (Entry<String, String> entry : nameset) {
//			System.out.println("id: "+entry.getKey()+" value:"+entry.getValue());
//		}
	
        driver.quit();
    }
}