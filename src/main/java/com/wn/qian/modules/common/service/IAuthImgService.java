package com.wn.qian.modules.common.service;

import java.awt.image.BufferedImage;

public interface IAuthImgService {
    String getSecurityCode();
    BufferedImage createImage(String securityCode);
}
