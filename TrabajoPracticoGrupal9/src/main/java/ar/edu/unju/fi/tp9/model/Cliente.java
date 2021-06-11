package ar.edu.unju.fi.tp9.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "CLIENTES")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@NotEmpty(message="Tipo de documento no puede estar vacio")
	@Column(name = "cli_tipoDocumento" , length = 10 , nullable = false)
	private String tipoDocumento ;
	
	@Min(value=6000000, message="El nº de DNI menor válido es 6.000.000")
	@Max(value=50000000, message="El nº de DNI maximo válido es 50.000.000")
	@Column(name = "cli_nroDocumento" , nullable = false)
	private int nroDocumento;
	
	@NotEmpty(message="El campo de nombre y apellido no puede estar vacio.")
	@Size(min = 5, max = 50, message="Como minimo debe ingresar 3 carácteres.")
	@Column(name = "cli_nombreApellido" , length = 50 , nullable = false)
	private String nombreApellido;
	
	@Email(message="Ingrese una dirección de email valida.")
	@NotEmpty(message = "Por favor ingrese email.")
	@Column(name = "cli_email" , length = 50 , nullable = false)
	private String email;
	
	@NotBlank(message="El campo de contraseña no puede estar en blanco.")
	@Size(min=6,max=15,message="La contraseña debe tener como minimo 6 cáracteres.")
	@Column(name = "cli_password" , length = 15 , nullable = false)
	private String password;
	
	@NotNull(message = "Debe ingresar fecha de nacimiento")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "cli_fechaNacimiento" , nullable = false)
	private LocalDate fechaNacimiento;
	
	@Min(value=100, message="El valor de codigo de area es menor al minimo.")
	@Max(value=1000, message="El valor de codigo de area es mayor al maximo.")
	@Column(name = "cli_codigoATelefono" , nullable = false)
	private int codigoAreaTelefono;
	
	@Min(value = 1000000,message = "El numero de telefono es mayor al minimo")
	@Max(value = 9999999,message = "El numero de telefono es mayor al máximo")
	@Column(name = "cli_nroTelefono" , nullable = false)
	private int nroTelefono;
	
	@NotNull(message = "Debe ingresar la fecha de su ultima compra.")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "cli_fechaUltimaCompra" , nullable = false)
	private LocalDate fechaUltimaCompra; 
	
	@Valid
	@Autowired
	@JoinColumn(name="cue_id")
	@OneToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
	private Cuenta cuenta;
	
	@ManyToMany
	@JoinTable(name = "cliente_beneficios",
	joinColumns=@JoinColumn(name="cli_id"),
	inverseJoinColumns=@JoinColumn(name="ben_id"))
	private List<Beneficio> beneficios = new ArrayList<Beneficio>();
	
	public Cliente() {
	 
	}
	

	/**
	 * @param id
	 * @param tipoDocumento
	 * @param nroDocumento
	 * @param nombreApellido
	 * @param email
	 * @param password
	 * @param fechaNacimiento
	 * @param codigoAreaTelefono
	 * @param nroTelefono
	 * @param fechaUltimaCompra
	 * @param cuenta
	 */
	public Cliente(Long id, String tipoDocumento, int nroDocumento, String nombreApellido, String email,
			String password, LocalDate fechaNacimiento, int codigoAreaTelefono, int nroTelefono,
			LocalDate fechaUltimaCompra, Cuenta cuenta,List<Beneficio> beneficios) {
		super();
		this.id = id;
		this.tipoDocumento = tipoDocumento;
		this.nroDocumento = nroDocumento;
		this.nombreApellido = nombreApellido;
		this.email = email;
		this.password = password;
		this.fechaNacimiento = fechaNacimiento;
		this.codigoAreaTelefono = codigoAreaTelefono;
		this.nroTelefono = nroTelefono;
		this.fechaUltimaCompra = fechaUltimaCompra;
		this.cuenta = cuenta;
		this.beneficios= beneficios;
	}


	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public int getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(int nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public String getNombreApellido() {
		return nombreApellido;
	}

	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getCodigoAreaTelefono() {
		return codigoAreaTelefono;
	}

	public void setCodigoAreaTelefono(int codigoAreaTelefono) {
		this.codigoAreaTelefono = codigoAreaTelefono;
	}

	public int getNroTelefono() {
		return nroTelefono;
	}

	public void setNroTelefono(int nroTelefono) {
		this.nroTelefono = nroTelefono;
	}

	public LocalDate getFechaUltimaCompra() {
		return fechaUltimaCompra;
	}

	public void setFechaUltimaCompra(LocalDate fechaUltimaCompra) {
		this.fechaUltimaCompra = fechaUltimaCompra;
	}
	
	public int getEdad() {
		LocalDate hoy = LocalDate.now();
		Period periodo = Period.between(fechaNacimiento, hoy);
		periodo.getYears();
		int edad = periodo.getYears();
		return edad;
	}
	
	public String getTiempoHastaCumple() {
		String texto = "";
		int anio = 0 ;
		LocalDate hoy = LocalDate.now();
		if (hoy.getMonthValue() < fechaNacimiento.getMonthValue()) {
			anio = hoy.getYear();
		}else {
			anio = hoy.getYear() +1;
		}
		LocalDate fechaProxCumple = LocalDate.of( anio, fechaNacimiento.getMonth(), fechaNacimiento.getDayOfMonth());
		Period periodo = Period.between(hoy, fechaProxCumple);
		
		texto = texto + periodo.getYears()+"A "+periodo.getMonths()+"M "+periodo.getDays()+"D ";
		
		LocalDateTime ahora = LocalDateTime.now();
		LocalDateTime fechaHoraProxCumple = LocalDateTime.of(anio,fechaNacimiento.getMonth(),fechaNacimiento.getDayOfMonth(),0,0,0);
		
		Duration duracion = Duration.between(ahora,fechaHoraProxCumple);
		texto = texto + duracion.toHoursPart() + "H "+duracion.toMinutesPart()+"M "+duracion.toSecondsPart()+"S ";
		
		return texto;
	}
		public String getTTransDesdeFechaN() {
			int tiempoTrans = 0;
			
			LocalDate hoy = LocalDate.now();
			//Period periodo = Period.between(fechaNacimiento, hoy);
			
			tiempoTrans = (int) ChronoUnit.DAYS.between(fechaNacimiento, hoy);
			
			return tiempoTrans+"D";
		}
		
		public String getTTransDesdeUltCompra () {
			String tiempoTrans = "";
			
			LocalDate hoy = LocalDate.now();
			Period periodo = Period.between(fechaUltimaCompra, hoy);
			int anio = periodo.getYears();
			int mes = periodo.getMonths();
			int dia = periodo.getDays();
			
			tiempoTrans = anio+"A "+mes+"M "+dia+"D ";
			
			return tiempoTrans;
		}


		/**
		 * @return the cuenta
		 */
		public Cuenta getCuenta() {
			return cuenta;
		}


		/**
		 * @param cuenta the cuenta to set
		 */
		public void setCuenta(Cuenta cuenta) {
			this.cuenta = cuenta;
		}

		
		
		/**
		 * @return the beneficios
		 */
		public List<Beneficio> getBeneficios() {
			return beneficios;
		}


		/**
		 * @param beneficios the beneficios to set
		 */
		public void setBeneficios(List<Beneficio> beneficios) {
			this.beneficios = beneficios;
		}


		@Override
		public String toString() {
			return "Cliente [id=" + id + ", tipoDocumento=" + tipoDocumento + ", nroDocumento=" + nroDocumento
					+ ", nombreApellido=" + nombreApellido + ", email=" + email + ", password=" + password
					+ ", fechaNacimiento=" + fechaNacimiento + ", codigoAreaTelefono=" + codigoAreaTelefono
					+ ", nroTelefono=" + nroTelefono + ", fechaUltimaCompra=" + fechaUltimaCompra + ", cuenta=" + cuenta
					+ "]";
		}
	
}
