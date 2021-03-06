/**
 * ijeomamotion
 * A cross-mode Processing library for sketching animations with numbers, colors vectors, beziers, curves and more. 
 * http://ekeneijeoma.com/processing/ijeomamotion
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
 * @modified    05/13/2013
 * @version     5.4.1 (54)
 */

package ijeoma.motion.tween;

import ijeoma.motion.Callback;
import ijeoma.motion.Motion;
import ijeoma.motion.MotionController;
import ijeoma.motion.event.MotionEvent;
import ijeoma.motion.event.MotionEventListener;

import java.lang.reflect.Method;

import processing.core.PApplet;

public class Parallel extends MotionController {
	private Method tweenParallelStartedMethod, tweenParallelEndedMethod,
			tweenParallelChangedMethod, tweenParallelRepeatedMethod;

	/**
	 * Constructs a TweenParallel 
	 */
	public Parallel() {
		super();
		setupEvents();
	}

	/**
	 * Constructs a TweenParallel
	 *  
	 * @param children
	 *            is an array of type Object[] can contain Tweens and/or
	 *            TweenParallels
	 */
	public Parallel(Motion[] _children) {
		super();
		addAll(_children);
		setupEvents();
	}

	/**
	 * Constructs a TweenParallel
	 * 
	 * @param name
	 *            is used to find a TweenParallel with in another TweenParallel
	 *            or TweenSequence
	 */
	public Parallel(String _name) {
		super(_name);
		setupEvents();
	}

	/**
	 * Constructs a TweenParallel
	 *  
	 * @param name
	 *            is used to find a TweenParallel with in another TweenParallel
	 *            or TweenSequence
	 * @param children
	 *            is an array of type Object[] can contain Tweens and/or
	 *            TweenParallels
	 */
	public Parallel(String _name, Motion[] _children) {
		super(_name);
		addAll(_children);
		setupEvents();
	}

	/**
	 * Sets the events
	 */
	@Override
	protected void setupEvents() {
		super.setupEvents();

		Class<? extends PApplet> parentClass = parent.getClass();

		try {
			tweenParallelStartedMethod = parentClass.getMethod(
					MotionEvent.TWEEN_PARALLEL_STARTED,
					new Class[] { Parallel.class });
		} catch (Exception e) {
		}

		try {
			tweenParallelEndedMethod = parentClass.getMethod(
					MotionEvent.TWEEN_PARALLEL_ENDED,
					new Class[] { Parallel.class });
		} catch (Exception e) {
		}

		try {
			tweenParallelChangedMethod = parentClass.getMethod(
					MotionEvent.TWEEN_PARALLEL_CHANGED,
					new Class[] { Parallel.class });
		} catch (Exception e) {
		}

		try {
			tweenParallelRepeatedMethod = parentClass.getMethod(
					MotionEvent.TWEEN_PARALLEL_REPEATED,
					new Class[] { Parallel.class });
		} catch (Exception e) {
		}
	}

	public Parallel play() {  
		return (Parallel) super.play();
	}
 
	public Parallel stop() {
		return (Parallel) super.stop();
	}

	public Parallel pause() {
		return (Parallel) super.pause();
	}

	public Parallel resume() {
		return (Parallel) super.resume();
	}
 
	public Parallel seek(float _value) {
		return (Parallel) super.seek(_value);
	}
	
	public Parallel delay(float _delay) {
		return (Parallel) super.delay(_delay);
	}

	public Parallel noDelay() {
		return (Parallel) super.noDelay();
	}

	public Parallel repeatDelay() {
		return (Parallel) super.repeatDelay();
	}

	public Parallel noRepeatDelay() {
		return (Parallel) super.noRepeatDelay();
	}

	public Parallel repeat() {
		return (Parallel) super.repeat();
	}

	public Parallel repeat(int _repeat) {
		return (Parallel) super.repeat(_repeat);
	}

	public Parallel noRepeat() {
		return (Parallel) super.noRepeat();
	}

	public Parallel reverse() {
		return (Parallel) super.reverse();
	}

	public Parallel noReverse() {
		return (Parallel) super.noReverse();
	}

	public Parallel setName(String _name) {
		return (Parallel) super.setName(_name);
	}

	public Parallel setTimeScale(float _timeScale) {
		return (Parallel) super.setTimeScale(_timeScale);
	}

	public Parallel setDuration(float _duration) {
		return (Parallel) super.setDuration(_duration);
	}

	public Parallel setEasing(String _easing) {
		return (Parallel) super.setEasing(_easing);
	}

	public Parallel noEasing() {
		return (Parallel) super.noEasing();
	}

	public Parallel setTimeMode(String _timeMode) {
		return (Parallel) super.setTimeMode(_timeMode);
	}

	public Parallel autoUpdate() {
		return (Parallel) super.autoUpdate();
	}

	public Parallel noAutoUpdate() {
		return (Parallel) super.noAutoUpdate();
	}

	public Parallel addEventListener(MotionEventListener listener) {
		return (Parallel) super.addEventListener(listener);
	}
	
	public Parallel add(Motion _child) {
		return (Parallel) super.add(_child);
	}
	 
	/**
	 * Removes Motion object
	 */
	public Parallel remove(Motion child) {
		return (Parallel) super.remove(child);
	}

	/**
	 * adds multiple Motion objects
	 */
	public Parallel addAll(Motion[] children) {
		return (Parallel) super.addAll(children);
	}

	/**
	 * Removes all Motion objects
	 */
	public Parallel removeAll() {
		return (Parallel) super.removeAll();
	}

	public Parallel addCall(Callback call) {
		return (Parallel) super.addCall(call);
	}

	public Parallel onBegin(Object object, String method) {
		return (Parallel) super.onBegin(object, method);
	}

	public Parallel onBegin(String method) {
		return (Parallel) super.call(getTween(0).getProperty(0).getObject(),
				method, 0);
	}

	public Parallel onEnd(Object object, String method) {
		return (Parallel) super.onEnd(object, method);
	}

	public Parallel onEnd(String method) {
		return (Parallel) super.call(getTween(0).getProperty(0).getObject(),
				method, duration);
	}

	public Parallel onChange(Object object, String method) {
		return (Parallel) super.onChange(object, method);
	}

	public Parallel onChange(String method) {
		return (Parallel) super.call(getTween(0).getProperty(0).getObject(),
				method, -1);
	}
	
	@Override
	protected void dispatchMotionStartedEvent() {
		// logger.println("dispatchMotionStartedEvent tweengroup");

		if (tweenParallelStartedMethod != null) {
			try {
				tweenParallelStartedMethod
						.invoke(parent, new Object[] { this });
			} catch (Exception e) {
				// e.printStackTrace();
				tweenParallelStartedMethod = null;
			}
		}

		dispatchEvent(MotionEvent.TWEEN_PARALLEL_STARTED);
	}

	@Override
	protected void dispatchMotionEndedEvent() {
		if (tweenParallelEndedMethod != null) {
			try {
				tweenParallelEndedMethod.invoke(parent, new Object[] { this });
			} catch (Exception e) {
				e.printStackTrace();
				tweenParallelEndedMethod = null;
			}
		}

		dispatchEvent(MotionEvent.TWEEN_PARALLEL_ENDED);
	}

	@Override
	protected void dispatchMotionChangedEvent() {
		if (tweenParallelChangedMethod != null) {
			try {
				tweenParallelChangedMethod
						.invoke(parent, new Object[] { this });
			} catch (Exception e) {
				// e.printStackTrace();
				tweenParallelChangedMethod = null;
			}
		}

		dispatchEvent(MotionEvent.TWEEN_PARALLEL_CHANGED);
	}

	@Override
	protected void dispatchMotionRepeatedEvent() {
		if (tweenParallelRepeatedMethod != null) {
			try {
				tweenParallelRepeatedMethod.invoke(parent,
						new Object[] { this });
			} catch (Exception e) {
				// e.printStackTrace();
				tweenParallelRepeatedMethod = null;
			}
		}

		dispatchEvent(MotionEvent.TWEEN_PARALLEL_REPEATED);
	}

	@Override
	public String toString() {
		return ("TweenParallel[tweens: {" + tweens + "}]");
	}

	@Override
	public void onMotionEvent(MotionEvent te) {
		// TODO Auto-generated method stub
	}
}
