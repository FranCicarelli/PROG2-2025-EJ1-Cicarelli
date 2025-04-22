import java.util.ArrayList;
import java.util.List;

public class LogicaCuenta {
    private static LogicaCuenta instancia;
    private final List<Cuenta> cuentas;

    private LogicaCuenta() {
        cuentas = new ArrayList<>();
    }

    public static synchronized LogicaCuenta getInstancia() {
        if (instancia == null) {
            instancia = new LogicaCuenta();
        }
        return instancia;
    }

    public synchronized void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

    public synchronized boolean agregarSaldo(int cuentaId, double monto) {
        if (cuentaId >= 0 && cuentaId < cuentas.size()) {
            IGestionSaldo cuenta = (IGestionSaldo) cuentas.get(cuentaId);
            return cuenta.agregarSaldo(monto);
        }
        return false;
    }

    public synchronized boolean quitarSaldo(int cuentaId, double monto) {
        if (cuentaId >= 0 && cuentaId < cuentas.size()) {
            IGestionSaldo cuenta = (IGestionSaldo) cuentas.get(cuentaId);
            return cuenta.quitarSaldo(monto);
        }
        return false;
    }

    public synchronized Double consultarSaldo(int cuentaId) {
        if (cuentaId >= 0 && cuentaId < cuentas.size()) {
            IGestionSaldo cuenta = (IGestionSaldo) cuentas.get(cuentaId);
            return cuenta.getSaldo();
        }
        return null;
    }

    public synchronized int consultarOperaciones(int cuentaId) {
        if (cuentaId >= 0 && cuentaId < cuentas.size()) {
            IGestionSaldo cuenta = (IGestionSaldo) cuentas.get(cuentaId);
            return cuenta.getOperaciones();
        }
        return -1;
    }

    public synchronized Object obtenerDto(int cuentaId) {
        if (cuentaId >= 0 && cuentaId < cuentas.size()) {
            Cuenta cuenta = cuentas.get(cuentaId);
            if (cuenta instanceof CajaDeAhorro) {
                return ((CajaDeAhorro) cuenta).toDto();
            } else if (cuenta instanceof CuentaCorriente) {
                return ((CuentaCorriente) cuenta).toDto();
            }
        }
        return null;
    }
}
