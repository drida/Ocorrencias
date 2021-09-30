package br.univesp.ocorrencias.basedados;

import java.util.ArrayList;

public class BasedadosEmpresa extends Basedados{

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

}
