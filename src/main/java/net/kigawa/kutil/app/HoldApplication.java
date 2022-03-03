package net.kigawa.kutil.app;

import java.util.ArrayList;
import java.util.List;

public abstract class HoldApplication extends Application {
    private final List<Option> allOpt = new ArrayList<>();
    private final List<Option> opt = new ArrayList<>();
    private final String[] args;

    protected HoldApplication(String[] args) {
        this.args = args;
        addAllOpt(allOpt);

        for (String arg : args) {
            if (arg == null) continue;
            for (Option option : allOpt) {
                if (arg.startsWith("--")) {
                    if (option.getOpt().equals(arg.substring(2))) {
                        opt.add(option);
                        continue;
                    }
                }
                if (arg.startsWith("-")) {
                    if (arg.contains(Character.toString(option.getsOpt()))) {
                        opt.add(option);
                        continue;
                    }
                }
            }
        }
    }

    protected abstract void addAllOpt(List<Option> options);

    public boolean hasOpt(Option option) {
        return hasOpt(option.getOpt());
    }

    public boolean hasOpt(String opt) {
        for (Option option : this.opt) {
            if (option.getOpt().equals(opt)) return true;
        }
        return false;
    }

    public boolean hasOpt(char opt) {
        for (Option option : this.opt) {
            if (option.getsOpt() == opt) return true;
        }
        return false;
    }
}
