package com.demo.pdfreader;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

/**
 * pdfbox 实现
 * 
 * @author Administrator
 * 
 */
public class PdfBoxApp {
	private static File file = new File("e:/PWS.pdf");

	public static void main(String[] args) {
		PDDocument doc = null;
		try {
			doc = PDDocument.load(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (doc == null)
			return;
		PDFRenderer renderer = new PDFRenderer(doc);
		int pageCount = doc.getNumberOfPages();
		for (int i = 0; i < pageCount; i++) {
			BufferedImage image = null;
			try {
				image = renderer.renderImage(i, 2.5f);
			} catch (IOException e) {
				System.out.println(i + " parse error");
			}
			if (image == null)
				continue;
			try {
				ImageIO.write(image, "PNG", new File("e:/images/" + (i+1) + ".png"));
			} catch (IOException e) {
			}
		}
	}

}
