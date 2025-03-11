import java.io.*;
import java.net.*;
import java.util.Scanner;

public class GimnasioCliente {
    private static final String SERVIDOR = "localhost";
    private static final int PUERTO = 5000;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVIDOR, PUERTO);
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Cliente conectado al servidor de gimnasio.");
            
            while (true) {
                System.out.println("Ingresa tu entrenamiento (ejercicio, series, repeticiones, peso) o 'salir' para terminar:");
                System.out.print("Ejercicio: ");
                String ejercicio = scanner.nextLine();
                if (ejercicio.equalsIgnoreCase("salir")) {
                    salida.println("salir");
                    break;
                }
                System.out.print("Series: ");
                int series = scanner.nextInt();
                System.out.print("Repeticiones: ");
                int repeticiones = scanner.nextInt();
                System.out.print("Peso (kg): ");
                double peso = scanner.nextDouble();
                scanner.nextLine();  // Limpiar buffer

                String mensaje = ejercicio + " - " + series + "x" + repeticiones + " - " + peso + "kg";
                salida.println(mensaje);
                System.out.println("Enviando datos...");

                String respuesta = entrada.readLine();
                System.out.println("Respuesta del servidor: " + respuesta);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

