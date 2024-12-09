package Comparators;

import java.util.Comparator;
import appModels.Moneda;
public class CompNMCTMoneda implements Comparator<Moneda> {

	@Override
	public int compare(Moneda o1, Moneda o2) {
		return o1.getNomenclatura().compareTo(o2.getNomenclatura());
	}
}
