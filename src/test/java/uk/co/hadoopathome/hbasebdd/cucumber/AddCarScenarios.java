package uk.co.hadoopathome.hbasebdd.cucumber;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import uk.co.hadoopathome.hbasebdd.Car;
import uk.co.hadoopathome.hbasebdd.CarWriter;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class AddCarScenarios extends TestRunner {
    private Car inputCar;
    private Connection connection;

    @Before
    public void setup() {
        this.connection = HBASE_TEST_SERVER.getConnection();
    }

    @Given("^a car to be added with registration \"([^\"]*)\", model \"([^\"]*)\" and productionYear (\\d+)$")
    public void aCarToBeAdded(String registration, String model, int productionYear) {
        this.inputCar = new Car(registration, model, productionYear);
    }

    @When("^the car is added$")
    public void theCarIsAdded() throws IOException {
        CarWriter carWriter = new CarWriter(this.connection);
        carWriter.addNewCar(this.inputCar);

    }

    @Then("^the car is available in HBase$")
    public void theCarIsAvailableInHBase() throws IOException {
        Table table = this.connection.getTable(TableName.valueOf("cars"));
        Get get = new Get(Bytes.toBytes(this.inputCar.getRegistration()));
        Result result = table.get(get);
        Car returnedCar = convertResultToCar(result);
        assertEquals(inputCar, returnedCar);
    }

    private Car convertResultToCar(Result result) {
        String registration = Bytes.toString(result.getRow());
        String model = Bytes.toString(result.getValue(Bytes.toBytes("c"), Bytes.toBytes("model")));
        int productionYear = Bytes.toInt(result.getValue(Bytes.toBytes("c"), Bytes.toBytes("productionYear")));
        return new Car(registration, model, productionYear);
    }
}
