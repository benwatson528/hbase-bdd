package uk.co.hadoopathome.hbasebdd;

import org.apache.commons.lang.ArrayUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class App {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        if (ArrayUtils.getLength(args) != 3) {
            throw new IllegalArgumentException("Invalid arguments provided. Please provide a table, row key and data");
        }

        Configuration conf = new Configuration();
        Connection connection;
        try {
            connection = ConnectionFactory.createConnection(conf);
        } catch (IOException e) {
            throw new RuntimeException("Unable to create connection to HBase, exiting");
        }
        CarWriter carWriter = new CarWriter(connection);
        try {
            carWriter.addNewCar(args[0], args[1], Integer.parseInt(args[2]));
        } catch (IOException e) {
            LOGGER.error("Unable to store car in HBase", e);
        }
    }
}
