package com.demo.pdfreader;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import javax.imageio.ImageIO;

import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
import com.sun.pdfview.PDFRenderer;

/**
 * pdf-renderer实现
 * 
 * @author Administrator
 * 
 */
public class PdfRendererApp {

	//private static File file = new File("e:/Eclipse插件开发(原书第3版).pdf");
	private static File file = new File("e:/PWS.pdf");

	public static void main(String[] args) throws InterruptedException,
			IOException {

		RandomAccessFile raf = new RandomAccessFile(file, "r");
		FileChannel channel = raf.getChannel();
		ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0,
				channel.size());
		PDFFile pdffile = new PDFFile(buf);

		for (int i = 1; i <= pdffile.getNumPages(); i++) {
			PDFPage page = pdffile.getPage(i);
			int width = (int) page.getBBox().getWidth();
			int height = (int) page.getBBox().getHeight();
			BufferedImage img = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2 = img.createGraphics();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			PDFRenderer renderer = new PDFRenderer(page, g2, new Rectangle(0,
					0, width, height), null, Color.WHITE);
			page.waitForFinish();
			renderer.run();
			g2.dispose();
			ImageIO.write(img, "jpeg", new File("e:/images/"+i+".jpeg"));
		}
		
		raf.close();

	}

}
