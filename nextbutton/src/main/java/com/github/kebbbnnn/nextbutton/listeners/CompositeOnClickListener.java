package com.github.kebbbnnn.nextbutton.listeners;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * From: http://stackoverflow.com/a/7587569/2687048
 */

public class CompositeOnClickListener implements View.OnClickListener {

    private List<View.OnClickListener> mListeners;

    public CompositeOnClickListener() {
        mListeners = new ArrayList<>();
    }

    public void addOnClickListener(View.OnClickListener listener) {
        mListeners.add(listener);
    }

    @Override
    public void onClick(View v) {
        for (View.OnClickListener listener : mListeners) {
            if (listener != null) listener.onClick(v);
        }
    }
}
