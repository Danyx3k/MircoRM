package co.hospital.MicroRM.crossscutting.specification.generics;

import co.hospital.MicroRM.crossscutting.specification.Specification;

import java.util.regex.Pattern;

public final class RegexPatternSpecification extends Specification<String> {

	private final Pattern pattern;

	public RegexPatternSpecification(String regex) {
		this.pattern = Pattern.compile(regex);
	}

	public RegexPatternSpecification(String regex, int flags) {
		this.pattern = Pattern.compile(regex, flags);
	}

	@Override
	public boolean isSatisfiedBy(String value) {
		return value != null && pattern.matcher(value).matches();
	}
}
