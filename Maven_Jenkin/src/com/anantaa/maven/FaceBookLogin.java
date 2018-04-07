package com.anantaa.maven;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class FaceBookLogin
{
	public static Properties pro=new Properties();;
	InputStream fis = FaceBookLogin.class.getResourceAsStream("/config.properties");
	public String exResult;
	public String acResult;
	WebDriver driver;
	public String actual;
	public String expected;
	
	
	@BeforeClass
	public void setUrl() throws IOException {
		pro.load(fis);
		String appUrl=pro.getProperty("facebook");	
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\Chrome_Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();  
		driver.get(appUrl);
	}
	

	@BeforeMethod
	public void setValue() {
		actual="false";
		expected="true";
	}
	
	/**
	 * Page title check
	 */
	@Test(priority=0)
	public void pageTitle()
	{
		//test = extent.createTest("pageTitle");
		exResult=driver.getTitle();
		acResult="Facebook – log in or sign up";
		if(acResult.contains(exResult)) {
	    	actual="true";
	  }
		 Assert.assertEquals(actual, expected);		
	}
		
	/**
	 * Error message while both credential are blank
	 */	
	@Test(priority=1)
	public void errMsgBothBlank()
	{
		Reporter.log("Error msg opens!", true);
		//test = extent.createTest("errMsgBothBlank");
		driver.findElement(By.id("loginbutton")).click();
		exResult=driver.findElement(By.xpath(".//*[@class='_4rbf _53ij']")).getText();
		acResult="The email address or phone number that you've entered doesn't match any account.";
		if(exResult.contains(acResult)) {
	    	actual="true";
	  }
		 Assert.assertEquals(actual, expected);		
	}
	
	/**
	 * Error message while password is blank
	 *//*	
	@Test(priority=2)
	public void errPassBlank()
	{
		//test = extent.createTest("errPassBlank");
		driver.findElement(By.id("email")).sendKeys("anantaa.mitra@gmail.com");
		driver.findElement(By.id("loginbutton")).click();
		//exResult=driver.findElement(By.className("_4rbf _53ij")).getText();
		exResult=driver.findElement(By.xpath(".//*[@class='_4rbf _53ij']")).getText();
		acResult="The email address that you've entered doesn't match any account. Try entering your phone number instead.";
		if(acResult.equals(exResult)) {
	    	actual="true";
	  }
		 Assert.assertEquals(actual, expected);	
		 if(actual!= "true") {
			 takeScreenSort()
		 }
	}
	
	*//**
	 * Error message while id is blank
	 *//*	
	@Test(priority=3)
	public void errIdBlank()
	{ 
		//test = extent.createTest("errIdBlank");
		driver.findElement(By.id("pass")).sendKeys("anantaa");
		driver.findElement(By.id("loginbutton")).click();
		exResult=driver.findElement(By.xpath("//*[@class=\"_4rbf _53ij\"]")).getText();
		acResult="The email address or phone number that you've entered doesn't match any account.";
		if(exResult.contains(acResult)) {
	    	actual="true";
	  }
		 Assert.assertEquals(actual, expected);		
	}
	*/
	@AfterClass
	public void tearDown()
	{
	
		driver.close();
	}
	
}
