package com.shan.mypubliclibrary.manager;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;

/**
 * 动画管理
 * Created by 陈俊山 on 2016/7/22.
 */

public class AnimatorManager {
    public static final String TRANSLATIONX = "translationX";//左右移动
    public static final String TRANSLATIONY = "translationY";//上下移动
    public static final String ROTATION = "rotation";//旋转
    public static final String ALPHA = "alpha";//渐变

    /**
     * 单效果动画
     * @param object View
     * @param start 动画开始位置
     * @param end 动画结束位置
     * @param duration 动画持续时间
     * @param action 动画效果
     */
    public static void startAnimotion(Object object, float start, float end, int duration, String action) {
        ObjectAnimator.ofFloat(object, action, start, end).setDuration(duration).start();
    }

    /**
     *  多效果动画
     * @param object View
     * @param duration 动画持续时间
     * @param valuesHolders 各个动画效果
     */
    public static void startMultiAnimotion(Object object, int duration, PropertyValuesHolder... valuesHolders){
        ObjectAnimator.ofPropertyValuesHolder(object,valuesHolders).setDuration(duration).start();
    }

    private static AnimatorSet set = null;

    /**
     *  多效果动画
     * @param duration 动画持续时间
     * @param animators 各个动画效果
     */
    public static void startMultiAnimotion(int duration, Animator... animators){
        if (set==null){
            set = new AnimatorSet();
        }
        set.playTogether(animators);
        set.setDuration(duration);
        set.start();
    }

    /**
     *  按顺序动画播放
     * @param duration 动画持续时间
     * @param animators 各个动画效果
     */
    public static void startSequentAnimotion(int duration, Animator... animators){
        if (set==null){
            set = new AnimatorSet();
        }
        set.playSequentially(animators);
        set.setDuration(duration);
        set.start();
    }

    /**
     *  各种动画效果的组合
     * @param duration 动画持续时间
     * @param animators 各个动画效果
     */
    public static void startCombineAnimotion(int duration, Animator... animators){
        if (set==null){
            set = new AnimatorSet();
        }
        ////////////////////////
        //这里自定义组合动画，例如：动画1,2一起播放后再开始动画3的播放
        set.play(animators[0]).with(animators[1]);
        set.play(animators[3]).after(animators[0]);//after中可以填1或者2的动画
        //////////////////////
        set.setDuration(duration);
        set.start();
    }

    /**
     * 除了上面几个方法，还有一些其他的动画功能还没写出来，后期实现
     * 例如：
     * 1、动画的监听事件；new ObjectAnimator().addListener(new Animator.AnimatorListener()；
     *   new ObjectAnimator().addListener(new AnimatorListenerAdapter() 共两种方式，选择其中一种
     * 2、动画渐变效果；
     * 3、动画放大缩小效果；
     * 4、ValueAnimator；
     */
}
