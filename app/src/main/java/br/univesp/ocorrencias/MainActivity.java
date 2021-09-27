package br.univesp.ocorrencias;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.univesp.ocorrencias.basedados.Basedados;
import br.univesp.ocorrencias.basedados.BasedadosUsuario;

public class MainActivity extends AppCompatActivity {

    BasedadosUsuario bdUsuario = new BasedadosUsuario();
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
                boolean logado = bdUsuario.validarAcesso(usuario, senha);
                if(logado) {
                    Intent i = new Intent(MainActivity.this, TabActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(MainActivity.this,"Usuário ou Senha, inválido !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}