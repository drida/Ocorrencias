package br.univesp.ocorrencias.basedados;

import java.util.ArrayList;

public class BasedadosTipoOcorrencia extends Basedados{

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

}
