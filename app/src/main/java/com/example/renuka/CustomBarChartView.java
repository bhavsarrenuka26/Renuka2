package com.example.renuka;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import android.content.Context;

import java.util.List;
import java.util.Map;

    public class CustomBarChartView extends View {

        private List<Float> data;
        private List<String> labels;
        private Paint barPaint, textPaint, axisPaint;

        public CustomBarChartView(Context context, AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        private void init() {
            barPaint = new Paint();
            barPaint.setColor(Color.BLUE);
            barPaint.setStyle(Paint.Style.FILL);

            textPaint = new Paint();
            textPaint.setColor(Color.BLACK);
            textPaint.setTextSize(30f);

            axisPaint = new Paint();
            axisPaint.setColor(Color.GRAY);
            axisPaint.setStrokeWidth(3f);
        }

        public void setData(List<Float> data, List<String> labels) {
            this.data = data;
            this.labels = labels;
            invalidate(); // redraw the view
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            if (data == null || data.isEmpty()) return;

            float width = getWidth();
            float height = getHeight();

            float paddingLeft = 100f;
            float paddingBottom = 100f;
            float paddingTop = 50f;
            float usableHeight = height - paddingBottom - paddingTop;
            float usableWidth = width - paddingLeft;

            float maxVal = getMax(data);
            float barWidth = usableWidth / (data.size() * 2);

            // Draw X-axis
            canvas.drawLine(paddingLeft, height - paddingBottom, width, height - paddingBottom, axisPaint);

            // Draw bars and X labels
            for (int i = 0; i < data.size(); i++) {
                float value = data.get(i);
                float left = paddingLeft + (i * 2 + 1) * barWidth;
                float top = height - paddingBottom - (value / maxVal * usableHeight);
                float right = left + barWidth;
                float bottom = height - paddingBottom;

                canvas.drawRect(left, top, right, bottom, barPaint);

                if (labels != null && i < labels.size()) {
                    canvas.drawText(labels.get(i), (left + right) / 2, height - paddingBottom + 30, textPaint);
                }
            }

            // Draw Y-axis
            canvas.drawLine(paddingLeft, paddingTop, paddingLeft, height - paddingBottom, axisPaint);

            // Draw Y-axis scale labels (0, 25%, 50%, 75%, 100%)
            int steps = 4;
            for (int i = 0; i <= steps; i++) {
                float yVal = maxVal * i / steps;
                float y = height - paddingBottom - (yVal / maxVal * usableHeight);
                canvas.drawText(String.valueOf((int) yVal), paddingLeft - 40, y + 10, textPaint);
                canvas.drawLine(paddingLeft - 10, y, paddingLeft, y, axisPaint);
            }


            // X-Axis label: "Timestamp"
            canvas.drawText("Timestamp", paddingLeft + usableWidth / 2f, height - 20, textPaint);

            // Y-Axis label: "Pitch" (rotated)
            canvas.save();
            canvas.rotate(-90, 30, height / 2f);
            canvas.drawText("Pitch", 30, height / 2f, textPaint);
            canvas.restore();
        }


        private float getMax(List<Float> data) {
            float max = Float.MIN_VALUE;
            for (float val : data) if (val > max) max = val;
            return max;
        }
    }
