package top.xuqingquan.sample

import top.xuqingquan.base.model.repository.IRepository
import top.xuqingquan.mvp.contract.IView

/**
 * Created by 许清泉 on 2019-07-01 13:47
 */
interface MainContract {

    interface View : IView {
        fun test()
    }

    interface Model : IRepository {
        fun test()

    }

}