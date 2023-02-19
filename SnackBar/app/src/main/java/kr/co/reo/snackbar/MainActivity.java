package kr.co.reo.snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import kr.co.reo.snackbar.databinding.ActivityMainBinding;
import kr.co.reo.snackbar.databinding.CustomSnackbarBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;

    // SnackBar의 출현 시간을 LENGTH_INDEFINITE로 설정했을 경우
    // 메시지를 닫는 처리를 위해 멤버 변수로 선언한다.
    Snackbar snackbar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        Button1ClickListener listener1 = new Button1ClickListener();
        activityMainBinding.button.setOnClickListener(listener1);

        Button2ClickListener listener2 = new Button2ClickListener();
        activityMainBinding.button2.setOnClickListener(listener2);

        Button3ClickListener listener3 = new Button3ClickListener();
        activityMainBinding.button3.setOnClickListener(listener3);
    }

    class Button1ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // SnackBar 객체를 생성한다.
            // Snackbar snackbar1 = Snackbar.make(v, "기본 스낵바",Snackbar.LENGTH_SHORT);
            // Snackbar snackbar1 = Snackbar.make(v, "기본 스낵바",Snackbar.LENGTH_LONG);
            snackbar1 = Snackbar.make(v, "기본 스낵바", Snackbar.LENGTH_INDEFINITE);

            // 메시지 색상
            snackbar1.setTextColor(Color.RED);

            // 배경색
            snackbar1.setBackgroundTint(Color.BLUE);

            // 애니메이션
            //snackbar1.setAnimationMode(Snackbar.ANIMATION_MODE_FADE);
            snackbar1.setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE);

            //Action 설정
            SnackBarActionClickListener listener1 = new SnackBarActionClickListener();
            snackbar1.setAction("Action", listener1);

            // Callback 설정
            SnackBarCallBack callBack1 = new SnackBarCallBack();
            snackbar1.addCallback(callBack1);

            snackbar1.show();
        }
    }

    class Button2ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (snackbar1 != null) {
                // 현재 Snackbar 메시지가 보여지고 있는 상태라면..
                if (snackbar1.isShown() == true) {
                    // SnackBar 메시지를 사라지게 한다.
                    snackbar1.dismiss();
                }
            }
        }
    }

    // SnackBar의 Action을 클릭하면 사용하는 리스너
    class SnackBarActionClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            activityMainBinding.textView.setText("Action Click");
        }
    }

    // SnackBar의 Callback
    class SnackBarCallBack extends BaseTransientBottomBar.BaseCallback<Snackbar> {
        // SnackBar가 나타났을 때
        @Override
        public void onShown(Snackbar transientBottomBar) {
            super.onShown(transientBottomBar);
            activityMainBinding.textView2.setText("SnackBar가 나타났습니다");
        }

        @Override
        public void onDismissed(Snackbar transientBottomBar, int event) {
            super.onDismissed(transientBottomBar, event);
            activityMainBinding.textView2.setText("SnackBar가 사라졌습니다");

        }
    }

    class Button3ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // 스낵바를 생성한다
            Snackbar snackbar2 = Snackbar.make(v, "Custom SnackBar", Snackbar.LENGTH_SHORT);

            // ViewBinding
            CustomSnackbarBinding customSnackbarBinding = CustomSnackbarBinding.inflate(getLayoutInflater());
            customSnackbarBinding.imageView.setImageResource(R.drawable.img_android);
            customSnackbarBinding.textView3.setText("새로 추가된 View");
            customSnackbarBinding.textView3.setTextColor(Color.WHITE);

            // SnackBar Layout을 추출해서 새로운 뷰를 추가한다.
            Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar2.getView();
            layout.addView(customSnackbarBinding.getRoot());

            // 원래 SnackBar가 가지고 있는 텍스트뷰는 안보이게 한다.
            TextView snackText = layout.findViewById(com.google.android.material.R.id.snackbar_text);
            snackText.setVisibility(View.INVISIBLE);

            snackbar2.show();
        }
    }
}