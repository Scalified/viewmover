/*
 * Copyright 2015 Shell Software Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * File created: 2015-03-08 21:41:24
 */

package com.software.shell.viewmover.configuration;

import android.content.Context;
import android.view.animation.Interpolator;
import com.software.shell.viewmover.utils.MetricsConverter;

/**
 * Entity class, which contains the detailed parameters for
 * view movement action
 *
 * @author shell
 * @version 1.0.0
 * @since 1.0.0
 */
public class MovingDetails {

	/**
	 * Move animation duration, which is used by default
	 */
	private static final long DEFAULT_ANIMATION_DURATION = 500;

	/**
	 * Context the view is running in
	 */
	private final Context context;

	/**
	 * An X-axis delta in actual pixels
	 * <p>
	 * Positive value means that view is moving to the right.
	 * Negative value means that view is moving to the left
	 */
	private float xAxisDelta;

	/**
	 * An Y-axis delta in actual pixels
	 * <p>
	 * Positive value means that view is moving down.
	 * Negative value means that view is moving up
	 */
	private float yAxisDelta;

	/**
	 * Move animation duration
	 * <p>
	 * By default set to {@link #DEFAULT_ANIMATION_DURATION}
	 */
	private final long animationDuration;

	/**
	 * Move animation interpolator
	 * <p>
	 * By default is not set and is {@code null}
	 */
	private final Interpolator animationInterpolator;

	/**
	 * Creates and instance of the {@link MovingDetails}
	 *
	 * @param context context the view is running in
	 * @param xAxisDelta X-axis delta in density-independent pixels.
	 *                   Positive value means that view is moving right.
	 *                   Negative value means that view is moving left
	 * @param yAxisDelta Y-axis delta in density-independent pixels.
	 *                   Positive value means that view is moving down.
	 *                   Negative value means that view is moving up
	 * @param animationDuration move animation duration
	 * @param animationInterpolator move animation interpolator
	 */
	public MovingDetails(Context context, float xAxisDelta, float yAxisDelta, long animationDuration,
	                     Interpolator animationInterpolator) {
		this.context = context;
		this.xAxisDelta = dpToPx(xAxisDelta);
		this.yAxisDelta = dpToPx(yAxisDelta);
		this.animationDuration = animationDuration;
		this.animationInterpolator = animationInterpolator;
	}

	/**
	 * Creates and instance of the {@link MovingDetails}
	 *
	 * @param context context the view is running in
	 * @param xAxisDelta X-axis delta in density-independent pixels.
	 *                   Positive value means that view is moving right.
	 *                   Negative value means that view is moving left
	 * @param yAxisDelta Y-axis delta in density-independent pixels.
	 *                   Positive value means that view is moving down.
	 *                   Negative value means that view is moving up
	 * @param animationDuration move animation duration
	 */
	public MovingDetails(Context context, float xAxisDelta, float yAxisDelta, long animationDuration) {
		this(context, xAxisDelta, yAxisDelta, animationDuration, null);
	}

	/**
	 * Creates and instance of the {@link MovingDetails}
	 *
	 * @param context context the view is running in
	 * @param xAxisDelta X-axis delta in density-independent pixels.
	 *                   Positive value means that view is moving right.
	 *                   Negative value means that view is moving left
	 * @param yAxisDelta Y-axis delta in density-independent pixels.
	 *                   Positive value means that view is moving down.
	 *                   Negative value means that view is moving up
	 */
	public MovingDetails(Context context, float xAxisDelta, float yAxisDelta) {
		this(context, xAxisDelta, yAxisDelta, DEFAULT_ANIMATION_DURATION, null);
	}

	/**
	 * Creates an instance of the {@link MovingDetails} by cloning it
	 *
	 * @param details moving details, which cloning is performed of
	 */
	public MovingDetails(MovingDetails details) {
		this(details.getContext(), details.getXAxisDelta(), details.getYAxisDelta(), details.getAnimationDuration(),
				details.getAnimationInterpolator());
	}

	/**
	 * Returns a context the view is running in
	 *
	 * @return context the view is running in
	 */
	Context getContext() {
		return context;
	}

	/**
	 * Returns an X-axis delta in actual pixels
	 *
	 * @return X-axis delta in actual pixels
	 */
	public float getXAxisDelta() {
		return xAxisDelta;
	}

	/**
	 * Sets an X-axis delta
	 *
	 * @param xAxisDelta X-axis delta in density-independent pixels
	 */
	public void setXAxisDelta(float xAxisDelta) {
		this.xAxisDelta = dpToPx(xAxisDelta);
	}

	/**
	 * Returns an Y-axis delta in actual pixels
	 *
	 * @return Y-axis delta in actual pixels
	 */
	public float getYAxisDelta() {
		return yAxisDelta;
	}

	/**
	 * Sets an Y-axis delta
	 *
	 * @param yAxisDelta Y-axis delta in density-independent pixels
	 */
	public void setYAxisDelta(float yAxisDelta) {
		this.yAxisDelta = dpToPx(yAxisDelta);
	}

	/**
	 * Returns the move animation duration in ms
	 *
	 * @return move animation duration in ms
	 */
	public long getAnimationDuration() {
		return animationDuration;
	}

	/**
	 * Returns move animation interpolator
	 *
	 * @return move animation interpolator
	 */
	public Interpolator getAnimationInterpolator() {
		return animationInterpolator;
	}

	/**
	 * Makes a call to
	 * {@link com.software.shell.viewmover.utils.MetricsConverter#dpToPx(android.content.Context, float)}
	 * for converting a density-independent value into density-dependent one
	 *
	 * @param dp density-independent value
	 * @return density-dependent value
	 */
	private float dpToPx(float dp) {
		return MetricsConverter.dpToPx(getContext(), dp);
	}

}
