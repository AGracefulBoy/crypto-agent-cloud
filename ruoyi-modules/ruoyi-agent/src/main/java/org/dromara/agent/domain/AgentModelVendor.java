package org.dromara.agent.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.io.Serial;

/**
 * 模型供应商对象 agent_model_vendor
 *
 * @author ruoyi
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("agent_model_vendor")
public class AgentModelVendor extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 供应商名称
     */
    private String name;

    /**
     * 供应商类型（如openai、anthropic等）
     */
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

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    private String delFlag;
}
