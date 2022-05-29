package com.agenda.agendacontato;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
        //ContatoAdapter adaptador = new ContatoAdapter(this, contatosFiltrado);
        listView.setAdapter(adaptador);
        registerForContextMenu(listView);
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

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate((R.menu.menu_contexto), menu);
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

    public void excluir(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
    final Contato contatoExcluir = contatosFiltrado.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Realmente deseja excluir este contato ? ")
                .setNegativeButton("NÃO", null)
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        contatosFiltrado.remove(contatoExcluir);
                        contatos.remove(contatoExcluir);
                        dao.excluir(contatoExcluir);
                        listView.invalidateViews();
                    }
                }).create();
            dialog.show();

    }

    public void cadastrar(MenuItem item){
        Intent  it = new Intent(this, MainActivity.class);
        startActivity(it);
    }

    public void atualizar(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Contato contatoAtualizar = contatosFiltrado.get(menuInfo.position);
        Intent it = new Intent(this, MainActivity.class);
        it.putExtra("contato", contatoAtualizar);
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