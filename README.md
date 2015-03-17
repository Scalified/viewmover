# View Mover Library for Android

[![Maven Central](https://img.shields.io/maven-central/v/com.github.shell-software/viewmover.svg)](http://search.maven.org/#search|gav|1|g%3A%22com.github.shell-software%22%20AND%20a%3A%22viewmover%22)

## Donation

Donation helps to improve the project development and speed up the release of new versions. I appreciate any contribution

[![Donate](https://www.paypalobjects.com/en_US/i/btn/btn_donate_SM.gif)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=44CVJBPFRKXJL)

## Description

The Library allows to move the [**View**](http://developer.android.com/reference/android/view/View.html) within its parent container. While moving, translation animation is used

> For API 16 and higher moving performed based on view's X- and Y-axis position. For other API moving performed based on margins of the parent container

## Requirements

The Library requires **Android SDK version 9 (Gingerbread)** and higher.

## Gradle Dependency

```java
dependencies {
	compile 'com.github.shell-software:viewmover:0.9.0'
}
```

## Activity Stream

[**Full ChangeLog**](https://github.com/shell-software/view-mover/blob/master/CHANGELOG.md)

### 1.0.0 - *current*

1. The first release! Everything is new.

### Features in the next versions:

**TBD**

## Demo

//TODO

## Usage

To move the **View** perform the following steps:
	
  1. Create an instance of the **ViewMover** class passing the **View** object to be moved
  2. Call the **move(MovingDetails)** method on the created **ViewMover** object passing the **MovingDetails** instance

### Creation

To create a **ViewMover** object call *public static* method **createInstance(View)** on **ViewMoverFactory**:

```java
ViewMover mover = ViewMoverFactory.createInstance(view);
```

**MovingDetails** class contains the details of how the view must be moved:

  * **xAxisDelta** - X-axis delta specifies the horizontal axis distance, which **View** is moving at.
    Positive value means moving right, negative - left.
  * **yAxisDelta** - Y-axis delta specifies the vertical axis distance, which **View** is moving at.
    Positive value means moving down, negative - up.
  * **animationDuration** - the duration of translate animation, which is used to move the view.
    Set to **500 ms** by default.
  * **animationInterpolator** - an animation interpolator, which is used to move the view.
    Not set by default.
	
**MovingDetails** has several *public* constructors, allowing to create the object with different parameters:

```java
// Create parameters
Context context = getContext();
int rightDistance = 300.0f;
int downDistance = 300.0f;
long animationDuration = 1000;
Interpolator animationInterpolator = new AccelerateInterpolator();

// Create MovingDetails object with distances, animation duration and animation interpolator
MovingDetails firstMovingDetails = new MovingDetails(context, rightDistance, upDistance, animationDuration, animationInterpolator);

// Create MovingDetails object with distances and animation duration
MovingDetails secondMovingDetails = new MovingDetails(context, rightDistance, upDistance, animationDuration);

// Create MovingDetails object with distances only
MovingDetails thirdMovingDetails = new MovingDetails(context, rightDistance, upDistance);

// Clone an existing MovingDetails object
MovingDetails clonedMovingDetails = new MovingDetails(firstMovingDetails)
```

**MovingDetails** X-axis and Y-axis deltas can be changed after the object is created:

```java
// Create MovingDetails object with initial parameters
MovingDetails movingDetails = new MovingDetails(getContext(), 300.0f, 300.0f);

// Create new parameters
float xAxisDelta = 100.0f;
float yAxisDelta = 100.0f;

// Set the new parameters to MovingDetails object
movingDetails.setXAxisDelta(xAxisDelta);
movingDetails.setYAxisDelta(yAxisDelta);
```

To move the view call **public** method **move(MovingDetails)** on **ViewMover** object:

```java
// Create ViewMover object
ViewMover mover = ViewMoverFactory.createInstance(view);

// Create MovingDetails object
MovingDetails movingDetails = new MovingDetails(getContext(), 300.0f, 300.0f);

// Move the view
mover.move(movingDetails);
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

## Shell Software Inc. links

* [Google+](https://plus.google.com/112119444427380215269)
* [Twitter Page](https://twitter.com/shell_software)
