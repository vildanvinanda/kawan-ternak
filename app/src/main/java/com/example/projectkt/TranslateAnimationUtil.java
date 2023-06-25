package com.example.projectkt;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class TranslateAnimationUtil implements View.OnTouchListener {
    private GestureDetector mGestureDetector;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    public class SimpleGestureDetector extends GestureDetector.SimpleOnGestureListener{
        private ImageView btnscrolldown;
        private boolean isFinishAnimation;


        public SimpleGestureDetector(ImageView sendPicturechat) {
            this.btnscrolldown = sendPicturechat;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

            if (distanceY > 0)
            {
                hiddenView();
            } else {
                showView();
            }

            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        private void showView() {
            if (btnscrolldown == null || btnscrolldown.getVisibility() == View.VISIBLE){
                return;
            }
//            Animation animation = AnimationUtils.loadAnimation(sendPicturechat.getContext(), R.anim.bottom_down);

        }

        private void hiddenView() {
            if (btnscrolldown == null || btnscrolldown.getVisibility() == View.GONE){
                return;
            }
        }

    }


}
