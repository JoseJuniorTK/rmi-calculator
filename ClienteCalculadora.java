import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClienteCalculadora {

  public static Scanner reader = new Scanner(System.in); // Leitor de entradas do usuario

  public static void main(String[] args) {

    try {

      String IP_SERVIDOR = "127.0.0.1"; // endereco do servidor na rede
      int PORTA_SERVIDOR = 1099; // porta padrao

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

  private static void showMainMenu() {
    System.out.println("MENU CALCULADORA");
    System.out.println("=====================================");
    System.out.println("1 - SOMA");
    System.out.println("2 - SUBTRACAO");
    System.out.println("3 - DIVISAO");
    System.out.println("4 - MULTIPLICACAO");
    System.out.println("5 - POTENCIA");
    System.out.println("6 - RAIZ QUADRADA");
    System.out.println("7 - PORCENTAGEM");
    System.out.println("8 - LOGARITMO");
    System.out.println("9 - CONVERSAO REAL -> DOLAR");
    System.out.println("10 - CONVERSAO DOLAR -> REAL");
    System.out.println("11 - CONVERSAO METROS -> KILOMETROS");
    System.out.println("12 - CONVERSAO KILOMETROS -> METROS");
    System.out.println("=====================================");
  }

  private static Numero[] genericOperationsMenu(String title, String firstInput, String secondInput) {
    System.out.println("SOMA");
    System.out.println("================");

    System.out.print("Digite o primeiro numero: ");
    Numero num1 = new NumeroImpl(reader.nextInt());

    System.out.print("Digite o segundo numero: ");
    Numero num2 = new NumeroImpl(reader.nextInt());
    System.out.println("================");

    Numero[] numbers = { num1, num2 };

    return numbers;
  }

  private static void calculate(int choice, Calculadora calc) {

    try {
      Numero[] numbers;
      Numero result;
      switch (choice) {
        case 1:
          numbers = genericOperationsMenu(
              "SOMA",
              "Digite o primeiro numero",
              "Digite o segundo numero");

          result = calc.soma(numbers[0], numbers[1]);
          System.out.println("Resultado da soma: " + result.getValor());
          waitForEnter();
          break;

        case 2:
          numbers = genericOperationsMenu(
              "SUBTRACAO",
              "Digite o primeiro numero",
              "Digite o segundo numero");

          result = calc.subtrai(numbers[0], numbers[1]);
          System.out.println("Resultado da subtracao: " + result.getValor());
          waitForEnter();
          break;

        case 3:
          System.out.println("DIVISAO");
          break;

        case 4:
          System.out.println("MULTIPLICACAO");
          break;

        case 5:
          System.out.println("POTENCIA");
          break;

        case 6:
          System.out.println("RAIZ QUADRADA");
          break;

        case 7:
          System.out.println("PORCENTAGEM");
          break;

        case 8:
          System.out.println("LOGARITMO");
          break;

        case 9:
          System.out.println("CONVERSAO REAL -> DOLAR");
          break;

        case 10:
          System.out.println("CONVERSAO DOLAR -> REAL");
          break;

        case 11:
          System.out.println("CONVERSAO METROS -> KILOMETROS");
          break;

        case 12:
          System.out.println("CONVERSAO KILOMETROS -> METROS ");
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
