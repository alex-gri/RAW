Driver downloads automatically (WebDriverManager).
Logging with Log4j2 ('logs' folder at project root) and surefire plugin (test-output).
Screenshot on failure.
CLI parameter (browser type: chrome-default/firefox).
Service layer.

To run the test run:
com.lightpointglobal.framework.runner.TestRunner

Test supports chrome and firefox, BUT it's written and tested for CHROME only.
Firefox runs it but not everything works as it supposed.
You can try to run it on firefox giving '-b firefox' program argument.