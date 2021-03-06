package com.github.blindpirate.gogradle.ide;

import com.github.blindpirate.gogradle.task.AbstractGolangTask;
import com.google.inject.Inject;
import org.gradle.api.tasks.TaskAction;

import static com.github.blindpirate.gogradle.task.GolangTaskContainer.INSTALL_BUILD_DEPENDENCIES_TASK_NAME;
import static com.github.blindpirate.gogradle.task.GolangTaskContainer.INSTALL_TEST_DEPENDENCIES_TASK_NAME;
import static com.github.blindpirate.gogradle.task.GolangTaskContainer.RENAME_VENDOR_TASK_NAME;

public class IntellijIdeTask extends AbstractGolangTask {
    @Inject
    private IntellijIdeIntegration intellijIdeIntegration;

    public IntellijIdeTask() {
        dependsOn(INSTALL_BUILD_DEPENDENCIES_TASK_NAME,
                INSTALL_TEST_DEPENDENCIES_TASK_NAME,
                RENAME_VENDOR_TASK_NAME);
    }

    @TaskAction
    public void generateXmls() {
        intellijIdeIntegration.generateXmls();
    }
}
