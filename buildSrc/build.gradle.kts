import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `kotlin-dsl`
}

// Required since Gradle 4.10+.
repositories {
    jcenter()
    maven(url = "http://jcenter.bintray.com/")
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}
