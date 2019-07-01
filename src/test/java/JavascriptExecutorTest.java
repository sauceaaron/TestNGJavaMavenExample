import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

public class JavascriptExecutorTest
{
	String SAUCE_USERNAME = System.getenv("SAUCE_USERNAME");
	String SAUCE_ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");
	String SAUCE_URL = "https://ondemand.saucelabs.com/wd/hub";

	String SELENIUM_PLATFORM = "Windows 10";
	String SELENIUM_BROWSER = "Chrome";
	String SELENIUM_VERSION = "latest";

	String TEST_NAME;
	RemoteWebDriver driver;

	@BeforeMethod
	public void setup(Method method) throws MalformedURLException
	{
		URL url = new URL(SAUCE_URL);

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("username", SAUCE_USERNAME);
		capabilities.setCapability("accessKey", SAUCE_ACCESS_KEY);
		capabilities.setCapability("platform", SELENIUM_PLATFORM);
		capabilities.setCapability("browserName", SELENIUM_BROWSER);
		capabilities.setCapability("version", SELENIUM_VERSION);

		driver = new RemoteWebDriver(url, capabilities);

		TEST_NAME = this.getClass().getSimpleName() + " " + method.getName();
	}

	@Test
	public void testNameSetUsingJavaScriptExecutor(Method method)
	{
		driver.get("https://saucelabs.com");
		System.out.println(driver.getTitle());
	}

	@AfterMethod
	public void teardown()
	{
		driver.executeScript("sauce:job-name=" + TEST_NAME);
		driver.quit();
	}
}
