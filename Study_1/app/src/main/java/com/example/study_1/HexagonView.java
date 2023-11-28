package com.example.study_1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class HexagonView extends View {
    private Path path;

    public HexagonView(Context context) {
        super(context);
        init();
    }

    public HexagonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HexagonView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        path = new Path();
        float midx = getWidth() / 2;
        float midy = getHeight() / 2;

        path.moveTo(midx, midy);
        path.lineTo(midx + 150, midy + 220);
        path.lineTo(midx, midy + 220);
        path.lineTo(midx - 150, midy + 220);
        path.lineTo(midx - 300, midy);
        path.lineTo(midx - 150, midy - 220);
        path.lineTo(midx + 150, midy - 220);
        path.lineTo(midx + 300, midy);
        path.lineTo(midx + 150, midy + 220);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Path clipPath = new Path();
        clipPath.addPath(path);
        canvas.clipPath(clipPath);

        // 이 아래에 커스텀 레이아웃의 내용을 그릴 수 있습니다.
        // 예: 배경 색상 또는 이미지 그리기
        canvas.drawColor(Color.RED);

        // 이후에 다른 내용을 추가하거나 버튼처럼 사용할 수 있습니다.
    }
}
