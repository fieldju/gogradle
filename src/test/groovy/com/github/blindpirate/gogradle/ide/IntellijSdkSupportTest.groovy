package com.github.blindpirate.gogradle.ide

import com.github.blindpirate.gogradle.GogradleRunner
import com.github.blindpirate.gogradle.crossplatform.Os
import com.github.blindpirate.gogradle.support.WithResource
import com.github.blindpirate.gogradle.util.IOUtils
import com.github.blindpirate.gogradle.util.ReflectionUtils
import com.github.blindpirate.gogradle.util.StringUtils
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import java.nio.file.Paths

@RunWith(GogradleRunner)
@WithResource('')
class IntellijSdkSupportTest {
    File resource

    @Before
    void setUp() {
        System.setProperty("user.home", resource.absolutePath)
    }

    String xmlWithGoSdk = '''
<application>
  <component name="ProjectJdkTable">
    <jdk version="2">
      <name value="IntelliJ IDEA IU-162.1812.17" />
      <type value="IDEA JDK" />
      <version value="java version &quot;1.8.0_74&quot;" />
      <homePath value="$APPLICATION_HOME_DIR$" />
      <roots>
        <annotationsPath>
          <root type="composite">
            <root type="simple" url="jar://$APPLICATION_HOME_DIR$/lib/jdkAnnotations.jar!/" />
          </root>
        </annotationsPath>
        <classPath>
          <root type="composite">
            <root type="simple" url="jar://$APPLICATION_HOME_DIR$/plugins/Spring/lib/spring-web.jar!/" />
          </root>
        </classPath>
        <javadocPath>
          <root type="composite" />
        </javadocPath>
        <sourcePath>
          <root type="composite">
            <root type="simple" url="jar://$APPLICATION_HOME_DIR$/lib/src/trove4j_src.jar!/" />
          </root>
        </sourcePath>
      </roots>
      <additional sdk="1.8 ">
        <option name="mySandboxHome" value="$USER_HOME$/Library/Caches/IntelliJIdea2016.2/plugins-sandbox" />
      </additional>
    </jdk>
    <jdk version="2">
      <name value="Go 1.7.1" />
      <type value="Go SDK" />
      <version value="1.7.1" />
      <homePath value="/usr/local/Cellar/go/1.7.1/libexec" />
      <roots>
        <annotationsPath>
          <root type="composite" />
        </annotationsPath>
        <classPath>
          <root type="composite">
            <root type="simple" url="file:///usr/local/Cellar/go/1.7.1/libexec/src" />
          </root>
        </classPath>
        <javadocPath>
          <root type="composite" />
        </javadocPath>
        <sourcePath>
          <root type="composite">
            <root type="simple" url="file:///usr/local/Cellar/go/1.7.1/libexec/src" />
          </root>
        </sourcePath>
      </roots>
      <additional />
    </jdk>
  </component>
</application>
'''
    String xmlWithoutGoSdk = '''
<application>
  <component name="ProjectJdkTable"> 
  </component>
</application>
'''

    @Test
    void 'sdk should be added if not exist'() {
        writeInto('IntelliJIdea', '2016.1', xmlWithoutGoSdk)
        writeInto('IntelliJIdea', '2016.3', xmlWithoutGoSdk)
        writeInto('IdeaIC', '2016.1', xmlWithoutGoSdk)
        writeInto('IdeaIC', '2016.3', xmlWithoutGoSdk)
        IntellijSdkSupport.ensureSpecificSdkExist('1.7.1', resource.toPath())

        ['Go 1.7.1', 'Go SDK', "url=\"file://${resource.toPath().resolve('src')}\""].each {
            assert getFileContent('IntelliJIdea', '2016.1').contains(it)
            assert getFileContent('IntelliJIdea', '2016.3').contains(it)
            assert getFileContent('IdeaIC', '2016.1').contains(it)
            assert getFileContent('IdeaIC', '2016.3').contains(it)
        }
    }

    @Test
    void 'file should not be changed if specific sdk exists'() {

        writeInto('IntelliJIdea', '2016.1', xmlWithGoSdk)
        writeInto('IntelliJIdea', '2016.3', xmlWithGoSdk)
        writeInto('IdeaIC', '2016.1', xmlWithGoSdk)
        writeInto('IdeaIC', '2016.3', xmlWithGoSdk)

        List<String> fileContents = loadFileContents()
        IntellijSdkSupport.ensureSpecificSdkExist('1.7.1', resource.toPath())
        assert loadFileContents() == fileContents
    }

    List loadFileContents() {
        return [getFileContent("IntelliJIdea", '2016.1'),
                getFileContent("IntelliJIdea", '2016.3'),
                getFileContent("IdeaIC", '2016.1'),
                getFileContent("IdeaIC", '2016.3')]
    }

    void writeInto(String product, String version, String xml) {
        IOUtils.write(new File(getLocation(product, version)), xml)
    }

    String getFileContent(String product, String version) {
        return IOUtils.toString(new File(getLocation(product, version)))
    }

    String getLocation(String product, String version) {
        String location
        if (Os.getHostOs() == Os.DARWIN) {
            location = ReflectionUtils.getStaticField(IntellijSdkSupport, "SETTING_LOCATION_ON_MAC")
        } else {
            location = ReflectionUtils.getStaticField(IntellijSdkSupport, "SETTING_LOCATION_ON_OTHER_OS")
        }
        return StringUtils.render(location, [userHome: System.getProperty('user.home'),
                                             product : product,
                                             version : version])
    }
}