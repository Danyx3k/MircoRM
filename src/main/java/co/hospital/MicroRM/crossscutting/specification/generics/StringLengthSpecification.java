package co.hospital.MicroRM.crossscutting.specification.generics;

import co.hospital.MicroRM.crossscutting.helper.TextHelper;
import co.hospital.MicroRM.crossscutting.specification.Specification;

public class StringLengthSpecification extends Specification<String> {

	private final int min;
	private final int max;
	private final boolean mustApplyTrim;

	public StringLengthSpecification(int min, int max, boolean mustApplyTrim) {
		this.min = min;
		this.max = max;
		this.mustApplyTrim = mustApplyTrim;
	}

	@Override
	public boolean isSatisfiedBy(final String string) {
		return TextHelper.lengthIsValid(string, min, max, mustApplyTrim);
	}
}
