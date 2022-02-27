package runner;

import com.google.common.collect.ClassToInstanceMap;
import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
//import org.junit.AfterClass;
//import com.cucumber.listener.Reporter;
//import cucumber.api.CucumberOptions;
//import cucumber.api.junit.Cucumber;
//import managers.FileReaderManager;



//import com.cucumber.listener.Reporter;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        tags= "@email",
        glue = {"stepDefinition"},
        plugin = {
                //"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html",
                "pretty","html:target/cucumber", "json:target/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        }
)

public class testRunner {


}
