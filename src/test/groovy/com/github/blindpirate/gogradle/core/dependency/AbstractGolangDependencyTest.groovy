package com.github.blindpirate.gogradle.core.dependency

import org.junit.Test
import org.mockito.Mockito

import static com.github.blindpirate.gogradle.core.dependency.AbstractGolangDependency.PropertiesExclusionSpec

class AbstractGolangDependencyTest {
    AbstractGolangDependency dependency = Mockito.mock(AbstractGolangDependency, Mockito.CALLS_REAL_METHODS)

    @Test(expected = UnsupportedOperationException)
    void 'copy() should be forbidden'() {
        dependency.copy()
    }

    @Test(expected = UnsupportedOperationException)
    void 'getGroup() should be forbidden'() {
        dependency.getGroup()
    }

    @Test(expected = UnsupportedOperationException)
    void 'getVersion() should be forbidden'() {
        dependency.getVersion()
    }

    @Test(expected = UnsupportedOperationException)
    void 'contentEquals() should be forbidden'() {
        dependency.contentEquals(null)
    }

    @Test
    void 'multiple PropertiesExclusionSpec should be compared properly'() {
        PropertiesExclusionSpec spec1 = PropertiesExclusionSpec.of([name: 'name'])
        PropertiesExclusionSpec spec2 = PropertiesExclusionSpec.of([name: 'name'] as TreeMap)
        assert spec1.equals(spec2)
        assert spec1.equals(spec1)
        assert !spec1.equals(null)
        assert spec1.hashCode() == spec2.hashCode()
    }
}
