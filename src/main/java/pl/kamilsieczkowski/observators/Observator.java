package pl.kamilsieczkowski.observators;

public class Observator {
    private boolean isObservatatedProcessExecuted;

    public boolean isObservatatedProcessIsNotExecuted() {
        return !isObservatatedProcessExecuted;
    }

    public void setObservatatedProcessExecuted(boolean observatatedProcessExecuted) {
        this.isObservatatedProcessExecuted = observatatedProcessExecuted;
    }
}
