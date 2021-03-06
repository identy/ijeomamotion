/**
 * ijeomamotion
 * A library for sketching animations with numbers, colors vectors, beziers, curves and more! 
 * http://ekeneijeoma.com/processing/ijeomamotion/
 *
 * Copyright (C) 2012 Ekene Ijeoma http://ekeneijeoma.com
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General
 * Public License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA  02111-1307  USA
 * 
 * @author      Ekene Ijeoma http://ekeneijeoma.com
 * @modified    10/30/2012
 * @version     4 (40)
 */

package ijeoma.geom;

import ijeoma.math.Simplify;
import ijeoma.math.Interpolator;

import java.util.ArrayList;
import java.util.Arrays;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PVector;

public class Path {
	protected int segmentIndex = 0;
	protected int segmentPointIndex = 0;
	protected float segmentPosition = 0;
	protected float segmentPositionRange = 0;

	protected int resolution = 10;

	protected float tension = 0;
	protected float bias = 0;

	protected ArrayList<PVector> points = new ArrayList<PVector>();

	protected float distance = 0;

	boolean computed = false;

	public static String LINEAR = "linear";
	public static String COSINE = "cosine";
	public static String CUBIC = "cubic";
	public static String HERMITE = "hermite";

	protected String mode = LINEAR;

	boolean is3D = false;

	public Path() {
	}

	public Path(PVector[] _points) {
		set(_points);
	}

	public Path(PVector[] _points, String _mode) {
		set(_points);

		mode = _mode;
	}

	public Path(PVector[] _points, String _mode, float _tension, float _bias) {
		set(_points);

		mode = _mode;

		tension = _tension;
		bias = _bias;
	}

	public void drawPoints(PGraphics _g) {
		draw(_g, PConstants.POINTS, 1);
	}

	public void drawPoints(PGraphics _g, float _p) {
		draw(_g, PConstants.POINTS, _p);
	}

	public void drawPoints(PGraphics _g, float _d, float _p) {
		draw(_g, PConstants.POINTS, _d, _p);
	}

	public void drawLine(PGraphics _g) {
		draw(_g, PConstants.LINE, 1);
	}

	public void drawLine(PGraphics _g, float _p) {
		draw(_g, PConstants.LINE, _p);
	}

	public void drawLine(PGraphics _g, float _d, float _p) {
		draw(_g, PConstants.LINE, _d, _p);
	}

	public void draw(PGraphics _g, int _mode, float _p) {
		draw(_g, _mode, 1, _p);
	}

	public void draw(PGraphics _g, int _mode, float _d, float _p) {
		int pointCount = points.size() * resolution;
		// _d = 1;

		if (_mode == PConstants.LINE)
			_g.beginShape();
		else
			_g.beginShape(_mode);

		for (int i = 0; i <= pointCount; i += _d) {
			PVector p1 = get((float) i / pointCount);

			if (is3D)
				_g.vertex(p1.x, p1.y, p1.z);
			else
				_g.vertex(p1.x, p1.y);
		}
		_g.endShape();
	}

	// public void drawPoints(PGraphics _g) {
	// _g.beginShape();
	// float s = _g.getStyle().strokeWeight * 2f;
	// for (PVector p : points)
	// _g.ellipse(p.x, p.y, s, s);
	// _g.endShape();
	// }

	public void add(float _x, float _y) {
		add(new PVector(_x, _y));
	}

	public void add(PVector _point) {
		points.add(_point);

		segmentPositionRange = (1f / (points.size() - 1));
	}

	public void removeAll() {
		points.clear();
	}

	public PVector get(float _position) {
		PVector point = new PVector();

		if (!computed)
			compute();

		if (_position < 1) {
			segmentPointIndex = PApplet.floor((points.size() - 1) * _position);
			segmentPosition = PApplet.map((_position % segmentPositionRange),
					0, segmentPositionRange, 0, 1);
		} else {
			segmentPointIndex = (points.size() - 2);
			segmentPosition = 1;
		}

		PVector v1, v2, v3, v4;

		v2 = points.get(segmentPointIndex);
		v3 = points.get(segmentPointIndex + 1);

		v1 = v4 = new PVector();

		if (segmentPointIndex == 0) {
			PVector segmentBegin = points.get(0);
			PVector segmentEnd = points.get(1);
			PVector segmentSlope = PVector.sub(segmentEnd, segmentBegin);

			v1 = PVector.sub(segmentEnd, segmentSlope);
		} else {
			v1 = points.get(segmentPointIndex - 1);
		}

		if ((segmentPointIndex + 1) == points.size() - 1) {
			PVector segmentBegin = points.get(points.size() - 2);
			PVector segmentEnd = points.get(points.size() - 1);
			PVector segmentSlope = PVector.sub(segmentEnd, segmentBegin);

			v4 = PVector.add(segmentEnd, segmentSlope);
		} else {
			v4 = points.get((segmentPointIndex + 1) + 1);
		}

		if (mode.equals(LINEAR)) {
			point.x = Interpolator.linear(v2.x, v3.x, segmentPosition);
			point.y = Interpolator.linear(v2.y, v3.y, segmentPosition);
			if (is3D)
				point.z = Interpolator.linear(v2.z, v3.z, segmentPosition);
		} else if (mode.equals(COSINE)) {
			point.x = Interpolator.cosine(v2.x, v3.x, segmentPosition);
			point.y = Interpolator.cosine(v2.y, v3.y, segmentPosition);
			if (is3D)
				point.z = Interpolator.cosine(v2.z, v3.z, segmentPosition);
		} else if (mode.equals(CUBIC)) {
			point.x = Interpolator.cubic(v1.x, v2.x, v3.x, v4.x,
					segmentPosition);
			point.y = Interpolator.cubic(v1.y, v2.y, v3.y, v4.y,
					segmentPosition);
			if (is3D)
				point.z = Interpolator.cubic(v1.z, v2.z, v3.z, v4.z,
						segmentPosition);
		} else if (mode.equals(HERMITE)) {
			point.x = Interpolator.hermite(v1.x, v2.x, v3.x, v4.x,
					segmentPosition, tension, bias);
			point.y = Interpolator.hermite(v1.y, v2.y, v3.y, v4.y,
					segmentPosition, tension, bias);
			if (is3D)
				point.z = Interpolator.hermite(v1.z, v2.z, v3.z, v4.z,
						segmentPosition, tension, bias);
		}

		return point;
	}

	public void set(PVector[] _points) {
		set(new ArrayList<PVector>(Arrays.asList(_points)));
	}

	public void set(ArrayList<PVector> _points) {
		points = _points;

		for (PVector p : points)
			if (p.z != 0) {
				is3D = true;
				break;
			}

		segmentPositionRange = (1f / (points.size() - 1));

		compute();
	}

	public PVector[] get() {
		return points.toArray(new PVector[points.size()]);
	}

	public int getCount() {
		return points.size();
	}

	public void simplify(float tolerance) {
		PVector[] simplifiedPoints = Simplify.simplify(
				points.toArray(new PVector[points.size()]), tolerance);
		points = new ArrayList<PVector>(Arrays.asList(simplifiedPoints));
	}

	public void simplify(float tolerance, boolean highestQuality) {
		PVector[] simplifiedPoints = Simplify.simplify(
				points.toArray(new PVector[points.size()]), tolerance,
				highestQuality);
		points = new ArrayList<PVector>(Arrays.asList(simplifiedPoints));
	}

	public void compute() {
		computeDist();

		computed = true;
	}

	public void computeDist() {
		distance = 0;

		for (int i = 0; i < points.size() - 1; i++) {
			PVector d1 = points.get(i);
			PVector d2 = points.get(i + 1);

			distance += PApplet.dist(d1.x, d1.y, d2.x, d2.y);
		}
	}

	public void setTension(float _tension) {
		tension = _tension;
	}

	public float getTension() {
		return tension;
	}

	public void setBias(float _bias) {
		bias = _bias;
	}

	public float getBias() {
		return bias;
	}

	public void setMode(String _mode) {
		mode = _mode;
	}

	public int getSegmentIndex() {
		return segmentIndex;
	}

	public int getSegmentPointIndex() {
		return segmentPointIndex;
	}

	public float getSegmentPosition() {
		return segmentPosition;
	}

	public float getDistance() {
		return distance;
	}

	// public float[] getBounds() {
	// return float[] {};//pointMin.x, pointMin.y, width,height}
	// }
}