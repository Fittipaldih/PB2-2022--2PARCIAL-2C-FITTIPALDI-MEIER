package parcial.alarma.fittipaldi;

import static org.junit.Assert.*;

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
	
	@Test
	public void alAgregarUnSensorDuplicadoEnUnaAlarmaSeLanceUnaSensorDuplicadoException() {
		Alarma alarma = new Alarma(1, "ACTIVACION", "CONFIGURACION", "ALARMA-UNLAM");
		Central central = new Central ();
		assertTrue (central.registrarAlarma(alarma));
		
		Usuario usuarioConfigurador = new UsuarioConfigurador (1, "Hernan", "CONFIGURACION");
		alarma.agregarUsuario(usuarioConfigurador);
			
		Sensor sensor = new Sensor(1, false);
		Sensor sensor2 = new Sensor(1, false);
		usuarioConfigurador.agregarSensorAAlarma(sensor);
		usuarioConfigurador.agregarSensorAAlarma(sensor2);
		
	}
	
	@Test
	public void queNoSePuedaActivarUnaAlarmaSiHayAlMenosUnSensorDesactivado() {
		
	}

	@Test
	public void queParaUnaAlarmaDeterminadaSePuedaObtenerUnaColeccionOrdenadaDeAccionEsDeTipoConfiguracionOrdenadasPorIdDeAccion() {
		
	}
}
