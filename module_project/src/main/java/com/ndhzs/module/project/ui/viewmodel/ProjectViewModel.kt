package com.ndhzs.module.project.ui.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ndhzs.lib.common.extensions.catchApiException
import com.ndhzs.lib.common.extensions.toast
import com.ndhzs.lib.common.ui.mvvm.BaseViewModel
import com.ndhzs.module.project.bean.ProjectList
import com.ndhzs.module.project.bean.ProjectTree
import com.ndhzs.module.project.repository.ProjectRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.Flow

/**
 *
 * @ProjectName:    WanAndroid_Multi
 * @Package:        com.ndhzs.module.project.ui.viewmodel
 * @ClassName:      ProjectViewModel
 * @Author:         Yan
 * @CreateDate:     2022年06月01日 23:10:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    项目模块的ViewModel
 */

@ExperimentalPagingApi
class ProjectViewModel : BaseViewModel(){

    val projectTree = MutableLiveData<List<ProjectTree>>()

    /**
     * 获得项目的列表数据
     */
    fun getProjectList(cid : Int) : Flow<PagingData<ProjectList>> =
        ProjectRepository
            .getProjectList(cid)
            .cachedIn(viewModelScope)


    /**
     * 获得项目分类
     */
    fun getProjectTree() {
        ProjectRepository.getProjectTree()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .catchApiException {
                toast(it.errorMsg)
            }
            .safeSubscribeBy{
                if(it.data.isNotEmpty()){
                    projectTree.value = it.data!!
                }
            }.lifecycle()
    }



}