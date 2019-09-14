package com.example.aluno.lpb3bim;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.aluno.lpb3bim.Controller.ConsumoCRUD;
import com.example.aluno.lpb3bim.Model.Consumo;
import com.example.aluno.lpb3bim.Model.Lista;

import java.util.ArrayList;

public class Relatorio extends AppCompatActivity {

    private Spinner cmbMes;
    private Spinner cmbAno;

    ArrayList<String> listaLst;
    ArrayList<Integer> lstMes = new ArrayList<>();
    ArrayList<Integer> lstAno = new ArrayList<>();
    ArrayAdapter<Integer> adapterMes;
    ArrayAdapter<Integer> adapterAno;
    ArrayAdapter<String> adapterLst;
    private ListView lstConsumos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);

        cmbMes=(Spinner) findViewById(R.id.cmbMes);
        cmbAno=(Spinner) findViewById(R.id.cmbAno);
        lstConsumos=(ListView) findViewById(R.id.lstConsumo);

        preencherCmb();

        cmbAno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                listar();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        cmbMes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                listar();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        lstConsumos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Consumo consumo;
                ConsumoCRUD consumoCRUD = new ConsumoCRUD();

                int dia=0;

                Cursor tabela;

                try{
                    tabela = consumoCRUD.listarMesAno(getBaseContext(), Integer.parseInt(cmbMes.getSelectedItem().toString()), Integer.parseInt(cmbAno.getSelectedItem().toString()));

                    if(tabela != null){
                        tabela.moveToFirst();
                        dia = tabela.getInt(0);
                    }

                    tabela = consumoCRUD.listarDiaMesAno(getBaseContext(), dia, Integer.parseInt(cmbMes.getSelectedItem().toString()), Integer.parseInt(cmbAno.getSelectedItem().toString()));

                    if(tabela != null){
                        if(Lista.lstConsumo!=null){
                            Lista.lstConsumo.clear();
                        }else{
                            Lista.lstConsumo=new ArrayList<>();
                        }

                        while(tabela.moveToNext()){
                            consumo = consumoCRUD.preencher(getBaseContext(), tabela.getInt(0));
                            Lista.lstConsumo.add(consumo);
                        }

                        abrirRelatorio(view);
                    }
                }catch(Exception ex){
                    Toast.makeText(getBaseContext(), "Erro: " + ex.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void abrirRelatorio(View v){
        Intent intent = new Intent(Relatorio.this, RelatorioDia.class);
        startActivity(intent);
    }

    public void preencherCmb(){
        try{
            for(int i=2000; i<=2019;i++){
                lstAno.add(i);
            }

            for(int i=1;i<=12;i++){
                lstMes.add(i);
            }

            adapterAno = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, lstAno);
            adapterMes = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, lstMes);

            cmbAno.setAdapter(adapterAno);
            cmbMes.setAdapter(adapterMes);
        }catch(Exception ex){
            Toast.makeText(getBaseContext(),"Erro: "+ex.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    public void listar(){
        ConsumoCRUD consumoCRUD = new ConsumoCRUD();
        Cursor tabela;

        try{
            tabela = consumoCRUD.listarMesAno(getBaseContext(), Integer.parseInt(cmbMes.getSelectedItem().toString()), Integer.parseInt(cmbAno.getSelectedItem().toString()));

            if(tabela != null){
                if(listaLst!=null){
                    listaLst.clear();
                }else{
                    listaLst=new ArrayList<>();
                }

                while(tabela.moveToNext()){
                    listaLst.add(tabela.getInt(0) + " || " + tabela.getDouble(3));
                }

                adapterLst = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaLst);

                lstConsumos.setAdapter(adapterLst);
            }

        }catch(Exception ex){
            Toast.makeText(getBaseContext(),"Erro: "+ex.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    public void fechar(View v){
        super.finish();
    }
}
