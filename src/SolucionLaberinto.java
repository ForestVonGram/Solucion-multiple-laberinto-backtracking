public class SolucionLaberinto {

    //Características del laberinto
    private static final int FILAS = 10;
    private static final int COLUMNAS = 10;

    //Variables
    private static final char SALIDA = 'S'; //no se usa, es necesaria?
    private static final char FIN = 'X';
    private static final char VACIO = ' ';
    private static final char MARCA = '*';

    private static boolean[][] visitado;

    public static void main(String[] args) {
        //Creación del laberinto. Se está usando el laberinto 2 de la plantilla de backtracking
        char[][] laberinto ={
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
                {'#', 'X', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'#', ' ', '#', ' ', '#', '#', ' ', '#', ' ', '#'},
                {'#', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', '#'},
                {'#', '#', '#', ' ', ' ', ' ', '#', '#', '#', '#'},
                {'#', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', '#'},
                {'#', '#', '#', '#', '#', ' ', '#', '#', '#', '#'},
                {'#', ' ', ' ', ' ', '#', ' ', '#', ' ', '#', '#'},
                {'#', 'S', '#', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
        };

        //Se crea un array booleano para indicar si ya se pasó por un camino
        visitado = new boolean[FILAS][COLUMNAS];

        //Se crean las soluciones para el laberinto
        SolucionLaberinto solucion = new SolucionLaberinto();
        //Se comienza desde la salida S
        solucion.buscarSalida(laberinto, 8, 1);
    }

    //Método para encontrar la salida
    public void buscarSalida(char[][] laberinto, int fila, int col) {
        if (!esValida(laberinto, fila, col) || visitado[fila][col]) {
            return;
        }

        if (laberinto[fila][col] == FIN) {
            //Encuentra una salida
            System.out.println("Se encontró una salida.");
            //Imprime ese resultado
            imprimirLaberinto(laberinto);
            return;
        }

        //Marca el camino como visitado
        visitado[fila][col] = true;

        //Si el espacio está vacío, deja una marca
        if (laberinto[fila][col] == VACIO) {
            laberinto[fila][col] = MARCA;
        }

        //Arriba
        buscarSalida(laberinto, fila - 1, col);
        //Abajo
        buscarSalida(laberinto, fila + 1, col);
        //Izquierda
        buscarSalida(laberinto, fila, col - 1);
        //Derecha
        buscarSalida(laberinto, fila, col + 1);

        //Después de encontrar una salida, borra las marcas
        if (laberinto[fila][col] == MARCA) {
            laberinto[fila][col] = VACIO;
        }

        //Desmarca el camino como visitado para las nuevas rutas.
        visitado[fila][col] = false;
    }

    //Verifica si el movimiento es válido
    private boolean esValida(char[][] laberinto, int fila, int col) {
        return fila >= 0 && fila < FILAS && col >= 0 && col < COLUMNAS && laberinto[fila][col] != '#';
    }

    //Imprime la solución al laberinto en consola
    private void imprimirLaberinto(char[][] laberinto) {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                System.out.print(laberinto[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
