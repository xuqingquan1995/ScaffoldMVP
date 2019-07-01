package top.xuqingquan.sample

import top.xuqingquan.mvp.presenter.BasePresenter

/**
 * Created by 许清泉 on 2019-07-01 13:46
 */
class MainPresenter(v: MainContract.View) : BasePresenter<MainContract.View, MainContract.Model>(v/*, MainModel()*/) {

    fun test() {
        model?.test()
        view.test()
    }
}