package br.univesp.ocorrencias.basedados;

import java.sql.SQLException;

public class BasedadosUsuario extends Basedados{

    int usuarioId;
    String usuarioNome;

    public boolean validarAcesso(String usuario, String senha) {
        try {
            getResultSet("select * from public.usuario where email = '"+usuario+"' and pass = '"+senha+"';" );
            while (resultSet.next()) {
                usuarioId = resultSet.getInt(1);
                usuarioNome = resultSet.getString(2);
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
