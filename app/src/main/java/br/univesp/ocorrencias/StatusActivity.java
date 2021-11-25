package br.univesp.ocorrencias;

import static java.security.AccessController.getContext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.util.ArrayList;

import br.univesp.ocorrencias.basedados.Basedados;

public class StatusActivity extends AppCompatActivity {

    static Basedados bd = (Basedados) MainActivity.getDb();

    protected static int idOcorrencia;
    static int usuarioId;
    static TextView tvUsuarioNome;
    static TextView tvIdOcorrencia;
    static TextView tvEmpresa;
    static TextView tvEquipe;
    static TextView tvTipoOcorrencia;
    static TextView tvSistema;
    static TextView tvAreaImpactada;
    static TextView tvEtapaImpactada;
    static TextView tvFuncionarios;
    static TextView tvCasosImpactados;
    static TextView tvCanalSuporte;
    static TextView tvProtocolo;
    static TextView tvObservacoes;
    static String statusNome;
    static int statusId;
    static Spinner spStatus;
    static ArrayList<String> alStatus;
    static Button btGravar;

    public static void setIdOcorrencia(int id) {
        idOcorrencia = id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        tvUsuarioNome = (TextView) findViewById(R.id.tvUsuarioNome);
        tvIdOcorrencia = (TextView) findViewById(R.id.tvId);
        tvEmpresa = (TextView) findViewById(R.id.tvEmpresa);
        tvEquipe = (TextView) findViewById(R.id.tvEquipe);
        tvTipoOcorrencia = (TextView) findViewById(R.id.tvTipoOcorrencia);
        tvSistema = (TextView) findViewById(R.id.tvSistema);
        tvAreaImpactada = (TextView) findViewById(R.id.tvAreaImpactada);
        tvEtapaImpactada = (TextView) findViewById(R.id.tvEtapaImpactada);
        tvFuncionarios = (TextView) findViewById(R.id.tvFuncionarios);
        tvCasosImpactados = (TextView) findViewById(R.id.tvCasosImpactados);
        tvCanalSuporte = (TextView) findViewById(R.id.tvCanalSuporte);
        tvProtocolo = (TextView) findViewById(R.id.tvProtocolo);
        tvObservacoes = (TextView) findViewById(R.id.tvObservacoes);

        ResultSet rs = bd.getOcorrencia(idOcorrencia);
        try {
            rs.next();
            tvUsuarioNome.setText(bd.getUsuarioNome());
            tvIdOcorrencia.setText("Id da Ocorrência: " + rs.getString(1));
            tvEmpresa.setText("Empresa: : " + rs.getString(2));
            tvEquipe.setText("Equipe:  " + rs.getString(3));
            tvTipoOcorrencia.setText("Tipo Ocorr.: " + rs.getString(7));
            tvSistema.setText("Sistema: " + rs.getString(9));
            tvAreaImpactada.setText("Área Impactada: " + rs.getString(5));
            tvEtapaImpactada.setText("Etapa Impactada: " + rs.getString(6));
            tvFuncionarios.setText("Funcionários Impactados: " + rs.getString(10));
            tvCasosImpactados.setText("Casos Impactados: " + rs.getString(11));
            tvCanalSuporte.setText("Canal Suporte: " + rs.getString(12));
            tvProtocolo.setText("Protocolo: " + rs.getString(13));
            tvObservacoes.setText("Observações: " + rs.getString(8));
            statusNome = rs.getString(4);
            System.out.println("ResultSet: " + statusNome);
        } catch (Exception e) {
            e.printStackTrace();
        }

        btGravar = (Button) findViewById(R.id.btGravar);
        spStatus = (Spinner) findViewById(R.id.spStatus);


        if (!statusNome.matches("PENDENTE")) {
            btGravar.setText("SAIR");
            spStatus.setEnabled(false);
            makeStatus(statusNome);
        } else {
            makeStatus();
        }

        btGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index;
                index = spStatus.getSelectedItemPosition();
                statusId = bd.getStatusId(alStatus.get(index));
                if (statusId > 1) {
                    bd.updateStatus(idOcorrencia, statusId);
                    ItemFragment.update();
                    Toast.makeText(StatusActivity.this, "Status alterado com sucesso.", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    finish();
                }
            }
        });
    }

    private void makeStatus (String name) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapter.add(name);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spStatus.setAdapter(adapter);
    }

    private void makeStatus () {
        alStatus = bd.getStatusToArrayList();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, alStatus);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spStatus.setAdapter(adapter);
    }

}