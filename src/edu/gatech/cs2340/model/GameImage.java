package edu.gatech.cs2340.model;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import edu.gatech.cs2340.view.GameWindow;

/**
 * Represents an image to be used by a view
 * 
 * @author Jon
 * 
 */
public class GameImage implements Drawable, Comparable<Object> {
	private VolatileImage vi;
	private int x, y, halfwidth, halfheight, layer;
	private double theta;
	private String imagePath;
	/**
	 * Creates a GameImage object using specified filepath and transparent
	 * background
	 * 
	 * @param filepath
	 *            - file to load image from
	 * @param inx
	 *            - x coordinate of center of image
	 * @param iny
	 *            - y coordinate of center of image
	 * @param inlayer
	 *            - the z-coordinate at which to draw this picture. Higher
	 *            values are drawn over lower values
	 */
	public GameImage(String filepath, int inx, int iny, int inlayer) {
		imagePath = filepath;
		try {
			vi = GameWindow.loadVIResource(imagePath, true);
		} catch (NullPointerException error) {
			throw new NullPointerException("The requested image was: "
					+ filepath);
		}
		x = inx;
		y = iny;
		layer = inlayer;
		halfwidth = vi.getWidth() / 2;
		halfheight = vi.getHeight() / 2;
		theta = 0;
	}

	/**
	 * Draws the image
	 * 
	 * @param g
	 *            - Graphics object supplied by the calling view
	 */
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform at = g2.getTransform();
		g2.translate(x, y);
		g2.rotate(theta);
		g2.translate(-x, -y);
		g2.drawImage(vi, x - halfwidth, y - halfheight, null);
		g2.setTransform(at);
	}

	/**
	 * Sets the layer at which the image is drawn (higher numbers are drawn over
	 * top of lower numbers)
	 * 
	 * @param in
	 */
	public void setLayer(int in) {
		layer = in;
	}

	public int getLayer() {
		return layer;
	}

	/**
	 * Sets the x coordinate at the center of the image
	 * 
	 * @param in
	 *            - the x coordinate of the center of the image.
	 */
	public void setX(int in) {
		x = in;
	}

	/**
	 * Sets the y coordinate at the center of the image
	 * 
	 * @param in
	 *            The y coordinate of the center of the image.
	 */
	public void setY(int in) {
		y = in;
	}

	/**
	 * Implements Comparable interface. Allows GameImages to be sorted correctly
	 * according to their layer parameter.
	 */
	public int compareTo(Object obj) {
		GameImage gi = (GameImage) obj;
		if (layer < gi.getLayer()) {
			return -1;
		}
		if (layer > gi.getLayer()) {
			return 1;
		}
		return 0;
	}

	/**
	 * Returns this image's bounding box
	 * 
	 * @return the bounds
	 */
	public Rectangle getBounds() {
		return new Rectangle(x - halfwidth, y - halfheight, halfwidth * 2,
				halfheight * 2);
	}

	/**
	 * disposes of the vi
	 */
	public void dispose() {
		vi.flush();
	}

	/**
	 * Rotates the GameImage
	 * 
	 * @param rads
	 */
	public void setAngle(double rads) {
		theta = rads;
	}
	/**
	 * Flips the image horizontally or vertically
	 * @param horizontal - whether the image should be flipped horizontally 
	 * (true) or vertically (false)
	 */
	public void flip(boolean horizontal)
	{
		if(horizontal)
		{
			Graphics2D g2 = vi.createGraphics();
			g2.setComposite(AlphaComposite.Src);
			g2.setColor(new Color(0, 0, 0, 0));
			g2.fillRect(0, 0, vi.getWidth(), vi.getHeight());
			g2.scale(-1, 1);
			BufferedImage bi = null;
			try {
				bi = ImageIO.read(new File(imagePath));
			} catch (IOException e) {
				e.printStackTrace();
			}
			g2.drawImage(bi, -bi.getWidth(), 0, null);
		}
		else
		{
			Graphics2D g2 = vi.createGraphics();
			g2.setComposite(AlphaComposite.Src);
			g2.setColor(new Color(0, 0, 0, 0));
			g2.fillRect(0, 0, vi.getWidth(), vi.getHeight());
			g2.scale(1, -1);
			BufferedImage bi = null;
			try {
				bi = ImageIO.read(new File(imagePath));
			} catch (IOException e) {
				e.printStackTrace();
			}
			g2.drawImage(bi, 0, -bi.getHeight(), null);
		}
	}
}
