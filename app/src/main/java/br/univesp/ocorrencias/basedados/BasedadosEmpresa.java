package br.univesp.ocorrencias.basedados;

import java.sql.ResultSet;
import java.util.ArrayList;

public class BasedadosEmpresa extends Basedados{

    public ResultSet getEmpresas() {
        try {
            getResultSet("select * from public.empresa;" );
            return resultSet;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getEmpresaId(String empresaNome) {
        try {
            getResultSet("select * from public.empresa where nome = '"+empresaNome+"';" );
            while (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public ArrayList<String> getEmpresasToArrayList() {
        ResultSet rs = getEmpresas();
        ArrayList<String> arrayEmpresas = new ArrayList<String>();
        try {
            while (resultSet.next()) {
                arrayEmpresas.add(resultSet.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayEmpresas;
    }

}
