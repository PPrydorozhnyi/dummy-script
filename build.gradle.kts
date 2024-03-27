plugins {
    id("com.diffplug.spotless") version "6.25.0"
}


spotless {
    java {
        target("src/*/java/**/*.java")

        palantirJavaFormat()
        removeUnusedImports()
        importOrder()
        formatAnnotations()

        custom("Refuse wildcard imports") {
            // Spotless can't resolve wildcard imports itself.
            // This will require the developers themselves to adhere to best practices.
            val importPattern = "import .*\\*;".toRegex()

            if (importPattern.containsMatchIn(it)) {
                throw AssertionError("Do not use wildcard imports. 'spotlessApply' cannot resolve this issue.")
            } else {
                it
            }
        }
    }
    kotlinGradle {
        target("*.gradle.kts")
        ktlint()
    }
}