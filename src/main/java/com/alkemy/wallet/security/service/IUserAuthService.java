package com.alkemy.wallet.security.service;

import com.alkemy.wallet.dto.UserDto;

public interface IUserAuthService {

    public boolean save(UserDto userDto) throws Exception;
}
