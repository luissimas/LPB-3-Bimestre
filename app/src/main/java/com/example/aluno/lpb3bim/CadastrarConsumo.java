package com.example.aluno.lpb3bim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.aluno.lpb3bim.Model.Lista;

import java.util.ArrayList;

public class CadastrarConsumo extends AppCompatActivity {

    private EditText txtCodigo;
    private EditText txtQuantidade;
    private EditText txtCalorias;
    private Spinner cmbProdutos;
    private Button btnCadastrar;
    private Button btnAlterar;
    private Button btnRemover;

    ArrayList<String> listaLst;
    ArrayAdapter<String> adapter;
    private ListView lstConsumo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_consumo);

        txtCodigo=(EditText) findViewById(R.id.edtCodigo);
        txtQuantidade=(EditText) findViewById(R.id.edtQuantidade);
        txtCalorias=(EditText) findViewById(R.id.edtCaloria);
        cmbProdutos=(Spinner) findViewById(R.id.cmbProdutos);
        btnCadastrar=(Button) findViewById(R.id.btnCadastrar);
        btnAlterar=(Button) findViewById(R.id.btnAlterar);
        btnRemover=(Button) findViewById(R.id.btnRemover);

    }

    public void fechar(View v){
        super.finish();
    }
}
