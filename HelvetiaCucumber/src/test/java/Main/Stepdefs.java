package Main;

import Main.WebElements.MainPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import cucumber.api.PendingException;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Main.Actions.WebActions;

public class Stepdefs extends WebActions {

	MainPage mainPage = new MainPage();

	private final static Logger LOGGER = Logger.getLogger("Main.Stepdefs");

//	@Given("The user enter the url")
//	public void theUserEnterTheUrl() {
//		driver.get(datos.getProperty("url_euipo"));
//		wait(5);
//		click(mainPage.Continue_Design_Class);
//	}

//	@Given("the Design class application loaded")
//	public void theDesignClassApplicationLoaded() {
//		driver.get(datos.getProperty("design_class_url"));
//		wait(5);
//		click(mainPage.CONTINUE_DESIGN_CLASS);
//
//	}

	@Given("the results of a search are retrieved")
	public void theResultsOfASearchAreRetrieved(){

	}

	@When("the Translate term tool is opened")
	public void theTranslateTermToolIsOpened(){

	}
	@When("user uses the Design class tool")
	public void userUsesTheDesignClassTool(){

	}
	@When("user click on a term to add")
	public void userClickOnATermToAdd(){

	}
	@When("At least 5 terms are added to My list")
	public void atLeast5TermsAreAddedToMyList(){

	}
	@When("user open My list")
	public void userOpenMyList(){

	}
	@When("user open the trashbin icon")
	public void userOpenTheTrashbinIcon(){

	}
	@When("the result of a search are retrieved")
	public void theResultOfASearchAreRetrieved(){

	}

	@When("user open the design view tool")
	public void userOpenTheDesignViewTool(){

	}

	@When("user open advanced criteria")
	public void userOpenAdvancedCriteria(){

	}

	@When("user uses the exact search tool")
	public void userUsesTheExactSearchTool(){

	}
	@When("user uses the sort by classification")
	public void userUsesTheSortByClassification(){

	}
	@When("it is ordered by classification")
	public void itIsOrderedByClassification(){

	}


	@Then("main page is loaded")
	public void mainPageIsLoaded(){

	}
	@Then("information of the offices is returned")
	public void informationOfTheOfficesIsReturned(){

	}
	@Then("link to the offices is shown")
	public void linkToTheOfficesIsShown(){

	}
	@Then("term is added to My list")
	public void termIsAddedToMyList(){

	}
	@Then("the term is remove from My list")
	public void theTermIsRemoveFromMyList(){

	}
	@Then("the page My list is reloaded")
	public void thePageMyListIsReloaded(){

	}

	@Then("the design view tool is opened")
	public void theDesignViewToolIsOpened(){

	}

	@Then("user open a term description")
	public void userOpenATermDescription(){

	}
	@Then("user closes advanced criteria")
	public void userClosesAdvancedCriteria(){

	}
	@Then("the result of a search are not retrieved")
	public void theResultOfASearchAreNotRetrieved(){

	}
	@Given("Una url y un usuario y una pass")
	public void loginHelvetia() {
		try {
			driver.get(datos.getProperty("url"));
			wait(5);
		}catch(Exception ex) {
			throw new RuntimeException("Ha ocurrido un problema al obtener la URL de Helvetia");
		}
		if (isDisplayed(driver.findElement(By.cssSelector(repositoryloginPage.getProperty("TXTBOX_USUARIO"))))) {
			Log.register("[OK] - Hemos accedido a Helvetia");
		} else {
			Log.register("[ERROR] - Ha habido un problema al acceder a Helvetia");
			assertTrue(isDisplayed(driver.findElement(By.cssSelector(repositoryloginPage.getProperty("TXTBOX_USUARIO")))));
		}
	}

	@When("El usuario busca")
	public void elUsuarioBusca(){
		try{
			click(mainPage.Continue);
			waitForVisibility(mainPage.language);
			selectByIndex2(mainPage.language, 15);
			selectByIndex2(mainPage.language2, 5);
			selectSamples(mainPage.language, "it");
			sendKeys(mainPage.Searchbar_Wikipedia, "Brandon sanderson");
			click(mainPage.Searchbat_Click);
			waitForVisibility(mainPage.Libro1);
		} catch (Exception ex) {
			System.out.println(ex);
			throw ex;
		}
	}

}



	/*@When("El usuario busca")
	public void searchWiki(){
		try{
			click(mainPage.Continue);
			waitForVisibility(mainPage.language);
			selectByIndex2(mainPage.language, 15);
			selectByIndex2(mainPage.language2, 5);
			selectSamples(mainPage.language, "it");
			sendKeys(mainPage.Searchbar_Wikipedia, "Brandon sanderson");
			click(mainPage.Searchbat_Click);
			waitForVisibility(mainPage.Libro1);
		} catch (Exception ex) {
			System.out.println(ex);
			throw ex;
		}
	}

	@And("Click en el libro imperio final")
	public void clickEnLibro1(){
		try {
			click(mainPage.Libro1);
			wait(3);
		} catch (Exception ex) {
			System.out.println(ex);
			throw ex;
		}
	}*/

