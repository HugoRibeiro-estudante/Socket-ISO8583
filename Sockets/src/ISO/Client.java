package ISO;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;


public class Client {
    public static void main(String args[]) throws Exception {
        Socket conexao = new Socket("127.0.0.1", 2001);
        DataInputStream entrada = new DataInputStream(conexao.getInputStream());
        DataOutputStream saida = new DataOutputStream(conexao.getOutputStream());

        while (true) {

            Transacao transacao = new Transacao("200", "0512","104446", "040104","1","nulo","nulo");
            Cartao cartao = new Cartao("401231021845","Hugo R A",1000);
            //String mensagem = tratarDados(transacao, cartao);

            byte[] m_msgBytes_0200 = new byte[0];

            int escolhaCartao = Integer.parseInt(JOptionPane.showInputDialog("Valor da Transação : R$200 \nEscolha um cartão : \n 1) Saldo R$1.000 Debito \n 2) Saldo R$10 Credito \n 3) Cartão inexistente"));

            if(escolhaCartao == 1){
                m_msgBytes_0200 = new byte[]{
                        '0', '2', '0', '0', '\n', //tipo da mensagem - 200 é uma mensagem de requisição de transação financeira
                        '0', '0', '0', '0', '0', '0', '0', '0', '2', '0', '1', '0', '\n', // bit 4, valor em centavos - neste
                        '1', '0', '4', '4', '4', '6', '\n', // bit 12, hora local da transacao - 10:44:46
                        '0', '5', '1', '2', '\n', // bit 13, data da transacao 05/12
                        '0', '4', '0', '1', '0', '4', '\n', // bit 33, rede transmissora
                        '4', '0', '1', '2', '3', '1', '0', '2', '1', '8', '4', '5', '\n',// bit 62, número do cartão 4012-3102-1845
                        '1' // bit 62, forma de pagamento 1 - débito, 2 - crédito
                };
            }else if(escolhaCartao == 2){
                m_msgBytes_0200 = new byte[]{
                        '0', '2', '0', '0', '\n', //tipo da mensagem - 200 é uma mensagem de requisição de transação financeira
                        '0', '0', '0', '0', '0', '0', '0', '0', '2', '0', '1', '0', '\n', // bit 4, valor em centavos - neste
                        '1', '0', '4', '4', '4', '6', '\n', // bit 12, hora local da transacao - 10:44:46
                        '0', '5', '1', '2', '\n', // bit 13, data da transacao 05/12
                        '0', '4', '0', '1', '0', '4', '\n', // bit 33, rede transmissora
                        '1', '3', '2', '5', '4', '1', '4', '5', '2', '8', '8', '9', '\n',// bit 62, número do cartão 4012-3102-1845
                        '1' // bit 62, forma de pagamento 1 - débito, 2 - crédito
                };
            }
            else if(escolhaCartao == 3){
                m_msgBytes_0200 = new byte[]{
                        '0', '2', '0', '0', '\n', //tipo da mensagem - 200 é uma mensagem de requisição de transação financeira
                        '0', '0', '0', '0', '0', '0', '0', '0', '2', '0', '1', '0', '\n', // bit 4, valor em centavos - neste
                        '1', '0', '4', '4', '4', '6', '\n', // bit 12, hora local da transacao - 10:44:46
                        '0', '5', '1', '2', '\n', // bit 13, data da transacao 05/12
                        '0', '4', '0', '1', '0', '4', '\n', // bit 33, rede transmissora
                        '1', '3', '2', '5', '0', '1', '4', '5', '2', '8', '8', '9', '\n',// bit 62, número do cartão 4012-3102-1845
                        '1' // bit 62, forma de pagamento 1 - débito, 2 - crédito
                };
            }else{
                JOptionPane.showMessageDialog(null,"Digite uma opção válida");
                break;
            }

            String mensagem = new String(m_msgBytes_0200, StandardCharsets.UTF_8);
            saida.writeUTF(mensagem);
            //System.out.println(mensagem);
            String resposta = entrada.readUTF();

            exibeResposta(resposta);

            System.out.println(resposta);
            break;
        }

    }

    private static void exibeResposta(String resposta) {
        
        //System.out.println(resposta.length());

        String tipoMensagem = resposta.substring(0,4);
        String valor = resposta.substring(4,16);
        String hora = resposta.substring(16,22);
        String data = resposta.substring(22,26);
        String redeTransmissora = resposta.substring(26,32);
        String codigoResposta = resposta.substring(32,34);
        String nsu = resposta.substring(34);
        

        JOptionPane.showMessageDialog(null,
                "Tipo de Mensagem: " + tipoMensagem + "\n"
                        + "Valor: " + Conversoes.converterDoubleCentavos(valor) + "\n"
                        + "Hora: " + hora + "\n"
                        + "Data: " + data + "\n"
                        + "Rede Transmissora: " + redeTransmissora + "\n"
                        + "Código de Resposta: " + codigoResposta + "\n"
                        + "NSU: " + nsu
                );

    }

}

