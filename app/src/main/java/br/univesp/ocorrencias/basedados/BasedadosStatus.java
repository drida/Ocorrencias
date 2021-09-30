package br.univesp.ocorrencias.basedados;

import java.util.ArrayList;

public class BasedadosStatus extends Basedados{

    public void getStatus() {
        String query = "select * from public.status;";
        getResultSet(query);
    }

    public int getEquipeId(String nome) {
        String query = "select * from public.status where nome = '"+nome+"';";
        return getId(query);
    }

    public ArrayList<String> getEquipesToArrayList() {
        getStatus();
        return toArrayList();
    }

}
