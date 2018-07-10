package com.example.kewen.buyitforus.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
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
import com.example.kewen.buyitforus.AlterarProdutoHelper;
import com.example.kewen.buyitforus.R;
import com.example.kewen.buyitforus.dao.ProdutoDAO;
import com.example.kewen.buyitforus.modelo.Produto;

import java.util.zip.Inflater;


public class AlteraProdutoFragment extends Fragment {
    private AlterarProdutoHelper alterarProdutoHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        View view = inflater.inflate(R.layout.fragment_altera_produto, container, false);
        alterarProdutoHelper = new AlterarProdutoHelper(view);


        final Intent intent = getActivity().getIntent();
        Produto produto = (Produto) intent.getSerializableExtra("produto");

        if(produto != null){
            alterarProdutoHelper.preencheFormulario(produto);
        }

        Button botaoPhoto = (Button) view.findViewById(R.id.alterar_produto_button_photo);
        botaoPhoto.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View view) {

                                              final int REQUEST_IMAGE_CAPTURE = 1;
                                              Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                              if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                                                  startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                                              }
                                          }

                                      }
        );





        return  view;
    }



    @Override
    public  void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        MenuInflater inflater = menuInflater;
        inflater.inflate(R.menu.menu_alterar_produto, menu);

        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menu_alterar_produto_ok:
                Produto produto = alterarProdutoHelper.pegaProduto();
                ProdutoDAO produtoDAO = new ProdutoDAO(getContext());
                if (produto.getId() != null){
                    produtoDAO.alteraProduto(produto);

                } else {

                    produtoDAO.insereProduto(produto);

                }
                produtoDAO.close();
                Toast.makeText(getContext(), produto.getNome() + " adicionado a lista", Toast.LENGTH_SHORT).show();
                getActivity().finish();
                break;
            case R.id.menu_alterar_produto_cancelar:
                Toast.makeText(getContext(), "Cancelado", Toast.LENGTH_SHORT).show();
                getActivity().finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
