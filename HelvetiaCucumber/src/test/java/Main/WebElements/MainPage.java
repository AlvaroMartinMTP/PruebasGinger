package Main.WebElements;


import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Main.Actions.WebActions;

public class MainPage extends WebActions {

        public MainPage(){
                PageFactory.initElements(driver, this);
        }


        //OFFICES

        @FindBy (xpath = "//div[contains(text(),'IPO CZ')]") public WebElement iPOCZ;
        @FindBy (xpath = "//body/div[@id='wrap_content']/div[@id='pageContent']/div[@id='contentWrapper']/div[5]/div[1]/div[3]/div[1]/div[1]/ul[1]/li[7]/a[1]/div[1]") public WebElement iNPI;

        @FindBy (xpath = "//body/div[@id='wrap_content']/div[@id='pageContent']/div[@id='contentWrapper']/div[5]/div[1]/div[3]/div[1]/div[1]/ul[1]/li[7]/a[1]") public WebElement fR;
        @FindBy (xpath = "//body/div[@id='wrap_content']/div[@id='pageContent']/div[@id='contentWrapper']/div[5]/div[1]/div[3]/div[1]/div[1]/ul[1]/li[2]/a[1]") public WebElement bG;
        @FindBy (xpath = "//body/div[@id='wrap_content']/div[@id='pageContent']/div[@id='contentWrapper']/div[5]/div[1]/div[3]/div[1]/div[1]/ul[1]/li[6]/a[1]") public WebElement eE;
        @FindBy (xpath = "//body/div[@id='wrap_content']/div[@id='pageContent']/div[@id='contentWrapper']/div[5]/div[1]/div[3]/div[1]/div[1]/ul[1]/li[1]/a[1]") public WebElement bX;

        @FindBy (xpath = "//body/div[@id='wrap_content']/div[@id='pageContent']/div[@id='contentWrapper']/div[5]/div[1]/div[3]/div[1]/div[1]/ul[1]/li[4]") public WebElement iPOCZCZ;
        @FindBy (xpath = "//body/div[@id='wrap_content']/div[@id='pageContent']/div[@id='contentWrapper']/div[5]/div[1]/div[3]/div[1]/div[1]/ul[1]/li[7]") public WebElement iNPIFR;
        @FindBy (xpath = "//body/div[@id='wrap_content']/div[@id='pageContent']/div[@id='contentWrapper']/div[5]/div[1]/div[3]/div[1]/div[1]/ul[1]/li[2]") public WebElement bPOBG;

        //body/div[@id='wrap_content']/div[@id='pageContent']/div[@id='contentWrapper']/div[5]/div[1]/div[3]/div[1]/div[3]/ul[1]/li[6]/a[1]
        //body/div[@id='wrap_content']/div[@id='pageContent']/div[@id='contentWrapper']/div[5]/div[1]/div[3]/div[1]/div[1]/ul[1]/li[8]/a[1]
        //body/div[@id='wrap_content']/div[@id='pageContent']/div[@id='contentWrapper']/div[5]/div[1]/div[3]/div[1]/div[1]/ul[1]/li[2]/a[1]
        //body/div[@id='wrap_content']/div[@id='pageContent']/div[@id='contentWrapper']/div[5]/div[1]/div[3]/div[1]/div[2]/ul[1]/li[1]
        //body/div[@id='wrap_content']/div[@id='pageContent']/div[@id='contentWrapper']/div[5]/div[1]/div[3]/div[1]/div[2]/ul[1]/li[2]

        public static final String img = "img[16]";
        @FindBy (xpath = "//div[contains(text(),'BOIP')]") public WebElement OFFICE_EXAMPLE_BODY;
        @FindBy (xpath = "//body/div[@id='wrap_content']/div[@id='pageContent']/div[@id='contentWrapper']/div[5]/div[1]/div[1]/" + img) public WebElement OFFICES_INFORMATION;
//        @FindBy (xpath = "//body/div[@id='wrap_content']/div[@id='pageContent']/div[@id='contentWrapper']/div[5]/div[1]/div[1]/img[24]") public WebElement OFFICES_INFORMATION;
//        @FindBy (xpath = "//body/div[@id='wrap_content']/div[@id='pageContent']/div[@id='contentWrapper']/div[5]/div[1]/div[1]/img[6]") public WebElement OFFICES_INFORMATION;


/* Ejemplos
    @FindBy (xpath = "xpathvalue") public WebElement labelvalue;
    @FindBy (xpath = "//*[@name=\"birthday_day\"]")
    @FindBy (xpath = "//*[@id=\"ap_email\"]")
    @FindBy (xpath = "//*[text()=\"Steelheart (The Reckoners, #1)\"]")
    @FindBy (xpath= "//*[@href= \"/wiki/El_imperio_final\"]")
    @FindBy (xpath = "//*[@class=\"select span12 saveInput\"]")
     */
//
//        @FindBy (xpath = "//*[@class=\"yui-dt27-col-actions yui-dt-col-actions yui-dt-last\"]")
//
//        @FindBy (xpath= "//*[@href= \"/wiki/El_imperio_final\"]")


        //Wikipedia
        @FindBy(xpath = "//*[@id=\"searchInput\"]")
        public WebElement Searchbar_Wikipedia;
        @FindBy(xpath = "//*[@id=\"searchButton\"]")
        public WebElement Searchbat_Click;
        @FindBy(xpath= "//*[@href= \"/wiki/El_imperio_final\"]")
        public WebElement Libro1;
        //*[@id=\"'_language_key'\"]

        @FindBy(xpath = "//*[@id=\"buttonBox\"]/a")
        public WebElement Continue;
        @FindBy(xpath = "//*[@class=\"select span12 saveInput\"]")
        public WebElement language;
        @FindBy(xpath = "//*[@id=\"_language_key\"]")
        public WebElement language2;
}

