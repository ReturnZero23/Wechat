package Recordingbtn;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

import com.example.ruturnzer0.wechat.R;

/**
 * Created by RuturnZer0 on 6/26.
 */
public class Recording_btn extends Button {
    private static final int DISTANCE_Y_CANCEL = 50;
    private static final int STATE_NORMAL = 1;
    private static final int STATE_RECORDING = 2;
    private static final int STATE_WANT_CANCEL = 3;

    private int mCurState = STATE_NORMAL;
    private boolean isRecording = false;

    public Recording_btn(Context context) {
        this(context, null);
    }

    public Recording_btn(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int x = (int)event.getX();
        int y = (int)event.getY();
        switch(action)
        {
            case MotionEvent.ACTION_DOWN:
                changeState(STATE_RECORDING);
                break;
            case MotionEvent.ACTION_MOVE:

                if(isRecording)
                {

                }
                if(wantToCancel(x,y))
                    changeState(STATE_WANT_CANCEL);
                else
                    changeState(STATE_RECORDING);
                break;
            case MotionEvent.ACTION_UP:
                if(mCurState==STATE_RECORDING){
                    //realse
                    //callback
                }
                else if(mCurState==STATE_WANT_CANCEL){
                    //cancel
                }
                reset();
                break;

        }

        return super.onTouchEvent(event);
    }

    private void reset() {
        isRecording = false;
        changeState(STATE_NORMAL);
    }

    private boolean wantToCancel(int x, int y) {
        if(x<0||x>getWidth())
        {
            return true;
        }
        if(y<-DISTANCE_Y_CANCEL||y>getHeight()+DISTANCE_Y_CANCEL){
            return true;
        }
        return false;
    }

    private void changeState(int state) {
        if(mCurState!=state){
            mCurState=state;
            switch(state) {
                case STATE_NORMAL:
                    setBackgroundResource(R.drawable.btn_recorder_normal);
                    setText(R.string.recording_btn_normal);
                    break;
                case STATE_RECORDING:
                    setBackgroundResource(R.drawable.btn_recorder_recording);
                    setText(R.string.recording_btn_recording);
                    if(isRecording){
                        //TODO
                    }
                    break;
                case STATE_WANT_CANCEL:
                    setBackgroundResource(R.drawable.btn_recorder_recording);
                    setText(R.string.recording_btn_want_cancel);
                    if(isRecording){
                        //TODO
                    }
                    break;
            }
        }
    }
}
