package com.sinosoft.datasupplyplatform.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.sinosoft.visualization.common.basic.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * LoginController
 * 用户登录接口
 * @author wangyl
 * Description:
 * Created in: 2020/11/2
 * Modified by:
 */
@Slf4j
@ResponseResult
@RestController
@CrossOrigin
@RequestMapping(value = "/user")
public class LoginController {

    @ResponseBody
    @PostMapping(value = "/login")
    public JSONObject login() {
        log.info("----------------登录接口通畅-------------------");
        return JSONObject.parseObject("{\n" +
                "\t\"id\": \"1111\",\n" +
                "\t\"name\": \"wyl\",\n" +
                "\t\"username\": \"admin\",\n" +
                "\t\"password\": \"\",\n" +
                "\t\"avatar\": \"https://gw.alipayobjects.com/zos/rmsportal/jZUIxmJycoymBprLOUbT.png\",\n" +
                "\t\"status\": 1,\n" +
                "\t\"telephone\": \"\",\n" +
                "\t\"lastLoginIp\": \"27.154.74.117\",\n" +
                "\t\"lastLoginTime\": 1534837621348,\n" +
                "\t\"creatorId\": \"admin\",\n" +
                "\t\"createTime\": 1497160610259,\n" +
                "\t\"deleted\": 0,\n" +
                "\t\"roleId\": \"admin\",\n" +
                "\t\"lang\": \"zh-CN\",\n" +
                "\t\"token\": \"4291d7da9005377ec9aec4a71ea837f\"\n" +
                "}");
    }

    @ResponseBody
    @GetMapping(value = "/getInfo")
    public JSONObject getInfo() {
        log.info("----------------用户信息接口通畅-------------------");
        JSONObject userInfo = JSONObject.parseObject(("{\n" +
                "    \"id\": \"4291d7da9005377ec9aec4a71ea837f\",\n" +
                "    \"name\": \"天野远子\",\n" +
                "    \"username\": \"admin\",\n" +
                "    \"password\": \"\",\n" +
                "    \"avatar\": \"/avatar2.jpg\",\n" +
                "    \"status\": 1,\n" +
                "    \"telephone\": \"\",\n" +
                "    \"lastLoginIp\": \"27.154.74.117\",\n" +
                "    \"lastLoginTime\": 1534837621348,\n" +
                "    \"creatorId\": \"admin\",\n" +
                "    \"createTime\": 1497160610259,\n" +
                "    \"merchantCode\": \"TLif2btpzg079h15bk\",\n" +
                "    \"deleted\": 0,\n" +
                "    \"roleId\": \"admin\",\n" +
                "    \"role\": {}\n" +
                "  }"));

        JSONObject roleObj = JSONObject.parseObject("{\n" +
                "\t\"id\": \"admin\",\n" +
                "\t\"name\": \"管理员\",\n" +
                "\t\"describe\": \"拥有所有权限\",\n" +
                "\t\"status\": 1,\n" +
                "\t\"creatorId\": \"system\",\n" +
                "\t\"createTime\": 1497160610259,\n" +
                "\t\"deleted\": 0,\n" +
                "\t\"permissions\": [{\n" +
                "\t\t\"roleId\": \"admin\",\n" +
                "\t\t\"permissionId\": \"dashboard\",\n" +
                "\t\t\"permissionName\": \"仪表盘\",\n" +
                "\t\t\"actions\": [{\n" +
                "\t\t\t\"action\": \"add\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"新增\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"query\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"查询\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"get\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"详情\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"update\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"修改\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"delete\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"删除\"\n" +
                "\t\t}],\n" +
                "\t\t\"actionEntitySet\": [{\n" +
                "\t\t\t\"action\": \"add\",\n" +
                "\t\t\t\"describe\": \"新增\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"query\",\n" +
                "\t\t\t\"describe\": \"查询\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"get\",\n" +
                "\t\t\t\"describe\": \"详情\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"update\",\n" +
                "\t\t\t\"describe\": \"修改\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"delete\",\n" +
                "\t\t\t\"describe\": \"删除\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}],\n" +
                "\t\t\"actionList\": null,\n" +
                "\t\t\"dataAccess\": null\n" +
                "\t}, {\n" +
                "\t\t\"roleId\": \"admin\",\n" +
                "\t\t\"permissionId\": \"exception\",\n" +
                "\t\t\"permissionName\": \"异常页面权限\",\n" +
                "\t\t\"actions\": [{\n" +
                "\t\t\t\"action\": \"add\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"新增\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"query\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"查询\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"get\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"详情\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"update\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"修改\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"delete\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"删除\"\n" +
                "\t\t}],\n" +
                "\t\t\"actionEntitySet\": [{\n" +
                "\t\t\t\"action\": \"add\",\n" +
                "\t\t\t\"describe\": \"新增\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"query\",\n" +
                "\t\t\t\"describe\": \"查询\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"get\",\n" +
                "\t\t\t\"describe\": \"详情\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"update\",\n" +
                "\t\t\t\"describe\": \"修改\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"delete\",\n" +
                "\t\t\t\"describe\": \"删除\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}],\n" +
                "\t\t\"actionList\": null,\n" +
                "\t\t\"dataAccess\": null\n" +
                "\t}, {\n" +
                "\t\t\"roleId\": \"admin\",\n" +
                "\t\t\"permissionId\": \"result\",\n" +
                "\t\t\"permissionName\": \"结果权限\",\n" +
                "\t\t\"actions\": [{\n" +
                "\t\t\t\"action\": \"add\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"新增\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"query\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"查询\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"get\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"详情\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"update\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"修改\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"delete\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"删除\"\n" +
                "\t\t}],\n" +
                "\t\t\"actionEntitySet\": [{\n" +
                "\t\t\t\"action\": \"add\",\n" +
                "\t\t\t\"describe\": \"新增\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"query\",\n" +
                "\t\t\t\"describe\": \"查询\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"get\",\n" +
                "\t\t\t\"describe\": \"详情\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"update\",\n" +
                "\t\t\t\"describe\": \"修改\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"delete\",\n" +
                "\t\t\t\"describe\": \"删除\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}],\n" +
                "\t\t\"actionList\": null,\n" +
                "\t\t\"dataAccess\": null\n" +
                "\t}, {\n" +
                "\t\t\"roleId\": \"admin\",\n" +
                "\t\t\"permissionId\": \"profile\",\n" +
                "\t\t\"permissionName\": \"详细页权限\",\n" +
                "\t\t\"actions\": [{\n" +
                "\t\t\t\"action\": \"add\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"新增\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"query\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"查询\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"get\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"详情\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"update\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"修改\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"delete\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"删除\"\n" +
                "\t\t}],\n" +
                "\t\t\"actionEntitySet\": [{\n" +
                "\t\t\t\"action\": \"add\",\n" +
                "\t\t\t\"describe\": \"新增\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"query\",\n" +
                "\t\t\t\"describe\": \"查询\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"get\",\n" +
                "\t\t\t\"describe\": \"详情\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"update\",\n" +
                "\t\t\t\"describe\": \"修改\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"delete\",\n" +
                "\t\t\t\"describe\": \"删除\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}],\n" +
                "\t\t\"actionList\": null,\n" +
                "\t\t\"dataAccess\": null\n" +
                "\t}, {\n" +
                "\t\t\"roleId\": \"admin\",\n" +
                "\t\t\"permissionId\": \"table\",\n" +
                "\t\t\"permissionName\": \"表格权限\",\n" +
                "\t\t\"actions\": [{\n" +
                "\t\t\t\"action\": \"add\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"新增\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"import\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"导入\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"get\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"详情\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"update\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"修改\"\n" +
                "\t\t}],\n" +
                "\t\t\"actionEntitySet\": [{\n" +
                "\t\t\t\"action\": \"add\",\n" +
                "\t\t\t\"describe\": \"新增\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"import\",\n" +
                "\t\t\t\"describe\": \"导入\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"get\",\n" +
                "\t\t\t\"describe\": \"详情\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"update\",\n" +
                "\t\t\t\"describe\": \"修改\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}],\n" +
                "\t\t\"actionList\": null,\n" +
                "\t\t\"dataAccess\": null\n" +
                "\t}, {\n" +
                "\t\t\"roleId\": \"admin\",\n" +
                "\t\t\"permissionId\": \"form\",\n" +
                "\t\t\"permissionName\": \"表单权限\",\n" +
                "\t\t\"actions\": [{\n" +
                "\t\t\t\"action\": \"add\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"新增\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"get\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"详情\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"query\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"查询\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"update\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"修改\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"delete\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"删除\"\n" +
                "\t\t}],\n" +
                "\t\t\"actionEntitySet\": [{\n" +
                "\t\t\t\"action\": \"add\",\n" +
                "\t\t\t\"describe\": \"新增\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"get\",\n" +
                "\t\t\t\"describe\": \"详情\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"query\",\n" +
                "\t\t\t\"describe\": \"查询\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"update\",\n" +
                "\t\t\t\"describe\": \"修改\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"delete\",\n" +
                "\t\t\t\"describe\": \"删除\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}],\n" +
                "\t\t\"actionList\": null,\n" +
                "\t\t\"dataAccess\": null\n" +
                "\t}, {\n" +
                "\t\t\"roleId\": \"admin\",\n" +
                "\t\t\"permissionId\": \"order\",\n" +
                "\t\t\"permissionName\": \"订单管理\",\n" +
                "\t\t\"actions\": [{\n" +
                "\t\t\t\"action\": \"add\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"新增\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"query\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"查询\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"get\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"详情\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"update\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"修改\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"delete\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"删除\"\n" +
                "\t\t}],\n" +
                "\t\t\"actionEntitySet\": [{\n" +
                "\t\t\t\"action\": \"add\",\n" +
                "\t\t\t\"describe\": \"新增\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"query\",\n" +
                "\t\t\t\"describe\": \"查询\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"get\",\n" +
                "\t\t\t\"describe\": \"详情\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"update\",\n" +
                "\t\t\t\"describe\": \"修改\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"delete\",\n" +
                "\t\t\t\"describe\": \"删除\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}],\n" +
                "\t\t\"actionList\": null,\n" +
                "\t\t\"dataAccess\": null\n" +
                "\t}, {\n" +
                "\t\t\"roleId\": \"admin\",\n" +
                "\t\t\"permissionId\": \"permission\",\n" +
                "\t\t\"permissionName\": \"权限管理\",\n" +
                "\t\t\"actions\": [{\n" +
                "\t\t\t\"action\": \"add\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"新增\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"get\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"详情\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"update\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"修改\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"delete\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"删除\"\n" +
                "\t\t}],\n" +
                "\t\t\"actionEntitySet\": [{\n" +
                "\t\t\t\"action\": \"add\",\n" +
                "\t\t\t\"describe\": \"新增\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"get\",\n" +
                "\t\t\t\"describe\": \"详情\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"update\",\n" +
                "\t\t\t\"describe\": \"修改\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"delete\",\n" +
                "\t\t\t\"describe\": \"删除\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}],\n" +
                "\t\t\"actionList\": null,\n" +
                "\t\t\"dataAccess\": null\n" +
                "\t}, {\n" +
                "\t\t\"roleId\": \"admin\",\n" +
                "\t\t\"permissionId\": \"role\",\n" +
                "\t\t\"permissionName\": \"角色管理\",\n" +
                "\t\t\"actions\": [{\n" +
                "\t\t\t\"action\": \"add\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"新增\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"get\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"详情\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"update\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"修改\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"delete\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"删除\"\n" +
                "\t\t}],\n" +
                "\t\t\"actionEntitySet\": [{\n" +
                "\t\t\t\"action\": \"add\",\n" +
                "\t\t\t\"describe\": \"新增\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"get\",\n" +
                "\t\t\t\"describe\": \"详情\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"update\",\n" +
                "\t\t\t\"describe\": \"修改\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"delete\",\n" +
                "\t\t\t\"describe\": \"删除\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}],\n" +
                "\t\t\"actionList\": null,\n" +
                "\t\t\"dataAccess\": null\n" +
                "\t}, {\n" +
                "\t\t\"roleId\": \"admin\",\n" +
                "\t\t\"permissionId\": \"table\",\n" +
                "\t\t\"permissionName\": \"桌子管理\",\n" +
                "\t\t\"actions\": [{\n" +
                "\t\t\t\"action\": \"add\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"新增\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"get\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"详情\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"query\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"查询\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"update\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"修改\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"delete\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"删除\"\n" +
                "\t\t}],\n" +
                "\t\t\"actionEntitySet\": [{\n" +
                "\t\t\t\"action\": \"add\",\n" +
                "\t\t\t\"describe\": \"新增\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"get\",\n" +
                "\t\t\t\"describe\": \"详情\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"query\",\n" +
                "\t\t\t\"describe\": \"查询\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"update\",\n" +
                "\t\t\t\"describe\": \"修改\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"delete\",\n" +
                "\t\t\t\"describe\": \"删除\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}],\n" +
                "\t\t\"actionList\": null,\n" +
                "\t\t\"dataAccess\": null\n" +
                "\t}, {\n" +
                "\t\t\"roleId\": \"admin\",\n" +
                "\t\t\"permissionId\": \"user\",\n" +
                "\t\t\"permissionName\": \"用户管理\",\n" +
                "\t\t\"actions\": [{\n" +
                "\t\t\t\"action\": \"add\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"新增\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"import\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"导入\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"get\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"详情\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"update\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"修改\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"delete\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"删除\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"export\",\n" +
                "\t\t\t\"defaultCheck\": false,\n" +
                "\t\t\t\"describe\": \"导出\"\n" +
                "\t\t}],\n" +
                "\t\t\"actionEntitySet\": [{\n" +
                "\t\t\t\"action\": \"add\",\n" +
                "\t\t\t\"describe\": \"新增\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"import\",\n" +
                "\t\t\t\"describe\": \"导入\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"get\",\n" +
                "\t\t\t\"describe\": \"详情\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"update\",\n" +
                "\t\t\t\"describe\": \"修改\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"delete\",\n" +
                "\t\t\t\"describe\": \"删除\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"export\",\n" +
                "\t\t\t\"describe\": \"导出\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}],\n" +
                "\t\t\"actionList\": null,\n" +
                "\t\t\"dataAccess\": null\n" +
                "\t}]\n" +
                "}");

        JSONObject permissions = JSONObject.parseObject("{\n" +
                "\t\"roleId\": \"admin\",\n" +
                "\t\"permissionId\": \"support\",\n" +
                "\t\"permissionName\": \"超级模块\",\n" +
                "\t\"actions\": [{\n" +
                "\t\t\"action\": \"add\",\n" +
                "\t\t\"defaultCheck\": false,\n" +
                "\t\t\"describe\": \"新增\"\n" +
                "\t}, {\n" +
                "\t\t\"action\": \"import\",\n" +
                "\t\t\"defaultCheck\": false,\n" +
                "\t\t\"describe\": \"导入\"\n" +
                "\t}, {\n" +
                "\t\t\"action\": \"get\",\n" +
                "\t\t\"defaultCheck\": false,\n" +
                "\t\t\"describe\": \"详情\"\n" +
                "\t}, {\n" +
                "\t\t\"action\": \"update\",\n" +
                "\t\t\"defaultCheck\": false,\n" +
                "\t\t\"describe\": \"修改\"\n" +
                "\t}, {\n" +
                "\t\t\"action\": \"delete\",\n" +
                "\t\t\"defaultCheck\": false,\n" +
                "\t\t\"describe\": \"删除\"\n" +
                "\t}, {\n" +
                "\t\t\"action\": \"export\",\n" +
                "\t\t\"defaultCheck\": false,\n" +
                "\t\t\"describe\": \"导出\"\n" +
                "\t}],\n" +
                "\t\"actionEntitySet\": [{\n" +
                "\t\t\"actionEntitySet\": [{\n" +
                "\t\t\t\"action\": \"add\",\n" +
                "\t\t\t\"describe\": \"新增\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"import\",\n" +
                "\t\t\t\"describe\": \"导入\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"get\",\n" +
                "\t\t\t\"describe\": \"详情\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"update\",\n" +
                "\t\t\t\"describe\": \"修改\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"delete\",\n" +
                "\t\t\t\"describe\": \"删除\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}, {\n" +
                "\t\t\t\"action\": \"export\",\n" +
                "\t\t\t\"describe\": \"导出\",\n" +
                "\t\t\t\"defaultCheck\": false\n" +
                "\t\t}],\n" +
                "\t\t\"actionList\": null,\n" +
                "\t\t\"dataAccess\": null\n" +
                "\t}]\n" +
                "}");
        roleObj.getJSONArray("permissions").add(permissions);
        userInfo.put("role", roleObj);
        return userInfo;
    }
}
