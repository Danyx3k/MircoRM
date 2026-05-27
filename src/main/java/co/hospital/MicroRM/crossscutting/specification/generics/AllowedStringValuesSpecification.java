package co.hospital.MicroRM.crossscutting.specification.generics;

import co.hospital.MicroRM.crossscutting.helper.ObjectHelper;
import co.hospital.MicroRM.crossscutting.helper.TextHelper;
import co.hospital.MicroRM.crossscutting.specification.Specification;

import java.util.Set;
import java.util.stream.Collectors;

public class AllowedStringValuesSpecification extends Specification<String> {

	private final Set<String> allowedNormalized;

	public AllowedStringValuesSpecification(String... allowed) {
		this.allowedNormalized = Set.of(allowed).stream().map(String::toUpperCase).collect(Collectors.toSet());
	}

	@Override
	public boolean isSatisfiedBy(final String value) {
		if (TextHelper.isNullOrWhiteSpace(value)) {
			return false;
		}
		return allowedNormalized.contains(value.trim().toUpperCase());
	}

	public static boolean isAllowedOrNull(final String value, Set<String> allowed) {
		if (ObjectHelper.isNull(value) || value.trim().isEmpty()) {
			return true;
		}
		return allowed.contains(value.trim().toUpperCase());
	}
}
