package com.mcb.iminitializr.support;

import com.mcb.iminitializr.constant.PathEnum;

public interface PathFactoryAware {
    void setPathFactory(PathFactory<PathEnum> factory);
}
