/********************************************************************
 * Copyright (C) 2005-06
 * IFS
 * All Rights Reserved 
 *
 * File: ThumbNail.java
 ********************************************************************
 *
 *  Ver   Date              Name               Description
 *  ====  ========          ============       ==================
 *  3.0  Dec 28, 2006           Sreeram J		   Created
/ ********************************************************************/

package com.churchgroup.util.image;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @author Sreeram J
 * @mailTO: jsreeram29@yahoo.com
 * 
 */
public class ThumbNail {

	/**
	 * This constructor is added to set the image dimensions outside of this
	 * class.
	 */
	public ThumbNail(int width, int height) {
		THUMB_NAIL_HEIGHT = height;
		THUMB_NAIL_WIDTH = width;
		if (height > width)
			largestDimension = height;
		else
			largestDimension = width;
	}

	public ThumbNail() {
		THUMB_NAIL_HEIGHT = 111;
		THUMB_NAIL_WIDTH = 111;
		largestDimension = 111;
	}

	protected final Log log = LogFactory.getLog(getClass());
	private MediaTracker mediaTracker;
	private Image inImage;

	// private static final Log log = LogFactory.getLog(PhotoAlbum.class);
	public int THUMB_NAIL_HEIGHT;
	public int THUMB_NAIL_WIDTH;
	public int THUMB_QUALITY = 800;
	public int largestDimension;

	public int getLargestDimension() {
		return largestDimension;
	}

	public void setLargestDimension(int largestDimension) {
		this.largestDimension = largestDimension;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			ThumbNail thumbNail = new ThumbNail();
			thumbNail.createThumbNail(
					"C:/Users/uroom11/Documents/0785229213.jpg",
					"C:/Users/uroom11/Documents/0785229213_thumb.jpg");
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public Image loadImageFromURL(String fileName) throws IOException {
		return Toolkit.getDefaultToolkit().getImage(fileName);
	}

	/**
	 * @return the mediaTracker
	 */
	public MediaTracker getMediaTracker() {
		if (this.mediaTracker == null) {
			this.mediaTracker = new MediaTracker(new Container());
		}
		return this.mediaTracker;
	}

	/**
	 * @param mediaTracker
	 *            the mediaTracker to set
	 */
	public void setMediaTracker(MediaTracker mediaTracker) {
		this.mediaTracker = mediaTracker;
	}

	public void createThumbNail(String inFileName, String outFileName) {

		try {
			double scale;
			double scaledW;
			double scaledH;
			
			int sizeDifference, originalImageLargestDim;

			if (!inFileName.endsWith(".jpg") && !inFileName.endsWith(".JPG")
					&& !inFileName.endsWith(".jpeg")
					&& !inFileName.endsWith(".JPEG")
					&& !inFileName.endsWith(".gif")
					&& !inFileName.endsWith(".GIF")
					&& !inFileName.endsWith(".png")
					&& !inFileName.endsWith(".PNG")) {
				System.out
						.println("Error: Unsupported image type, please only either JPG, GIF or PNG");
			} else {
				 inImage = loadImageFromURL(inFileName);
                 getMediaTracker().addImage(inImage, 0);
                 getMediaTracker().waitForID(0);

				if (inImage.getWidth(null) == -1
						|| inImage.getHeight(null) == -1) {
					log.debug("Error loading file: \"" + inFileName+ "\"");
				} else {
					// find biggest dimension
					if (inImage.getWidth(null) > inImage.getHeight(null)) {
						scale = (double) largestDimension
								/ (double) inImage.getWidth(null);
						sizeDifference = inImage.getWidth(null)
								- largestDimension;
						originalImageLargestDim = inImage.getWidth(null);
					} else {
						scale = (double) largestDimension
								/ (double) inImage.getHeight(null);
						sizeDifference = inImage.getHeight(null)
								- largestDimension;
						originalImageLargestDim = inImage.getHeight(null);
					}
					// create an image buffer to draw to
					BufferedImage outImage = new BufferedImage(111, 111,
							BufferedImage.TYPE_INT_RGB); // arbitrary init so
															// code compiles
					Graphics2D g2d;
					AffineTransform tx;
					// only scale if desired size is smaller than original
					if (scale < 1.0d) 
					{
						if (sizeDifference > 100) {
							int numSteps = sizeDifference / 100;
							int stepSize = sizeDifference / numSteps;
							int stepWeight = stepSize / 2;
							int heavierStepSize = stepSize + stepWeight;
							int lighterStepSize = stepSize - stepWeight;
							int currentStepSize, centerStep;
							scaledW = inImage.getWidth(null);
							scaledH = inImage.getHeight(null);
							if (numSteps % 2 == 1) // if there's an odd number of steps
								centerStep = (int) Math
										.ceil((double) numSteps / 2d); // find the center step
							else
								centerStep = -1; // set it to -1 so it's ignored later
							Integer intermediateSize = originalImageLargestDim, previousIntermediateSize = originalImageLargestDim;
							//Integer calculatedDim;
							for (Integer i = 0; i < numSteps; i++) {
								if (i + 1 != centerStep) // if this isn't the
															// center step
								{
									if (i == numSteps - 1) // if this is the
															// last step
									{
										// fix the stepsize to account for
										// decimal place errors previously
										currentStepSize = previousIntermediateSize
												- largestDimension;
									} else {
										if (numSteps - i > numSteps / 2) // if
																			// we're
																			// in
																			// the
																			// first
																			// half
																			// of
																			// the
																			// reductions
											currentStepSize = heavierStepSize;
										else
											currentStepSize = lighterStepSize;
									}
								} else // center step, use natural step size
								{
									currentStepSize = stepSize;
								}
								intermediateSize = previousIntermediateSize
										- currentStepSize;
								scale = (double) intermediateSize
										/ (double) previousIntermediateSize;
								scaledW = (int) scaledW * scale;
								scaledH = (int) scaledH * scale;
								outImage = new BufferedImage((int) scaledW,
										(int) scaledH,
										BufferedImage.TYPE_INT_RGB);
								g2d = outImage.createGraphics();
								g2d.setBackground(Color.WHITE);
								g2d.clearRect(0, 0, outImage.getWidth(),
										outImage.getHeight());
								g2d.setRenderingHint(
										RenderingHints.KEY_RENDERING,
										RenderingHints.VALUE_RENDER_QUALITY);
								tx = new AffineTransform();
								tx.scale(scale, scale);
								g2d.drawImage(inImage, tx, null);
								g2d.dispose();
								inImage = new ImageIcon(outImage).getImage();
								previousIntermediateSize = intermediateSize;
							}
						} else {
							// scale =
							// (double)intermediateSize/(double)previousIntermediateSize;
							scaledW = inImage.getWidth(null);
							scaledH = inImage.getHeight(null);
							scaledW = (int) scaledW * scale;
							scaledH = (int) scaledH * scale;
							outImage = new BufferedImage((int) scaledW,
									(int) scaledH, BufferedImage.TYPE_INT_RGB);
							g2d = outImage.createGraphics();
							g2d.setBackground(Color.WHITE);
							g2d.clearRect(0, 0, outImage.getWidth(), outImage
									.getHeight());
							g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
									RenderingHints.VALUE_RENDER_QUALITY);
							tx = new AffineTransform();
							tx.scale(scale, scale);
							g2d.drawImage(inImage, tx, null);
							g2d.dispose();
							inImage = new ImageIcon(outImage).getImage();
						}
					} else {
						// just copy the original						
						outImage = new BufferedImage(inImage.getWidth(null),
								inImage.getHeight(null),
								BufferedImage.TYPE_INT_RGB);
						g2d = outImage.createGraphics();
						g2d.setBackground(Color.WHITE);
						g2d.clearRect(0, 0, outImage.getWidth(), outImage
								.getHeight());
						tx = new AffineTransform();
						tx.setToIdentity(); // use identity matrix so image is
											// copied exactly
						g2d.drawImage(inImage, tx, null);
						g2d.dispose();
					}
					// JPEG-encode the image and write to file.

					OutputStream os = new FileOutputStream(outFileName);
					ImageIO.write(outImage, "JPG", os);
					/*JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
					JPEGEncodeParam encodeParam = encoder
							.getDefaultJPEGEncodeParam(outImage);
					encodeParam.setQuality((float) 0.9, true);
					encoder.setJPEGEncodeParam(encodeParam);
					encoder.encode(outImage, encodeParam);*/
					os.close();
				}

			}
		} catch (IOException io) {
			io.printStackTrace();
		}
		catch(InterruptedException ie)
        {
            ie.printStackTrace();
        }

	}
	public void createStamp(String inFileName, String outFileName) {

		try {
			double scale;
			double scaledW;
			double scaledH;
			
			int sizeDifference, originalImageLargestDim;

			if (!inFileName.endsWith(".jpg") && !inFileName.endsWith(".JPG")
					&& !inFileName.endsWith(".jpeg")
					&& !inFileName.endsWith(".JPEG")
					&& !inFileName.endsWith(".gif")
					&& !inFileName.endsWith(".GIF")
					&& !inFileName.endsWith(".png")
					&& !inFileName.endsWith(".PNG")) {
				System.out
						.println("Error: Unsupported image type, please only either JPG, GIF or PNG");
			} else {
				 inImage = loadImageFromURL(inFileName);
                 getMediaTracker().addImage(inImage, 0);
                 getMediaTracker().waitForID(0);

				if (inImage.getWidth(null) == -1
						|| inImage.getHeight(null) == -1) {
					log.debug("Error loading file: \"" + inFileName+ "\"");
				} else {
					// find biggest dimension
					if (inImage.getWidth(null) > inImage.getHeight(null)) {
						scale = (double) largestDimension
								/ (double) inImage.getWidth(null);
						sizeDifference = inImage.getWidth(null)
								- largestDimension;
						originalImageLargestDim = inImage.getWidth(null);
					} else {
						scale = (double) largestDimension
								/ (double) inImage.getHeight(null);
						sizeDifference = inImage.getHeight(null)
								- largestDimension;
						originalImageLargestDim = inImage.getHeight(null);
					}
					// create an image buffer to draw to
					BufferedImage outImage = new BufferedImage(37, 37,
							BufferedImage.TYPE_INT_RGB); // arbitrary init so
															// code compiles
					Graphics2D g2d;
					AffineTransform tx;
					// only scale if desired size is smaller than original
					if (scale < 1.0d) 
					{
						if (sizeDifference > 100) {
							int numSteps = sizeDifference / 100;
							int stepSize = sizeDifference / numSteps;
							int stepWeight = stepSize / 2;
							int heavierStepSize = stepSize + stepWeight;
							int lighterStepSize = stepSize - stepWeight;
							int currentStepSize, centerStep;
							scaledW = inImage.getWidth(null);
							scaledH = inImage.getHeight(null);
							if (numSteps % 2 == 1) // if there's an odd number of steps
								centerStep = (int) Math
										.ceil((double) numSteps / 2d); // find the center step
							else
								centerStep = -1; // set it to -1 so it's ignored later
							Integer intermediateSize = originalImageLargestDim, previousIntermediateSize = originalImageLargestDim;
							//Integer calculatedDim;
							for (Integer i = 0; i < numSteps; i++) {
								if (i + 1 != centerStep) // if this isn't the
															// center step
								{
									if (i == numSteps - 1) // if this is the
															// last step
									{
										// fix the stepsize to account for
										// decimal place errors previously
										currentStepSize = previousIntermediateSize
												- largestDimension;
									} else {
										if (numSteps - i > numSteps / 2) // if
																			// we're
																			// in
																			// the
																			// first
																			// half
																			// of
																			// the
																			// reductions
											currentStepSize = heavierStepSize;
										else
											currentStepSize = lighterStepSize;
									}
								} else // center step, use natural step size
								{
									currentStepSize = stepSize;
								}
								intermediateSize = previousIntermediateSize
										- currentStepSize;
								scale = (double) intermediateSize
										/ (double) previousIntermediateSize;
								scaledW = (int) scaledW * scale;
								scaledH = (int) scaledH * scale;
								outImage = new BufferedImage((int) scaledW,
										(int) scaledH,
										BufferedImage.TYPE_INT_RGB);
								g2d = outImage.createGraphics();
								g2d.setBackground(Color.WHITE);
								g2d.clearRect(0, 0, 37,37);
								g2d.setRenderingHint(
										RenderingHints.KEY_RENDERING,
										RenderingHints.VALUE_RENDER_QUALITY);
								tx = new AffineTransform();
								tx.scale(scale, scale);
								g2d.drawImage(inImage, tx, null);
								g2d.dispose();
								inImage = new ImageIcon(outImage).getImage();
								previousIntermediateSize = intermediateSize;
							}
						} else {
							// scale =
							// (double)intermediateSize/(double)previousIntermediateSize;
							scaledW = inImage.getWidth(null);
							scaledH = inImage.getHeight(null);
							scaledW = (int) scaledW * scale;
							scaledH = (int) scaledH * scale;
							outImage = new BufferedImage((int) scaledW,
									(int) scaledH, BufferedImage.TYPE_INT_RGB);
							g2d = outImage.createGraphics();
							g2d.setBackground(Color.WHITE);
							g2d.clearRect(0, 0, 37, 37);
							g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
									RenderingHints.VALUE_RENDER_QUALITY);
							tx = new AffineTransform();
							tx.scale(scale, scale);
							g2d.drawImage(inImage, tx, null);
							g2d.dispose();
							inImage = new ImageIcon(outImage).getImage();
						}
					} else {
						// just copy the original						
						outImage = new BufferedImage(inImage.getWidth(null),
								inImage.getHeight(null),
								BufferedImage.TYPE_INT_RGB);
						g2d = outImage.createGraphics();
						g2d.setBackground(Color.WHITE);
						g2d.clearRect(0, 0, 37, 37);
						tx = new AffineTransform();
						tx.setToIdentity(); // use identity matrix so image is
											// copied exactly
						g2d.drawImage(inImage, tx, null);
						g2d.dispose();
					}
					// JPEG-encode the image and write to file.

					OutputStream os = new FileOutputStream(outFileName);
					ImageIO.write(outImage, "JPG", os);
					/*JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
					JPEGEncodeParam encodeParam = encoder
							.getDefaultJPEGEncodeParam(outImage);
					encodeParam.setQuality((float) 0.9, true);
					encoder.setJPEGEncodeParam(encodeParam);
					encoder.encode(outImage, encodeParam);*/
					os.close();
				}

			}
		} catch (IOException io) {
			io.printStackTrace();
		}
		catch(InterruptedException ie)
        {
            ie.printStackTrace();
        }

	}
	/**
	 * @return the image
	 */
	public Image getInImage() {
		return inImage;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setInImage(Image image) {
		this.inImage = image;
	}

}
