object ApplicationInfo {
    const val app_package = "com.gluehome.inhome"
    const val app_name = "InHome"
}

object Releases {

    private const val versionMajor = 1
    private const val versionMinor = 0
    private const val versionPatch = 0

    const val version_code = versionMajor * 100000 + versionMinor * 100 + versionPatch
    const val version_name = "$versionMajor.$versionMinor.$versionPatch"
}

object Versions {
    const val kotlin = "1.3.0"

    const val gradle = "3.2.1"

    const val coroutines = "1.0.0"

    const val min_sdk = 18
    const val compile_sdk = 28
    const val target_sdk = 28
    const val build_tools = "28.0.3"
}
