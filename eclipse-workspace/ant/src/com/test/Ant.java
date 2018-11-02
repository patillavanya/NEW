package com.test;

import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import testlink.api.java.client.TestLinkAPIClient;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;

public class Ant {

public static String DEVKEY="fb46355c4f15f09358aa070adab58774";
public static String URL="http://localhost/testlink/lib/api/xmlrpc/v1/xmlrpc.php";

public void reportResult(String TestProject,String TestPlan,String testsuite,String Testcase,String Build,String Notes,String Result) throws TestLinkAPIException{
TestLinkAPIClient api=new TestLinkAPIClient(DEVKEY, URL);
api.reportTestCaseResult(TestProject, TestPlan, Testcase, Build, Notes, Result);
}

@Test
public void Test1()throws Exception
{
Ant a=new Ant();
WebDriver driver=new FirefoxDriver();
//WebDriverWait wait=new WebDriverWait(driver, 600);
String testProject="Login";
String testPlan="SampleTestPlan";
String testsuite="Logout";
String testCase="saplogin";
String build="SampleBuild";
String notes=null;
String result=null;
try{
driver.manage().window().maximize();
driver.get("https://quality.saplingapp.io/#/login");
Thread.sleep(3000);;
driver.findElement(By.xpath("//input[@id='input_0']")).sendKeys("vamsi@gmail.com");
driver.findElement(By.xpath("//input[@id='input_1']")).sendKeys("admin123!");
driver.findElement(By.xpath("//button[@type='submit']")).click();
//driver.switchTo().defaultContent();
//wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("+Testlink")));
result= TestLinkAPIResults.TEST_PASSED;
notes="Executed successfully";
}
catch(Exception e){
result=TestLinkAPIResults.TEST_FAILED;
notes="Execution failed";
}
finally{
	a.reportResult(testProject,testPlan,testsuite,testCase,build,notes,result);
driver.quit();
}
}
}
