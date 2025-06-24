package org.dromara.agent.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.agent.domain.AgentModelVendor;
import org.dromara.agent.domain.bo.AgentModelVendorBo;
import org.dromara.agent.domain.vo.AgentModelVendorVo;
import org.dromara.agent.mapper.AgentModelVendorMapper;
import org.dromara.agent.service.IAgentModelVendorService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * 模型供应商Service业务层处理
 *
 * @author ruoyi
 */
@RequiredArgsConstructor
@Service
public class AgentModelVendorServiceImpl implements IAgentModelVendorService {

    private final AgentModelVendorMapper baseMapper;

    /**
     * 查询模型供应商
     */
    @Override
    public AgentModelVendorVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询模型供应商列表
     */
    @Override
    public TableDataInfo<AgentModelVendorVo> queryPageList(AgentModelVendorBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<AgentModelVendor> lqw = buildQueryWrapper(bo);
        Page<AgentModelVendorVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询模型供应商列表
     */
    @Override
    public List<AgentModelVendorVo> queryList(AgentModelVendorBo bo) {
        LambdaQueryWrapper<AgentModelVendor> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<AgentModelVendor> buildQueryWrapper(AgentModelVendorBo bo) {
        LambdaQueryWrapper<AgentModelVendor> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getName()), AgentModelVendor::getName, bo.getName());
        lqw.eq(StringUtils.isNotBlank(bo.getType()), AgentModelVendor::getType, bo.getType());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), AgentModelVendor::getStatus, bo.getStatus());
        lqw.eq(bo.getModelCount() != null, AgentModelVendor::getModelCount, bo.getModelCount());
        lqw.eq(StringUtils.isNotBlank(bo.getApiConfigured()), AgentModelVendor::getApiConfigured, bo.getApiConfigured());
        lqw.like(StringUtils.isNotBlank(bo.getSupportFeatures()), AgentModelVendor::getSupportFeatures, bo.getSupportFeatures());
        lqw.like(StringUtils.isNotBlank(bo.getDescription()), AgentModelVendor::getDescription, bo.getDescription());
        return lqw;
    }

    /**
     * 新增模型供应商
     */
    @Override
    public Boolean insertByBo(AgentModelVendorBo bo) {
        AgentModelVendor add = MapstructUtils.convert(bo, AgentModelVendor.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改模型供应商
     */
    @Override
    public Boolean updateByBo(AgentModelVendorBo bo) {
        AgentModelVendor update = MapstructUtils.convert(bo, AgentModelVendor.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(AgentModelVendor entity) {
        // TODO 做一些数据校验，如唯一约束
    }

    /**
     * 批量删除模型供应商
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            // TODO 做一些业务上的校验，判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    /**
     * 校验供应商名称是否唯一
     */
    @Override
    public boolean checkNameUnique(AgentModelVendorBo bo) {
        boolean exist = baseMapper.exists(new LambdaQueryWrapper<AgentModelVendor>()
            .eq(AgentModelVendor::getName, bo.getName())
            .ne(ObjectUtil.isNotNull(bo.getId()), AgentModelVendor::getId, bo.getId()));
        return !exist;
    }
}
