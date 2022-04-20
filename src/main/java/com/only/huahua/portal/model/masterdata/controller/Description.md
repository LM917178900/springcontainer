## 构建多模块项目(同 npi 系统)
1. 项目结构
项目名称nbi-portal-server

包含三个模块：common-platform/portal/data-center
common-platform：
包结构：com.only.platform
系统结构：common
包含包: model/util/vo/...

portal:
包结构：com.only.nbi
系统结构：portal.module.module_name_xxx
包含包：controller/service/model/util/vo/domain/mapper...

data-center
包结构：com.only.nbi
系统结构：auth.center
包含包：controller/service/model/util/vo/domain/mapper...

2.项目阶段：
新建系统：
新建模块：
新建包：
配置数据库：
调试数据库：
跨模块调用：
打包配置：
保存到 github:
发布到远程maven仓库：
项目被其他项目依赖：

