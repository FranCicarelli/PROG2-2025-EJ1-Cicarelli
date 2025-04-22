public class CuentaCorriente extends Cuenta implements IGestionSaldo {

    private double giroDescubierto;

    public CuentaCorriente(int id, double giroDescubierto) {
        super(id);
        this.giroDescubierto = giroDescubierto;
    }

    @Override
    public synchronized boolean agregarSaldo(double monto) {
        if (monto > 0) {
            saldo += monto;
            operaciones++;
            return true;
        }
        return false;
    }

    @Override
    public synchronized boolean quitarSaldo(double monto) {
        if (monto > 0) {
            if ((saldo - monto) >= giroDescubierto) {
                saldo -= monto;
                operaciones++;
                return true;
            }
        }
        return false;
    }

    @Override
    public synchronized double getSaldo() {
        return saldo;
    }

    @Override
    public synchronized int getOperaciones() {
        return operaciones;
    }

    public CuentaCorrienteDto toDto() {
        return new CuentaCorrienteDto(id, saldo, operaciones, giroDescubierto);
    }
}