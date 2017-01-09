package appRecomendations;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Utilities {
	// ------------- FROM MODEL -----------------

		public static int MAX_WIDTH = 80;

		public static int MAX_HEIGHT = 80;

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
			// saveImage(bimage, copyPath);
		}
}
