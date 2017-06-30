package uk.co.hadoopathome.hbasebdd.hbase;

import org.apache.hadoop.hbase.HBaseTestingUtility;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.junit.rules.ExternalResource;

import java.io.IOException;

public class HBaseTestServer extends ExternalResource {
    private static HBaseTestingUtility HBASE_UTILITY;

    @Override
    protected void before() throws Exception {
        HBASE_UTILITY = new HBaseTestingUtility();
        HBASE_UTILITY.startMiniCluster();
        HBASE_UTILITY.createTable(TableName.valueOf("cars"), "c");
    }

    public Connection getConnection() {
        try {
            return HBASE_UTILITY.getConnection();
        } catch (IOException e) {
            throw new RuntimeException("Unable to get HBase connection, has the test been initialised correctly?", e);
        }
    }

    @Override
    protected void after() {
        if (HBASE_UTILITY != null) {
            try {
                HBASE_UTILITY.shutdownMiniCluster();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
