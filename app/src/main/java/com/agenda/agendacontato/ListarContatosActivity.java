package com.agenda.agendacontato;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class ListarContatosActivity extends AppCompatActivity {

    private ListView listView;
    private ContatoDAO dao;
    private List<Contato> contatos;
    private List<Contato> contatosFiltrado = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_contatos);

        listView = findViewById(R.id.lista_contatos);
        dao = new ContatoDAO(this);
        contatos = dao.obterTodos();
        contatosFiltrado.addAll(contatos);
        ArrayAdapter<Contato> adaptador = new ArrayAdapter<Contato>(this, android.R.layout.simple_list_item_1, contatosFiltrado);
        listView.setAdapter(adaptador);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_principal, menu);

        SearchView sv = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                procuraContato(newText);
                return false;
            }
        });

        return true;
    }

    public void procuraContato(String nome){
        contatosFiltrado.clear();
        for (Contato c : contatos){
            if(c.getNome().toLowerCase().contains(nome.toLowerCase())){
                contatosFiltrado.add(c);
            }
        }
        listView.invalidateViews();
    }

    public void cadastrar(MenuItem item){
        Intent  it = new Intent(this, MainActivity.class);
        startActivity(it);
    }

    @Override
    public void onResume(){
       super.onResume();
       contatos = dao.obterTodos();
       contatosFiltrado.clear();
       contatosFiltrado.addAll(contatos);
       listView.invalidateViews();
    }
}