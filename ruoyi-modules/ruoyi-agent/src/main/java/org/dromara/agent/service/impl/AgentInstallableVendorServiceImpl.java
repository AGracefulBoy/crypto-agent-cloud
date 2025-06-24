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
import org.dromara.agent.domain.AgentInstallableVendor;
import org.dromara.agent.domain.bo.AgentInstallableVendorBo;
import org.dromara.agent.domain.vo.AgentInstallableVendorVo;
import org.dromara.agent.mapper.AgentInstallableVendorMapper;
import org.dromara.agent.service.IAgentInstallableVendorService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * 可安装供应商Service业务层处理
 *
 * @author ruoyi
 */
@RequiredArgsConstructor
@Service
public class AgentInstallableVendorServiceImpl implements IAgentInstallableVendorService {

    private final AgentInstallableVendorMapper baseMapper;

    /**
     * 查询可安装供应商
     */
    @Override
    public AgentInstallableVendorVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询可安装供应商列表
     */
    @Override
    public TableDataInfo<AgentInstallableVendorVo> queryPageList(AgentInstallableVendorBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<AgentInstallableVendor> lqw = buildQueryWrapper(bo);
        Page<AgentInstallableVendorVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询可安装供应商列表
     */
    @Override
    public List<AgentInstallableVendorVo> queryList(AgentInstallableVendorBo bo) {
        LambdaQueryWrapper<AgentInstallableVendor> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<AgentInstallableVendor> buildQueryWrapper(AgentInstallableVendorBo bo) {
        LambdaQueryWrapper<AgentInstallableVendor> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getName()), AgentInstallableVendor::getName, bo.getName());
        lqw.eq(bo.getModelCount() != null, AgentInstallableVendor::getModelCount, bo.getModelCount());
        lqw.like(StringUtils.isNotBlank(bo.getSupportFeatures()), AgentInstallableVendor::getSupportFeatures, bo.getSupportFeatures());
        lqw.eq(StringUtils.isNotBlank(bo.getInstallStatus()), AgentInstallableVendor::getInstallStatus, bo.getInstallStatus());
        lqw.like(StringUtils.isNotBlank(bo.getDescription()), AgentInstallableVendor::getDescription, bo.getDescription());
        return lqw;
    }

    /**
     * 新增可安装供应商
     */
    @Override
    public Boolean insertByBo(AgentInstallableVendorBo bo) {
        AgentInstallableVendor add = MapstructUtils.convert(bo, AgentInstallableVendor.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改可安装供应商
     */
    @Override
    public Boolean updateByBo(AgentInstallableVendorBo bo) {
        AgentInstallableVendor update = MapstructUtils.convert(bo, AgentInstallableVendor.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 安装供应商
     */
    @Override
    public Boolean installVendor(Long id) {
        AgentInstallableVendor vendor = baseMapper.selectById(id);
        if (vendor != null) {
            vendor.setInstallStatus("1");
            return baseMapper.updateById(vendor) > 0;
        }
        return false;
    }

    /**
     * 卸载供应商
     */
    @Override
    public Boolean uninstallVendor(Long id) {
        AgentInstallableVendor vendor = baseMapper.selectById(id);
        if (vendor != null) {
            vendor.setInstallStatus("0");
            return baseMapper.updateById(vendor) > 0;
        }
        return false;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(AgentInstallableVendor entity) {
        // TODO 做一些数据校验，如唯一约束
    }

    /**
     * 批量删除可安装供应商
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
    public boolean checkNameUnique(AgentInstallableVendorBo bo) {
        boolean exist = baseMapper.exists(new LambdaQueryWrapper<AgentInstallableVendor>()
            .eq(AgentInstallableVendor::getName, bo.getName())
            .ne(ObjectUtil.isNotNull(bo.getId()), AgentInstallableVendor::getId, bo.getId()));
        return !exist;
    }
}
