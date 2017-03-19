package com.github.kebbbnnn.nextbutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.github.kebbbnnn.nextbutton.listeners.CompositeOnClickListener;
import com.github.kebbbnnn.nextbutton.listeners.OnStringArrayListener;
import com.github.kebbbnnn.nextbutton.util.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevinladan on 3/19/17.
 */

public class NextButton extends AppCompatButton {

    private CompositeOnClickListener mGroupListener = new CompositeOnClickListener();

    private List<View> mReferencedViews = new ArrayList<>();

    private OnStringArrayListener mOnStringArrayListener;

    private String mTextFields;

    public void setOnStringArrayListener(OnStringArrayListener onStringArrayListener) {
        mOnStringArrayListener = onStringArrayListener;
    }

    public NextButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        warmupTypes(context, attrs);
        attachCompositeListener();
    }

    public NextButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        warmupTypes(context, attrs);
        attachCompositeListener();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        initReferencedViews();
    }

    private void warmupTypes(Context context, AttributeSet attrs) {
        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NextButton);
        mTextFields = typedArray.getString(R.styleable.NextButton_nb_textFields);
        typedArray.recycle();
    }

    private void initReferencedViews() {
        Context context = getContext();

        checkValidity();

        mTextFields = Utils.removeWhiteSpace(Utils.removeClosePair(mTextFields));
        String[] elements = mTextFields.split(",");

        String packageName = context.getPackageName();
        View root = getRootView();

        for (String element : elements) {
            int resId = getResources().getIdentifier(element, "id", packageName);
            View refView = root.findViewById(resId);
            //Accepts all Views that are extended to TextView
            if (refView instanceof TextView) {
                mReferencedViews.add(refView);
            }
        }
    }

    private void attachCompositeListener() {
        mGroupListener.addOnClickListener(mInternalClick);
        super.setOnClickListener(mGroupListener);
    }

    private void checkValidity() {
        if (!Utils.isValidExpression(mTextFields))
            throw new IllegalArgumentException("textField value is in wrong format!");
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener externalClick) {
        mGroupListener.addOnClickListener(externalClick);
    }

    private OnClickListener mInternalClick = new OnClickListener() {
        @Override
        public void onClick(View view) {
            if (mOnStringArrayListener != null) {
                int length = mReferencedViews.size();

                if (length < 0)
                    throw new NegativeArraySizeException("length should be greater than 0");

                String[] strings = new String[length];

                for (int i = 0; i < length; i++) {
                    strings[i] = ((TextView) mReferencedViews.get(i)).getEditableText().toString();
                }

                mOnStringArrayListener.onStrings(strings);
            }
        }
    };

}
