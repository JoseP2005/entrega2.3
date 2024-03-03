package ejercicios;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.Arrays;

public class EjerciciosLambda {

    public static void main(String[] args) {
        // Ejercicio 13: Uso de lambda para calcular una integral
        System.out.println("Integral de x^2 (de 0 a 10): " + integral(0, 10, 0.01, x -> x * x));

        // Ejercicio 14: Generación de Streams de números
        IntStream.of(1, 2, 3, 4, 5).forEach(n -> System.out.println("Stream of: " + n));
        IntStream.range(1, 6).forEach(n -> System.out.println("Range: " + n));
        IntStream.iterate(0, n -> n + 2).limit(5).forEach(n -> System.out.println("Iterate: " + n));
        new Random().doubles(5, 0, 1).forEach(n -> System.out.println("Doubles: " + n));

        //Ejercicio 15:
        int[] lista = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // Suma usando Stream
        int suma = IntStream.rangeClosed(1, 10).sum();
        System.out.println("Suma: " + suma);

        // Factorial usando reduce con Stream
        int n = 5;
        int factorial = IntStream.rangeClosed(1, n).reduce(1, (a, b) -> a * b);
        System.out.println("Factorial de " + n + ": " + factorial);

        // Potencia usando IntStream.iterate y limit
        int base = 2;
        int exponente = 3;
        int potencia = IntStream.iterate(base, a -> a).limit(exponente).reduce((a, b) -> a * b).getAsInt();
        System.out.println("Potencia: " + potencia);

        // Suma de lista usando Stream
        int sumaLista = Arrays.stream(lista).sum();
        System.out.println("Suma de lista: " + sumaLista);

        // Media usando Stream
        double media = Arrays.stream(lista).average().orElse(Double.NaN);
        System.out.println("Media: " + media);

        // Desviación estándar usando Stream
        double desviacion = Math.sqrt(Arrays.stream(lista).mapToDouble(i -> Math.pow(i - media, 2)).sum() / lista.length);
        System.out.println("Desviación Estándar: " + desviacion);

        // Suma de pares usando Stream
        int sumaPares = IntStream.rangeClosed(1, n).filter(i -> i % 2 == 0).sum();
        System.out.println("Suma de pares: " + sumaPares);

        // Suma de pares en lista usando Stream
        int sumaParesLista = Arrays.stream(lista).filter(i -> i % 2 == 0).sum();
        System.out.println("Suma de pares en lista: " + sumaParesLista);

        // Ejercicio 16: Ejercicio 13 utilizando tambien streams en la otra clase

        // Ejercicio 17: Clase Persona y operaciones con expresiones lambda
        Personas personas = new Personas();
        personas.annadirPersona(new Persona("Alice", LocalDate.of(1990, 1, 1)));
        personas.annadirPersona(new Persona("Bob", LocalDate.of(2000, 1, 1)));
        personas.annadirPersona(new Persona("Charlie", LocalDate.of(1985, 1, 1)));

        System.out.println("La persona más joven es: " + personas.elMasJoven().nombre);
        System.out.println("La suma de edades es: " + personas.calcularSumaEdades());
        System.out.println("La edad mínima es: " + personas.calcularEdadMinima());
        System.out.println("La media de edad es: " + personas.calcularMediaDeEdad());
    }

    public static double integral(double a, double b, double h, Function<Double, Double> funcion) {
        double resultado = 0.0;
        for (double i = a; i < b; i += h) {
            resultado += funcion.apply(i) * h;
        }
        return resultado;
    }

    static class Persona {
        String nombre;
        LocalDate fechaDeNacimiento;

        public Persona(String nombre, LocalDate fechaDeNacimiento) {
            this.nombre = nombre;
            this.fechaDeNacimiento = fechaDeNacimiento;
        }

        public long calcularEdad() {
            return Period.between(fechaDeNacimiento, LocalDate.now()).getYears();
        }
    }

    static class Personas {
        List<Persona> listaPersonas = new ArrayList<>();

        public void annadirPersona(Persona persona) {
            listaPersonas.add(persona);
        }

        public Persona elMasJoven() {
            return listaPersonas.stream().min(Comparator.comparing(Persona::calcularEdad)).orElse(null);
        }

        public long calcularSumaEdades() {
            return listaPersonas.stream().mapToLong(Persona::calcularEdad).sum();
        }

        public long calcularEdadMinima() {
            return listaPersonas.stream().mapToLong(Persona::calcularEdad).min().orElse(0);
        }

        public double calcularMediaDeEdad() {
            return listaPersonas.stream().mapToLong(Persona::calcularEdad).average().orElse(0.0);
        }
    }
}
