package com.agenda.agendacontato;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText nome;
    private EditText email;
    private EditText telefone;
    private ContatoDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome = findViewById(R.id.editNome);
        email = findViewById(R.id.editEmail);
        telefone = findViewById(R.id.editFone);
        dao = new ContatoDAO(this);
    }

    public void salvar(View view){
        Contato c = new Contato();

        c.setNome(nome.getText().toString());
        c.setEmail(email.getText().toString());
        c.setTelefone(telefone.getText().toString());
        long id = dao.inserir(c);
        Toast.makeText(this, "Contato Salvo com id" + id, Toast.LENGTH_SHORT).show();
    }
}