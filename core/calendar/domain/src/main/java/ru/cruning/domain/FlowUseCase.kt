package ru.cruning.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

//todo вынести в какой то отдельный модуль
abstract class FlowUseCase<Args, Type> {

    private val arguments = MutableSharedFlow<Args>(replay = 1)

    @OptIn(ExperimentalCoroutinesApi::class)
    val observe = arguments.flatMapLatest {
        createFlow(it).flowOn(Dispatchers.IO)
    }

    operator fun invoke(args: Args): FlowUseCase<Args, Type> {
        arguments.tryEmit(args)
        return this
    }

    protected abstract fun createFlow(args: Args): Flow<Type>
}

class TestFlowUsecase @Inject constructor(
) : FlowUseCase<TestFlowUsecase.Args, Int>() {

    data class Args(
        private val qwer: String,
        private val asdf: Boolean,
    )

    override fun createFlow(args: Args) = flow {
        delay(2000)
        emit(31)
    }

}