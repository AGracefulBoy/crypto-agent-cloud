package org.dromara.agent.service;

import org.dromara.agent.domain.vo.AgentVendorConfigVo;
import org.dromara.agent.domain.bo.AgentVendorConfigBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 供应商配置Service接口
 *
 * @author ruoyi
 */
public interface IAgentVendorConfigService {

    /**
     * 查询供应商配置
     */
    AgentVendorConfigVo queryById(Long id);

    /**
     * 根据供应商ID查询配置
     */
    AgentVendorConfigVo queryByVendorId(Long vendorId);

    /**
     * 查询供应商配置列表
     */
    TableDataInfo<AgentVendorConfigVo> queryPageList(AgentVendorConfigBo bo, PageQuery pageQuery);

    /**
     * 查询供应商配置列表
     */
    List<AgentVendorConfigVo> queryList(AgentVendorConfigBo bo);

    /**
     * 新增供应商配置
     */
    Boolean insertByBo(AgentVendorConfigBo bo);

    /**
     * 修改供应商配置
     */
    Boolean updateByBo(AgentVendorConfigBo bo);

    /**
     * 校验并批量删除供应商配置信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
