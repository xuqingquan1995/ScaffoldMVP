package top.xuqingquan.sample

import android.os.Bundle
import top.xuqingquan.mvp.view.activity.BaseActivity
import top.xuqingquan.utils.Timber

class MainActivity : BaseActivity<MainPresenter>(), MainContract.View {

    override fun getP() = MainPresenter(this)

    override fun getLayoutId() = R.layout.activity_main

    override fun initData(savedInstanceState: Bundle?) {
        presenter?.test()
    }

    override fun test() {
        Timber.d("hahahahahaha")
    }
}
