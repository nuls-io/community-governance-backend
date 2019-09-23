# Community Governance Backend
接口列表
----
### 0.1 查询系统基本信息
#### Cmd: /cg/info
_**详细描述: 查询系统基本信息**_
#### HttpMethod: GET

#### 参数列表
无参数

#### 返回值
| 字段名                   |  字段类型  | 参数描述 |
| --------------------- |:------:| ---- |
| &nbsp;contractAddress | string | 合约地址 |
#### Example request data: 

_**request path:**_
略

_**request form data:**_
无

#### Example response data: 
略

### 1.1 查询代理人的委托地址列表
#### Cmd: /cg/mandator/list
_**详细描述: 查询代理人的委托地址列表**_
#### HttpMethod: POST

#### Form json data: 
```json
{
  "page" : 1,
  "pageSize" : 11,
  "agentAddress" : null
}
```

#### 参数列表
| 参数名                                                                |        参数类型         | 参数描述           | 是否必填 |
| ------------------------------------------------------------------ |:-------------------:| -------------- |:----:|
| &nbsp;查询代理人的委托地址列表                                                 | mandatorspagesearch | 查询代理人的委托地址列表表单 |  是   |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;page         |       integer       | 当前页            |  是   |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;pageSize     |       integer       | 分页大小           |  是   |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;agentAddress |       string        | 代理人地址          |  是   |

#### 返回值
| 字段名                   |  字段类型  | 参数描述  |
| --------------------- |:------:| ----- |
| &nbsp;mandatorAddress | string | 委托人地址 |
| &nbsp;votesNumber     | string | 票数    |
| &nbsp;createTime      |  long  | 委托时间  |
#### Example request data: 

_**request path:**_
略

_**request form data:**_
无

#### Example response data: 
略

### 1.2 查询代理人地址
#### Cmd: /cg/agent/{mandatorAddress}
_**详细描述: 查询代理人地址**_
#### HttpMethod: GET

#### 参数列表
| 参数名                   |  参数类型  | 参数描述  | 是否必填 |
| --------------------- |:------:| ----- |:----:|
| &nbsp;mandatorAddress | string | 委托人地址 |  是   |

#### 返回值
| 字段名       |  字段类型  | 参数描述  |
| --------- |:------:| ----- |
| &nbsp;返回值 | string | 代理人地址 |
#### Example request data: 

_**request path:**_
略

_**request form data:**_
无

#### Example response data: 
略

### 2.1 查询理事会成员数量结构
#### Cmd: /cg/council/number
_**详细描述: 查询理事会成员数量结构**_
#### HttpMethod: GET

#### 参数列表
无参数

#### 返回值
| 字段名                    | 字段类型 | 参数描述   |
| ---------------------- |:----:| ------ |
| &nbsp;operationsNumber | int  | 运营理事数量 |
| &nbsp;managementNumber | int  | 管理理事数量 |
| &nbsp;technologyNumber | int  | 技术理事数量 |
#### Example request data: 

_**request path:**_
略

_**request form data:**_
无

#### Example response data: 
略

### 2.2 查询理事会成员列表
#### Cmd: /cg/council/member/list
_**详细描述: 查询理事会成员列表**_
#### HttpMethod: GET

#### 参数列表
无参数

#### 返回值
| 字段名               |  字段类型   | 参数描述                |
| ----------------- |:-------:| ------------------- |
| &nbsp;logoUrl     | string  | 头像URL               |
| &nbsp;address     | string  | 理事成员地址              |
| &nbsp;name        | string  | 理事成员名称              |
| &nbsp;type        |  byte   | 理事类型 1:管理 2:运营 3:技术 |
| &nbsp;isCouncil   | boolean | 是否是理事成员             |
| &nbsp;desc        | string  | 理事成员介绍              |
| &nbsp;votesNumber | string  | 票数                  |
#### Example request data: 

_**request path:**_
略

_**request form data:**_
无

#### Example response data: 
略

### 2.3 理事详情
#### Cmd: /cg/council/member/{address}
_**详细描述: 理事详情**_
#### HttpMethod: GET

#### 参数列表
| 参数名           |  参数类型  | 参数描述   | 是否必填 |
| ------------- |:------:| ------ |:----:|
| &nbsp;address | string | 理事成员地址 |  是   |

#### 返回值
| 字段名               |  字段类型   | 参数描述                |
| ----------------- |:-------:| ------------------- |
| &nbsp;address     | string  | 地址                  |
| &nbsp;name        | string  | 名称                  |
| &nbsp;votesNumber | string  | 票数                  |
| &nbsp;type        |  byte   | 理事类型 1:管理 2:运营 3:技术 |
| &nbsp;isCouncil   | boolean | 是否是理事成员             |
| &nbsp;email       | string  | 邮箱                  |
| &nbsp;logoUrl     | string  | 头像URL               |
| &nbsp;desc        | string  | 介绍                  |
#### Example request data: 

_**request path:**_
略

_**request form data:**_
无

#### Example response data: 
略

### 3.1 查询提案列表
#### Cmd: /cg/proposal/list
_**详细描述: 查询提案列表**_
#### HttpMethod: POST

#### Form json data: 
```json
{
  "page" : 1,
  "pageSize" : 11,
  "type" : null,
  "status" : null,
  "title" : null
}
```

#### 参数列表
| 参数名                                                            |         参数类型          | 参数描述                                             | 是否必填 |
| -------------------------------------------------------------- |:---------------------:| ------------------------------------------------ |:----:|
| &nbsp;查询提案列表                                                   | proposalistpagesearch | 查询提案列表表单                                         |  是   |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;page     |        integer        | 当前页                                              |  是   |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;pageSize |        integer        | 分页大小                                             |  是   |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;type     |         byte          | 提案类型 0:全部, 1:角色, 2:系统参数, 3:社区基金, 4:其他            |  是   |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;status   |         byte          | 提案状态 0:全部, 1:审核中, 2:审核拒绝, 3:投票中, 4:投票通过, 5:投票未通过 |  是   |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;title    |        string         | 提案标题                                             |  是   |

#### 返回值
| 字段名              |  字段类型   | 参数描述                             |
| ---------------- |:-------:| -------------------------------- |
| &nbsp;proposalId | integer | 提案ID                             |
| &nbsp;logoUrl    | string  | 提案图标URL                          |
| &nbsp;title      | string  | 提案标题                             |
| &nbsp;desc       | string  | 提案介绍                             |
| &nbsp;address    | string  | 提案人地址                            |
| &nbsp;type       | string  | 提案类型 1:角色 2:系统参数 3:社区基金 4:其他类型   |
| &nbsp;status     | string  | 提案状态 0待确认 1未开始，2进行中，3 暂停挂起 4 已结束 |
| &nbsp;startTime  |  long   | 提案开始时间                           |
| &nbsp;endTime    |  long   | 提案结束时间                           |
#### Example request data: 

_**request path:**_
略

_**request form data:**_
无

#### Example response data: 
略

### 3.2 查询提案详情
#### Cmd: /cg/proposal/{proposalId}
_**详细描述: 查询提案详情**_
#### HttpMethod: GET

#### 参数列表
| 参数名              | 参数类型 | 参数描述 | 是否必填 |
| ---------------- |:----:| ---- |:----:|
| &nbsp;proposalId | int  | 提案ID |  是   |

#### 返回值
| 字段名                                                               |      字段类型       | 参数描述                             |
| ----------------------------------------------------------------- |:---------------:| -------------------------------- |
| &nbsp;proposalId                                                  |     integer     | 提案ID                             |
| &nbsp;logoUrl                                                     |     string      | 提案图标URL                          |
| &nbsp;title                                                       |     string      | 提案标题                             |
| &nbsp;desc                                                        |     string      | 提案介绍                             |
| &nbsp;address                                                     |     string      | 提案人地址                            |
| &nbsp;type                                                        |     string      | 提案类型 1:角色 2:系统参数 3:社区基金 4:其他类型   |
| &nbsp;status                                                      |     string      | 提案状态 0待确认 1未开始，2进行中，3 暂停挂起 4 已结束 |
| &nbsp;startTime                                                   |      long       | 提案开始时间                           |
| &nbsp;endTime                                                     |      long       | 提案结束时间                           |
| &nbsp;ordinaryVoteDtoList                                         | list&lt;object> | 关联的普通投票列表                        |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;voteId      |     integer     | 普通投票ID                           |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;title       |     string      | 普通投票标题                           |
| &nbsp;favour                                                      |     object      | 同意选项                             |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;itemId      |     integer     | 选项ID                             |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;content     |     string      | 选项内容                             |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;votesNumber |     string      | 投票数                              |
| &nbsp;against                                                     |     object      | 不同意选项                            |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;itemId      |     integer     | 选项ID                             |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;content     |     string      | 选项内容                             |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;votesNumber |     string      | 投票数                              |
| &nbsp;abstention                                                  |     object      | 弃权选项                             |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;itemId      |     integer     | 选项ID                             |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;content     |     string      | 选项内容                             |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;votesNumber |     string      | 投票数                              |
#### Example request data: 

_**request path:**_
略

_**request form data:**_
无

#### Example response data: 
略

### 3.3 查询提案选项详情
#### Cmd: /cg/proposal/{proposalId}/{itemId}
_**详细描述: 查询提案选项详情**_
#### HttpMethod: GET

#### 参数列表
| 参数名              | 参数类型 | 参数描述   | 是否必填 |
| ---------------- |:----:| ------ |:----:|
| &nbsp;proposalId | int  | 提案ID   |  是   |
| &nbsp;itemId     | int  | 提案选项ID |  是   |

#### 返回值
| 字段名                                                               |      字段类型       | 参数描述  |
| ----------------------------------------------------------------- |:---------------:| ----- |
| &nbsp;voterList                                                   | list&lt;object> | 投票人列表 |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;address     |     string      | 投票人地址 |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;alias       |     string      | 投票人别名 |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;votesNumber |     string      | 投票数   |
#### Example request data: 

_**request path:**_
略

_**request form data:**_
无

#### Example response data: 
略

### 3.4 查询所有提案
#### Cmd: /cg/proposal/all
_**详细描述: 查询所有提案**_
#### HttpMethod: GET

#### Form json data: 
```json
{
  "page" : 1,
  "pageSize" : 11,
  "type" : null,
  "status" : null,
  "title" : null
}
```

#### 参数列表
| 参数名                                                            |         参数类型          | 参数描述                                             | 是否必填 |
| -------------------------------------------------------------- |:---------------------:| ------------------------------------------------ |:----:|
| &nbsp;查询所有提案                                                   | proposalistpagesearch | 查询所有提案表单                                         |  是   |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;page     |        integer        | 当前页                                              |  是   |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;pageSize |        integer        | 分页大小                                             |  是   |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;type     |         byte          | 提案类型 0:全部, 1:角色, 2:系统参数, 3:社区基金, 4:其他            |  是   |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;status   |         byte          | 提案状态 0:全部, 1:审核中, 2:审核拒绝, 3:投票中, 4:投票通过, 5:投票未通过 |  是   |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;title    |        string         | 提案标题                                             |  是   |

#### 返回值
| 字段名              |  字段类型   | 参数描述 |
| ---------------- |:-------:| ---- |
| &nbsp;proposalId | integer | 提案ID |
| &nbsp;title      | string  | 提案标题 |
#### Example request data: 

_**request path:**_
略

_**request form data:**_
无

#### Example response data: 
略

### 4.1 查询普通投票列表
#### Cmd: /cg/vote/list
_**详细描述: 查询普通投票列表**_
#### HttpMethod: POST

#### Form json data: 
```json
{
  "page" : 1,
  "pageSize" : 11,
  "status" : null,
  "title" : null
}
```

#### 参数列表
| 参数名                                                            |        参数类型        | 参数描述                     | 是否必填 |
| -------------------------------------------------------------- |:------------------:| ------------------------ |:----:|
| &nbsp;查询普通投票列表                                                 | votelistpagesearch | 查询普通投票列表表单               |  是   |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;page     |      integer       | 当前页                      |  是   |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;pageSize |      integer       | 分页大小                     |  是   |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;status   |        byte        | 投票状态 0:全部, 1:投票中, 2:投票结束 |  是   |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;title    |       string       | 投票标题                     |  是   |

#### 返回值
| 字段名                  |  字段类型   | 参数描述                             |
| -------------------- |:-------:| -------------------------------- |
| &nbsp;voteId         | integer | 投票ID                             |
| &nbsp;logoUrl        | string  | 投票图标URL                          |
| &nbsp;title          | string  | 投票标题                             |
| &nbsp;desc           | string  | 投票介绍                             |
| &nbsp;creatorAddress | string  | 创建人地址                            |
| &nbsp;status         | string  | 投票状态 0待确认 1未开始，2进行中，3 暂停挂起 4 已结束 |
| &nbsp;startTime      |  long   | 投票开始时间                           |
| &nbsp;endTime        |  long   | 投票结束时间                           |
#### Example request data: 

_**request path:**_
略

_**request form data:**_
无

#### Example response data: 
略

### 4.2 查询投票详情
#### Cmd: /cg/vote/{voteId}
_**详细描述: 查询投票详情**_
#### HttpMethod: GET

#### 参数列表
| 参数名          | 参数类型 | 参数描述 | 是否必填 |
| ------------ |:----:| ---- |:----:|
| &nbsp;voteId | int  | 投票ID |  是   |

#### 返回值
| 字段名                                                               |      字段类型       | 参数描述                             |
| ----------------------------------------------------------------- |:---------------:| -------------------------------- |
| &nbsp;voteId                                                      |     integer     | 投票ID                             |
| &nbsp;logoUrl                                                     |     string      | 投票图标URL                          |
| &nbsp;title                                                       |     string      | 投票标题                             |
| &nbsp;desc                                                        |     string      | 投票介绍                             |
| &nbsp;creatorAddress                                              |     string      | 创建人地址                            |
| &nbsp;status                                                      |     string      | 投票状态 0待确认 1未开始，2进行中，3 暂停挂起 4 已结束 |
| &nbsp;startTime                                                   |      long       | 投票开始时间                           |
| &nbsp;endTime                                                     |      long       | 投票结束时间                           |
| &nbsp;hasMultipleSelect                                           |     boolean     | 单选或者是多选                          |
| &nbsp;minSelectCount                                              |     integer     | 至少可选几个选项                         |
| &nbsp;maxSelectCount                                              |     integer     | 最多可选几个选项                         |
| &nbsp;voteCanModify                                               |     boolean     | 投票后是否允许改票                        |
| &nbsp;deposit                                                     |     string      | 保证金(押金)                          |
| &nbsp;proposal                                                    |     object      | 关联的提案                            |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;proposalId  |     integer     | 提案ID                             |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;title       |     string      | 提案标题                             |
| &nbsp;itemList                                                    | list&lt;object> | 选项列表                             |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;itemId      |     integer     | 选项ID                             |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;content     |     string      | 选项内容                             |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;votesNumber |     string      | 投票数                              |
#### Example request data: 

_**request path:**_
略

_**request form data:**_
无

#### Example response data: 
略

### 4.3 查询投票选项详情
#### Cmd: /cg/vote/{voteId}/{itemId}
_**详细描述: 查询投票选项详情**_
#### HttpMethod: GET

#### 参数列表
| 参数名          | 参数类型 | 参数描述   | 是否必填 |
| ------------ |:----:| ------ |:----:|
| &nbsp;voteId | int  | 投票ID   |  是   |
| &nbsp;itemId | int  | 投票选项ID |  是   |

#### 返回值
| 字段名                                                               |      字段类型       | 参数描述  |
| ----------------------------------------------------------------- |:---------------:| ----- |
| &nbsp;voterList                                                   | list&lt;object> | 投票人列表 |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;address     |     string      | 投票人地址 |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;alias       |     string      | 投票人别名 |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;votesNumber |     string      | 投票数   |
#### Example request data: 

_**request path:**_
略

_**request form data:**_
无

#### Example response data: 
略

### 5.1 查询我的投票记录列表
#### Cmd: /cg/voter/record/list
_**详细描述: 查询我的投票记录列表**_
#### HttpMethod: POST

#### Form json data: 
```json
{
  "page" : 1,
  "pageSize" : 11,
  "address" : null,
  "type" : null,
  "title" : null
}
```

#### 参数列表
| 参数名                                                            |      参数类型       | 参数描述                         | 是否必填 |
| -------------------------------------------------------------- |:---------------:| ---------------------------- |:----:|
| &nbsp;查询我的投票记录列表                                               | voterpagesearch | 查询我的投票记录列表表单                 |  是   |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;page     |     integer     | 当前页                          |  是   |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;pageSize |     integer     | 分页大小                         |  是   |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;address  |     string      | 投票人地址                        |  是   |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;type     |      byte       | 类型 0:全部, 1:理事会, 2:提案, 3:普通投票 |  是   |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;title    |     string      | 标题                           |  是   |

#### 返回值
| 字段名               |  字段类型  | 参数描述                   |
| ----------------- |:------:| ---------------------- |
| &nbsp;type        |  byte  | 类型 1:理事会, 2:提案, 3:普通投票 |
| &nbsp;title       | string | 标题                     |
| &nbsp;votesNumber | string | 投票数                    |
| &nbsp;voteTime    |  long  | 投票时间                   |
#### Example request data: 

_**request path:**_
略

_**request form data:**_
无

#### Example response data: 
略

