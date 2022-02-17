package Main.Actions;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Main.Log;
import Main.RunCucumberTest;

/*
 * En esta clase hay métodos de Selenium y otros útiles
 * */

public class WebActions extends RunCucumberTest {

	public void takeScreenShot() {
		try {
			String folder = "outputs" + File.separator + "screenshots";
			File capturaPantalla = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy__hh_mm_ss");

			// Comprobar si la carpeta existe. Si no, se crea
			File folderScreenshots = new File(folder);

			if (!folderScreenshots.exists()) {
				if (!folderScreenshots.getParentFile().exists()) {
					folderScreenshots.getParentFile().mkdir();
				}
				folderScreenshots.mkdir();
			}

			String destFile = df.format(new Date()) + ".jpg";
			try {
				FileUtils.copyFile(capturaPantalla, new File(folder + File.separator + destFile));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public static File compressImage(File input) {

		BufferedImage image = null;
		OutputStream os = null;
		ImageOutputStream ios = null;
		File compressedImageFile = null;
		ImageWriter writer = null;

		try {
			image = ImageIO.read(input);
			compressedImageFile = new File(input.getPath() + "comp_" + input.getName());
			os = new FileOutputStream(compressedImageFile);
			Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
			writer = (ImageWriter) writers.next();
			ios = ImageIO.createImageOutputStream(os);
			writer.setOutput(ios);
			ImageWriteParam param = writer.getDefaultWriteParam();
			param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
			param.setCompressionQuality(0.05f);
			writer.write(null, new IIOImage(image, null, null), param);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			os.close();
			ios.close();
			writer.dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return compressedImageFile;
	}

	public static String encodeImageToBase64(File image) {

		byte[] encoded = null;
		try {
			encoded = Base64.encodeBase64(FileUtils.readFileToByteArray(image));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String(encoded, StandardCharsets.US_ASCII);
	}

	public void switchFrame(String frameId) {
		try {
			driver.switchTo().frame(frameId);
			Log.register("[OK][" + this.getClass().getSimpleName() + "]" + ": Cambio al frame " + frameId
					+ " realizado con éxito");
		} catch (Exception e) {
			Log.register("[ERROR][" + this.getClass().getSimpleName() + "]" + ": Error al realizar un cambio al frame "
					+ frameId);
		}
	}

	public void click(WebElement elm) {
		try {
			Actions actions = new Actions(driver);
			actions.moveToElement(elm).click().perform();
			Log.register("[OK][" + this.getClass().getSimpleName() + "]" + ": Click con éxito en: " + elm.toString());
		} catch (Exception e) {
			Log.register(
					"[ERROR][" + this.getClass().getSimpleName() + "]" + ": Fallo al dar click: " + e.getMessage());
		}
	}

	public void sendKeys(WebElement elm, String texto) {
		try {
			elm.sendKeys(texto);
			Log.register("[OK][" + this.getClass().getSimpleName() + "]" + ": \"" + texto + "\" enviado con éxito en: "
					+ elm.toString());
		} catch (Exception ex) {
			Log.register("[ERROR][" + this.getClass().getSimpleName() + "]" + ": No se ha podido enviar \"" + texto
					+ "\" al elemento " + elm.toString());
		}
	}

	// Presiona una tecla del teclado en un elemento
	public void pressKey(WebElement elm, Keys key) {
		try {
			elm.sendKeys(Keys.RETURN);
			Log.register("[OK][" + this.getClass().getSimpleName() + "]" + ": tecla " + key.toString()
					+ "presionada con éxito en: " + elm.toString());
		} catch (Exception ex) {
			Log.register("[ERROR][" + this.getClass().getSimpleName() + "]" + ": error al pulsar la tecla "
					+ key.toString());
		}
	}

	public void clear(WebElement elm) {
		try {
			elm.clear();
			Log.register("[OK][" + this.getClass().getSimpleName() + "]" + elm.toString() + " limpiado con éxito");
		} catch (Exception ex) {
			Log.register("[ERROR][" + this.getClass().getSimpleName() + "]" + ": No se ha podido limpiar el elemento "
					+ elm.toString());
		}
	}

	public void clearSendKeys(WebElement elm, String texto) {
		try {
			elm.clear();
			Thread.sleep(500);
			elm.sendKeys(texto);
			Thread.sleep(200);
			Log.register("[OK][" + this.getClass().getSimpleName() + "]" + ": \"" + texto + "\" enviado con éxito en: "
					+ elm.toString());
		} catch (Exception ex) {
			Log.register("[ERROR][" + this.getClass().getSimpleName() + "]" + ": No se ha podido enviar \"" + texto
					+ "\" al elemento " + elm.toString());
		}
	}

	// Buscar un elemento por xpath o css
	public WebElement searchElement(String nombreElemento, String repositorio, String tipoBusqueda) {
		WebElement elm = null;
		if (repositorio.equalsIgnoreCase("login") || repositorio.equalsIgnoreCase("loginPage")) {
			switch (tipoBusqueda) {
				case "css": elm = driver.findElement(By.cssSelector(repositoryloginPage.getProperty(nombreElemento)));
					break;
				case "xpath": elm = driver.findElement(By.xpath(repositoryloginPage.getProperty(nombreElemento)));
					break;
				case "name": elm = driver.findElement(By.name(repositoryloginPage.getProperty(nombreElemento)));
					break;
				case "id": elm = driver.findElement(By.id(repositoryloginPage.getProperty(nombreElemento)));
					break;
			}
		} else if (repositorio.equalsIgnoreCase("main") || repositorio.equalsIgnoreCase("mainPage")) {
			switch (tipoBusqueda) {
				case "css": elm = driver.findElement(By.cssSelector(repositoryMainPage.getProperty(nombreElemento)));
					break;
				case "xpath": elm = driver.findElement(By.xpath(repositoryMainPage.getProperty(nombreElemento)));
					break;
				case "name": elm = driver.findElement(By.name(repositoryMainPage.getProperty(nombreElemento)));
					break;
				case "id": elm = driver.findElement(By.id(repositoryMainPage.getProperty(nombreElemento)));
					break;
			}
		}
		
		return elm;
	}

	/**
	 * Booleans
	 **/
	public boolean isEquals(WebElement element, String message) {
		if (element.getText().equalsIgnoreCase(message)) {
			Log.register("[OK][" + this.getClass().getSimpleName() + "]" + "[OK: Los textos coinciden:]"
					+ element.getText() + " = " + message);
			return true;
		} else {
			Log.register("[ERROR][" + this.getClass().getSimpleName() + "]" + "[Error: Los textos no coinciden]");
			Log.register("[  WARNING  ] - Texto mostrado: " + element.getText());
			Log.register("[  WARNING  ] - Texto que se mostraba: " + message);
			return false;
		}
	}

	public boolean isDisplayed(WebElement element) {
		if (element.isDisplayed()) {
			Log.register("[OK][" + this.getClass().getSimpleName() + "]" + "[OK: Se muestra el elemento "
					+ element.getText() + "]");
			return true;
		} else {
			Log.register("[ERROR][" + this.getClass().getSimpleName() + "]" + "[Error: No se muestra el elemento "
					+ element.getText() + "]");
			return false;
		}
	}

	// Método para seleccionar una opcion de un desplegable con texto visible
	public void selectSamples(WebElement element, String txtvisible) {
		try {
			Select sel = new Select(element);
			sel.selectByVisibleText(txtvisible);
			Log.register("[OK][" + this.getClass().getSimpleName() + "]" + ": Select: " + txtvisible + " in "
					+ element.toString());
		} catch (Exception e) {
			Log.register("[ERROR][" + this.getClass().getSimpleName() + "]" + ": Fail Select: " + txtvisible + " in "
					+ e.getMessage());
		}
	}

	public void selectByIndex2 (WebElement elm, int index) {
		String msg;
		try {
			Select sel = new Select(elm);
			sel.selectByIndex(index);
			Log.register("[OK][" + this.getClass().getSimpleName() + "]" + ": Espera OK. Elemento " + elm.toString()
					+ " visible y pulsado");
		} catch (Exception ex) {
			Log.register("[ERROR][" + this.getClass().getSimpleName() + "]" + ": Error al realizar la espera.");
		}
	}
	// Método para seleccionar de un desplegable pasandole el id y el index
	public void selectByIndex(String id, int index) {
		try {
			WebElement testDropDown = driver.findElement(By.id(id));
			Select dropdown = new Select(testDropDown);
			dropdown.selectByIndex(index);
			Log.register("[OK][" + this.getClass().getSimpleName() + "]" + ": Select: " + index + " in "
					+ testDropDown.toString());
		} catch (Exception e) {
			Log.register("[ERROR][" + this.getClass().getSimpleName() + "]" + ": Fail Select: " + index + " in "
					+ e.getMessage());
		}
	}

	public void selectByName(String name, int index) {
		try {
			WebElement testDropDown = driver.findElement(By.name(name));
			Select dropdown = new Select(testDropDown);
			dropdown.selectByIndex(index);
			Log.register("[OK][" + this.getClass().getSimpleName() + "]" + ": Select: " + index + " in "
					+ testDropDown.toString());
		} catch (Exception e) {
			Log.register("[ERROR][" + this.getClass().getSimpleName() + "]" + ": Fail Select: " + index + " in "
					+ e.getMessage());
		}
	}

	// Pasa el ratón por encima
	public void mouseOver(WebElement elm) {
		try {
			Actions action = new Actions(driver);
			action.moveToElement(elm).build().perform();
			Log.register("[OK][" + this.getClass().getSimpleName() + "]" + ": Ratón sobre : " + elm.toString());
		} catch (Exception ex) {
			Log.register("[ERROR][" + this.getClass().getSimpleName() + "]" + ": Fallo al pasar el ratón sobre "
					+ elm.toString() + " - " + ex.getMessage());
		}
	}

	/* Métodos para las esperas */

	// Este método espera a que un elemento esté visible
	public void waitForVisibility(WebElement elm) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 55);
			wait.until(ExpectedConditions.visibilityOf(elm));
			Log.register("[OK][" + this.getClass().getSimpleName() + "]" + ": Espera OK. Elemento " + elm.toString()
					+ " visible");
		} catch (Exception ex) {
			Log.register("[ERROR][" + this.getClass().getSimpleName() + "]" + ": Error al realizar la espera.");
		}
	}

	public void waitForVisibilityClick(WebElement elm) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(elm));
			elm.click();
			Log.register("[OK][" + this.getClass().getSimpleName() + "]" + ": Espera OK. Elemento " + elm.toString()
					+ " visible y pulsado");
		} catch (Exception ex) {
			Log.register("[ERROR][" + this.getClass().getSimpleName() + "]" + ": Error al realizar la espera.");
		}
	}

	// Este método espera un nçumero de segundos determinado
	public void wait(int secs) {
		try {
			Thread.sleep(secs * 1000);
			Log.register("[OK][" + this.getClass().getSimpleName() + "]" + ": Espera de " + secs + " realizada.");
		} catch (Exception ex) {
			Log.register("[ERROR][" + this.getClass().getSimpleName() + "]" + ": Error al realizar la espera.");
		}
	}

	/* Métodos para los scrolls */

	// Realiza un scroll hasta abajo de la pantalla
	public void scrollBottom() {
		try {
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
			Log.register("[OK][" + this.getClass().getSimpleName() + "]" + ": Scroll hacia abajo con éxito.");
		} catch (Exception ex) {
			Log.register(
					"[ERROR][" + this.getClass().getSimpleName() + "]" + ": Error al realizar un scroll hacia abajo");
		}
	}

	// Realiza un scroll hasta arriba de la pantalla
	public void scrollTop() {
		try {
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollTop)");
			Log.register("[OK][" + this.getClass().getSimpleName() + "]" + ": Scroll hacia arriba con éxito.");
		} catch (Exception ex) {
			Log.register(
					"[ERROR][" + this.getClass().getSimpleName() + "]" + ": Error al realizar un scroll hacia arriba.");
		}
	}

	// Realiza un scroll un número de pixeles que se pasan como parámetro
	public void scrollDown(int puntos) {
		try {
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0," + puntos + ")");
			Log.register("[OK][" + this.getClass().getSimpleName() + "]" + ": Scroll hacia abajo con éxito, " + puntos
					+ " pixeles");
		} catch (Exception ex) {
			Log.register("[ERROR][" + this.getClass().getSimpleName() + "]"
					+ ": Error al realizar un scroll hacia abajo en " + puntos + " píxeles");
		}
	}

	public void scrollUp(int puntos) {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("scroll(0, " + puntos + ");");
			Log.register("[OK][" + this.getClass().getSimpleName() + "]" + ": Scroll hacia arriba con éxito, " + puntos
					+ " pixeles");
		} catch (Exception ex) {
			Log.register("[ERROR][" + this.getClass().getSimpleName() + "]"
					+ ": Error al realizar un scroll hacia arriba en " + puntos + " píxeles");
		}
	}

	// Método para hacer un scroll hacia abajo hasta que un elemento es visible
	public void scrollDownTo(WebElement elm) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", elm);
			Log.register("[OK][" + this.getClass().getSimpleName() + "]" + ": Scroll hacia abajo con éxito, "
					+ elm.toString() + " visible");
		} catch (Exception ex) {
			Log.register("[ERROR][" + this.getClass().getSimpleName() + "]"
					+ ": Error al realizar un scroll hacia abajo en busca de " + elm.toString());
		}
	}

	/* Metodos para las fechas */
	public String fechaDDMMYY() {
		Date now = new Date();
		String date = new SimpleDateFormat("dd/MM/yyyy").format(now);
		return date;
	}

	public String fechaDDMM() {
		Date now = new Date();
		String date = new SimpleDateFormat("dd/MM/yyyy").format(now);
		date = date.substring(0, 5);
		String dateN = date.replace("/", "");
		return dateN;
	}
	
}
