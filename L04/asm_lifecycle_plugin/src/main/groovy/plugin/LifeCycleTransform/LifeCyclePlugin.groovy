package plugin.LifeCycleTransform

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

public class LifeCyclePlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        System.out.println("==plugin.LifeCycleTransform.LifeCyclePlugin gradle plugin==")

        def android = project.extensions.getByType(AppExtension)
        println '------------  registering AutoTrackTransform  ---------------'

        LifeCycleTransform transform = new LifeCycleTransform()
        android.registerTransform(transform)
    }
}