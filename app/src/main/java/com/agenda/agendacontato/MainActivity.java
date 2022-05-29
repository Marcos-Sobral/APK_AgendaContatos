package com.agenda.agendacontato;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText nome;
    private EditText email;
    private EditText telefone;
    private EditText endereco;
    private EditText nascimento;
    private ContatoDAO dao;
    private Contato contato = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome = findViewById(R.id.editNome);
        email = findViewById(R.id.editEmail);
        telefone = findViewById(R.id.editFone);
        endereco = findViewById(R.id.editEndereco);
        nascimento = findViewById(R.id.editNascimento);

        dao = new ContatoDAO(this);

        Intent it = getIntent();
        if (it.hasExtra("contato")){
            contato = (Contato) it.getSerializableExtra("contato");
            nome.setText(contato.getNome());
            email.setText(contato.getEmail());
            telefone.setText(contato.getTelefone());
            endereco.setText(contato.getEndereco());
            nascimento.setText(contato.getNascimento());
        }
    }

    public void salvar(View view){
        if (contato == null) {
            contato = new Contato();
            contato.setNome(nome.getText().toString());
            contato.setEmail(email.getText().toString());
            contato.setTelefone(telefone.getText().toString());
            contato.setEndereco(endereco.getText().toString());
            contato.setNascimento(nascimento.getText().toString());
            long id = dao.inserir(contato);
            Toast.makeText(this, "Contato Salvo com id" + id, Toast.LENGTH_SHORT).show();
        }else {
            contato.setNome(nome.getText().toString());
            contato.setEmail(email.getText().toString());
            contato.setTelefone(telefone.getText().toString());
            contato.setEndereco(endereco.getText().toString());
            contato.setNascimento(nascimento.getText().toString());
            dao.atualizar(contato);
            Toast.makeText(this, "Contato foi atualziado", Toast.LENGTH_SHORT).show();
        }
    }
}