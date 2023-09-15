package ISO;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Conversoes {

    public static double converterDoubleCentavos(String s){

        int size = s.length();
        StringBuilder sb = new StringBuilder(s);
        double comPonto = Double.parseDouble(String.valueOf(sb.insert(size - 2, '.')));

        return comPonto;
    }

    public static String[] converterMensagemArray(String mensagem) {

        String[] partes = mensagem.split("\n");
        return partes;
    }
}
