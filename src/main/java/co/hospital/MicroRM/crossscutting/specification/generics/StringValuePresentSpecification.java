package co.hospital.MicroRM.crossscutting.specification.generics;

import co.hospital.MicroRM.crossscutting.helper.TextHelper;
import co.hospital.MicroRM.crossscutting.specification.Specification;

public class StringValuePresentSpecification extends Specification<String> {

	@Override
	public boolean isSatisfiedBy(final String value) {
		return !TextHelper.isNullOrWhiteSpace(value);
	}
}
