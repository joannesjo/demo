package com.example.demo.controller;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CassandraRepository extends CrudRepository<User,Integer> { @Override
List<User> findAll();
}
