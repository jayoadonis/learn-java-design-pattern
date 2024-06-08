@file:Suppress( "UNUSED_VARIABLE", "UNUSED", "UnstableApiUsage" )

import java.text.SimpleDateFormat
import java.util.Date

pluginManagement {
    println(
        String.format(
            "::: settings.gradle.kts@%s[ pluginManagement ]",
            Integer.toHexString( settings.hashCode() )
        )
    )
    this.repositories {
        this.mavenCentral()
        this.gradlePluginPortal()
    }
    this.plugins {
        //REM: TODO-HERE...
    }
}

settings.dependencyResolutionManagement {
    println(
        String.format(
            "::: settings.gradle.kts@%s[ dependencyResolutionManagement ]",
            Integer.toHexString( settings.hashCode() )
        )
    )
    this.repositories {
        this.mavenCentral()
        this.gradlePluginPortal()
    }
    this.versionCatalogs {
        //REM: TODO-HERE...
        this.register( "designPattern" ) {
            this.from( files( "./gradle/designPattern.versions.toml" ) )
        }
        this.register( "testAPI" ) {
            this.from( files( "./gradle/testAPI.versions.toml" ) );
        }
    }
}

settings.rootProject.name = "learn-java-design-pattern"
println(String.format("Gradle Version: %s, %s", settings.gradle.gradleVersion,
    settings.rootProject.name ));

settings.include( ":app" )
settings.include( ":lib" )

settings.gradle.beforeSettings {
    println(
        String.format(
            "::: settings.gradle.kts@%s[ beforeSettings ]",
            SETTINGS_HASH_CODE
        )
    )
}

settings.gradle.settingsEvaluated {
    println(
        String.format(
            "::: settings.gradle.kts@%s[ settingsEvaluated ]",
            SETTINGS_HASH_CODE
        )
    );
    //REM: TODO-HERE...
    JAVA_PLUGIN_IDS = setOf (
        "java",
        "java-application",
        "application",
        "java-library",
        "war",
        "kotlin",
        "groove" ).map { it.lowercase() }.toSet();
    CPP_PLUGIN_IDS = setOf(
        "cpp",
        "cpp-application",
        "cpp-library" ).map { it.lowercase() }.toSet();
    RUST_PLUGIN_IDS = setOf(
        "rust",
        "rust-application",
        "rust-library" ).map { it.lowercase() }.toSet()
}

settings.gradle.beforeProject {

    this.allprojects {
        //REM: TODO-HERE...
        this.project.group = PROJECT_GROUP_NAME;
        projectName = this.project.name;
        projectNameCompound = this.project.rootProject.run {
            if( name != projectName )
                "$name-$projectName";
            else
                name;
        }
        projectHashCode = Integer.toHexString( this.project.hashCode() )
        println(
            String.format(
                "::: settings.gradle.kts@%s[ beforeProject, build.gradle.kts@%s[ %s ] ]",
                SETTINGS_HASH_CODE, projectHashCode, "${project.group}:${projectNameCompound}:${project.version}"
            )
        );
    }
}

settings.gradle.projectsEvaluated {
    //REM: TODO-HERE...
    this.allprojects {
        //REM: TODO-HERE...
        projectName = this.project.name;
        projectNameCompound = this.project.rootProject.run {
            if( this.project.name != projectName )
                "${this.project.name}-$projectName"
            else
                this.project.name
        }
        projectHashCode = Integer.toHexString( this.project.hashCode() )

        println(
            String.format(
                "::: settings.gradle.kts@%s[ projectsEvaluated, build.gradle.kts@%s[ %s, %s] ]",
                SETTINGS_HASH_CODE, projectHashCode, "${project.group}:${projectNameCompound}:${project.version}",
                this.project.plugins.getPluginz()
            )
        )

        this.group = this.group.toString()
            .takeIf { it.isNotBlank() }
            ?: PROJECT_GROUP_NAME

        if(
            JAVA_PLUGIN_IDS.any {
                javaPluginId -> this.project.plugins
                .hasPlugin( javaPluginId )
            }
        ) {
            val SOURCE_SETS_EXTENSION: SourceSetContainer = this.project.extensions
                .getByType<SourceSetContainer>();
            val PUBLISHING_EXTENSION: PublishingExtension = this.project.extensions
                .getByType<PublishingExtension>();
            val VERSION_CATALOGS: VersionCatalogsExtension = this.project.extensions
                .getByType<VersionCatalogsExtension>();

            //REM: TODO-HERE...
            SOURCE_SETS_EXTENSION.apply {
                this.named( "main" ) {
                    this.java {
                        this.setSrcDirs( listOf( "src/main/" ) )
                        this.setExcludes( listOf( "src/main/resources/" ) )
                    }
                    this.resources {
                        this.setSrcDirs( listOf( "src/main/resources/" ) )
                        this.setExcludes( listOf( "src/main/" ) )
                    }
                }
                this.named( "test" ) {
                    this.java {
                        this.setSrcDirs( listOf( "src/test/" ) )
                        this.setExcludes( listOf( "src/test/resources/" ) )
                    }
                    this.resources {
                        this.setSrcDirs( listOf( "src/test/resources/" ) )
                        this.setExcludes( listOf( "src/test/" ) )
                    }
                }
            }

            this.project.dependencies {
                val TEST_API = VERSION_CATALOGS.named( "testAPI" );
                val JUNIT_JUPITER_API = TEST_API.findLibrary( "junit-jupiter-api" ).get();
                val JUNIT_JUPITER_ENGINE = TEST_API.findLibrary( "junit-jupiter-engine" ).get();
                this.add( "testImplementation", JUNIT_JUPITER_API );
                this.add( "testRuntimeOnly", JUNIT_JUPITER_ENGINE );
            }

            this.project.tasks.register<Jar>( "customTask_sourceJar" ) {
                this.group = "build"
                this.from( SOURCE_SETS_EXTENSION.get( "main" ).allSource );
                this.archiveClassifier.set( "sources" );
            }

            this.project.tasks.register<Jar>( "customTask_testSourceJar" ) {
                this.group = "build"
                this.from( SOURCE_SETS_EXTENSION.get( "main" ).allSource );
                this.from( SOURCE_SETS_EXTENSION.get( "test" ).allSource );
                this.archiveClassifier.set( "test-sources" );
            }

            this.project.tasks.register<Javadoc>( "customTask_mainJavaDoc" ) {
                this.group = "build"
                this.title = "Main JavaDoc"
                this.source( SOURCE_SETS_EXTENSION.get( "main" ).allJava );
                this.classpath = project.configurations.get( "compileClasspath" );
                this.setDestinationDir( file( "${project.layout.buildDirectory.get().dir( "docs/javadoc/main" )}") );
            }

//            this.project.tasks.register<Javadoc>( "customTask_testJavaDoc" ) {
//                this.group = "build"
//                this.title = "Test JavaDoc";
//                this.source( SOURCE_SETS_EXTENSION.get( "test" ).allJava );
//                this.classpath = project.configurations.get( "testCompileClasspath" );
//                this.setDestinationDir( file( "${project.layout.buildDirectory.get().dir( "docs/javadoc/test" )}") );
//            }

            this.project.tasks.register<Jar>( "customTask_JavaDocJar" ) {
                this.group = "build"
                this.dependsOn( "customTask_mainJavaDoc"/*, "customTask_testJavaDoc"*/ );
                this.archiveClassifier.set( "documentation" )
                this.from( project.layout.buildDirectory.get().dir( "docs/javadoc/" ) )
            }

            if( this.project.plugins.hasPlugin( IvyPublishPlugin::class.java ) ) {
                PUBLISHING_EXTENSION.apply {
                    this.publications {
                        //REM: TODO-HERE...
                        this.register<IvyPublication>( "_IVY_PUB__${projectNameCompound.uppercase()}_" ) {
                            this.from( components[ "java" ] )
//                            this.module = ROOT_PROJECT_NAME
                            this.module = projectNameCompound
//                            this.artifact( tasks.getByName( "jar" ) ) {
//                                this.classifier = project.name
//                            }
                        }
                    }
                    this.repositories {
                        this.ivy {
                            this.name = "_IVY_REPO__${projectNameCompound.uppercase()}_"
                            this.url = uri( project.layout.projectDirectory.dir( "repo/ivy/" ) )
                        }
                        this.ivy {
                            this.name = "_IVY_REPO__ROOT_PROJ_REPO_"
                            this.url = uri( project.rootProject.layout.projectDirectory.dir( "repo/ivy/$projectNameCompound" ) )
                        }
                    }
                }
                this.project.tasks.register( "_DELETE_IVY_REPO__${projectNameCompound}_" ) {
                    this.group = "publishing"
                    this.doLast {
                        gradle.delete( project.layout.projectDirectory.dir( "repo/ivy/" ).toString() )
                    }
                }
            }

            if( this.project.plugins.hasPlugin( MavenPublishPlugin::class.java ) ) {
                PUBLISHING_EXTENSION.apply {
                    this.publications {
                        if( !this.asMap.contains( "_MVN_PUB__${projectNameCompound.uppercase()}_" ) ) {
                            this.register<MavenPublication>("_MVN_PUB__${projectNameCompound.uppercase()}_") {
                                this.from(components["java"])
//                            this.artifactId = ROOT_PROJECT_NAME
                                this.artifactId = projectNameCompound
                                /*                            this.artifact( tasks.getByName( "jar" ) ) {
                                this.classifier = project.name
                            }*/
                            }
                            //REM: TODO-HERE...
                        }
                    }
                    this.repositories {
                        this.maven {
                            this.name = "_MVN_REPO__${projectNameCompound.uppercase()}_"
                            this.url = uri( project.layout.projectDirectory.dir( "repo/mvn/" ) )
                        }
                        this.maven {
                            this.name = "_MVN_REPO__ROOT_PROJ_REPO_"
                            this.url = uri( project.rootProject.layout.projectDirectory.dir( "repo/mvn/$projectNameCompound" ) )
                        }
                    }
                }
                this.project.tasks.register( "_DELETE_MVN_REPO__${projectNameCompound}_" ) {
                    this.group = "publishing"
                    this.doLast {
                        gradle.delete( project.layout.projectDirectory.dir( "repo/mvn/" ).toString() )
                    }
                }
            }
            if( this.project.plugins.hasPlugin( SigningPlugin::class.java ) ) {
                //REM: TODO-HERE...
            }

//            this.tasks.withType<Javadoc>() {
//                this.dependsOn( tasks.withType<Jar>() )
//            }

            this.tasks.withType<Test>() {
                this.useJUnitPlatform();
            }
            this.tasks.withType<Zip>() {
                this.archiveBaseName.set(projectNameCompound);
            }
            this.tasks.withType<Tar>() {
                this.archiveBaseName.set(projectNameCompound);
            }
            this.tasks.named( "jar" ).configure {
                this.dependsOn( "customTask_sourceJar", "customTask_testSourceJar", "customTask_JavaDocJar" );
            }
            this.tasks.withType<Jar>() {
//                this.from( SOURCE_SETS_EXTENSION.named( "main" ).get().output )
//                this.archiveBaseName.set( this.project.rootProject.name )
                this.archiveBaseName.set( projectNameCompound )
//                this.archiveClassifier.set( this.project.name )
                this.doFirst {
                    manifest.attributes["whoami"] = "jayo.arb"
                    manifest {
                        attributes["Name"] = attributes["Name"]
                            .takeIf { it.toString().isNotBlank() }
                            ?: archiveFileName
                        attributes["Built-By"] = attributes["Built-By"]
                            .takeIf { it.toString().isNotBlank() }
                            ?: (project.providers.gradleProperty("project.group.name")
                                .orNull?.takeIf { it.isNotBlank() } ?: "jayo.arb.learn-j")
                                .split(".").reversed().joinToString(" ")
                        attributes["Build-Time"] = attributes["Build-Time"]
                            .takeIf { it.toString().isNotBlank() }
                            ?: SimpleDateFormat("yyyy-MM-dd h:mm:ss-a").format(Date())
                        attributes["Description"] = attributes["Description"]
                            .takeIf { it.toString().isNotBlank() }
                            ?: "n/a"
                        attributes["Implementation-Vendor"] = attributes["Implementation-Vendor"]
                            .takeIf { it.toString().isNotBlank() }
                            ?: project.providers.gradleProperty("project.group.name")
                                .orNull?.takeIf { it.isNotBlank() } ?: "jayo.arb.learn-j"
                        attributes["Implementation-Title"] = attributes["Implementation-Title"]
                            .takeIf { it.toString().isNotBlank() }
                            ?: archiveBaseName
                        attributes["Implementation-Version"] = attributes["Implementation-Version"]
                            .takeIf { it.toString().isNotBlank() }
                            ?: project.version
                        attributes["Specification-Vendor"] = attributes["Specification-Vendor"]
                            .takeIf { it.toString().isNotBlank() }
                            ?: "${
                                project.providers.gradleProperty("project.group.name")
                                    .orNull.takeIf { !it.isNullOrBlank() } ?: "jayo.arb.learn-j"
                            }, learn more, do more."
                        attributes["Specification-Title"] = attributes["Specification-Title"]
                            .takeIf { it.toString().isNotBlank() }
                            ?: "${archiveBaseName.get()}, practice makes perfect"
                        attributes["Specification-Version"] = attributes["Specification-Version"]
                            .takeIf { it.toString().isNotBlank() }
                            ?: "${project.version}, unending versions of triumph"
                    }
                }
            }

        }
    }
}

final val SETTINGS_HASH_CODE: String = Integer.toHexString( settings.hashCode() );
final val PROJECT_GROUP_NAME: String = settings.providers.gradleProperty( "project.group.name" )
    .orNull.takeIf { !it.isNullOrBlank() }
//    ?.replace( Regex( "[\\ */+._]+" ), "-" )
//    ?.replace( Regex( "^[.-]+|[.-]+$" ), "" )
//    ?.lowercase()
    ?: "jayo.arb.learn-j";
final val ROOT_PROJECT_NAME: String = settings.rootProject.name;

final lateinit var JAVA_PLUGIN_IDS: Set<String>;
final lateinit var CPP_PLUGIN_IDS: Set<String>;
final lateinit var RUST_PLUGIN_IDS: Set<String>;
lateinit var projectName: String;
lateinit var projectNameCompound: String;
lateinit var projectHashCode: String;

fun PluginContainer.getPluginz(): Set<String> {
    return this.mapNotNull {
        it::class.simpleName?.substringBefore( "$")
            ?.takeIf { str -> str.isNotEmpty() }
    }.toSet();
}

fun Gradle.delete( path: String ) : Unit {
    val FILE_TO_DELETE: File = file( path )
    try {
        if (FILE_TO_DELETE.exists()) {
            FILE_TO_DELETE.deleteRecursively()
            println( "File or directory deleted: '$FILE_TO_DELETE'" )
        } else
            System.err.println( "File or directory does not exist: '$FILE_TO_DELETE'" )
    } catch( ex: Exception ) {
        System.err.println( "Error cannot be deleted file or directory: '$FILE_TO_DELETE'\n\t$ex" )
    }
}

println(
    String.format(
        "::: settings.gradle.kts@%s[ rootProject ]",
        SETTINGS_HASH_CODE
    )
)
