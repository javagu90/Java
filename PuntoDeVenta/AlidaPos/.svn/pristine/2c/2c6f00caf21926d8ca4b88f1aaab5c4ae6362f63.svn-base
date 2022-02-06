package com.alidasoftware.pos.util;

import java.io.IOException;

import org.jdom2.*;
import org.jdom2.output.XMLOutputter;
import org.jdom2.util.NamespaceStack;
import org.jdom2.Namespace;

import com.alidasoftware.pos.model.Factura;


public class CreateXmlFile {
	
	public static void getXml(Factura factura){
		Namespace asd = Namespace.getNamespace("Cfdi");
		System.out.println("entro a getXml");
		Document doc = new Document();
		Element header = new Element("Comprobante", asd);
		System.out.println("luego de crear el header");
		System.out.println("prueba antes de todo " + header.toString());
		System.out.println("luego de agregar un atributo");
		header.setAttribute("version", "3.2");
		System.out.println("luego de agregar otro atributo");
		System.out.println("luego de esto");
		header.addNamespaceDeclaration(asd);
		doc.setRootElement(header);
//		doc.setRootElement(header);
		System.out.println("prueba " + doc.toString());
		XMLOutputter xo = new XMLOutputter();
        try {
			xo.output(doc, System.out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

