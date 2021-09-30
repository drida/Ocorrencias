package br.univesp.ocorrencias.basedados;

import java.util.ArrayList;

public class BasedadosEquipe extends Basedados{

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

}
