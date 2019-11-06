package dk.dtu.philipsclockradio;

import android.os.CountDownTimer;
import android.os.Handler;
import java.util.Date;

public class StateStandby extends StateAdapter {

    private Date mTime;
    private static Handler mHandler = new Handler();
    private ContextClockradio mContext;

    StateStandby(Date time){
        mTime = time;

        /*if (mContext.getAlarm1Type() != null && mContext.getAlarm1Type() == 1){
            mCountDownTimer1.start();
        }

        if (mContext.getAlarm2Type() != null && mContext.getAlarm2Type() == 1){
            mCountDownTimer2.start();
        }*/
    }

    //Opdaterer hvert 60. sekund med + 1 min til tiden
    Runnable mSetTime = new Runnable() {

        @Override
        public void run() {
            if (!mContext.currentState.getClass().getSimpleName().equals("StateRadio")) {
                try {
                    long currentTime = mTime.getTime();
                    mTime.setTime(currentTime + 60000);
                    mContext.setTime(mTime);
                } finally {
                    mHandler.postDelayed(mSetTime, 60000);
                }
            }
        }
    };

    CountDownTimer mCountDownTimer1 = new CountDownTimer(1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            if (mContext.getAlarm1().equals(mContext.getTime().toString().substring(11,16))){
                mContext.setState(new StateAlarmRing());
                mCountDownTimer1.cancel();
            }
            else {
                mCountDownTimer1.start();
            }
        }
    };

    CountDownTimer mCountDownTimer2 = new CountDownTimer(1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            if (mContext.getAlarm2().equals(mContext.getTime().toString().substring(11,16))){
                mContext.setState(new StateAlarmRing());
                mCountDownTimer2.cancel();
            }
            else {
                mCountDownTimer2.start();
            }
        }
    };

    void startClock() {
        mSetTime.run();
        mContext.isClockRunning = true;
    }

    void stopClock() {
        mHandler.removeCallbacks(mSetTime);
        mContext.isClockRunning = false;
    }

    @Override
    public void onEnterState(ContextClockradio context) {
        //Lokal context oprettet for at Runnable kan få adgang
        mContext = context;

        context.updateDisplayTime();
        if(!context.isClockRunning){
            startClock();
        }
    }

    @Override
    public void onLongClick_Preset(ContextClockradio context) {
        stopClock();
        context.setState(new StateSetTime());
    }

    // Radio
    @Override
    public void onClick_Power(ContextClockradio context){
        context.setState(new StateRadio());
    }

    // Sleep
    @Override
    public void onClick_Sleep(ContextClockradio context) {
        context.setState(new StateSleep());
    }

    // AlarmTimer
    @Override
    public void onLongClick_AL1(ContextClockradio context) {
        context.setState(new StateAlarmTimer(1));
    }

    @Override
    public void onLongClick_AL2(ContextClockradio context) {
        context.setState(new StateAlarmTimer(2));
    }

    @Override
    public void onClick_AL1(ContextClockradio context) {
        if (context.getAlarm1Type() == 0){
            context.updateAlarm1Type(1);
            context.ui.turnOnLED(2);
            mCountDownTimer1.start();
        }
        else if (context.getAlarm1Type() == 1){
            context.updateAlarm1Type(2);
            context.ui.turnOnLED(1);
            context.ui.turnOffLED(2);
        }
        else {
            context.updateAlarm1Type(0);
            context.ui.turnOffLED(1);
            mCountDownTimer1.cancel();
        }
    }

    @Override
    public void onClick_AL2(ContextClockradio context) {
        if (context.getAlarm2Type() == 0){
            context.updateAlarm2Type(1);
            context.ui.turnOnLED(5);
            mCountDownTimer2.start();
        }
        else if (context.getAlarm2Type() == 1){
            context.updateAlarm2Type(2);
            context.ui.turnOnLED(4);
            context.ui.turnOffLED(5);
        }
        else {
            context.updateAlarm2Type(0);
            context.ui.turnOffLED(4);
            mCountDownTimer2.cancel();
        }
    }
}
