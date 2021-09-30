package br.univesp.ocorrencias.basedados;

public class BasedadosUsuario extends Basedados{

    static int usuarioId;
    static String usuarioNome;

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
}
