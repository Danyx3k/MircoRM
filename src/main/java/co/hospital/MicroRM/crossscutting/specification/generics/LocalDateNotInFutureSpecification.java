package co.hospital.MicroRM.crossscutting.specification.generics;

import co.hospital.MicroRM.crossscutting.helper.ObjectHelper;
import co.hospital.MicroRM.crossscutting.specification.Specification;

import java.time.LocalDate;

public class LocalDateNotInFutureSpecification extends Specification<LocalDate> {

	@Override
	public boolean isSatisfiedBy(final LocalDate date) {
		if (ObjectHelper.isNull(date)) {
			return false;
		}
		return !date.isAfter(LocalDate.now());
	}
}
