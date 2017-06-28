package uk.co.hadoopathome.hbasebdd;

import org.apache.hadoop.hbase.HBaseTestingUtility;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.ipc.AsyncRpcClient;
import org.apache.hadoop.hbase.ipc.RpcClientFactory;
import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;

public class HBaseWriterTest {
    private static final HBaseTestingUtility util = new HBaseTestingUtility();

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

}