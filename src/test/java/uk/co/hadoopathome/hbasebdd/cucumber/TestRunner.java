package uk.co.hadoopathome.hbasebdd.cucumber;

import cucumber.api.junit.Cucumber;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import uk.co.hadoopathome.hbasebdd.hbase.HBaseTestServer;

@RunWith(Cucumber.class)
public class TestRunner {
    @ClassRule
    public static HBaseTestServer HBASE_TEST_SERVER = new HBaseTestServer();
}
