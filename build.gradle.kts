plugins {
    kotlin("jvm") version "1.3.71"
    id("application")
}

val mainClass = "org.gern.background.AppKt"

application {
    mainClassName = mainClass
}

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.5")
}

tasks {
    jar {
        manifest {
            attributes("Main-Class" to mainClass)
        }

        from({
            configurations.compileClasspath.get()
                    .filter { it.name.endsWith("jar") }
                    .map { zipTree(it) }
        })
    }
}
