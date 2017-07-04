package com.demo;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.web.Browser;
import com.hp.lft.sdk.web.BrowserFactory;
import com.hp.lft.sdk.web.BrowserType;
import com.hp.lft.verifications.Verify;
import org.junit.*;
import com.hp.lft.sdk.web.*;
import com.hp.lft.sdk.*;
import com.hp.lft.sdk.WaitUntilTestObjectState.WaitUntilEvaluator;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import com.hp.lft.sdk.WaitUntilTestObjectState.WaitUntilEvaluator;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class E2EAvantageDemo extends UnitTestClassBase {

    public static final String USERNAME = "johnhpe1";
    public static final String PASSWORD = "HPEsw123";
    public static String appURL = "http://advantageonlineshopping.com/";

    public BrowserType browserType = BrowserType.CHROME;

    protected static Browser browser;

    public E2EAvantageDemo() {
        //Change this constructor to private if you supply your own public constructor
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        instance = new E2EAvantageDemo();
        globalSetup(E2EAvantageDemo.class);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        browser.describe(Link.class, new LinkDescription.Builder()
                .tagName("A").innerText("johnhpe1 My account My Orders Sign out ").build()).click();
        browser.describe(Link.class, new LinkDescription.Builder()
                .tagName("LABEL").innerText("Sign out").build()).click();
        browser.close();
        globalTearDown();
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    public boolean waitUntilElementExists(WebElement webElem) throws GeneralLeanFtException
    {
        return WaitUntilTestObjectState.waitUntil(webElem,new WaitUntilEvaluator<WebElement>(){
            public boolean evaluate(WebElement we){
                try{
                    return we.exists() && we.isVisible();
                }
                catch(Exception e){
                    return false;
                }
            }
        });
    }

    @Test
    public void cumparare1Boxa() throws GeneralLeanFtException {
        // Lanseaza browserul
        browser = BrowserFactory.launch(browserType);

        // Navigheaza catre site-ul web
        browser.navigate(appURL);
        browser.sync();

        //Apasa buton Boxe
        browser.describe(Link.class, new LinkDescription.Builder()
                .tagName("DIV").innerText("SPEAKERS Shop Now ").build()).click();
        //Selecteaza boxa "Bose"
        browser.describe(WebElement.class, new WebElementDescription.Builder()
                .tagName("A").innerText("Bose Soundlink Bluetooth Speaker III").build()).click();
        //Adauga boxa in cosul de cumparaturi
        browser.describe(Button.class, new ButtonDescription.Builder()
                .buttonType("submit").tagName("BUTTON").name("ADD TO CART").build()).click();
        //apasa buton utilizator
        browser.describe(Link.class, new LinkDescription.Builder()
                .tagName("A").innerText("My account My Orders Sign out ").build()).click();
        //logIn
        browser.describe(EditField.class, new EditFieldDescription.Builder()
                .type("text").tagName("INPUT").name("username").build()).setValue(USERNAME);
        browser.describe(EditField.class, new EditFieldDescription.Builder()
                .type("password").tagName("INPUT").name("password").build()).setValue(PASSWORD);
        browser.describe(Button.class, new ButtonDescription.Builder()
                .buttonType("button").tagName("BUTTON").name("SIGN IN").build()).click();
        //revin la pagina principala
        browser.describe(Link.class, new LinkDescription.Builder()
                .tagName("A").innerText("HOME").build()).click();
    }

    @Test
    public void cumparare2Tablete() throws GeneralLeanFtException {
        //Apasa buton Tablete
        browser.describe(Link.class, new LinkDescription.Builder()
                .tagName("DIV").innerText("TABLETS Shop Now ").build()).click();
        //Selecteaza tableta "HP PRO"
        browser.describe(WebElement.class, new WebElementDescription.Builder()
                .tagName("A").innerText("HP Pro Tablet 608 G1").build()).click();
        //schimba culoarea tabletei
        browser.describe(WebElement.class, new WebElementDescription.Builder()
                .className("productColor ng-scope").tagName("SPAN").innerText("").build()).click();
        //Adauga tableta in cosul de cumparaturi
        browser.sync();
        browser.describe(Button.class, new ButtonDescription.Builder()
                .buttonType("submit").tagName("BUTTON").name("ADD TO CART").build()).click();
        //revin la pagina principala
        browser.describe(Link.class, new LinkDescription.Builder()
                .tagName("A").innerText("HOME").build()).click();

    }
    @Test
    public void cumparare3Speciale() throws GeneralLeanFtException {
        //accesare oferte speciale
        browser.describe(Button.class, new ButtonDescription.Builder()
                .buttonType("submit").tagName("BUTTON").name("SEE OFFER").build()).click();
        //schimba culoare laptop
        browser.describe(WebElement.class, new WebElementDescription.Builder()
                .className("productColor ng-scope colorSelected").tagName("SPAN").innerText("").build()).click();
        //adauga mai multe laptopuri
        browser.describe(EditField.class, new EditFieldDescription.Builder()
                .type("text").tagName("INPUT").name("quantity").build()).setValue("5");
        //Adauga laptopurile in cosul de cumparaturi
        browser.describe(Button.class, new ButtonDescription.Builder()
                .buttonType("submit").tagName("BUTTON").name("ADD TO CART").build()).click();
        //accesare cos cumparaturi
        browser.describe(Link.class, new LinkDescription.Builder()
                .role("link").accessibilityName("").tagName("A").index(1).build()).click();
        waitUntilElementExists(browser.describe(Button.class, new ButtonDescription.Builder()
                .buttonType("submit").tagName("BUTTON").className("roboto-medium ng-binding").build()));
        //check-out
        browser.describe(Button.class, new ButtonDescription.Builder()
                .buttonType("submit").tagName("BUTTON").className("roboto-medium ng-binding").build()).click();
        Verify.areEqual("Shabazi 19", browser.describe(WebElement.class, new WebElementDescription.Builder()
                .accessibilityName("").className("ng-binding").tagName("LABEL").innerText("Shabazi 19").index(0).build()).getInnerText());
        browser.describe(Button.class, new ButtonDescription.Builder()
                .buttonType("submit").tagName("BUTTON").name("NEXT").build()).click();
        browser.describe(Button.class, new ButtonDescription.Builder()
                .buttonType("button").role("button").accessibilityName("").tagName("BUTTON").name("PAY NOW").index(0).build()).click();
        Verify.areEqual("Thank you for buying with Advantage", browser.describe(WebElement.class, new WebElementDescription.Builder()
                .tagName("SPAN").innerText("Thank you for buying with Advantage").build()).getInnerText());
    }

}

