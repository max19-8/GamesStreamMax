package com.example.gamesstreammax.viewmodel.main

import androidx.lifecycle.ViewModel
import com.example.gamesstreammax.DI
import com.example.gamesstreammax.core_network.api.RawgApi
import com.example.gamesstreammax.di.ScreenScope
import com.example.gamesstreammax.di.ViewModelFactory
import com.example.gamesstreammax.di.ViewModelKey
import com.example.gamesstreammax.interactor.MainScreenInteractor
import com.example.gamesstreammax.interactor.MainScreenInteractorImpl
import com.example.gamesstreammax.util.ResourceProvider
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap

@Component(modules = [MainScreenModule::class])
@ScreenScope
interface MainScreenComponent {
    fun viewModelFactory():ViewModelFactory

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun resources(resourceProvider: ResourceProvider):Builder

        @BindsInstance
        fun api(api: RawgApi):Builder

        fun build():MainScreenComponent
    }

    companion object{
        fun create() = with(DI.appComponent) {
           DaggerMainScreenComponent.builder()
                .resources(resources())
                .api(DI.networkComponent.api())
                .build()
        }
    }
}
@Module
abstract class MainScreenModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainScreenViewModel::class)
    abstract fun mainScreenViewModel(viewModel: MainScreenViewModel):ViewModel

    @Binds
    @ScreenScope
    abstract fun mainScreenInteractor(interactorImpl: MainScreenInteractorImpl):MainScreenInteractor
}