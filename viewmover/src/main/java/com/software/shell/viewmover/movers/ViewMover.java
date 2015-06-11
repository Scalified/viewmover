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

package com.software.shell.viewmover.movers;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.TranslateAnimation;
import com.software.shell.viewmover.configuration.MovingParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract class, which contains the base view movement logic
 * <p>
 * Is extended by subclasses, which implements specific movement logic
 *
 * @author shell
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class ViewMover {

	/**
	 * Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ViewMover.class);

	/**
	 * {@link android.view.View}, which is to be moved
	 */
	private final View view;

	/**
	 * Overrides default constructor
	 *
	 * @param view {@link android.view.View}, which is to be moved
	 */
	ViewMover(View view) {
		this.view = view;
	}

	/**
	 * Is called to calculate the end X point of the view's left bound
	 * <p>
	 * Used to check whether there is enough space inside parent container to move the view
	 * to the left
	 *
	 * @param xAxisDelta X-axis delta in actual pixels
	 * @return end X point of the view's left bound
	 */
	abstract int calculateEndLeftBound(float xAxisDelta);

	/**
	 * Is called to calculate the end X point of the view's right bound
	 * <p>
	 * Used to check whether there is enough space inside parent container to move the view
	 * to the right
	 *
	 * @param xAxisDelta X-axis delta in actual pixels
	 * @return end X point of the view's right bound
	 */
	abstract int calculateEndRightBound(float xAxisDelta);

	/**
	 * Is called to calculate the end Y point of the view's top bound
	 * <p>
	 * Used to check whether there is enough space inside parent container to move the view
	 * to the top
	 *
	 * @param yAxisDelta Y-axis delta in actual pixels
	 * @return end Y point of the view's top bound
	 */
	abstract int calculateEndTopBound(float yAxisDelta);

	/**
	 * Is called to calculate the end Y point of the view's bottom bound
	 * <p>
	 * Used to check whether there is enough space inside parent container to move the view
	 * to the bottom
	 *
	 * @param yAxisDelta Y-axis delta in actual pixels
	 * @return end Y point of the view's bottom bound
	 */
	abstract int calculateEndBottomBound(float yAxisDelta);

	/**
	 * Is called when move animation completes
	 * <p>
	 * Used to change the view position within its parent container
	 *
	 * @param xAxisDelta X-axis delta in actual pixels
	 * @param yAxisDelta Y-axis delta in actual pixels
	 */
	abstract void changeViewPosition(float xAxisDelta, float yAxisDelta);

	/**
	 * Returns the view, which is to be moved
	 *
	 * @return view to be moved
	 */
	View getView() {
		return view;
	}

	/**
	 * Returns the parent container of the view, which is to be moved
	 *
	 * @return parent container of the view to be moved
	 */
	View getParentView() {
		return (View) view.getParent();
	}

	/**
	 * Moves the view based on the {@link MovingParams}
	 *
	 * @param params params of the move action
	 */
	public void move(MovingParams params) {
		if (isPreviousAnimationCompleted()) {
			MovingParams verifiedParams = getVerifiedMovingParams(params);
			if (isMoveNonZero(verifiedParams)) {
				final Animation moveAnimation = createAnimation(verifiedParams);
				LOGGER.trace("View is about to be moved at: delta X-axis = {}, delta Y-axis = {}",
						verifiedParams.getXAxisDelta(), verifiedParams.getYAxisDelta());
				view.startAnimation(moveAnimation);
			}
		}
	}

	/**
	 * Checks whether previous animation on the view completed
	 *
	 * @return true if previous animation on the view completed, otherwise false
	 */
	boolean isPreviousAnimationCompleted() {
		Animation previousAnimation = view.getAnimation();
		boolean previousAnimationCompleted = previousAnimation == null || previousAnimation.hasEnded();
		if (!previousAnimationCompleted) {
			LOGGER.warn("Unable to move the view. View is being currently moving");
		}
		return previousAnimationCompleted;
	}

	/**
	 * Checks whether both X-axis and Y-axis delta of the moving details are not {@code zero}
	 *
	 * @param details moving details, which needs to be checked
	 * @return true, if any of the X-axis or Y-axis delta of the moving details are {@code zero},
	 *         otherwise false
	 */
	boolean isMoveNonZero(MovingParams details) {
		boolean moveNonZero = details.getXAxisDelta() != 0.0f
				|| details.getYAxisDelta() != 0.0f;
		if (!moveNonZero) {
			LOGGER.warn("Zero movement detected. No movement will be performed");
		}
		return moveNonZero;
	}

	/**
	 * Creates an updated copy of the {@link MovingParams}
	 * with X-axis and Y-axis deltas updated based on calculations returned from
	 * {@link #updateXAxisDelta(MovingParams)} and
	 * {@link #updateYAxisDelta(MovingParams)}
	 *
	 * @param params moving params, which needs to be updated
	 */
	private MovingParams getVerifiedMovingParams(final MovingParams params) {
		MovingParams mParams = new MovingParams(params);
		updateXAxisDelta(mParams);
		updateYAxisDelta(mParams);
		LOGGER.trace("Updated moving details values: X-axis from {} to {}, Y-axis from {} to {}",
				params.getXAxisDelta(), mParams.getXAxisDelta(), params.getYAxisDelta(), mParams.getYAxisDelta());
		return mParams;
	}

	/**
	 * Updates the X-axis delta in moving details based on checking whether
	 * there is enough space left to move the view horizontally
	 *
	 * @param details moving details, which X-axis delta needs to be updated in
	 */
	private void updateXAxisDelta(MovingParams details) {
		if (!hasHorizontalSpaceToMove(details.getXAxisDelta())) {
			LOGGER.warn("Unable to move the view horizontally. No horizontal space left to move");
			details.setXAxisDelta(0.0f);
		}
	}

	/**
	 * Updates the Y-axis delta in moving details based on checking whether
	 * there is enough space left to move the view vertically
	 *
	 * @param details moving details, which Y-axis delta needs to be updated in
	 */
	private void updateYAxisDelta(MovingParams details) {
		if (!hasVerticalSpaceToMove(details.getYAxisDelta())) {
			LOGGER.warn("Unable to move the view vertically. No vertical space left to move");
			details.setYAxisDelta(0.0f);
		}
	}

	/**
	 * Checks whether there is enough space left to move the view horizontally within
	 * its parent container
	 * <p>
	 * Calls {@link #calculateEndLeftBound(float)} and {@link #calculateEndRightBound(float)}
	 * to calculate the resulting X coordinate of the view's left and right bounds
	 *
	 * @param xAxisDelta X-axis delta in actual pixels
	 * @return true if there is enough space to move the view horizontally, otherwise false
	 */
	private boolean hasHorizontalSpaceToMove(float xAxisDelta) {
		int parentWidth = getParentView().getWidth();
		LOGGER.trace("Parent view width is: {}", parentWidth);
		int endLeftBound = calculateEndLeftBound(xAxisDelta);
		int endRightBound = calculateEndRightBound(xAxisDelta);
		LOGGER.trace("Calculated end bounds: left = {}, right = {}", endLeftBound, endRightBound);
		return endLeftBound >= 0 && endRightBound <= parentWidth;
	}

	/**
	 * Checks whether there is enough space left to move the view vertically within
	 * its parent container
	 * <p>
	 * Calls {@link #calculateEndTopBound(float)} and {@link #calculateEndBottomBound(float)}
	 * to calculate the resulting Y coordinate of the view's top and bottom bounds
	 *
	 * @param yAxisDelta Y-axis delta in actual pixels
	 * @return true if there is enough space to move the view vertically, otherwise false
	 */
	private boolean hasVerticalSpaceToMove(float yAxisDelta) {
		int parentHeight = getParentView().getHeight();
		LOGGER.trace("Parent view height is: {}", parentHeight);
		int endTopBound = calculateEndTopBound(yAxisDelta);
		int endBottomBound = calculateEndBottomBound(yAxisDelta);
		LOGGER.trace("Calculated end bounds: top = {}, bottom = {}", endTopBound, endBottomBound);
		return endTopBound >= 0 && endBottomBound <= parentHeight;
	}

	/**
	 * Creates the moving animation
	 * <p>
	 * Configures the moving animation based on moving params
	 *
	 * @param params params, which is used to configure the moving animation
	 * @return moving animation
	 */
	private Animation createAnimation(MovingParams params) {
		Animation animation = new TranslateAnimation(0, params.getXAxisDelta(), 0, params.getYAxisDelta());
		animation.setFillEnabled(true);
		animation.setFillBefore(false);
		animation.setDuration(params.getAnimationDuration());
		Interpolator interpolator = params.getAnimationInterpolator();
		if (interpolator != null) {
			animation.setInterpolator(interpolator);
		}
		animation.setAnimationListener(new MoveAnimationListener(params));
		return animation;
	}

	/**
	 * Move animation listener class
	 * <p>
	 * Used to listen the animation and call the {@link #changeViewPosition(float, float)}
	 * when animation completes
	 */
	private class MoveAnimationListener implements Animation.AnimationListener {

		/**
		 * Moving details
		 */
		private final MovingParams details;

		/**
		 * Creates an instance of the
		 * {@link com.software.shell.viewmover.movers.ViewMover.MoveAnimationListener}
		 *
		 * @param details moving details
		 */
		private MoveAnimationListener(MovingParams details) {
			this.details = details;
		}

		@Override
		public void onAnimationStart(Animation animation) {
		}

		@Override
		public void onAnimationRepeat(Animation animation) {
		}

		/**
		 * Is called when animation completes
		 * <p>
		 * Calls the {@link #changeViewPosition(float, float)} giving the subclasses
		 * the ability to change the position of the view based on their logic
		 *
		 * @param animation moving animation
		 */
		@Override
		public void onAnimationEnd(Animation animation) {
			changeViewPosition(details.getXAxisDelta(), details.getYAxisDelta());
		}

	}

}
