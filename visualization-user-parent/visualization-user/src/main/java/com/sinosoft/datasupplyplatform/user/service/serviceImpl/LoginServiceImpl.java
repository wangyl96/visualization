package com.sinosoft.datasupplyplatform.user.service.serviceImpl;

import com.sinosoft.datasupplyplatform.user.api.dto.UserDTO;
import com.sinosoft.datasupplyplatform.user.api.entity.SysUser;
import com.sinosoft.datasupplyplatform.user.repository.SysUserRepository;
import com.sinosoft.datasupplyplatform.user.service.LoginService;
import com.sinosoft.visualization.common.util.ConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * LoginServiceImpl
 *
 * @author wangyl
 * Description:
 * Created in: 2020/8/24
 * Modified by:
 */
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private SysUserRepository sysUserRepository;

    /**
     * 获取并更新用户信息
     * @return
     */
    @Override
    public UserDTO login() {
        SysUser sysUser = sysUserRepository.findById("571349816924311552").get();
        UserDTO userDTO = ConvertUtil.trans(sysUser, UserDTO.class);
        return userDTO;
    }
}
