package ar.com.historiasocial.util;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.com.historiasocial.entities.GeoCord;

/**
 * @author Manuel Ortiz - ortizman@gmail.com
 * 
 */
public class GeocodingServiceTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception{
		// Se espera entre llamada y llamada porque sino la api de google te
				// bloquea
		Thread.sleep(1000);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception{
	}

	/**
	 * Test method for
	 * {@link ar.com.historiasocial.util.GeocodingService#getGeocoding(java.lang.String, java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public final void testGetGeocodingForAddress() {
		// Hospital de ninhos - La Plata
		GeoCord geocord = GeocodingService.getGeocoding("14", "1631", "", "La Plata", "Buenos Aires");
		Assert.assertEquals(new Double(-34.932207), geocord.getLatitud());
		Assert.assertEquals(new Double(-57.9425037), geocord.getLongitud());

	}
	
	@Test
	public final void testGetGeocodingForInstersection() {
		// Hospital de ninhos - La Plata
		GeoCord geocord = GeocodingService.getGeocoding("65", "", "12", "La Plata", "Buenos Aires");
		Assert.assertEquals(new Double(-34.9297905), geocord.getLatitud());
		Assert.assertEquals(new Double(-57.9410019), geocord.getLongitud());

	}
	
	@Test
	public final void testGetGeocodingForInstersectionSinProv() {
		// Hospital de ninhos - La Plata
		GeoCord geocord = GeocodingService.getGeocoding("65", "", "12", "La Plata", "");
		Assert.assertEquals(new Double(-34.9297905), geocord.getLatitud());
		Assert.assertEquals(new Double(-57.9410019), geocord.getLongitud());

	}
	
	@Test
	public final void testGetGeocodingForInstersectionVariante() {
		// Hospital de ninhos - La Plata
		GeoCord geocord = GeocodingService.getGeocoding("Calle 65", "", "12", "La Plata", "");
		Assert.assertEquals(new Double(-34.9297905), geocord.getLatitud());
		Assert.assertEquals(new Double(-57.9410019), geocord.getLongitud());

	}
	
	/**
	 * Test method for
	 * {@link ar.com.historiasocial.util.GeocodingService#getGeocoding(java.lang.String, java.lang.String, java.lang.String, java.lang.String)}
	 */
	@Test
	public final void testGetGeocodingForAddressEmptyFields() {
		GeoCord geocord = GeocodingService.getGeocoding("", "", "", "La Plata", "Buenos Aires");
		Assert.assertEquals(new Double(-34.921439), geocord.getLatitud());
		Assert.assertEquals(new Double(-57.954541), geocord.getLongitud());

	}
	
	/**
	 * Test method for
	 * {@link ar.com.historiasocial.util.GeocodingService#getGeocoding(java.lang.String, java.lang.String, java.lang.String, java.lang.String)}
	 */
	@Test
	public final void testGetGeocodingForAddressEmptyAllFields() {
		GeoCord geocord = GeocodingService.getGeocoding("", "", "", "", "");
		Assert.assertEquals(new Double(-34.921439), geocord.getLatitud());
		Assert.assertEquals(new Double(-57.954541), geocord.getLongitud());
	}
	 
	/**
	 * Test method for
	 * {@link ar.com.historiasocial.util.GeocodingService#getGeocoding(java.lang.String, java.lang.String, java.lang.String, java.lang.String)}
	 */
	@Test
	public final void testGetGeocodingForAddressNullAllFields() {
		GeoCord geocord = GeocodingService.getGeocoding(null, null, null, null, null);
		Assert.assertEquals(new Double(-34.921439), geocord.getLatitud());
		Assert.assertEquals(new Double(-57.954541), geocord.getLongitud());
	}

}
