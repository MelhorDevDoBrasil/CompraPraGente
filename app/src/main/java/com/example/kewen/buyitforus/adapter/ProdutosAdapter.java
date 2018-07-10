package com.example.kewen.buyitforus.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kewen.buyitforus.ListaActivity;
import com.example.kewen.buyitforus.R;
import com.example.kewen.buyitforus.dao.ProdutoDAO;
import com.example.kewen.buyitforus.modelo.Produto;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by kewen on 11/02/18.
 */

public class ProdutosAdapter extends BaseAdapter {

    private final Context context;

    private List<Produto> produtos;

    private ProdutoDAO produtoDAO;


    public ProdutosAdapter(Context context) {
        this.context = context;
        this.produtoDAO = new ProdutoDAO(context);
        this.produtos = reordenaLista(produtoDAO.buscaProdutos());
    }



    @Override
    public int getCount() {


        return produtos.size();
    }

    @Override
    public Object getItem(int i) {
        return produtos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return produtos.get(i).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {

        final Produto produto = produtos.get(position);
        LayoutInflater listItemInflater = LayoutInflater.from(context);

        View view = convertView;
        if(view == null){
            view = listItemInflater.inflate(R.layout.list_item, viewGroup,false);

        }

        TextView textNome = (TextView) view.findViewById(R.id.item_nome);
        textNome.setText(produto.getNome());

        TextView textDescicao = (TextView) view.findViewById(R.id.item_Descricao);
        if (textDescicao != null){
            textDescicao.setText(produto.getDescricao());
        }

        CheckBox checkButton = (CheckBox) view.findViewById(R.id.item_checkbox_button);


        if (produto.isComprado()){
            textNome.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            checkButton.setChecked(true);
        }
        else {
            textNome.setPaintFlags(0);
        }

        checkButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                produto.setComprado(checked);
                produtoDAO = new ProdutoDAO(context);
                produtoDAO.alteraProduto(produto);
                produtoDAO.close();
                notifyDataSetChanged();
                Toast.makeText(context, "o produto " + produto.getNome() + " agora esta como comprado =" + produto.isComprado(), Toast.LENGTH_SHORT).show();

            }
        });
        produtoDAO.close();
        return view;
    }


    public List<Produto> reordenaLista(List<Produto> produtos){

        Collections.sort( produtos, new Comparator<Produto>(){
            public int compare(Produto obj1, Produto obj2) {
                // ## Ascending order
                return Boolean.compare(obj1.isComprado(), obj2.isComprado());
            }
        });
        return produtos;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
