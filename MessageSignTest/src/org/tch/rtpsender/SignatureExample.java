package org.tch.rtpsender;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.Security;
import java.security.Signature;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.keyinfo.X509IssuerSerial;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.SignatureMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;

public class SignatureExample
{
    // Private keystore path/file in PKCS512 format (*.p12 file extension)
    private static final String DEFAULT_KEYSTORE_PATH_AND_FILENAME = "F:/devtest.p12";
    
    // Key alias used to find the private key in the key store
    private static final String KEY_ALIAS = "bt.tchrtp.org";
    
    // Password used to access the PKCS512 keystore
    private static final String KEYSTORE_PASSWORD = "devtest";
    
    // Public certificate path/filename for the private key to 
    private static final String DEFAULT_PUBLIC_CERTIFICATE_PATH_AND_FILENAME = "F:/devtest.cer";

    // Location in XML document to place the generated XMOL enveloped signature fragment
//    private static final String RTPS_SIGNATURE_PLACEHOLDER_TAG = "head:Sgntr";
    private static final String RTPS_SIGNATURE_PLACEHOLDER_TAG = "head:Sgntr";

    private static final String USAGE_STRING = "Usage: java ... org.tch.rtp.util.SignatureExample <XML input file>\nSigned XML output printed to console";

    private static final String KEYSTORE_PATH_AND_FILENAME_KEY = "keystore.path.filename";

    private static final String PUBLIC_CERTIFICATE_PATH_AND_FILENAME_KEY = "public.certificate.path.filename";
    
    private static final String SOFTWARE_SIGNING_KEY_ALIAS_KEY = "software.signing.key.alias";
    private static final String SOFTWARE_SIGNING_KEYSTORE_PASSWORD_KEY = "software.signing.key.store.password";
    
    private static XMLSignatureFactory xmlSignatureFactory;
    
    private static X509Certificate _publicCertificate;
    
    private static PrivateKey _privateKey;
    private static KeyInfo _certificateKeyInformation;
    
    private SignedInfo _signedInfo;
    private XMLSignature _signature;
    private TransformerFactory _transformerFactory;
    private DocumentBuilderFactory _dbf;
    
    // Only load the private key and certificate once.
    static
    {
//        String keyStorePathFilename = System.getProperty(KEYSTORE_PATH_AND_FILENAME_KEY, DEFAULT_KEYSTORE_PATH_AND_FILENAME);
//        String publicCertificatePathFilename = System.getProperty(PUBLIC_CERTIFICATE_PATH_AND_FILENAME_KEY, DEFAULT_PUBLIC_CERTIFICATE_PATH_AND_FILENAME);
//    
//        
        String keyStorePathFilename = "C:\\RTPLoadTesters\\QC\\loadtester\\bt.tchrtp.org.p12";
        String publicCertificatePathFilename = "C:\\RTPLoadTesters\\QC\\loadtester\\bt.tchrtp.org.cer";
     
//        String keyStorePathFilename = "C:\\Users\\subarna.gaine\\Documents\\RTP\\invalidcerts\\devtest.p12";
//        String publicCertificatePathFilename = "C:\\Users\\subarna.gaine\\Documents\\RTP\\invalidcerts\\devtest.cer";
//     
        
        // Create a Reference to the enveloped document (in this case we are
        // signing the whole document, so a URI of "" signifies that) and
        // also specify the SHA1 digest algorithm and the ENVELOPED Transform.
        if ((keyStorePathFilename != null) && !keyStorePathFilename.isEmpty())
        {
            _privateKey = getPrivateKey(keyStorePathFilename);
        }
        else
        {
            _privateKey = getPrivateKey(DEFAULT_KEYSTORE_PATH_AND_FILENAME);
        }
        
        if ((publicCertificatePathFilename != null) && !publicCertificatePathFilename.isEmpty())
        {
            _certificateKeyInformation = getCertificateX509Information(publicCertificatePathFilename);
        }
        else
        {
            _certificateKeyInformation = getCertificateX509Information(DEFAULT_PUBLIC_CERTIFICATE_PATH_AND_FILENAME);
        }
    }
    
    public SignatureExample()
    {
        if (_privateKey == null)
        {
            System.err.println("Could not retrieve private key from keystore. Exiting.");
        }

        _signedInfo = getCertificateSignedInformation();

        if (_signedInfo == null)
        {
            System.err.println("Could not retrieve public certificate signing information. Exiting.");
        }

        if (_certificateKeyInformation == null)
        {
            System.err.println("Could not create X509 certificate information from public certificate. Exiting.");
        }
        
//        // Create the XMLSignature (but don't sign it yet)
//        _signature = xmlSignatureFactory.newXMLSignature(_signedInfo, _certificateKeyInformation);
//
        _transformerFactory = TransformerFactory.newInstance();
        _dbf = DocumentBuilderFactory.newInstance();
        
    }

    public String getSignedXMLMessageStringFromFile(String xmlInputPathAndFile)
    {
        Document xmlDocument = null;
        String signedXMLString = "";
        
        try
        {
            xmlDocument = unmarshalXMLFile(new FileInputStream(xmlInputPathAndFile));
            signedXMLString = getSignedXMLMessageString(xmlDocument);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        
        return signedXMLString;
    }
    
    public Document unmarshalXML(String xmlString)
    {
        ByteArrayInputStream xmlInputStream = new ByteArrayInputStream(xmlString.getBytes());
        return unmarshalXMLFile(xmlInputStream);
    }
    
    /**
     * Helper to create the XML Enveloped Signature for this RTPS message
     * @param xmlInputPathAndFile Path and filename to an XML document for signing
     * @return Signed XML document string
     */
    public String getSignedXMLMessageString(Document xmlDocument)
    {
        Document unmarshaledXMLDocument = xmlDocument;
        
        // Find the XML document placeholder tag for the enveloped signature
        Node signatureParentNode = unmarshaledXMLDocument.getElementsByTagName(RTPS_SIGNATURE_PLACEHOLDER_TAG).item(0);

        // Only move forward with signing if the <Sgntr> placeholder tag in the Business Application Header is present
        if (signatureParentNode != null)
        {
            unmarshaledXMLDocument = applySignature(_privateKey, signatureParentNode, _signedInfo, _certificateKeyInformation, unmarshaledXMLDocument);
        }
        else
        {
            System.err.println("Can't find " + RTPS_SIGNATURE_PLACEHOLDER_TAG + " parent node. Not signing document. XML: " + convertDOMDocumentToString(unmarshaledXMLDocument));
        }
        
        return convertDOMDocumentToString(unmarshaledXMLDocument);
    }

    /**
     * Helper to load an XML file
     * @param xmlInputPathAndFile Path and filename of XML file
     * @return DOM Document
     */
    private Document unmarshalXMLFile(InputStream xmlStream)
    {
        // Instantiate the document to be signed
        _dbf.setNamespaceAware(true);

        Document documentObjectModel = null;
        
        // Parse/unmarshal the input XML file into a DOM object
        try
        {
            documentObjectModel = _dbf.newDocumentBuilder().parse(xmlStream);
        }
        catch (IOException ioe)
        {
            System.err.println("Could not load the XML input file. Ex: " + ioe.getMessage());
        }
        catch (SAXException | ParserConfigurationException e)
        {
            System.err.println("Failed to parse XML message. Exception: " + e.getMessage());
            e.printStackTrace();
        }
        
        if (documentObjectModel == null)
        {
            System.err.println("Failed to parse XML message.");
        }

        return documentObjectModel;
    }
    
    /**
     * Helper to apply digital signature
     * @param privateKey Private key used for signing
     * @param signatureParentNode Location in XML document to store the signature
     * @param signedInfo Metadata about the signature details being used
     * @param certificateKeyInformation Public certificate info (i.e. X509 data)
     * @param documentObjectModel DOM Document of XML Document
     * @return
     */
    private Document applySignature(PrivateKey privateKey, Node signatureParentNode, SignedInfo signedInfo, KeyInfo certificateKeyInformation, Document documentObjectModel)
    {
        // Create a DOMSignContext and specify the DSA PrivateKey and
        // location of the resulting XMLSignature's parent element
        DOMSignContext dsc = new DOMSignContext(privateKey, signatureParentNode);

        // Marshal, generate (and sign) the enveloped signature 
        try
        {
            // Create the XMLSignature (but don't sign it yet)
            _signature = xmlSignatureFactory.newXMLSignature(_signedInfo, _certificateKeyInformation);

            // Sign the document now
            _signature.sign(dsc);
        }
        catch (MarshalException | XMLSignatureException e)
        {
            System.err.println("Failed to sign and output XML message. Exception: " + e.getMessage());
            e.printStackTrace();
        }
        
        return documentObjectModel;
    }
    
    public String convertDOMDocumentToString(Document domDocument)
    {
        // Create a String Writer to dump the XML output into
        StringWriter stringWriter = new StringWriter();
        StreamResult result = new StreamResult(stringWriter);
        Writer out = new StringWriter();

        // The transformer will take the DOM and make an XML string out of it
        Transformer trans;
        try
        {
            trans = _transformerFactory.newTransformer();
            trans.transform(new DOMSource(domDocument), result);
            
            // This code "pretty prints" the XML with indention and line feeds
            OutputFormat format = new OutputFormat(domDocument);
//            format.setLineWidth(65);
//            format.setIndenting(true);
//            format.setIndent(2);
            XMLSerializer serializer = new XMLSerializer(out, format);
            serializer.serialize(domDocument);
        }
        catch (IOException | TransformerException e)
        {
            System.err.println("Failed to transform DOM Document to XML String. Ex: " + e.getMessage());
        }
        
        return out.toString();
    }
    
    /**
     * Helper method to load a private key from a PKCS12 keystore file
     * @param privateKeyPathAndFilename Path and filename to keystore file
     * @return Private key used for signing XML document
     */
    private static PrivateKey getPrivateKey(String privateKeyPathAndFilename)
    {
                if (_privateKey != null)
                {
                                System.out.println("Private key loaded. Returning.");
                }
                else
                {
//                        String keyAlias = System.getProperty(SOFTWARE_SIGNING_KEY_ALIAS_KEY, KEY_ALIAS);
//                        String keyStorePassword = System.getProperty(SOFTWARE_SIGNING_KEYSTORE_PASSWORD_KEY, KEYSTORE_PASSWORD);
//                    
                        String keyAlias = "bt.tchrtp.org";
                        String keyStorePassword = "devtest";
                   
                        
                        // Open the PKCS512 keystore file to access the private key
                        FileInputStream kis;
                        try
                        {
                            KeyStore keystore = KeyStore.getInstance("PKCS12");
                            
                            kis = new FileInputStream(new File(privateKeyPathAndFilename));
                            keystore.load(kis, keyStorePassword.toCharArray());
                            kis.close();
                            
                            // Retrieve private key from keystore
                            _privateKey = (PrivateKey)keystore.getKey(keyAlias, keyStorePassword.toCharArray());
                        }
                        catch (NoSuchAlgorithmException | CertificateException | IOException | KeyStoreException | UnrecoverableKeyException e)
                        {
                            System.err.println("Could not load private key. " + e.getMessage());
                            e.printStackTrace();
                        } 
                }
        
                return _privateKey;
    }

    /**
     * Helper to retrieve X509 information from public certificate
     * @param certificatePathAndFilename Path and filename of public certificate
     * @return X509 information from public certificate
     */
    private static KeyInfo getCertificateX509Information(String certificatePathAndFilename)
    {
                if (_certificateKeyInformation == null)
                {
                                System.out.println("Private key loaded. Returning.");
                        XMLSignatureFactory xmlSignatureFactory = getXMLSignatureFactory();
                        
                        // This try/catch below does the following:
                        //  - Load the public certificate for this private key
                        //  - Extract information from public certificate for X509 fields of XML enveloped signature
                        try
                        {
                            // Open the public certificate used to retrieve X509 data for signature tags in XML
                            File publicCertificateFile = new File(certificatePathAndFilename);
                            FileInputStream publicCertificateFileStream = new FileInputStream(publicCertificateFile);
                    
                            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
                            X509Certificate cert = (X509Certificate) certificateFactory.generateCertificate(publicCertificateFileStream);
                            
                            // Save reference to public certificate for valdiation step
                            _publicCertificate = cert;
                            
                            // Create a KeyValue containing the DSA PublicKey that was generated
                            KeyInfoFactory kif = xmlSignatureFactory.getKeyInfoFactory();
                            
                            // Set up the certificate's X509 key info data
                            X509IssuerSerial x509Issuer = kif.newX509IssuerSerial(cert.getSubjectX500Principal().getName(), cert.getSerialNumber());
                            List<Object> x509Content = new ArrayList<Object>();
                            x509Content.add(cert.getSubjectX500Principal().getName());
                            x509Content.add(x509Issuer);
                            
                            X509Data xd = kif.newX509Data(x509Content);
                            
                            List keyInfoItems = new ArrayList();
                            keyInfoItems.add(xd);
                            
                            // Create a KeyInfo and add the KeyValue to it. This is the X509 info used in the XML enveloped signature
                            _certificateKeyInformation = kif.newKeyInfo(keyInfoItems);
                        }
                        catch (CertificateException | IOException e1)
                        {
                            System.err.println("Failed to set up public keys for signing. Exception: " + e1.getMessage());
                           e1.printStackTrace();
                        }
                }
                
        return _certificateKeyInformation;
    }
    
    /**
     * Helper to create signed information structure for XML enveloped signature
     * @return Signed Information object
     */
    private SignedInfo getCertificateSignedInformation()
    {
        Transform envTransform = null;
        SignedInfo signedInfo = null;
        XMLSignatureFactory xmlSignatureFactory = getXMLSignatureFactory();
        List<Transform> transforms = new ArrayList<Transform>();
        
        // RTPS uses EXCLUSIVE Canonicalization for XML enveloped signatures.
        // RTPS uses the SHA256 hashing algorithm for signing 
        // Set this up in the SignedInfo object
        try
        {
            envTransform = xmlSignatureFactory.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null);
            Transform exc14n11Transform = xmlSignatureFactory.newTransform("http://www.w3.org/2001/10/xml-exc-c14n#", (TransformParameterSpec) null);
//            Transform exc14n11Transform = xmlSignatureFactory.newTransform("http://www.w3.org/TR/2001/REC-xml-c14n-20010315", (TransformParameterSpec) null);
            transforms.add(envTransform);
            transforms.add(exc14n11Transform);
            
            Reference ref = xmlSignatureFactory.newReference("", xmlSignatureFactory.newDigestMethod(DigestMethod.SHA256, null), transforms, null, null);
    
            // Create the SignedInfo
            signedInfo = xmlSignatureFactory.newSignedInfo(
                    xmlSignatureFactory.newCanonicalizationMethod(CanonicalizationMethod.EXCLUSIVE, (C14NMethodParameterSpec) null),
                    xmlSignatureFactory.newSignatureMethod("http://www.w3.org/2001/04/xmldsig-more#rsa-sha256", (SignatureMethodParameterSpec) null), Collections.singletonList(ref));
//                  xmlSignatureFactory.newSignatureMethod(SignatureMethod.RSA_SHA1, null), Collections.singletonList(ref));
    
        }
        catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException e1)
        {
            System.err.println("Failed to set up signature fields. Exception: " + e1.getMessage());
            e1.printStackTrace();
        }
        
        return signedInfo;
    }

    /**
     * Helper to get XML Signature Factory singleton
     * @return XML Signature Factory
     */
    private static XMLSignatureFactory getXMLSignatureFactory()
    {
        if (xmlSignatureFactory == null)
        {
            // Create a DOM XMLSignatureFactory that will be used to generate the enveloped signature
            String providerName = System.getProperty("jsr105Provider", "org.jcp.xml.dsig.internal.dom.XMLDSigRI");
            
            // Dynamically instantiate DOM XMLDSig factory from class/package specified above
            try
            {
                xmlSignatureFactory = XMLSignatureFactory.getInstance("DOM", (Provider) Class.forName(providerName).newInstance());
            }
            catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1)
            {
                System.err.println("Failed to get instance of XMLSignatureFactory. Exception: " + e1.getMessage());
                e1.printStackTrace();
            }
        }
        
        return xmlSignatureFactory;
    }

    public boolean isSignatureValid(String xmlDocumentString)
    {
        boolean valid = false;
        
        Document doc = unmarshalXMLFile(new ByteArrayInputStream(xmlDocumentString.getBytes()));
        
        // Find Signature element.
        NodeList nl = doc.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature");

        DOMValidateContext valContext = null;
        XMLSignatureFactory factory = null;
        XMLSignature signature = null;
        
        if (nl.getLength() > 0) 
        {
            try
            {
                // Create a DOMValidateContext. Pass the public key and document context.
                valContext = new DOMValidateContext(_publicCertificate.getPublicKey(), nl.item(0));
                factory = XMLSignatureFactory.getInstance("DOM");
                signature = factory.unmarshalXMLSignature(valContext);
                
                valid = signature.validate(valContext);
            }
            catch (MarshalException | XMLSignatureException e)
            {
                System.err.println("Failed to validate signature. Ex: " + e.getMessage());
            }
        }

        return valid;
    }

    public final static void main(String[] args)
    {
        if (args == null)
        {
            System.err.println("Missing XML input filename");
            System.err.println(USAGE_STRING);
            System.exit(0);
        }
        
        if (args.length != 1)
        {
            System.err.println("Invalid argument list");
            System.err.println(USAGE_STRING);
            System.exit(0);
        }
        
        if (args[0].equalsIgnoreCase("-h") || args[0].equalsIgnoreCase("-?"))
        {
            System.err.println(USAGE_STRING);
            System.exit(0);
        }
        
        // Command line arguments look good. Attempt to sign the XML document specified.
        SignatureExample example = new SignatureExample();
        String xmlSignedDocument = example.getSignedXMLMessageStringFromFile(args[0]);
        
        if (xmlSignedDocument != null)
        {
            System.out.println(xmlSignedDocument);
            System.out.println("Is signature valid: " + example.isSignatureValid(xmlSignedDocument));
        }
        else
        {
            System.err.println("Failed to sign XML document. Document: " + args[0]);
        }
    }
}
