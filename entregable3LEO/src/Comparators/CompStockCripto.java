package Comparators;

import java.util.Comparator;

import appModels.Cripto;

public class CompStockCripto implements Comparator<Cripto> {

	@Override
	public int compare(Cripto o1, Cripto o2) {
		return Double.compare(o2.getStock(), o1.getStock());
	}
}
