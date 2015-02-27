package negocio;

import java.util.Date;

public class Aviso {
	private Date fechaAviso;
	private Date fechaVacunacion;
	private Vacuna vacuna;
	private Animal animal;

	
	public Date getFechaAviso() {
		return fechaAviso;
	}
	public void setFechaAviso(Date fechaAviso) {
		this.fechaAviso = fechaAviso;
	}
	public Date getFechaVacunacion() {
		return fechaVacunacion;
	}
	public void setFechaVacunacion(Date fechaVacunacion) {
		this.fechaVacunacion = fechaVacunacion;
	}
	public Vacuna getVacuna() {
		return vacuna;
	}
	public void setVacuna(Vacuna vacuna) {
		this.vacuna = vacuna;
	}
	public Animal getAnimal() {
		return animal;
	}
	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	
	public Aviso(Date fa, Date fv, Vacuna v, Animal a)
	{
		this.setFechaAviso(fa);
		this.setFechaVacunacion(fv);
		this.setVacuna(v);
		this.setAnimal(a);
	}
	
	public String[] hayContacto(){
		String[] contactos = new String[]{this.animal.getPropietario().getCelular(),this.animal.getPropietario().getEmail(),this.animal.getPropietario().getTelefono_fijo()};				
		return contactos;
	}

}
