package top.xuqingquan.mvp.view.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import top.xuqingquan.base.view.activity.SimpleActivity
import top.xuqingquan.mvp.contract.IPresenter
import javax.inject.Inject

/**
 * Created by 许清泉 on 2019-05-12 02:00
 */
abstract class BaseActivity<P : IPresenter, VDB : ViewDataBinding> : SimpleActivity() {

    @JvmField
    @Inject
    var presenter: P? = null
    protected lateinit var binding: VDB

    public override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
        binding.unbind()
    }

    override fun initView(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        setContentView(binding.root)
        initData(savedInstanceState)
    }

}