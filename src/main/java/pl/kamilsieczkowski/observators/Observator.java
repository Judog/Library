package pl.kamilsieczkowski.observators;

public class Observator {
    private boolean isObservatatedProcessExecuted;

    public boolean isObservatatedProcessNotExecuted() {
        return !isObservatatedProcessExecuted;
    }

    public void setObservatatedProcessNotExecuted(boolean observatatedProcessExecuted) {
        this.isObservatatedProcessExecuted = observatatedProcessExecuted;
    }
}
