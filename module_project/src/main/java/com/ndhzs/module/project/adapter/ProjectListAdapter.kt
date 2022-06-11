package com.ndhzs.module.project.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ndhzs.module.project.bean.ProjectItem
import com.ndhzs.module.project.bean.ProjectList
import com.ndhzs.module.test.R
import com.ndhzs.module.test.databinding.ItemProjectMainBinding

/**
 *
 * @ProjectName:    WanAndroid_Multi
 * @Package:        com.ndhzs.module.project.adapter
 * @ClassName:      ProjectListAdapter
 * @Author:         Yan
 * @CreateDate:     2022年06月06日 00:02:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    项目的Paging Adapter
 */
class ProjectListAdapter (private val onItemClick : (url : String) -> Unit) : PagingDataAdapter<ProjectList,ProjectListAdapter.ViewHolder>(COMPARATOR){


    companion object{
        private val COMPARATOR = object : DiffUtil.ItemCallback<ProjectList>(){

            override fun areItemsTheSame(oldItem: ProjectList, newItem: ProjectList): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ProjectList, newItem: ProjectList): Boolean {
                return oldItem == newItem
            }

        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if(item != null){
            val projectItem = ProjectItem(item.envelopePic,item.title,item.desc)
            holder.bind(projectItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_project_main,parent,false)
        return ViewHolder(view)
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        private val mBinding : ItemProjectMainBinding? = DataBindingUtil.bind(itemView)

        init {
            itemView.setOnClickListener {
                onItemClick(getItem(bindingAdapterPosition)!!.link)
            }
        }
        fun bind(projectItem: ProjectItem){
            if(mBinding != null){
                mBinding.projectItem = projectItem
            }
        }
    }
}