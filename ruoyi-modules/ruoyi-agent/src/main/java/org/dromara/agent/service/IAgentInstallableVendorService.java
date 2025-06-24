package org.dromara.agent.service;

import org.dromara.agent.domain.vo.AgentInstallableVendorVo;
import org.dromara.agent.domain.bo.AgentInstallableVendorBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 可安装供应商Service接口
 *
 * @author ruoyi
 */
public interface IAgentInstallableVendorService {

    /**
     * 查询可安装供应商
     */
    AgentInstallableVendorVo queryById(Long id);

    /**
     * 查询可安装供应商列表
     */
    TableDataInfo<AgentInstallableVendorVo> queryPageList(AgentInstallableVendorBo bo, PageQuery pageQuery);

    /**
     * 查询可安装供应商列表
     */
    List<AgentInstallableVendorVo> queryList(AgentInstallableVendorBo bo);

    /**
     * 新增可安装供应商
     */
    Boolean insertByBo(AgentInstallableVendorBo bo);

    /**
     * 修改可安装供应商
     */
    Boolean updateByBo(AgentInstallableVendorBo bo);

    /**
     * 安装供应商
     */
    Boolean installVendor(Long id);

    /**
     * 卸载供应商
     */
    Boolean uninstallVendor(Long id);

    /**
     * 校验并批量删除可安装供应商信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 校验供应商名称是否唯一
     */
    boolean checkNameUnique(AgentInstallableVendorBo bo);
}
