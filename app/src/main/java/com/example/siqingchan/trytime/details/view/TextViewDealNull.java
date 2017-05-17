package com.example.siqingchan.trytime.details.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.siqingchan.trytime.R;

/**
 * Created by siqingchan on 2016/11/25.
 */

public class TextViewDealNull extends TextView {
    private CharSequence defaultString;
    private static String DEFAULT_STRING = "暂无";

    public void setDefaultString(CharSequence defaultString) {
        this.defaultString = defaultString;
    }

    public TextViewDealNull(Context context, @Nullable CharSequence defaultString) {
        super(context);
        this.defaultString = defaultString;
    }

    public TextViewDealNull(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.TextViewDealNull, 0, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.TextViewDealNull_defaultString:
                    defaultString = a.getText(attr);
                    break;
            }
        }
        a.recycle();
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        if (TextUtils.isEmpty(text)) {
            if (TextUtils.isEmpty(defaultString)) {
                defaultString = DEFAULT_STRING;
            }
            super.setText(defaultString, type);
        } else {
            super.setText(text, type);
        }
    }
}
