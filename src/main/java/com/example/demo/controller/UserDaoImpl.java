package com.example.demo.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ProtocolVersion;
import com.yammer.metrics.reporting.JmxReporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Repository
public class UserDaoImpl {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ResourceLoader resourceLoader;

    public JasperPrint exportPdfFile() throws SQLException, JRException, IOException,ClassNotFoundException {
        //Connection conn = jdbcTemplate.getDataSource().getConnection();
        //BasicDataSource dataSource = new BasicDataSource();

       /* Cluster cluster = Cluster.builder()
                .withoutJMXReporting()
                .build();*/
        //Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
       // Class.forName("com.github.adejanovski.cassandra.jdbc.CassandraDriver");

        Connection conn = DriverManager.getConnection("jdbc:cassandra://localhost:9042/test");
        String path = resourceLoader.getResource("classpath:rpt_users.jrxml").getURI().getPath();

        JasperReport jasperReport = JasperCompileManager.compileReport(path);

        // Parameters for report
        Map<String, Object> parameters = new HashMap<String, Object>();

        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conn);

        return print;
    }
}
