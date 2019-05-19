package top.xuqingquan.mvp.view.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import top.xuqingquan.base.view.fragment.SimpleFragment
import top.xuqingquan.mvp.contract.IPresenter
import javax.inject.Inject

/**
 * Created by 许清泉 on 2019-05-12 02:09
 */
abstract class BaseFragment<P : IPresenter, VDB : ViewDataBinding> : SimpleFragment() {

    @JvmField
    @Inject
    var presenter: P? = null
    protected lateinit var binding: VDB

    final override fun initView(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return binding.root
    }

    override fun initView(view: View) {}

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
        binding.unbind()
    }
}