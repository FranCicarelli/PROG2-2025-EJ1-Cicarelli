public abstract class Cuenta {
    protected double saldo;
    protected int operaciones;
    protected int id;

    public Cuenta(int id) {
        this.id = id;
        this.saldo = 0;
        this.operaciones = 0;
    }

    public int getId() {
        return id;
    }
}