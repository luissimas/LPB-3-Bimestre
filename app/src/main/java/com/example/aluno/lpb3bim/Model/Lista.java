package com.example.aluno.lpb3bim.Model;

import java.util.ArrayList;

public class Lista {
    public static ArrayList<Produto> lstProdutos;
    public static ArrayList<Consumo> lstConsumo;

    static{
        if(lstProdutos == null){
            lstProdutos = new ArrayList<>();
        }

        if(lstConsumo == null){
            lstConsumo = new ArrayList<>();
        }
    }
}
