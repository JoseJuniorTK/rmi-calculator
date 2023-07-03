import java.rmi.RemoteException;

public class CalculadoraImpl implements Calculadora {

    public Numero somar(Numero a, Numero b) {
        return new NumeroImpl(a.getValor() + b.getValor());
    };

    public Numero subtrair(Numero a, Numero b) {
        return new NumeroImpl(a.getValor() - b.getValor());
    };

    public Numero multiplicar(Numero a, Numero b) {
        return new NumeroImpl(a.getValor() * b.getValor());
    };

    public Numero dividir(Numero a, Numero b)
            throws DivisaoPorZeroException {
        if (b.getValor() == 0)
            throw new DivisaoPorZeroException();
        return new NumeroImpl(a.getValor() / b.getValor());
    }

    @Override
    public Numero potencia(Numero base, Numero expoente) throws RemoteException {
        double result = Math.pow(base.getValor(), expoente.getValor());
        return new NumeroImpl(result);
    }

    @Override
    public Numero raizQuadrada(Numero a) throws RemoteException {
        double result = Math.sqrt(a.getValor());
        return new NumeroImpl(result);
    }

    @Override
    public Numero dolarParaReal(Numero dolares) throws RemoteException {
        return new NumeroImpl(dolares.getValor() * 4.8);
    }

    @Override
    public Numero realParaDolar(Numero reais) throws RemoteException {
        return new NumeroImpl(reais.getValor() * 0.21);
    }

    @Override
    public Numero metroParaKilometro(Numero metros) throws RemoteException {
        return new NumeroImpl(metros.getValor() / 1000);
    }

    @Override
    public Numero kilometroParaMetro(Numero kilometros) throws RemoteException {
        return new NumeroImpl(kilometros.getValor() * 1000);
    }

    @Override
    public Numero logaritmo(Numero a, Numero b) throws RemoteException {
        return new NumeroImpl(Math.log(a.getValor()) / Math.log(b.getValor()));
    }

    @Override
    public Numero porcentualParaDecimal(Numero percentual) throws RemoteException {
        return new NumeroImpl(percentual.getValor() / 100);
    }

    @Override
    public Numero porcentagemDe(Numero a, Numero percentual) throws RemoteException {
        return new NumeroImpl((percentual.getValor() * a.getValor()) / 100);
    };

}
