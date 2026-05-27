package co.hospital.MicroRM.infrastructure.persistence.sql.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "muestra_paciente")
public class MuestraPacienteJPAEntity {

	@Id
	@Column(name = "id_muestra_paciente")
	private UUID idMuestraPaciente;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_muestra", nullable = false)
	private MuestraJPAEntity muestra;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_paciente", nullable = false)
	private PacienteJPAEntity paciente;

	@Column(name = "fecha_asociacion", nullable = false)
	private Instant fechaAsociacion;

	@PrePersist
	void onCreate() {
		if (fechaAsociacion == null) {
			fechaAsociacion = Instant.now();
		}
	}

	public UUID getIdMuestraPaciente() {
		return idMuestraPaciente;
	}

	public void setIdMuestraPaciente(UUID idMuestraPaciente) {
		this.idMuestraPaciente = idMuestraPaciente;
	}

	public MuestraJPAEntity getMuestra() {
		return muestra;
	}

	public void setMuestra(MuestraJPAEntity muestra) {
		this.muestra = muestra;
	}

	public PacienteJPAEntity getPaciente() {
		return paciente;
	}

	public void setPaciente(PacienteJPAEntity paciente) {
		this.paciente = paciente;
	}

	public Instant getFechaAsociacion() {
		return fechaAsociacion;
	}

	public void setFechaAsociacion(Instant fechaAsociacion) {
		this.fechaAsociacion = fechaAsociacion;
	}
}
