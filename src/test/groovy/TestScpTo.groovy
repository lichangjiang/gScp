import org.gradle.testkit.runner.GradleRunner

import static org.gradle.testkit.runner.TaskOutcome.SUCCESS

/**
 * Created by lichangjiang on 18-2-11.
 */
class TestScpTo extends GroovyTestCase{

    def projectDir = new File(System.getProperty("user.dir") + "/testProject/simpleTestProject")
    def pluginClasspathResource = getClass().classLoader.findResource("plugin-classpath.txt")
    def pluginClasspath = pluginClasspathResource.readLines().collect { new File(it) }

    void setUp() {
        def buildDir = new File(projectDir, "build")
        if(buildDir.exists()) buildDir.deleteDir()
        buildDir.delete()
    }

    void tearDown() {
        setUp()
    }

    void testScptoTask() {
        def result = GradleRunner.create()
                .withProjectDir(projectDir)
                .withPluginClasspath(pluginClasspath)
                .withArguments("scpToTask")
                .build()

        assertEquals(SUCCESS, result.task(":scpToTask").getOutcome())
    }
}
