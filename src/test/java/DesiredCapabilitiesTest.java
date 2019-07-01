import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

public class DesiredCapabilitiesTest
{
	String SAUCE_USERNAME = System.getenv("SAUCE_USERNAME");
	String SAUCE_ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");
	String SAUCE_URL = "https://ondemand.saucelabs.com/wd/hub";

	String SELENIUM_PLATFORM = "Windows 10";
	String SELENIUM_BROWSER = "Chrome";
	String SELENIUM_VERSION = "latest";

	@Test
	public void testNameSetUsingDesiredCapabilities(Method method) throws MalformedURLException
	{
		URL url = new URL(SAUCE_URL);

		String TEST_NAME = this.getClass().getSimpleName() + " " + method.getName();

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("username", SAUCE_USERNAME);
		capabilities.setCapability("accessKey", SAUCE_ACCESS_KEY);
		capabilities.setCapability("platform", SELENIUM_PLATFORM);
		capabilities.setCapability("browserName", SELENIUM_BROWSER);
		capabilities.setCapability("version", SELENIUM_VERSION);
		capabilities.setCapability("name", TEST_NAME);

		RemoteWebDriver driver = new RemoteWebDriver(url, capabilities);

		driver.get("https://saucelabs.com");
		System.out.println(driver.getTitle());

		driver.quit();
	}
}
