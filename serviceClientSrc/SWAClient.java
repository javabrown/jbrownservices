 

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.xml.namespace.QName;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.om.OMText;
import org.apache.axiom.soap.SOAP11Constants;
import org.apache.axiom.soap.SOAPBody;
import org.apache.axiom.soap.SOAPEnvelope;
import org.apache.axiom.soap.SOAPFactory;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.OperationClient;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.util.CommandLineOption;
import org.apache.axis2.util.CommandLineOptionParser;
import org.apache.axis2.util.OptionsValidator;
import org.apache.axis2.wsdl.WSDLConstants;

public class SWAClient {

	private static EndpointReference targetEPR = new EndpointReference(
			"http://localhost:8080/myservices/services/StockQuoteService");

	public static void main(String[] args) throws Exception {
 
		File file = new File("c:\\test\\astro.jar");
		if (file.exists())
			transferFile(file, "rk-saaj.jar");
		else
			throw new FileNotFoundException();
	}

	public static void transferFile(File file, String destinationFile)
			throws Exception {

		Options options = new Options();
		options.setTo(targetEPR);
		options.setProperty(Constants.Configuration.ENABLE_SWA,
				Constants.VALUE_TRUE);
		options.setSoapVersionURI(SOAP11Constants.SOAP_ENVELOPE_NAMESPACE_URI);
		// Increase the time out when sending large attachments
		options.setTimeOutInMilliSeconds(10000);
		options.setTo(targetEPR);
		options.setAction("urn:uploadFile");

		// assume the use runs this sample at
		// <axis2home>/samples/soapwithattachments/ dir
		ConfigurationContext configContext = ConfigurationContextFactory
				.createConfigurationContextFromFileSystem("c:\\test",
						null);

		ServiceClient sender = new ServiceClient(configContext, null);
		sender.setOptions(options);
		OperationClient mepClient = sender
				.createClient(ServiceClient.ANON_OUT_IN_OP);

		MessageContext mc = new MessageContext();
		FileDataSource fileDataSource = new FileDataSource(file);

		// Create a dataHandler using the fileDataSource. Any implementation of
		// javax.activation.DataSource interface can fit here.
		DataHandler dataHandler = new DataHandler(fileDataSource);
		String attachmentID = mc.addAttachment(dataHandler);

		SOAPFactory fac = OMAbstractFactory.getSOAP11Factory();
		SOAPEnvelope env = fac.getDefaultEnvelope();
		OMNamespace omNs = fac.createOMNamespace(
				"http://service.core.com", "swa");
		OMElement uploadFile = fac.createOMElement("uploadFile", omNs);
		OMElement nameEle = fac.createOMElement("name", omNs);
		nameEle.setText(destinationFile);
		OMElement idEle = fac.createOMElement("attchmentID", omNs);
		idEle.setText(attachmentID);
		uploadFile.addChild(nameEle);
		uploadFile.addChild(idEle);
		env.getBody().addChild(uploadFile);
		mc.setEnvelope(env);

		mepClient.addMessageContext(mc);
		mepClient.execute(true);
		MessageContext response = mepClient
				.getMessageContext(WSDLConstants.MESSAGE_LABEL_IN_VALUE);
	  	SOAPBody body = response.getEnvelope().getBody();
	  	OMElement element = body.getFirstElement().getFirstChildWithName(
	  	new QName("http://service.core.com","return"));
		System.out.println(element.getText());
	}
}
