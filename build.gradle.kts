import org.jetbrains.changelog.Changelog
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

fun properties(key: String) = providers.gradleProperty(key)
fun environment(key: String) = providers.environmentVariable(key)

plugins {
    id("java")
    id("antlr")
    alias(libs.plugins.kotlin)
    alias(libs.plugins.intelliJPlatform)
    alias(libs.plugins.changelog)
    alias(libs.plugins.qodana)
    alias(libs.plugins.kover)
}

sourceSets {
    main {
        java {
            srcDir(layout.buildDirectory.dir("generated-src/antlr/main"))
        }
    }
}
group = properties("pluginGroup").get()
version = properties("pluginVersion").get()

repositories {
    mavenCentral()
    intellijPlatform {
        defaultRepositories()
    }
}

dependencies {
    intellijPlatform {
        intellijIdea(properties("platformVersion"))   // was: create(properties("platformType"), properties("platformVersion"))
        plugins(properties("platformPlugins").map { it.split(',').map(String::trim).filter(String::isNotEmpty) })
        pluginVerifier()
        zipSigner()
    }
    implementation("dev.karmakrafts.antlr4:antlr4-intellij-adaptor:0.2.2")
    antlr("org.antlr:antlr4:4.13.1")
}

kotlin {
    jvmToolchain(25)
    compilerOptions {
        jvmTarget = JvmTarget.JVM_21 // bytecode target — keep at 21 unless you also raise pluginSinceBuild past whatever build ships JBR 25
    }
}

intellijPlatform {
    instrumentCode = false

    pluginConfiguration {
        version = properties("pluginVersion")

        ideaVersion {
            sinceBuild = properties("pluginSinceBuild")
            untilBuild = properties("pluginUntilBuild").map { it.ifBlank { null } }
        }

        val changelog = project.changelog
        changeNotes = properties("pluginVersion").map { pluginVersion ->
            with(changelog) {
                renderItem(
                    (getOrNull(pluginVersion) ?: getUnreleased())
                        .withHeader(false)
                        .withEmptySections(false),
                    Changelog.OutputType.HTML,
                )
            }
        }
    }

    pluginVerification {
        ides {
            recommended()
        }
    }

    signing {
        certificateChain = environment("CERTIFICATE_CHAIN")
        privateKey = environment("PRIVATE_KEY")
        password = environment("PRIVATE_KEY_PASSWORD")
    }

    publishing {
        token = environment("PUBLISH_TOKEN")
    }
}

changelog {
    groups.empty()
    repositoryUrl = properties("pluginRepositoryUrl")
}

qodana {
    resultsPath = provider { file("build/reports/inspections").canonicalPath }
}

kover { reports { total { xml { onCheck = true } } } }

tasks.named<AntlrTask>("generateGrammarSource") {
    maxHeapSize = "64m"

    val tokensDir = layout.buildDirectory.dir("generated-src/antlr/main/com/github/besok/foresterintellijplugin/gramma").get().asFile

    doFirst {
        tokensDir.mkdirs()
    }

    arguments = arguments + listOf(
        "-visitor",
        "-listener",
        "-package", "com.github.besok.foresterintellijplugin.gramma", // <-- Add this back
        "-lib", tokensDir.absolutePath
    )

    setSource(
        source.filter { it.name == "TreeLexer.g4" } + source.filter { it.name == "TreeParser.g4" }
    )
}