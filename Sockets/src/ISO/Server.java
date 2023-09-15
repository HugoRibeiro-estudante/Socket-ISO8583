package ISO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;


public class Server {

    private static int nsu = 0; // Variável de instância para armazenar o NSU


    public static void main(String[] args) throws Exception {

        carregaCartoes();


        ServerSocket s = new ServerSocket(2001);
        Thread.currentThread().setName("ThreadServidor");
        while (true) {
            System.out.print("Esperando conectar...");
            Socket conexao = s.accept();
            System.out.println(" Conectou!");
            try (DataInputStream entrada = new DataInputStream(conexao.getInputStream())) {
                DataOutputStream saida = new DataOutputStream(conexao.getOutputStream());

                String mensagemDoCliente = entrada.readUTF();
                incrementaNsu();
                byte[] respostaParaCliente = processarMensagem(mensagemDoCliente, (nsu+""));

                saida.writeUTF(new String(respostaParaCliente, StandardCharsets.UTF_8));
                
                saida.flush();
                conexao.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized static void incrementaNsu() {
        nsu++;
    }

    private static void carregaCartoes() {
        CadastroCartao cadastroCartao = new CadastroCartao(); // Crie uma instância da classe CadastroCartao

        Cartao cartao1 = new Cartao("401231021845", "Hugo R A", 1000.0);
        Cartao cartao2 = new Cartao("123456789012", "João da Silva", 500.0);
        Cartao cartao3 = new Cartao("132541452889", "David V B", 10);

        CadastroCartao.adicionarCartao(cartao1);
        CadastroCartao.adicionarCartao(cartao2);
        CadastroCartao.adicionarCartao(cartao3);
    }

    private static byte[] processarMensagem(String mensagemDoCliente, String nsu) {

        String[] partes = Conversoes.converterMensagemArray(mensagemDoCliente);


        String tipoMensagem = partes[0];
        String valor = partes[1];
        String hora = partes[2];
        String data = partes[3];
        String redeTransmissora = partes[4];
        String numeroCartao = partes[5];
        String formaPagamento = partes[6];


        var valorConvertido = Conversoes.converterDoubleCentavos(valor);
        String nsuString = gerarNSU(nsu);

        Cartao cartao = CadastroCartao.obterCartao(numeroCartao);
        String codigoResposta;

        if(cartao == null){
            codigoResposta = "05";
            nsu = "0000000000000000";
        }else {
            var saldo = cartao.getSaldo();
            double valorTransacao = valorConvertido;


            if (saldo >= valorTransacao) {
                codigoResposta = "00";
                processarSaldo(saldo, valorTransacao, cartao);
                
            } else if (saldo < valorTransacao) {
                codigoResposta = "51";
                nsu = "0000000000000000";
            } else {
                codigoResposta = "91";
                nsu = "0000000000000000";
            }
        }

        // Construa a mensagem de resposta substituindo os valores
        String resposta = "0210" +
                valor +
                hora +
                data +
                redeTransmissora +
                codigoResposta +
                nsuString;

        byte[] respostaBytes = resposta.getBytes(StandardCharsets.UTF_8);
        //System.out.println(cartao.getSaldo());
        return respostaBytes;
    }

    private static synchronized String gerarNSU(String nsuStr){
        // long timestamp = System.currentTimeMillis(); // Obtém o timestamp atual
        // int random = (int) (Math.random() * 10000); // Gera um número aleatório
        // return timestamp +""+ random;
        
        int nZeros = nsuStr.length();
        nZeros = 12 - nZeros;
        String zeros = "";
        for(var i = 1; i <= nZeros; i++){
            zeros += "0";
        }
        nsuStr = zeros + nsuStr;
        
        return nsuStr;

    }

    private static synchronized void processarSaldo(double saldo, double valorTransacao, Cartao cartao){
        cartao.setSaldo(saldo - valorTransacao);
    }


}
