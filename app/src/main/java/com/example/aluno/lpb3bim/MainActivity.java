package com.example.aluno.lpb3bim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnCadastro;
    private Button btnConsumo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCadastro = (Button) findViewById(R.id.btnAbrirCadastro);
        btnConsumo =(Button) findViewById(R.id.btnAbrirConsumo);
    }

    public void abrirCadastro(View v){
        Intent intent = new Intent(MainActivity.this, CadastrarProduto.class);
        startActivity(intent);
    }

    public void abrirConsumo(View v){
        Intent intent = new Intent(MainActivity.this, CadastrarConsumo.class);
        startActivity(intent);
    }

    public void abrirRelatorio(View v){
        Intent intent = new Intent(MainActivity.this, Relatorio.class);
        startActivity(intent);
    }
}
