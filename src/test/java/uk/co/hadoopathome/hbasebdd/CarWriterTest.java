package uk.co.hadoopathome.hbasebdd;

import org.apache.hadoop.hbase.HBaseTestingUtility;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.ClassRule;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class CarWriterTest {

    @ClassRule
    public static HBaseTestServer HBASE_TEST_SERVER = new HBaseTestServer();

    @Test
    public void testAddData() throws IOException {
        HBaseTestingUtility hbaseUtility = HBASE_TEST_SERVER.getHbaseUtility();
        Connection connection = hbaseUtility.getConnection();
        CarWriter carWriter = new CarWriter(connection);
        carWriter.addNewCar("AB56 XYZ", "Vauxhall Vectra", 2016);

        Get get = new Get(Bytes.toBytes("AB56 XYZ"));
        get.addFamily(Bytes.toBytes("c"));
        Table table = connection.getTable(TableName.valueOf("cars"));
        Result result = table.get(get);
        byte[] model = result.getValue(Bytes.toBytes("c"), Bytes.toBytes("model"));
        assertEquals("Vauxhall Vectra", Bytes.toString(model));
        byte[] productionYear = result.getValue(Bytes.toBytes("c"), Bytes.toBytes("productionYear"));
        assertEquals(2016, Bytes.toInt(productionYear));
    }
}