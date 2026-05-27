package co.hospital.MicroRM.crossscutting.specification.generics;

import co.hospital.MicroRM.crossscutting.helper.EmailHelper;
import co.hospital.MicroRM.crossscutting.helper.TextHelper;
import co.hospital.MicroRM.crossscutting.specification.Specification;

public class EmailFormatSpecification extends Specification<String> {

	@Override
	public boolean isSatisfiedBy(final String email) {
		return !TextHelper.isNullOrWhiteSpace(email) && EmailHelper.isValidFormat(email);
	}
}
