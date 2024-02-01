plugins {
    `java-library`
    `maven-publish`
    `ivy-publish`
    signing
}

project.version = designPattern.app.get().version.toString()
    .takeIf{ it.isNotBlank() }?: "0.1.0-SNAPSHOT";

val PROJECT_NAME_COMPOUND: String = "${project.rootProject.name}-${project.name}"
val PROJECT_HASH_CODE: String = Integer.toHexString( project.hashCode() );
val MODULE_NAME: String = "${
        project.group.toString().replace( Regex( "[\\ */+-]+"), "_")
            .replace( Regex( "^[._]+|[._]+$" ), "" )
    }.${
        project.rootProject.name.replace( Regex( "[\\ */+-]+" ), "_" )
            .replace( Regex( "^[._]+|[._]+$" ), "" )
    }.lib".lowercase();

println( "::: Module name: $MODULE_NAME" );
println( String.format(
    "::: build.gradle.kts@%s[ %s:%s:%s ]",
    PROJECT_HASH_CODE,
    project.group, PROJECT_NAME_COMPOUND, project.version
))

project.sourceSets {
    this.main {
        this.java {
            this.setSrcDirs( listOf( "src/main/" ) );
            this.setExcludes( listOf( "src/main/resources/" ) );
        }
        this.resources {
            this.setSrcDirs( listOf( "src/main/resources/" ) );
            this.setExcludes( listOf( "src/main/" ) );
        }
    }
    this.test {
        this.java {
            this.setSrcDirs( listOf( "src/test/" ) );
            this.setExcludes( listOf( "src/test/resources/" ) );
        }
        this.resources {
            this.setSrcDirs( listOf( "src/test/resources/" ) );
            this.setExcludes( listOf( "src/test/" ) );
        }
    }
}
project.java {
//    this.modularity.inferModulePath.set( true );
}
project.dependencies {

}
project.publishing {

}
project.signing {

}