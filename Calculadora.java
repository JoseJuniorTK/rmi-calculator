import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculadora extends Remote {
    public Numero somar(Numero a, Numero b)
            throws RemoteException;

    public Numero subtrair(Numero a, Numero b)
            throws RemoteException;

    public Numero multiplica(Numero a, Numero b)
            throws RemoteException;

    public Numero dividir(Numero numerador, Numero denominador)
            throws RemoteException, DivisaoPorZeroException;

    public Numero potencia(Numero base, Numero expoente)
            throws RemoteException, DivisaoPorZeroException;

    public Numero raizQuadrada(Numero a)
            throws RemoteException, DivisaoPorZeroException;

    public Numero dolarParaReal(Numero dolares)
            throws RemoteException, DivisaoPorZeroException;

    public Numero realParaDolar(Numero reais)
            throws RemoteException, DivisaoPorZeroException;

    public Numero metroParaKilometro(Numero metros)
            throws RemoteException, DivisaoPorZeroException;

    public Numero kilometroParaMetro(Numero kilometros)
            throws RemoteException, DivisaoPorZeroException;

    public Numero logaritmo(Numero metros)
            throws RemoteException, DivisaoPorZeroException;

    public Numero porcentualParaDecimal(Numero a)
            throws RemoteException, DivisaoPorZeroException;

    public Numero porcentagemDe(Numero a, Numero percentual)
            throws RemoteException, DivisaoPorZeroException;
}
