package Comparators;

import java.util.Comparator;

import appModels.Moneda;

public class CompValorMoneda implements Comparator<Moneda> {

	@Override
	public int compare(Moneda o1, Moneda o2) {
		return Double.compare(o2.getValor_Dolar(), o1.getValor_Dolar());
	}
}
