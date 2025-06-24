package org.dromara.agent.service;

import org.dromara.agent.domain.vo.AgentModelVendorVo;
import org.dromara.agent.domain.bo.AgentModelVendorBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 模型供应商Service接口
 *
 * @author ruoyi
 */
public interface IAgentModelVendorService {

    /**
     * 查询模型供应商
     */
    AgentModelVendorVo queryById(Long id);

    /**
     * 查询模型供应商列表
     */
    TableDataInfo<AgentModelVendorVo> queryPageList(AgentModelVendorBo bo, PageQuery pageQuery);

    /**
     * 查询模型供应商列表
     */
    List<AgentModelVendorVo> queryList(AgentModelVendorBo bo);

    /**
     * 新增模型供应商
     */
    Boolean insertByBo(AgentModelVendorBo bo);

    /**
     * 修改模型供应商
     */
    Boolean updateByBo(AgentModelVendorBo bo);

    /**
     * 校验并批量删除模型供应商信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 校验供应商名称是否唯一
     */
    boolean checkNameUnique(AgentModelVendorBo bo);
}
