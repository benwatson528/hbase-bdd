package uk.co.hadoopathome.hbasebdd;

import org.apache.hadoop.hbase.HBaseTestingUtility;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.ipc.AsyncRpcClient;
import org.apache.hadoop.hbase.ipc.RpcClientFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import static org.junit.Assert.*;

public class HBaseWriterTest {

    @ClassRule
    public static HBaseTestServer HBASE_TEST_SERVER = new HBaseTestServer();

    @Test
    public void testCreateTable() {
        
    }

}