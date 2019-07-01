import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

public class W3CCapabilitiesTest
{
	String SAUCE_USERNAME = System.getenv("SAUCE_USERNAME");
	String SAUCE_ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");
	String SAUCE_URL = "https://ondemand.saucelabs.com/wd/hub";

	String PLATFORM_NAME = "Windows 10";
	String BROWSER_NAME = "Chrome";
	String BROWSER_VERSION = "latest";
	String SELENIUM_VERSION = "3.141.59";

	String TEST_NAME;
	RemoteWebDriver driver;

	@BeforeMethod
	public void setup(Method method) throws MalformedURLException
	{
		TEST_NAME = this.getClass().getSimpleName() + " " + method.getName();

		URL url = new URL(SAUCE_URL);

		MutableCapabilities capabilities = new MutableCapabilities();
		capabilities.setCapability("platformName", PLATFORM_NAME);
		capabilities.setCapability("browserName", BROWSER_NAME);
		capabilities.setCapability("browserVersion", BROWSER_VERSION);

		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setCapability("w3c", true);

		MutableCapabilities sauceOptions = new MutableCapabilities();
		sauceOptions.setCapability("username", SAUCE_USERNAME);
		sauceOptions.setCapability("accessKey", SAUCE_ACCESS_KEY);
		sauceOptions.setCapability("seleniumVersion", SELENIUM_VERSION);
		sauceOptions.setCapability("name", TEST_NAME);

		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		capabilities.setCapability("sauce:options", sauceOptions);

		driver = new RemoteWebDriver(url, capabilities);
	}

	@Test
	public void testNameSetUsingW3cCapabilities(Method method)
	{
		driver.get("https://saucelabs.com");
		System.out.println(driver.getTitle());
	}

	@AfterMethod
	public void teardown()
	{
		driver.quit();
	}
}
