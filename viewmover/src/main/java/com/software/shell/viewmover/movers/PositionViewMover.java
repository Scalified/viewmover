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

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * View mover class, which is used to move the view, based on view's visual position
 * within parent container
 * <p>
 * Used for {@code TargetApi} {@link android.os.Build.VERSION_CODES#JELLY_BEAN} and higher
 *
 * @author shell
 * @version 1.0.0
 * @since 1.0.0
 */
class PositionViewMover extends ViewMover {

	/**
	 * Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(PositionViewMover.class);

	/**
	 * Creates the {@link PositionViewMover} instance
	 *
	 * @param view view to be moved
	 */
	PositionViewMover(View view) {
		super(view);
	}

	/**
	 * Changes the position of the view based on view's visual position within its parent container
	 *
	 * @param xAxisDelta X-axis delta in actual pixels
	 * @param yAxisDelta Y-axis delta in actual pixels
	 */
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@Override
	void changeViewPosition(float xAxisDelta, float yAxisDelta) {
		float endLeftBoundPointX = calculateEndLeftBound(xAxisDelta);
		float endTopBoundPointY = calculateEndTopBound(yAxisDelta);
		getView().setX(endLeftBoundPointX);
		getView().setY(endTopBoundPointY);
		LOGGER.trace("Updated view position: leftX = {}, topY = {}", endLeftBoundPointX, endTopBoundPointY);
	}

	/**
	 * Calculates the resulting X coordinate of the view's left bound based on the
	 * X position of the view and the X-axis delta
	 *
	 * @param xAxisDelta X-axis delta in actual pixels
	 * @return resulting X coordinate of the view's left bound
	 */
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@Override
	int calculateEndLeftBound(float xAxisDelta) {
		return (int) (getView().getX() + xAxisDelta);
	}

	/**
	 * Calculates the resulting X coordinate of the view's right bound based on the
	 * resulting X coordinate of the view's left bound and the view's width
	 *
	 * @param xAxisDelta X-axis delta in actual pixels
	 * @return resulting X coordinate of the view's right bound
	 */
	@Override
	int calculateEndRightBound(float xAxisDelta) {
		return calculateEndLeftBound(xAxisDelta) + getView().getWidth();
	}

	/**
	 * Calculates the resulting Y coordinate of the view's top bound based on the
	 * Y position of the view and the Y-axis delta
	 *
	 * @param yAxisDelta Y-axis delta in actual pixels
	 * @return resulting Y coordinate of the view's top bound
	 */
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@Override
	int calculateEndTopBound(float yAxisDelta) {
		return (int) (getView().getY() + yAxisDelta);
	}

	/**
	 * Calculates the resulting Y coordinate of the view's bottom bound based on the
	 * resulting Y coordinate of the view's top bound and the view's height
	 *
	 * @param yAxisDelta Y-axis delta in actual pixels
	 * @return resulting Y coordinate of the view's bottom bound
	 */
	@Override
	int calculateEndBottomBound(float yAxisDelta) {
		return calculateEndTopBound(yAxisDelta) + getView().getHeight();
	}

}
