import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ClienteCalculadora {
  public static void main(String[] args) {
    try {

      String IP_SERVIDOR = "127.0.0.1"; // endereco do servidor na rede
      int PORTA_SERVIDOR = 1099; // porta padrao

      // Localiza o registry. É possível usar endereço/IP porta
      Registry registry = LocateRegistry.getRegistry(IP_SERVIDOR,PORTA_SERVIDOR);
      // Consulta o registry e obtém o stub para o objeto remoto
      Calculadora calc = (Calculadora) registry
          .lookup("calculadora");
      // A partir deste momento, chamadas à Caluladora podem ser
      // feitas como qualquer chamada a métodos

      Scanner scan = new Scanner(System.in);
      int choice = -1;
      do {
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

        choice = scan.nextInt();

        ClienteCalculadora.nextMenu(choice);

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

  private static void nextMenu(int choice) {
    System.out.println(choice);
  }
}
