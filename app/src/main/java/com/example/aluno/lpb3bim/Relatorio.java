package com.example.aluno.lpb3bim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Relatorio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);
    }

    public void fechar(View v){
        super.finish();
    }
}
