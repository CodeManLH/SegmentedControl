package com.jw0ng.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.jw0ng.widget.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * 分段控制器
 * @author Jw0ng
 * @date 2018/12/13
 */
public class SegmentedControl extends LinearLayout {

    private Context mContext;
    private List<Segmented> mSegmenteds;
    private OnItemSelectedListener mOnItemSelectedListener;
    private int mCurrentSelectedPosition;
    private int mSelectedIndex;
    private int mTintColor;
    private int mStrokeWidth;
    private float mCornerRadius;
    private StateListDrawable mLeftItemBackgroup;
    private StateListDrawable mRightItemBackgroup;
    private int colorTransparent;

    public SegmentedControl(Context context) {
        this(context,null);
    }

    public SegmentedControl(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public SegmentedControl(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.SegmentedControl);
        mSelectedIndex = a.getInteger(R.styleable.SegmentedControl_selectedIndex,0);
        mTintColor = a.getColor(R.styleable.SegmentedControl_tintColor, Color.rgb(0,128,255));
        mStrokeWidth = (int)a.getDimension(R.styleable.SegmentedControl_strokeWidth,
                DensityUtil.toPx(context,2));
        mCornerRadius = a.getDimension(R.styleable.SegmentedControl_cornerRadius,
                DensityUtil.toDp(context,4));
        colorTransparent = Color.TRANSPARENT;
        a.recycle();
    }

    /**
     * 所有子视图添加完毕
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        // 绘制圆角矩形
        drawBackgroup();

        // 绘制左边item图形
        drawLeftItemBackGroupSelector();

        // 绘制左边item图形
        drawRightItemBackGroupSelector();

        if (getChildCount() > 0) {
            mSegmenteds = new ArrayList<>();
            initView();
        }
    }


    /**
     * 绘制背景圆角矩形
     */
    private void drawBackgroup() {
        GradientDrawable backGroupDrawable = new GradientDrawable();
        backGroupDrawable.setShape(GradientDrawable.RECTANGLE);
        backGroupDrawable.setCornerRadius(mCornerRadius);
        backGroupDrawable.setStroke(mStrokeWidth,mTintColor);
//        setPadding(0,mStrokeWidth,0,mStrokeWidth);
        setBackgroundDrawable(backGroupDrawable);
    }

    /**
     * 绘制中间item图形
     */
    private void drawRightItemBackGroupSelector() {

        float[] radii = {0,0,mCornerRadius,mCornerRadius,mCornerRadius,mCornerRadius, 0,0};

        mRightItemBackgroup = new StateListDrawable();
        mRightItemBackgroup.addState(new int[] { android.R.attr.state_selected }, createRoundShape(radii,mTintColor));
        mRightItemBackgroup.addState(new int[] { }, createRoundShape(radii,colorTransparent));
    }

    /**
     * 绘制中间item图形
     */
    private StateListDrawable drawMidItemBackGroupSelector() {

        float[] radii = {0,0,0,0,0,0,0,0};

        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[] { android.R.attr.state_selected },
                createRoundShape(radii,mTintColor));
        stateListDrawable.addState(new int[] { },
                createRoundShape(radii,colorTransparent));
        return stateListDrawable;
    }

    /**
     * 绘制左边item图形
     */
    private void drawLeftItemBackGroupSelector() {

        float[] radii = {mCornerRadius,mCornerRadius,0,0,0,0, mCornerRadius,mCornerRadius};

        mLeftItemBackgroup = new StateListDrawable();
        mLeftItemBackgroup.addState(new int[] { android.R.attr.state_selected }, createRoundShape(radii,mTintColor));
        mLeftItemBackgroup.addState(new int[] { }, createRoundShape(radii,colorTransparent));
    }

    /**
     * 创建圆角图形
     * @param radii
     * @param color
     * @return
     */
    private Drawable createRoundShape(float[] radii, int color){
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadii(radii);
        drawable.setColor(color);

        return drawable;
    }


    /**
     * 初始化视图
     */
    private void initView() {

        setOrientation(LinearLayout.HORIZONTAL);

        // 获取子元素, 并删除
        for (int i = 0; i < getChildCount(); i++) {

            View childView = getChildAt(i);
            if (childView instanceof Segmented) {
                mSegmenteds.add((Segmented) childView);
            }

            removeViewAt(i);
            i--;
        }


        initSegmentedItem();
    }

    /**
     * 初始化SegmentedItem点击事件
     */
    private void initSegmentedItem() {

        // 设置当前已选择位置
        mCurrentSelectedPosition = 0;

        for (int i = 0; i < mSegmenteds.size(); i++) {

            Segmented segmented = mSegmenteds.get(i);
            LayoutParams layoutParams = (LayoutParams) segmented.getLayoutParams();
            layoutParams.weight = 1;
            segmented.setLayoutParams(layoutParams);
            if (i == 0) {   // 设置左边Item背景
                segmented.setBackgroundDrawable(mLeftItemBackgroup);

            }else if (i == mSegmenteds.size() - 1){ // 设置右边Item背景
                segmented.setBackgroundDrawable(mRightItemBackgroup);

            }else { // 设置中间Item背景
                segmented.setBackgroundDrawable(drawMidItemBackGroupSelector());
            }

            addView(segmented);

            // 添加分割线
            if (i !=  mSegmenteds.size() - 1){
                addView(createDevider());
            }

            GradientDrawable drawable = new GradientDrawable();
            drawable.setShape(GradientDrawable.RECTANGLE);

            if (i == mSelectedIndex) {
                segmented.setSelected(true);
                mCurrentSelectedPosition = mSelectedIndex;
            }
            setSegmentedItemClickListener(segmented,i);
        }
    }

    /**
     * 创建分割线
     */
    private View createDevider(){
        View devider = new View(mContext);
        LayoutParams layoutParams = new LayoutParams(mStrokeWidth,LayoutParams.MATCH_PARENT);
        devider.setBackgroundColor(mTintColor);
        devider.setLayoutParams(layoutParams);
        return devider;
    }

    /**
     * 设置SegmentedItem点击事件
     */
    private void setSegmentedItemClickListener(final Segmented segmented, final int index){

        segmented.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemSelectedListener != null) {
                    unSelecedSegmented(mSegmenteds.get(mCurrentSelectedPosition));
                    mOnItemSelectedListener.onItemSelected(segmented,index);
                    selecedSegmented(segmented,index);
                }
            }
        });
    }

    private void unSelecedSegmented(Segmented segmented){
        segmented.setSelected(false);
        segmented.setClickable(true);
    }

    private void selecedSegmented(Segmented segmented, int index){
        segmented.setSelected(true);
        segmented.setClickable(false);
        mCurrentSelectedPosition = index;
    }


    public void setOnItemSelectedListener(OnItemSelectedListener listener){
        this.mOnItemSelectedListener = listener;
    }

    public interface OnItemSelectedListener {
        void onItemSelected(Segmented segmented, int index);
    }

    public int getTintColor() {
        return mTintColor;
    }

    public Segmented getSegmented(int index){

        if (mSegmenteds != null) {
            return mSegmenteds.get(index);
        }
        return null;
    }

}
