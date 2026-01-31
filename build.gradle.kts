plugins {
    id("java")
    id("net.fabricmc.fabric-loom-remap") version "1.14-SNAPSHOT"
}

group = "com.laineypowell"
version = "1.2-beta"

loom {
    accessWidenerPath.set(file("src/main/resources/biome-variety.accesswidener"))
}

repositories {
    mavenCentral()
    maven("https://maven.impactdev.net/repository/development/")
    maven("https://maven.minecraftforge.net/")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    minecraft("com.mojang:minecraft:1.20.1")
    mappings(loom.officialMojangMappings())

    modImplementation("net.fabricmc:fabric-loader:0.18.4")
    modImplementation("net.fabricmc.fabric-api:fabric-api:0.92.7+1.20.1")

    modCompileOnly("com.cobblemon:fabric:1.5.2+1.20.1")
    modImplementation("com.github.glitchfiend:TerraBlender-fabric:1.20.1-3.0.1.10")
}

tasks.test {
    useJUnitPlatform()
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks.processResources {
    inputs.property("version", project.version)

    filesMatching("fabric.mod.json") {
        expand(mapOf("version" to inputs.properties["version"]))
    }
}

tasks.remapJar {
    archiveClassifier.set("1.20.1")
}
