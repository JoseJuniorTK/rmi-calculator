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
    }
    @Override
    public Numero restoDivisão(Numero a, Numero b){
        return new NumeroImpl(a.getValor() % b.getValor());
    }
    @Override
    public Numero calculaSeno(Numero a){
        return new NumeroImpl(Math.sin(Math.toRadians(a.getValor())));
    }
    @Override
    public Numero calculaCosseno(Numero a){
        return new NumeroImpl(Math.cos(Math.toRadians(a.getValor())));
    }
    @Override
    public Numero calculaTangente(Numero a){
        return new NumeroImpl(Math.tan(Math.toRadians(a.getValor())));
    }

    @Override
    public Numero kilometroParaMetro(Numero kilometros) throws RemoteException {
        return new NumeroImpl(kilometros.getValor() * 1000);
    }

    @Override
    public Numero kilogramaParaGrama(Numero kilogramas) throws RemoteException {
        return new NumeroImpl(kilogramas.getValor() * 1000);
    }

    @Override
    public Numero gramaParaKilograma(Numero gramas) throws RemoteException {
       return new NumeroImpl(gramas.getValor() / 1000);
    }

    @Override
    public Numero celsiusParaFahrenheit(Numero celsius) throws RemoteException {
    double fahrenheit = celsius.getValor() * 9 / 5 + 32;
       return new NumeroImpl(fahrenheit);
    }

    @Override
    public Numero fahrenheitParaCelsius(Numero fahrenheit) throws RemoteException {
    double celsius = (fahrenheit.getValor() - 32) * 5 / 9;
       return new NumeroImpl(celsius);
    }

    @Override
    public Numero litrosParaMililitros(Numero litros) throws RemoteException {
       return new NumeroImpl(litros.getValor() * 1000);
    }

    @Override
    public Numero mililitrosParaLitros(Numero mililitros) throws RemoteException {
       return new NumeroImpl(mililitros.getValor() / 1000);
    }

    @Override
    public Numero horasParaMinutos(Numero horas) throws RemoteException {
      return new NumeroImpl(horas.getValor() * 60);
    }

    @Override
    public Numero minutosParaHoras(Numero minutos) throws RemoteException {
      return new NumeroImpl(minutos.getValor() / 60);
    }

    @Override
    public Numero celsiusParaKelvin(Numero celsius) throws RemoteException {
    double kelvin = celsius.getValor() + 273.15;
       return new NumeroImpl(kelvin);
    }

    @Override
    public Numero kelvinParaCelsius(Numero kelvin) throws RemoteException {
    double celsius = kelvin.getValor() - 273.15;
       return new NumeroImpl(celsius);
    }


    public Numero calculaFatorial(Numero a) {
    int Fat = 1;
    for (int i = 1; i <= a.getValor(); i++) {
        Fat = Fat * i;
    }
    return new NumeroImpl(Fat);
}


}
