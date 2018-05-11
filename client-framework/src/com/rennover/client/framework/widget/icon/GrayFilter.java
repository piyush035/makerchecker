package com.rennover.client.framework.widget.icon;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
/**
 * 
 * @author Piyush
 *
 */
class GrayFilter extends RGBImageFilter {
	public GrayFilter() {
		canFilterIndexColorModel = true;
	}

	public static Image createDisabledImage(Image i) {
		GrayFilter filter = new GrayFilter();
		ImageProducer prod = new FilteredImageSource(i.getSource(), filter);
		Image grayImage = Toolkit.getDefaultToolkit().createImage(prod);
		return grayImage;
	}

	public int filterRGB(int x, int y, int rgb) {
		int t = (rgb & 0xff000000);
		int r = (rgb & 0x00ff0000) >> 16;
		int g = (rgb & 0x0000ff00) >> 8;
		int b = (rgb & 0x000000ff);

		long l = (r * 30 + g * 50 + b * 20) / 100;

		return (int) (t | l | (l << 8) | (l << 16));
	}
}