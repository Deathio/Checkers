package Entities;

import java.util.LinkedList;
import java.util.Queue;

public class Tabuleiro {
    int caminho, size = 8;
    int[][] posicoesTabuleiro;
    Queue<Cavalo> caminhoPercorrido;

    public Tabuleiro(Cavalo cavalo) {
        caminho = 0;
        posicoesTabuleiro = new int[size][size];
        caminhoPercorrido = new LinkedList<>();

        checandoPosibilidades(cavalo);
        System.out.println(caminho);
    }

    @Override
    public String toString() {
        String print = "\s";
        for (int firstLine = 0; firstLine < size; ++firstLine)
            print += String.format(firstLine == size - 1 ?"\s\s\s%d\n" : "\s\s\s%d", firstLine);
        for (int collumn = 0; collumn < size; ++collumn) {
            print += collumn;
            for (int line = 0; line < size; ++line) {
                String valorRepresent = posicoesTabuleiro[collumn][line] > 9 ? "\s\s" + posicoesTabuleiro[collumn][line] 
                                                                            : "\s\s\s" + posicoesTabuleiro[collumn][line];
                print += valorRepresent;
            }
            print += "\n";
        }
        return print;
    }

    private void checandoPosibilidades(Cavalo atual) {
        if (atual != null && caminho <= 64) {
            Cavalo[] movimentos = possiveisMovimentos(atual);

            if (!caminhoPercorrido.isEmpty()) {
                for (Cavalo atribuir : caminhoPercorrido) {
                    AtribuirPos(atribuir);
                }
            }
            if (movimentos.length > 0) {
                for (Cavalo cavalo : movimentos) {
                    caminhoPercorrido.add(cavalo);
                    caminho++;
                    checandoPosibilidades(cavalo);
                    caminhoPercorrido.remove();
                    caminho--;
                }
            }
        }
    }

    private void AtribuirPos(Cavalo cavalo) {
        if (cavalo != null)
            posicoesTabuleiro[cavalo.posX][cavalo.posY] += 1;
    }

    private Cavalo[] possiveisMovimentos(Cavalo atual) {
        Cavalo check;
        Cavalo[] line = new Cavalo[8];
        int posicaoArmazenamento = 0;
        if (caminho <= 64) {

            // esquerda cima
            if (atual.posX - 2 >= 0 && atual.posX - 2 < size && atual.posY - 1 > 0 && atual.posY - 1 < size) {
                check = new Cavalo(atual.posX - 2, atual.posY - 1);
                if (checagemIgualdade(check)) {
                    line[posicaoArmazenamento] = check;
                    ++posicaoArmazenamento;
                }
            }
            // esquerda baixo
            if (atual.posX - 2 >= 0 && atual.posX - 2 < size && atual.posY + 1 > 0 && atual.posY + 1 < size) {
                check = new Cavalo(atual.posX - 2, atual.posY + 1);
                if (checagemIgualdade(check)) {
                    line[posicaoArmazenamento] = check;
                    ++posicaoArmazenamento;
                }
            }
            // direita baixo
            if (atual.posX + 2 >= 0 && atual.posX + 2 < size && atual.posY - 1 >= 0 && atual.posY - 1 < size) {
                check = new Cavalo(atual.posX + 2, atual.posY - 1);
                if (checagemIgualdade(check)) {
                    line[posicaoArmazenamento] = check;
                    ++posicaoArmazenamento;
                }
            }
            // direita cima
            if (atual.posX + 2 >= 0 && atual.posX + 2 < size && atual.posY + 1 >= 0 && atual.posY + 1 < size) {
                check = new Cavalo(atual.posX + 2, atual.posY + 1);
                if (checagemIgualdade(check)) {
                    line[posicaoArmazenamento] = check;
                    ++posicaoArmazenamento;
                }
            }
            // cima esquerda
            if (atual.posX - 1 >= 0 && atual.posX - 1 < size && atual.posY - 2 >= 0 && atual.posY - 2 < size) {
                check = new Cavalo(atual.posX - 1, atual.posY - 2);
                if (checagemIgualdade(check)) {
                    line[posicaoArmazenamento] = check;
                    ++posicaoArmazenamento;
                }
            }
            // cima direita
            if (atual.posX + 1 >= 0 && atual.posX + 1 < size && atual.posY - 2 >= 0 && atual.posY - 2 < size) {
                check = new Cavalo(atual.posX + 1, atual.posY - 2);
                if (checagemIgualdade(check)) {
                    line[posicaoArmazenamento] = check;
                    ++posicaoArmazenamento;
                }
            }
            // baixo esquerda
            if (atual.posX - 1 >= 0 && atual.posX - 1 < size && atual.posY + 2 >= 0 && atual.posY + 2 < size) {
                check = new Cavalo(atual.posX - 1, atual.posY + 2);
                if (checagemIgualdade(check)) {
                    line[posicaoArmazenamento] = check;
                    ++posicaoArmazenamento;
                }
            }
            // baixo direita
            if (atual.posX + 1 >= 0 && atual.posX + 1 < size && atual.posY + 2 >= 0 && atual.posY + 2 < size) {
                check = new Cavalo(atual.posX + 1, atual.posY + 2);
                if (checagemIgualdade(check)) {
                    line[posicaoArmazenamento] = check;
                    ++posicaoArmazenamento;
                }
            }
        }
        return line;

    }

    private boolean checagemIgualdade(Cavalo cavalo) {
        if (cavalo != null) {
            for (Cavalo check : caminhoPercorrido) {
                if (cavalo.equals(check))
                    return false;
            }
        }
        return true;
    }
}
