package com.ndhzs.lib.common.config

/**
 * 路由表命名规则：
 *
 * 1、常量名（全大写）：模块名_功能描述，例：QA_ENTRY
 * 2、二级路由：/模块名/功能描述，例：/qa/entry
 * 3、多级路由：/模块依赖关系倒置/功能描述，例：/map/discover/entry
 */

// 测试模块
const val TEST_ENTRY = "/test/entry"
const val TEST_SHOW = "/test/show/entry"
const val TEST_SERVICE = "/test/service"

// login 模块
const val LOGIN_ENTRY = "/login/entry"

// account 模块
const val ACCOUNT_SERVICE = "/account/service"

// system 模块
const val SYSTEM_ENTRY = "/system/entry"