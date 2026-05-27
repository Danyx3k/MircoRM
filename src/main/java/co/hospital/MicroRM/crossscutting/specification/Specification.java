package co.hospital.MicroRM.crossscutting.specification;

public abstract class Specification<T> {

	public abstract boolean isSatisfiedBy(T data);

	public Specification<T> and(Specification<T> other) {
		return new AndSpecification<>(this, other);
	}

	public Specification<T> or(Specification<T> other) {
		return new OrSpecification<>(this, other);
	}

	public Specification<T> not() {
		return new NotSpecification<>(this);
	}
}
