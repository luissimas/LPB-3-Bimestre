package com.example.aluno.lpb3bim.Model;

import java.util.ArrayList;

public class Lista {
    public static ArrayList<Consumo> lstConsumo;

    static{

        if(lstConsumo == null){
            lstConsumo = new ArrayList<>();
        }
    }
}
