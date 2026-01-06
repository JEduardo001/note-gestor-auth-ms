package com.SwSOFTWARE.authMs.client;

import com.SwSOFTWARE.authMs.dto.api.DtoResponseApiWithData;
import com.SwSOFTWARE.authMs.dto.user.DtoCreateUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-ms")
public interface UserClient {

    @PostMapping("/api/user")
    DtoResponseApiWithData createUser(@RequestBody DtoCreateUser data);


}
