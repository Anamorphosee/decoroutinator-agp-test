# Decoroutinator ProGuard rules
-keep class dev.reformator.bytecodeprocessor.intrinsics.** { *; }
-keep class dev.reformator.stacktracedecoroutinator.** { *; }
-keep class kotlin.coroutines.jvm.internal.BaseContinuationImpl { *; }
-keepclassmembers class * extends kotlin.coroutines.jvm.internal.BaseContinuationImpl {
    *;
}
-dontwarn dev.reformator.bytecodeprocessor.**
-dontwarn dev.reformator.stacktracedecoroutinator.**

