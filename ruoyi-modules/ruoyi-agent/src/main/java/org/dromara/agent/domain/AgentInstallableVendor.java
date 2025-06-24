package org.dromara.agent.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.io.Serial;

/**
 * 可安装供应商对象 agent_installable_vendor
 *
 * @author ruoyi
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("agent_installable_vendor")
public class AgentInstallableVendor extends BaseEntity {

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

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    private String delFlag;
}
