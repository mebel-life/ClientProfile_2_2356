package org.client.service;


import org.client.common.dto.AvatarDto;

public interface AvatarService {
    AvatarDto addAvatarForClient(String icp);

    AvatarDto getAvatarClient(String icp);

    void setAvatarForClient(AvatarDto avatarDto, String icp);
}
