package br.univesp.ocorrencias.basedados;

import java.util.ArrayList;

public class BasedadosSistema extends Basedados{

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

}
