package org.dromara.agent.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.agent.domain.bo.AgentInstallableVendorBo;
import org.dromara.agent.domain.vo.AgentInstallableVendorVo;
import org.dromara.agent.service.IAgentInstallableVendorService;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.web.core.BaseController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 可安装供应商管理
 *
 * @author ruoyi
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/agent/installableVendor")
public class AgentInstallableVendorController extends BaseController {

    private final IAgentInstallableVendorService installableVendorService;

    /**
     * 查询可安装供应商列表
     */
    @SaCheckPermission("agent:installableVendor:list")
    @GetMapping("/list")
    public TableDataInfo<AgentInstallableVendorVo> list(AgentInstallableVendorBo bo, PageQuery pageQuery) {
        return installableVendorService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出可安装供应商列表
     */
    @SaCheckPermission("agent:installableVendor:export")
    @Log(title = "可安装供应商管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(AgentInstallableVendorBo bo, HttpServletResponse response) {
        List<AgentInstallableVendorVo> list = installableVendorService.queryList(bo);
        ExcelUtil.exportExcel(list, "可安装供应商", AgentInstallableVendorVo.class, response);
    }

    /**
     * 获取可安装供应商详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("agent:installableVendor:query")
    @GetMapping("/{id}")
    public R<AgentInstallableVendorVo> getInfo(@NotNull(message = "主键不能为空")
                                               @PathVariable Long id) {
        return R.ok(installableVendorService.queryById(id));
    }

    /**
     * 新增可安装供应商
     */
    @SaCheckPermission("agent:installableVendor:add")
    @Log(title = "可安装供应商管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody AgentInstallableVendorBo bo) {
        if (!installableVendorService.checkNameUnique(bo)) {
            return R.fail("新增可安装供应商'" + bo.getName() + "'失败，供应商名称已存在");
        }
        return toAjax(installableVendorService.insertByBo(bo));
    }

    /**
     * 修改可安装供应商
     */
    @SaCheckPermission("agent:installableVendor:edit")
    @Log(title = "可安装供应商管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody AgentInstallableVendorBo bo) {
        if (!installableVendorService.checkNameUnique(bo)) {
            return R.fail("修改可安装供应商'" + bo.getName() + "'失败，供应商名称已存在");
        }
        return toAjax(installableVendorService.updateByBo(bo));
    }

    /**
     * 安装供应商
     *
     * @param id 主键
     */
    @SaCheckPermission("agent:installableVendor:install")
    @Log(title = "可安装供应商管理", businessType = BusinessType.UPDATE)
    @PostMapping("/install/{id}")
    public R<Void> install(@NotNull(message = "主键不能为空") @PathVariable Long id) {
        return toAjax(installableVendorService.installVendor(id));
    }

    /**
     * 卸载供应商
     *
     * @param id 主键
     */
    @SaCheckPermission("agent:installableVendor:uninstall")
    @Log(title = "可安装供应商管理", businessType = BusinessType.UPDATE)
    @PostMapping("/uninstall/{id}")
    public R<Void> uninstall(@NotNull(message = "主键不能为空") @PathVariable Long id) {
        return toAjax(installableVendorService.uninstallVendor(id));
    }

    /**
     * 删除可安装供应商
     *
     * @param ids 主键串
     */
    @SaCheckPermission("agent:installableVendor:remove")
    @Log(title = "可安装供应商管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(installableVendorService.deleteWithValidByIds(List.of(ids), true));
    }
}
