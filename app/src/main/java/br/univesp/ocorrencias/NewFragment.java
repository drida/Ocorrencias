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

import br.univesp.ocorrencias.basedados.BasedadosEmpresa;
import br.univesp.ocorrencias.basedados.BasedadosOcorrencias;
import br.univesp.ocorrencias.basedados.BasedadosUsuario;

public class NewFragment extends Fragment {

    static BasedadosOcorrencias bdOcorrencias = new BasedadosOcorrencias();
    static BasedadosUsuario bdUsuario = new BasedadosUsuario();
    static BasedadosEmpresa bdEmpresa = new BasedadosEmpresa();

    static int usuarioId;
    static int empresaId;
    static Spinner spEmpresas;
    static ArrayList<String> alEmpresas;

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

        btGravar = (Button) view.findViewById(R.id.btGravar);
        btGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = spEmpresas.getSelectedItemPosition();
                empresaId = bdEmpresa.getEmpresaId(alEmpresas.get(index));
                bdOcorrencias.insertOcorrencia(usuarioId, empresaId);
            }
        });

        return view;
    }

    private void makeUsuario (View view) {
        bdUsuario = (BasedadosUsuario) MainActivity.getDbUsuario();
        usuarioId = bdUsuario.getUsuarioId();
        TextView edUsuario = (TextView) view.findViewById(R.id.tvUsuarioNome);
        edUsuario.setText(bdUsuario.getUsuarioNome());
    }

    private void makeEmpresa (View view) {
        alEmpresas = bdEmpresa.getEmpresasToArrayList();
        spEmpresas = (Spinner) view.findViewById(R.id.spEmpresa);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, alEmpresas);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spEmpresas.setAdapter(adapter);
    }
}