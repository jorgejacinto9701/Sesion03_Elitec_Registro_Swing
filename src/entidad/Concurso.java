package entidad;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Concurso {

	private int idConcurso;
	private String nombre;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private int estado;

}
