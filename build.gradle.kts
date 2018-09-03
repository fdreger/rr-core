import de.richsource.gradle.plugins.gwt.GwtCompile
import de.richsource.gradle.plugins.gwt.GwtDraftCompile

plugins {
    java
    application
    id("net.ltgt.apt-idea").version("0.18")
    id("gwt").version("0.6")
}

group = "net.snowyhollows.omg.rr"
version = "1.0-SNAPSHOT"

application {
    mainClassName = "net.snowyhollows.ogam.rr.Main"
}

repositories {
    jcenter()
    maven("https://dl.bintray.com/snowyhollows/default/")
}

dependencies {
    testCompile("junit", "junit", "4.12")
    compile("com.googlecode.lanterna", "lanterna", "3.0.1")
    compile("net.snowyhollows.beach", "beach", "1.0", null, "sources")
    compile("net.snowyhollows.beach", "beach", "1.0")
    compile("net.snowyhollows.bento", "bento2-core", "1.1.2")
    compile ("com.google.gwt", "gwt", "2.8.2")
    compile ("com.google.elemental2", "elemental2-core", "1.0.0-RC1")
    compile ("com.google.elemental2", "elemental2-dom", "1.0.0-RC1")
    annotationProcessor("net.snowyhollows.bento", "bento2-generator", "1.0")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks.create("addSource") {
    java.sourceSets {
        get("main").compileClasspath.add(files("build/generated/source/apt/main"))
    }
}

tasks.getting(GwtCompile::class) {
    dependsOn("addSource")
}

tasks.getting(GwtDraftCompile::class) {
    dependsOn("addSource")
}

gwt {
    gwtVersion = "2.8.2"
    modules = mutableListOf("RrCore")
    minHeapSize = "2048M";
    maxHeapSize = "2048M";

}
