package cn.readsense.com.calendarrecyclerview.java;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.ScreenUtils;

import org.w3c.dom.Text;

import cn.readsense.com.calendarrecyclerview.R;

/**
 * @description: <自定义RecyclerView的每个item>
 * @author: dongyiming
 * @date: 2018/9/23 0:34
 * @version: v1.0
 */
public class CalendarGlidView extends ViewGroup {

    int weekBackgroundColor = 0;
    int weekTextColor = 0;
    int monthBackgroundColor = 0;
    int dayBackgroundColor = 0;
    int monthTextColor = 0;
    int currentDayBackgroundColor = 0;
    int selectedDayBackgroundColor = 0;
    int dayNullBackgroundColor = 0;
    int dayTextColor = 0;
    int daySelectedTextColor = 0;
    int dividerColor = 0;

    Float weekHeight = 0F;
    Float weekSize = 0F;
    Float monthHeight = 0F;
    Float monthSize = 0F;
    Float dayHeight = 0F;
    Float daySize = 0F;
    Float dividerHeight = 0F;
    Float marginLeft = 0F;
    Float marginRight = 0F;

    //每个item的长/宽
    private double cellWidth = 0, cellHeight = 0;
    //viewgroup的高度
    private double contentHeight = 0;
    private double screenWidth;

    /* !日期的行数，不包括第一行的月份 */
    private int rowCounts;
    /* ！列数 */
    private int columnCounts;
    private Context mContext;

    private int childCount;


    public CalendarGlidView(Context context) {
        this(context, null);
    }

    public CalendarGlidView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
        Resources resources = context.getResources();
        TypedArray typeArray = context.obtainStyledAttributes(attrs, R.styleable.calendar_gridview);

        weekBackgroundColor = typeArray.getColor(R.styleable.calendar_gridview_attr_week_bg, resources.getColor(R.color.color_week_bg, null));
        weekTextColor = typeArray.getColor(R.styleable.calendar_gridview_attr_week_text_color, resources.getColor(R.color.color_week_text, null));
        monthBackgroundColor = typeArray.getColor(R.styleable.calendar_gridview_attr_month_bg, resources.getColor(R.color.color_month_bg, null));
        dayBackgroundColor = typeArray.getColor(R.styleable.calendar_gridview_attr_day_item_bg, resources.getColor(R.color.color_day_bg, null));
        monthTextColor = typeArray.getColor(R.styleable.calendar_gridview_attr_month_text_color, resources.getColor(R.color.color_month_text, null));
        currentDayBackgroundColor = typeArray.getColor(R.styleable.calendar_gridview_attr_day_item_current_bg, resources.getColor(R.color.color_day_current_bg, null));
        selectedDayBackgroundColor = typeArray.getColor(R.styleable.calendar_gridview_attr_day_item_selected_bg, resources.getColor(R.color.color_day_selected_bg, null));
        dayNullBackgroundColor = typeArray.getColor(R.styleable.calendar_gridview_attr_day_item_null_bg, resources.getColor(R.color.color_day_null_bg, null));
        dayTextColor = typeArray.getColor(R.styleable.calendar_gridview_attr_day_item_text_color, resources.getColor(R.color.color_day_text, null));
        daySelectedTextColor = typeArray.getColor(R.styleable.calendar_gridview_attr_day_item_text_selected_color, resources.getColor(R.color.color_day_selected_text, null));
        dividerColor = typeArray.getColor(R.styleable.calendar_gridview_attr_divider_color, resources.getColor(R.color.color_divider, null));

        weekHeight = typeArray.getDimension(R.styleable.calendar_gridview_attr_week_height, resources.getDimension(R.dimen.dimen_week_height));
        weekSize = typeArray.getDimension(R.styleable.calendar_gridview_attr_week_text_size, resources.getDimension(R.dimen.dimen_week_text_size));
        monthHeight = typeArray.getDimension(R.styleable.calendar_gridview_attr_month_height, resources.getDimension(R.dimen.dimen_month_height));
        monthSize = typeArray.getDimension(R.styleable.calendar_gridview_attr_month_text_size, resources.getDimension(R.dimen.dimen_month_text_size));
        dayHeight = typeArray.getDimension(R.styleable.calendar_gridview_attr_day_item_height, resources.getDimension(R.dimen.dimen_day_height));
        daySize = typeArray.getDimension(R.styleable.calendar_gridview_attr_day_item_text_size, resources.getDimension(R.dimen.dimen_day_text_size));
        dividerHeight = typeArray.getDimension(R.styleable.calendar_gridview_attr_divider_height, resources.getDimension(R.dimen.dimen_divider_height));
        marginLeft = typeArray.getDimension(R.styleable.calendar_gridview_attr_day_item_margin_left, resources.getDimension(R.dimen.dimen_day_marginleft));
        marginRight = typeArray.getDimension(R.styleable.calendar_gridview_attr_day_item_margin_right, resources.getDimension(R.dimen.dimen_day_marginright));
        rowCounts = typeArray.getInt(R.styleable.calendar_gridview_attr_day_row_count, 7);
        columnCounts = typeArray.getInt(R.styleable.calendar_gridview_attr_day_column_count, 7);
        typeArray.recycle();

        screenWidth = ScreenUtils.getScreenWidth();
        cellHeight = dayHeight;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//
//        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        childCount = getChildCount();
        //double才能获得小数位
        rowCounts = (int) Math.ceil((double) (childCount - 1) / columnCounts);
        cellWidth = (double) (screenWidth - columnCounts * marginLeft - marginRight) / columnCounts;
        contentHeight = monthHeight + dividerHeight * (rowCounts - 1) + rowCounts * cellHeight;
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        setMeasuredDimension((int) screenWidth, (int) contentHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        //日期行(1.2.3...)和列(1.2.3...)的下标位置
        int rowIndex = 0;
        int columnIndex = 0;
        double left = 0, top = 0, right = 0, bottom = 0;
        //第一个item为显示年月(2018年/9月)
        for (int i = 0; i < childCount; i++) {
            if (i == 0) {
                getChildAt(0).layout((int) left, (int) top, (int) screenWidth, monthHeight.intValue());
                getChildAt(0).setBackgroundColor(monthBackgroundColor);
            } else {
                rowIndex = (int) Math.floor((double) (i - 1) / columnCounts) + 1;
                columnIndex = i - (rowIndex - 1) * columnCounts;
                left = (columnIndex - 1) * cellWidth + columnIndex * marginLeft;
                top = monthHeight + (rowIndex - 1) * (cellHeight + dividerHeight);
                right = left + cellWidth;
                bottom = top + cellHeight;
                getChildAt(i).layout((int) left, (int) top, (int) right, (int) bottom);
                getChildAt(i).setBackgroundColor(dayBackgroundColor);
                ((TextView)getChildAt(i)).setText(i+"");
                ((TextView)getChildAt(i)).setTextColor(dayTextColor);
            }
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        for (int i = 0; i < 31; i++) {

            TextView textView = new TextView(mContext);
            textView.setGravity(Gravity.CENTER);
            addView(textView);
        }
    }
}