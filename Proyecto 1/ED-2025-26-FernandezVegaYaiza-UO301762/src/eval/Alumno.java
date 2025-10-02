//  NO MODIFICAR NOMBRE DE ESTE PAQUETE
package eval;

public class Alumno {
	// Poned vuestros apellidos, nombre y UO; sustituyendolos en las asignaciones de debajo...
	String nombre="Yaiza", // Primera en mayuscula y si es compuesto, sin espacios
			apellido1="Fernandez", // Primera en mayuscula y si es compuesto, sin espacios
			apellido2="Vega", // Primera en mayuscula y si es compuesto, sin espacios
			uo="UO301762"; // El UO en mayusculas
	
	public String getNombreFicheroAlumno(){
		return apellido1+apellido2+nombre+"-"+uo+"-2025-26";
	}

	public String email() {
		return uo+"@uniovi.es";
	}
	
}
