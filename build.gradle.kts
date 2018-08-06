plugins {
    java
    application
    id("net.ltgt.apt-idea").version("0.18")
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
    compile("net.snowyhollows.beach", "beach", "1.0")
    compile("net.snowyhollows.beach", "beach", "1.0")
    annotationProcessor("net.snowyhollows.bento", "bento2-core", "1.1.2")
    annotationProcessor("net.snowyhollows.bento", "bento2-generator", "1.0")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}