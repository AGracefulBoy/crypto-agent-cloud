package org.dromara.agent.domain.bo;

import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.agent.domain.AgentVendorConfig;

import jakarta.validation.constraints.NotNull;

/**
 * 供应商配置业务对象 agent_vendor_config
 *
 * @author ruoyi
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = AgentVendorConfig.class, reverseConvertGenerate = false)
public class AgentVendorConfigBo extends BaseEntity {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 供应商ID
     */
    @NotNull(message = "供应商ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long vendorId;

    /**
     * API Key
     */
    private String apiKey;

    /**
     * 组织ID
     */
    private String orgId;

    /**
     * API Base URL
     */
    private String apiBaseUrl;

    /**
     * 描述
     */
    private String description;

    /**
     * 配置状态（0未配置 1已配置）
     */
    private String configStatus;

    /**
     * 安全提示
     */
    private String securityTip;

    /**
     * 外部链接
     */
    private String externalLink;
}
