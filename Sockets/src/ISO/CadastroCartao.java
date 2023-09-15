package ISO;

import java.util.HashMap;
import java.util.Map;

public class CadastroCartao {
    private static Map<String, Cartao> cartoes;

    public CadastroCartao() {
        cartoes = new HashMap<>();
    }

    public static void adicionarCartao(Cartao cartao) {
        cartoes.put(cartao.getNumero(), cartao);
    }

    public static Cartao obterCartao(String numeroCartao) {
        return cartoes.get(numeroCartao);
    }
}
