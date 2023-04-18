package org.client.service;

import org.client.common.dto.AvatarDto;

public interface AvatarService {
    AvatarDto getAvatar(String icp);

    void setAvatar(AvatarDto avatarDto, String icp);

}
