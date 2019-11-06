package dk.dtu.philipsclockradio;

import java.util.Calendar;
import java.util.Date;

//
public class ContextClockradio {
    public State currentState;
    public int preset = 0;
    private Date mTime;
    private String mDisplayText;
    private Double frekvens;
    private String frekvenser;
    private Integer sleepTimer;
    private String alarmHour;
    private String alarmMinut;
    private String alarm1;
    private Integer alarm1Type = 0;
    private String alarm2;
    private Integer alarm2Type = 0;
    public boolean isClockRunning = false;
    Double[] fmFrekvens = new Double[20];
    Double[] amFrekvens = new Double[20];

    public static MainUI ui;

    public ContextClockradio(MainUI context){
        ui = context;

        //Sætter tiden til 12.00, hvis tiden ikke er sat endnu
        if(mTime == null){
            Calendar date = Calendar.getInstance();
            date.set(2019, 1, 1, 12, 00);
            mTime = date.getTime();
        }

        if (frekvenser == null) {
            frekvenser = "FM";
        }

        if (frekvens == null){
            frekvens = 90.0;
        }

        if (sleepTimer == null){
            sleepTimer = 120;
        }

        if (alarmHour == null && alarmMinut == null){
            alarmHour = "12";
            alarmMinut = "00";
        }

        if (alarm1 == null && alarm2 == null){
            alarm1 = "12:00";
            alarm2 = "12:00";
        }

        //Når app'en starter, så går vi ind i Standby State
        currentState = new StateStandby(mTime);
        currentState.onEnterState(this);
    }

    //setState er når vi skifter State
    void setState(final State newState) {
        currentState.onExitState(this);
        currentState = newState;
        currentState.onEnterState(this);
        System.out.println("Current state: "+ newState.getClass().getSimpleName());
    }

    //Opdaterer kontekst time state og UI
    void setTime(Date time){
        mTime = time;
        if(currentState.getClass().getSimpleName().equals("StateStandby") || currentState.getClass().getSimpleName().equals("StateRadio")){
            updateDisplayTime();
        }
    }


    void updateDisplayTime(){
        mDisplayText = mTime.toString().substring(11,16);
        ui.setDisplayText(mDisplayText);
    }

    Date getTime(){
        return mTime;
    }

    void updateFrekvenser (String s){
        frekvenser = s;
    }

    String getFrekvenser (){
        return frekvenser;
    }

    void updateFrekvens (Double n){
        frekvens += n;
    }

    Double getFrekvens(){
        return frekvens;
    }

    void savePreset (int n){
        if (frekvenser.equals("FM")){
            fmFrekvens[n] = frekvens;
        }
        else {
            amFrekvens[n] = frekvens;
        }
    }

    void updateSleepTimer(Integer n){
        sleepTimer = n;
    }

    Integer getSleepTimer (){
        return sleepTimer;
    }

    void updateAlarmHour(String hour){
        alarmHour = hour;
    }

    void updateAlarmMinut(String minut){
        alarmMinut = minut;
    }

    String getAlarmHour(){
        return alarmHour;
    }

    String getAlarmMinut(){
        return alarmMinut;
    }

    void updateAlarm1(String clock){
        alarm1 = clock;
    }

    String getAlarm1(){
        return alarm1;
    }

    void updateAlarm1Type(int n){
        alarm1Type = n;
    }

    String getAlarm2(){
        return alarm2;
    }

    Integer getAlarm1Type(){
        return alarm1Type;
    }

    void updateAlarm2(String clock){
        alarm2 = clock;
    }

    void updateAlarm2Type(int n){
        alarm2Type = n;
    }

    Integer getAlarm2Type(){
        return alarm2Type;
    }

    //Disse metoder bliver kaldt fra UI tråden
    public void onClick_Hour() {
        currentState.onClick_Hour(this);
    }

    public void onClick_Min() {
        currentState.onClick_Min(this);
    }

    public void onClick_Preset() {
        currentState.onClick_Preset(this);
    }

    public void onClick_Power() {
        currentState.onClick_Power(this);
    }

    public void onClick_Sleep() {
        currentState.onClick_Sleep(this);
    }

    public void onClick_AL1() {
        currentState.onClick_AL1(this);
    }

    public void onClick_AL2() {
        currentState.onClick_AL2(this);
    }

    public void onClick_Snooze() {
        currentState.onClick_Snooze(this);
    }

    public void onLongClick_Hour(){
        currentState.onLongClick_Hour(this);
    }

    public void onLongClick_Min(){
        currentState.onLongClick_Min(this);
    }

    public void onLongClick_Preset(){
        currentState.onLongClick_Preset(this);
    }

    public void onLongClick_Power(){
        currentState.onLongClick_Power(this);
    }

    public void onLongClick_Sleep(){
        currentState.onLongClick_Sleep(this);
    }

    public void onLongClick_AL1(){
        currentState.onLongClick_AL1(this);
    }

    public void onLongClick_AL2(){
        currentState.onLongClick_AL2(this);
    }

    public void onLongClick_Snooze(){
        currentState.onLongClick_Snooze(this);
    }
}