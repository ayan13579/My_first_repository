package com.test;



	import java.io.File;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
	import org.testng.Assert;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

	public class Test_main {
		
		private WebDriver driver;
		private ExtentReports report;
		String appURL = "http://google.com";

		@BeforeClass
		public void testSetUp() {
			try {
			System.out.println("i am before class");
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Chromedriver\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			 report=new ExtentReports(System.getProperty("user.dir")+"\\Reports\\Report.html");
			
			}catch(Exception e ) {System.out.println(e.getMessage());}
		}
		
		@Test
		public void verifyGooglePageTittle() throws InterruptedException {
			try {
			System.out.println("i am test");
			driver.navigate().to(appURL);
			Thread.sleep(3000);
			ExtentTest test=report.startTest("Page Title Validation");
			
			String getTitle = driver.getTitle();
			
			Assert.assertEquals(getTitle, "Google");
			driver.findElement(By.xpath("//*[@id='tsf']/div[2]/div[1]/div[1]/div/div[2]/input")).sendKeys("Ayan");
			
			AShot ashot=new AShot();
			Screenshot screen=ashot.shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
			ImageIO.write(screen.getImage(), "jpg", new File(System.getProperty("user.dir")+"\\Screenshot\\Image.jpg"));
			if(getTitle.trim().equals("Google")) {
				test.log(LogStatus.PASS, "Its matching"+test.addScreenCapture(System.getProperty("user.dir")+"\\Screenshot\\Image.jpg"));
			}else {
				test.log(LogStatus.FAIL, "Its not matching"+test.addScreenCapture(System.getProperty("user.dir")+"\\Screenshot\\Image.jpg"));
			}
			report.endTest(test);
			report.flush();
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

