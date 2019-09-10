package com.example.aluno.lpb3bim.Model;

import java.util.ArrayList;

public class Lista {
    public static ArrayList<Produto> lstProdutos;

    static{
        if(lstProdutos == null){
            lstProdutos = new ArrayList<>();
        }
    }
}
