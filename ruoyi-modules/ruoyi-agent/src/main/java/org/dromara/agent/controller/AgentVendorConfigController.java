package org.dromara.agent.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.web.core.BaseController;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.agent.domain.bo.AgentVendorConfigBo;
import org.dromara.agent.domain.vo.AgentVendorConfigVo;
import org.dromara.agent.service.IAgentVendorConfigService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * 供应商配置管理
 *
 * @author ruoyi
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/agent/vendorConfig")
public class AgentVendorConfigController extends BaseController {

    private final IAgentVendorConfigService vendorConfigService;

    /**
     * 查询供应商配置列表
     */
    @SaCheckPermission("agent:vendorConfig:list")
    @GetMapping("/list")
    public TableDataInfo<AgentVendorConfigVo> list(AgentVendorConfigBo bo, PageQuery pageQuery) {
        return vendorConfigService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出供应商配置列表
     */
    @SaCheckPermission("agent:vendorConfig:export")
    @Log(title = "供应商配置管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(AgentVendorConfigBo bo, HttpServletResponse response) {
        List<AgentVendorConfigVo> list = vendorConfigService.queryList(bo);
        ExcelUtil.exportExcel(list, "供应商配置", AgentVendorConfigVo.class, response);
    }

    /**
     * 获取供应商配置详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("agent:vendorConfig:query")
    @GetMapping("/{id}")
    public R<AgentVendorConfigVo> getInfo(@NotNull(message = "主键不能为空")
                                          @PathVariable Long id) {
        return R.ok(vendorConfigService.queryById(id));
    }

    /**
     * 根据供应商ID获取配置信息
     *
     * @param vendorId 供应商ID
     */
    @SaCheckPermission("agent:vendorConfig:query")
    @GetMapping("/byVendor/{vendorId}")
    public R<AgentVendorConfigVo> getInfoByVendorId(@NotNull(message = "供应商ID不能为空")
                                                    @PathVariable Long vendorId) {
        return R.ok(vendorConfigService.queryByVendorId(vendorId));
    }

    /**
     * 新增供应商配置
     */
    @SaCheckPermission("agent:vendorConfig:add")
    @Log(title = "供应商配置管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody AgentVendorConfigBo bo) {
        return toAjax(vendorConfigService.insertByBo(bo));
    }

    /**
     * 修改供应商配置
     */
    @SaCheckPermission("agent:vendorConfig:edit")
    @Log(title = "供应商配置管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody AgentVendorConfigBo bo) {
        return toAjax(vendorConfigService.updateByBo(bo));
    }

    /**
     * 删除供应商配置
     *
     * @param ids 主键串
     */
    @SaCheckPermission("agent:vendorConfig:remove")
    @Log(title = "供应商配置管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(vendorConfigService.deleteWithValidByIds(List.of(ids), true));
    }
}
