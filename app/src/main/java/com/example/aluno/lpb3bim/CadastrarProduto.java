package com.example.aluno.lpb3bim;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.aluno.lpb3bim.Controller.ProdutoCRUD;
import com.example.aluno.lpb3bim.Model.Lista;
import com.example.aluno.lpb3bim.Model.Produto;

import java.util.ArrayList;

public class CadastrarProduto extends AppCompatActivity {

    private EditText txtCodigo;
    private EditText txtDescr;
    private EditText txtUnidade;
    private EditText txtCalorias;
    private Button btnCadastrar;
    private Button btnAlterar;
    private Button btnRemover;

    ArrayList<String> listaLst;
    ArrayAdapter<String> adapter;
    private ListView lstProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_produtos);

        txtCodigo=(EditText) findViewById(R.id.edtCodigo);
        txtDescr=(EditText) findViewById(R.id.edtDescr);
        txtUnidade=(EditText) findViewById(R.id.edtUnidade);
        txtCalorias=(EditText) findViewById(R.id.edtCaloria);
        btnCadastrar=(Button) findViewById(R.id.btnCadastrar);
        btnAlterar=(Button) findViewById(R.id.btnAlterar);
        btnRemover=(Button) findViewById(R.id.btnRemover);
        lstProduto =(ListView) findViewById(R.id.lstProdutos);

        listar();
    }

    public void gravar(View v){
        Produto produto = new Produto();
        ProdutoCRUD produtoCRUD = new ProdutoCRUD();

        try{
            produto.setDescr(txtDescr.getText().toString());
            produto.setUnidade(txtUnidade.getText().toString());
            produto.setCaloria(txtCalorias.getText().toString());

            produtoCRUD.gravar(getBaseContext(), produto);

            limpar();
            listar();

            Toast.makeText(getBaseContext(),"Produto: "+produto.getDescr()+" cadastrado com sucesso!",Toast.LENGTH_SHORT).show();
        }catch(Exception ex){
            Toast.makeText(getBaseContext(),"Erro: "+ex.getMessage(),Toast.LENGTH_SHORT).show();
        }


    }

    public void alterar(View v){
        Produto produto = new Produto();
        ProdutoCRUD produtoCRUD = new ProdutoCRUD();

        try{
            produto.setCodigo(txtCodigo.getText().toString());
            produto.setDescr(txtDescr.getText().toString());
            produto.setUnidade(txtUnidade.getText().toString());
            produto.setCaloria(txtCalorias.getText().toString());

            produtoCRUD.alterar(getBaseContext(), produto);

            limpar();
            listar();

            Toast.makeText(getBaseContext(),"Produto: "+produto.getDescr()+" cadastrado com sucesso!",Toast.LENGTH_SHORT).show();
        }catch(Exception ex){
            Toast.makeText(getBaseContext(),"Erro: "+ex.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    public void remover(View v){
        Produto produto = new Produto();
        ProdutoCRUD produtoCRUD = new ProdutoCRUD();

        try{
            produto.setCodigo(txtCodigo.getText().toString());

            produtoCRUD.remover(getBaseContext(), produto);

            limpar();
            listar();

            Toast.makeText(getBaseContext(),"Produto: "+produto.getDescr()+" cadastrado com sucesso!",Toast.LENGTH_SHORT).show();
        }catch(Exception ex){
            Toast.makeText(getBaseContext(),"Erro: "+ex.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    public void listar(){
        Produto produto = new Produto();
        ProdutoCRUD produtoCRUD = new ProdutoCRUD();
        Cursor tabela;

        try{
            tabela = produtoCRUD.listar(getBaseContext());

            if(tabela != null){
                if(listaLst!=null){
                    listaLst.clear();
                }else{
                    listaLst=new ArrayList<>();
                }

                while(tabela.moveToNext()){

                    listaLst.add(tabela.getString(1) + "||" + tabela.getInt(2) + "||" + tabela.getInt(3));

                }

                adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaLst);

                lstProduto.setAdapter(adapter);
            }

        }catch(Exception ex){
            Toast.makeText(getBaseContext(),"Erro: "+ex.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    public void limpar(){
        try{
            txtCodigo.setText("");
            txtDescr.setText("");
            txtCalorias.setText("");
            txtUnidade.setText("");
        }catch(Exception ex){
            Toast.makeText(getBaseContext(),"Erro: "+ex.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    public void fechar(View v){
        super.finish();
    }
}
