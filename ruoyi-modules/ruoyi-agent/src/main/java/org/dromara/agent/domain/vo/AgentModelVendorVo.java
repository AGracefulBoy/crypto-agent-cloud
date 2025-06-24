package org.dromara.agent.domain.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import org.dromara.agent.domain.AgentModelVendor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 模型供应商视图对象 agent_model_vendor
 *
 * @author ruoyi
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = AgentModelVendor.class)
public class AgentModelVendorVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ExcelProperty(value = "主键")
    private Long id;

    /**
     * 供应商名称
     */
    @ExcelProperty(value = "供应商名称")
    private String name;

    /**
     * 供应商类型（如openai、anthropic等）
     */
    @ExcelProperty(value = "供应商类型")
    private String type;

    /**
     * 状态（1启用 0禁用）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "1=启用,0=禁用")
    private String status;

    /**
     * 已配置模型数量
     */
    @ExcelProperty(value = "已配置模型数量")
    private Integer modelCount;

    /**
     * API配置状态（0未配置 1已配置）
     */
    @ExcelProperty(value = "API配置状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "0=未配置,1=已配置")
    private String apiConfigured;

    /**
     * 支持功能（如LLM,TTS等，逗号分隔）
     */
    @ExcelProperty(value = "支持功能")
    private String supportFeatures;

    /**
     * 描述
     */
    @ExcelProperty(value = "描述")
    private String description;
}
