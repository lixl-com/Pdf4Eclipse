package com.demo.pdfreader;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.jpedal.PdfDecoder;
import org.jpedal.exception.PdfException;

public class JpedalApp {
	private static File file = new File("e:/Eclipse插件开发(原书第3版).pdf" );//"e:/a.pdf"
	
	public static void main(String[] args) throws PdfException, IOException {
		int pageNr = 4;
		System.setProperty("org.jpedal.jai", "true");
		PdfDecoder decoder = new PdfDecoder(false);
		decoder.openPdfFile(file.getAbsolutePath());
		int pageNumbers = decoder.getPageCount();
		System.out.println(pageNumbers);
		/*PdfPageData data = decoder.getPdfPageData();
		int cropx = data.getCropBoxX(pageNr);
		int cropy = data.getCropBoxY(pageNr);
		System.out.println("cropx="+cropx+"\t cropy="+cropy);
		int width = data.getCropBoxWidth(pageNr);
		int height = data.getCropBoxHeight(pageNr);
		System.out.println("width="+width+"\t height="+height);*/
		
		decoder.scaling =  2f;
		BufferedImage image = decoder.getPageAsImage(pageNr);
		ImageIO.write(image, "PNG", new File("e:/images/page.png"));
	}

}
