package br.univesp.ocorrencias;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.univesp.ocorrencias.basedados.BasedadosOcorrencias;
import br.univesp.ocorrencias.basedados.BasedadosUsuario;

public class PlaceholderItemOcorrencia {

    public static final List<PlaceholderItem> ITEMS = new ArrayList<PlaceholderItem>();
    public static final Map<String, PlaceholderItem> ITEM_MAP = new HashMap<String, PlaceholderItem>();
    static {
        try {
            BasedadosUsuario dbUsuario = (BasedadosUsuario) MainActivity.getDbUsuario();
            BasedadosOcorrencias ocorrencias = new BasedadosOcorrencias();
            ResultSet rs = ocorrencias.getOcorrencias(dbUsuario.getUsuarioId());
            while (rs.next()) {
                addItem(createPlaceholderItem(rs.getRow(), rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addItem(PlaceholderItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static PlaceholderItem createPlaceholderItem(int row, ResultSet resultSet) {
        String id = null;
        String status = null;
        String detalhes = null;
        try {
            id = resultSet.getString(1);
            status = resultSet.getString(4);

            StringBuilder builder = new StringBuilder();
            builder.append(resultSet.getString(2)+" / ");
            builder.append(resultSet.getString(3)+"\n");
            builder.append(resultSet.getString(5)+" / ");
            builder.append(resultSet.getString(7)+"\n");
            builder.append("Func. afetado(s): "+ resultSet.getString(8)+"\n");
            builder.append(resultSet.getString(9)+" / ");
            builder.append(resultSet.getString(10)+"\n");
            builder.append(resultSet.getString(6)+"\n");
            builder.append("Abertura: " +resultSet.getString(11)+"\n");

            detalhes = builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new PlaceholderItem(id, status, detalhes);
    }

    public static class PlaceholderItem {
        public final String id;
        public final String content;
        public final String details;

        public PlaceholderItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}