# View Mover Library for Android

[![Build Status](https://travis-ci.org/shell-software/viewmover.svg?branch=master)](https://travis-ci.org/shell-software/viewmover)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.shell-software/viewmover.svg)](http://search.maven.org/#search|gav|1|g%3A%22com.github.shell-software%22%20AND%20a%3A%22viewmover%22)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-View%20Mover-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/1804)

## Description

The Library allows to move the [**View**](http://developer.android.com/reference/android/view/View.html) within its parent container. While moving, translation animation is used

> For API 16 (Jelly Bean) and higher moving performed based on view's X- and Y-axis position. For other API moving performed based on margins of the parent container

## Requirements

The Library requires **Android SDK version 9 (Gingerbread)** and higher.

> For API lower than Jelly Bean (version code 16) moving works as expected with **FrameLayout** and **RelativeLayout**. Working as expected with other layouts not guaranteed

## Gradle Dependency

```java
dependencies {
	compile 'com.github.shell-software:viewmover:1.1.0'
}
```

> **View Mover** has a dependency on an external [**UI Tools**](https://github.com/shell-software/uitools) library. 
If it is used already in the project it must be excluded as a transitive dependency

## Activity Stream

[**Full ChangeLog**](https://github.com/shell-software/view-mover/blob/master/CHANGELOG.md)

### 1.1.0 - *current*

1. Changed the standard Android logging API to **SLF4J Logging API**

### 1.0.1 - *previous*

1. Fixed **NullPointerException** in **MovingParams**

### Features in the next versions:

**TBD**

## Usage

To move the **View** the following steps must be performed:
	
  1. Create an instance of the **ViewMover** using **ViewMoverFactory.createInstance(View)** passing the **View** object to be moved
  2. Create the **MovingParams** instance, passing the necessary moving parameters
  3. Call the **move(MovingParams)** method on the created **ViewMover** object passing the **MovingParams** instance

### Example

```java
View view;
// ... view initialization code goes here

// Create ViewMover instance
ViewMover mover = ViewMoverFactory.createInstance(view);

// Create MovingDetails instance
MovingParams params = new MovingParams(getContext(), 300.0f, 300.0f);

// Move the view
mover.move(params);
```

### Customization

**MovingParams** class contains the details of how the view must be moved:

  * **xAxisDelta** - X-axis delta specifies the horizontal axis distance, which **View** is moving at.
    Positive value means moving right, negative - left.
  * **yAxisDelta** - Y-axis delta specifies the vertical axis distance, which **View** is moving at.
    Positive value means moving down, negative - up.
  * **animationDuration** - the duration of translate animation, which is used to move the view.
    Set to **500 ms** by default.
  * **animationInterpolator** - an animation interpolator, which is used to move the view.
    Not set by default.
    
> X- and Y-axis deltas are stored in **MovingParams** as actual pixels. When **MovingParams** instance created or when 
setting X- or Y-axis the density-independent values are converted into real ones.

**MovingParams** has several *public* constructors, allowing to create the object with different parameters:

```java
// Define parameters
Context context = getContext();
int rightDistance = 200.0f;
int downDistance = 200.0f;
long animationDuration = 1000;
Interpolator animationInterpolator = new AccelerateInterpolator();

// Declare moving parameters
MovingParams params;

// Create MovingParams object with distances, animation duration and animation interpolator
params = new MovingParams(context, rightDistance, upDistance, animationDuration, animationInterpolator);

// Create MovingParams object with distances and animation duration
params = new MovingParams(context, rightDistance, upDistance, animationDuration);

// Create MovingParams object with distances only
params = new MovingParams(context, rightDistance, upDistance);

// Clone an existing MovingParams object
MovingParams clonedDetails = new MovingParams(params)
```

**MovingParams** X-axis and Y-axis deltas can be changed after the object is created:

```java
// Create MovingParams object with initial parameters
MovingParams params = new MovingParams(getContext(), 200.0f, 200.0f);

// Declare the new parameters
float xAxisDelta = 100.0f;
float yAxisDelta = 100.0f;

// Apply the new parameters to MovingParams object
params.setXAxisDelta(xAxisDelta);
params.setYAxisDelta(yAxisDelta);
```

## Logging

To enable logging:

1. Add the following dependency:

	```java
	dependencies {
		compile 'com.github.tony19:logback-android-classic:1.1.1-3'
	}
	```
2. Create the **logback.xml** file in the **src/main/assets** with the sample configuration:

	```xml
	<?xml version="1.0" encoding="UTF-8"?>
	<configuration>
		<appender name="LOGCAT" class="ch.qos.logback.classic.android.LogcatAppender">
			<tagEncoder>
				<pattern>%logger{0}</pattern>
			</tagEncoder>
			<encoder>
				<pattern>%d{HH:mm:ss.SSS} [%thread] [%logger{0}] - %msg%n</pattern>
			</encoder>
		</appender>
	
		<root level="TRACE" additivity="false">
			<appender-ref ref="LOGCAT" />
		</root>
	</configuration>
	```
	> You may wish to configure different appenders with different log levels for packages, classes etc.
	
	> More information about **LOGBack** can be found @ [LOGBack Project Site](http://logback.qos.ch)

3. Add the following **InvalidPackage** ignore rule into **lint.xml** file (located @ the root of the project):

	```xml
	<issue id="InvalidPackage" >
    	<ignore path="**/logback-android-core/*" />
    </issue>
	```

## License

```
  Copyright 2015 Shell Software Inc.

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
```

## Shell Software Inc. Links

* [Shell Software Inc. Google+ Official Page](https://plus.google.com/+ShellSoftware)
* [Shell Software Inc. YouTube Official Channel](https://www.youtube.com/c/ShellSoftware)
* <a href="mailto:com.software.shell@gmail.com?subject=[UI Tools]: Proposals And Suggestions">Shell Software Inc. Gmail</a>
* [Shell Software Inc. Twitter Official Page](https://twitter.com/shell_software)
