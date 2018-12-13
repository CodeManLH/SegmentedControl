package com.jw0ng.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.jw0ng.widget.utils.DensityUtil;

/**
 * 分段控制器Item
 * @author Jw0ng
 * @date 2018/12/12
 */
public class Segmented extends LinearLayout {

    private Context mContext;
    private AppCompatTextView mTvText;
    private AppCompatTextView mTvDescription;

    private String mText;
    private String mDescription;
    private int mTextColor;
    private int mDescriptionColor;
    private int mTextSelectedColor;
    private int mDescriptionSelectedColor;
    private float mTextSize;
    private float mDescriptionSize;

    public Segmented(Context context) {
       this(context,null);
    }


    public Segmented(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public Segmented(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Segmented);
        mText = a.getString(R.styleable.Segmented_text);
        mDescription = a.getString(R.styleable.Segmented_description);
        mTextSize = a.getDimension(R.styleable.Segmented_textSize,DensityUtil.toSp(context,12));
        mDescriptionSize = a.getDimension(R.styleable.Segmented_descriptionSize,DensityUtil.toSp(context,12));

        mTextColor = a.getColor(R.styleable.Segmented_textColor,0);
        mTextSelectedColor = a.getColor(R.styleable.Segmented_textSelectedColor,Color.WHITE);

        mDescriptionColor = a.getColor(R.styleable.Segmented_descriptionColor,0);
        mDescriptionSelectedColor = a.getColor(R.styleable.Segmented_descriptionSelectedColor,Color.WHITE);
        a.recycle();

        initView();
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        SegmentedControl segmentedControl = (SegmentedControl) getParent();

        if (mTextColor == 0) {
            mTextColor = segmentedControl.getTintColor();
        }

        int[] textColors = new int[] { mTextSelectedColor, mTextColor };
        mTvText.setTextColor(createColorStateList(textColors));

        if (mDescriptionColor == 0) {
            mDescriptionColor = segmentedControl.getTintColor();
        }
        int[] descriptioncolors = new int[] { mDescriptionSelectedColor, mDescriptionColor };
        mTvDescription.setTextColor(createColorStateList(descriptioncolors));

    }

    /**
     * 初始化视图
     */
    private void initView() {
        setClickable(true);
        setOrientation(LinearLayout.VERTICAL);

        setGravity(Gravity.CENTER);
        setVerticalGravity(Gravity.CENTER_VERTICAL);

        initTitle();

        initSubTitle();
    }

    /**
     * 初始化标题
     */
    private void initTitle() {

        mTvText = new AppCompatTextView(mContext);
        mTvText.setTextSize(TypedValue.COMPLEX_UNIT_PX,mTextSize);

        LayoutParams layoutParams = new LayoutParams(LayoutParams
                .WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        mTvText.setLayoutParams(layoutParams);
        if (!TextUtils.isEmpty(mText)) {
            mTvText.setText(mText);
        }

        addView(mTvText);
    }

    /**
     * 初始化子标题
     */
    private void initSubTitle() {

        mTvDescription = new AppCompatTextView(mContext);
        mTvDescription.setTextSize(TypedValue.COMPLEX_UNIT_PX,mDescriptionSize);


        LayoutParams layoutParams = new LayoutParams(LayoutParams
                .WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        mTvDescription.setLayoutParams(layoutParams);

        if (!TextUtils.isEmpty(mDescription)) {
            mTvDescription.setText(mDescription);
        }else {
            mTvDescription.setVisibility(View.GONE);
        }

        addView(mTvDescription);
    }


    public void setText(String title) {
        mTvText.setText(title);
    }

    public String getText() {
        return mText;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String subtitle){
        mTvDescription.setText(subtitle);
        mTvDescription.setVisibility(View.VISIBLE);
    }

    public int getTextColor() {
        return mTextColor;
    }

    public void setTextColor(int textColor) {
        this.mTextColor = textColor;
        mTvText.setTextColor(textColor);
    }

    public int getDescriptionColor() {
        return mDescriptionColor;
    }

    public void setDescriptionColor(int descriptionColor) {
        this.mDescriptionColor = descriptionColor;
        mTvDescription.setTextColor(descriptionColor);
    }

    public float getTextSize() {
        return mTextSize;
    }

    public void setTextSize(float textSize) {
        this.mTextSize = textSize;
        mTvText.setTextSize(textSize);
    }

    public float getDescriptionSize() {
        return mDescriptionSize;
    }

    public void setDescriptionSize(float descriptionSize) {
        this.mDescriptionSize = descriptionSize;
        mTvDescription.setTextSize(descriptionSize);
    }

    @Override
    public void setSelected(boolean selected) {
        mTvText.setSelected(selected);
        mTvDescription.setSelected(selected);
        super.setSelected(selected);
    }

    private ColorStateList createColorStateList(int[] colors){
        int[][] states = new int[2][];
        states[0] = new int[] { android.R.attr.state_selected };
        states[1] = new int[] { };
        ColorStateList colorStateList = new ColorStateList(states,colors);

        return colorStateList;
    }
}
