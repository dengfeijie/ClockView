package com.example.clockview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.Calendar;

public class ClockView extends View {
    private Calendar time;
    private float centerX, centerY, radius;
    private float width, height;
    public int hour, minute, second;

    /**
     * 绘制表盘及时钟指针的画笔
     */
    private Paint paintPoint;
    private Paint paintCircle;
    private Paint paintHour;
    private Paint paintMinute;
    private Paint paintSecond;
    private Paint paintSecondMin;


    public ClockView(Context context) {
        super(context);
        initView();
    }

    public ClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        centerX = width / 2;
        centerY = height / 2;
        radius = Math.min(width / 3, height / 3);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制外圆
        canvas.drawCircle(centerX, centerY, radius, paintCircle);
        //绘制小时点
        int points = 0;
        int pointCount = 12;
        while (points++ < pointCount) {
            canvas.drawCircle(centerX, centerY / 2 + 150, 12, paintPoint);
            canvas.rotate(30f, centerX, centerY);
        }

        //绘制分钟点
        int pointMinute = 0;
        int minuteCount = 60;
        while (pointMinute++ < minuteCount) {
            canvas.drawCircle(centerX, centerY / 2 + 150, 5, paintSecondMin);
            canvas.rotate(6f, centerX, centerY);
        }

        //绘制圆心
        canvas.drawCircle(centerX, centerY, 12, paintPoint);
        canvas.save();
        drawHand(canvas);
        postInvalidateDelayed(1000);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void initView() {
        /**
         * 定义外圆表盘画笔
         */
        paintCircle = new Paint();
        paintCircle.setStyle(Paint.Style.FILL);
        paintCircle.setAntiAlias(true);
        paintCircle.setColor(Color.BLACK);

        /**
         * 定义小时圆点
         */
        paintPoint = new Paint();
        paintPoint.setStyle(Paint.Style.FILL);
        paintPoint.setAntiAlias(true);
        paintPoint.setColor(Color.WHITE);

        /**
         * 定义分钟圆点
         */
        paintSecondMin = new Paint();
        paintSecondMin.setStyle(Paint.Style.FILL);
        paintSecondMin.setAntiAlias(true);
        paintSecondMin.setColor(Color.WHITE);

        /**
         *定义小时画笔
         */
        paintHour = new Paint();
        paintHour.setAntiAlias(true);
        paintHour.setStrokeWidth(10f);
        paintHour.setStyle(Paint.Style.FILL);
        paintHour.setColor(Color.WHITE);

        /**
         *定义分钟画笔
         */
        paintMinute = new Paint();
        paintMinute.setAntiAlias(true);
        paintMinute.setStrokeWidth(8f);
        paintMinute.setStyle(Paint.Style.FILL);
        paintMinute.setColor(Color.WHITE);

        /**
         *定义秒针画笔
         */
        paintSecond = new Paint();
        paintSecond.setAntiAlias(true);
        paintSecond.setStrokeWidth(2f);
        paintSecond.setStyle(Paint.Style.FILL);
        paintSecond.setColor(Color.RED);
    }

    private void drawHand(Canvas canvas) {
        time = Calendar.getInstance();
        hour = time.get(Calendar.HOUR);
        minute = time.get(Calendar.MINUTE);
        second = time.get(Calendar.SECOND);
        //时针转过的角度
        float angleHour = hour * (360 / 12) + minute / 60 * 30;
        //分针转过的角度
        float angleMinute = minute * 6;
        //秒针转过的角度
        float angleSecond = second * 6;
        //绘制时针
        canvas.save();
        canvas.rotate(angleHour, centerX, centerY);
        canvas.drawLine(centerX, centerY, centerX, centerY / 2 + 350, paintHour);
        canvas.restore();

        canvas.save();
        canvas.rotate(angleMinute, centerX, centerY);
        canvas.drawLine(centerX, centerY, centerX, centerY / 2 + 300, paintMinute);
        canvas.restore();

        canvas.save();
        canvas.rotate(angleSecond, centerX, centerY);
        canvas.drawLine(centerX, centerY, centerX, centerY / 2 + 200, paintSecond);
        canvas.restore();
    }

    public String getTime() {
        return hour + " : " + minute + " : " + second;
    }
}
