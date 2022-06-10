package week4.day2.assignments;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LearnSortable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		
		
		driver.get("http://www.leafground.com/pages/sortable.html");
		List<WebElement> list = driver.findElements(By.xpath("//span[@class='ui-icon ui-icon-arrowthick-2-n-s']"));
		Actions builder = new Actions(driver);
		builder.clickAndHold(list.get(6)).moveToElement(list.get(0)).release().perform();

	}	

}
