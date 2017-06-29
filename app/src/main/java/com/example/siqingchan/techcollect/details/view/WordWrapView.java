package com.example.siqingchan.techcollect.details.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.example.siqingchan.techcollect.R;

public class WordWrapView extends ViewGroup {
    private static final int PADDING_HOR = 10;//水平方向padding
    private static final int PADDING_VERTICAL = 5;//垂直方向padding
    private static final int HORIZONTAL_MARGIN = 10;//左右间距
    private static final int VERTICAL_MARGIN = 10;  //上下间距
    private int horizontalMargin;

    public void setHorizontalMargin(int horizontalMargin) {
        this.horizontalMargin = horizontalMargin;
    }

    public void setVerticalMargin(int verticalMargin) {
        this.verticalMargin = verticalMargin;
    }

    private int verticalMargin;

    /**
     * @param context
     */
    public WordWrapView(Context context) {
        super(context);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public WordWrapView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    /**
     * @param context
     * @param attrs
     */
    public WordWrapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.WordWrapView, 0, 0);
        int n = a.getIndexCount();

        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.WordWrapView_vertical_margin:
                    verticalMargin = (int) a.getDimension(attr, VERTICAL_MARGIN);
                    break;
                case R.styleable.WordWrapView_horizontal_margin:
                    horizontalMargin = (int) a.getDimension(attr, HORIZONTAL_MARGIN);
            }
        }
        a.recycle();
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int autualWidth = r - l;
        int x = horizontalMargin;// 横坐标开始
        int y;//纵坐标开始
        int rows = 1;
        int width;
        int height;
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            width = view.getMeasuredWidth();
            height = view.getMeasuredHeight();
            x += width + horizontalMargin;
            if (x > autualWidth) {
                x = width + horizontalMargin * 2;
                rows++;
            }
            y = rows * (height + verticalMargin);
            view.layout(x - width - horizontalMargin, y - height, x - horizontalMargin, y);
            /*if(i==0){
                view.layout(x-width-VERTICAL_MARGIN, y-height, x-VERTICAL_MARGIN, y);
            }else{
                view.layout(x-width, y-height, x, y);
            }*/
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int x = 0;//横坐标
        int y = 0;//纵坐标
        int rows = 1;//总行数
        int specWidth = MeasureSpec.getSize(widthMeasureSpec);
        int actualWidth = specWidth - horizontalMargin * 2;//实际宽度
        int childCount = getChildCount();
        for (int index = 0; index < childCount; index++) {
            View child = getChildAt(index);
            //child.setPadding(PADDING_HOR, PADDING_VERTICAL, PADDING_HOR, PADDING_VERTICAL);
            child.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();
            x += width + horizontalMargin;
            if (x > actualWidth) {//换行
                x = width;
                rows++;
            }
            y = rows * (height + verticalMargin);
        }
        setMeasuredDimension(actualWidth, y + verticalMargin);
    }

}