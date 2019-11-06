package dk.dtu.philipsclockradio;

import android.os.CountDownTimer;
import android.os.Handler;

public class StateRadio extends StateAdapter{

    private ContextClockradio mContext;
    private boolean isCounterRunning = false;

    StateRadio (){}

    CountDownTimer mCountDownTimer = new CountDownTimer(1000, 1000) {
        @Override
        public void onTick(long millisUntilFinnished) {

        }

        @Override
        public void onFinish() {
            isCounterRunning = false;
            mContext.updateDisplayTime();
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
    public void onEnterState(final ContextClockradio context) {
        mContext = context;

        context.ui.toggleRadioPlaying();
        context.ui.setDisplayText(context.getFrekvenser());
        sleep();
    }

    @Override
    public void onClick_Power(ContextClockradio context) {
        if (context.getFrekvenser().equals("FM")) {
            context.updateFrekvenser("AM");
            context.ui.setDisplayText(context.getFrekvenser());
            sleep();
        }
        else {
            context.updateFrekvenser("FM");
            context.ui.setDisplayText(context.getFrekvenser());
            sleep();
        }
    }

    @Override
    public void onLongClick_Power(ContextClockradio context) {
        context.setState(new StateStandby(context.getTime()));
        context.ui.toggleRadioPlaying();
    }

    @Override
    public void onClick_Hour(ContextClockradio context) {
        context.updateFrekvens(-0.1);
        context.ui.setDisplayText(String.valueOf(context.getFrekvens()));
        sleep();
    }

    @Override
    public void onClick_Min(ContextClockradio context) {
        context.updateFrekvens(0.1);
        context.ui.setDisplayText(String.valueOf(context.getFrekvens()));
        sleep();
    }

    @Override
    public void onLongClick_Preset(ContextClockradio context) {
        context.setState(new StatePresetRadio());
        context.ui.toggleRadioPlaying();
    }

    /*@Override
    public void onClick_Preset(ContextClockradio context) {
        if (context.getFrekvenser().equals("FM")){
            if (context.preset == 20){
                context.preset = 1;
            }
            else {
                context.preset++;
            }
            if (context.fmFrekvens[context.preset-1] != null) {
                context.updateFrekvens(context.fmFrekvens[context.preset-1]);
                context.ui.setDisplayText(String.valueOf(context.getFrekvens()));
                sleep();
            }
        }
    }*/
}
