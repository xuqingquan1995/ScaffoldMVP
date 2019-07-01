package top.xuqingquan.mvp.presenter

import kotlinx.coroutines.*
import top.xuqingquan.BuildConfig
import top.xuqingquan.base.model.repository.IRepository
import top.xuqingquan.mvp.contract.IPresenter
import top.xuqingquan.mvp.contract.IView
import kotlin.coroutines.CoroutineContext

/**
 * Created by 许清泉 on 2019-05-12 01:17
 */
abstract class BasePresenter<V : IView, M : IRepository> : IPresenter {

    protected val view: V


    protected var model: M? = null

    constructor(view: V) {
        this.view = view
    }

    constructor(view: V, model: M) {
        this.view = view
        this.model = model
    }


    private var presenterScope = CoroutineScope(Dispatchers.Default) + Job()

    override fun onDestroy() {
        model?.onDestroy()
        presenterScope.cancel()
    }

    protected fun <T> launch(
        context: CoroutineContext = Dispatchers.Main,
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
        context: CoroutineContext = Dispatchers.Main,
        tryBlock: suspend CoroutineScope.() -> T
    ): Job {
        return launch(context, tryBlock, {}, {})
    }

}