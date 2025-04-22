public class CajaDeAhorroDto{
    public int id;
    public double saldo;
    public int operaciones;

    public CajaDeAhorroDto(int id, double saldo, int operaciones) {
        this.id = id;
        this.saldo = saldo;
        this.operaciones = operaciones;
    }
}