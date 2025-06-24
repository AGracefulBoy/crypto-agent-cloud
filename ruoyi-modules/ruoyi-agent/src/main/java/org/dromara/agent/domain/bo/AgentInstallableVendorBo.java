package org.dromara.agent.domain.bo;

import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.agent.domain.AgentInstallableVendor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 可安装供应商业务对象 agent_installable_vendor
 *
 * @author ruoyi
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = AgentInstallableVendor.class, reverseConvertGenerate = false)
public class AgentInstallableVendorBo extends BaseEntity {

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
     * 模型数量
     */
    private Integer modelCount;

    /**
     * 支持功能
     */
    private String supportFeatures;

    /**
     * 安装状态（0未安装 1已安装）
     */
    private String installStatus;

    /**
     * 描述
     */
    private String description;
}
