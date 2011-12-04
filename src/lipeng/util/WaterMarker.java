package lipeng.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class WaterMarker {

	public enum Position {
		left, top, right, bottom, center, leftTop, rightTop, leftBottom, rightBottom
	}

	private final File source;

	private String text = null;

	private File waterImage = null;

	private Font font = new Font(null, Font.PLAIN, 12);

	private Color color = Color.WHITE;

	private Position position = Position.rightBottom;

	public WaterMarker(File source, String text) {
		this.source = notNull(source);
		this.text = notNull(text);
	}

	public WaterMarker(File source, File water) {
		this.source = notNull(source);
		this.waterImage = notNull(water);
	}

	public WaterMarker setFontColor(Font font, Color color) {
		this.font = notNull(font);
		this.color = notNull(color);
		return this;
	}

	public WaterMarker setPosition(Position position) {
		this.position = notNull(position);
		return this;
	}

	public void generateTo(File target) throws IOException {
		notNull(target);

		Image sourceImage = ImageIO.read(source);
		int width = sourceImage.getWidth(null);
		int height = sourceImage.getHeight(null);

		BufferedImage imageContainer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = imageContainer.getGraphics();
		g.drawImage(sourceImage, 0, 0, width, height, null);
		g.setFont(this.font);
		g.setColor(this.color);

		if (this.text != null) {
			FontMetrics metrics = g.getFontMetrics();
			int[] xy = calcPosition(width, height, metrics.stringWidth(text), metrics.getHeight(), position);
			g.drawString(text, xy[0], xy[1] + metrics.getLeading() + metrics.getAscent());
		} else {
			Image water = ImageIO.read(waterImage);
			int[] xy = calcPosition(width, height, water.getWidth(null), water.getHeight(null), position);
			g.drawImage(water, xy[0], xy[1], water.getWidth(null), water.getHeight(null), null);
		}
		g.dispose();

		OutputStream out = null;
		try {
			out = new FileOutputStream(target);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(imageContainer);
		} finally {
			closeQuietly(out);
		}
	}

	private static void closeQuietly(OutputStream stream) {
		if (stream != null) {
			try {
				stream.close();
			} catch (IOException e) {
				// ignore
			}
		}
	}

	private static int[] calcPosition(int imageWidth, int imageHeight, int waterWidth, int waterHeight, Position position) {
		int widthDiff = imageWidth - waterWidth;
		int heightDiff = imageHeight - waterHeight;
		switch (position) {
		case top:
			return new int[] { widthDiff / 2, 0 };
		case rightTop:
			return new int[] { widthDiff, 0 };
		case left:
			return new int[] { 0, heightDiff / 2 };
		case center:
			return new int[] { widthDiff / 2, heightDiff / 2 };
		case right:
			return new int[] { widthDiff, heightDiff / 2 };
		case leftBottom:
			return new int[] { 0, heightDiff };
		case bottom:
			return new int[] { widthDiff / 2, heightDiff };
		case rightBottom:
			return new int[] { widthDiff, heightDiff };
		case leftTop:
		default:
			return new int[] { 0, 0 };
		}
	}

	private static <T> T notNull(T argument) {
		if (argument == null) {
			throw new IllegalArgumentException();
		}
		return argument;
	}

	public static void main(String[] args) throws IOException {
		File sourceImage = new File("c:/source.jpg");

		// Use text
		new WaterMarker(sourceImage, "MyTest").setPosition(Position.leftTop).generateTo(new File("c:/text.leftTop.jpg"));
		new WaterMarker(sourceImage, "MyTest").setPosition(Position.top).generateTo(new File("c:/text.top.jpg"));
		new WaterMarker(sourceImage, "MyTest").setPosition(Position.rightTop).generateTo(new File("c:/text.rightTop.jpg"));
		new WaterMarker(sourceImage, "MyTest").setPosition(Position.left).generateTo(new File("c:/text.left.jpg"));
		new WaterMarker(sourceImage, "MyTest").setPosition(Position.center).generateTo(new File("c:/text.center.jpg"));
		new WaterMarker(sourceImage, "MyTest").setPosition(Position.right).generateTo(new File("c:/text.right.jpg"));
		new WaterMarker(sourceImage, "MyTest").setPosition(Position.leftBottom).generateTo(new File("c:/text.leftBottom.jpg"));
		new WaterMarker(sourceImage, "MyTest").setPosition(Position.bottom).generateTo(new File("c:/text.bottom.jpg"));
		new WaterMarker(sourceImage, "MyTest").setPosition(Position.rightBottom).generateTo(new File("c:/text.rightBottom.jpg"));

		// Use image
		File waterImage = new File("c:/water.jpg");
		new WaterMarker(sourceImage, waterImage).setPosition(Position.leftTop).generateTo(new File("c:/water.leftTop.jpg"));
		new WaterMarker(sourceImage, waterImage).setPosition(Position.top).generateTo(new File("c:/water.top.jpg"));
		new WaterMarker(sourceImage, waterImage).setPosition(Position.rightTop).generateTo(new File("c:/water.rightTop.jpg"));
		new WaterMarker(sourceImage, waterImage).setPosition(Position.left).generateTo(new File("c:/water.left.jpg"));
		new WaterMarker(sourceImage, waterImage).setPosition(Position.center).generateTo(new File("c:/water.center.jpg"));
		new WaterMarker(sourceImage, waterImage).setPosition(Position.right).generateTo(new File("c:/water.right.jpg"));
		new WaterMarker(sourceImage, waterImage).setPosition(Position.leftBottom).generateTo(new File("c:/water.leftBottom.jpg"));
		new WaterMarker(sourceImage, waterImage).setPosition(Position.bottom).generateTo(new File("c:/water.bottom.jpg"));
		new WaterMarker(sourceImage, waterImage).setPosition(Position.rightBottom).generateTo(new File("c:/water.rightBottom.jpg"));
	}
}
