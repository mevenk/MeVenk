/**
 * 
 */
package util.spring.boot.banner;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 * @author vkolisetty
 *
 */
public class ASCIIArt {

	public static void main(String[] args) throws Exception {

		String[] RGB = { "${Ansi.RED}", "${Ansi.GREEN}", "${Ansi.BLUE}" };

		int width = 100;
		int height = 30;

		// BufferedImage image = ImageIO.read(new
		// File("logo.jpg"));
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		g.setFont(new Font("SansSerif", Font.BOLD, 18));

		Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		graphics.drawString("MeVenk", 10, 20);

		// save this image
		// ImageIO.write(image, "png", new File("ascii-art.png"));

		for (int y = 0; y < height; y++) {
			StringBuilder sb = new StringBuilder();
			for (int x = 0; x < width; x++) {

				sb.append(image.getRGB(x, y) == -16777216 ? " " : "$");

			}

			if (sb.toString().trim().isEmpty()) {
				continue;
			}

			System.out.println(RGB[y % 3] + "" + sb);
		}

		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		g = image.getGraphics();
		g.setFont(new Font("SansSerif", Font.BOLD, 18));

		graphics = (Graphics2D) g;
		graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		graphics.drawString("WebApp", 10, 20);

		for (int y = 0; y < height; y++) {
			StringBuilder sb = new StringBuilder();
			for (int x = 0; x < width; x++) {

				sb.append(image.getRGB(x, y) == -16777216 ? " " : "$");

			}

			if (sb.toString().trim().isEmpty()) {
				continue;
			}

			System.out.println(RGB[y % 3] + "" + sb);
		}

	}

}
