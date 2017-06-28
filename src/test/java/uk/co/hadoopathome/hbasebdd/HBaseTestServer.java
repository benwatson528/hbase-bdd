package uk.co.hadoopathome.hbasebdd;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HBaseTestingUtility;
import org.apache.hadoop.hbase.client.Connection;
import org.junit.rules.ExternalResource;

public class HBaseTestServer extends ExternalResource {

    private static final String ZOOKEEPER_PORT = "59530";
    private static HBaseTestingUtility HBASE_UTILITY;

    @Override
    protected void before() throws Exception {
        setup();
    }

    private static void setup() throws Exception {
        Configuration conf = HBaseConfiguration.create();
        conf.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
        conf.set("fs.file.impl", org.apache.hadoop.fs.LocalFileSystem.class.getName());
        conf.set("test.hbase.zookeeper.property.clientPort", ZOOKEEPER_PORT);
        HBASE_UTILITY = new HBaseTestingUtility(conf);
        HBASE_UTILITY.startMiniCluster();
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
