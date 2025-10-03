package com.springframework.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClueController {

    /**
     * 张琪  这个用户 拥有如下权限资源：
     * 线索管理
     * 线索管理
     * 线索管理-列表
     * 线索管理-录入
     * 线索管理-编辑
     * 线索管理-查看
     */
//    @RequestMapping(value = "/api/clue/index")
//    public String clueIndex() {
//        return "clueIndex";
//    }
//
//    @PreAuthorize(value = "hasRole('saler')")
//    @RequestMapping(value = "/api/clue/menu")
//    public String clueMenu() {
//        return "clueMenu";
//    }
//
//    @PreAuthorize(value = "hasRole('saler')")
//    @RequestMapping(value = "/api/clue/menu/child")
//    public String clueMenuChild() {
//        return "clueMenuChild";
//    }
//
//    @PreAuthorize(value = "hasRole('saler')")
//    @RequestMapping(value = "/api/clue/list")
//    public String clueList() {
//        return "clueList";
//    }
//
//    @PreAuthorize(value = "hasRole('saler')")
//    @RequestMapping(value = "/api/clue/input")
//    public String clueInput() {
//        return "clueInput";
//    }
//
//    @PreAuthorize(value = "hasRole('saler')")
//    @RequestMapping(value = "/api/clue/edit")
//    public String clueEdit() {
//        return "clueEdit";
//    }
//
//    @PreAuthorize(value = "hasRole('saler')")
//    @RequestMapping(value = "/api/clue/view")
//    public String clueView() {
//        return "clueView";
//    }
//
//    @PreAuthorize(value = "hasRole('admin')")
//    @RequestMapping(value = "/api/clue/del")
//    public String clueDel() {
//        return "clueDel";
//    }
//
//    @PreAuthorize(value = "hasAnyRole('admin', 'manager')") //只要有'admin', 'manager'这两个角色中的任何一个就可以访问
//    @RequestMapping(value = "/api/clue/export")
//    public String clueExport() {
//        return "clueExport";
//    }

    /**
     * 张琪  这个用户 拥有如下权限资源：
     * 线索管理-列表	clue:list
     * 线索管理-录入	clue:add
     * 线索管理-编辑	clue:edit
     * 线索管理-查看	clue:view
     * 线索管理-导入	clue:import
     * 权限标识符的命名：   模块名:功能名(clue:list)   或者   项目名:模块名:功能名(dlyk:clue:list)
     */
    @RequestMapping(value = "/api/clue/index") //不做权限控制
    public String clueIndex() {
        return "clueIndex";
    }

    @RequestMapping(value = "/api/clue/menu")
    public String clueMenu() {
        return "clueMenu";
    }

    @RequestMapping(value = "/api/clue/menu/child")
    public String clueMenuChild() {
        return "clueMenuChild";
    }

    @PreAuthorize(value = "hasAuthority('clue:list')")
    @RequestMapping(value = "/api/clue/list")
    public String clueList() {
        return "clueList";
    }

    @PreAuthorize(value = "hasAuthority('clue:add')")
    @RequestMapping(value = "/api/clue/input")
    public String clueInput() {
        return "clueInput";
    }

    @PreAuthorize(value = "hasAuthority('clue:edit')")
    @RequestMapping(value = "/api/clue/edit")
    public String clueEdit() {
        return "clueEdit";
    }

    @PreAuthorize(value = "hasAuthority('clue:view')")
    @RequestMapping(value = "/api/clue/view")
    public String clueView() {
        return "clueView";
    }

    @PreAuthorize(value = "hasAuthority('clue:import')")
    @RequestMapping(value = "/api/clue/imports")
    public String clueImports() {
        return "clueImports";
    }

    @PreAuthorize(value = "hasAuthority('clue:del')")
    @RequestMapping(value = "/api/clue/del")
    public String clueDel() {
        return "clueDel";
    }

    @PreAuthorize(value = "hasAnyAuthority('clue:export', 'clue:download')") //只要有'admin', 'manager'这两个角色中的任何一个就可以访问
    @RequestMapping(value = "/api/clue/export")
    public String clueExport() {
        return "clueExport";
    }
}
