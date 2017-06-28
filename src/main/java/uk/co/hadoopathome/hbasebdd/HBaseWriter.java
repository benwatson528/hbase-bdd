package uk.co.hadoopathome.hbasebdd;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * Writes data to HBase
 */
public class HBaseWriter {
    private static final byte[] COLUMN_QUALIFIER = Bytes.toBytes("f");
    private static final byte[] COLUMN_FAMILY = Bytes.toBytes("c");
    //private final Configuration config;
    private final Connection connection;

    HBaseWriter(Connection connection) {
        this.connection = connection;
        //this.config = HBaseConfiguration.create();
    }

    public void writeToHbase(String tableName, String rowKey, String data) throws IOException {
        Table table = this.connection.getTable(TableName.valueOf(tableName));
        Put p = new Put(Bytes.toBytes(rowKey));
        p.addColumn(COLUMN_FAMILY, COLUMN_QUALIFIER, Bytes.toBytes(data));
        table.put(p);
    }
}
