import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ClienteCalculadora {

  public static Scanner reader = new Scanner(System.in); // Leitor de entradas do usuario

  public static void main(String[] args) {

    try {

      String IP_SERVIDOR = args[0]; // endereco do servidor na rede
      int PORTA_SERVIDOR = Integer.parseInt(args[1]); // porta padrao

      // Localiza o registry. É possível usar endereço/IP porta
      Registry registry = LocateRegistry.getRegistry(IP_SERVIDOR, PORTA_SERVIDOR);
      // Consulta o registry e obtém o stub para o objeto remoto
      Calculadora calc = (Calculadora) registry.lookup("calculadora");

      // A partir deste momento, chamadas à Caluladora podem ser
      // feitas como qualquer chamada a métodos

      int choice = -1;
      do {

        showMainMenu();
        choice = reader.nextInt();
        calculate(choice, calc);
        choice = -1;

      } while (choice != 0);
      /*
       * Numero num1 = new NumeroImpl(4.3);
       * Numero num2 = new NumeroImpl(4.5);
       * //Aqui são feitas diversas chamadas remotas
       * Numero soma = calc.soma(num1, num2);
       * Numero sub = calc.subtrai(num1, num2);
       * Numero mult = calc.multiplica(num1, num2);
       * Numero div = calc.divide(num1, num2);
       * System.out.println("Resultados obtidos do servidor:" +
       * "\n\t+:" + soma.getValor() +
       * "\n\t-:" + sub.getValor() +
       * "\n\t*:" + mult.getValor() +
       * "\n\t/:" + div.getValor());
       */

      /*
       * try {
       * calc.divide(new NumeroImpl(1), new NumeroImpl(0));
       * } catch (DivisaoPorZeroException e) {
       * System.out.println(
       * "Tentou dividir por zero! Esta é uma exceção remota.");
       * }
       * 
       */
    } catch (Exception e) {
      System.err.println("Ocorreu um erro no cliente: " +
          e.toString());
    }
  }

private static String makeApiRequest(String apiUrl) throws Exception {
    URL url = new URL(apiUrl);
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("GET");

    // Read the response
    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    StringBuilder responseBuilder = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
        responseBuilder.append(line);
    }
    reader.close();

    return responseBuilder.toString();
}


  private static void showMainMenu() {
    System.out.println("MENU CALCULADORA");
    System.out.println("=====================================");
    System.out.println("1 - SOMA");
    System.out.println("2 - SUBTRACAO");
    System.out.println("3 - DIVISAO");
    System.out.println("4 - MULTIPLICACAO");
    System.out.println("5 - POTENCIA");
    System.out.println("6 - RAIZ QUADRADA");
    System.out.println("7 - PORCENTAGEM RELATIVA");
    System.out.println("8 - LOGARITMO");
    System.out.println("9 - CONVERSAO REAL -> DOLAR");
    System.out.println("10 - CONVERSAO DOLAR -> REAL");
    System.out.println("11 - CONVERSAO METROS -> KILOMETROS");
    System.out.println("12 - CONVERSAO KILOMETROS -> METROS");
    System.out.println("13 - CONVERSAO PORCENTAGEM -> DECIMAL ");
    System.out.println("14 - RESTO DA DIVISÃO");
    System.out.println("15 - SENO");
    System.out.println("16 - COSSENO");
    System.out.println("17 - TANGENTE");
    System.out.println("18 - FATORIAL");
    System.out.println("19 - CONVERSAO KILOGRAMAS -> GRAMAS ");
    System.out.println("20 - CONVERSAO GRAMAS -> KILOGRAMAS ");
    System.out.println("21 - CONVERSAO CELSIUS -> FAHRENHEIT ");
    System.out.println("22 - CONVERSAO FAHRENHEIT -> CELSIUS ");
    System.out.println("23 - CONVERSAO LITROS -> MILILITROS ");
    System.out.println("24 - CONVERSAO MILILITROS -> LITROS ");
    System.out.println("25 - CONVERSAO HORAS -> MINUTOS ");
    System.out.println("26 - CONVERSAO MINUTOS -> HORAS ");
    System.out.println("27 - CONVERSAO CELSIUS -> KELVIN ");
    System.out.println("28 - CONVERSAO KELVIN -> CELSIUS ");
    System.out.println("=====================================");
  }

  private static Numero[] genericOperationsMenu(String title, String firstInput, String secondInput) {
    System.out.println(title);
    System.out.println("================");

    System.out.print(firstInput);
    Numero num1 = new NumeroImpl(reader.nextInt());
    Numero num2 = null;

    if (!secondInput.equals("")) {
      System.out.print(secondInput);
      num2 = new NumeroImpl(reader.nextInt());
      System.out.println("================");
    }

    Numero[] numbers = { num1, num2 };

    return numbers;
  }

  private static void calculate(int choice, Calculadora calc) {

    try {
      Numero[] numbers;
      Numero result;
      Scanner scanner = new Scanner(System.in);
      switch (choice) {
        case 1:
          numbers = genericOperationsMenu(
              "SOMA",
              "Digite o primeiro numero: ",
              "Digite o segundo numero: ");

          result = calc.somar(numbers[0], numbers[1]);
          System.out.println("Resultado da soma: " + result.getValor());
          System.out.println("Gostaria de saber a explicação do resultado? (Digite 'sim' ou 'nao')");
          String response = scanner.nextLine();

    if (response.equalsIgnoreCase("sim")) {
        try {
            String apiUrl = "http://152.67.42.101:8012/gptAPI?PerguntaString=" + "Me%20de%20somente%20a%20explicacao%20e%20nada%20mais%20detalhadamente%20o%20passo%20a%20passo%20da%20seguinte%20operacao:%20" + numbers[0].getValor() + "%20Somado%20" + numbers[1].getValor() + "%20%3D%20" + result.getValor();

            String jsonResponse = makeApiRequest(apiUrl);
            String formattedResponse = jsonResponse.replace("\\n", "\n");
            formattedResponse = formattedResponse.substring(1, formattedResponse.length() - 1);
            System.out.println(formattedResponse);
        } catch (Exception e) {
            System.out.println("Erro ao obter a explicação do resultado: " + e.getMessage());
        }
    }
            waitForEnter();
            break;

        case 2:
          numbers = genericOperationsMenu(
              "SUBTRACAO",
              "Digite o primeiro numero: ",
              "Digite o segundo numero: ");

          result = calc.subtrair(numbers[0], numbers[1]);
          System.out.println("Resultado da subtracao: " + result.getValor());
          System.out.println("Gostaria de saber a explicação do resultado? (Digite 'sim' ou 'nao')");
          String response2 = scanner.nextLine();

    if (response2.equalsIgnoreCase("sim")) {
        try {
            String apiUrl = "http://152.67.42.101:8012/gptAPI?PerguntaString=" + "Me%20de%20somente%20a%20explicacao%20e%20nada%20mais%20detalhadamente%20o%20passo%20a%20passo%20da%20seguinte%20operacao:%20" + numbers[0].getValor() + "%20Subtraido%20" + numbers[1].getValor() + "%20%3D%20" + result.getValor();

            String jsonResponse = makeApiRequest(apiUrl);
            String formattedResponse = jsonResponse.replace("\\n", "\n");
            formattedResponse = formattedResponse.substring(1, formattedResponse.length() - 1);
            System.out.println(formattedResponse);
        } catch (Exception e) {
            System.out.println("Erro ao obter a explicação do resultado: " + e.getMessage());
        }
    }
            waitForEnter();
            break;

        case 3:
          numbers = genericOperationsMenu(
              "DIVISAO",
              "Digite o numerador: ",
              "Digite o denominador: ");

          result = calc.dividir(numbers[0], numbers[1]);
          System.out.println("Resultado da divisao: " + result.getValor());
          System.out.println("Gostaria de saber a explicação do resultado? (Digite 'sim' ou 'nao')");
          String response3 = scanner.nextLine();

    if (response3.equalsIgnoreCase("sim")) {
        try {
            String apiUrl = "http://152.67.42.101:8012/gptAPI?PerguntaString=" + "Me%20de%20somente%20a%20explicacao%20e%20nada%20mais%20detalhadamente%20o%20passo%20a%20passo%20da%20seguinte%20operacao:%20" + numbers[0].getValor() + "%20Dividido%20" + numbers[1].getValor() + "%20%3D%20" + result.getValor();

            String jsonResponse = makeApiRequest(apiUrl);
            String formattedResponse = jsonResponse.replace("\\n", "\n");
            formattedResponse = formattedResponse.substring(1, formattedResponse.length() - 1);
            System.out.println(formattedResponse);
        } catch (Exception e) {
            System.out.println("Erro ao obter a explicação do resultado: " + e.getMessage());
        }
    }
            waitForEnter();
            break;

        case 4:
          numbers = genericOperationsMenu(
              "MULTIPLICACAO",
              "Digite o primeiro numero: ",
              "Digite o segundo numero: ");

          result = calc.multiplicar(numbers[0], numbers[1]);
          System.out.println("Resultado da multiplicacao: " + result.getValor());
          System.out.println("Gostaria de saber a explicação do resultado? (Digite 'sim' ou 'nao')");
          String response4 = scanner.nextLine();

    if (response4.equalsIgnoreCase("sim")) {
        try {
            String apiUrl = "http://152.67.42.101:8012/gptAPI?PerguntaString=" + "Me%20de%20somente%20a%20explicacao%20e%20nada%20mais%20detalhadamente%20o%20passo%20a%20passo%20da%20seguinte%20operacao:%20" + numbers[0].getValor() + "%20Multiplicado%20" + numbers[1].getValor() + "%20%3D%20" + result.getValor();

            String jsonResponse = makeApiRequest(apiUrl);
            String formattedResponse = jsonResponse.replace("\\n", "\n");
            formattedResponse = formattedResponse.substring(1, formattedResponse.length() - 1);
            System.out.println(formattedResponse);
        } catch (Exception e) {
            System.out.println("Erro ao obter a explicação do resultado: " + e.getMessage());
        }
    }
            waitForEnter();
            break;

        case 5:
          numbers = genericOperationsMenu(
              "POTENCIA",
              "Digite a base: ",
              "Digite o expoente: ");

          result = calc.potencia(numbers[0], numbers[1]);
          System.out.println("Resultado: " + result.getValor());
          System.out.println("Gostaria de saber a explicação do resultado? (Digite 'sim' ou 'nao')");
          String response5 = scanner.nextLine();

    if (response5.equalsIgnoreCase("sim")) {
        try {
            String apiUrl = "http://152.67.42.101:8012/gptAPI?PerguntaString=" + "Me%20de%20somente%20a%20explicacao%20e%20nada%20mais%20detalhadamente%20o%20passo%20a%20passo%20da%20seguinte%20operacao:%20" + numbers[0].getValor() + "%20Potencia%20com%20expoente%20=" + numbers[1].getValor() + "%20%3D%20" + result.getValor();

            String jsonResponse = makeApiRequest(apiUrl);
            String formattedResponse = jsonResponse.replace("\\n", "\n");
            formattedResponse = formattedResponse.substring(1, formattedResponse.length() - 1);
            System.out.println(formattedResponse);
        } catch (Exception e) {
            System.out.println("Erro ao obter a explicação do resultado: " + e.getMessage());
        }
    }
            waitForEnter();
            break;

        case 6:
          numbers = genericOperationsMenu(
              "RAIZ QUADRADA",
              "Digite o numero: ",
              "");

          result = calc.raizQuadrada(numbers[0]);
          System.out.println("Resultado: " + result.getValor());
          System.out.println("Gostaria de saber a explicação do resultado? (Digite 'sim' ou 'nao')");
          String response6 = scanner.nextLine();

    if (response6.equalsIgnoreCase("sim")) {
        try {
            String apiUrl = "http://152.67.42.101:8012/gptAPI?PerguntaString=" + "Me%20de%20somente%20a%20explicacao%20e%20nada%20mais%20detalhadamente%20o%20passo%20a%20passo%20da%20seguinte%20operacao:%20" + numbers[0].getValor() + "%20Raiz%20quadrada" + "%20%3D%20" + result.getValor();

            String jsonResponse = makeApiRequest(apiUrl);
            String formattedResponse = jsonResponse.replace("\\n", "\n");
            formattedResponse = formattedResponse.substring(1, formattedResponse.length() - 1);
            System.out.println(formattedResponse);
        } catch (Exception e) {
            System.out.println("Erro ao obter a explicação do resultado: " + e.getMessage());
        }
    }
            waitForEnter();
            break;

        case 7:
          numbers = genericOperationsMenu(
              "PORCENTAGEM RELATIVA",
              "Digite o numero: ",
              "Digite a percentual: ");

          result = calc.porcentagemDe(numbers[0], numbers[1]);
          System.out.println(numbers[1].getValor() + "% de " + numbers[0].getValor() + " eh: " + result.getValor());
          System.out.println("Gostaria de saber a explicação do resultado? (Digite 'sim' ou 'nao')");
          String response7 = scanner.nextLine();

    if (response7.equalsIgnoreCase("sim")) {
        try {
            String apiUrl = "http://152.67.42.101:8012/gptAPI?PerguntaString=" + "Me%20de%20somente%20a%20explicacao%20e%20nada%20mais%20detalhadamente%20o%20passo%20a%20passo%20da%20seguinte%20operacao:%20" + numbers[0].getValor() + "com%20tirando%20percentual%20de" + numbers[1].getValor() + "%20%3D%20" + result.getValor();

            String jsonResponse = makeApiRequest(apiUrl);
            String formattedResponse = jsonResponse.replace("\\n", "\n");
            formattedResponse = formattedResponse.substring(1, formattedResponse.length() - 1);
            System.out.println(formattedResponse);
        } catch (Exception e) {
            System.out.println("Erro ao obter a explicação do resultado: " + e.getMessage());
        }
    }
            waitForEnter();
            break;

        case 8:
          numbers = genericOperationsMenu(
              "LOGARITMO",
              "Digite a: ",
              "Digite b: ");

          result = calc.logaritmo(numbers[0], numbers[1]);
          System.out.println("Resultado: " + result.getValor());
          System.out.println("Gostaria de saber a explicação do resultado? (Digite 'sim' ou 'nao')");
          String response8 = scanner.nextLine();

    if (response8.equalsIgnoreCase("sim")) {
        try {
            String apiUrl = "http://152.67.42.101:8012/gptAPI?PerguntaString=" + "Me%20de%20somente%20a%20explicacao%20e%20nada%20mais%20detalhadamente%20o%20passo%20a%20passo%20da%20seguinte%20operacao:%20" + numbers[0].getValor() + "%logaritmo%20de" + numbers[1].getValor() + "%20%3D%20" + result.getValor();

            String jsonResponse = makeApiRequest(apiUrl);
            String formattedResponse = jsonResponse.replace("\\n", "\n");
            formattedResponse = formattedResponse.substring(1, formattedResponse.length() - 1);
            System.out.println(formattedResponse);
        } catch (Exception e) {
            System.out.println("Erro ao obter a explicação do resultado: " + e.getMessage());
        }
    }
            waitForEnter();
            break;

        case 9:
          numbers = genericOperationsMenu(
              "CONVERSAO REAL -> DOLAR",
              "Quantidade de reais: ",
              "");

          result = calc.realParaDolar(numbers[0]);
          System.out.println("Resultado: " + result.getValor());
          System.out.println("Gostaria de saber a explicação do resultado? (Digite 'sim' ou 'nao')");
          String response9 = scanner.nextLine();

    if (response9.equalsIgnoreCase("sim")) {
        try {
            String apiUrl = "http://152.67.42.101:8012/gptAPI?PerguntaString=" + "Me%20de%20somente%20a%20explicacao%20e%20nada%20mais%20detalhadamente%20o%20passo%20a%20passo%20da%20seguinte%20operacao:%20" + numbers[0].getValor() + "%20Reais%20Convertido%20para%20dolar" + "%20%3D%20" + result.getValor();

            String jsonResponse = makeApiRequest(apiUrl);
            String formattedResponse = jsonResponse.replace("\\n", "\n");
            formattedResponse = formattedResponse.substring(1, formattedResponse.length() - 1);
            System.out.println(formattedResponse);
        } catch (Exception e) {
            System.out.println("Erro ao obter a explicação do resultado: " + e.getMessage());
        }
    }
            waitForEnter();
            break;

        case 10:
          numbers = genericOperationsMenu(
              "CONVERSAO DOLAR -> REAL",
              "Quantidade de dolares: ",
              "");

          result = calc.dolarParaReal(numbers[0]);
          System.out.println("Resultado: " + result.getValor());
          System.out.println("Gostaria de saber a explicação do resultado? (Digite 'sim' ou 'nao')");
          String response10 = scanner.nextLine();

    if (response10.equalsIgnoreCase("sim")) {
        try {
            String apiUrl = "http://152.67.42.101:8012/gptAPI?PerguntaString=" + "Me%20de%20somente%20a%20explicacao%20e%20nada%20mais%20detalhadamente%20o%20passo%20a%20passo%20da%20seguinte%20operacao:%20" + numbers[0].getValor() + "%20dolares%20Convertido%20para%20real" + "%20%3D%20" + result.getValor();

            String jsonResponse = makeApiRequest(apiUrl);
            String formattedResponse = jsonResponse.replace("\\n", "\n");
            formattedResponse = formattedResponse.substring(1, formattedResponse.length() - 1);
            System.out.println(formattedResponse);
        } catch (Exception e) {
            System.out.println("Erro ao obter a explicação do resultado: " + e.getMessage());
        }
    }
            waitForEnter();
            break;

        case 11:
           numbers = genericOperationsMenu(
              "CONVERSAO METROS -> KILOMETROS",
              "Metros: ",
              "");

          result = calc.metroParaKilometro(numbers[0]);
          System.out.println("Resultado: " + result.getValor());
          System.out.println("Gostaria de saber a explicação do resultado? (Digite 'sim' ou 'nao')");
          String response11 = scanner.nextLine();

    if (response11.equalsIgnoreCase("sim")) {
        try {
            String apiUrl = "http://152.67.42.101:8012/gptAPI?PerguntaString=" + "Me%20de%20somente%20a%20explicacao%20e%20nada%20mais%20detalhadamente%20o%20passo%20a%20passo%20da%20seguinte%20operacao:%20" + numbers[0].getValor() + "%20metros%20Convertido%20para%20kilometros" + "%20%3D%20" + result.getValor();

            String jsonResponse = makeApiRequest(apiUrl);
            String formattedResponse = jsonResponse.replace("\\n", "\n");
            formattedResponse = formattedResponse.substring(1, formattedResponse.length() - 1);
            System.out.println(formattedResponse);
        } catch (Exception e) {
            System.out.println("Erro ao obter a explicação do resultado: " + e.getMessage());
        }
    }
            waitForEnter();
            break;

        case 12:
          numbers = genericOperationsMenu(
              "CONVERSAO KILOMETROS -> METROS ",
              "Kilometros: ",
              "");

          result = calc.kilometroParaMetro(numbers[0]);
          System.out.println("Resultado: " + result.getValor());
          System.out.println("Gostaria de saber a explicação do resultado? (Digite 'sim' ou 'nao')");
          String response12 = scanner.nextLine();

    if (response12.equalsIgnoreCase("sim")) {
        try {
            String apiUrl = "http://152.67.42.101:8012/gptAPI?PerguntaString=" + "Me%20de%20somente%20a%20explicacao%20e%20nada%20mais%20detalhadamente%20o%20passo%20a%20passo%20da%20seguinte%20operacao:%20" + numbers[0].getValor() + "%20kilometros%20Convertido%20para%20metros" + "%20%3D%20" + result.getValor();

            String jsonResponse = makeApiRequest(apiUrl);
            String formattedResponse = jsonResponse.replace("\\n", "\n");
            formattedResponse = formattedResponse.substring(1, formattedResponse.length() - 1);
            System.out.println(formattedResponse);
        } catch (Exception e) {
            System.out.println("Erro ao obter a explicação do resultado: " + e.getMessage());
        }
    }
            waitForEnter();
            break;

        case 13:
          System.out.println();
          numbers = genericOperationsMenu(
              "CONVERSAO PORCENTAGEM EM DECIMAL ",
              "Percentual: ",
              "");

          result = calc.porcentualParaDecimal(numbers[0]);
          System.out.println("Resultado: " + result.getValor());
          System.out.println("Gostaria de saber a explicação do resultado? (Digite 'sim' ou 'nao')");
          String response13 = scanner.nextLine();

    if (response13.equalsIgnoreCase("sim")) {
        try {
            String apiUrl = "http://152.67.42.101:8012/gptAPI?PerguntaString=" + "Me%20de%20somente%20a%20explicacao%20e%20nada%20mais%20detalhadamente%20o%20passo%20a%20passo%20da%20seguinte%20operacao:%20" + numbers[0].getValor() + "%20percentual%20Convertido%20para%20decimal" + "%20%3D%20" + result.getValor();

            String jsonResponse = makeApiRequest(apiUrl);
            String formattedResponse = jsonResponse.replace("\\n", "\n");
            formattedResponse = formattedResponse.substring(1, formattedResponse.length() - 1);
            System.out.println(formattedResponse);
        } catch (Exception e) {
            System.out.println("Erro ao obter a explicação do resultado: " + e.getMessage());
        }
    }
            waitForEnter();
            break;

        case 14:
          System.out.println();
          numbers = genericOperationsMenu(
                  "RESTO DA DIVISAO",
                  "Digite o numerador: ",
                  "Digite o denominador: ");

          result = calc.restoDivisão(numbers[0], numbers[1]);
          System.out.println("Resto da divisao: " + result.getValor());
          System.out.println("Gostaria de saber a explicação do resultado? (Digite 'sim' ou 'nao')");
          String response14 = scanner.nextLine();

    if (response14.equalsIgnoreCase("sim")) {
        try {
            String apiUrl = "http://152.67.42.101:8012/gptAPI?PerguntaString=" + "Me%20de%20somente%20a%20explicacao%20e%20nada%20mais%20detalhadamente%20o%20passo%20a%20passo%20da%20seguinte%20operacao:%20" + numbers[0].getValor() + "%20Resto%20Da%20Divisao%20" + numbers[1].getValor() + "%20%3D%20" + result.getValor();

            String jsonResponse = makeApiRequest(apiUrl);
            String formattedResponse = jsonResponse.replace("\\n", "\n");
            formattedResponse = formattedResponse.substring(1, formattedResponse.length() - 1);
            System.out.println(formattedResponse);
        } catch (Exception e) {
            System.out.println("Erro ao obter a explicação do resultado: " + e.getMessage());
        }
    }
            waitForEnter();
            break;
            
        case 15:
          System.out.println();
          numbers = genericOperationsMenu(
                  "SENO ",
                  "Ângulo em graus: ",
                  "");

          result = calc.calculaSeno(numbers[0]);
          System.out.println("Resultado: " + result.getValor());
          System.out.println("Gostaria de saber a explicação do resultado? (Digite 'sim' ou 'nao')");
          String response15 = scanner.nextLine();

    if (response15.equalsIgnoreCase("sim")) {
        try {
            String apiUrl = "http://152.67.42.101:8012/gptAPI?PerguntaString=" + "Me%20de%20somente%20a%20explicacao%20e%20nada%20mais%20detalhadamente%20o%20passo%20a%20passo%20da%20seguinte%20operacao:%20" + numbers[0].getValor() + "%20Graus%20De%20Seno" + "%20%3D%20" + result.getValor();

            String jsonResponse = makeApiRequest(apiUrl);
            String formattedResponse = jsonResponse.replace("\\n", "\n");
            formattedResponse = formattedResponse.substring(1, formattedResponse.length() - 1);
            System.out.println(formattedResponse);
        } catch (Exception e) {
            System.out.println("Erro ao obter a explicação do resultado: " + e.getMessage());
        }
    }
            waitForEnter();
            break;

        case 16:
          System.out.println();
          numbers = genericOperationsMenu(
                  "COSSENO ",
                  "Ângulo em graus: ",
                  "");

          result = calc.calculaCosseno(numbers[0]);
          System.out.println("Resultado: " + result.getValor());
          System.out.println("Gostaria de saber a explicação do resultado? (Digite 'sim' ou 'nao')");
          String response16 = scanner.nextLine();

    if (response16.equalsIgnoreCase("sim")) {
        try {
            String apiUrl = "http://152.67.42.101:8012/gptAPI?PerguntaString=" + "Me%20de%20somente%20a%20explicacao%20e%20nada%20mais%20detalhadamente%20o%20passo%20a%20passo%20da%20seguinte%20operacao:%20" + numbers[0].getValor() + "%20Graus%20De%20Cosseno" + "%20%3D%20" + result.getValor();

            String jsonResponse = makeApiRequest(apiUrl);
            String formattedResponse = jsonResponse.replace("\\n", "\n");
            formattedResponse = formattedResponse.substring(1, formattedResponse.length() - 1);
            System.out.println(formattedResponse);
        } catch (Exception e) {
            System.out.println("Erro ao obter a explicação do resultado: " + e.getMessage());
        }
    }
            waitForEnter();
            break;

        case 17:
          System.out.println();
          numbers = genericOperationsMenu(
                  "TANGENTE ",
                  "Ângulo em graus: ",
                  "");

          result = calc.calculaTangente(numbers[0]);
          System.out.println("Resultado: " + result.getValor());
          System.out.println("Gostaria de saber a explicação do resultado? (Digite 'sim' ou 'nao')");
          String response17 = scanner.nextLine();

    if (response17.equalsIgnoreCase("sim")) {
        try {
            String apiUrl = "http://152.67.42.101:8012/gptAPI?PerguntaString=" + "Me%20de%20somente%20a%20explicacao%20e%20nada%20mais%20detalhadamente%20o%20passo%20a%20passo%20da%20seguinte%20operacao:%20" + numbers[0].getValor() + "%20Graus%20De%20Tangente" + "%20%3D%20" + result.getValor();

            String jsonResponse = makeApiRequest(apiUrl);
            String formattedResponse = jsonResponse.replace("\\n", "\n");
            formattedResponse = formattedResponse.substring(1, formattedResponse.length() - 1);
            System.out.println(formattedResponse);
        } catch (Exception e) {
            System.out.println("Erro ao obter a explicação do resultado: " + e.getMessage());
        }
    }
            waitForEnter();
            break;

        case 18:
          System.out.println();
          numbers = genericOperationsMenu(
                  "FATORIAL ",
                  "Número inteiro: ",
                  "");

          result = calc.calculaFatorial(numbers[0]);
          System.out.println("Resultado: " + result.getValor());
          System.out.println("Gostaria de saber a explicação do resultado? (Digite 'sim' ou 'nao')");
          String response18 = scanner.nextLine();

    if (response18.equalsIgnoreCase("sim")) {
        try {
            String apiUrl = "http://152.67.42.101:8012/gptAPI?PerguntaString=" + "Me%20de%20somente%20a%20explicacao%20e%20nada%20mais%20detalhadamente%20o%20passo%20a%20passo%20da%20seguinte%20operacao:%20" + numbers[0].getValor() + "%20Fatorial" + "%20%3D%20" + result.getValor();

            String jsonResponse = makeApiRequest(apiUrl);
            String formattedResponse = jsonResponse.replace("\\n", "\n");
            formattedResponse = formattedResponse.substring(1, formattedResponse.length() - 1);
            System.out.println(formattedResponse);
        } catch (Exception e) {
            System.out.println("Erro ao obter a explicação do resultado: " + e.getMessage());
        }
    }
            waitForEnter();
            break;

          case 19:
                numbers = genericOperationsMenu(
                        "KILOGRAMA -> GRAMA",
                        "KILOgramas: ",
                        "");

                result = calc.kilogramaParaGrama(numbers[0]);
                System.out.println("Resultado: " + result.getValor());
          System.out.println("Gostaria de saber a explicação do resultado? (Digite 'sim' ou 'nao')");
          String response19 = scanner.nextLine();

    if (response19.equalsIgnoreCase("sim")) {
        try {
            String apiUrl = "http://152.67.42.101:8012/gptAPI?PerguntaString=" + "Me%20de%20somente%20a%20explicacao%20e%20nada%20mais%20detalhadamente%20o%20passo%20a%20passo%20da%20seguinte%20operacao:%20" + numbers[0].getValor() + "%20Kilogramas%20Convertido%20para%20gramas" + "%20%3D%20" + result.getValor();

            String jsonResponse = makeApiRequest(apiUrl);
            String formattedResponse = jsonResponse.replace("\\n", "\n");
            formattedResponse = formattedResponse.substring(1, formattedResponse.length() - 1);
            System.out.println(formattedResponse);
        } catch (Exception e) {
            System.out.println("Erro ao obter a explicação do resultado: " + e.getMessage());
        }
    }
            waitForEnter();
            break;

           case 20:
                numbers = genericOperationsMenu(
                        "GRAMA -> KILOGRAMA",
                        "Gramas: ",
                        "");

                result = calc.gramaParaKilograma(numbers[0]);
                System.out.println("Resultado: " + result.getValor());
          System.out.println("Gostaria de saber a explicação do resultado? (Digite 'sim' ou 'nao')");
          String response20 = scanner.nextLine();

    if (response20.equalsIgnoreCase("sim")) {
        try {
            String apiUrl = "http://152.67.42.101:8012/gptAPI?PerguntaString=" + "Me%20de%20somente%20a%20explicacao%20e%20nada%20mais%20detalhadamente%20o%20passo%20a%20passo%20da%20seguinte%20operacao:%20" + numbers[0].getValor() + "%20Gramas%20Convertido%20para%20Kilogramas" + "%20%3D%20" + result.getValor();

            String jsonResponse = makeApiRequest(apiUrl);
            String formattedResponse = jsonResponse.replace("\\n", "\n");
            formattedResponse = formattedResponse.substring(1, formattedResponse.length() - 1);
            System.out.println(formattedResponse);
        } catch (Exception e) {
            System.out.println("Erro ao obter a explicação do resultado: " + e.getMessage());
        }
    }
            waitForEnter();
            break;

            case 21:
                numbers = genericOperationsMenu(
                        "CELSIUS -> FAHRENHEIT",
                        "Graus Celsius: ",
                        "");

                result = calc.celsiusParaFahrenheit(numbers[0]);
                System.out.println("Resultado: " + result.getValor());
          System.out.println("Gostaria de saber a explicação do resultado? (Digite 'sim' ou 'nao')");
          String response21 = scanner.nextLine();

    if (response21.equalsIgnoreCase("sim")) {
        try {
            String apiUrl = "http://152.67.42.101:8012/gptAPI?PerguntaString=" + "Me%20de%20somente%20a%20explicacao%20e%20nada%20mais%20detalhadamente%20o%20passo%20a%20passo%20da%20seguinte%20operacao:%20" + numbers[0].getValor() + "%20CELSIUS%20Convertido%20para%20FAHRENHEIT" + "%20%3D%20" + result.getValor();

            String jsonResponse = makeApiRequest(apiUrl);
            String formattedResponse = jsonResponse.replace("\\n", "\n");
            formattedResponse = formattedResponse.substring(1, formattedResponse.length() - 1);
            System.out.println(formattedResponse);
        } catch (Exception e) {
            System.out.println("Erro ao obter a explicação do resultado: " + e.getMessage());
        }
    }
            waitForEnter();
            break;

            case 22:
                numbers = genericOperationsMenu(
                        "FAHRENHEIT -> CELSIUS",
                        "Graus Fahrenheit: ",
                        "");

                result = calc.fahrenheitParaCelsius(numbers[0]);
                System.out.println("Resultado: " + result.getValor());
          System.out.println("Gostaria de saber a explicação do resultado? (Digite 'sim' ou 'nao')");
          String response22 = scanner.nextLine();

    if (response22.equalsIgnoreCase("sim")) {
        try {
            String apiUrl = "http://152.67.42.101:8012/gptAPI?PerguntaString=" + "Me%20de%20somente%20a%20explicacao%20e%20nada%20mais%20detalhadamente%20o%20passo%20a%20passo%20da%20seguinte%20operacao:%20" + numbers[0].getValor() + "%20FAHRENHEIT%20Convertido%20para%20CELSIUS" + "%20%3D%20" + result.getValor();

            String jsonResponse = makeApiRequest(apiUrl);
            String formattedResponse = jsonResponse.replace("\\n", "\n");
            formattedResponse = formattedResponse.substring(1, formattedResponse.length() - 1);
            System.out.println(formattedResponse);
        } catch (Exception e) {
            System.out.println("Erro ao obter a explicação do resultado: " + e.getMessage());
        }
    }
            waitForEnter();
            break;

            case 23:
                numbers = genericOperationsMenu(
                        "LITROS -> MILILITROS",
                        "Litros: ",
                        "");

                result = calc.litrosParaMililitros(numbers[0]);
                System.out.println("Resultado: " + result.getValor());
          System.out.println("Gostaria de saber a explicação do resultado? (Digite 'sim' ou 'nao')");
          String response23 = scanner.nextLine();

    if (response23.equalsIgnoreCase("sim")) {
        try {
            String apiUrl = "http://152.67.42.101:8012/gptAPI?PerguntaString=" + "Me%20de%20somente%20a%20explicacao%20e%20nada%20mais%20detalhadamente%20o%20passo%20a%20passo%20da%20seguinte%20operacao:%20" + numbers[0].getValor() + "%20litros%20Convertido%20para%20mililitros" + "%20%3D%20" + result.getValor();

            String jsonResponse = makeApiRequest(apiUrl);
            String formattedResponse = jsonResponse.replace("\\n", "\n");
            formattedResponse = formattedResponse.substring(1, formattedResponse.length() - 1);
            System.out.println(formattedResponse);
        } catch (Exception e) {
            System.out.println("Erro ao obter a explicação do resultado: " + e.getMessage());
        }
    }
            waitForEnter();
            break;

            case 24:
                numbers = genericOperationsMenu(
                        "MILILITROS -> LITROS",
                        "Mililitros: ",
                        "");

                result = calc.mililitrosParaLitros(numbers[0]);
                System.out.println("Resultado: " + result.getValor());
          System.out.println("Gostaria de saber a explicação do resultado? (Digite 'sim' ou 'nao')");
          String response24 = scanner.nextLine();

    if (response24.equalsIgnoreCase("sim")) {
        try {
            String apiUrl = "http://152.67.42.101:8012/gptAPI?PerguntaString=" + "Me%20de%20somente%20a%20explicacao%20e%20nada%20mais%20detalhadamente%20o%20passo%20a%20passo%20da%20seguinte%20operacao:%20" + numbers[0].getValor() + "%20mililitros%20Convertido%20para%20litros" + "%20%3D%20" + result.getValor();

            String jsonResponse = makeApiRequest(apiUrl);
            String formattedResponse = jsonResponse.replace("\\n", "\n");
            formattedResponse = formattedResponse.substring(1, formattedResponse.length() - 1);
            System.out.println(formattedResponse);
        } catch (Exception e) {
            System.out.println("Erro ao obter a explicação do resultado: " + e.getMessage());
        }
    }
            waitForEnter();
            break;

            case 25:
                numbers = genericOperationsMenu(
                        "HORAS -> MINUTOS",
                        "Horas: ",
                        "");

                result = calc.horasParaMinutos(numbers[0]);
                System.out.println("Resultado: " + result.getValor());
          System.out.println("Gostaria de saber a explicação do resultado? (Digite 'sim' ou 'nao')");
          String response25 = scanner.nextLine();

    if (response25.equalsIgnoreCase("sim")) {
        try {
            String apiUrl = "http://152.67.42.101:8012/gptAPI?PerguntaString=" + "Me%20de%20somente%20a%20explicacao%20e%20nada%20mais%20detalhadamente%20o%20passo%20a%20passo%20da%20seguinte%20operacao:%20" + numbers[0].getValor() + "%20horas%20Convertido%20para%20minutos" + "%20%3D%20" + result.getValor();

            String jsonResponse = makeApiRequest(apiUrl);
            String formattedResponse = jsonResponse.replace("\\n", "\n");
            formattedResponse = formattedResponse.substring(1, formattedResponse.length() - 1);
            System.out.println(formattedResponse);
        } catch (Exception e) {
            System.out.println("Erro ao obter a explicação do resultado: " + e.getMessage());
        }
    }
            waitForEnter();
            break;

            case 26:
                numbers = genericOperationsMenu(
                        "MINUTOS -> HORAS",
                        "Minutos: ",
                        "");

                result = calc.minutosParaHoras(numbers[0]);
                System.out.println("Resultado: " + result.getValor());
          System.out.println("Gostaria de saber a explicação do resultado? (Digite 'sim' ou 'nao')");
          String response26 = scanner.nextLine();

    if (response26.equalsIgnoreCase("sim")) {
        try {
            String apiUrl = "http://152.67.42.101:8012/gptAPI?PerguntaString=" + "Me%20de%20somente%20a%20explicacao%20e%20nada%20mais%20detalhadamente%20o%20passo%20a%20passo%20da%20seguinte%20operacao:%20" + numbers[0].getValor() + "%20minutos%20Convertido%20para%20horas" + "%20%3D%20" + result.getValor();

            String jsonResponse = makeApiRequest(apiUrl);
            String formattedResponse = jsonResponse.replace("\\n", "\n");
            formattedResponse = formattedResponse.substring(1, formattedResponse.length() - 1);
            System.out.println(formattedResponse);
        } catch (Exception e) {
            System.out.println("Erro ao obter a explicação do resultado: " + e.getMessage());
        }
    }
            waitForEnter();
            break;
            
            case 27:
                numbers = genericOperationsMenu(
                        "CELSIUS -> KELVIN",
                        "Graus Celsius: ",
                        "");

                result = calc.celsiusParaKelvin(numbers[0]);
                System.out.println("Resultado: " + result.getValor());
          System.out.println("Gostaria de saber a explicação do resultado? (Digite 'sim' ou 'nao')");
          String response27 = scanner.nextLine();

    if (response27.equalsIgnoreCase("sim")) {
        try {
            String apiUrl = "http://152.67.42.101:8012/gptAPI?PerguntaString=" + "Me%20de%20somente%20a%20explicacao%20e%20nada%20mais%20detalhadamente%20o%20passo%20a%20passo%20da%20seguinte%20operacao:%20" + numbers[0].getValor() + "%20celsius%20Convertido%20para%20kelvin" + "%20%3D%20" + result.getValor();

            String jsonResponse = makeApiRequest(apiUrl);
            String formattedResponse = jsonResponse.replace("\\n", "\n");
            formattedResponse = formattedResponse.substring(1, formattedResponse.length() - 1);
            System.out.println(formattedResponse);
        } catch (Exception e) {
            System.out.println("Erro ao obter a explicação do resultado: " + e.getMessage());
        }
    }
            waitForEnter();
            break;

            case 28:
                numbers = genericOperationsMenu(
                        "KELVIN -> CELSIUS",
                        "Kelvin: ",
                        "");

                result = calc.kelvinParaCelsius(numbers[0]);
                System.out.println("Resultado: " + result.getValor());
          System.out.println("Gostaria de saber a explicação do resultado? (Digite 'sim' ou 'nao')");
          String response28 = scanner.nextLine();

    if (response28.equalsIgnoreCase("sim")) {
        try {
            String apiUrl = "http://152.67.42.101:8012/gptAPI?PerguntaString=" + "Me%20de%20somente%20a%20explicacao%20e%20nada%20mais%20detalhadamente%20o%20passo%20a%20passo%20da%20seguinte%20operacao:%20" + numbers[0].getValor() + "%20kelvin%20Convertido%20para%20celsius" + "%20%3D%20" + result.getValor();

            String jsonResponse = makeApiRequest(apiUrl);
            String formattedResponse = jsonResponse.replace("\\n", "\n");
            formattedResponse = formattedResponse.substring(1, formattedResponse.length() - 1);
            System.out.println(formattedResponse);
        } catch (Exception e) {
            System.out.println("Erro ao obter a explicação do resultado: " + e.getMessage());
        }
    }
            waitForEnter();
            break;


      }

    } catch (RemoteException e) {
      System.out.println("Ocorreu um erro ao tentar se comunicar com o servidor. " + e.toString());
    }
  }

  public static void waitForEnter() {
    System.out.println("\nPressione enter para continuar...");
    reader.nextLine();
    reader.nextLine();
  }

}
