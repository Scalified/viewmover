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
import android.view.ViewGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * View mover class, which is used to move the view, based on the view's margins
 * within parent container
 * <p>
 * While moving the view, the actual view margins are changed
 * <p>
 * Used for {@code TargetApi} lower than {@link android.os.Build.VERSION_CODES#JELLY_BEAN}
 * <p>
 * The mover works as expected with {@link android.widget.FrameLayout} and {@link android.widget.RelativeLayout}.
 * Working as expected with other layouts not guaranteed
 *
 * @author shell
 * @version 1.0.0
 * @since 1.0.0
 */
class MarginViewMover extends ViewMover {

	/**
	 * Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(MarginViewMover.class);

	/**
	 * Creates the {@link MarginViewMover} instance
	 *
	 * @param view view to be moved
	 */
	MarginViewMover(View view) {
		super(view);
	}

	/**
	 * Changes the position of the view, based on view's margins within its parent container
	 *
	 * @param xAxisDelta X-axis delta in actual pixels
	 * @param yAxisDelta Y-axis delta in actual pixels
	 */
	@Override
	void changeViewPosition(float xAxisDelta, float yAxisDelta) {
		ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) getView().getLayoutParams();
		if (isViewLeftAligned(layoutParams)) {
			layoutParams.leftMargin += xAxisDelta;
		} else {
			layoutParams.rightMargin -= xAxisDelta;
		}
		if (isViewTopAligned(layoutParams)) {
			layoutParams.topMargin += yAxisDelta;
		} else {
			layoutParams.bottomMargin -= yAxisDelta;
		}
		LOGGER.trace("Updated view margins: left = {}, top = {}, right = {}, bottom = {}",
				layoutParams.leftMargin, layoutParams.topMargin, layoutParams.rightMargin, layoutParams.bottomMargin);
		getView().setLayoutParams(layoutParams);
	}

	/**
	 * Checks whether view is left aligned
	 *
	 * @param layoutParams view's layout parameters
	 * @return true if the view is left aligned, otherwise false
	 */
	private boolean isViewLeftAligned(ViewGroup.MarginLayoutParams layoutParams) {
		final int left =  getView().getLeft();
		boolean viewLeftAligned = left == 0 || left == layoutParams.leftMargin;
		LOGGER.trace("View is {} aligned", viewLeftAligned ? "LEFT" : "RIGHT");
		return viewLeftAligned;
	}

	/**
	 * Checks whether view is top aligned
	 *
	 * @param layoutParams view's layout parameters
	 * @return true if the view is top aligned, otherwise false
	 */
	private boolean isViewTopAligned(ViewGroup.MarginLayoutParams layoutParams) {
		final int top = getView().getTop();
		boolean viewTopAligned = top == 0 || top == layoutParams.topMargin;
		LOGGER.trace("View is {} aligned", viewTopAligned ? "TOP" : "BOTTOM");
		return viewTopAligned;
	}

	/**
	 * Calculates the resulting X coordinate of the view's left bound based on the left
	 * position of the view relative to its parent container and the X-axis delta
	 *
	 * @param xAxisDelta X-axis delta in actual pixels
	 * @return resulting X coordinate of the view's left bound
	 */
	@Override
	int calculateEndLeftBound(float xAxisDelta) {
		return getView().getLeft() + (int) xAxisDelta;
	}

	/**
	 * Calculates the resulting X coordinate of the view's right bound based on the right
	 * position of the view relative to its parent container and the X-axis delta
	 *
	 * @param xAxisDelta X-axis delta in actual pixels
	 * @return resulting X coordinate of the view's right bound
	 */
	@Override
	int calculateEndRightBound(float xAxisDelta) {
		return getView().getRight() + (int) xAxisDelta;
	}

	/**
	 * Calculates the resulting Y coordinate of the view's top bound based on the top
	 * position of the view relative to its parent container and the Y-axis delta
	 *
	 * @param yAxisDelta Y-axis delta in actual pixels
	 * @return resulting Y coordinate of the view's top bound
	 */
	@Override
	int calculateEndTopBound(float yAxisDelta) {
		return getView().getTop() + (int) yAxisDelta;
	}

	/**
	 * Calculates the resulting Y coordinate of the view's bottom bound based on the bottom
	 * position of the view relative to its parent container and the Y-axis delta
	 *
	 * @param yAxisDelta Y-axis delta in actual pixels
	 * @return resulting Y coordinate of the view's bottom bound
	 */
	@Override
	int calculateEndBottomBound(float yAxisDelta) {
		return getView().getBottom() + (int) yAxisDelta;
	}

}
