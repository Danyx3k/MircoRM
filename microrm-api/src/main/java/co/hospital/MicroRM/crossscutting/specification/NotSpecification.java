package co.hospital.MicroRM.crossscutting.specification;

public final class NotSpecification<T> extends Specification<T> {

	private final Specification<T> condition;

	public NotSpecification(Specification<T> condition) {
		this.condition = condition;
	}

	@Override
	public boolean isSatisfiedBy(T data) {
		return !condition.isSatisfiedBy(data);
	}
}
