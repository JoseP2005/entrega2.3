package ejercicios;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.IntStream;

public class EjerciciosLambda {

    public static void main(String[] args) {
        // Ejercicio 13: Uso de lambda para calcular una integral
        System.out.println("Integral de x^2 (de 0 a 10): " + integral(0, 10, 0.01, x -> x * x));

        // Ejercicio 14: Generación de Streams de números
        IntStream.of(1, 2, 3, 4, 5).forEach(n -> System.out.println("Stream of: " + n));
        IntStream.range(1, 6).forEach(n -> System.out.println("Range: " + n));
        IntStream.iterate(0, n -> n + 2).limit(5).forEach(n -> System.out.println("Iterate: " + n));
        new Random().doubles(5, 0, 1).forEach(n -> System.out.println("Doubles: " + n));

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
