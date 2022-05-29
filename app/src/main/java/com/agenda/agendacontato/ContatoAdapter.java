package com.agenda.agendacontato;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ContatoAdapter extends BaseAdapter {

    private List<Contato> contatos;
    private MainActivity main;

    public ContatoAdapter(MainActivity main, List<Contato> contatos) {
        this.main = main;
        this.contatos = contatos;
    }

    @Override
    public int getCount() {
        return contatos.size();
    }

    @Override
    public Object getItem(int i) {
        return contatos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return contatos.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View V = main.getLayoutInflater() .inflate(R.layout.item, viewGroup, false);
        TextView nome = V.findViewById(R.id.txt_nome);
        TextView email = V.findViewById(R.id.txt_email);
        TextView telefone = V.findViewById(R.id.txt_telefone);

        Contato c = contatos.get(i);

        nome.setText(c.getNome());
        email.setText(c.getEmail());
        telefone.setText(c.getTelefone());
        return V;
    }
}
