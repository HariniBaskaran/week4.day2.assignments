package week4.day2.assignments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LearnResize {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://jqueryui.com/resizable");
		
		//Change the size of an element
		driver.switchTo().frame(0);
		WebElement resizeElement = driver.findElement(By.xpath("//div[@class='ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se']"));	
	//	System.out.println(resizeElement.getSize().getHeight() + "  " + resizeElement.getSize().getWidth() );
		System.out.println(resizeElement.getLocation().getX() + "  " + resizeElement.getLocation().getY() );
		Actions builder = new Actions(driver);
		builder.clickAndHold(resizeElement).moveByOffset(200, 200).perform();
		
	}

}
