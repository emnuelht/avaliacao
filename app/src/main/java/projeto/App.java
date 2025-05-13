package projeto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class App {
    public static void main(String[] args) {
        // getSoma();
        // getFibonacci();
        // getCalculeDias();
        getFaturamentoMensal();
    }

    private static void getFaturamentoMensal() {
        double sp = 67836.43;
        double rj = 36678.66;
        double mg = 29229.88;
        double es = 27165.48;
        double outros = 19849.53;

        double somaTotal = sp + rj + mg + es + outros;

        System.out.printf("SP: %.2f%%\n", (sp/somaTotal) * 100);
        System.out.printf("RJ: %.2f%%\n", (rj/somaTotal) * 100);
        System.out.printf("MG: %.2f%%\n", (mg/somaTotal) * 100);
        System.out.printf("ES: %.2f%%\n", (es/somaTotal) * 100);
        System.out.printf("Outros: %.2f%%\n", (outros/somaTotal) * 100);
    }

    private static void getSoma() {
        int indice = 13;
        int k = 0;
        int soma = 0;
        while (k < indice) {
            k = k + 1;
            soma = soma + k;
        }
        System.out.println(soma);
    }

    private static void getCalculeDias() {
        try {
            FileReader file = new FileReader("src/main/resources/arquivo.json");
            JsonArray jsonArray = JsonParser.parseReader(file).getAsJsonArray();

            double valorMenor = 9999;
            double valorMaior = 0;
            int diaMenor = 0;
            int diaMaior = 0;
            double soma = 0;
            int qtd = 0;
            for (JsonElement element : jsonArray) {
                JsonObject object = element.getAsJsonObject();
                if (object.has("dia") && object.has("valor")) {
                    double iValor = object.get("valor").getAsDouble();
                    int iDia = object.get("dia").getAsInt();

                    if (iValor != 0 && iValor < valorMenor) {
                        valorMenor = iValor;
                        diaMenor = iDia;
                    }
                    if (iValor > valorMaior) {
                        valorMaior = iValor;
                        diaMaior = iDia;
                    }

                    if (iValor != 0) {
                        soma += iValor;
                        qtd++;
                    }
                }
            }

            double media = soma / qtd;

            for (JsonElement element : jsonArray) {
                JsonObject object = element.getAsJsonObject();
                if (object.has("dia") && object.has("valor")) {
                    double iValor = object.get("valor").getAsDouble();
                    int iDia = object.get("dia").getAsInt();

                    if (iValor > media) {
                        System.out.println("Acima da media - Dia: " + iDia + " | Valor: " + iValor);
                    }
                }
            }

            System.out.println("Menor valor - Dia: " + diaMenor + " | Valor: " + valorMenor);
            System.out.println("Maior valor - Dia: " + diaMaior + " | Valor: " + valorMaior);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void getFibonacci() {
        int length = 35;
        List<Integer> fibonacci = new ArrayList<>();
        int f1 = 0, f2 = 1;
        while (true) {
            int next = f1 + f2;
            if (next > length) break;
            fibonacci.add(next);
            f1 = f2;
            f2 = next;
        }

        for (int i = 0; i < length; i++) {
            if (fibonacci.contains(i)) {
                System.out.println("O numero '" + i + "' pertence a sequencia de Fibonacci");
            } else {
                System.out.println("O numero '" + i + "' nao pertence a sequencia de Fibonacci");
            }
        }
    }
}
