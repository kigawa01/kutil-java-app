package net.kigawa.kutil.app;

import net.kigawa.kutil.kutil.file.FileUtil;
import net.kigawa.kutil.kutil.thread.ThreadExecutors;
import net.kigawa.kutil.log.log.Logger;
import net.kigawa.kutil.terminal.Terminal;
import net.kigawa.kutil.thread.ThreadExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public abstract class ApplicationBase extends MinApplicationBase {
    public final Logger logger;
    public final ThreadExecutor executor;
    public final Terminal terminal;
    public final Level log;
    public final boolean jline;
    private final List<Option> opt = new ArrayList<>();

    protected ApplicationBase(String[] args) {

        List<Option> allOpt = new ArrayList<>();
        allOpt.add(new Option("log-fine", 'd'));
        allOpt.add(new Option("no-jline", null));

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

        if (hasOpt("log-fine")) log = Level.FINE;
        else log = Level.INFO;
        jline = !hasOpt("no-jline");

        logger = new Logger(getClass().getName(), null, log, FileUtil.getRelativeFile("log"));
        executor = new ThreadExecutor(Terminal.class.getName(), logger);
        terminal = new Terminal(jline, logger);

        addModule(logger);
        addModule(executor);
        addModule(new ThreadExecutors(logger));
        addModule(terminal);
    }

    protected abstract void addAllOpt(List<Option> options);

    public boolean hasOpt(String opt) {
        for (Option option : this.opt) {
            if (option.getOpt().equals(opt)) return true;
        }
        return false;
    }

    public boolean hasOpt(Option option) {
        return hasOpt(option.getOpt());
    }

    public boolean hasOpt(char opt) {
        for (Option option : this.opt) {
            if (option.getsOpt() == opt) return true;
        }
        return false;
    }
}
