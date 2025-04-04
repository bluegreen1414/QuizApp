import com.diffplug.gradle.spotless.SpotlessExtension

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidTest) apply false
    alias(libs.plugins.baselineprofile) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.spotless) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ksp) apply false
}

subprojects {
    plugins.apply(rootProject.libs.plugins.spotless.get().pluginId)
    configure<SpotlessExtension> {
        kotlin {
            target("**/*.kt")
            targetExclude("**/camera/viewfinder/**")
            ktlint(libs.ktlint.get().version)
        }
        kotlinGradle {
            ktlint(libs.ktlint.get().version)
        }
    }
}
