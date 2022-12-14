package parcial.alarma.fittipaldi;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class Alarma {
	protected Integer id;
	protected String codigoActivacion, codigoConfiguracion, nombre;
	protected ArrayList <Usuario> listaUsuariosValidos;
	protected Set <Accion> listaDeAcciones;
	protected Set <Sensor> listaDeSensores;
	
	public Alarma(Integer id, String codigoActivacion, String codigoConfiguracion, String nombre) {
		
		this.id = id;
		this.codigoActivacion = codigoActivacion;
		this.codigoConfiguracion = codigoConfiguracion;
		this.nombre = nombre;
		listaUsuariosValidos = new ArrayList<>();
		listaDeAcciones = new TreeSet<>();
		listaDeSensores= new TreeSet<>();
		
	}
	
	public Boolean agregarUsuario(Usuario usuarioAAgregar) {
		if (listaUsuariosValidos.add(usuarioAAgregar)) {
		return true;
		} return false;
	}
	
	public boolean validarCodigoConfiguracion(String codigoConfigurador) {
		if ( codigoConfigurador.equals(this.codigoConfiguracion)) {
			return true;
		} return false;
	}
	
	public boolean agregarSensor(Sensor sensor) throws SensorDuplicadoException {
		
		for (Sensor sensores : listaDeSensores) {
			if (!sensores.getId().equals(sensor.getId())) {
					listaDeSensores.add(sensor) ;
				return true;
				} 
		} throw new SensorDuplicadoException();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigoActivacion() {
		return codigoActivacion;
	}

	public void setCodigoActivacion(String codigoActivacion) {
		this.codigoActivacion = codigoActivacion;
	}

	public String getCodigoConfiguracion() {
		return codigoConfiguracion;
	}

	public void setCodigoConfiguracion(String codigoConfiguracion) {
		this.codigoConfiguracion = codigoConfiguracion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Usuario> getListaUsuariosValidos() {
		return listaUsuariosValidos;
	}

	public void setListaUsuariosValidos(ArrayList<Usuario> listaUsuariosValidos) {
		this.listaUsuariosValidos = listaUsuariosValidos;
	}

	public Set<Accion> getListaDeAcciones() {
		return listaDeAcciones;
	}

	public void setListaDeAcciones(Set<Accion> listaDeAcciones) {
		this.listaDeAcciones = listaDeAcciones;
	}

	public Set<Sensor> getListaDeSensores() {
		return listaDeSensores;
	}

	public void setListaDeSensores(Set<Sensor> listaDeSensores) {
		this.listaDeSensores = listaDeSensores;
	}
	
	
	
	
	
	
	
}
