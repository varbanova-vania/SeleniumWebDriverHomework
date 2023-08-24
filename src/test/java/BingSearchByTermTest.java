import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.*;

public class BingSearchByTermTest extends WebTest {
    @BeforeEach
    public void testSetup() {
        driver.get("https://www.bing.com/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='bnp_btn_accept']")));
        WebElement acceptButton = driver.findElement(By.xpath("//button[@id='bnp_btn_accept']"));
        waitForButtonToBeClickable(acceptButton);
        acceptButton.click();
    }

    @Test
    public void resultFound_when_searchTermProvided_telerikAcademyAlpha() {
        String searchTerm = "IT Career Start in 6 Months - Telerik Academy Alpha";
        WebElement searchField = driver.findElement(By.id("sb_form_q"));
        waitForElementToBeVisible(searchField);
        searchField.sendKeys("Telerik Academy Alpha");
        WebElement searchForm = driver.findElement(By.id("search_icon"));
        searchForm.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ol[@id='b_results']//a[contains(text(),'IT Career Start in 6 Months - Telerik Academy Alpha')]")));
        WebElement firstResult = driver.findElement(By.xpath("//ol[@id='b_results']//a[contains(text(),'IT Career Start in 6 Months - Telerik Academy Alpha')]"));
        waitForElementToBeVisible(firstResult);
        assertEquals(searchTerm, firstResult.getText(), "Search result not found");
        firstResult.click();

        assertTabTitleEquals(searchTerm);
    }
}
