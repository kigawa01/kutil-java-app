package net.kigawa.kutil.app;

import java.util.ArrayList;
import java.util.List;

public abstract class HoldApplication extends Application {
    private final List<Option> allOpt;
    private final List<Option> opt = new ArrayList<>();

    protected HoldApplication() {
        allOpt = getAllOpt();
    }

    protected abstract List<Option> getAllOpt();
}
