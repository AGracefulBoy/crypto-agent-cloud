package org.dromara.agent.domain.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import org.dromara.agent.domain.AgentVendorConfig;

import java.io.Serial;
import java.io.Serializable;

/**
 * 供应商配置视图对象 agent_vendor_config
 *
 * @author ruoyi
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = AgentVendorConfig.class)
public class AgentVendorConfigVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ExcelProperty(value = "主键")
    private Long id;

    /**
     * 供应商ID
     */
    @ExcelProperty(value = "供应商ID")
    private Long vendorId;

    /**
     * API Key
     */
    @ExcelProperty(value = "API Key")
    private String apiKey;

    /**
     * 组织ID
     */
    @ExcelProperty(value = "组织ID")
    private String orgId;

    /**
     * API Base URL
     */
    @ExcelProperty(value = "API Base URL")
    private String apiBaseUrl;

    /**
     * 描述
     */
    @ExcelProperty(value = "描述")
    private String description;

    /**
     * 配置状态（0未配置 1已配置）
     */
    @ExcelProperty(value = "配置状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "0=未配置,1=已配置")
    private String configStatus;

    /**
     * 安全提示
     */
    @ExcelProperty(value = "安全提示")
    private String securityTip;

    /**
     * 外部链接
     */
    @ExcelProperty(value = "外部链接")
    private String externalLink;
}
