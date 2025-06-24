package org.dromara.agent.domain.bo;

import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.agent.domain.AgentModelVendor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 模型供应商业务对象 agent_model_vendor
 *
 * @author ruoyi
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = AgentModelVendor.class, reverseConvertGenerate = false)
public class AgentModelVendorBo extends BaseEntity {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 供应商名称
     */
    @NotBlank(message = "供应商名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

    /**
     * 供应商类型（如openai、anthropic等）
     */
    @NotBlank(message = "供应商类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String type;

    /**
     * 状态（1启用 0禁用）
     */
    private String status;

    /**
     * 已配置模型数量
     */
    private Integer modelCount;

    /**
     * API配置状态（0未配置 1已配置）
     */
    private String apiConfigured;

    /**
     * 支持功能（如LLM,TTS等，逗号分隔）
     */
    private String supportFeatures;

    /**
     * 描述
     */
    private String description;
}
