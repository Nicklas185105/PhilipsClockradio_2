package dk.dtu.philipsclockradio;

import android.os.CountDownTimer;

public class StateSleep extends StateAdapter {

    private ContextClockradio mContext;
    private boolean isCounterRunning = false;

    StateSleep(){}

    CountDownTimer mCountDownTimer = new CountDownTimer(5000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            isCounterRunning = false;

            mContext.setState(new StateStandby(mContext.getTime()));
        }
    };

    public void sleep(){
        if (!isCounterRunning){
            isCounterRunning = true;
            mCountDownTimer.start();
        }
        else {
            mCountDownTimer.cancel();
            mCountDownTimer.start();
        }
    }

    @Override
    public void onEnterState(ContextClockradio context) {
        mContext = context;

        context.ui.turnOnTextBlink();
        context.ui.setDisplayText(context.getSleepTimer().toString());
        context.ui.turnOnLED(3);
        sleep();
    }

    @Override
    public void onExitState(ContextClockradio context) {
        context.ui.turnOffTextBlink();
        context.updateDisplayTime();
    }

    @Override
    public void onClick_Sleep(ContextClockradio context) {
        if (context.getSleepTimer().equals(0)) {
            context.updateSleepTimer(120);
            context.ui.setDisplayText(context.getSleepTimer().toString());
            context.ui.turnOnLED(3);
        }
        else if (context.getSleepTimer().equals(120)) {
            context.updateSleepTimer(90);
            context.ui.setDisplayText(context.getSleepTimer().toString());
        }
        else if (context.getSleepTimer().equals(90)) {
            context.updateSleepTimer(60);
            context.ui.setDisplayText(context.getSleepTimer().toString());
        }
        else if (context.getSleepTimer().equals(60)) {
            context.updateSleepTimer(30);
            context.ui.setDisplayText(context.getSleepTimer().toString());
        }
        else if (context.getSleepTimer().equals(30)) {
            context.updateSleepTimer(15);
            context.ui.setDisplayText(context.getSleepTimer().toString());
        }
        else {
            context.updateSleepTimer(0);
            context.ui.setDisplayText("OFF");
            context.ui.turnOffLED(3);
        }
        sleep();
    }
}
