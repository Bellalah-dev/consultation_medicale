package Consultation_Medicale;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Test {
    public static void Supprimer(String  cin) throws FileNotFoundException, IOException, JDOMException{
        String fileName = "test.xml";
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File(fileName);
        Document jdomDoc = (Document) builder.build(xmlFile);

        // Get list of User element
        Element rootElement = jdomDoc.getRootElement();
        List < Element > listUserElement = rootElement.getChildren("patient");

        // loop through to edit every User element
        for (Element userElement: listUserElement) {

            // change the name to BLOCK letters
            String cinn = userElement.getAttributeValue("cin");
            if (cin.equals(cinn) ){

                userElement.removeAttribute("cin");
                userElement.removeChild("nom");
                userElement.removeChild("prenom");
                userElement.removeChild("mail");
                userElement.removeChild("sexe");
                userElement.removeChild("adresse");
                System.out.println("deleted succefully !");


            }

        }

        // document is processed and edited successfully, lets save it in new file
        XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
        // output xml to console for debugging
         xmlOutputter.output(jdomDoc,  System.out);
        xmlOutputter.output(jdomDoc, new FileOutputStream("test.xml"));
    }
    public static void Ajouter(String n, String p,String cin, String e, String s, String a) throws JDOMException, IOException {

        Document document = null;
        Element root = null;

        File xmlFile = new File("test.xml");
        if (xmlFile.exists()) {
            // try to load document from xml file if it exist
            // create a file input stream
            FileInputStream fis = new FileInputStream(xmlFile);
            // create a sax builder to parse the document
            SAXBuilder sb = new SAXBuilder();
            // parse the xml content provided by the file input stream and create a Document object
            document = sb.build(fis);
            // get the root element of the document
            root = document.getRootElement();
            fis.close();
        } else {
            // if it does not exist create a new document and new root
            document = new Document();
            root = new Element("patients");
        }


        Element child = new Element("patient");
        child.setAttribute(new Attribute("cin",cin));
        child.addContent(new Element("nom").setText(n));
        child.addContent(new Element("prenom").setText(p));
        child.addContent(new Element("mail").setText(e));
        child.addContent(new Element("sexe").setText(s));
        child.addContent(new Element("adresse").setText(a));
        root.addContent(child);
        document.setContent(root);
        try {
            FileWriter writer = new FileWriter("test.xml");
            XMLOutputter outputter = new XMLOutputter();
            outputter.setFormat(Format.getPrettyFormat());
            outputter.output(document, writer);
            outputter.output(document, System.out);
            writer.close(); // close writer
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public static void Rechercher(String  cin) throws FileNotFoundException, IOException, JDOMException{
        String fileName = "test.xml";
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File(fileName);
        Document jdomDoc = (Document) builder.build(xmlFile);

        // Get list of User element
        Element rootElement = jdomDoc.getRootElement();
        List < Element > listpatientElement = rootElement.getChildren("patient");
            boolean test = false;
        // loop through to edit every User element
        for (int i=0; i< listpatientElement.size();i++) {
            Element patient=listpatientElement.get(i);

            // change the name to BLOCK letters
            String cinn = patient.getAttributeValue("cin");
            if (cin.equals(cinn) ){
                System.out.println("Nom : "
                        + patient.getChild("nom").getText());
                System.out.println("Prénom : "
                        + patient.getChild("prenom").getText());
                System.out.println("Adresse mail : "
                        + patient.getChild("mail").getText());
                System.out.println("Sexe : "
                        + patient.getChild("sexe").getText());
                System.out.println("Adresse postale : "
                        + patient.getChild("adresse").getText());
                test = true;

            }

            }
        if (test==false){
            System.out.println("Element not found");
        }



}
    public static void Modifier(String n, String p,String cin, String e, String s, String a) throws FileNotFoundException, IOException, JDOMException{
        String fileName = "test.xml";
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File(fileName);
        Document jdomDoc = (Document) builder.build(xmlFile);

        // Get list of User element
        Element rootElement = jdomDoc.getRootElement();
        List < Element > listUserElement = rootElement.getChildren("patient");

        // loop through to edit every User element
        for (Element userElement: listUserElement) {

            // change the name to BLOCK letters
            String cinn = userElement.getAttributeValue("cin");
            if (cin.equals(cinn) ){
                userElement.getAttribute("cin").setValue(cin);
                userElement.getChild("nom").setText(n);
                userElement.getChild("prenom").setText(p);
                userElement.getChild("mail").setText(e);
                userElement.getChild("sexe").setText(s);
                userElement.getChild("adresse").setText(a);

            }

        }

        //stockage de resultat dans le fichier xml
        XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
        xmlOutputter.output(jdomDoc, new FileOutputStream("test.xml"));
    }
}
