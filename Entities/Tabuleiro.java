package Entities;

import java.util.LinkedList;
import java.util.Queue;

public class Tabuleiro {
    static int size = 8;
    int[][] posicoesTabuleiro;
    Queue<Cavalo> caminhoPercorrido;
    Queue<Cavalo> maiorCaminhoPercorrido;

    public Tabuleiro(Cavalo cavalo) {
        posicoesTabuleiro = new int[size][size];
        caminhoPercorrido = new LinkedList<>();
        maiorCaminhoPercorrido = new LinkedList<>();

        checandoPosibilidades(cavalo);
        IlustrarTabuleiro();
    }

    @Override
    public String toString() {
        String print = "\s";
        for (int firstLine = 0; firstLine < size; ++firstLine)
            print += String.format(firstLine == size - 1 ? "\s\s\s%d\n" : "\s\s\s%d", firstLine);
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
        if (atual != null || maiorCaminhoPercorrido.size() < 64) {
            Queue<Cavalo> movimentos = possiveisMovimentos(atual);

            if (movimentos != null)
                for (Cavalo cavalo : movimentos) {
                    if(caminhoPercorrido.size() < 64) {
                        caminhoPercorrido.add(cavalo);

                        checandoPosibilidades(cavalo);
                    }

                    if (caminhoPercorrido.size() > maiorCaminhoPercorrido.size())
                        maiorCaminhoPercorrido = caminhoPercorrido;

                    if(caminhoPercorrido.size() == 64)
                        break;
                    caminhoPercorrido.remove();
                }
        }
    }

    private Queue<Cavalo> possiveisMovimentos(Cavalo atual) {
        Cavalo check;
        Queue<Cavalo> line = new LinkedList<>();

        // esquerda cima
        if (atual.posX - 2 >= 0 && atual.posX - 2 < size && atual.posY - 1 > 0 && atual.posY - 1 < size) {
            check = new Cavalo(atual.posX - 2, atual.posY - 1);
            if (checagemIgualdade(check)) {
                line.add(check);
            }
        }
        // esquerda baixo
        if (atual.posX - 2 >= 0 && atual.posX - 2 < size && atual.posY + 1 > 0 && atual.posY + 1 < size) {
            check = new Cavalo(atual.posX - 2, atual.posY + 1);
            if (checagemIgualdade(check)) {
                line.add(check);
            }
        }
        // direita cima
        if (atual.posX + 2 >= 0 && atual.posX + 2 < size && atual.posY - 1 >= 0 && atual.posY - 1 < size) {
            check = new Cavalo(atual.posX + 2, atual.posY - 1);
            if (checagemIgualdade(check)) {
                line.add(check);
            }
        }
        // direita baixo
        if (atual.posX + 2 >= 0 && atual.posX + 2 < size && atual.posY + 1 >= 0 && atual.posY + 1 < size) {
            check = new Cavalo(atual.posX + 2, atual.posY + 1);
            if (checagemIgualdade(check)) {
                line.add(check);
            }
        }
        // cima esquerda
        if (atual.posX - 1 >= 0 && atual.posX - 1 < size && atual.posY - 2 >= 0 && atual.posY - 2 < size) {
            check = new Cavalo(atual.posX - 1, atual.posY - 2);
            if (checagemIgualdade(check)) {
                line.add(check);
            }
        }
        // cima direita
        if (atual.posX + 1 >= 0 && atual.posX + 1 < size && atual.posY - 2 >= 0 && atual.posY - 2 < size) {
            check = new Cavalo(atual.posX + 1, atual.posY - 2);
            if (checagemIgualdade(check)) {
                line.add(check);
            }
        }
        // baixo esquerda
        if (atual.posX - 1 >= 0 && atual.posX - 1 < size && atual.posY + 2 >= 0 && atual.posY + 2 < size) {
            check = new Cavalo(atual.posX - 1, atual.posY + 2);
            if (checagemIgualdade(check)) {
                line.add(check);
            }
        }
        // baixo direita
        if (atual.posX + 1 >= 0 && atual.posX + 1 < size && atual.posY + 2 >= 0 && atual.posY + 2 < size) {
            check = new Cavalo(atual.posX + 1, atual.posY + 2);
            if (checagemIgualdade(check)) {
                line.add(check);
            }
        }
        return line.size() > 0 ? line : null;
    }

    private boolean checagemIgualdade(Cavalo cavalo) {
        for (Cavalo check : caminhoPercorrido) {
            if (cavalo == null || cavalo.equals(check))
                return false;
        }

        return true;
    }
    
    private void IlustrarTabuleiro() {
        int valor = 1;
        for (Cavalo cavalo : maiorCaminhoPercorrido) {
            posicoesTabuleiro[cavalo.posY][cavalo.posX] = valor;
            valor++;
        }
    }
}
