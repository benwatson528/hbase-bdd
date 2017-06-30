package uk.co.hadoopathome.hbasebdd.hbase;

import org.apache.hadoop.hbase.HBaseTestingUtility;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.junit.rules.ExternalResource;

import java.io.IOException;

public class HBaseTestServer extends ExternalResource {
    private HBaseTestingUtility hbaseUtility;

    @Override
    protected void before() throws Exception {
        this.hbaseUtility = new HBaseTestingUtility();
        this.hbaseUtility.startMiniCluster();
        this.hbaseUtility.createTable(TableName.valueOf("cars"), "c");
    }

    public Connection getConnection() {
        try {
            return this.hbaseUtility.getConnection();
        } catch (IOException e) {
            throw new RuntimeException("Unable to get HBase connection, has the test been initialised correctly?", e);
        }
    }

    @Override
    protected void after() {
        if (this.hbaseUtility != null) {
            try {
                this.hbaseUtility.shutdownMiniCluster();
            } catch (Exception e) {
                throw new RuntimeException("Unable to close HBase connection", e);
            }
        }
    }
}
