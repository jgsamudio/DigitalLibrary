# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log)

cmake_minimum_required(VERSION 3.4.1)

file(GLOB shared_sources
        ../../djinni/support-lib/jni/*.cpp
        ../../generated/jni/*.cpp
        ../../src/cpp/*.cpp
        )

add_library( # Sets the name of the library.
        shared-library

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        ${shared_sources} )

include_directories(
        ../../djinni/support-lib/
        ../../djinni/support-lib/jni/
        ../../generated/cpp/
        ../../generated/jni/
        ../../src/cpp/
)

target_link_libraries(shared-library)