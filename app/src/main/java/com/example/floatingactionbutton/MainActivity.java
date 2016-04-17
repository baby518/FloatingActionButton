package com.example.floatingactionbutton;

import android.animation.Animator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private boolean mIsWorking = false;

    private ArrayList<ImageView> buttons = new ArrayList<>();

    private static final int[] buttonIds = {
            R.id.floating_action_button,
            R.id.ripple_button,
            R.id.ripple_button_with_anim,
            R.id.default_button_borderless,
            R.id.default_button_border
    };

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            toggleWorking();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int id : buttonIds) {
            View view = findViewById(id);
            if (view != null && view instanceof ImageView) {
                view.setOnClickListener(mOnClickListener);
                buttons.add((ImageView) view);
            }
        }

    }

    private boolean useAnimation(int id) {
        return id == R.id.ripple_button_with_anim
                || id == R.id.default_button_borderless
                || id == R.id.default_button_border;
    }

    private void toggleWorking() {
        mIsWorking = !mIsWorking;

        for (ImageView view : buttons) {
            if (useAnimation(view.getId())) {
                createAnimation(view).start();
            }
            view.setImageResource(mIsWorking ? R.drawable.ic_pause : R.drawable.ic_mic);
        }
    }

    public Animator createAnimation(View v) {
        Animator animator;
        animator = ViewAnimationUtils.createCircularReveal(
                v,
                v.getWidth() / 2,
                v.getHeight() / 2,
                0,
                v.getWidth());
//        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(800);
        return animator;
    }
}
