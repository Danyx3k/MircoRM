package co.hospital.MicroRM.infrastructure.persistence.sql.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "paciente")
public class PacienteJPAEntity {

	@Id
	@Column(name = "id_paciente", nullable = false)
	private UUID idPaciente;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_tipo_documento", nullable = false)
	private TipoDocumentoJPAEntity tipoDocumento;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_sexo", nullable = false)
	private SexoJPAEntity sexo;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_eps", nullable = false)
	private EpsJPAEntity eps;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_colaborador_registra")
	private ColaboradorJPAEntity colaboradorRegistra;

	@Column(nullable = false, length = 100)
	private String nombre;

	@Column(nullable = false, length = 100)
	private String apellido;

	@Column(name = "numero_identificacion", nullable = false, unique = true, length = 20)
	private String numeroIdentificacion;

	@Column(length = 15)
	private String celular;

	@Column(length = 100)
	private String correo;

	@Column(name = "fecha_nacimiento", nullable = false)
	private LocalDate fechaNacimiento;

	@Column(name = "observacion_clinica", columnDefinition = "text")
	private String observacionClinica;

	@Column(name = "fecha_creacion", nullable = false)
	private Instant fechaCreacion;

	@Column(name = "fecha_actualizacion", nullable = false)
	private Instant fechaActualizacion;

	@Column(name = "usuario_crea", length = 100)
	private String usuarioCrea;

	@PrePersist
	void onCreate() {
		Instant now = Instant.now();
		if (fechaCreacion == null) {
			fechaCreacion = now;
		}
		fechaActualizacion = now;
	}

	@PreUpdate
	void onUpdate() {
		fechaActualizacion = Instant.now();
	}

	public UUID getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(UUID idPaciente) {
		this.idPaciente = idPaciente;
	}

	public TipoDocumentoJPAEntity getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumentoJPAEntity tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public SexoJPAEntity getSexo() {
		return sexo;
	}

	public void setSexo(SexoJPAEntity sexo) {
		this.sexo = sexo;
	}

	public EpsJPAEntity getEps() {
		return eps;
	}

	public void setEps(EpsJPAEntity eps) {
		this.eps = eps;
	}

	public ColaboradorJPAEntity getColaboradorRegistra() {
		return colaboradorRegistra;
	}

	public void setColaboradorRegistra(ColaboradorJPAEntity colaboradorRegistra) {
		this.colaboradorRegistra = colaboradorRegistra;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getObservacionClinica() {
		return observacionClinica;
	}

	public void setObservacionClinica(String observacionClinica) {
		this.observacionClinica = observacionClinica;
	}

	public Instant getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Instant fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Instant getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Instant fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public String getUsuarioCrea() {
		return usuarioCrea;
	}

	public void setUsuarioCrea(String usuarioCrea) {
		this.usuarioCrea = usuarioCrea;
	}
}
