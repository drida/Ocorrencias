package br.univesp.ocorrencias;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Basedados bd = new Basedados();
    EditText edUsuario;
    EditText edSenha;
    Button btAcessar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edUsuario = (EditText) findViewById(R.id.edUsuario);
        edSenha = (EditText) findViewById(R.id.edSenha);
        btAcessar = (Button) findViewById(R.id.btAcessar);

        btAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usuario = edUsuario.getText().toString();
                String senha = edSenha.getText().toString();

                bd.validarAcesso(usuario, senha);

                //Intent i = new Intent(MainActivity.this, AcessActivity.class);
                //startActivity(i);
            }
        });
    }
}