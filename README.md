# TestNGJavaMavenExample

This demonstrates various ways you can configure a simple test using Selenium Webdriver with TestNG.

The custom capability "name" set the test name which is usefule for reporting and analytics in Sauce Labs.

Note how capabilities are set differently in W3C Webdriver MutableCapabilities vs previously in DesiredCapabilities.
Using W3C Capabilities you need to specify "name" under "sauce:options"

Using DesiredCapabilities:

```desiredCapabilities.setCapability("name", TEST_NAME);```

Using MutableCapabilties with W3C WebDriver:

  ```MutableCapabilities sauceOptions = new MutableCapabilities();
  sauceOptions.setCapability("username", SAUCE_USERNAME);
  sauceOptions.setCapability("accessKey", SAUCE_ACCESS_KEY);
  sauceOptions.setCapability("seleniumVersion", SELENIUM_VERSION);
  sauceOptions.setCapability("name", TEST_NAME);
  
  MutableCapabilities capabilities = new MutableCapabilities();
  capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
  capabilities.setCapability("sauce:options", sauceOptions);```

Alternately, you can set the name using the Sauce Labs javascript executor during the test:

  ```driver.executeScript("sauce:job-name=MyTestName");```

Or using the Sauce REST api at any point, including after the test is completed:

  ```HashMap<String, Object> jobInfo = new HashMap<>();
  jobInfo.put("name", TEST_NAME);
  
  SauceREST api = new SauceREST(SAUCE_USERNAME, SAUCE_ACCESS_KEY);
  api.updateJobInfo(sessionId, jobInfo);```
