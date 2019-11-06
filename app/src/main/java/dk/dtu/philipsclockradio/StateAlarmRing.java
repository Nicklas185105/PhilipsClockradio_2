package dk.dtu.philipsclockradio;

import android.os.CountDownTimer;

public class StateAlarmRing extends StateAdapter{

    private boolean isCounterRunning = false;
    private ContextClockradio mContext;

    StateAlarmRing(){}

    @Override
    public void onEnterState(ContextClockradio context) {
        mContext = context;

        if (context.getAlarm1Type() == 1 || context.getAlarm2Type() == 1){
            context.ui.setDisplayText("RING");
        }
        else if (context.getAlarm1Type() == 2 || context.getAlarm2Type() == 2){
            context.ui.setDisplayText("RADIO");
            context.ui.toggleRadioPlaying();
        }
        context.ui.turnOnTextBlink();
    }

    @Override
    public void onExitState(ContextClockradio context) {
        countDownTimer.cancel();
        context.updateDisplayTime();
        context.ui.turnOffTextBlink();
    }

    @Override
    public void onClick_AL1(ContextClockradio context) {
        if (context.getAlarm1Type() == 2 || context.getAlarm2Type() == 2){
            context.ui.toggleRadioPlaying();
        }
        context.setState(new StateStandby(context.getTime()));
    }

    @Override
    public void onClick_AL2(ContextClockradio context) {
        if (context.getAlarm1Type() == 2 || context.getAlarm2Type() == 2){
            context.ui.toggleRadioPlaying();
        }
        context.setState(new StateStandby(context.getTime()));
    }

    CountDownTimer countDownTimer = new CountDownTimer(540000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            onEnterState(mContext);
        }
    };

    @Override
    public void onClick_Snooze(final ContextClockradio context) {
        context.ui.setDisplayText("ZZZZ");
        countDownTimer.start();
    }
}
