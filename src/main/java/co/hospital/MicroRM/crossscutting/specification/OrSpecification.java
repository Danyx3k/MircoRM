package co.hospital.MicroRM.crossscutting.specification;

public final class OrSpecification<T> extends Specification<T> {

	private final Specification<T> left;
	private final Specification<T> right;

	public OrSpecification(Specification<T> left, Specification<T> right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public boolean isSatisfiedBy(T data) {
		return left.isSatisfiedBy(data) || right.isSatisfiedBy(data);
	}
}
