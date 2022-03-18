package net.kigawa.kutil.app;

public class Option {
    private final String opt;
    private final Character sOpt;

    protected Option(String opt, Character sOpt) {
        this.opt = opt;
        this.sOpt = sOpt;
    }

    public String getOpt() {
        return opt;
    }

    public char getsOpt() {
        return sOpt;
    }
}
