public class CuentaCorrienteDto{
    public int id;
    public double saldo;
    public int operaciones;
    public double giroDescubierto;

    public CuentaCorrienteDto(int id, double saldo, int operaciones, double giroDescubierto) {
        this.id = id;
        this.saldo = saldo;
        this.operaciones = operaciones;
        this.giroDescubierto = giroDescubierto;
    }
}