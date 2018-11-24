package main.java.source_code.strategy;

import java.sql.*;

public class StrategyJDBC {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost/Shortener?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    static final String USER_NAME = "root";
    static final String USER_PASSWORD = "rootpasswordgiven";

    public static String dataFromDB(String data) {
        System.out.println("Registering StrategyJDBC driver and connection...");
        try {
            Class.forName(JDBC_DRIVER);
            //DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());
        } catch(Exception e) {
            e.printStackTrace();
        }
        System.out.println("Registering Statement...");
        ResultSet resultSet = null;
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER_NAME, USER_PASSWORD);
             Statement statement = connection.createStatement();
        ) {
            if (isID(data)) {
                String sqlQueryID = String.format("SELECT Strings FROM ShortenerStrings WHERE id = %s", data);
                resultSet = statement.executeQuery(sqlQueryID);
                resultSet.first();
                return resultSet.getString("Strings");
            } else {
                String sqlQuerySTRING = String.format("SELECT id FROM ShortenerStrings WHERE Strings = '%s'", data);
                resultSet = statement.executeQuery(sqlQuerySTRING);
                resultSet.first();
                return resultSet.getInt("id") + "";
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static boolean isID(String data) {
        for(int i = 0; i < data.length(); ++i) {
            if (!Character.isDigit(data.charAt(i)))
                return false;
        }
        return true;
    }
}
