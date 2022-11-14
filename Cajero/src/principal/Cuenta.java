package principal;

public class Cuenta {
    private int saldo;
    private int movimientos;
    private String historico = "";

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public int getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(int movimientos) {
        this.movimientos = movimientos;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public Cuenta(int saldo, int movimientos) {
        this.saldo = saldo;
        this.movimientos = movimientos;
    }

    public String hisMovimientos(String mov) {
        setHistorico(getHistorico() + "\n" + mov);
        return historico;
    }
}
