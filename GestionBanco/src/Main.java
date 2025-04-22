import java.util.Random;
import java.util.concurrent.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        LogicaCuenta logica = LogicaCuenta.getInstancia();
        Random random = new Random();
        int idCounter = 0;

        for (int i = 0; i < 10; i++) {
            Cuenta cuenta;
            if (random.nextBoolean()) {
                cuenta = new CajaDeAhorro(idCounter++);
            } else {
                double giro = 100 + random.nextDouble() * 400;
                cuenta = new CuentaCorriente(idCounter++, giro);
            }
            logica.agregarCuenta(cuenta);
            System.out.println("Cuenta creada: ID " + cuenta.getId() + " - Tipo: " + cuenta.getClass().getSimpleName());
        }

        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<Future<Boolean>> resultados = new ArrayList<>();

        for (int i = 0; i < 10_000; i++) {
            final int cuentaId = random.nextInt(10);
            final double monto = 10 + random.nextDouble() * 90;

            Callable<Boolean> tarea = random.nextBoolean()
                    ? () -> logica.agregarSaldo(cuentaId, monto)
                    : () -> logica.quitarSaldo(cuentaId, monto);

            resultados.add(executor.submit(tarea));
        }

        executor.shutdown();
        try {
            executor.awaitTermination(2, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            System.err.println("Interrumpido durante la espera.");
            Thread.currentThread().interrupt();
        }

        int totalOperaciones = 0;
        System.out.println("\nSaldo de cuentas:");
        for (int i = 0; i < 10; i++) {
            Object dto = logica.obtenerDto(i);
            if (dto instanceof CajaDeAhorroDto cajaDto) {
                System.out.println("\nCaja de Ahorro ID: " + cajaDto.id +
                        "\nSaldo: " + cajaDto.saldo +
                        "\nOperaciones: " + cajaDto.operaciones);
                totalOperaciones += cajaDto.operaciones;
            } else if (dto instanceof CuentaCorrienteDto corrienteDto) {
                System.out.println("\nCuenta Corriente ID: " + corrienteDto.id +
                        "\nSaldo: " + corrienteDto.saldo +
                        "\nOperaciones: " + corrienteDto.operaciones);
                totalOperaciones += corrienteDto.operaciones;
            } else {
                System.out.println("\nLa cuenta: " + i + " no existe.");
            }
        }
        System.out.println("\nOperaciones realizadas totales: " + totalOperaciones);
    }
}