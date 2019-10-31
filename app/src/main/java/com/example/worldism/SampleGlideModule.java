package com.example.worldism;

import com.bumptech.glide.module.AppGlideModule;

public class SampleGlideModule extends AppGlideModule {

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}
