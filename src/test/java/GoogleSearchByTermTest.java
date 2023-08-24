import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GoogleSearchByTermTest extends WebTest {

    @BeforeEach
    public void testSetup() {
        driver.get("http://google.com");
        //WebElement acceptAllButton = driver.findElement(By.xpath("//button[@id='L2AGLb']"));
        // acceptAllButton.click();
        WebElement rejectAllCookiesButton = driver.findElement(By.id("W0wltc"));
        waitForButtonToBeClickable(rejectAllCookiesButton);
        rejectAllCookiesButton.click();
    }

    @Test
    public void resultFound_when_searchTermProvided_telerikAcademyAlpha() {
        String searchTerm = "IT Career Start in 6 Months - Telerik Academy Alpha";
        WebElement searchField = driver.findElement(By.id("APjFqb"));
        waitForElementToBeVisible(searchField);
        searchField.sendKeys("Telerik Academy Alpha");

        WebElement searchButton = driver.findElement(By.xpath("(//input[@class='gNO89b'])[2]"));
        waitForButtonToBeClickable(searchButton);
        searchButton.click();
        WebElement firstResult = driver.findElement(By.xpath("(//h3[@class = 'LC20lb MBeuO DKV0Md'])[1]"));
        waitForElementToBeVisible(firstResult);
        assertEquals(searchTerm, firstResult.getText(), "Search result not found");
        firstResult.click();
        assertTabTitleEquals(searchTerm);
    }
}
