package week4.day2.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		
		//Go to https://www.nykaa.com/
		driver.get("https://www.nykaa.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		// Mouseover on Brands and Search L'Oreal Paris
		WebElement brand = driver.findElement(By.xpath("//a[text()='brands']"));
		WebElement search = driver.findElement(By.id("brandSearchBox"));
		Actions builder = new Actions(driver);
		builder.moveToElement(brand).click(search).sendKeys("L'Oreal Paris").perform();
		
		//Click L'Oreal Paris
		driver.findElement(By.xpath("//a[text()=\"L'Oreal Paris\"]")).click();
		
		//Check the title contains L'Oreal Paris(Hint-GetTitle)
		if(driver.getTitle().contains("L'Oreal Paris"))
			System.out.println("The title contains L'Oreal Paris");
		
		//Click sort By and select customer top rated
		driver.findElement(By.xpath("//span[@class='sort-name']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='radio_customer top rated_undefined']/following-sibling::label//div[@class='control-indicator radio ']")).click();
		
		
		// Click Category and click Hair->Click haircare->Shampoo
		driver.findElement(By.xpath("//span[text()='Category']")).click();
		driver.findElement(By.xpath("//span[text()='Hair']")).click();;
		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
		driver.findElement(By.xpath("//div[@class='control-indicator checkbox ']")).click();
		
		// Click->Concern->Color Protection
		driver.findElement(By.xpath("//span[text()='Concern']")).click();
		driver.findElement(By.xpath("//span[text()='Color Protection']/parent::div[@class='control-value']/following-sibling::div[@class='control-indicator checkbox ']")).click();
		
		//check whether the Filter is applied with Shampoo - Doubt
		System.out.println("Whether filter is applied to shampoo? " + driver.findElement(By.xpath("//span[text()='Shampoo']")).isDisplayed());
		
		//Click on L'Oreal Paris Colour Protect Shampoo
		driver.findElement(By.xpath("//div[text()=\"L'Oreal Paris Colour Protect Shampoo\"]")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> handles= new ArrayList<String>(windowHandles);
		
		// GO to the new window and select size as 175ml
		driver.switchTo().window(handles.get(1));
		Select s = new Select(driver.findElement(By.xpath("//select")));
		s.selectByIndex(1);
		
		
		//Print the MRP of the product
		System.out.println("The MRP of the product " + driver.findElement(By.xpath("//span[@class='css-1jczs19']")).getText());
		
		//Click on ADD to BAG
		driver.findElement(By.xpath("//span[text()='Add to Bag']")).click();
		// Go to Shopping Bag 
		driver.findElement(By.xpath("//span[@class='cart-count']")).click();
		
		//Print the Grand Total amount
		driver.switchTo().frame(0);
		System.out.println("Grand Total " + driver.findElement(By.xpath("//div[@class='value medium-strong']")).getText());
		String price = driver.findElement(By.xpath("//div[@class='value medium-strong']")).getText();
		
		//Click Proceed
		driver.findElement(By.xpath("//button[@class='btn full fill no-radius proceed ']")).click();
		
		//Click on Continue as Guest
		driver.findElement(By.xpath("//button[@class='btn full big']")).click();
		
		
		//Check if this grand total is the same in step 14
		if(price.contains(driver.findElement(By.xpath("//div[@class='value']//span")).getText()))
			System.out.println("Grand total is same");
		
		//Close all windows
		//driver.quit();
		
		
	}

}
