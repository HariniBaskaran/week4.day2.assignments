package week4.day2.assignments;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LearnTables {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("http://www.leafground.com/pages/table.html");
		
		
		//Get the count of number of rows
		List<WebElement> rows = driver.findElements(By.xpath("//table//tr"));
		System.out.println("Number of rows : " + rows.size());
		
		
		//Get the count of number of columns
		List<WebElement> columns = driver.findElements(By.xpath("//table//tr//th"));
		System.out.println("Number of columns : " + columns.size() );
		
		//Get the progress value of 'Learn to interact with Elements'
		System.out.println("The progress value of \'Learn to interact with Elements\'" + driver.findElement(By.xpath("//table//tr[3]//td[2]")).getText());
	
	
		List<String> progress = new ArrayList<String>();
		for(int i = 2; i <= rows.size(); i++)
			for(int j = 2; j < columns.size(); j++)
				progress.add(driver.findElement(By.xpath("//table//tr[" + i + "]//td["+ j + "]")).getText());
		
		
		List<Integer> progressInt = new ArrayList<Integer>();
		for(String s: progress)
				progressInt.add(Integer.parseInt(s.substring(0, s.length() - 1)));
		
		Collections.sort(progressInt);
		
		for(int i: progressInt)
			System.out.println(i);
		
		//Check the vital task for the least completed progress.
		for(int i = 2; i <= rows.size(); i++)
			for(int j = 2; j <= columns.size(); j++)
				if(driver.findElement(By.xpath("//table//tr[" + i + "]//td["+ j + "]")).getText().contains(Integer.toString(progressInt.get(0))))
				  driver.findElement(By.xpath("//table//tr[" + i + "]//td["+ (j+1) + "]")).click();
		
		Thread.sleep(3000);
		
		//doubt
		//System.out.println(driver.findElement(By.xpath("//table//tr[6]//td[3]")).isSelected());

		
	}
	

}
