import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Calculadora extends Remote {
        public Numero somar(Numero a, Numero b)
                        throws RemoteException;

        public Numero subtrair(Numero a, Numero b)
                        throws RemoteException;

        public Numero multiplicar(Numero a, Numero b)
                        throws RemoteException;

        public Numero dividir(Numero numerador, Numero denominador)
                        throws RemoteException, DivisaoPorZeroException;

        public Numero potencia(Numero base, Numero expoente)
                        throws RemoteException;

        public Numero raizQuadrada(Numero a)
                        throws RemoteException;

        public Numero dolarParaReal(Numero dolares)
                        throws RemoteException;

        public Numero realParaDolar(Numero reais)
                        throws RemoteException;

        public Numero metroParaKilometro(Numero metros)
                        throws RemoteException;

        public Numero kilometroParaMetro(Numero kilometros)
                        throws RemoteException;

        public Numero logaritmo(Numero logaritmando, Numero base)
                        throws RemoteException;

        public Numero porcentualParaDecimal(Numero a)
                        throws RemoteException;

        public Numero porcentagemDe(Numero a, Numero percentual)
                        throws RemoteException;
        public Numero restoDivisão(Numero a, Numero b)
                throws RemoteException;
        public Numero calculaSeno(Numero a)
                throws RemoteException;
        public Numero calculaCosseno(Numero a)
                throws RemoteException;
        public Numero calculaTangente(Numero a)
                throws RemoteException;
        public Numero calculaFatorial(Numero a)
                throws RemoteException;
}
