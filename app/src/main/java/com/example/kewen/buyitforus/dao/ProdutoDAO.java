package com.example.kewen.buyitforus.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.kewen.buyitforus.modelo.Produto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kewen on 10/02/18.
 */

public class ProdutoDAO extends SQLiteOpenHelper {
    public ProdutoDAO(Context context) {
        super(context, "ListaDeCompras", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Produtos (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, descricao TEXT); ";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS Produtos;";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insereProduto(Produto produto) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();
        dados.put("nome", produto.getNome());
        dados.put("descricao", produto.getDescricao());

        db.insert("Produtos", null, dados);
    }

    public List<Produto> buscaProdutos() {
        String sql = "SELECT * FROM Produtos";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        List<Produto> produtos = new ArrayList<Produto>();
        while (cursor.moveToNext()){
            Produto produto = new Produto();
            produto.setId(cursor.getLong(cursor.getColumnIndex("id")));
            produto.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            produto.setDescricao((cursor.getString(cursor.getColumnIndex("descricao"))));

            produtos.add(produto);
        }
        cursor.close();

        return produtos;

    }
}
