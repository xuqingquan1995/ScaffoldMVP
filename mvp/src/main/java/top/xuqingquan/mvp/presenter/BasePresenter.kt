package top.xuqingquan.mvp.presenter

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.*
import top.xuqingquan.BuildConfig
import top.xuqingquan.base.model.repository.IRepository
import top.xuqingquan.mvp.contract.IPresenter
import top.xuqingquan.mvp.contract.IView
import kotlin.coroutines.CoroutineContext

/**
 * Created by 许清泉 on 2019-05-12 01:17
 */
open class BasePresenter<M : IRepository, V : IView>(protected val model: M? = null, protected val view: V? = null) :
    IPresenter,
    LifecycleObserver {

    private var presenterScope = CoroutineScope(Dispatchers.Main)

    init {
        if (view is LifecycleOwner) {
            view.lifecycle.addObserver(this)
        }
    }

    override fun onDestroy() {
        if (view is LifecycleOwner) {
            view.lifecycle.removeObserver(this)
        }
        model?.onDestroy()
        presenterScope.cancel()
    }

    protected fun <T> launch(
        context: CoroutineContext = Dispatchers.Default,
        tryBlock: suspend CoroutineScope.() -> T,
        catchBlock: suspend CoroutineScope.(Throwable) -> Unit = {},
        finallyBlock: suspend CoroutineScope.() -> Unit = {}
    ): Job {
        return presenterScope.launch(context) {
            try {
                tryBlock()
            } catch (e: Throwable) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace()
                }
                catchBlock(e)
            } finally {
                finallyBlock()
            }
        }
    }

    protected fun <T> launch(
        context: CoroutineContext = Dispatchers.Default,
        tryBlock: suspend CoroutineScope.() -> T
    ): Job {
        return launch(context, tryBlock, {}, {})
    }

}