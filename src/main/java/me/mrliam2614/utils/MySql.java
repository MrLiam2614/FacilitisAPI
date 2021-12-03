package me.mrliam2614.utils;

import me.mrliam2614.FacilitisAPI;
import org.bukkit.plugin.Plugin;

import java.sql.*;
import java.util.ArrayList;

public class MySql {
    private FacilitisAPI plugin;
    static Connection connection;
    static ArrayList<Connection> connArray = new ArrayList<Connection>();
    static ArrayList<Plugin> connInt = new ArrayList<Plugin>();
    static Integer IdPlugin;

    public MySql(FacilitisAPI plugin) {
        this.plugin = plugin;
    }

    final String usermane = "", pasword = "", url = "", pluginRequest = "";

    private boolean isConnected(int IdPlugin) {
        return (connArray.get(IdPlugin) != null);
    }


    public Integer MySqlConnect(Plugin requestPlugin, String username, String password, String adress, String port, String databse, String table, String tableColumns, String defaultData) {
        String pluginRequest = requestPlugin.getName();
        String url = "jdbc:mysql://" + adress + ":" + port + "/" + databse + "?autoReconnect=true";
        String sql = "CREATE TABLE " + table + " " + tableColumns + ";";
        String sql2 = "SHOW TABLES LIKE '" + table + "';";
        String sql3 = "INSERT INTO " + table + defaultData + ";";
        Statement stmt;

        if (connInt.contains(requestPlugin)) {
            IdPlugin = connInt.indexOf(requestPlugin);
            if (isConnected(IdPlugin)) {
                return 3;
            }
        } else {
            connInt.add(requestPlugin);
            IdPlugin = connInt.indexOf(requestPlugin);
        }


        try {
            connection = DriverManager.getConnection(url, username, password);
            connArray.add(connection);
            stmt = connArray.get(IdPlugin).createStatement();

            ResultSet res = stmt.executeQuery(sql2);
            if (!res.next()) {
                stmt.executeUpdate(sql);
                stmt.executeUpdate(sql3);
                return 2;
            } else {
                return 1;
            }

        } catch (SQLException e) {
            MySqlError(requestPlugin, e);
            return 0;
        }
    }

    @SuppressWarnings("resource")
    public int MySqlInsert(Plugin requestPlugin, String table, String WhereColumn, String EqualsToRow, String column, String value) {
        IdPlugin = connInt.indexOf(requestPlugin);
        if (!isConnected(IdPlugin))
            return 0;

        String sql = "SELECT * FROM " + table + " WHERE `" + WhereColumn + "` = '" + EqualsToRow + "';";
        PreparedStatement stmt;

        try {
            stmt = connArray.get(IdPlugin).prepareStatement(sql);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                //UPDATE
                sql = "UPDATE " + table + " SET " + column + " = '" + value + "' WHERE `" + WhereColumn + "` = '" + EqualsToRow + "';";
                stmt = connArray.get(IdPlugin).prepareStatement(sql);
                stmt.executeUpdate();
            } else {
                //INSERT
                sql = "INSERT INTO " + table + " (`" + column + "`, `" + WhereColumn + "`) VALUES ('" + value + "', '" + EqualsToRow + "');";
                stmt = connArray.get(IdPlugin).prepareStatement(sql);
                stmt.executeUpdate();
            }
            result.close();
            stmt.close();
            return 1;
        } catch (SQLException e) {
            MySqlError(requestPlugin, e);
            return 0;
        }
    }


    public String MySqlGet(Plugin requestPlugin, String table, String WhereColumn, String EqualsToRow, String columnRequest) {
        IdPlugin = connInt.indexOf(requestPlugin);
        if (!isConnected(IdPlugin))
            return "!SERVER MYSQL IS NOT CONNECTED!";

        String results = "";
        String sql = "SELECT * FROM " + table + " WHERE `" + WhereColumn + "` = '" + EqualsToRow + "';";
        Statement stmt;


        try {
            stmt = connArray.get(IdPlugin).createStatement();
            ResultSet result = stmt.executeQuery(sql);

            while (result.next()) {
                results = result.getString(columnRequest);
            }
            result.close();
            stmt.close();
        } catch (SQLException e) {
            MySqlError(requestPlugin, e);
            return "!SERVER MYSQL IS NOT CONNECTED!";
        }
        return results;
    }


    public ResultSet MySqlGetAll(Plugin requestPlugin, String table) {
        IdPlugin = connInt.indexOf(requestPlugin);
        if (!isConnected(IdPlugin))
            return null;

        String results = "";
        String sql = "SELECT * FROM " + table;
        Statement stmt;
        ResultSet result;

        try {
            stmt = connArray.get(IdPlugin).createStatement();
            result = stmt.executeQuery(sql);
        } catch (SQLException e) {
            MySqlError(requestPlugin, e);
            return null;
        }
        return result;
    }

    public int MySqlUnconnect(Plugin requestPlugin, String pluginRequest) {
        IdPlugin = connInt.indexOf(requestPlugin);
        try {
            connArray.get(IdPlugin).close();
            return 1;
        } catch (SQLException e) {
            MySqlError(requestPlugin, e);
            return 0;
        }

    }

    private void MySqlError(Plugin requestPlugin, SQLException e) {
        String error = "Cannot connect to the database due to error:\n" + e.getMessage();
        plugin.console.sendMessage(requestPlugin, error, "info");
    }
}
