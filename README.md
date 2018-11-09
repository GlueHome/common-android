# common-android [![](https://jitpack.io/v/GlueHome/common-android.svg)](https://jitpack.io/#GlueHome/common-android)
> Common android/poko classes/utils for `clean architecture` with `MVVM` + `Coroutines`/`RxKotlin`

# Installation

main `build.gradle`:
```groovy
allprojects { repositories { maven { url 'https://jitpack.io' } } }
```

main `build.gradle`:

```groovy
dependencies {
  implementation 'com.github.gluehome.common-android:data:${Versions.common}'
  implementation 'com.github.gluehome.common-android:domain:${Versions.common}'
  implementation 'com.github.gluehome.common-android:presentation:${Versions.common}'
}
```

# Presentation

## ViewModel and Observable extensions

```kotlin

import com.gluehome.common.presentation.extensions.*

class HomeFragment : BaseFragment() {

    override fun layoutId() = R.layout.delivery_list_fragment
    
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        viewModel = viewModel(viewModelFactory) {
            observe(deliveriesState, ::onDeliveriesStateChanged)
            observe(setupCompletionState, ::onSetupCompletionChanged)
            observe(failure, ::onFailure)
        }
    }
}
```