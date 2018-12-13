# SegmentedControl
Android SegmentedControl

**API(14+)

<img width="320" height="480" src="https://github.com/CodeManLH/SegmentedControl/blob/master/screenshots/WX20181213-210229@2x.png"/>
<img width="320" height="480" src="https://github.com/CodeManLH/SegmentedControl/blob/master/screenshots/WX20181213-210649@2x.png"/>


## Download
### Gradle
Add it in your root build.gradle at the end of repositories:
```
allprojects {
    repositories {
      ...
      maven { url 'https://jitpack.io' }
    }
}
```

Add dependency to app module level build.gradle
```
dependencies {
    implementation 'com.github.CodeManLH:SegmentedControl:1.0.0'
}
```

### Maven
```
<repositories>
    <repository>
      <id>jitpack.io</id>
      <url>https://jitpack.io</url>
    </repository>
</repositories>
```
Add the dependency
```
<dependency>
    <groupId>com.github.CodeManLH</groupId>
        <artifactId>SegmentedControl</artifactId>
    <version>Tag</version>
</dependency>
```
### Done.

## Simple usage in XML
```xml
    <com.jw0ng.widget.SegmentedControl
        android:id="@+id/sg_control"
        android:layout_width="match_parent"
        android:layout_height="40dp"
        app:selectedIndex="0">

        <com.jw0ng.widget.Segmented
            android:layout_width="0dp"
            android:layout_height="match_parent"
            app:text="Complete"
            app:description="0"/>

        <com.jw0ng.widget.Segmented
            android:layout_width="0dp"
            android:layout_height="match_parent"
            app:text="Undone"
            app:description="0" />
        
        ...
        
    </com.jw0ng.widget.SegmentedControl>
```
### SegmentedControl Attributes

Change Color
```xml
app:tintColor="@color/colorPrimary"
```

Line Width
```xml
app:strokeWidth="2px"
```
Corner Radius
```xml
app:cornerRadius="5dp"
```
Init Selected Index
```xml
app:selectedIndex="0"
```

### Segmented Attributes
Top Text
```xml
app:text="Complete"
```

Bottom Text (if you don't need the instructions below, no setting will automatically hide)
```xml
app:description="0"
```

Font Size (default 12sp)
```xml
app:textSize="16sp"
app:descriptionSize="13sp"
```

Text Color  ('textColor' is the normal color)
```xml
app:textColor="@color/colorPrimary"
app:textSelectedColor="@color/colorAccent"
app:descriptionColor="@color/colorPrimaryDark"
app:descriptionSelectedColor="@color/colorAccent"
```
## Example
```java
segmentedControl = findViewById(R.id.sg_control);
segmentedControl.setOnItemSelectedListener(new SegmentedControl.OnItemSelectedListener() {
    @Override
    public void onItemSelected(Segmented segmented, int index) {
        switch (index) {
            case 0:
                Log.v(TAG,index + "");
                Log.v(TAG,segmented.getText());
                break;
            case 1:
                segmented.setDescription("1");
                break;
        }

    }
});
```
