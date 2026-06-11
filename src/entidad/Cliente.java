package entidad;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cliente {

	private int idCliente;
	private String nombre;
	private String dni;
	private LocalDateTime fechaRegistro;
	private int estado;
	
	//forein key
	private Categoria categoria;
	
}
