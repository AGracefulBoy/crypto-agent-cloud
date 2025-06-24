package org.dromara.agent.mapper;

import org.dromara.agent.domain.AgentInstallableVendor;
import org.dromara.agent.domain.vo.AgentInstallableVendorVo;
import org.apache.ibatis.annotations.Mapper;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;

/**
 * 可安装供应商Mapper接口
 *
 * @author ruoyi
 */
@Mapper
public interface AgentInstallableVendorMapper extends BaseMapperPlus<AgentInstallableVendor, AgentInstallableVendorVo> {

}
