package com.example.kewen.buyitforus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.kewen.buyitforus.dao.ProdutoDAO;
import com.example.kewen.buyitforus.modelo.Produto;

import java.util.List;

public class ListaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        Button addProduto = (Button) findViewById(R.id.lista_Add_button);
        addProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentVaiParaAddProduto = new Intent(ListaActivity.this, AddProdutoActivity.class);
                startActivity(intentVaiParaAddProduto);

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        carregaLista();


    }

    private void carregaLista() {
        ProdutoDAO produtoDAO = new ProdutoDAO(this);
        List<Produto> produtos = produtoDAO.buscaProdutos();
        produtoDAO.close();

        ListView listaProdutos = (ListView) findViewById(R.id.lista_produtos);
        listaProdutos.setAdapter(new ArrayAdapter<Produto>(this, android.R.layout.simple_list_item_checked, produtos));
    }
}
