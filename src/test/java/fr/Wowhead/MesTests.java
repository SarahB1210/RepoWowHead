package fr.Wowhead;
import static org.junit.Assert.*;


import java.awt.Desktop.Action;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;


public class MesTests {
	WebDriver driver = new ChromeDriver();

	public List<String> loadFile(String filePath) throws FileNotFoundException {

		URI uri = null;

		File file = new File(filePath);
		ArrayList<String> list = new ArrayList<String>();

		if(file.exists()) {
			Scanner s = new Scanner(file, "UTF-8");

			while (s.hasNextLine()){
				list.add(s.nextLine());
			}
			s.close();

		}
		return list;
	}


	@Test
	public void Test() throws InterruptedException, FileNotFoundException {
		driver.get("https://fr.wowhead.com/");
		driver.manage().window().maximize();

		PageAccueil page_accueil = PageFactory.initElements(driver, PageAccueil.class);
		Thread.sleep(1000);	

		page_accueil.btn_accept_cookies.click();
		Thread.sleep(1000);	

		PageRecherchePNJ page_recherchePNJ = page_accueil.submit_search(driver,"Lardeur");
		Thread.sleep(1000);

		PagePNJ page_PNJ = page_recherchePNJ.click_element(driver);
		Thread.sleep(1000);




		for (int i=1; i<= driver.findElements(By.xpath("//tbody[contains(@class,'clickable')]/tr")).size(); i++)
		{
			WebElement elt_link = driver.findElement(By.xpath("//tbody[contains(@class,'clickable')]/tr["+i+"]/td[3]//a"));

			new Actions(driver).moveToElement(elt_link).build().perform();
			Thread.sleep(1000);

			WebElement item_form = driver.findElement(By.xpath("//div[contains(@class,'wowhead-tooltip wowhead')]"));
			

			String item_array = item_form.getText();
			//String[] item_array = item_form.getText().split("\n");
			
			String[] file_name = {"import1.txt","import2.txt","import3.txt","import4.txt","import5.txt"};
			
			List<String> item_list =  loadFile("src/test/resources/"+file_name[i-1]);


			for(int j = 1; j < item_list.size(); j++) {

					assertTrue(item_array.contains(item_list.get(j)));
					
				
			}

		}

	}
	@After
	public void tearDown() {
		driver.quit();
	}


}
