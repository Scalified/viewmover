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

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * View mover class, which is used to move the view, based on view's margins
 * within parent container
 *
 * @author shell
 * @version 1.0.0
 * @since 1.0.0
 */
class MarginViewMover extends ViewMover {

	/**
	 * Logging tag
	 */
	private static final String LOG_TAG = String.format("[FAB][%s]", MarginViewMover.class.getSimpleName());

	/**
	 * Creates an instance of the {@link com.software.shell.viewmover.movers.MarginViewMover}
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
		final ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) getView().getLayoutParams();
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
		Log.v(LOG_TAG, String.format("Updated view margins: left = %s, top = %s, right = %s, bottom = %s",
				layoutParams.leftMargin, layoutParams.topMargin, layoutParams.rightMargin, layoutParams.bottomMargin));
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
		Log.v(LOG_TAG, String.format("View is %s aligned", viewLeftAligned ? "LEFT" : "RIGHT"));
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
		Log.v(LOG_TAG, String.format("View is %s aligned", viewTopAligned ? "TOP" : "BOTTOM"));
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
