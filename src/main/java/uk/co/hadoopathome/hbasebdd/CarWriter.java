package uk.co.hadoopathome.hbasebdd;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * Stores cars into the HBase database.
 */
public class CarWriter {
    private static final TableName TABLE_NAME = TableName.valueOf("cars");
    private static final byte[] COLUMN_FAMILY = Bytes.toBytes("c");
    private static final byte[] MODEL_COLUMN_QUALIFIER = Bytes.toBytes("model");
    private static final byte[] PRODUCTION_YEAR_COLUMN_QUALIFIER = Bytes.toBytes("productionYear");

    private final Connection connection;

    CarWriter(Connection connection) {
        this.connection = connection;
    }

    public void addNewCar(String registration, String model, int productionYear) throws IOException {
        Table table = this.connection.getTable(TABLE_NAME);
        Put p = new Put(Bytes.toBytes(registration));
        p.addColumn(COLUMN_FAMILY, MODEL_COLUMN_QUALIFIER, Bytes.toBytes(model));
        p.addColumn(COLUMN_FAMILY, PRODUCTION_YEAR_COLUMN_QUALIFIER, Bytes.toBytes(productionYear));
        table.put(p);
    }
}