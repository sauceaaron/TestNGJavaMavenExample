# TestNGJavaMavenExample

This demonstrates various ways you can configure a simple test using Selenium Webdriver with TestNG.

You can specify the custom capability "name" to set the test name which is usefule for reporting and analytics in Sauce Labs.

Note how capabilities are set differently in W3C Webdriver MutableCapabilities vs previously in DesiredCapabilities.

Using older versions of webDriver with DesiredCapabilities you can set "name" directly:

```desiredCapabilities.setCapability("name", TEST_NAME);```

Using W3C Capabilities you need to specify "name" under "sauce:options" with MutableCapabilities

```
MutableCapabilities sauceOptions = new MutableCapabilities();
sauceOptions.setCapability("username", SAUCE_USERNAME);
sauceOptions.setCapability("accessKey", SAUCE_ACCESS_KEY);
sauceOptions.setCapability("seleniumVersion", SELENIUM_VERSION);
sauceOptions.setCapability("name", TEST_NAME);
MutableCapabilities capabilities = new MutableCapabilities();
capabilities.setCapability(ChromeOptions.CAPABILITY, new ChromeOptions());
capabilities.setCapability("sauce:options", sauceOptions);
```

Alternately, you can set the name using the Sauce Labs javascript executor during the test:

```driver.executeScript("sauce:job-name=" + TEST_NAME);```

Or using the Sauce REST api at any point, including after the test is completed:

```
HashMap<String, Object> jobInfo = new HashMap<>();
jobInfo.put("name", TEST_NAME);
SauceREST api = new SauceREST(SAUCE_USERNAME, SAUCE_ACCESS_KEY);
api.updateJobInfo(sessionId, jobInfo);
```
