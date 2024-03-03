package ejercicios;

import java.util.function.Function;
import java.util.stream.DoubleStream;

public class ejercicio16 {

    public static double integral(double a, double b, double h, Function<Double, Double> funcion) {
        // Calculamos el número de pasos desde 'a' hasta 'b' con un incremento 'h'
        long numSteps = (long) ((b - a) / h);

        // Generamos un stream con todos los puntos en el intervalo [a, b) con un incremento 'h'
        // Luego aplicamos la función a cada punto
        // Y sumamos todos los resultados multiplicados por 'h'
        return DoubleStream.iterate(a, n -> n + h)
                .limit(numSteps)
                .map(funcion::apply)
                .sum() * h;
    }

    public static void main(String[] args) {
        // Ejemplo de uso: calcular la integral de la función x -> x^2 en el intervalo [0, 10] con h = 0.01
        double resultado = integral(0, 10, 0.01, x -> x * x);
        System.out.println("Resultado de la integral: " + resultado);
    }
}
