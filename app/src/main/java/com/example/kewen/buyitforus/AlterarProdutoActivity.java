package com.example.kewen.buyitforus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.kewen.buyitforus.dao.ProdutoDAO;
import com.example.kewen.buyitforus.modelo.Produto;

public class AlterarProdutoActivity extends AppCompatActivity {


    private AlterarProdutoHelper alterarProdutoHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_produto);

        alterarProdutoHelper = new AlterarProdutoHelper(this);


        Intent intent = getIntent();
        Produto produto = (Produto) intent.getSerializableExtra("produto");

        if(produto != null){
            alterarProdutoHelper.preencheFormulario(produto);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_alterar_produto, menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_alterar_produto_ok:
                Produto produto = alterarProdutoHelper.pegaProduto();
                ProdutoDAO produtoDAO = new ProdutoDAO(this);
                produtoDAO.insereProduto(produto);
                produtoDAO.close();


                Toast.makeText(AlterarProdutoActivity.this, produto.getNome() + " adicionado a lista", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.menu_alterar_produto_cancelar:
                Toast.makeText(AlterarProdutoActivity.this, "Cancelado", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
