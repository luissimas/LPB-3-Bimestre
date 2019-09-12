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

import com.example.aluno.lpb3bim.Controller.ProdutosCRUD;
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

        lstProduto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int pos;
                try{
                    pos=i;
                    if((pos>=0)&&(pos<Lista.lstProdutos.size())){
                        txtCodigo.setText(Lista.lstProdutos.get(pos).getCodigo());
                        txtDescr.setText(Lista.lstProdutos.get(pos).getDescr());
                        txtCalorias.setText(String.valueOf(Lista.lstProdutos.get(pos).getCaloria()));
                        txtUnidade.setText(String.valueOf(Lista.lstProdutos.get(pos).getUnidade()));
                    }
                }catch(Exception ex){
                    Toast.makeText(getBaseContext(), "Erro: " + ex.getMessage(),Toast.LENGTH_LONG);
                }
            }
        });
    }

    public void gravar(){

    }

    public void alterar(){

    }

    public void remover(){

    }

    public void atualizarLista(){
        Produto produto = new Produto();
        ProdutosCRUD produtoCRUD = new ProdutosCRUD();
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
                    
                }
            }

        }catch(Exception ex){
            Toast.makeText(getBaseContext(),"Erro: "+ex.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    public void fechar(View v){
        super.finish();
    }
}
