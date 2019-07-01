package top.xuqingquan.sample

import top.xuqingquan.base.model.repository.BaseRepository
import top.xuqingquan.utils.Timber

/**
 * Created by 许清泉 on 2019-07-01 13:58
 */
class MainModel : MainContract.Model, BaseRepository() {
    override fun test() {
        Timber.d("hahahahahaha")
    }
}