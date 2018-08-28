rootProject.name = "rr-core"

pluginManagement {
    repositories {
        jcenter()
        gradlePluginPortal()
    }

    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "gwt") {
                useModule("de.richsource.gradle.plugins:gwt-gradle-plugin:${requested.version}")
            }
        }
    }

}