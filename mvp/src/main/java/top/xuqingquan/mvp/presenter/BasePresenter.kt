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
abstract class BasePresenter : IPresenter {

    protected var model: IRepository? = null
        get() {
            if (field == null) {
                field = getM()
            }
            return field!!
        }
        private set

    protected var view: IView? = null
        get() {
            if (field == null) {
                field = getV()
            }
            return field!!
        }
        private set

    protected abstract fun <V : IView> getV(): V?

    protected abstract fun <M : IRepository> getM(): M?

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