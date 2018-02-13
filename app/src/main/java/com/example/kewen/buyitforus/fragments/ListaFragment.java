package com.example.kewen.buyitforus.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.kewen.buyitforus.AlterarProdutoActivity;
import com.example.kewen.buyitforus.DetalhesActivity;
import com.example.kewen.buyitforus.R;
import com.example.kewen.buyitforus.adapter.ProdutosAdapter;
import com.example.kewen.buyitforus.modelo.Produto;


public class ListaFragment extends Fragment {

    private ListView listViewProdutos;
    private ProdutosAdapter produtosAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        produtosAdapter= new ProdutosAdapter(getContext());

        View view = inflater.inflate(R.layout.fragment_lista, container, false);



        listViewProdutos = (ListView) view.findViewById(R.id.lista_produtos);

        listViewProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Produto produto = (Produto) listViewProdutos.getItemAtPosition(position);
                Intent intentVaiParaDetalhes = new Intent(getContext(), DetalhesActivity.class);
                intentVaiParaDetalhes.putExtra("produto", produto);

                startActivity(intentVaiParaDetalhes);
            }
        });


        Button addProduto = (Button) view.findViewById(R.id.lista_Add_button);
        addProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentVaiParaAlterarProduto = new Intent(getContext(), AlterarProdutoActivity.class);
                startActivity(intentVaiParaAlterarProduto);
            }
        });

        registerForContextMenu(listViewProdutos);
        listViewProdutos.setAdapter(produtosAdapter);

        return view;
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
