package uk.co.hadoopathome.hbasebdd;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Stores cars into the HBase database.
 */
public class CarWriter {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarWriter.class);
    private static final TableName TABLE_NAME = TableName.valueOf("cars");
    private static final byte[] COLUMN_FAMILY = Bytes.toBytes("c");
    private static final byte[] MODEL_COLUMN_QUALIFIER = Bytes.toBytes("model");
    private static final byte[] PRODUCTION_YEAR_COLUMN_QUALIFIER = Bytes.toBytes("productionYear");

    private final Connection connection;

    public CarWriter(Connection connection) {
        this.connection = connection;
    }

    public void addNewCar(Car car) throws IOException {
        LOGGER.debug("Adding new car with registration {}", car.getRegistration());
        Table table = this.connection.getTable(TABLE_NAME);
        Put p = new Put(Bytes.toBytes(car.getRegistration()));
        p.addColumn(COLUMN_FAMILY, MODEL_COLUMN_QUALIFIER, Bytes.toBytes(car.getModel()));
        p.addColumn(COLUMN_FAMILY, PRODUCTION_YEAR_COLUMN_QUALIFIER, Bytes.toBytes(car.getProductionYear()));
        table.put(p);
        LOGGER.debug("Car successfully added");
    }
}
