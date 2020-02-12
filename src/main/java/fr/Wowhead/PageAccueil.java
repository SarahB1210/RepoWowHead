package fr.Wowhead;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageAccueil {
	

	@FindBy (xpath="//button[contains(text(),'Accept and Continue')]")
	WebElement btn_accept_cookies;
	
	@FindBy (xpath="//form/input[1]")
	WebElement search_bar;
	
	public PageRecherchePNJ submit_search(WebDriver driver, String name) {
		
		search_bar.clear();
		search_bar.sendKeys(name);
		search_bar.submit();
		
		return PageFactory.initElements(driver, PageRecherchePNJ.class);
	}
	
}
