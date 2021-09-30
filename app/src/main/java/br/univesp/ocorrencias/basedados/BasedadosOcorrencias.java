package br.univesp.ocorrencias.basedados;

import java.sql.ResultSet;

public class BasedadosOcorrencias extends Basedados{

    public ResultSet getOcorrencias(int usuarioId) {
        String query;
        try {
            query = "select o.id, emp.nome nome_empresa, eqp.nome nome_equipe, " +
                    "sts.nome nome_status, tpo.nome nome_tipo, " +
                    "o.observacoes, sis.nome nome_sistema, o.funcionariosafetados, " +
                    "o.canalsuporte, o.protocolo, o.datahoraocorrencia, " +
                    "o.datahoraconclusao, o.tempoparaconclusao " +
                    "from public.ocorrencia o " +
                    "join empresa emp on emp.id = o.idempresa " +
                    "join equipe eqp on eqp.id = o.idequipe " +
                    "join status sts on sts.id = o.idstatus " +
                    "join tipoocorrencia tpo on tpo.id = o.idtipoocorrencia " +
                    "join sistemas sis on sis.id = o.idsistemas "+
                    "where o.idusuario = " + usuarioId + ";";
            getResultSet(query);
            return resultSet;
        } catch (Exception e) {
            e.printStackTrace();;
        }
        return null;
    }

    public void insertOcorrencia(int usuarioId, int empresaId, int equipeId, int statusId, int tipoOcorrenciaId, int sistemaId) {
        String query = "insert into public.ocorrencia (idempresa, idequipe, idstatus, idusuario, idtipoocorrencia, idsistemas) values " +
                "(" + empresaId + "," + equipeId + "," + statusId + "," + usuarioId + "," + tipoOcorrenciaId + "," + sistemaId + ");";
        getResultSet(query, false);
    }
}
