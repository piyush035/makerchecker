package com.rennover.client.framework.widget.icon;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;

class BrightFilter extends RGBImageFilter {

	private int m_percent;

	public BrightFilter(int percent) {
		m_percent = percent;
		canFilterIndexColorModel = true;
	}

	public static Image createBrightImage(Image i, int percent) {
		BrightFilter filter = new BrightFilter(percent);
		ImageProducer prod = new FilteredImageSource(i.getSource(), filter);
		Image grayImage = Toolkit.getDefaultToolkit().createImage(prod);
		return grayImage;
	}

	public int filterRGB(int x, int y, int rgb) {
		int t = (rgb & 0xff000000) >> 24;
		int r = (rgb & 0x00ff0000) >> 16;
		int g = (rgb & 0x0000ff00) >> 8;
		int b = (rgb & 0x000000ff);

		r = bright(r, m_percent);
		g = bright(g, m_percent);
		b = bright(b, m_percent);

		return (t << 24 | b | (g << 8) | (r << 16));
	}

	public int bright(int c, int percent) {
		c = (255 - ((255 - c) * (100 - percent) / 100));
		c = c < 0 ? 0 : c;
		c = c > 255 ? 255 : c;
		return c;
	}
}