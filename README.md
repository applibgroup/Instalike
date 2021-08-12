[![Build](https://github.com/applibgroup/Instalike/actions/workflows/main.yml/badge.svg)](https://github.com/applibgroup/Instalike/actions/workflows/main.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=applibgroup_Instalike&metric=alert_status)](https://sonarcloud.io/dashboard?id=applibgroup_Instalike)
# InstaLikeView

A HMOS library to add "like" animation similar to instagram.

## Source
Inspired by [KshitijDroid/InstaLikeView](https://github.com/KshitijDroid/InstaLikeView) - version 1.05

## Feature
This library provides an animation similar to instagram "like" feature.

<img src="https://github.com/applibgroup/Instalike/blob/master/screenshots/instalikeview.gif" width="256">

## Dependency
1. For using instalike module in sample app, include the source code and add the below dependencies in entry/build.gradle to generate hap/support.har.
```groovy
	dependencies {
		implementation project(':instalike')
                implementation fileTree(dir: 'libs', include: ['*.har'])
                testImplementation 'junit:junit:4.13'
	}
```
2. For using instalike in separate application using har file, add the har file in the entry/libs folder and add the dependencies in entry/build.gradle file.
```groovy
	dependencies {
		implementation fileTree(dir: 'libs', include: ['*.har'])
		testImplementation 'junit:junit:4.13'
	}
```
3. For using instalike from a remote repository in separate application, add the below dependencies in entry/build.gradle file.
``` groovy
         dependencies {
	         implementation 'dev.applibgroup:instalike:1.0.0'
	         testCompile 'junit:junit:4.13'
         }
```

## Usage

#### Include following code in your layout:

```xml
<com.github.kshitijjain.instalike.InstaLikeView
        ohos:height="match_content"
        ohos:width="match_content"
        ohos:top_margin="20vp"
        ohos:bottom_margin="30vp"
        ohos:layout_alignment="center"
        ohos:id="$+id:instalikeview"
        app:likeSrc="$graphic:img_heart"
        app:likeSize="$float:instalike_image_size" />
```

#### Include following code in your activity:

```java
InstaLikeView instaLikeView = (InstaLikeView) findComponentById(ResourceTable.Id_instalikeview);
// To start animation
instaLikeView.start();
```
#### Supported xml attributes:
```xml
 app:likeSize="$float:instalike_image_size" // Set Like Size (Default 80vp)
 app:likeSrc="$graphic:img_heart" // Set Like Drawable
 ``` 

#### Other supported methods:

```java
mInstaLikeView.start(); // Start Animation
instaLikeView.setLikeResource(ResourceTable.Media_icon); // Set Like Resource
instaLikeView.setLikeDrawable(ElementScatter.getInstance(MainAbilitySlice.this).parse(ResourceTable.Graphic_img_heart)); // Set Like Drawable
instaLikeView.setLikeColor(colorMatrix); // Set Like Color
``` 

## Future Work
Since there is no alternate api for setColorFilter in HMOS platform, custom attribute - "app:likeColor" is currently not supported. As a result, user needs to call the setter function instaLikeView.setLikeColor(colorMatrix) and pass a colorMatrix as an argument which will internally call setColorMatrix(colorMatrix) to change the color of the drawable. Once HMOS platform supports setColorFilter, then this custom attribute can be included.


## License
```
Copyright 2017 Kshitij Jain

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
