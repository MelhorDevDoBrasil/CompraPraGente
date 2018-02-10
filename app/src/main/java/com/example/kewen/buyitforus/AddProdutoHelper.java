package com.example.kewen.buyitforus;

import android.widget.EditText;

import com.example.kewen.buyitforus.modelo.Produto;

/**
 * Created by kewen on 10/02/18.
 */

public class AddProdutoHelper {

    private final EditText campoNomeProduto;
    private final EditText campoDescricaoProduto;

    public AddProdutoHelper(AddProdutoActivity activity) {
        campoNomeProduto = (EditText) activity.findViewById(R.id.add_produto_nome_produto);
        campoDescricaoProduto = (EditText) activity.findViewById(R.id.add_produto_descricao);

    }


    public Produto pegaProduto() {

        Produto produto = new Produto();
        produto.setNome(campoNomeProduto.getText().toString());
        produto.setDescricao(campoDescricaoProduto.getText().toString());


        return produto;
    }
}
