package com.example.kewen.buyitforus;

import android.view.View;
import android.widget.EditText;

import com.example.kewen.buyitforus.modelo.Produto;

/**
 * Created by kewen on 10/02/18.
 */

public class AlterarProdutoHelper {

    private final EditText campoNomeProduto;
    private final EditText campoDescricaoProduto;
    private Produto produto;

    public AlterarProdutoHelper(View view) {
        campoNomeProduto = (EditText) view.findViewById(R.id.alterar_produto_nome_produto);
        campoDescricaoProduto = (EditText) view.findViewById(R.id.alterar_produto_descricao);
        produto = new Produto();

    }


    public Produto pegaProduto() {


        produto.setNome(campoNomeProduto.getText().toString());
        produto.setDescricao(campoDescricaoProduto.getText().toString());


        return produto;
    }

    public void preencheFormulario(Produto produto) {
        campoNomeProduto.setText(produto.getNome());
        campoDescricaoProduto.setText(produto.getDescricao());
        this.produto = produto;


    }
}
