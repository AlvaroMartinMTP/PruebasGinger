package Main;

import Main.Log;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import java.util.Properties;

import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import Main.Actions.WebActions;


@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "json:target/report.json", "de.monochromata.cucumber.report.PrettyReports:target/pretty-cucumber"})
public class RunCucumberTest {

	public static WebDriver driver = null;
    public static WebDriverWait wait = null;
    
    public static Log log;
    public static WebActions wa;
    
    public static Properties config = null;
    public static Properties datos = null;
    
    //Repositorios
    public static Properties repositoryloginPage = null;
    public static Properties repositoryMainPage = null; 
	
}
