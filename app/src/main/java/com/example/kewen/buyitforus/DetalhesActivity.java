package com.example.kewen.buyitforus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.kewen.buyitforus.modelo.Produto;

public class DetalhesActivity extends AppCompatActivity {


    private Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        DetalhesHelper detalhesHelper= new DetalhesHelper(this);

        Intent intent = getIntent();
        produto = (Produto) intent.getSerializableExtra("produto");

        if(produto != null){
            detalhesHelper.preencheDetalhes(produto);
        }

        Button buttonApagar = (Button) findViewById(R.id.detalhes_button_apagar_produto);
        buttonApagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(DetalhesActivity.this, "Apagando o produto " + produto.getNome() + " da lista", Toast.LENGTH_SHORT).show();
                
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_detalhes, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_detalhes_editar :
                Intent vaiparaAlterarProduto = new Intent(DetalhesActivity.this, AlterarProdutoActivity.class);
                vaiparaAlterarProduto.putExtra("produto", produto);
                startActivity(vaiparaAlterarProduto);
                finish();

                break;


        }
        return super.onOptionsItemSelected(item);
    }
}
