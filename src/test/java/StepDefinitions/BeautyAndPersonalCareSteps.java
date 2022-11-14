package StepDefinitions;

import Pages.DialogContent;
import Utilities.GWD;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BeautyAndPersonalCareSteps {

    DialogContent dc = new DialogContent();

    WebDriverWait wait = new WebDriverWait(GWD.getDriver(), Duration.ofSeconds(30));

    @Given("Select Beauty and Personal Care from the drop-down menu")
    public void selectBeautyAndPersonalCareFromTheDropDownMenu() {
        dc.findAndSelect("allSelectMenu", "Beauty & Personal Care");
    }

    @And("Verify that you are on the Beauty and Personal Care page")
    public void verifyThatYouAreOnTheBeautyAndPersonalCarePage() {
        dc.findAndContainsText("baslikYazisi","Beauty and Personal Care");
    }

    @And("Click Shave & Hair Removal")
    public void clickShaveHairRemoval() {
        dc.findAndClick("shaveAndHairRemovalBtn");
    }

    @And("Sort from most expensive to least expensive")
    public void sortFromMostExpensiveToLeastExpensive() {
        dc.findAndSelect("miniSelectMenu","Price: High to Low");
//        WebElement menu= GWD.getDriver().findElement(By.id("s-result-sort-select"));
//        Select ddmenu=new Select(menu);
//        ddmenu.selectByIndex(2);
    }

    @And("Choose shopping filters")
    public void chooseShoppingFilters() {

        dc.findAndClick("filtre4YildizVeUzeri");
        dc.findAndClick("filtre200DolarVeUzeriBirim");
        dc.findAndClick("filtrePremiumSelection");
        dc.findAndClick("filtreSellerAmazon");

    }

    @And("Check how many items are left in the desired criteria")
    public void checkHowManyItemsAreLeftInTheDesiredCriteria() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Department']")));

        int bosluk=dc.getMyElement("geriyeKalanAramaSonucu").getText().indexOf(" ");
        String sadeceSayi=dc.getMyElement("geriyeKalanAramaSonucu").getText().substring(0,bosluk);
        System.out.println("<------------------------------------------------>");
        System.out.println("İstenen kriterlere uygun " +sadeceSayi+ " tane ürün bulunmuştur.");
        System.out.println("<------------------------------------------------>");

    }

    @And("Randomly select one of the products that meet the desired criteria")
    public void randomlySelectOneOfTheProductsThatMeetTheDesiredCriteria() {

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div[data-component-type='s-search-result']")));
        List<WebElement> kriterlereUyanUrunler=GWD.getDriver().findElements(By.cssSelector("div[data-component-type='s-search-result']"));
        int random=(int)(Math.random() * kriterlereUyanUrunler.size());
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[data-component-type='s-search-result']")));
        kriterlereUyanUrunler.get(random).click();

    }

    @Then("Critical information of the selected product should appear")
    public void criticalInformationOfTheSelectedProductShouldAppear() {
        System.out.println("<--------------------------------------------------------------------------------------->");
        System.out.println("Seçilen Ürün= "+ dc.getMyElement("urunAdi").getText());
        System.out.println("<--------------------------------------------------------------------------------------->");

        List<WebElement> urunBilgi=GWD.getDriver().findElements(By.cssSelector("ul[class='a-unordered-list a-nostyle a-vertical a-spacing-none detail-bullet-list']>li>span>span[class='a-text-bold']"));
        List<WebElement> urunBilgiCevap=GWD.getDriver().findElements(By.cssSelector("span[class='a-text-bold']+span"));

        System.out.println("<----------------------->");
        System.out.println("Seçilen Ürünün Detayları");
        System.out.println("<--------------------------------------------------------------------------------------->");
        for (int i = 0; i < urunBilgiCevap.size(); i++) {
            System.out.println(urunBilgi.get(i).getText()+urunBilgiCevap.get(i).getText());
        }
        System.out.println("<--------------------------------------------------------------------------------------->");

    }
}
