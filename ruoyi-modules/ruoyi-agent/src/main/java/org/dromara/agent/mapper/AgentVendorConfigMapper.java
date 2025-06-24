package org.dromara.agent.mapper;

import org.dromara.agent.domain.AgentVendorConfig;
import org.dromara.agent.domain.vo.AgentVendorConfigVo;
import org.apache.ibatis.annotations.Mapper;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;

/**
 * 供应商配置Mapper接口
 *
 * @author ruoyi
 */
@Mapper
public interface AgentVendorConfigMapper extends BaseMapperPlus<AgentVendorConfig, AgentVendorConfigVo> {

}
