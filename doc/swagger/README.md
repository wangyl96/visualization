# 原文章参考地址https://blog.csdn.net/wyb880501/article/details/79576784
1.这里使用的版本：springfox-swagger2（2.4）springfox-swagger-ui （2.4） 
2.这里是说明常用注解的含义和基本用法（也就是说已经对swagger进行集成完成） 
没有集成的请参见 
SpringBoot集成springfox-swagger2构建restful API 
SpringMVC集成springfox-swagger2构建restful API 
官网WIKI 
常用注解： 
- @Api()用于类； 
表示标识这个类是swagger的资源 
- @ApiOperation()用于方法； 
表示一个http请求的操作 
- @ApiParam()用于方法，参数，字段说明； 
表示对参数的添加元数据（说明或是否必填等） 
- @ApiModel()用于类 
表示对类进行说明，用于参数用实体类接收 
- @ApiModelProperty()用于方法，字段 
表示对model属性的说明或者数据操作更改 
- @ApiIgnore()用于类，方法，方法参数 
表示这个方法或者类被忽略 
- @ApiImplicitParam() 用于方法 
表示单独的请求参数 
- @ApiImplicitParams() 用于方法，包含多个 @ApiImplicitParam

具体使用举例说明： 
@Api() 
用于类；表示标识这个类是swagger的资源 
tags–表示说明 
value–也是说明，可以使用tags替代 
但是tags如果有多个值，会生成多个list

@Api(value="用户controller",tags={"用户操作接口"})
@RestController
public class UserController { }
 

效果图： 
![Image text](https://github.com/your_github/address/blob/master/image/1.png)
这里写图片描述

@ApiOperation() 用于方法；表示一个http请求的操作 
value用于方法描述 
notes用于提示内容 
tags可以重新分组（视情况而用） 
@ApiParam() 用于方法，参数，字段说明；表示对参数的添加元数据（说明或是否必填等） 
name–参数名 
value–参数说明 
required–是否必填

@Api(value="用户controller",tags={"用户操作接口"})
@RestController
public class UserController { @ApiOperation(value="获取用户信息",tags={"获取用户信息copy"},notes="注意问题点") @GetMapping("/getUserInfo") public User getUserInfo(@ApiParam(name="id",value="用户id",required=true) Long id,@ApiParam(name="username",value="用户名") String username) { // userService可忽略，是业务逻辑 User user = userService.getUserInfo(); return user; } }
 

效果图： 
![Image text](https://github.com/your_github/address/blob/master/image/1.png)
这里写图片描述

@ApiModel()用于类 ；表示对类进行说明，用于参数用实体类接收 
value–表示对象名 
description–描述 
都可省略 
@ApiModelProperty()用于方法，字段； 表示对model属性的说明或者数据操作更改 
value–字段说明 
name–重写属性名字 
dataType–重写属性类型 
required–是否必填 
example–举例说明 
hidden–隐藏

@ApiModel(value="user对象",description="用户对象user")
public class User implements Serializable{ private static final long serialVersionUID = 1L; @ApiModelProperty(value="用户名",name="username",example="xingguo") private String username; @ApiModelProperty(value="状态",name="state",required=true) private Integer state; private String password; private String nickName; private Integer isDeleted; @ApiModelProperty(value="id数组",hidden=true) private String[] ids; private List<String> idList; //省略get/set }
 

 

  @ApiOperation("更改用户信息")
  @PostMapping("/updateUserInfo")
  public int updateUserInfo(@RequestBody @ApiParam(name="用户对象",value="传入json格式",required=true) User user){ int num = userService.updateUserInfo(user); return num; }
 

 

效果图： 
这里写图片描述
![Image text](https://github.com/your_github/address/blob/master/image/1.png)
这里写图片描述

@ApiIgnore()用于类或者方法上，可以不被swagger显示在页面上 
比较简单, 这里不做举例

@ApiImplicitParam() 用于方法 
表示单独的请求参数 
@ApiImplicitParams() 用于方法，包含多个 @ApiImplicitParam 
name–参数ming 
value–参数说明 
dataType–数据类型 
paramType–参数类型 
example–举例说明

  @ApiOperation("查询测试")
  @GetMapping("select")
  //@ApiImplicitParam(name="name",value="用户名",dataType="String", paramType = "query") @ApiImplicitParams({ @ApiImplicitParam(name="name",value="用户名",dataType="string", paramType = "query",example="xingguo"), @ApiImplicitParam(name="id",value="用户id",dataType="long", paramType = "query")}) public void select(){ }
 

效果图： 
![Image text](https://github.com/your_github/address/blob/master/image/1.png)
这里写图片描述