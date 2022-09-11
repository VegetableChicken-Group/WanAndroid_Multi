package com.ndhzs.lib.config.route

/**
 * 路由表命名规则：
 *
 * 1、常量名（全大写）：模块名_功能描述，例：QA_ENTRY
 * 2、二级路由：/模块名/功能描述，例：/qa/entry
 * 3、多级路由：/模块依赖关系倒置/功能描述，例：/map/discover/entry
 *
 * api 模块的路由表应该放在 api 模块里面，不要放在这里面！！！！！
 */

// 测试模块
const val TEST_ENTRY = "/test/entry"

// login 模块
const val LOGIN_ENTRY = "/login/entry"

// cookie 服务
const val COOKIE_SERVICE = "/cookie/service"