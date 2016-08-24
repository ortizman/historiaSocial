package ar.com.historiasocial.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import ar.com.historiasocial.entities.GeoCord;

/**
 * Esta clase obtiene las coordenadas {@link GeoCord} a partir de una direccion.
 * En caso de que falle la comunicacion con la API de google, el parseo del xml,
 * etc. se devuelve el centro geografico de la ciudad de La Plata, Buenos Aires.
 * 
 * @author Manuel Ortiz - ortizman@gmail.com
 */
public class GeocodingService {

	private static final Logger	LOGGER							= Logger.getLogger(GeocodingService.class);

	//URL prefix to the geocoder
	private static final String	GEOCODER_REQUEST_PREFIX_FOR_XML	= "http://maps.google.com/maps/api/geocode/xml";
	private static final String	GEOCODER_REQUEST_MAPQUEST	= "http://geocoder.cit.api.here.com/6.2/geocode.xml";

	private static final Double LAT_DEFAULT = -34.921439;
	private static final Double LNG_DEFAULT = -57.954541;

	/**
	 * Constructor por defecto privado
	 */
	private GeocodingService() {

	}

	private static GeoCord getGeocodingForAddress(String calle, String numero, String ciudad, String provincia){
		Double latitud = LAT_DEFAULT;
		Double lngitud = LNG_DEFAULT;

		if ((StringUtils.isEmpty(calle) || StringUtils.isEmpty(numero))) { return new GeoCord(latitud, lngitud); }

		if (StringUtils.isEmpty(ciudad)) {
			ciudad = "+La+Plata";
		}

		if (StringUtils.isEmpty(provincia)) {
			provincia = "Buenos Aires";
		}

		// query address
		String address = numero + "+Calle+" + calle + "," + ciudad + ",+AR";

		// prepare a URL to the geocoder
		URL url = null;
		HttpURLConnection conn = null;
		Document geocoderResultDocument = null;
		try {
			url = new URL(GEOCODER_REQUEST_PREFIX_FOR_XML + "?address=" + URLEncoder.encode(address, "UTF-8") + "&sensor=false");

			// prepare an HTTP connection to the geocoder
			conn = (HttpURLConnection) url.openConnection();

			conn.connect();
			InputSource geocoderResultInputSource = new InputSource(conn.getInputStream());

			// read result and parse into XML Document
			geocoderResultDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(geocoderResultInputSource);

			// prepare XPath
			XPath xpath = XPathFactory.newInstance().newXPath();

			// extract the result
			NodeList resultNodeList = null;

			// c) extract the coordinates of the first result
			resultNodeList = (NodeList) xpath.evaluate("/GeocodeResponse/result[1]/geometry/location/*", geocoderResultDocument, XPathConstants.NODESET);
			for (int i = 0; i < resultNodeList.getLength(); ++i) {
				Node node = resultNodeList.item(i);
				if ("lat".equals(node.getNodeName())) {
					latitud = Double.parseDouble(node.getTextContent());
				}
				if ("lng".equals(node.getNodeName())) {
					lngitud = Double.parseDouble(node.getTextContent());
				}
			}

		} catch (IOException e) {
			LOGGER.error("Error de entrada / salida intentando obtener las coordenadas de calle: " + calle + ", numero: " + numero + ", ciudad: " + ciudad
					+ ", provincia: " + provincia, e);
			LOGGER.warn("Se devuelve las corrdenadas del centro geografico de la ciudad de la plata");
			return new GeoCord(LAT_DEFAULT, LNG_DEFAULT); // Centro geografico de
														// la ciudad de La
														// Plata, Buenos Aires.
		} catch (Exception e) {
			LOGGER.error("Error Inesperado al intentar obtener las coordenadas de la direccion. calle: " + calle + ", numero: " + numero + ", ciudad: "
					+ ciudad + ", provincia: " + provincia, e);
			LOGGER.warn("Se devuelve las corrdenadas del centro geografico de la ciudad de la plata");
			return new GeoCord(LAT_DEFAULT, LNG_DEFAULT);
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}

		return new GeoCord(latitud, lngitud);
	}
	
	/**
	 * Obtiene las coordenadas a partir de una dirección.
	 * La dirección puede estar compuesta por calle, numero e intersección (además de ciudad y prov.)
	 * Si se especifica un numero de calle tendrá prioridad por sobre la intersección. 
	 * Si se recibe calle1 y calle2, se obtienen las coordenadas para la intersección.
	 * De otra forma, se devuelve las coordenadas geográficas del centro de la ciudad de La Plata.
	 * 
	 * @param calle1
	 * @param numero
	 * @param calle2
	 * @param ciudad
	 * @param provincia
	 * @return coordenadas geográficas
	 */
	public static GeoCord getGeocoding(String calle1, String numero, String calle2, String ciudad, String provincia){
		if ((StringUtils.isEmpty(calle1) || (StringUtils.isEmpty(numero) && StringUtils.isEmpty(calle2)) )) {
			return new GeoCord(LAT_DEFAULT, LNG_DEFAULT); 
		}
		
		if (StringUtils.isNotEmpty(calle1) && StringUtils.isNotEmpty(numero)){
			return getGeocodingForAddress(calle1, numero, ciudad, provincia);
		} else {
			return getGeocodingForIntersection(calle1, calle2, ciudad, provincia);
		}
		
	}
	
	private static GeoCord getGeocodingForIntersection(String calle1, String calle2, String ciudad, String provincia){
		Double lat = LAT_DEFAULT;
		Double lng = LNG_DEFAULT;

		if ((StringUtils.isEmpty(calle1) || StringUtils.isEmpty(calle2))) { return new GeoCord(lat, lng); }
		
		if (StringUtils.isEmpty(ciudad)) {
			ciudad = "La%20Plata";
		}

		if (StringUtils.isEmpty(provincia)) {
			provincia = "Buenos%20Aires";
		}
		
		URLConnection conn = null;
		
		
		try {
			String intersection = "?city="+URLEncoder.encode(ciudad, "UTF-8")+","+URLEncoder.encode(provincia, "UTF-8")+"&street0="+ URLEncoder.encode(calle1, "UTF-8")+"&street1="+URLEncoder.encode(calle2, "UTF-8")+"&app_id=rME43ZBwYaaqN91WA0vo&app_code=zxMKM0o0f5RIZ12V2inGPw&gen=5";
			URL url = new URL(GEOCODER_REQUEST_MAPQUEST +  intersection);

			conn = url.openConnection();
			conn.connect();
			InputSource geocoderResultInputSource = new InputSource(conn.getInputStream());

			// read result and parse into XML Document
			Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(geocoderResultInputSource);

			// prepare XPath
			XPath xpath = XPathFactory.newInstance().newXPath();

			// extract the result
			NodeList resultNodeList = null;

			// c) extract the coordinates of the first result
			resultNodeList = (NodeList) xpath.evaluate("/*/Response/View/Result/Location/DisplayPosition/*", document, XPathConstants.NODESET);
			
			for (int i = 0; i < resultNodeList.getLength(); ++i) {
				Node node = resultNodeList.item(i);
				if ("Latitude".equals(node.getNodeName())) {
					lat = Double.parseDouble(node.getTextContent());
				}
				if ("Longitude".equals(node.getNodeName())) {
					lng = Double.parseDouble(node.getTextContent());
				}
			}
			

		} catch (Exception e) {
			LOGGER.error("Error Inesperado al intentar obtener las coordenadas de la interseccion. calle: " + calle1 + " y calle: " + calle2 + ", ciudad: "
					+ ciudad + ", provincia: " + provincia, e);
			LOGGER.warn("Se devuelve las corrdenadas del centro geografico de la ciudad de la plata");
			return new GeoCord(lat, lng);
		} finally {
			if (conn != null) {
				((HttpURLConnection)conn).disconnect();
			}
		}
		
		return new GeoCord(lat, lng);
	}

}
