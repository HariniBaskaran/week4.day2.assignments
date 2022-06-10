package week4.day2.assignments;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Snapdeal {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		
		//Launch https://www.snapdeal.com/
		driver.get("https://www.snapdeal.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
		
		//Go to Mens Fashion
		WebElement mensFashion = driver.findElement(By.xpath("//span[text()=\"Men's Fashion\"]"));
		Actions builder = new Actions(driver);
		builder.moveToElement(mensFashion).perform();
		
		//Go to Sports Shoes
		driver.findElement(By.xpath("//span[text()=\"Sports Shoes\"]")).click();
		//Get the count of the sports shoes
		System.out.println("The cound of shoes is "  + driver.findElement(By.xpath("//div[@class='child-cat-count ']")).getText());
		
		//Click Training shoes
		List<WebElement> namelist = driver.findElements(By.xpath("//a[@class='child-cat-node dp-widget-link hashAdded']//div[@class='child-cat-name ']"));
		namelist.get(1).click();
		
		//Sort by Low to High
		driver.findElement(By.xpath("//div[@class='sorting-sec animBounce']//span")).click();
		
		driver.findElement(By.xpath("//li[@class='search-li']")).click();

		// Check if the items displayed are sorted correctly
		Thread.sleep(3000);
		List<WebElement> pricelist = driver.findElements(By.xpath("//span[@class='lfloat product-price']"));
		List<Integer> price = new ArrayList<Integer>();
		List<Integer> sort = new ArrayList<Integer>();
		for(int i = 0; i < pricelist.size() ; i++)
			price.add(Integer.parseInt(pricelist.get(i).getAttribute("data-price")));
		
		sort.addAll(price);
		Collections.sort(sort);
		

		if(!(price.equals(sort)))
			System.out.println("It is not sorted properly ");
		
		Thread.sleep(3000);
		//Select the price range (900-1200)
		driver.findElement(By.xpath("//input[@name='fromVal']")).clear();
		driver.findElement(By.xpath("//input[@name='fromVal']")).sendKeys("900");
		driver.findElement(By.xpath("//input[@name='toVal']")).clear();
		driver.findElement(By.xpath("//input[@name='toVal']")).sendKeys("1200");
		
		driver.findElement(By.xpath("//button[text()='View More ']")).click();
		
		//Filter with color Navy 
		List<WebElement> elements = driver.findElements(By.xpath("//label//span[@class='filter-color-tile']"));
		elements.get(5).click();

		//verify the all applied filters 
		System.out.println(driver.findElement(By.xpath("//a[text()='Navy']")).isDisplayed());
		
		WebElement mouseHover = driver.findElement(By.xpath("//img[@title='Columbus Navy Training Shoes']"));
		
		//Mouse Hover on first resulting Training shoes
		// click QuickView button
		WebElement quickView = driver.findElement(By.xpath("//div[@class='center quick-view-bar  btn btn-theme-secondary  ']"));
		builder.moveToElement(mouseHover).click(quickView).perform();
		
		Thread.sleep(3000);
		// Print the cost and the discount percentage
		System.out.println(driver.findElement(By.xpath("//span[@class='payBlkBig']")).getText());
		System.out.println(driver.findElement(By.xpath("//span[@class='percent-desc ']")).getText());
		
		
		// Take the snapshot of the shoes.
		File image = driver.getScreenshotAs(OutputType.FILE);
		File image1 = new File(".\\image.png");
		FileUtils.copyFile(image, image1);
		
		// Close the current window
		driver.findElement(By.xpath("//div[@class='close close1 marR10']//i[@class='sd-icon sd-icon-delete-sign']")).click();
		
		
		//Close the main window
		//driver.close();

	}
	
	

}
