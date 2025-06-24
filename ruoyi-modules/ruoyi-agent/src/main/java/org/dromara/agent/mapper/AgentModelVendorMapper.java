package org.dromara.agent.mapper;

import org.dromara.agent.domain.AgentModelVendor;
import org.dromara.agent.domain.vo.AgentModelVendorVo;
import org.apache.ibatis.annotations.Mapper;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;

/**
 * 模型供应商Mapper接口
 *
 * @author ruoyi
 */
@Mapper
public interface AgentModelVendorMapper extends BaseMapperPlus<AgentModelVendor, AgentModelVendorVo> {

}
