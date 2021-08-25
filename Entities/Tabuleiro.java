package Entities;

public class Tabuleiro {
    final int size = 8;
    int[][] posicoesTabuleiro;

    public Tabuleiro(Cavalo cavalo) {
        posicoesTabuleiro = new int[size][size];

        resolvendoCaminho(cavalo);
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
    // OKAY!!!
    private boolean resolvendoCaminho(Cavalo cavalo) {
        int valor = 1;

        int[] matrizX = { 2, 1, -1, -2, -2, -1, 1, 2 };
        int[] matrizY = { 1, 2, 2, 1, -1, -2, -2, -1 };

        posicoesTabuleiro[cavalo.posY][cavalo.posX] = valor;

        if (!checandoPosibilidades(cavalo.posX, cavalo.posY, ++valor, matrizX, matrizY)) {
            System.out.println("Solucao não existe");
            return false;
        } else {
            System.out.println("OKAY");
        }
        return true;
    }

    private boolean checandoPosibilidades(int valorX, int valorY, int valor, int[] posX, int[] posY) {
        int prox_X, prox_Y;
        if (valor > size * size)
            return true;

        for (int pos = 0; pos < size; ++pos) {
            prox_X = valorX + posX[pos];
            prox_Y = valorY + posY[pos];

            if (isSafe(prox_X, prox_Y)) {

                posicoesTabuleiro[prox_X][prox_Y] = valor;
                if (checandoPosibilidades(prox_X, prox_Y, ++valor, posX, posY))
                    return true;
                else {
                    --valor;
                    posicoesTabuleiro[prox_X][prox_Y] = 0;
                }
            }
        }
        return false;
    }
    // OK!
    private boolean isSafe(int xValue, int yValue) {
        if (xValue >= 0 && xValue < size && yValue >= 0 && yValue < size && posicoesTabuleiro[xValue][yValue] == 0)
            return true;
        return false;
    }
}
