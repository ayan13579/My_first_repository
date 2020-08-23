package com.test;



	import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
	import org.testng.Assert;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;

	public class Test_main {
		
		private WebDriver driver; 
		String appURL = "http://google.com";

		@BeforeClass
		public void testSetUp() {
			try {
			System.out.println("i am before class");
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ayan Paul\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			}catch(Exception e ) {System.out.println(e.getMessage());}
		}
		
		@Test
		public void verifyGooglePageTittle() throws InterruptedException {
			try {
			System.out.println("i am test");
			driver.navigate().to(appURL);
			Thread.sleep(3000);
			String getTitle = driver.getTitle();
			Assert.assertEquals(getTitle, "Google");
			driver.findElement(By.xpath("//*[@id='tsf']/div[2]/div[1]/div[3]/center/input[2]"));
		    }catch(Exception e ) {System.out.println(e.getMessage());}
		}
		
		@AfterClass
		public void tearDown() {
			try {
			System.out.println("i am after class");
			driver.quit();
			}catch(Exception e ) {System.out.println(e.getMessage());}
		}
		
	}

