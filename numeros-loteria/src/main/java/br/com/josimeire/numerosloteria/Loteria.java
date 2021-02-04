package br.com.josimeire.numerosloteria;

import java.util.Random;
import java.util.stream.IntStream;
	
public final class Loteria {

	private final int[][] values = {{25, 15, 0}, {100, 50, 1}, {60, 6, 0}, {80, 5, 0}};
    private int size, dozens, type;
    private int[] numbers; // Array para receber os números sorteados
    private int[] compare; // Array para comparar e controlar números sorteados
    private Random generator; // Gerador de números aleatórios

    public enum LOTO {
        LOTOFACIL(0), LOTOMANIA(1), MEGASENA(2), QUINA(3);
        int valor;

        private LOTO(int valor) {
            this.valor = valor;
        }
    };

    /**
     * Método construtor
     *
     * @param loto Enum tipo de jogo
     */
    public Loteria(LOTO loto) {
        initComponents(loto);
    }

	/**
     * Inicializa a classe
     *
     * @param loto Enum tipo de jogo
     */
    private void initComponents(LOTO loto) {
        // Verifica o tipo de loteria
        size = values[loto.valor][0]; //dezenas máximas
        dozens = values[loto.valor][1]; //dezenas possíveis
        type = values[loto.valor][2]; //tipo

        compare = new int[size];     // Cria o Array
        numbers = new int[dozens];   // Cria o Array
        generator = new Random();    // Cria o Random

        generatorNumbers();   // Chama o método para gerar numeros
        ascendingOrder();     // Ordena os números em ordem crescente
    }

    /**
     * Método para gerar números aleatórios
     */
    private void generatorNumbers() {
        IntStream.range(0, compare.length).forEach(i -> compare[i] = i + 1);

        IntStream.range(0, numbers.length).forEach(i -> {
            boolean ctl = false;
            while (!ctl) {
                ctl = setNumber(i, launch_Random());
            }
        });
    }

    /**
     * Seta o valor dentro do array de números
     * @param i posição do número no array
     * @param x número a ser inserido no array
     * @return boolean sucesso da operação
     */
    private boolean setNumber(int i, int x) {
        for (int j = 0; j < compare.length; j++) {
            if (x == compare[j]) {
                numbers[i] = x;
                compare[j] = -1;
                return true;
            }
        }
        return false;
    }

    /**
     * Gera valor inteiro randonico
     *
     * @return int valor
     */
    private int launch_Random() {
        return type == 0 ? generator.nextInt(size) + 1 : generator.nextInt(size);
    }

    /**
     * Método para ordenar os números em
     */
    private void ascendingOrder() {
        numbers = IntStream.of(numbers).sorted().toArray();
    }

    /**
     * Mostra os valores aleatórios
     *
     * @return String com valores
     */
    public String showNumbers() {
        return IntStream.of(numbers).mapToObj(String::valueOf).reduce("", (s1, s2) -> s1.concat("  " + s2));
    }

}