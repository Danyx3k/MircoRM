package co.hospital.MicroRM.crossscutting.specification.generics;

import co.hospital.MicroRM.crossscutting.helper.ObjectHelper;
import co.hospital.MicroRM.crossscutting.specification.Specification;

public class IntNonNegativeSpecification extends Specification<Integer> {

	@Override
	public boolean isSatisfiedBy(final Integer value) {
		if (ObjectHelper.isNull(value)) {
			return true;
		}
		return value >= 0;
	}
}
