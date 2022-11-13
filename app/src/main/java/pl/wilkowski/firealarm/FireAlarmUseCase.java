package pl.wilkowski.firealarm;

class FireAlarmUseCase {
    private Alarm alarm;

    FireAlarmUseCase(Alarm alarm) {
        this.alarm = alarm;
    }

    void invoke() {
        alarm.start();
    }
}
