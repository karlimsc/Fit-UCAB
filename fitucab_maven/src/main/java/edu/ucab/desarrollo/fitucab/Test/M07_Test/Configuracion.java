package edu.ucab.desarrollo.fitucab.Test.M07_Test;

import java.sql.*;


public class Configuracion {

    private static String BD_USER = "fitucab";
    private static String BD_PASSWORD = "fitucab";
    protected static String BD_URL = "jdbc:postgresql://localhost/FitUcabDB_test";
    protected static String BD_CLASS_FOR_NAME = "org.postgresql.Driver";
    protected static String BD_CLASS_FOR_NAME_FALSA = "org.postgresql.Driver.FALSA";

    protected static Connection bdConnect()
    {
        Connection conn = null;
        try
        {
            Class.forName(BD_CLASS_FOR_NAME);
            conn = DriverManager.getConnection(BD_URL,BD_USER, BD_PASSWORD);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return conn;
    }

    protected static void setUpPerson(Connection conn) {
        final String query1 = "INSERT INTO PERSON VALUES(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(query1);
            stm.setInt(1,1);
            stm.setString(2, "prueba");
            stm.setString(3, "password");
            stm.setString(4, "prueba@prueba.com");
            stm.setString(5, "M");
            stm.setInt(6, 123456);
            stm.setDate(7, Date.valueOf("2017-01-01"));
            stm.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected static void setUpPlanification(Connection conn) {
        final String query = "INSERT INTO PLANIFICATION VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(query);
            stm.setInt(1,1);
            stm.setDate(2, Date.valueOf("2017-01-01"));
            stm.setDate(3, Date.valueOf("2017-02-01"));
            stm.setTime(4, Time.valueOf("08:00:00"));
            stm.setTime(5, Time.valueOf("00:30:00"));
            stm.setBoolean(6, false);
            stm.setBoolean(7, false);
            stm.setBoolean(8, false);
            stm.setBoolean(9, false);
            stm.setBoolean(10, false);
            stm.setBoolean(11, false);
            stm.setBoolean(12, false);
            stm.setInt(13, 1);
            stm.setInt(14, 1);
            stm.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected static void setUpSport(Connection conn) {
        final String query = "INSERT INTO SPORT VALUES(?, ?, ?)";
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(query);
            stm.setInt(1,1);
            stm.setString(2, "rugby");
            stm.setDouble(3, 5.6);
            stm.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected static void tearDownPerson(Connection conn) {
        final String query = " DELETE FROM PERSON";
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(query);
            stm.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected static void tearDownSport(Connection conn) {
        final String query = " DELETE FROM SPORT";
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(query);
            stm.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected static void tearDownPlanification(Connection conn) {
        final String query = " DELETE FROM PLANIFICATION";
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(query);
            stm.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
