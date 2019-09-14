package com.example.aluno.lpb3bim;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.aluno.lpb3bim.Controller.ConsumoCRUD;
import com.example.aluno.lpb3bim.Controller.ProdutoCRUD;
import com.example.aluno.lpb3bim.Model.Consumo;
import com.example.aluno.lpb3bim.Model.Lista;
import com.example.aluno.lpb3bim.Model.Produto;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CadastrarConsumo extends AppCompatActivity {

    private EditText txtCodigo;
    private EditText txtCodigoProduto;
    private EditText txtQuantidade;
    private EditText txtCalorias;
    private Spinner cmbProdutos;
    private Button btnCadastrar;
    private Button btnAlterar;
    private Button btnRemover;

    ArrayList<String> listaLstCmb;
    ArrayList<String> listaLst;
    ArrayAdapter<String> adapterLst;
    ArrayAdapter<String> adapterCmb;
    ArrayList<Integer> lstCodigosConsumo;
    private ListView lstConsumo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_consumo);

        txtCodigo=(EditText) findViewById(R.id.edtCodigo);
        txtCodigoProduto=(EditText) findViewById(R.id.edtCodigoProduto);
        txtQuantidade=(EditText) findViewById(R.id.edtQuantidade);
        txtCalorias=(EditText) findViewById(R.id.edtCaloria);
        cmbProdutos=(Spinner) findViewById(R.id.cmbProdutos);
        btnCadastrar=(Button) findViewById(R.id.btnCadastrar);
        btnAlterar=(Button) findViewById(R.id.btnAlterar);
        btnRemover=(Button) findViewById(R.id.btnRemover);
        lstConsumo=(ListView) findViewById(R.id.lstConsumos);

        listar();
        preencherCmb();

        lstConsumo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Consumo consumo;
                Produto produto;
                ConsumoCRUD consumoCRUD = new ConsumoCRUD();
                ProdutoCRUD produtoCRUD = new ProdutoCRUD();

                Cursor tabela;
                Date data = new Date(System.currentTimeMillis());

                try{
                    tabela = consumoCRUD.listar(getBaseContext());

                    if(tabela != null){
                        if(lstCodigosConsumo!=null){
                            lstCodigosConsumo.clear();
                        }else{
                            lstCodigosConsumo=new ArrayList<>();
                        }

                        while(tabela.moveToNext()){
                            lstCodigosConsumo.add(tabela.getInt(0));
                        }
                    }

                    if((i>=0)&&(i<lstCodigosConsumo.size())){
                        consumo = consumoCRUD.preencher(getBaseContext(), lstCodigosConsumo.get(i));
                        produto = produtoCRUD.preencher(getBaseContext(), consumo.getCodproduto());

                        txtCodigo.setText(String.valueOf(consumo.getCodigo()));
                        txtCodigoProduto.setText(String.valueOf(consumo.getCodproduto()));
                        txtCalorias.setText(String.valueOf((consumo.getQtde()) * (produto.getCaloria())));
                        txtQuantidade.setText(String.valueOf(consumo.getQtde()));
                    }
                }catch(Exception ex){
                    Toast.makeText(getBaseContext(), "Erro: " + ex.getMessage(),Toast.LENGTH_LONG);
                }
            }
        });

        cmbProdutos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Produto produto;
                ProdutoCRUD produtoCRUD = new ProdutoCRUD();

                Cursor tabela;

                try{
                    tabela = produtoCRUD.listar(getBaseContext());

                    if(tabela != null){
                        if(Lista.lstCodigosProdutos!=null){
                            Lista.lstCodigosProdutos.clear();
                        }else{
                            Lista.lstCodigosProdutos=new ArrayList<>();
                        }

                        while(tabela.moveToNext()){
                            Lista.lstCodigosProdutos.add(tabela.getInt(0));
                        }
                    }

                    if((i>=0)&&(i<Lista.lstCodigosProdutos.size())){
                        produto = produtoCRUD.preencher(getBaseContext(), Lista.lstCodigosProdutos.get(i));

                        txtQuantidade.setText("1");
                        txtCalorias.setText(String.valueOf(produto.getCaloria()));
                        txtCodigoProduto.setText(String.valueOf(produto.getCodigo()));
                    }
                }catch(Exception ex){
                    Toast.makeText(getBaseContext(), "Erro: " + ex.getMessage(),Toast.LENGTH_LONG);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        txtQuantidade.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Produto produto = new Produto();
                ProdutoCRUD produtoCRUD = new ProdutoCRUD();

                try{
                    if(charSequence.length() > 0){
                        produto = produtoCRUD.preencher(getBaseContext(), Integer.valueOf(txtCodigoProduto.getText().toString()));

                        txtCalorias.setText(String.valueOf(Double.valueOf(txtQuantidade.toString()) * produto.getCaloria()));
                    }
                }catch(Exception ex){
                    Toast.makeText(getBaseContext(), "Erro: " + ex.getMessage(),Toast.LENGTH_LONG);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void gravar(View v) throws Exception {
        Consumo consumo = new Consumo();
        ConsumoCRUD consumoCRUD = new ConsumoCRUD();
        ProdutoCRUD produtoCRUD = new ProdutoCRUD();

        try{
            consumo.setCodproduto(txtCodigoProduto.getText().toString());
            consumo.setQtde(txtQuantidade.getText().toString());

            consumoCRUD.gravar(getBaseContext(), consumo);

            limpar();
            listar();

            Toast.makeText(getBaseContext(),"Consumo "+consumo.getQtde()+ " " + produtoCRUD.preencher(getBaseContext(), consumo.getCodproduto()).getDescr() +  " cadastrado com sucesso!",Toast.LENGTH_SHORT).show();
        }catch(Exception ex){
            Toast.makeText(getBaseContext(),"Erro: "+ex.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    public void alterar(View v) throws Exception {
        Consumo consumo = new Consumo();
        ConsumoCRUD consumoCRUD = new ConsumoCRUD();

        try{
            consumo.setCodigo(txtCodigo.getText().toString());
            consumo.setCodproduto(txtCodigoProduto.getText().toString());
            consumo.setQtde(txtQuantidade.getText().toString());

            consumoCRUD.alterar(getBaseContext(), consumo);

            limpar();
            listar();

            Toast.makeText(getBaseContext(),"Consumo: "+consumo.getCodigo()+" alterado com sucesso!",Toast.LENGTH_SHORT).show();
        }catch(Exception ex){
            Toast.makeText(getBaseContext(),"Erro: "+ex.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    public void remover(View v) throws Exception {
        Consumo consumo = new Consumo();
        ConsumoCRUD consumoCRUD = new ConsumoCRUD();

        try{
            consumo.setCodigo(txtCodigo.getText().toString());

            consumoCRUD.remover(getBaseContext(), consumo);

            limpar();
            listar();

            Toast.makeText(getBaseContext(),"Consumo: "+consumo.getCodigo()+" removido com sucesso!",Toast.LENGTH_SHORT).show();
        }catch(Exception ex){
            Toast.makeText(getBaseContext(),"Erro: "+ex.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    public void preencherCmb(){
        ProdutoCRUD produtoCRUD = new ProdutoCRUD();
        Cursor tabela;

        try{
            tabela = produtoCRUD.listar(getBaseContext());

            if(tabela != null){
                if(listaLstCmb!=null){
                    listaLstCmb.clear();
                }else{
                    listaLstCmb=new ArrayList<>();
                }

                while(tabela.moveToNext()){
                    listaLstCmb.add(tabela.getString(1));
                }

                adapterCmb = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaLstCmb);

                cmbProdutos.setAdapter(adapterCmb);
            }

        }catch(Exception ex){
            Toast.makeText(getBaseContext(),"Erro: "+ex.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    public void listar(){
        ProdutoCRUD produtoCRUD = new ProdutoCRUD();
        ConsumoCRUD consumoCRUD = new ConsumoCRUD();
        Cursor tabela;
        Date data = new Date(System.currentTimeMillis());

        try{
            tabela = consumoCRUD.listar(getBaseContext());

            if(tabela != null){
                if(listaLst!=null){
                    listaLst.clear();
                }else{
                    listaLst=new ArrayList<>();
                }

                while(tabela.moveToNext()){
                    listaLst.add(tabela.getString(1) + " || " + (tabela.getDouble(8) * produtoCRUD.preencher(getBaseContext(), tabela.getInt(2)).getCaloria()));
                }

                adapterLst = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaLst);

                lstConsumo.setAdapter(adapterLst);
            }

        }catch(Exception ex){
            Toast.makeText(getBaseContext(),"Erro: "+ex.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    public void limpar(){
        try{
            txtCodigo.setText("");
            txtCalorias.setText("");
            txtQuantidade.setText("");
        }catch(Exception ex){
            Toast.makeText(getBaseContext(),"Erro: "+ex.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    public void fechar(View v){
        super.finish();
    }
}
