package com.agenda.agendacontato;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ContatoDAO {

    private Conexao conexao;
    private SQLiteDatabase banco;

    public ContatoDAO(Context context){
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    public long inserir(Contato contato){
        ContentValues values = new ContentValues();
        values.put("nome", contato.getNome());
        values.put("email",contato.getEmail());
        values.put("telefone", contato.getTelefone());
        return banco.insert("contato", null, values);
    }

}

