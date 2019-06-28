package top.xuqingquan.mvp.view.fragment

import top.xuqingquan.base.view.fragment.SimpleFragment
import top.xuqingquan.mvp.contract.IPresenter
import top.xuqingquan.mvp.contract.IView

/**
 * Created by 许清泉 on 2019-05-12 02:09
 */
abstract class BaseFragment : SimpleFragment(), IView {

    protected var presenter: IPresenter? = null
        get() {
            if (field == null) {
                field = getP()
            }
            return field
        }
        private set

    protected abstract fun <P : IPresenter> getP(): P?

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
    }
}