package dk.dtu.philipsclockradio;

public class StatePresetRadio extends StateAdapter{

    private int preset = 1;

    StatePresetRadio(){}

    @Override
    public void onEnterState(ContextClockradio context) {
        context.ui.turnOnTextBlink();
        context.ui.setDisplayText(String.valueOf(preset));
    }

    @Override
    public void onExitState(ContextClockradio context) {
        context.ui.turnOffTextBlink();
    }

    @Override
    public void onClick_Min(ContextClockradio context) {
        if (preset == 20){
            preset = 1;
        }
        else {
            preset++;
        }
        context.ui.setDisplayText(String.valueOf(preset));
    }

    @Override
    public void onClick_Hour(ContextClockradio context) {
        if (preset == 1){
            preset = 20;
        }
        else {
            preset--;
        }
        context.ui.setDisplayText(String.valueOf(preset));
    }

    @Override
    public void onLongClick_Preset(ContextClockradio context) {
        if (preset == 20){
            preset = 1;
        }
        else {
            preset++;
        }
        context.ui.setDisplayText(String.valueOf(preset));
    }

    @Override
    public void onClick_Preset(ContextClockradio context) {
        context.savePreset(preset);
        context.setState(new StateRadio());
    }
}
