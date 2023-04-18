package org.client.util;


import lombok.AllArgsConstructor;
import org.client.common.dto.AvatarDto;
import org.client.common.entity.Avatar;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AvatarUtils {
    private ModelMapper modelMapper;
    public Avatar convertToEntity(AvatarDto avatarDto) {
        Avatar avatar = modelMapper.map(avatarDto, Avatar.class);
        return avatar;
    }

    public  AvatarDto convertToDto(Avatar avatar) {
        AvatarDto avatarDto = modelMapper.map(avatar, AvatarDto.class);
        return avatarDto;
    }
}

