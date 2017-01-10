package appRecomender;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;

public class Utilities {

		public static int MAX_WIDTH = 150;

		public static int MAX_HEIGHT = 150;

		public static BufferedImage loadImage(String pathName) {
			BufferedImage bimage = null;
			try {
				bimage = ImageIO.read(new File(pathName));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return bimage;
		}

		public static BufferedImage resize(BufferedImage bufferedImage, int newW,
				int newH) {
			int w = bufferedImage.getWidth();
			int h = bufferedImage.getHeight();
			BufferedImage bufim = new BufferedImage(newW, newH,
					bufferedImage.getType());
			Graphics2D g = bufim.createGraphics();
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
					RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.drawImage(bufferedImage, 0, 0, newW, newH, 0, 0, w, h, null);
			g.dispose();
			return bufim;
		}

		public static ImageIcon resizeImage(String filePath) {
			BufferedImage bimage = loadImage(filePath);
			if (bimage.getHeight() > bimage.getWidth()) {
				int heigt = (bimage.getHeight() * MAX_WIDTH) / bimage.getWidth();
				bimage = resize(bimage, MAX_WIDTH, heigt);
				int width = (bimage.getWidth() * MAX_HEIGHT) / bimage.getHeight();
				bimage = resize(bimage, width, MAX_HEIGHT);
			} else {
				int width = (bimage.getWidth() * MAX_HEIGHT) / bimage.getHeight();
				bimage = resize(bimage, width, MAX_HEIGHT);
				int heigt = (bimage.getHeight() * MAX_WIDTH) / bimage.getWidth();
				bimage = resize(bimage, MAX_WIDTH, heigt);
			}
			return new ImageIcon(bimage);
		}
		
		public static void generateTextPane(JTextPane component, String constant,
				int fontSize, Color fontColor) {
			component.setText(constant);
			component.setBorder(null);
			component.setFont(new Font("Monospaced", Font.PLAIN, fontSize));
			component.setForeground(fontColor);
			component.setOpaque(false);
			component.setEditable(false);
		}
}
