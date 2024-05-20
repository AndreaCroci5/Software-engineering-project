package it.polimi.ingsw.am40.server.network.TCP;

import java.util.TimerTask;

//TODO doc

class TimeoutCounter extends TimerTask {
    public static int i = 0;

    private TimeOutCheckerInterface timeOutChecker;

    TimeoutCounter( TimeOutCheckerInterface timeOutChecker){
        this.timeOutChecker = timeOutChecker;
    }


    public void run()
    {
        Boolean stop = timeOutChecker.check(++i);
        if (stop) {
            System.out.println("Got stop inside TimerTask");
            this.cancel();
        }
    }
}