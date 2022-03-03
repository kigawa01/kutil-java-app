package net.kigawa.kutil.app;

import net.kigawa.kutil.kutil.interfaces.Module;

import java.util.LinkedList;

public abstract class Application implements Module {
    private final LinkedList<Module> moduleList = new LinkedList<>();
    private boolean run = false;

    public void enableAddModule(Module module) {
        addModule(module);
        enableModule(module);
    }

    public void enableModule(Module module) {
        try {
            module.enable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addModule(Module module) {
        moduleList.add(module);
    }

    public boolean isRun() {
        return run;
    }

    public void enable() {
        run = true;
        for (Module module : moduleList) {
            enableModule(module);
        }
        try {
            onEnable();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void disable() {
        try {
            onDisable();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = moduleList.size() - 1; i >= 0; i--) {
            disableModule(moduleList.get(i));
        }
        run = false;
    }

    public void disableModule(Module module) {
        try {
            module.disable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract void onDisable();

    protected abstract void onEnable();
}
