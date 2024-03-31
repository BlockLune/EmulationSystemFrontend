package com.njupt.epsm.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.njupt.epsm.common.*;
import com.njupt.epsm.dto.SysConfigDto;
import com.njupt.epsm.dto.SysRoleDto;
import com.njupt.epsm.dto.SysUserDto;
import com.njupt.epsm.mapper.SysConfigMapper;
import com.njupt.epsm.po.*;
import com.njupt.epsm.req.*;
import com.njupt.epsm.mapper.SysUserMapper;
import com.njupt.epsm.mapper.SysRoleMapper;
import com.njupt.epsm.util.BeanUtil;
import com.njupt.epsm.util.SeqIdUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class SystemService {

    private static final Logger log = LoggerFactory.getLogger(SystemService.class);

    @Value("${user.initpassword}")
    private String initpassword;

    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysConfigMapper sysConfigMapper;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public SysRole selectRoleByParam(String name) {
        SysRoleExample sysRoleExample = new SysRoleExample();
        sysRoleExample.createCriteria().andNameEqualTo(name);
        List<SysRole> sysRoles = sysRoleMapper.selectByExample(sysRoleExample);
        return CollectionUtils.isEmpty(sysRoles) ? null : sysRoles.get(0);
    }

    public SysUser selectUserByParam(String userName, String loginName) {
        SysUserExample sysUserExample = new SysUserExample();
        if (!StringUtils.isEmpty(userName)) {
            sysUserExample.createCriteria().andUserNameEqualTo(userName);
        }
        if (!StringUtils.isEmpty(loginName)) {
            sysUserExample.createCriteria().andLoginNameEqualTo(loginName);
        }
        List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
        return CollectionUtils.isEmpty(sysUsers) ? null : sysUsers.get(0);
    }


    public SysConfig selectConfigByParam(String configItem) {
        SysConfigExample sysConfigExample = new SysConfigExample();
        if (!StringUtils.isEmpty(configItem)) {
            sysConfigExample.createCriteria().andConfigNameEqualTo(configItem);
        }
        List<SysConfig> sysConfigs = sysConfigMapper.selectByExample(sysConfigExample);
        return CollectionUtils.isEmpty(sysConfigs) ? null : sysConfigs.get(0);
    }

    public CommonResult createRole(CreateRoleReq createRoleReq) {
        SysRole role = selectRoleByParam(createRoleReq.getRoleName());
        if (ObjectUtils.isNotEmpty(role)) {
            return CommonResult.failed("角色已存在");
        }

        SysRole sysRole = new SysRole();
        sysRole.setId(SeqIdUtil.getId());
        sysRole.setAuth(createRoleReq.getAuth());
        sysRole.setName(createRoleReq.getRoleName());
        sysRole.setCreateTime(new Date());
        sysRole.setUpdateTime(new Date());
        sysRoleMapper.insertSelective(sysRole);
        return CommonResult.success();
    }

    public CommonResult updateRole(UpdateRoleReq updateRoleReq) {
        SysRole role = selectRoleByParam(updateRoleReq.getRoleName());
        if (ObjectUtils.isNotEmpty(role) && role.getId() != updateRoleReq.getRoleId().longValue()) {
            return CommonResult.failed("角色已存在");
        }
        SysRole sysRole = sysRoleMapper.selectByPrimaryKey(updateRoleReq.getRoleId());
        sysRole.setAuth(updateRoleReq.getAuth());
        sysRole.setName(updateRoleReq.getRoleName());
        sysRole.setUpdateTime(new Date());
        sysRoleMapper.updateByPrimaryKeySelective(sysRole);
        return CommonResult.success();
    }

    public CommonResult selectRoleByPage(int pageNum, int pageSize) {
        Page page = PageHelper.startPage(pageNum, pageSize);
        SysRoleExample sysRoleExample = new SysRoleExample();
        sysRoleExample.setOrderByClause("create_time desc");
        List<SysRole> sysRoles = sysRoleMapper.selectByExample(sysRoleExample);
        return CommonResult.success(new PageDto(pageNum, pageSize, page.getTotal(), BeanUtil.copyBeanList(sysRoles, SysRoleDto.class)));
    }

    public CommonResult listRoles() {
        List<SysRole> sysRoles = sysRoleMapper.selectByExample(new SysRoleExample());
        return CommonResult.success(sysRoles);
    }

    public CommonResult deleteRoleById(Long roleId) {
        sysRoleMapper.deleteByPrimaryKey(roleId);
        return CommonResult.success();
    }

    public CommonResult createUser(CreateUserReq createUserReq) {
        SysRole sysRole = sysRoleMapper.selectByPrimaryKey(createUserReq.getRoleId());
        if (Objects.isNull(sysRole)) {
            return CommonResult.failed("所选角色不存在");
        }
        SysUser sysUser = selectUserByParam(createUserReq.getUserName(), createUserReq.getLoginName());
        if (!Objects.isNull(sysUser)) {
            return CommonResult.failed("用户名或者登录名重复");
        }
        SysUser user = new SysUser();
        user.setId(SeqIdUtil.getId());
        user.setRoleId(createUserReq.getRoleId());
        user.setLoginName(createUserReq.getLoginName());
        user.setUserName(createUserReq.getUserName());
        user.setPassword(initpassword);
        user.setCreateTime(new Date());
        user.setStatus(YesOrNoEnum.YES.getCode());
        user.setUpdateTime(new Date());
        sysUserMapper.insertSelective(user);
        return CommonResult.success();
    }

    public CommonResult deleteUserById(Long userId) {
        sysUserMapper.deleteByPrimaryKey(userId);
        return CommonResult.success();
    }

    public CommonResult updateUser(UpdateUserReq updateUserReq) {
        SysUser sysUser = selectUserByParam(updateUserReq.getUserName(), updateUserReq.getLoginName());
        if (ObjectUtils.isNotEmpty(sysUser) && sysUser.getId() != updateUserReq.getUserId().longValue()) {
            return CommonResult.failed("用户名或者登录名重复");
        }
        if (updateUserReq.getRoleId() != null) {
            SysRole sysRole = sysRoleMapper.selectByPrimaryKey(updateUserReq.getRoleId());
            if (sysRole == null) return CommonResult.failed("角色不存在");
        }
        SysUser user = sysUserMapper.selectByPrimaryKey(updateUserReq.getUserId());
        user.setRoleId(updateUserReq.getRoleId());
        user.setUserName(updateUserReq.getUserName());
        user.setLoginName(updateUserReq.getLoginName());
        user.setUpdateTime(new Date());
        sysUserMapper.updateByPrimaryKeySelective(user);
        return CommonResult.success();
    }

    public CommonResult changeUserStatus(Long userId, String status) {
        SysUser user = sysUserMapper.selectByPrimaryKey(userId);
        user.setStatus(status);
        user.setUpdateTime(new Date());
        sysUserMapper.updateByPrimaryKeySelective(user);
        return CommonResult.success();
    }

    public CommonResult selectUserByPage(int pageNum, int pageSize) {
        Page page = PageHelper.startPage(pageNum, pageSize);
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.setOrderByClause("create_time desc");
        List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
        return CommonResult.success(new PageDto(pageNum, pageSize, page.getTotal(), BeanUtil.copyBeanList(sysUsers, SysUserDto.class)));
    }

    public CommonResult createConfig(CreateConfigReq createConfigReq) {
        SysConfig sysConfig = selectConfigByParam(createConfigReq.getConfigItem());
        if (sysConfig != null) return CommonResult.failed("配置项已存在");

        SysConfig config = new SysConfig();
        config.setId(SeqIdUtil.getId());
        config.setConfigName(createConfigReq.getConfigItem());
        config.setConfigValue(createConfigReq.getConfigValue());
        config.setRemark(createConfigReq.getRemark());
        config.setCreateTime(new Date());
        config.setUpdateTime(new Date());
        sysConfigMapper.insertSelective(config);
        return CommonResult.success();
    }

    public CommonResult updateConfig(UpdateConfigReq updateConfigReq) {
        SysConfig sysConfig = selectConfigByParam(updateConfigReq.getConfigItem());
        if (sysConfig != null && sysConfig.getId().longValue() != updateConfigReq.getConfigId().longValue()) {
            return CommonResult.failed("配置项已存在");
        }

        SysConfig config = sysConfigMapper.selectByPrimaryKey(updateConfigReq.getConfigId());
        if (config == null) {
            return CommonResult.failed("配置不存在");
        }

        config.setConfigName(updateConfigReq.getConfigItem());
        config.setConfigValue(updateConfigReq.getConfigValue());
        config.setRemark(updateConfigReq.getRemark());
        config.setUpdateTime(new Date());
        sysConfigMapper.updateByPrimaryKeySelective(config);
        return CommonResult.success();
    }

    public CommonResult deleteConfig(Long configId) {
        sysConfigMapper.deleteByPrimaryKey(configId);
        return CommonResult.success();
    }

    public CommonResult selectConfigByPage(int pageNum, int pageSize) {
        Page page = PageHelper.startPage(pageNum, pageSize);
        SysConfigExample sysConfigExample = new SysConfigExample();
        sysConfigExample.setOrderByClause("create_time desc");
        List<SysConfig> sysConfigs = sysConfigMapper.selectByExample(sysConfigExample);
        return CommonResult.success(new PageDto(pageNum, pageSize, page.getTotal(), BeanUtil.copyBeanList(sysConfigs, SysConfigDto.class)));
    }

    public CommonResult login(String loginName, String password) {
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andLoginNameEqualTo(loginName);
        List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
        if (CollectionUtils.isEmpty(sysUsers)) {
            return CommonResult.failed("登录名错误");
        }
        SysUser sysUser = sysUsers.get(0);
        if (!StringUtils.equals(password, sysUser.getPassword())) {
            return CommonResult.failed("密码错误");
        }

        //返回token
        String token = jwtTokenUtil.generateToken(new JwtUserInfo(String.valueOf(sysUser.getId()), sysUser.getUserName(), sysUser.getRoleId()));
        return CommonResult.success(token, "登录成功");
    }

    public CommonResult getUserInfo(Long userId) {
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        SysUserDto sysUserDto = BeanUtil.copyProperties(sysUser, SysUserDto.class);
        SysRole sysRole = sysRoleMapper.selectByPrimaryKey(sysUser.getRoleId());
        sysUserDto.setAuth(sysRole != null ? sysRole.getAuth() : "");
        return CommonResult.success(sysUserDto);
    }
}
