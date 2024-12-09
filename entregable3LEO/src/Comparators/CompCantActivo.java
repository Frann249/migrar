package Comparators;

import java.util.Comparator;

import appModels.Activo;

public class CompCantActivo implements Comparator<Activo> {

	@Override
	public int compare(Activo o1, Activo o2) {
		return Double.compare(o2.getCantidad(),o1.getCantidad());
	}
}
