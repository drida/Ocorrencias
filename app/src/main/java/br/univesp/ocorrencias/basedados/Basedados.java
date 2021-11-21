package br.univesp.ocorrencias.basedados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Basedados {
    private Connection connection;
    public ResultSet resultSet;

    static int usuarioId;
    static String usuarioNome;

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

    void getResultSet (String query) {
        getResultSet(query, true);
    }

    void getResultSet (String query, boolean select) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Statement statement = null;
                try {
                    System.out.println("query:" + query);
                    resultSet = null;
                    int rs = 0;
                    statement = connection.createStatement();
                    if (select) {
                        resultSet = statement.executeQuery(query);
                    } else {
                        rs = statement.executeUpdate(query);
                    }
                    System.out.println("ResultSet: " + resultSet.toString() + rs);
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

    public int getId(String query) {
        try {
            getResultSet(query);
            while (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public ArrayList<String> toArrayList() {
        ArrayList<String> arrayList = new ArrayList<String>();
        try {
            while (resultSet.next()) {
                arrayList.add(resultSet.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public boolean validarAcesso(String usuario, String senha) {
        try {
            getResultSet("select * from public.usuario where email = '"+usuario+"' and pass = '"+senha+"';" );
            while (resultSet.next()) {
                usuarioId = resultSet.getInt(1);
                usuarioNome = resultSet.getString(2);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int getUsuarioId() {
        return usuarioId;
    }

    public static String getUsuarioNome() {
        return usuarioNome;
    }

    public void getEmpresas() {
        String query = "select * from public.empresa;";
        getResultSet(query);
    }

    public int getEmpresaId(String nome) {
        String query = "select * from public.empresa where nome = '"+nome+"';";
        return getId(query);
    }

    public ArrayList<String> getEmpresasToArrayList() {
        getEmpresas();
        return toArrayList();
    }

    public void getEquipes() {
        String query = "select * from public.equipe;";
        getResultSet(query);
    }

    public int getEquipeId(String nome) {
        String query = "select * from public.equipe where nome = '"+nome+"';";
        return getId(query);
    }

    public ArrayList<String> getEquipesToArrayList() {
        getEquipes();
        return toArrayList();
    }

    public void getSistemas() {
        String query = "select * from public.sistemas;";
        getResultSet(query);
    }

    public int getSistemaId(String nome) {
        String query = "select * from public.sistemas where nome = '"+nome+"';";
        return getId(query);
    }

    public ArrayList<String> getSistemasToArrayList() {
        getSistemas();
        return toArrayList();
    }

    public void getStatus() {
        String query = "select * from public.status;";
        getResultSet(query);
    }

    public int getStatusId(String nome) {
        String query = "select * from public.status where nome = '"+nome+"';";
        return getId(query);
    }

    public ArrayList<String> getStatusToArrayList() {
        getStatus();
        return toArrayList();
    }

    public void getTipoOcorrencias() {
        String query = "select * from public.tipoocorrencia;";
        getResultSet(query);
    }

    public int getTipoOcorrenciaId(String nome) {
        String query = "select * from public.tipoocorrencia where nome = '"+nome+"';";
        return getId(query);
    }

    public ArrayList<String> getTipoOcorrenciasToArrayList() {
        getTipoOcorrencias();
        return toArrayList();
    }

    public void getAreaImpactada() {
        String query = "select * from public.areaimpactada;";
        getResultSet(query);
    }

    public int getAreaImpactadaId(String nome) {
        String query = "select * from public.areaimpactada where nome = '"+nome+"';";
        return getId(query);
    }

    public ArrayList<String> getAreaImpactadaToArrayList() {
        getAreaImpactada();
        return toArrayList();
    }

    public void getEtapaImpactada() {
        String query = "select * from public.etapaimpactada;";
        getResultSet(query);
    }

    public int getEtapaImpactadaId(String nome) {
        String query = "select * from public.etapaimpactada where nome = '"+nome+"';";
        return getId(query);
    }

    public ArrayList<String> getEtapaImpactadaToArrayList() {
        getEtapaImpactada();
        return toArrayList();
    }

    public ResultSet getOcorrencias() {
        String query;
        try {
            query = "select * from public.ocorrencias where idusuario = " + usuarioId + ";";
            getResultSet(query);
            return resultSet;
        } catch (Exception e) {
            e.printStackTrace();;
        }
        return null;
    }

    public void insertOcorrencia(int usuarioId, int empresaId, int equipeId, int statusId,
                                 int tipoOcorrenciaId, int sistemaId, int areaImpactadaId,
                                 int etapaImpactadaId, String funcionarios,
                                 String casos, String canalSuporte, String protocolo,
                                 String observacoes) {
        String query = "insert into public.ocorrencia (idempresa, idequipe, idstatus, idareaimpactada, idetapaimpactada, idusuario, idtipoocorrencia," +
                "observacoes, idsistemas, funcionariosafetados, casosimpactados, canalsuporte, protocolo, datahoraocorrencia) values " +
                "(" + empresaId + "," + equipeId + "," + statusId + "," + areaImpactadaId + "," + etapaImpactadaId + "," + usuarioId + "," + tipoOcorrenciaId + ",'" +
                observacoes + "'," + sistemaId + "," + funcionarios + "," + casos + ",'" + canalSuporte + "','" +
                protocolo + "',now());";
        getResultSet(query, false);
    }
}
