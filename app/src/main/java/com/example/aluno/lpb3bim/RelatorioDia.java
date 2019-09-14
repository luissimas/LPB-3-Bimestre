package com.example.aluno.lpb3bim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.aluno.lpb3bim.Controller.ConsumoCRUD;
import com.example.aluno.lpb3bim.Controller.ProdutoCRUD;
import com.example.aluno.lpb3bim.Model.Consumo;
import com.example.aluno.lpb3bim.Model.Lista;

import java.util.ArrayList;

public class RelatorioDia extends AppCompatActivity {

    private EditText txtTotal;

    ArrayAdapter<String> adapterLst;
    ArrayList<String> lstLista;
    private ListView lstConsumos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio_dia);

        txtTotal=(EditText) findViewById(R.id.txtTotal);
        lstConsumos=(ListView) findViewById(R.id.lstConsumo);

        listar();
    }

    public void listar(){
        ProdutoCRUD produtoCRUD = new ProdutoCRUD();

        Double total=0.0;

        try{
            if(Lista.lstConsumo != null){
                if(lstLista != null){
                    lstLista.clear();
                }else{
                    lstLista = new ArrayList<>();
                }

                for(int i=0; i < Lista.lstConsumo.toArray().length; i++){
                    total += (Lista.lstConsumo.get(i).getQtde() * produtoCRUD.preencher(getBaseContext(), Lista.lstConsumo.get(i).getCodproduto()).getCaloria());

                    lstLista.add(produtoCRUD.preencher(getBaseContext(), Lista.lstConsumo.get(i).getCodproduto()).getDescr() + " || " + (produtoCRUD.preencher(getBaseContext(), Lista.lstConsumo.get(i).getCodproduto()).getCaloria() * Lista.lstConsumo.get(i).getQtde()));
                }
                adapterLst = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lstLista);
                lstConsumos.setAdapter(adapterLst);

                txtTotal.setText(total.toString());
            }
        }catch(Exception ex){
            Toast.makeText(getBaseContext(),"Erro: "+ex.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    public void fechar(View v){
        super.finish();
    }
}
