package parcial.alarma.fittipaldi;

public class UsuarioActivador extends Usuario implements Activable{

	public UsuarioActivador(Integer dni, String nombre) {
		super(dni, nombre);
		
	}

	@Override
	public boolean activarDesactivarAlarma(Alarma alarma, String codigoActivacion) throws SensorDesactivadoException {
		for (Sensor sensores : alarma.listaDeSensores) {
			if ( sensores.getEstado() == true) {
				Accion accion = new Accion (1, alarma, null, "09.11.22", TipoOperacion.ACTIVACION);
				alarma.listaDeAcciones.add(accion);
				return true;
			}	
		} throw new SensorDesactivadoException();
	}
	
	
}
