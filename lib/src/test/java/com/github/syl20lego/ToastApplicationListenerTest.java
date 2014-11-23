package com.github.syl20lego;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Toast.class})
public class ToastApplicationListenerTest {

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
}