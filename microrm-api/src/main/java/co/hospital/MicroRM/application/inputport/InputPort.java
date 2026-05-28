package co.hospital.MicroRM.application.inputport;

public interface InputPort<T, R> {

	R execute(T data);
}
