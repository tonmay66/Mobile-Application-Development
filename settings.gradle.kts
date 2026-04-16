pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

<<<<<<< HEAD
rootProject.name = "Contact Book App"
include(":app")
 
=======
<<<<<<< HEAD
rootProject.name = "News Article Reader App"
include(":app")
 
=======
<<<<<<< HEAD
rootProject.name = "Photo Gallery App"
include(":app")
 
=======
<<<<<<< HEAD
rootProject.name = "In-App Browser & Learning Portal App"
include(":app")
 
=======
<<<<<<< HEAD
rootProject.name = "University Event Management App"
include(":app")
=======
<<<<<<< HEAD
rootProject.name = "E-Commerce Product Listing App"
include(":app")
 
=======
rootProject.name = "Student Registration Form App"
include(":app")
>>>>>>> 92d34802c73be524b9ed8c8bcae072d8130a2bab
>>>>>>> 17ad233603d79270c303dbe930f45e713681f1ef
>>>>>>> 531ca80b51bfc395e069b3e39bd4d405347e14d5
>>>>>>> 86b314acfd5e407b5ffbf56abd692d6c9914aff2
>>>>>>> 68fbabf14224cb53a4b70bac2b79aa882e5bf447
>>>>>>> f322a597384b93e401cd209c683458f6de2ff280
