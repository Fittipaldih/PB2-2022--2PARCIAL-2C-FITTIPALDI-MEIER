package parcial.alarma.fittipaldi;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

public class TestAlarma {

	@Test
	public void queSePuedaRegistrarUnaAlarmaEnLaCentral() {
		Alarma alarma = new Alarma(1, "ACTIVACION", "CONFIGURACION", "ALARMA-UNLAM");
		Central central = new Central ();
		assertTrue (central.registrarAlarma(alarma));
		
	}
	
	@Test
	public void queSePuedaAgregarUnUsuarioConfiguradorAUnaAlarma() {
		Alarma alarma = new Alarma(1, "ACTIVACION", "CONFIGURACION", "ALARMA-UNLAM");
		Central central = new Central ();
		assertTrue (central.registrarAlarma(alarma));
		
		Usuario usuarioConfigurador = new UsuarioConfigurador (1, "Hernan", "CONFIGURACION");
		boolean sePudoAgregar = alarma.agregarUsuario(usuarioConfigurador);
		
		assertTrue(sePudoAgregar);
	}
	
	@Test (expected = AlarmaIncorrectoException.class)
	public void alAgregarUnUsuarioAUnaAlarmaConCondigoDeConfiguracionDeAlarmaInvalidoSeLanceElCodigoAlarmaIncorrectoException() throws AlarmaIncorrectoException {
		Alarma alarma = new Alarma(1, "ACTIVACION", "CONFIGURACION", "ALARMA-UNLAM");
		Central central = new Central ();
		assertTrue (central.registrarAlarma(alarma));
		String codigoConfigurador = "CODIGOINVALIDO";
		Usuario usuarioConfigurador = new UsuarioConfigurador (1, "Hernan", codigoConfigurador);
		
		if ( alarma.validarCodigoConfiguracion(codigoConfigurador)) {
		alarma.agregarUsuario(usuarioConfigurador);
		} else throw new AlarmaIncorrectoException();
		
	}
	
	@Test (expected = SensorDuplicadoException.class)
	public void alAgregarUnSensorDuplicadoEnUnaAlarmaSeLanceUnaSensorDuplicadoException() throws SensorDuplicadoException {
		Alarma alarma = new Alarma(1, "ACTIVACION", "CONFIGURACION", "ALARMA-UNLAM");
		Central central = new Central ();
		assertTrue (central.registrarAlarma(alarma));
		
		Usuario usuarioConfigurador = new UsuarioConfigurador (1, "Hernan", "CONFIGURACION");
		alarma.agregarUsuario(usuarioConfigurador);
			
		Sensor sensor = new Sensor(1, false);
		Sensor sensor2 = new Sensor(1, false);
		
		try {
			assertTrue(((UsuarioConfigurador) usuarioConfigurador).agregarSensorAAlarma(sensor, alarma));
			assertFalse (((UsuarioConfigurador) usuarioConfigurador).agregarSensorAAlarma(sensor2,alarma));
		} catch (Exception SensorDuplicadoException) {
			throw new SensorDuplicadoException();
		}
		
	}
	
	@Test ( expected = SensorDuplicadoException.class)
	public void queNoSePuedaActivarUnaAlarmaSiHayAlMenosUnSensorDesactivado() throws SensorDuplicadoException {
		Alarma alarma = new Alarma(1, "ACTIVACION", "CONFIGURACION", "ALARMA-UNLAM");
		Central central = new Central ();
		assertTrue (central.registrarAlarma(alarma));
		
		Usuario usuarioActivador = new UsuarioActivador (1, "Hernan");
		alarma.agregarUsuario(usuarioActivador);
			
		Sensor sensor = new Sensor(5, false);
		
		alarma.agregarSensor(sensor);
		
		String codigoActivacion = "ACTIVACION";
		try {
		assertFalse ( ((UsuarioActivador) usuarioActivador).activarDesactivarAlarma(alarma, codigoActivacion) );
		} catch (Exception SensorDesactivadoException) {
			throw new SensorDuplicadoException();
		}
		
	}

	@Test
	public void queParaUnaAlarmaDeterminadaSePuedaObtenerUnaColeccionOrdenadaDeAccionEsDeTipoConfiguracionOrdenadasPorIdDeAccion() {
		Alarma alarma = new Alarma(1, "ACTIVACION", "CONFIGURACION", "ALARMA-UNLAM");
		Central central = new Central ();
		
		Set <Accion> accionesOrdenadas = new TreeSet <>();
		for (Accion acciones : alarma.listaDeAcciones) {
			if ( acciones.getTipo().equals(TipoOperacion.CONFIGURACION)) {
				accionesOrdenadas.add(acciones);
			}
		}
		
		
	}
}
