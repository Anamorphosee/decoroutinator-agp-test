# Unit Test ClassNotFoundException with AGP 8.9.1

## Environment

- Android Gradle Plugin: 8.9.1
- Kotlin: 2.1.20
- Decoroutinator: 2.5.8
- Gradle: 8.11.1
- JDK: 17

## The Problem

All unit tests fail with `ClassNotFoundException` for app classes. Interestingly, this happens even for classes that don't use suspend functions and aren't transformed by Decoroutinator.

To reproduce:
```bash
./gradlew :app:testDebugUnitTest
```

Result:
```
AnotherClassTest > testGetValue FAILED
    java.lang.NoClassDefFoundError: com/example/decoroutinatortest/AnotherClass

MyClassTest > testDoSomething FAILED
    java.lang.NoClassDefFoundError: com/example/decoroutinatortest/MyClass
```

## What We Found

The transformation itself works fine - `MyClass` gets the `@DecoroutinatorTransformed` annotation as expected. And `AnotherClass` correctly doesn't get transformed since it has no suspend functions.

All the compiled classes end up in:
```
app/build/intermediates/compile_app_classes_jar/debug/bundleDebugClassesToCompileJar/classes.jar
```

But when you run tests with `--debug` and check the classpath, that jar isn't there. The classpath has:
- Decoroutinator runtime libs (provider, common, generator, etc.)
- All external dependencies
- Test resources

Just not the app classes.

Looking at the plugin source (`common-gradle-plugin.kt`), it looks like it should be adding classes to `.*UnitTestRuntimeClasspath`. Maybe AGP 8.9.1 changed something about how those configurations work?

## Things We Tried

- `useTransformedClassesForCompilation = true`
- Explicitly setting `androidRuntimeDependencyConfigurations` and `jvmRuntimeDependencyConfigurations`
- Clean builds
- Different Decoroutinator config options

None of them helped.

## Questions

Has this been tested with AGP 8.9.x? We're wondering if there's some config we're missing, or if this is a compatibility issue with the newer AGP version.

Happy to provide more diagnostics or try different setups if that helps track this down.
