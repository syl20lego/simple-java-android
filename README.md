simple-java-android
===================

Sample application
------------------

Test Driven Development (TDD) android using a java library with Android dependencies.

Today it is very difficult to do TDD on android, Robolectric is nice but tends to cover the
integration side and works on Android Libs. Also since the move to Gradle and Android Studio, it
is very difficult to have Robolectric working. There are few hacks here and there but seems like
a moving target as Android Studio evolve.

I was looking to a simplistic approach, where I can do simple unit test (I don't need to test the
platform). The biggest hurdle was Android comes with Junit 3.2 and the libraries format is aar.
Ideally I would find a clean version of the jar on Maven Central.


`Exception in thread "main" java.lang.RuntimeException: Stub!`

I use PowerMockito to mock final and static framework.
This also force you to right concise unit test and avoid testing the framework, since you shouldn't
be testing the framework but only your business logic.

The idea is to copy the dependencies needed (while cleaning up what is not needed) directly from
the sdk.

There are few limitations:

*   You have to be careful not to use classes from Java SDK that are not supported on Android.
*   It cannot support the different configuration, but for a java library Strategy is probably better.
*   You need to compile first
*   It's not a gradle plugin

    Instead of the build.gradle tasks in /lib, I would like to create a gradle plugin that would fake
the android dependencies. So you can use the same dependencies format as android, maybe without using
libs folder at all.

How does it work
----------------
In lib/build.gradle there are 2 task 'copyAndroidLibs' and 'createJar' that sanitize the android libs.
The rest is normal java and junit using Mockito and PowerMockito. They are currently hardcoded, but
I' thinking to parametrize them. Right now you have to modify them manually.

Now the lib (java library) can use all the android dependencies needed while being able to do TDD.

Now you can just mock what is needed, even static classes like Toast.

    @SuppressLint("ShowToast")
    @Test
    public void testOnActivityStarted() throws Exception {
        mockStatic(Toast.class);
        Toast toast = mock(Toast.class);
        ArgumentCaptor<String> text = ArgumentCaptor.forClass(String.class);
        when(Toast.makeText(any(Context.class), text.capture(), anyInt())).thenReturn(toast);

        ToastApplicationListener tracker = new ToastApplicationListener();
        tracker.onActivityStarted(mock(Activity.class));

        verify(toast, times(1)).show();
        assertEquals("TEST", text.getValue());
    }



Next steps:

* use sdk.lib variable
* add parametrization
* create gradle plugin



