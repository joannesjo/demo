package com.example.demo.controller;

import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
public class UserRestController {


    @Autowired
    private CassandraRepository cassandraRepository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List all() throws IOException, JRException, SQLException,ClassNotFoundException {
        return cassandraRepository.findAll();
    }
}
