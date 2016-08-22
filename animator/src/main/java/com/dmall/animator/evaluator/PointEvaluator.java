package com.dmall.animator.evaluator;

import android.animation.TypeEvaluator;

/**
 * ValueAnimator还有一个方法是ofObject方法,是用于对任何对象进行动画操作的
 * 因为系统无法得知对象如何从初始对象到结束对象,因此我们需要实现一个自己的TypeEvaluator告知系统如何进行过渡
 * Created by yelong on 16/7/18.
 * mail:354734713@qq.com
 */
public class PointEvaluator implements TypeEvaluator<Point> {
    @Override
    public Point evaluate(float fraction, Point startPoint, Point endPoint) {
        float x = startPoint.getX() + fraction * (endPoint.getX() - startPoint.getX());
        float y = startPoint.getY() + fraction * (endPoint.getY() - startPoint.getY());
        return new Point(x, y);
    }
}
