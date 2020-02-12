package fr.Wowhead;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageRecherchePNJ {

	@FindBy (xpath="//a[contains(@href,'46254')][@class='listview-cleartext']")
	WebElement elt_clickable;
	
	public PagePNJ click_element(WebDriver driver) {
		
		elt_clickable.click();
		return PageFactory.initElements(driver, PagePNJ.class);
	}
	
}
