import com.saucelabs.saucerest.SauceREST;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class SauceRestApiTest
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
	String sessionId;

	@Test
	public void testNameSetUsingSauceRestApi(Method method) throws MalformedURLException
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

		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		capabilities.setCapability("sauce:options", sauceOptions);

		driver = new RemoteWebDriver(url, capabilities);
		sessionId = driver.getSessionId().toString();

		driver.get("https://saucelabs.com");
		System.out.println(driver.getTitle());

		driver.quit();
	}

	@AfterMethod
	public void report()
	{
		HashMap<String, Object> jobInfo = new HashMap<>();
		jobInfo.put("name", TEST_NAME);
		jobInfo.put("passed", true);

		SauceREST api = new SauceREST(SAUCE_USERNAME, SAUCE_ACCESS_KEY);
		api.updateJobInfo(sessionId, jobInfo);
	}
}
