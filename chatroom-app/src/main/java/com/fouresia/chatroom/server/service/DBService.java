package com.fouresia.chatroom.server.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBService {

    private String host = "localhost";

    private String port = "3306";

    private String dbtype = "mysql";

    private String driverclass = "com.mysql.cj.jdbc.Driver";

    private String drivertype = "jdbc";

    private String dbname = "chatapp";

    private String username = "root";

    private String password = "pED18ggA!";

    private Connection connection;

    private static DBService instance;

    private DBService() {
    }

    public DBService(String host, String port, String dbtype, String driverclass, String drivertype, String dbname,
            String username, String password) {
        this.host = host;
        this.port = port;
        this.dbtype = dbtype;
        this.driverclass = driverclass;
        this.drivertype = drivertype;
        this.dbname = dbname;
        this.username = username;
        this.password = password;
    }

    public static DBService getInstance() {
        if (instance == null) {
            instance = new DBService();
        }
        return instance;
    }

    public void connectToDB() {
        System.out.println("Connecting to DB...");
        try {
            Class.forName(driverclass);
            String dbURL = drivertype + ":" + dbtype + "://" + host + ":" + port + "/" + dbname;
            connection = DriverManager.getConnection(dbURL, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeDBConnection() {
        try {
            System.out.println("Closing DB connection...");
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // INSERT INTO tablename (column1, column2, column3) VALUES (value1, value2 ,
    // value3)
    public boolean insert(String tablename, DBValue[] values) throws SQLException {
        String valuesPart = "(";
        for (int i = 0; i < values.length; i++) {
            DBValueType type = values[i].getType();
            switch (type) {
                case STR:
                    valuesPart += "'" + values[i].getValue() + "'";
                    break;
                case INT:
                case FLOAT:
                case LONG:
                case BOOL:
                default:
                    valuesPart += values[i].getValue();
            }
            if (i < values.length - 1) {
                valuesPart += ",";
            }
        }
        valuesPart += ")";
        String command = "INSERT INTO" + tablename + " VALUES" + valuesPart + "";

        Statement statement = connection.createStatement();
        int result = statement.executeUpdate(command);
        if (result > 0) {
            System.out.println("Insertion successful");
        }
        if (result == 0) {
            System.out.println("Insertion failed");
        }
        return result > 0;
    }

    // SELECT * FROM tablename WHERE columnname = value

    public ResultSet select(String tableame, String whereclause) {
        try {
            Statement statement = connection.createStatement();
            String command = "SELECT * FROM " + tableame + " WHERE " + whereclause;
            ResultSet resultSet = statement.executeQuery(command);
            return resultSet;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
