public class CajaDeAhorro extends Cuenta implements IGestionSaldo {

    public CajaDeAhorro(int id) {
        super(id);
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
            if (saldo >= monto) {
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

    public CajaDeAhorroDto toDto() {
        return new CajaDeAhorroDto(id, saldo, operaciones);
    }
}