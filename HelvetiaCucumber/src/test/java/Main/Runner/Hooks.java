package Main.Runner;

import Main.RunCucumberTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import Main.Log;
import Main.Actions.WebActions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import javax.swing.JOptionPane;

public class Hooks extends RunCucumberTest {
	
    @Before
    public static void loadDriver () {
    	config = new Properties();
		datos = new Properties();
		//Repositorios
		repositoryloginPage = new Properties();
		repositoryMainPage = new Properties();
		
		try {
			DesiredCapabilities caps = new DesiredCapabilities();

			try {
				config.load(new FileInputStream("config/config.properties"));
				datos.load(new FileInputStream("datos/datos.properties"));

				repositoryloginPage.load(new FileInputStream("repository/loginPage.properties"));
				repositoryMainPage.load(new FileInputStream("repository/mainPage.properties"));
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			// Estableciendo el log
			try {
				wa = new WebActions();
				log = new Log();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			caps.setPlatform(Platform.WINDOWS);

			// Chrome
			/*
			System.setProperty("webdriver.chrome.driver", config.getProperty("chromePath"));
			caps = DesiredCapabilities.chrome();
			driver = new ChromeDriver();
			*/

			// Firefox

			System.setProperty("webdriver.gecko.driver", config.getProperty("firefoxPath"));
			caps = DesiredCapabilities.firefox();
			driver = new FirefoxDriver();


				driver.manage().window().maximize();

			}


		/*
			// Capabilities para el SO
			if (config.getProperty("platform").equalsIgnoreCase("windows")) {
				caps.setPlatform(Platform.WINDOWS);
			} else if (config.getProperty("platform").equalsIgnoreCase("linux")) {
				caps.setPlatform(Platform.LINUX);
			} else {
				caps.setPlatform(Platform.MAC);
			}

			// Driver según el config.getProperty("browser")
			if (config.getProperty("browser").equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", config.getProperty("chromePath"));
				caps = DesiredCapabilities.chrome();
				
				driver = new ChromeDriver();
			} else {
				System.setProperty("webdriver.gecko.driver", config.getProperty("firefoxPath"));
				caps = DesiredCapabilities.firefox();
				driver = new FirefoxDriver();
			}

			driver.manage().window().maximize();

			*/

		 catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Operación fallida, no se ha podido configurar el entorno");
		}
    }




	@After
	public static void afterClass(){
		try {
			driver.wait(10);
			driver.close();
			driver.quit();
			System.out.println("OK al desconectar");
		}catch(Exception ex) {
			System.out.println("Error al desconectar");
		}
	}

}
