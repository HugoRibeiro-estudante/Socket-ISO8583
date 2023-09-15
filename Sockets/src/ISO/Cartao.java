package ISO;

public class Cartao {

    private String numero, nomeCliente;
    private double saldo;

    public Cartao(String numero, String nomeCliente, double saldo) {
        this.numero = numero;
        this.nomeCliente = nomeCliente;
        this.saldo = saldo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public double getSaldo() {
        return saldo;
    }

    public  void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
