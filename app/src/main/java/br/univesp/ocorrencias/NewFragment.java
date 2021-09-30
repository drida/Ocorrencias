package br.univesp.ocorrencias;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import br.univesp.ocorrencias.basedados.Basedados;

public class NewFragment extends Fragment {

    static Basedados bd = (Basedados) MainActivity.getDb();

    static int usuarioId;
    static int empresaId;
    static Spinner spEmpresas;
    static ArrayList<String> alEmpresas;
    static int equipeId;
    static Spinner spEquipe;
    static ArrayList<String> alEquipes;
    static int statusId;
    static Spinner spStatus;
    static ArrayList<String> alStatus;
    static int tipoOcorrenciaId;
    static Spinner spTipoOcorrencia;
    static ArrayList<String> alTipoOcorrencia;
    static int sistemaId;
    static Spinner spSistema;
    static ArrayList<String> alSistema;

    static Button btGravar;

    private static final String ARG_PARAM1 = "param1";
    private String mParam1;

    public NewFragment() {
        // Required empty public constructor
    }

    public static NewFragment newInstance(String param1) {
        NewFragment fragment = new NewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new, container, false);

        makeUsuario(view);
        makeEmpresa(view);
        makeEquipe(view);
        makeStatus(view);
        makeTipoOcorrencia(view);
        makeSistema(view);

        btGravar = (Button) view.findViewById(R.id.btGravar);
        btGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("usuarioId:" + usuarioId);
                int index;
                index = spEmpresas.getSelectedItemPosition();
                empresaId = bd.getEmpresaId(alEmpresas.get(index));
                index = spEquipe.getSelectedItemPosition();
                equipeId = bd.getEquipeId(alEquipes.get(index));
                index = spStatus.getSelectedItemPosition();
                statusId = bd.getEquipeId(alStatus.get(index));
                index = spTipoOcorrencia.getSelectedItemPosition();
                tipoOcorrenciaId = bd.getTipoOcorrenciaId(alTipoOcorrencia.get(index));
                index = spSistema.getSelectedItemPosition();
                sistemaId = bd.getSistemaId(alSistema.get(index));

                bd.insertOcorrencia(usuarioId, empresaId, equipeId, statusId, tipoOcorrenciaId, sistemaId);
            }
        });

        return view;
    }

    private void makeUsuario (View view) {
        usuarioId = bd.getUsuarioId();
        TextView edUsuario = (TextView) view.findViewById(R.id.tvUsuarioNome);
        edUsuario.setText(bd.getUsuarioNome());
    }

    private void makeEmpresa (View view) {
        alEmpresas = bd.getEmpresasToArrayList();
        spEmpresas = (Spinner) view.findViewById(R.id.spEmpresa);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, alEmpresas);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spEmpresas.setAdapter(adapter);
    }

    private void makeEquipe (View view) {
        alEquipes = bd.getEquipesToArrayList();
        spEquipe = (Spinner) view.findViewById(R.id.spEquipe);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, alEquipes);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spEquipe.setAdapter(adapter);
    }

    private void makeStatus (View view) {
        alStatus = bd.getEquipesToArrayList();
        spStatus = (Spinner) view.findViewById(R.id.spStatus);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, alStatus);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spStatus.setAdapter(adapter);
    }

    private void makeTipoOcorrencia (View view) {
        alTipoOcorrencia = bd.getTipoOcorrenciasToArrayList();
        spTipoOcorrencia = (Spinner) view.findViewById(R.id.spTipoOcorrencia);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, alTipoOcorrencia);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spTipoOcorrencia.setAdapter(adapter);
    }

    private void makeSistema (View view) {
        alSistema = bd.getSistemasToArrayList();
        spSistema = (Spinner) view.findViewById(R.id.spSistema);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, alSistema);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spSistema.setAdapter(adapter);
    }
}