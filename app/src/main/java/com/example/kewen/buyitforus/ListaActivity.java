package com.example.kewen.buyitforus;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kewen.buyitforus.dao.ProdutoDAO;
import com.example.kewen.buyitforus.modelo.Produto;

import java.util.List;

public class ListaActivity extends AppCompatActivity {

    private ListView listaProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        listaProdutos = (ListView) findViewById(R.id.lista_produtos);

        listaProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Produto produto = (Produto) listaProdutos.getItemAtPosition(position);
                Intent vaiParaDetalhes = new Intent(ListaActivity.this, DetalhesActivity.class);
                vaiParaDetalhes.putExtra("produto", produto);

                startActivity(vaiParaDetalhes);





                Toast.makeText(ListaActivity.this, "produto " + produto.getNome() + " clicado", Toast.LENGTH_SHORT).show();
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

        registerForContextMenu(listaProdutos);

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

       
        listaProdutos.setAdapter(new ArrayAdapter<Produto>(this, android.R.layout.simple_list_item_checked, produtos));
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        Produto produto = (Produto) listaProdutos.getItemAtPosition(info.position);


        MenuItem itemAmazon = menu.add("Amazon.com");
        Intent intentAmazon = new Intent(Intent.ACTION_VIEW);
        intentAmazon.setData(Uri.parse("https://www.amazon.com/s?url=search-alias%3Daps&field-keywords="+ produto.getNome()));
        itemAmazon.setIntent(intentAmazon);



        MenuItem itemMercadoLivre = menu.add("MercadoLivre");
        Intent intentMercadoLivre = new Intent(Intent.ACTION_VIEW);
        intentMercadoLivre.setData(Uri.parse("https://lista.mercadolivre.com.br/"+ produto.getNome()));
        itemMercadoLivre.setIntent(intentMercadoLivre);

        MenuItem itemSupermercado = menu.add("Supermercado Mais Proximo");
        Intent intentSuperMercado = new Intent(Intent.ACTION_VIEW);
        intentSuperMercado.setData(Uri.parse("https://www.google.com.br/maps/search/supermercado/@-"));
        itemSupermercado.setIntent(intentSuperMercado);

    }
}
