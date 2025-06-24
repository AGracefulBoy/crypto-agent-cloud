package org.dromara.agent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.agent.domain.AgentVendorConfig;
import org.dromara.agent.domain.bo.AgentVendorConfigBo;
import org.dromara.agent.domain.vo.AgentVendorConfigVo;
import org.dromara.agent.mapper.AgentVendorConfigMapper;
import org.dromara.agent.service.IAgentVendorConfigService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * 供应商配置Service业务层处理
 *
 * @author ruoyi
 */
@RequiredArgsConstructor
@Service
public class AgentVendorConfigServiceImpl implements IAgentVendorConfigService {

    private final AgentVendorConfigMapper baseMapper;

    /**
     * 查询供应商配置
     */
    @Override
    public AgentVendorConfigVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 根据供应商ID查询配置
     */
    @Override
    public AgentVendorConfigVo queryByVendorId(Long vendorId) {
        return baseMapper.selectVoOne(new LambdaQueryWrapper<AgentVendorConfig>()
            .eq(AgentVendorConfig::getVendorId, vendorId));
    }

    /**
     * 查询供应商配置列表
     */
    @Override
    public TableDataInfo<AgentVendorConfigVo> queryPageList(AgentVendorConfigBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<AgentVendorConfig> lqw = buildQueryWrapper(bo);
        Page<AgentVendorConfigVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询供应商配置列表
     */
    @Override
    public List<AgentVendorConfigVo> queryList(AgentVendorConfigBo bo) {
        LambdaQueryWrapper<AgentVendorConfig> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<AgentVendorConfig> buildQueryWrapper(AgentVendorConfigBo bo) {
        LambdaQueryWrapper<AgentVendorConfig> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getVendorId() != null, AgentVendorConfig::getVendorId, bo.getVendorId());
        lqw.like(StringUtils.isNotBlank(bo.getApiKey()), AgentVendorConfig::getApiKey, bo.getApiKey());
        lqw.eq(StringUtils.isNotBlank(bo.getOrgId()), AgentVendorConfig::getOrgId, bo.getOrgId());
        lqw.like(StringUtils.isNotBlank(bo.getApiBaseUrl()), AgentVendorConfig::getApiBaseUrl, bo.getApiBaseUrl());
        lqw.like(StringUtils.isNotBlank(bo.getDescription()), AgentVendorConfig::getDescription, bo.getDescription());
        lqw.eq(StringUtils.isNotBlank(bo.getConfigStatus()), AgentVendorConfig::getConfigStatus, bo.getConfigStatus());
        return lqw;
    }

    /**
     * 新增供应商配置
     */
    @Override
    public Boolean insertByBo(AgentVendorConfigBo bo) {
        AgentVendorConfig add = MapstructUtils.convert(bo, AgentVendorConfig.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改供应商配置
     */
    @Override
    public Boolean updateByBo(AgentVendorConfigBo bo) {
        AgentVendorConfig update = MapstructUtils.convert(bo, AgentVendorConfig.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(AgentVendorConfig entity) {
        // TODO 做一些数据校验，如唯一约束
    }

    /**
     * 批量删除供应商配置
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            // TODO 做一些业务上的校验，判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
