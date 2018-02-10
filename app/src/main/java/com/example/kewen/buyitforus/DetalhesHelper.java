package com.example.kewen.buyitforus;

import android.widget.EditText;
import android.widget.TextView;

import com.example.kewen.buyitforus.modelo.Produto;

/**
 * Created by kewen on 10/02/18.
 */

class DetalhesHelper {

    private final TextView textNomeProduto;
    private final TextView textDescricaoProduto;



    public DetalhesHelper(DetalhesActivity activity) {
        textNomeProduto = (TextView) activity.findViewById(R.id.detalhes_nome_produto);
        textDescricaoProduto = (TextView) activity.findViewById(R.id.detalhes_descricao_produto);
    }

    public void preencheDetalhes(Produto produto) {
        textNomeProduto.setText(produto.getNome());
        textDescricaoProduto.setText(produto.getDescricao());

    }
}
