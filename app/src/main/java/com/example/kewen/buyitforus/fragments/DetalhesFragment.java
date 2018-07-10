package com.example.kewen.buyitforus.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.kewen.buyitforus.AlterarProdutoActivity;
import com.example.kewen.buyitforus.DetalhesActivity;
import com.example.kewen.buyitforus.DetalhesHelper;
import com.example.kewen.buyitforus.R;
import com.example.kewen.buyitforus.dao.ProdutoDAO;
import com.example.kewen.buyitforus.modelo.Produto;

import java.util.zip.Inflater;


public class DetalhesFragment extends Fragment {

    private Produto produto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        View view = inflater.inflate(R.layout.fragment_detalhes, container, false);

        Intent intent = getActivity().getIntent();
        produto = (Produto) intent.getSerializableExtra("produto");


        Button buttonApagar = (Button) view.findViewById(R.id.detalhes_button_apagar_produto);
        buttonApagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(produto != null) {

                    Toast.makeText(getContext(), "Apagando o produto " + produto.getNome() + " da lista", Toast.LENGTH_SHORT).show();

                    ProdutoDAO produtoDAO = new ProdutoDAO(getContext());
                    produtoDAO.deletaProduto(produto);
                    produtoDAO.close();
                    getActivity().finish();
                }
            }
        });




        DetalhesHelper detalhesHelper= new DetalhesHelper(view);

        if(produto != null){
            detalhesHelper.preencheDetalhes(produto);
        }

        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.menu_detalhes, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_detalhes_editar :
                Intent vaiparaAlterarProduto = new Intent(getContext(), AlterarProdutoActivity.class);
                vaiparaAlterarProduto.putExtra("produto", produto);
                startActivity(vaiparaAlterarProduto);
                getActivity().finish();

                break;


        }
        return super.onOptionsItemSelected(item);
    }



}
