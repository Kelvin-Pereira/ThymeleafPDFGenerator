package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

public class SubconjuntoSoma {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int alvo = 9;

        // Lista para armazenar os resultados
        List<List<Integer>> resultados = new ArrayList<>();

        // Chamar a função para encontrar subconjuntos
        encontrarSubconjuntos(array, 0, alvo, new ArrayList<>(), resultados);

        // Exibir os subconjuntos encontrados
        System.out.println("Subconjuntos que somam " + alvo + ":");
        for (List<Integer> subconjunto : resultados) {
            System.out.println(subconjunto);
        }
    }

    public static void encontrarSubconjuntos(int[] array, int indice, int alvo, List<Integer> subconjuntoAtual, List<List<Integer>> resultados) {
        // Se o alvo for atingido, adiciona o subconjunto atual à lista de resultados
        if (alvo == 0) {
            resultados.add(new ArrayList<>(subconjuntoAtual));
            return;
        }

        // Se ultrapassarmos o tamanho do array ou o alvo for negativo, retornamos
        if (indice >= array.length || alvo < 0) {
            return;
        }

        // Incluir o elemento atual no subconjunto e continuar a busca
        subconjuntoAtual.add(array[indice]);
        encontrarSubconjuntos(array, indice + 1, alvo - array[indice], subconjuntoAtual, resultados);

        // Não incluir o elemento atual no subconjunto e continuar a busca
        subconjuntoAtual.removeLast();
        encontrarSubconjuntos(array, indice + 1, alvo, subconjuntoAtual, resultados);
    }
}

