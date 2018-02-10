package com.example.kewen.buyitforus;

import android.widget.EditText;

import com.example.kewen.buyitforus.modelo.Produto;

/**
 * Created by kewen on 10/02/18.
 */

public class AddProdutoHelper {

    private final EditText campoNomeProduto;

    public AddProdutoHelper(AddProdutoActivity activity) {
        campoNomeProduto = (EditText) activity.findViewById(R.id.add_produto_nome_produto);

    }


    public Produto pegaProduto() {

        Produto produto = new Produto();
        produto.setNome(campoNomeProduto.getText().toString());

        return produto;
    }
}
