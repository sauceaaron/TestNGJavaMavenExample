# TestNGJavaMavenExample

This demonstrates various ways you can configure a simple test using Selenium Webdriver with TestNG.

The custom capability "name" set the test name which is usefule for reporting and analytics in Sauce Labs.

Note how capabilities are set differently in W3C Webdriver MutableCapabilities vs previously in DesiredCapabilities.
Using W3C Capabilities you need to specify "name" under "sauce:options"
