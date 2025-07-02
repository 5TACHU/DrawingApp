package com.example.androidgrafika;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class RysunekView extends View {

    private static class Linia {
        Path path;
        int kolor;
        PointF start;
        PointF end;

        Linia(Path p, int k, PointF s, PointF e) {
            path = p;
            kolor = k;
            start = s;
            end = e;
        }
    }

    private final ArrayList<Linia> linie = new ArrayList<>();
    private Path aktualnaSciezka;
    private int aktualnyKolor = 0xFFFF0000;
    private PointF startPoint;

    public RysunekView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setKolor(int kolor) {
        aktualnyKolor = kolor;
    }

    public void wyczysc() {
        linie.clear();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(6);
        paint.setAntiAlias(true);

        Paint punkt = new Paint();
        punkt.setStyle(Paint.Style.FILL);

        for (Linia l : linie) {
            paint.setColor(l.kolor);
            punkt.setColor(l.kolor);
            canvas.drawPath(l.path, paint);
            canvas.drawCircle(l.start.x, l.start.y, 10, punkt);
            canvas.drawCircle(l.end.x, l.end.y, 10, punkt);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                aktualnaSciezka = new Path();
                aktualnaSciezka.moveTo(x, y);
                startPoint = new PointF(x, y);
                return true;
            case MotionEvent.ACTION_MOVE:
                aktualnaSciezka.lineTo(x, y);
                invalidate();
                return true;
            case MotionEvent.ACTION_UP:
                aktualnaSciezka.lineTo(x, y);
                linie.add(new Linia(aktualnaSciezka, aktualnyKolor, startPoint, new PointF(x, y)));
                aktualnaSciezka = null;
                invalidate();
                return true;
        }
        return false;
    }
}
