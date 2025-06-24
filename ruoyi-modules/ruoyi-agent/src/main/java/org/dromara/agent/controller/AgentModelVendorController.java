package org.dromara.agent.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.apikey.SaApiKeyUtil;
import cn.dev33.satoken.apikey.model.ApiKeyModel;
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
import org.dromara.agent.domain.bo.AgentModelVendorBo;
import org.dromara.agent.domain.vo.AgentModelVendorVo;
import org.dromara.agent.service.IAgentModelVendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * 模型供应商管理
 *
 * @author ruoyi
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/modelVendor")
public class AgentModelVendorController extends BaseController {

    private final IAgentModelVendorService modelVendorService;
    /**
     * 查询模型供应商列表
     */
    @SaCheckPermission("agent:modelVendor:list")
    @GetMapping("/list")
    public TableDataInfo<AgentModelVendorVo> list(AgentModelVendorBo bo, PageQuery pageQuery) {

        return modelVendorService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出模型供应商列表
     */
    @SaCheckPermission("agent:modelVendor:export")
    @Log(title = "模型供应商管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(AgentModelVendorBo bo, HttpServletResponse response) {
        List<AgentModelVendorVo> list = modelVendorService.queryList(bo);
        ExcelUtil.exportExcel(list, "模型供应商", AgentModelVendorVo.class, response);
    }

    /**
     * 获取模型供应商详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("agent:modelVendor:query")
    @GetMapping("/{id}")
    public R<AgentModelVendorVo> getInfo(@NotNull(message = "主键不能为空")
                                         @PathVariable Long id) {
        return R.ok(modelVendorService.queryById(id));
    }

    /**
     * 新增模型供应商
     */
    @SaCheckPermission("agent:modelVendor:add")
    @Log(title = "模型供应商管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody AgentModelVendorBo bo) {
        if (!modelVendorService.checkNameUnique(bo)) {
            return R.fail("新增模型供应商'" + bo.getName() + "'失败，供应商名称已存在");
        }
        return toAjax(modelVendorService.insertByBo(bo));
    }

    /**
     * 修改模型供应商
     */
    @SaCheckPermission("agent:modelVendor:edit")
    @Log(title = "模型供应商管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody AgentModelVendorBo bo) {
        if (!modelVendorService.checkNameUnique(bo)) {
            return R.fail("修改模型供应商'" + bo.getName() + "'失败，供应商名称已存在");
        }
        return toAjax(modelVendorService.updateByBo(bo));
    }

    /**
     * 删除模型供应商
     *
     * @param ids 主键串
     */
    @SaCheckPermission("agent:modelVendor:remove")
    @Log(title = "模型供应商管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(modelVendorService.deleteWithValidByIds(List.of(ids), true));
    }
}
