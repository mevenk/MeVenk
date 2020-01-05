export class RunningClock {

    constructor(private hours: number, private minutes: number, private seconds: number, private millis: number) { }

    stringify(): string {
        let runningClockString = this.hours + ':' + this.minutes + ':' + this.seconds;
        if (this.millis != null) {
            runningClockString += ':' + this.millis;
        }
        return runningClockString;
    }

}
