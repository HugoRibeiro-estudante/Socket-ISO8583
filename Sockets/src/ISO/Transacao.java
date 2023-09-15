package ISO;

public class Transacao {

    private String valor;
    private String data;
    private String hora;
    private String redeTransmissora;
    private String formaPagamento;
    private String NSU;
    private String codigoResposta;

    public Transacao(String valor, String data, String hora, String redeTransmissora, String formaPagamento, String NSU, String codigoResposta) {
        this.valor = valor;
        this.data = data;
        this.hora = hora;
        this.redeTransmissora = redeTransmissora;
        this.formaPagamento = formaPagamento;
        this.NSU = NSU;
        this.codigoResposta = codigoResposta;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getRedeTransmissora() {
        return redeTransmissora;
    }

    public void setRedeTransmissora(String redeTransmissora) {
        this.redeTransmissora = redeTransmissora;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getNSU() {
        return NSU;
    }

    public void setNSU(String NSU) {
        this.NSU = NSU;
    }

    public String getCodigoResposta() {
        return codigoResposta;
    }

    public void setCodigoResposta(String codigoResposta) {
        this.codigoResposta = codigoResposta;
    }
}
