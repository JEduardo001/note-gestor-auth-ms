package com.SwSOFTWARE.authMs.controller;

import com.SwSOFTWARE.authMs.conostants.ApiBase;
import com.SwSOFTWARE.authMs.dto.api.DtoResponseApiWithData;
import com.SwSOFTWARE.authMs.dto.role.DtoCreateRole;
import com.SwSOFTWARE.authMs.dto.role.DtoUpdateRole;
import com.SwSOFTWARE.authMs.mapper.RoleMapper;
import com.SwSOFTWARE.authMs.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiBase.apiBase + "auth/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService,RoleMapper roleMapper){
        this.roleService = roleService;
    }

    @GetMapping()
    public ResponseEntity<DtoResponseApiWithData> getAllRoles(@RequestParam Integer page, @RequestParam Integer size){
        return ResponseEntity.status(HttpStatus.OK).body(new DtoResponseApiWithData<>(
                HttpStatus.OK.value(),
                "Roles obtained",
                roleService.getAllRole(page,size)
        ));
    }

    @GetMapping("/{idRole}")
    public ResponseEntity<DtoResponseApiWithData> getRole(@PathVariable Long idRole){
        return ResponseEntity.status(HttpStatus.OK).body(new DtoResponseApiWithData(
                HttpStatus.OK.value(),
                "Role obtained",
                roleService.getRole(idRole)
        ));
    }

    @PostMapping()
    public ResponseEntity<DtoResponseApiWithData> createRole(@Valid @RequestBody DtoCreateRole request){
        return ResponseEntity.status(HttpStatus.CREATED).body(new DtoResponseApiWithData(
                HttpStatus.CREATED.value(),
                "Role Created",
                roleService.createRole(request)
        ));
    }

    @PutMapping()
    public ResponseEntity<DtoResponseApiWithData> updateRole(@Valid @RequestBody DtoUpdateRole request){
        return ResponseEntity.status(HttpStatus.OK).body(new DtoResponseApiWithData(
                HttpStatus.OK.value(),
                "Role updated",
                roleService.updateRole(request)
        ));
    }
}
