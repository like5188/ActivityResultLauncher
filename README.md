#### 最新版本

模块|ActivityResultLauncher
---|---
最新版本|[![Download](https://jitpack.io/v/like5188/ActivityResultLauncher.svg)](https://jitpack.io/#like5188/ActivityResultLauncher)

## 功能介绍

1、基于 Activity Result API 的封装。

## 使用方法：

1、引用

在Project的gradle中加入：

```groovy
    allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

在Module的gradle中加入：

```groovy
    dependencies {
    implementation 'androidx.activity:activity-ktx:1.4.0'
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2'
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2'
    implementation 'com.github.like5188:ActivityResultLauncher:版本号'
}
```