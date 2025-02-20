import java.util.LinkedList;
import java.util.Queue;

public class Practica1 {

    // Clase que implementa Runnable para simular una impresora
    static class Impresora implements Runnable {
        private String nombre;
        private Queue<String> colaDeTrabajos;
        //crea una cola de trabajos para cada impresora
        public Impresora(String nombre) {
            this.nombre = nombre;
            this.colaDeTrabajos = new LinkedList<>();
        }

        // Método para agregar trabajos a la cola de la impresora
        public void agregarTrabajo(String trabajo) {
            colaDeTrabajos.add(trabajo);
        }

        @Override
        public void run() {
            //bucle que se ejecuta mientras haya trabajos en la cola
            while (!colaDeTrabajos.isEmpty()) {
                // Obtener el siguiente trabajo de la cola mediante poll(), que devuelve null si la cola está vacía
                String trabajo = colaDeTrabajos.poll(); // Obtener el siguiente trabajo
                System.out.println(nombre + " está procesando: " + trabajo);

                // Simular el tiempo de procesamiento del trabajo
                try {
                    Thread.sleep(2000); // 2 segundos por trabajo
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(nombre + " ha terminado: " + trabajo);
            }
            System.out.println(nombre + " no tiene más trabajos.");
        }
    }

    public static void main(String[] args) {
        // Crear dos impresoras (hilos)
        Impresora impresora1 = new Impresora("Impresora 1");
        Impresora impresora2 = new Impresora("Impresora 2");

        // Agregar trabajos a las impresoras
        impresora1.agregarTrabajo("Trabajo 1");
        impresora1.agregarTrabajo("Trabajo 2");
        impresora1.agregarTrabajo("Trabajo 3");

        impresora2.agregarTrabajo("Trabajo A");
        impresora2.agregarTrabajo("Trabajo B");

        // Iniciar las impresoras (hilos)
        Thread hiloImpresora1 = new Thread(impresora1);
        Thread hiloImpresora2 = new Thread(impresora2);
        //inicia los hilos
        hiloImpresora1.start();
        hiloImpresora2.start();
    }
    //metodo toString
    @Override
    public String toString() {
        return "Practica1 []";
    }
}