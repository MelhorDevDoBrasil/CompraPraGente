package com.example.kewen.buyitforus;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.kewen.buyitforus.dao.ProdutoDAO;
import com.example.kewen.buyitforus.fragments.AlteraProdutoFragment;
import com.example.kewen.buyitforus.modelo.Produto;

import java.io.File;

public class AlterarProdutoActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_produto);


        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.alterar_produto_frame_layout, new AlteraProdutoFragment());
        fragmentTransaction.commit();


    }

}
