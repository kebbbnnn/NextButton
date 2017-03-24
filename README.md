NextButton
=========================

A custom button widget that handles the string extraction from all EdiTexts or/and TextViews referenced to it. 

# Usage

### XML
```xml
        <EditText
            android:id="@+id/edit1"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="EditText 1" />

        <EditText
            android:id="@+id/edit2"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="EditText 2" />

        <EditText
            android:id="@+id/edit3"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="EditText 3" />

        <com.github.kebbbnnn.nextbutton.NextButton
            android:id="@+id/nextButton"
            xmlns:nb="http://schemas.android.com/apk/res-auto"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Next"
            nb:nb_textFields="{edit1, edit2, edit3}" />
```

### Code
```java
        NextButton nextButton = (NextButton) findViewById(R.id.nextButton);
        nextButton.setOnStringArrayListener(new OnStringArrayListener() {
            @Override
            public void onStrings(String... s) {
                for (int i = 0; i < s.length; i++) {
                    Log.e(MainActivity.class.getName(), "index[" + i + "]" + ", string = " + s[i]);
                }
            }
        });
```

License
--------

    Copyright 2017 Kevin Ladan

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
