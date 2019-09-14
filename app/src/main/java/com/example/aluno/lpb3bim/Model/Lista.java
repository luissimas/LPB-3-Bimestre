package com.example.aluno.lpb3bim.Model;

import java.util.ArrayList;

public class Lista {
    public static ArrayList<Consumo> lstConsumo;
    public static ArrayList<Integer> lstCodigosProdutos;

    static{

        if(lstCodigosProdutos == null){
            lstCodigosProdutos = new ArrayList<>();
        }

        if(lstConsumo == null){
            lstConsumo = new ArrayList<>();
        }
    }
}
