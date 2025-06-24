package org.dromara.agent.domain.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import org.dromara.agent.domain.AgentInstallableVendor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 可安装供应商视图对象 agent_installable_vendor
 *
 * @author ruoyi
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = AgentInstallableVendor.class)
public class AgentInstallableVendorVo implements Serializable {

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
     * 模型数量
     */
    @ExcelProperty(value = "模型数量")
    private Integer modelCount;

    /**
     * 支持功能
     */
    @ExcelProperty(value = "支持功能")
    private String supportFeatures;

    /**
     * 安装状态（0未安装 1已安装）
     */
    @ExcelProperty(value = "安装状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "0=未安装,1=已安装")
    private String installStatus;

    /**
     * 描述
     */
    @ExcelProperty(value = "描述")
    private String description;
}
