package co.hospital.MicroRM.crossscutting.specification.generics;

import co.hospital.MicroRM.crossscutting.helper.ObjectHelper;
import co.hospital.MicroRM.crossscutting.helper.UUIDHelper;
import co.hospital.MicroRM.crossscutting.specification.Specification;

import java.util.UUID;

public class UuidValuePresentSpecification extends Specification<UUID> {

	@Override
	public boolean isSatisfiedBy(final UUID value) {
		return !ObjectHelper.isNull(value) && !UUIDHelper.getUUIDHelper().isDefaultUUID(value);
	}
}
