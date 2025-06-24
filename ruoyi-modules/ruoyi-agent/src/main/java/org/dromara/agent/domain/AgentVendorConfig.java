package org.dromara.agent.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.io.Serial;

/**
 * 供应商配置对象 agent_vendor_config
 *
 * @author ruoyi
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("agent_vendor_config")
public class AgentVendorConfig extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 供应商ID
     */
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

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    private String delFlag;
}
