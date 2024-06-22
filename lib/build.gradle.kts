
public sealed class TaskProperties {
    public abstract val taskName: String?
    public abstract val targetClassName: String
    public companion object {
        public fun create(
            tasksName: String?,
            targetClassName: String
        ): TaskProperties {
            require( targetClassName.isNotBlank() ) {
                "::: targetClassName must not be 'null' nor 'empty'!"
            }
            return TaskPropertiesImpl( tasksName, targetClassName );
        }
    }
    private data class TaskPropertiesImpl(
        public override val taskName: String?,
        public override val targetClassName: String
    ): TaskProperties();
}

plugins {
    `java-library`
    `maven-publish`
    `ivy-publish`
    signing
}

project.java {
    this.modularity.inferModulePath.set( true );
    this.sourceCompatibility = JavaVersion.VERSION_1_9;
    this.targetCompatibility = JavaVersion.VERSION_11;
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
    }.${project.name}".lowercase();

val TASK_PROPERTIES_TESTS: Set<TaskProperties> = setOf(
    TaskProperties.create( null, "$MODULE_NAME.behavioral.command.test.TestCommand000" ),
    TaskProperties.create( null, "$MODULE_NAME.behavioral.visitor.test.TestVisitor000" ),
    TaskProperties.create( null, "$MODULE_NAME.structural.decorator.test.TestDecorator" )
)



println( String.format(
    "::: build.gradle.kts@%s[ %s:%s:%s ]",
    PROJECT_HASH_CODE,
    project.group, PROJECT_NAME_COMPOUND, project.version
))
println( "::: Module name: $MODULE_NAME" );
println( "::: Project sourceCompatibility: ${project.java.sourceCompatibility.majorVersion}" );
println( "::: Project targetCompatibility: ${project.java.targetCompatibility.majorVersion}" );

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
project.dependencies {

}
project.publishing {

}
project.signing {

}
fun Test.defaultConfigTask( taskGroupName: String = "verification" ) {
    this.group = taskGroupName;
    this.useJUnitPlatform();
}
TASK_PROPERTIES_TESTS.forEach {taskPropertiesTest ->
    project.tasks.register<Test>(
        "${taskPropertiesTest.taskName?: taskPropertiesTest.targetClassName}@${project.name}"
    ) {
        this.defaultConfigTask();
        this.filter.includeTestsMatching( taskPropertiesTest.targetClassName );
    }
};