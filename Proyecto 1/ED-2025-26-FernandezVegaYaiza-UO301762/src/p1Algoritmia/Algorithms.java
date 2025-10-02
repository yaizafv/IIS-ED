package p1Algoritmia;

public class Algorithms {
	private static final long SLEEP_TIME = 1;

	public static void doNothing() {
		try {
			Thread.sleep(SLEEP_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Devuelve si un numero es par o no de forma recursiva Complejidad O(n)
	 * 
	 * @param n numero
	 * @return true si n es par, false en caso contrario
	 */
	public static boolean esPar(int n) {
		if (n == 0) {
			return true;
		}
		if (n == 1) {
			return false;
		}
		if (n < 0) {
			throw new IllegalArgumentException("n < 0");
		}
		return esPar(n - 2);
	}

	/**
	 * Devuelve el factorial de un numero de forma iterativa Complejidad O(n)
	 * 
	 * @param n numero
	 * @return factorial de n
	 */
	public static int factorialIterative(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("n < 0"); // 0! = 1
		}
		int factorial = 1;
		for (int i = 1; i <= n; i++) {
			factorial *= i;
		}
		return factorial;
	}

	/**
	 * Devuelve el factorial de un numero de forma recursiva Complejidad O(n)
	 * 
	 * @param n numero
	 * @return factorial de n
	 */
	public static int factorialRec(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("n < 0");
		}

		if (n == 0 || n == 1) {
			return 1;
		}

		return n * factorialRec(n - 1);
	}

	/**
	 * Devuelve el valor del termino de la serie de Fibonacci de forma iterativa
	 * Complejidad O(n)
	 * 
	 * @param n numero
	 * @return valor del termino de la serie de Fibonacci
	 */
	public static int fibonacciIter(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("n < 0");
		}
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}
		int num1 = 0;
		int num2 = 1;
		int total = 0;
		for (int i = 2; i <= n; i++) {
			total = num1 + num2;
			num1 = num2;
			num2 = total;
		}
		return total;
	}

	/**
	 * Devuelve el valor del termino de la serie de Fibonacci de forma recursiva
	 * Complejidad O(2^n)
	 * 
	 * @param n numero
	 * @return valor del termino de la serie de Fibonacci
	 */
	public static int fibonacciRec(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("n < 0");
		}
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}
		return fibonacciRec(n - 1) + fibonacciRec(n - 2);
	}

	/**
	 * Recibe una matriz y calcula si es simetrica de forma recursiva Complejidad
	 * O(n^2)
	 * 
	 * @param matrix matriz
	 * @return true si la matriz es simetrica, false en caso contrario
	 */
	public static boolean esSimetrico(int[][] matrix) {
		if (matrix == null) {
			throw new NullPointerException("la matriz no puede ser null");
		}
		if (!esCuadrada(matrix)) {
			return false;
		}
		return esSimetricoRec(matrix, 0, 0);
	}

	/**
	 * Comprueba que una matriz es cuadrada a excepcion de la ultima fila
	 * 
	 * @param m, matriz a comprobar
	 * @return true si es cuadrada o false en caso contrario
	 */
	private static boolean esCuadrada(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			if (matrix.length != matrix[i].length) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Compara el contenido de la matriz pasada como parametro de forma recursiva
	 * 
	 * @param matrix matriz
	 * @param row    fila
	 * @param col    columna
	 * @return true si es simetrica, false en caso contrario
	 */
	private static boolean esSimetricoRec(int[][] matrix, int row, int col) {
		if (row >= matrix.length) {
			return true;
		}
		if (col < matrix.length) {
			if (row != col && matrix[row][col] != matrix[col][row]) {
				return false;
			}
			return esSimetricoRec(matrix, row, col + 1);
		}
		return esSimetricoRec(matrix, row + 1, 0);
	}

	/**
	 * Metodo que recibe dividendo y divisor y devuelve el resto de la division de
	 * forma recursiva
	 * 
	 * @param dividendo dividendo
	 * @param divisor   divisor
	 * @return resto de la division
	 */
	public static int resto(int dividendo, int divisor) {
		if (dividendo == 0 && divisor == 0) {
			throw new IllegalArgumentException("dividendo y divisor no pueden ser 0 (indeterminacion)");
		}
		if (dividendo < 0 || divisor < 0) {
			throw new IllegalArgumentException("no puede ser < 0");
		}
		if (dividendo == divisor) {
			return 0;
		}
		if (dividendo < divisor) {
			return dividendo;
		}
		return resto(dividendo - divisor, divisor);
	}

	/**
	 * Metodo que recibe dividendo y divisor y retorna el resultado de la division
	 * de forma recursiva
	 * 
	 * @param dividendo
	 * @param divisor
	 * @return resultado de la division
	 */
	public static int division(int dividendo, int divisor) {
		if (dividendo == 0 && divisor == 0) {
			throw new IllegalArgumentException("dividendo y divisor no pueden ser 0 (indeterminacion)");
		}
		if (dividendo < 0 || divisor < 0) {
			throw new IllegalArgumentException("no puede ser < 0");
		}
		if (dividendo == divisor) {
			return 1;
		}
		if (dividendo < divisor) {
			return 0;
		}
		return 1 + division(dividendo - divisor, divisor);
	}

	/**
	 * Metodo que calcula la potencia de 2 para un exponente dado de forma iterativa
	 * Complejidad O(n)
	 * 
	 * @param n exponente
	 * @return potencia de 2
	 */
	public static int powIter(int n) {
		doNothing();
		int resultado = 1;
		if (n < 0) {
			throw new IllegalArgumentException("n < 0");
		}
		if (n == 0) {
			return 1;
		}
		while (n > 0) {
			resultado *= 2;
			n--;
		}
		return resultado;
	}

	/**
	 * Metodo que calcula la potencia recursiva 2 de 2 elevado al numero introducido
	 * con 1 llamada que decrece el exponente Complejidad O(n)
	 * 
	 * @param n, numero al que elevar el 2
	 * @return potencia de 2 elevado a n
	 */
	public static int powRec1(int n) {
		doNothing();
		if (n < 0) {
			throw new IllegalArgumentException("n < 0");
		}
		if (n == 0) {
			return 1;
		}
		return 2 * powRec1(n - 1);
	}

	/**
	 * Metodo que calcula la potencia recursiva 2 de 2 elevado al numero introducido
	 * con 1 llamada que decrece el exponente mas rapidamente Complejidad O(log n)
	 * 
	 * @param n, numero al que elevar el 2
	 * @return potencia de 2 elevado a n
	 */
	public static int powRec2(int n) {
		doNothing();
		if (n < 0) {
			throw new IllegalArgumentException("n < 0");
		}
		if (n == 0) {
			return 1;
		}
		int mitad = powRec2(n / 2);
		if (n % 2 == 0) {
			return mitad * mitad;
		}
		return 2 * mitad * mitad;

	}

	/**
	 * Metodo que calcula la potencia recursiva 2 de 2 elevado al numero introducido
	 * con 2 llamadas que decrecen el exponente ligeramente Complejidad O(2^n)
	 * 
	 * @param n, numero al que elevar el 2
	 * @return potencia de 2 elevado a n
	 */
	public static int powRec3(int n) {
		doNothing();
		if (n < 0) {
			throw new IllegalArgumentException("n < 0");
		}
		if (n == 0) {
			return 1;
		}
		return powRec3(n - 1) + powRec3(n - 1);
	}

	/**
	 * Metodo que calcula la potencia recursiva 2 de 2 elevado al numero introducido
	 * con 2 llamadas que decrecen el exponente mas rapidamente Complejidad O(n)
	 * 
	 * @param n, numero al que elevar el 2
	 * @return potencia de 2 elevado a n
	 */
	public static int powRec4(int n) {
		doNothing();
		if (n < 0) {
			throw new IllegalArgumentException("n < 0");
		}
		if (n == 0) {
			return 1;
		}
		int mitad = powRec4(n / 2);
		if (n % 2 == 0) {
			return mitad * mitad;
		} else {
			return 2 * mitad * mitad;
		}
	}

	/**
	 * Metodo que tiene una complejidad temporal lineal Complejidad O(n)
	 * 
	 * @param n numero
	 */
	public static void lineal(int n) {
		doNothing();
		if (n < 0) {
			throw new IllegalArgumentException("n < 0");
		}
		for (int i = 0; i <= n; i++) {
			doNothing();
			System.out.println("Ejecucion número " + i);
		}
	}

	/**
	 * Metodo que tiene una complejidad temporal cuadratica Complejidad O(n^2)
	 * 
	 * @param n numero
	 */
	public static void cuadratica(int n) {
		doNothing();
		if (n < 0) {
			throw new IllegalArgumentException("n < 0");
		}
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= n; j++) {
				doNothing();
				System.out.println("Ejecucion número " + j);
			}
			System.out.println("Más " + i);
		}
	}

	/**
	 * Método que tiene una complejidad temporal cubica Complejidad O(n^3)
	 * 
	 * @param n numero
	 */
	public static void cubica(int n) {
		doNothing();
		if (n < 0) {
			throw new IllegalArgumentException("n < 0");
		}
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= n; j++) {
				for (int k = 0; k <= n; k++) {
					doNothing();
					System.out.println("Ejecucion número " + k);
				}
				System.out.println("Más " + j);
			}
			System.out.println("Y más " + i);
		}
	}

	/**
	 * Método que tiene una complejidad temporal logaritmica Complejidad O(log n)
	 * 
	 * @param n numero
	 */
	public static void logaritmica(int n) {
		doNothing();
		if (n < 0)
			throw new IllegalArgumentException("n < 0");
		while (n > 0) {
			doNothing();
			n = n / 2;
			System.out.println(n);
		}
	}

}
