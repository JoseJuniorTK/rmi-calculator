import java.rmi.RemoteException;

public class CalculadoraImpl implements Calculadora {

    public Numero soma (Numero a, Numero b) {
        return new NumeroImpl (a.getValor() + b.getValor());
    };

    public Numero subtrai (Numero a, Numero b) {
        return new NumeroImpl (a.getValor() - b.getValor());
    };

    public Numero multiplica (Numero a, Numero b)  {
        return new NumeroImpl (a.getValor() * b.getValor());
    };

    public Numero divide (Numero a, Numero b)
        throws DivisaoPorZeroException  {
        if (b.getValor() == 0) throw new DivisaoPorZeroException();
        return new NumeroImpl (a.getValor() / b.getValor());
    }

    @Override
    public Numero somar(Numero a, Numero b) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'somar'");
    }

    @Override
    public Numero subtrair(Numero a, Numero b) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'subtrair'");
    }

    @Override
    public Numero dividir(Numero numerador, Numero denominador) throws RemoteException, DivisaoPorZeroException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dividir'");
    }

    @Override
    public Numero potencia(Numero base, Numero expoente) throws RemoteException, DivisaoPorZeroException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'potencia'");
    }

    @Override
    public Numero raizQuadrada(Numero a) throws RemoteException, DivisaoPorZeroException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'raizQuadrada'");
    }

    @Override
    public Numero dolarParaReal(Numero dolares) throws RemoteException, DivisaoPorZeroException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dolarParaReal'");
    }

    @Override
    public Numero realParaDolar(Numero reais) throws RemoteException, DivisaoPorZeroException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'realParaDolar'");
    }

    @Override
    public Numero metroParaKilometro(Numero metros) throws RemoteException, DivisaoPorZeroException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'metroParaKilometro'");
    }

    @Override
    public Numero kilometroParaMetro(Numero kilometros) throws RemoteException, DivisaoPorZeroException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'kilometroParaMetro'");
    }

    @Override
    public Numero logaritmo(Numero metros) throws RemoteException, DivisaoPorZeroException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'logaritmo'");
    }

    @Override
    public Numero porcentualParaDecimal(Numero a) throws RemoteException, DivisaoPorZeroException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'porcentualParaDecimal'");
    }

    @Override
    public Numero porcentagemDe(Numero a, Numero percentual) throws RemoteException, DivisaoPorZeroException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'porcentagemDe'");
    };

}
