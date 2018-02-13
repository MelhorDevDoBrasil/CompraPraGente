package com.example.kewen.buyitforus;

import android.content.Intent;
import android.database.DataSetObserver;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kewen.buyitforus.adapter.ProdutosAdapter;
import com.example.kewen.buyitforus.dao.ProdutoDAO;
import com.example.kewen.buyitforus.modelo.Produto;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListaActivity extends AppCompatActivity {

    private ListView listViewProdutos;
    private ProdutosAdapter produtosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        listViewProdutos = (ListView) findViewById(R.id.lista_produtos);

        listViewProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Produto produto = (Produto) listViewProdutos.getItemAtPosition(position);
                Intent intentVaiParaDetalhes = new Intent(ListaActivity.this, DetalhesActivity.class);
                intentVaiParaDetalhes.putExtra("produto", produto);

                startActivity(intentVaiParaDetalhes);
            }
        });


        Button addProduto = (Button) findViewById(R.id.lista_Add_button);
        addProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentVaiParaAlterarProduto = new Intent(ListaActivity.this, AlterarProdutoActivity.class);
                startActivity(intentVaiParaAlterarProduto);
            }
        });

        registerForContextMenu(listViewProdutos);


    }

    @Override
    protected void onResume() {
        super.onResume();

        produtosAdapter= new ProdutosAdapter(this);
        listViewProdutos.setAdapter(produtosAdapter);


    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        Produto produto = (Produto) listViewProdutos.getItemAtPosition(info.position);


        MenuItem itemAmazon = menu.add("Amazon.com");
        Intent intentAmazon = new Intent(Intent.ACTION_VIEW);
        intentAmazon.setData(Uri.parse("https://www.amazon.com/s?url=search-alias%3Daps&field-keywords="+ produto.getNome()));
        itemAmazon.setIntent(intentAmazon);



        MenuItem itemMercadoLivre = menu.add("MercadoLivre");
        Intent intentMercadoLivre = new Intent(Intent.ACTION_VIEW);
        intentMercadoLivre.setData(Uri.parse("https://lista.mercadolivre.com.br/"+ produto.getNome()));
        itemMercadoLivre.setIntent(intentMercadoLivre);

        MenuItem itemSupermercado = menu.add("#PagaNois Mais Proximo");
        Intent intentSuperMercado = new Intent(Intent.ACTION_VIEW);
        intentSuperMercado.setData(Uri.parse("https://www.google.com.br/maps/search/supermercado/@-"));
        itemSupermercado.setIntent(intentSuperMercado);

    }
}
