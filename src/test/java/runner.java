import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/feature"},
        plugin = {"pretty"},
        junit = "--step-notifications",
        tags = "@runit"
)
public class runner {
}
