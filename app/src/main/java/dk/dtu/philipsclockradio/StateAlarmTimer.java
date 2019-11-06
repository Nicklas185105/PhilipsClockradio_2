package dk.dtu.philipsclockradio;

public class StateAlarmTimer extends StateAdapter {

    private ContextClockradio mContext;
    private int alarm1or2 = 0;
    private int hour;
    private int minut;

    StateAlarmTimer (int n) {alarm1or2 = n;}

    @Override
    public void onEnterState(ContextClockradio context) {
        context.ui.turnOnTextBlink();
        hour = 12;
        minut = 00;
        context.updateAlarmHour(String.format("%02d", hour));
        context.updateAlarmMinut(String.format("%02d", minut));
        context.ui.setDisplayText(context.getAlarmHour() + ":" + context.getAlarmMinut());
    }

    @Override
    public void onExitState(ContextClockradio context) {
        context.ui.turnOffTextBlink();
    }

    @Override
    public void onClick_Hour(ContextClockradio context) {
        if (context.getAlarmHour().equals("23")){
            hour = 0;
        }
        else {
            hour++;
        }
        context.updateAlarmHour(String.format("%02d", hour));
        context.ui.setDisplayText(context.getAlarmHour() + ":" + context.getAlarmMinut());
    }

    @Override
    public void onClick_Min(ContextClockradio context) {
        if (context.getAlarmMinut().equals("59")){
            minut = 0;
        }
        else {
            minut++;
        }
        context.updateAlarmMinut(String.format("%02d", minut));
        context.ui.setDisplayText(context.getAlarmHour() + ":" + context.getAlarmMinut());
    }

    @Override
    public void onClick_AL1(ContextClockradio context) {
        context.updateAlarm1(context.getAlarmHour() + ":" + context.getAlarmMinut());
        /*if (context.getAlarm1Type() == 0) {
            context.updateAlarm1Type(1);
            context.ui.turnOnLED(2);
        }*/
        context.setState(new StateStandby(context.getTime()));
    }

    @Override
    public void onClick_AL2(ContextClockradio context) {
        context.updateAlarm2(context.getAlarmHour() + ":" + context.getAlarmMinut());
        /*if (context.getAlarm2Type() == 0) {
            context.updateAlarm2Type(1);
            context.ui.turnOnLED(5);
        }*/
        context.setState(new StateStandby(context.getTime()));
    }
}
