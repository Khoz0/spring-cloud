package com.example.userservice.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * @author Roberge-Mentec Corentin
 */


public class InitBD implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Création et initialisation de la BDD");

        String sqlStatements[] = {
                "drop table User if exists",
                "create table User(id serial, name varchar(255), email varchar(255), password varchar(255), city varchar(255)",
                "INSERT INTO User(name, email, password, city) VALUES('Jinx', 'jinx@arcane.fr', 'boom, 'Zaun');",
                "INSERT INTO User(name, email, password, city) VALUES('Violet', 'violet@arcane.fr', 'Gauntlet', 'Zaun');",
                "INSERT INTO User(name, email, password, city) VALUES('Caitlyn', 'caitlyn@arcane.fr', 'Sniper', 'Piltover');",
                "INSERT INTO User(name, email, password, city) VALUES('Jayce', 'jayce@arcane.fr', 'Hextech', 'Piltover');"
        };

        Arrays.asList(sqlStatements).stream().forEach(sql -> {
            System.out.println(sql);
            jdbcTemplate.execute(sql);
        });

        System.out.println("---------- Affichage de tous les utilisateurs ----------");

        jdbcTemplate.query("select * from User",
                new RowMapper<Object>() {
                    @Override
                    public Object mapRow(ResultSet user, int i) throws SQLException {
                        System.out.println("id: "+user.getString("id")+
                                "name: "+user.getString("name")+
                                "email: "+user.getString("email")+
                                "password: "+user.getString("password")+
                                "city: "+user.getString("city")
                        );
                        return null;
                    }
                });

    }
}
