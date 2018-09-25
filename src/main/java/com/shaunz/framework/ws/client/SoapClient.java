package com.shaunz.framework.ws.client;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.xml.bind.JAXBException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SoapClient {
	private static final Logger logger = LoggerFactory.getLogger(SoapClient.class);
	private static SOAPConnectionFactory soapConnectionFactory;
	private static MessageFactory messageFactory;
	
	/**
	 * get SOAPConnectionFactory
	 * @method getSOAPConnectionFactory
	 * @return
	 */
	public static SOAPConnectionFactory getSOAPConnectionFactory(){
		if(soapConnectionFactory == null){
			try {
				soapConnectionFactory = SOAPConnectionFactory.newInstance();
			} catch (UnsupportedOperationException e) {
				logger.error(e.getMessage());
			} catch (SOAPException e) {
				logger.error(e.getMessage());
			}
		}
		return soapConnectionFactory;
	}
	/**
	 * get MessageFactory
	 * @method
	 * @return
	 */
	public static MessageFactory getMessageFactory(){
		if(messageFactory == null){
			try {
				messageFactory = MessageFactory.newInstance();
			} catch (SOAPException e) {
				logger.error(e.getMessage());
			}
		}
		return messageFactory;
	}
	/**
	 * @method requestWSWithSOAP
	 * @param URL
	 * @param nameSpaces
	 * @param xmlParams
	 * @return
	 */
	public static SOAPMessage requestWSWithSOAP(String URL,Map<String, String> nameSpaces,String xmlParams)throws Exception{
		SOAPMessage soapResponse = null;
		SOAPConnection soapConnection = null;
		try {
			// Create SOAP Connection
	        SOAPConnectionFactory soapConnectionFactory = getSOAPConnectionFactory();
	        soapConnection = soapConnectionFactory.createConnection();

	        // Send SOAP Message to SOAP Server
	        logger.info("SOAP request" + URL);
	        soapResponse = soapConnection.call(createSOAPRequest(nameSpaces,xmlParams), URL);
	        
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		} finally {
			if(soapConnection != null)
				try {
					soapConnection.close();
				} catch (SOAPException e) {
					logger.error(e.getMessage());
				}
		}
		return soapResponse;
	}
	/**
	 * @method createSOAPRequest
	 * @param param
	 * @return
	 * @throws SOAPException
	 * @throws IOException
	 */
	public static SOAPMessage createSOAPRequest(Map<String, String> nameSpaces,String xmlParams) throws SOAPException, IOException {
		MessageFactory messageFactory = getMessageFactory();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        
        prepareSOAPRequestBody(envelope,xmlParams,nameSpaces);

        soapMessage.saveChanges();

        /* Print the request message */
        logger.info("SOAP request with parameters: " + soapMessage.toString());

        return soapMessage;
	}
	/**
	 * @method prepareSOAPRequestBody
	 * @param envelope
	 * @param xmlParams
	 * @param nameSpaces
	 * @throws SOAPException
	 */
	public static void prepareSOAPRequestBody(SOAPEnvelope envelope,String xmlParams,Map<String, String> nameSpaces) throws SOAPException {
		//Name Space
		if(nameSpaces != null && nameSpaces.size() > 0){
			Iterator<String> nsKeys = nameSpaces.keySet().iterator();
			while (nsKeys.hasNext()) {
				String key = (String) nsKeys.next();
				envelope.addNamespaceDeclaration(key, nameSpaces.get(key));
			}
		}
		//SOAP body
        SOAPBody soapBody = envelope.getBody();
		soapBody.addChildElement(xmlParams);
	}
	
	/**
	 * According to the tag name to find the specific value
	 * @method getResponseInfo
	 * @param iterator
	 * @param side
	 * @return String
	 * @throws JAXBException
	 */
	@SuppressWarnings("unchecked")
	public static String getResponseInfo(Iterator<SOAPElement> iterator,String tagNm) throws JAXBException {
		String result = "";
		while (iterator.hasNext()) {
			SOAPElement element = (SOAPElement) iterator.next();
			if(tagNm.equals(element.getLocalName())){
				result = element.getValue();
				break;
			} else {
				if (null == element.getValue() && element.getChildElements().hasNext()) {
					result = getResponseInfo(element.getChildElements(), tagNm);
				}
			}
		}
		return result;
	}
}
