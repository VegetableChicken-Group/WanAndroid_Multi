package project.base.base

import groovy.lang.Closure
import org.gradle.api.*
import org.gradle.api.artifacts.ConfigurationContainer
import org.gradle.api.artifacts.dsl.ArtifactHandler
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.artifacts.dsl.DependencyLockingHandler
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.api.component.SoftwareComponentContainer
import org.gradle.api.file.*
import org.gradle.api.initialization.dsl.ScriptHandler
import org.gradle.api.invocation.Gradle
import org.gradle.api.logging.Logger
import org.gradle.api.logging.LoggingManager
import org.gradle.api.model.ObjectFactory
import org.gradle.api.plugins.*
import org.gradle.api.provider.Provider
import org.gradle.api.provider.ProviderFactory
import org.gradle.api.resources.ResourceHandler
import org.gradle.api.tasks.TaskContainer
import org.gradle.api.tasks.WorkResult
import org.gradle.normalization.InputNormalizationHandler
import org.gradle.process.ExecResult
import org.gradle.process.ExecSpec
import org.gradle.process.JavaExecSpec
import java.io.File
import java.net.URI
import java.util.concurrent.Callable

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/5/28 12:19
 */
@Suppress("UsePropertyAccessSyntax")
abstract class BaseProject : Project {
  
  private lateinit var mProject: Project
  
  fun initProject(project: Project) {
    mProject = project
    initProjectInternal()
  }
  
  protected open fun initProjectInternal() {
    initProject()
  }
  
  protected abstract fun initProject()
  
  override fun compareTo(other: Project?): Int = mProject.compareTo(other)
  
  override fun getExtensions(): ExtensionContainer = mProject.extensions
  
  override fun getPlugins(): PluginContainer = mProject.plugins
  
  override fun apply(closure: Closure<*>) = mProject.apply(closure)
  
  override fun apply(action: Action<in ObjectConfigurationAction>) = mProject.apply(action)
  
  override fun apply(options: MutableMap<String, *>) = mProject.apply(options)
  
  override fun getPluginManager(): PluginManager = mProject.pluginManager
  
  override fun getRootProject(): Project = mProject.rootProject
  
  override fun getRootDir(): File = mProject.rootDir
  
  override fun getBuildDir(): File = mProject.buildDir
  
  override fun setBuildDir(path: File) = mProject.setBuildDir(path)
  
  override fun setBuildDir(path: Any) = mProject.setBuildDir(path)
  
  override fun getBuildFile(): File = mProject.buildFile
  
  override fun getParent(): Project? = mProject.parent
  
  override fun getName(): String = mProject.name
  
  override fun getDisplayName(): String = mProject.displayName
  
  override fun getDescription(): String? = mProject.description
  
  override fun setDescription(description: String?) = mProject.setDescription(description)
  
  override fun getGroup(): Any = mProject.group
  
  override fun setGroup(group: Any) = mProject.setGroup(group)
  
  override fun getVersion(): Any = mProject.version
  
  override fun setVersion(version: Any) = mProject.setVersion(version)
  
  override fun getStatus(): Any = mProject.status
  
  override fun setStatus(status: Any) = mProject.setStatus(status)
  
  override fun getChildProjects(): MutableMap<String, Project> = mProject.childProjects
  
  override fun setProperty(name: String, value: Any?) = mProject.setProperty(name, value)
  
  override fun getProject(): Project = mProject.project
  
  override fun getAllprojects(): MutableSet<Project> = mProject.allprojects
  
  override fun getSubprojects(): MutableSet<Project> = mProject.subprojects
  
  override fun task(name: String): Task = mProject.task(name)
  
  override fun task(args: MutableMap<String, *>, name: String): Task = mProject.task(args, name)
  
  override fun task(args: MutableMap<String, *>, name: String, configureClosure: Closure<*>): Task =
    mProject.task(args, name, configureClosure)
  
  override fun task(name: String, configureClosure: Closure<*>): Task =
    mProject.task(name, configureClosure)
  
  override fun task(name: String, configureAction: Action<in Task>): Task =
    mProject.task(name, configureAction)
  
  override fun getPath(): String = mProject.path
  
  override fun getDefaultTasks(): MutableList<String> = mProject.defaultTasks
  
  override fun setDefaultTasks(defaultTasks: MutableList<String>) =
    mProject.setDefaultTasks(defaultTasks)
  
  override fun defaultTasks(vararg defaultTasks: String?) = mProject.defaultTasks(*defaultTasks)
  
  override fun evaluationDependsOn(path: String): Project = mProject.evaluationDependsOn(path)
  
  override fun evaluationDependsOnChildren() = mProject.evaluationDependsOnChildren()
  
  override fun findProject(path: String): Project? = mProject.findProject(path)
  
  override fun project(path: String): Project = mProject.project(path)
  
  override fun project(path: String, configureClosure: Closure<*>): Project =
    mProject.project(path, configureClosure)
  
  override fun project(path: String, configureAction: Action<in Project>): Project =
    mProject.project(path, configureAction)
  
  override fun getAllTasks(recursive: Boolean): MutableMap<Project, MutableSet<Task>> =
    mProject.getAllTasks(recursive)
  
  override fun getTasksByName(name: String, recursive: Boolean): MutableSet<Task> =
    mProject.getTasksByName(name, recursive)
  
  override fun getProjectDir(): File = mProject.projectDir
  
  override fun file(path: Any): File = mProject.file(path)
  
  override fun file(path: Any, validation: PathValidation): File = mProject.file(path, validation)
  
  override fun uri(path: Any): URI = mProject.uri(path)
  
  override fun relativePath(path: Any): String = mProject.relativePath(path)
  
  override fun files(vararg paths: Any?): ConfigurableFileCollection = mProject.files(paths)
  
  override fun files(paths: Any, configureClosure: Closure<*>): ConfigurableFileCollection =
    mProject.files(paths, configureClosure)
  
  override fun files(
    paths: Any,
    configureAction: Action<in ConfigurableFileCollection>
  ): ConfigurableFileCollection = mProject.files(paths, configureAction)
  
  override fun fileTree(baseDir: Any): ConfigurableFileTree = mProject.fileTree(baseDir)
  
  override fun fileTree(baseDir: Any, configureClosure: Closure<*>): ConfigurableFileTree =
    mProject.fileTree(baseDir, configureClosure)
  
  override fun fileTree(
    baseDir: Any,
    configureAction: Action<in ConfigurableFileTree>
  ): ConfigurableFileTree = mProject.fileTree(baseDir, configureAction)
  
  override fun fileTree(args: MutableMap<String, *>): ConfigurableFileTree = mProject.fileTree(args)
  
  override fun zipTree(zipPath: Any): FileTree = mProject.zipTree(zipPath)
  
  override fun tarTree(tarPath: Any): FileTree = mProject.tarTree(tarPath)
  
  override fun <T : Any?> provider(value: Callable<T>): Provider<T> = mProject.provider(value)
  
  override fun getProviders(): ProviderFactory = mProject.providers
  
  override fun getObjects(): ObjectFactory = mProject.objects
  
  override fun getLayout(): ProjectLayout = mProject.layout
  
  override fun mkdir(path: Any): File = mProject.mkdir(path)
  
  override fun delete(vararg paths: Any?): Boolean = mProject.delete(paths)
  
  override fun delete(action: Action<in DeleteSpec>): WorkResult = mProject.delete(action)
  
  override fun javaexec(closure: Closure<*>): ExecResult = mProject.javaexec(closure)
  
  override fun javaexec(action: Action<in JavaExecSpec>): ExecResult = mProject.javaexec(action)
  
  override fun exec(closure: Closure<*>): ExecResult = mProject.exec(closure)
  
  override fun exec(action: Action<in ExecSpec>): ExecResult = mProject.exec(action)
  
  override fun absoluteProjectPath(path: String): String = mProject.absoluteProjectPath(path)
  
  override fun relativeProjectPath(path: String): String = mProject.relativeProjectPath(path)
  
  override fun getAnt(): AntBuilder = mProject.ant
  
  override fun createAntBuilder(): AntBuilder = mProject.createAntBuilder()
  
  override fun ant(configureClosure: Closure<*>): AntBuilder = mProject.ant(configureClosure)
  
  override fun ant(configureAction: Action<in AntBuilder>): AntBuilder =
    mProject.ant(configureAction)
  
  override fun getConfigurations(): ConfigurationContainer = mProject.configurations
  
  override fun configurations(configureClosure: Closure<*>) =
    mProject.configurations(configureClosure)
  
  override fun getArtifacts(): ArtifactHandler = mProject.artifacts
  
  override fun artifacts(configureClosure: Closure<*>) = mProject.artifacts(configureClosure)
  
  override fun artifacts(configureAction: Action<in ArtifactHandler>) =
    mProject.artifacts(configureAction)
  
  @Deprecated("Deprecated in Java")
  override fun getConvention(): Convention = mProject.convention
  
  override fun depthCompare(p0: Project): Int = mProject.depthCompare(p0)
  
  override fun getDepth(): Int = mProject.depth
  
  override fun getTasks(): TaskContainer = mProject.tasks
  
  override fun subprojects(action: Action<in Project>) = mProject.subprojects(action)
  
  override fun subprojects(configureClosure: Closure<*>) = mProject.subprojects(configureClosure)
  
  override fun allprojects(action: Action<in Project>) = mProject.allprojects(action)
  
  override fun allprojects(configureClosure: Closure<*>) = mProject.allprojects(configureClosure)
  
  override fun beforeEvaluate(action: Action<in Project>) = mProject.beforeEvaluate(action)
  
  override fun beforeEvaluate(closure: Closure<*>) = mProject.beforeEvaluate(closure)
  
  override fun afterEvaluate(action: Action<in Project>) = mProject.afterEvaluate(action)
  
  override fun afterEvaluate(closure: Closure<*>) = mProject.afterEvaluate(closure)
  
  override fun hasProperty(propertyName: String): Boolean = mProject.hasProperty(propertyName)
  
  override fun getProperties(): MutableMap<String, *> = mProject.properties
  
  override fun property(propertyName: String): Any? = mProject.property(propertyName)
  
  override fun findProperty(propertyName: String): Any? = mProject.findProperty(propertyName)
  
  override fun getLogger(): Logger = mProject.logger
  
  override fun getGradle(): Gradle = mProject.gradle
  
  override fun getLogging(): LoggingManager = mProject.logging
  
  override fun configure(`object`: Any, configureClosure: Closure<*>): Any =
    mProject.configure(`object`, configureClosure)
  
  override fun configure(
    objects: MutableIterable<*>,
    configureClosure: Closure<*>
  ): MutableIterable<*> = mProject.configure(objects, configureClosure)
  
  override fun <T : Any?> configure(
    objects: MutableIterable<T>,
    configureAction: Action<in T>
  ): MutableIterable<T> = mProject.configure(objects, configureAction)
  
  override fun getRepositories(): RepositoryHandler = mProject.repositories
  
  override fun repositories(configureClosure: Closure<*>) = mProject.repositories(configureClosure)
  
  override fun getDependencies(): DependencyHandler = mProject.dependencies
  
  override fun dependencies(configureClosure: Closure<*>) = mProject.dependencies(configureClosure)
  
  override fun getBuildscript(): ScriptHandler = mProject.buildscript
  
  override fun buildscript(configureClosure: Closure<*>) = mProject.buildscript(configureClosure)
  
  override fun copy(closure: Closure<*>): WorkResult = mProject.copy(closure)
  
  override fun copy(action: Action<in CopySpec>): WorkResult = mProject.copy(action)
  
  override fun copySpec(closure: Closure<*>): CopySpec = mProject.copySpec(closure)
  
  override fun copySpec(action: Action<in CopySpec>): CopySpec = mProject.copySpec(action)
  
  override fun copySpec(): CopySpec = mProject.copySpec()
  
  override fun sync(action: Action<in CopySpec>): WorkResult = mProject.sync(action)
  
  override fun getState(): ProjectState = mProject.state
  
  override fun <T : Any?> container(type: Class<T>): NamedDomainObjectContainer<T> =
    mProject.container(type)
  
  override fun <T : Any?> container(
    type: Class<T>,
    factory: NamedDomainObjectFactory<T>
  ): NamedDomainObjectContainer<T> = mProject.container(type, factory)
  
  override fun <T : Any?> container(
    type: Class<T>,
    factoryClosure: Closure<*>
  ): NamedDomainObjectContainer<T> = mProject.container(type, factoryClosure)
  
  override fun getResources(): ResourceHandler = mProject.resources
  
  override fun getComponents(): SoftwareComponentContainer = mProject.components
  
  override fun getNormalization(): InputNormalizationHandler = mProject.normalization
  
  override fun normalization(configuration: Action<in InputNormalizationHandler>) =
    mProject.normalization(configuration)
  
  override fun dependencyLocking(configuration: Action<in DependencyLockingHandler>) =
    mProject.dependencyLocking(configuration)
  
  override fun getDependencyLocking(): DependencyLockingHandler = mProject.dependencyLocking
}