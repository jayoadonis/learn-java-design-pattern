plugins {
    application
    `maven-publish`
    `ivy-publish`
    signing
}

project.version = designPattern.app.get().version.toString()
    .takeIf { it.isNotBlank() }?: "0.0.0-SNAPSHOT"
//project.group = "" //REM: Resolve it by the settings.gradle

val PROJECT_NAME_COMPOUND: String = "${project.rootProject.name}-${project.name}"
val PROJECT_HASH_CODE: String = Integer.toHexString( project.hashCode() );
val MODULE_NAME: String = "${
        project.group.toString().replace( Regex( "[\\ */+-]+" ), "_")
            .replace( Regex( "^[._]+|[._]+$" ), "" )
    }.${
        project.rootProject.name.replace( Regex( "[\\ */+-.]+" ), "_")
            .replace( Regex( "^[._]+|[._]+$" ), "" )
    }_app".lowercase();


project.java {
    //REM: TODO-HERE...
//    this.withJavadocJar()
//    this.withSourcesJar()
}

project.application {
    //REM: TODO-HERE...
    this.mainClass.set( "${MODULE_NAME}.MainExe" )
    this.mainModule.set( MODULE_NAME )
}

//REM: Resolve by the corresponding settings.gradle.kts
/*project.sourceSets {
    this.main {
        this.java {
            this.setSrcDirs( listOf( "./src/main/" ) )
            this.setExcludes( listOf( "./src/main/resources/" ) )
        }
        this.resources {
            this.setSrcDirs( listOf( "./src/main/resources/" ) )
            this.setExcludes( listOf( "./src/main/" ) )
        }
    }
    this.test {
        this.java {
            this.setSrcDirs( listOf( "./src/test/" ) )
            this.setExcludes( listOf( "./src/test/resources/" ) )
        }
        this.resources {
            this.setSrcDirs( listOf( "./src/test/resources/" ) )
            this.setExcludes( listOf( "./src/test/") )
        }
    }
}*/

project.publishing {
    //REM: TODO-HERE...
}

project.signing {
    //REM: TODO-HERE...
}

project.dependencies {
    //REM: TODO-HERE...
    this.implementation( project( ":lib" ) );
}

project.tasks.test {
    this.useJUnitPlatform()
}

println( "::: Module name: $MODULE_NAME" )
println(
    String.format( "::: build.gradle.kts@%s[ %s:%s:%s ]",
        PROJECT_HASH_CODE,
        project.group, PROJECT_NAME_COMPOUND, project.version
    )
)