package br.univesp.ocorrencias.basedados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Basedados {
    private Connection connection;
    public ResultSet resultSet;

    private String url = "jdbc:postgresql://%s:%d/%s";
    private final String host = "ec2-18-235-45-217.compute-1.amazonaws.com";
    private final int port = 5432;
    private final String database = "de34fno26f5kdm";
    private final String user = "qagcogeanjbjht";
    private final String pass = "1325489a683f1ea76102bee3a6cdf5663ae1788094dd3da4b111a216a4c6741e";
    private boolean status;

    public Basedados() {
        this.url = String.format(this.url, this.host, this.port, this.database);
        connect();
    }

    private void connect() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("org.postgresql.Driver");
                    connection = DriverManager.getConnection(url, user, pass);
                    status = true;
                    System.out.println("connected:" + status);
                } catch (Exception e) {
                    status = false;
                    System.out.print(e.getMessage());
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
            this.status = false;
        }
    }

    public Connection getExtraConnection(){
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return c;
    }

    void getResultSet(String query) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Statement statement = null;
                try {
                    resultSet = null;
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery(query);
                } catch (Exception e) {
                    System.out.print(e.getMessage());
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
            this.status = false;
        }
    }

    public boolean validarAcesso(String usuario, String senha) {
        return false;
    }
}
