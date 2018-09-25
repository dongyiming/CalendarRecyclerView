package cn.readsense.com.calendarrecyclerview

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.content.res.TypedArray
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.RelativeLayout

class CalendarGlidView(context: Context?) : RelativeLayout(context) {

    var weekBackgroundColor: Int = 0
    var weekTextColor: Int = 0
    var monthBackgroundColor: Int = 0
    var dayBackgroundColor: Int = 0
    var monthTextColor: Int = 0
    var currentDayBackgroundColor: Int = 0
    var selectedDayBackgroundColor: Int = 0
    var dayNullBackgroundColor: Int = 0
    var dayTextColor: Int = 0
    var daySelectedTextColor: Int = 0
    var dividerColor: Int = 0

    var weekHeight: Float = 0F
    var weekSize: Float = 0F
    var monthHeight: Float = 0F
    var monthSize: Float = 0F
    var dayHeight: Float = 0F
    var daySize: Float = 0F
    var dividerHeight: Float = 0F

    @SuppressLint("NewApi")
    constructor(context: Context, attrs: AttributeSet) : this(context) {

        var resources: Resources = context.resources
        var typeArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.calendar_gridview)
        weekBackgroundColor = typeArray.getColor(R.styleable.calendar_gridview_attr_week_bg, resources.getColor(R.color.color_week_bg, null))
        weekTextColor = typeArray.getColor(R.styleable.calendar_gridview_attr_week_text_color, resources.getColor(R.color.color_week_text, null))
        monthBackgroundColor = typeArray.getColor(R.styleable.calendar_gridview_attr_month_bg, resources.getColor(R.color.color_month_bg, null))
        dayBackgroundColor = typeArray.getColor(R.styleable.calendar_gridview_attr_day_item_bg, resources.getColor(R.color.color_day_bg, null))
        monthTextColor = typeArray.getColor(R.styleable.calendar_gridview_attr_month_text_color, resources.getColor(R.color.color_month_text, null))
        currentDayBackgroundColor = typeArray.getColor(R.styleable.calendar_gridview_attr_day_item_current_bg, resources.getColor(R.color.color_day_current_bg, null))
        selectedDayBackgroundColor = typeArray.getColor(R.styleable.calendar_gridview_attr_day_item_selected_bg, resources.getColor(R.color.color_day_selected_bg, null))
        dayNullBackgroundColor = typeArray.getColor(R.styleable.calendar_gridview_attr_day_item_null_bg, resources.getColor(R.color.color_day_null_bg, null))
        dayTextColor = typeArray.getColor(R.styleable.calendar_gridview_attr_day_item_text_color, resources.getColor(R.color.color_day_text, null))
        daySelectedTextColor = typeArray.getColor(R.styleable.calendar_gridview_attr_day_item_text_selected_color, resources.getColor(R.color.color_day_selected_text, null))
        dividerColor = typeArray.getColor(R.styleable.calendar_gridview_attr_divider_color, resources.getColor(R.color.color_divider, null))

        weekHeight = typeArray.getDimension(R.styleable.calendar_gridview_attr_week_height, resources.getDimension(R.dimen.dimen_week_height))
        weekSize = typeArray.getDimension(R.styleable.calendar_gridview_attr_week_text_size, resources.getDimension(R.dimen.dimen_week_text_size))
        monthHeight = typeArray.getDimension(R.styleable.calendar_gridview_attr_month_height, resources.getDimension(R.dimen.dimen_month_height))
        monthSize = typeArray.getDimension(R.styleable.calendar_gridview_attr_month_text_size, resources.getDimension(R.dimen.dimen_month_text_size))
        dayHeight = typeArray.getDimension(R.styleable.calendar_gridview_attr_day_item_height, resources.getDimension(R.dimen.dimen_day_height))
        daySize = typeArray.getDimension(R.styleable.calendar_gridview_attr_day_item_text_size, resources.getDimension(R.dimen.dimen_day_text_size))
        dividerHeight = typeArray.getDimension(R.styleable.calendar_gridview_attr_divider_height, resources.getDimension(R.dimen.dimen_divider_height))
        typeArray.recycle()
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }
}