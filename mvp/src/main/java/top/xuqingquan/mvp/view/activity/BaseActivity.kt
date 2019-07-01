package top.xuqingquan.mvp.view.activity

import top.xuqingquan.base.view.activity.SimpleActivity
import top.xuqingquan.mvp.contract.IPresenter
import top.xuqingquan.mvp.contract.IView

/**
 * Created by 许清泉 on 2019-05-12 02:00
 */
abstract class BaseActivity<P : IPresenter> : SimpleActivity(), IView {

    protected var presenter: P? = null
        get() {
            if (field == null) {
                field = getP()
            }
            return field
        }
        private set

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
    }

    protected abstract fun getP(): P?
}