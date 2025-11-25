# Decoroutinator Unit Test Classpath Issue

This is a minimal reproducible example demonstrating an issue with Decoroutinator 2.5.8 where transformed classes are not available on the unit test classpath.

## Environment

- Android Gradle Plugin: 8.9.1
- Kotlin: 2.1.20
- Decoroutinator: 2.5.8
- Gradle: 8.11.1 (via wrapper)

## The Problem

With Decoroutinator enabled, all unit tests fail with `ClassNotFoundException`. The app classes are compiled and transformed fine, but they don't end up on the test classpath.

## Reproduction Steps

1. Build the project:
   ```bash
   ./gradlew clean build
   ```

2. Run unit tests:
   ```bash
   ./gradlew :app:testDebugUnitTest
   ```

## Expected Behavior

All tests should pass. The transformed classes should be available on the unit test classpath.

## Actual Behavior

ALL tests fail with `ClassNotFoundException`, including tests for classes that don't use suspend functions:

```
AnotherClassTest > testGetValue FAILED
    java.lang.NoClassDefFoundError: com/example/decoroutinatortest/AnotherClass
        Caused by: java.lang.ClassNotFoundException: com.example.decoroutinatortest.AnotherClass

MyClassTest > testDoSomething FAILED
    java.lang.NoClassDefFoundError: com/example/decoroutinatortest/MyClass
        Caused by: java.lang.ClassNotFoundException: com.example.decoroutinatortest.MyClass
```

**Interesting**: Even `AnotherClass` (no suspend functions, not transformed) fails the same way. So it's not just about the transformed classes - something's wrong with how the test classpath gets set up.

## What We Found

1. Transformation works - `MyClass` has the `@DecoroutinatorTransformed` annotation

2. All app classes are in:
   ```
   app/build/intermediates/compile_app_classes_jar/debug/bundleDebugClassesToCompileJar/classes.jar
   ```

3. But that jar isn't on the test classpath (checked with `--debug`)

4. The plugin code looks like it should add classes to `.*UnitTestRuntimeClasspath`, but with AGP 8.9.1 they're not showing up

## Tried But Didn't Work

- `useTransformedClassesForCompilation = true`
- Manually adding runtime dependencies
- Clean builds

## Files to Check

- `app/build.gradle.kts` - Decoroutinator configuration
- `app/src/main/java/com/example/decoroutinatortest/MyClass.kt` - Class with suspend function
- `app/src/test/java/com/example/decoroutinatortest/MyClassTest.kt` - Failing test

